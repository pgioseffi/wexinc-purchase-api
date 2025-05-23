package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPurchaseByIdInteractorTest {

  @InjectMocks private FindPurchaseByIdInteractor instance;

  private final PurchaseGateway purchaseGateway;

  FindPurchaseByIdInteractorTest(@Mock final PurchaseGateway purchaseGatewayParam) {
    this.purchaseGateway = purchaseGatewayParam;
  }

  @Test
  void testShouldNotFindPurchase() {
    Mockito.when(this.purchaseGateway.findById(Constantes.LONG_MIN_VALUE))
        .thenThrow(EntityNotFoundException.class);

    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> this.instance.apply(Constantes.LONG_MIN_VALUE),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseGateway).findById(Constantes.LONG_MIN_VALUE);
  }

  @Test
  void testShouldUpdatePurchase() {
    Mockito.when(this.purchaseGateway.findById(Constantes.LONG_MIN_VALUE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        this.instance.apply(Constantes.LONG_MIN_VALUE),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseGateway).findById(Constantes.LONG_MIN_VALUE);
  }
}
