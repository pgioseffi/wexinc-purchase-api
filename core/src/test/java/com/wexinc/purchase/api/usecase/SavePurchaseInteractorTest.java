package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
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

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                NullPointerException.class,
                () -> this.instance.apply(null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseGateway).save(null));
  }

  @Test
  void testShouldSavePurchase() {
    Mockito.when(this.purchaseGateway.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () ->
            Mockito.verify(this.purchaseGateway)
                .save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE));
  }
}
