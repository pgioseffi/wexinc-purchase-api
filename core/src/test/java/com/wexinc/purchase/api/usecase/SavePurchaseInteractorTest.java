package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SavePurchaseInteractorTest {

  @InjectMocks private SavePurchaseInteractor instance;

  private final PurchaseGateway purchaseGateway;

  SavePurchaseInteractorTest(@Mock final PurchaseGateway purchaseGatewayParam) {
    this.purchaseGateway = purchaseGatewayParam;
  }

  @Test
  void testShouldThrowNullPointerException() {
    Mockito.when(this.purchaseGateway.save(null)).thenThrow(NullPointerException.class);

    Assertions.assertThrows(
        NullPointerException.class,
        () -> this.instance.apply(null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseGateway).save(null);
  }

  @Test
  void testShouldSavePurchase() {
    Mockito.when(this.purchaseGateway.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseGateway).save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
  }
}
