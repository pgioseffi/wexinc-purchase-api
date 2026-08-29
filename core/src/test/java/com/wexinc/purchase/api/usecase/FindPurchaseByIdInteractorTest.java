package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.util.Optional;
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
    Mockito.when(this.purchaseGateway.findById(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(Optional.empty());

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                ConstantsCore.EXCEPTION_MESSAGE_PURCHASE_NOT_FOUND.formatted(
                    CoreTestConstants.LONG_MIN_VALUE),
                Assertions.assertThrowsExactly(
                        EntityNotFoundException.class,
                        () -> this.instance.apply(CoreTestConstants.LONG_MIN_VALUE),
                        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN)
                    .getMessage(),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseGateway).findById(CoreTestConstants.LONG_MIN_VALUE));
  }

  @Test
  void testShouldFindPurchase() {
    Mockito.when(this.purchaseGateway.findById(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(Optional.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE));

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                this.instance.apply(CoreTestConstants.LONG_MIN_VALUE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseGateway).findById(CoreTestConstants.LONG_MIN_VALUE));
  }
}
