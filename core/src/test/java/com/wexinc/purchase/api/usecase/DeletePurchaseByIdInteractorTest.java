package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePurchaseByIdInteractorTest {

  @InjectMocks private DeletePurchaseByIdInteractor instance;

  private final PurchaseGateway purchaseGateway;

  DeletePurchaseByIdInteractorTest(@Mock final PurchaseGateway purchaseGatewayParam) {
    this.purchaseGateway = purchaseGatewayParam;
  }

  @Test
  void testShouldNotDeleteNonexistentPurchase() {
    Mockito.when(this.purchaseGateway.existsById(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(Boolean.FALSE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                EntityNotFoundException.class,
                () -> this.instance.accept(Long.MIN_VALUE),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseGateway).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoMoreInteractions(this.purchaseGateway));
  }

  @Test
  void testShouldDeletePurchase() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);
    final var valueCapture = ArgumentCaptor.forClass(Long.class);
    Mockito.doNothing().when(this.purchaseGateway).deleteById(valueCapture.capture());

    this.instance.accept(Long.MIN_VALUE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                Long.MIN_VALUE,
                valueCapture.getValue(),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseGateway).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verify(this.purchaseGateway).deleteById(CoreTestConstants.LONG_MIN_VALUE));
  }
}
