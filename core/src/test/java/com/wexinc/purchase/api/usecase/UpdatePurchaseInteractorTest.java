package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.mapper.PurchaseCoreMapper;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdatePurchaseInteractorTest {

  @InjectMocks private UpdatePurchaseInteractor instance;

  @Mock private PurchaseGateway purchaseGateway;

  @Mock private PurchaseCoreMapper purchaseCoreMapper;

  @Test
  void shouldNotUpdateNonexistentPurchase() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(Constantes.LONG_MIN_VALUE)))
        .thenReturn(Boolean.FALSE);

    Assertions.assertThrows(
        EntityNotFoundException.class,
        () ->
            this.instance.apply(Constantes.LONG_MIN_VALUE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(Constantes.LONG_MIN_VALUE);
    Mockito.verify(this.purchaseGateway, Mockito.times(0))
        .save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
  }

  @Test
  void shouldUpdatePurchase() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(Constantes.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);
    Mockito.when(
            this.purchaseCoreMapper.fromIdAndDTOToDTO(
                Constantes.LONG_MIN_VALUE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
    Mockito.when(this.purchaseGateway.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        this.instance.apply(Constantes.LONG_MIN_VALUE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(Constantes.LONG_MIN_VALUE);
    Mockito.verify(this.purchaseGateway, Mockito.times(1))
        .save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
  }

  @Test
  void shouldThrowNullPointerExceptionInExistsById() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(null)))
        .thenThrow(NullPointerException.class);

    Assertions.assertThrows(
        NullPointerException.class,
        () -> this.instance.apply(null, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(null);
  }

  @Test
  void shouldThrowNullPointerExceptionWhenSaving() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(Constantes.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);

    Assertions.assertThrows(
        NullPointerException.class,
        () -> this.instance.apply(Constantes.LONG_MIN_VALUE, null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(Constantes.LONG_MIN_VALUE);
    Mockito.verify(this.purchaseGateway, Mockito.times(0)).save(null);
  }
}
