package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.mapper.PurchaseCoreMapper;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
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
class UpdatePurchaseInteractorTest {

  @InjectMocks private UpdatePurchaseInteractor instance;

  private final PurchaseGateway purchaseGateway;
  private final PurchaseCoreMapper purchaseCoreMapper;

  UpdatePurchaseInteractorTest(
      @Mock final PurchaseGateway purchaseGatewayParam,
      @Mock final PurchaseCoreMapper purchaseCoreMapperParam) {
    this.purchaseGateway = purchaseGatewayParam;
    this.purchaseCoreMapper = purchaseCoreMapperParam;
  }

  @Test
  void testShouldNotUpdateNonexistentPurchase() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.FALSE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                EntityNotFoundException.class,
                () ->
                    this.instance.apply(
                        CoreTestConstants.LONG_MIN_VALUE,
                        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseGateway).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoMoreInteractions(this.purchaseGateway));
  }

  @Test
  void testShouldUpdatePurchase() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);
    Mockito.when(
            this.purchaseCoreMapper.apply(
                CoreTestConstants.LONG_MIN_VALUE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);
    Mockito.when(this.purchaseGateway.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                this.instance.apply(
                    CoreTestConstants.LONG_MIN_VALUE,
                    PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseGateway).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () ->
            Mockito.verify(this.purchaseCoreMapper)
                .apply(
                    CoreTestConstants.LONG_MIN_VALUE,
                    PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
        () ->
            Mockito.verify(this.purchaseGateway)
                .save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE));
  }

  @Test
  void testShouldThrowNullPointerExceptionInExistsById() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(null)))
        .thenThrow(NullPointerException.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                NullPointerException.class,
                () -> this.instance.apply(null, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseGateway).existsById(null),
        () -> Mockito.verifyNoInteractions(this.purchaseCoreMapper),
        () -> Mockito.verifyNoMoreInteractions(this.purchaseGateway));
  }

  @Test
  void testShouldThrowNullPointerExceptionWhenSaving() {
    Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                NullPointerException.class,
                () -> this.instance.apply(CoreTestConstants.LONG_MIN_VALUE, null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseGateway).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.purchaseCoreMapper),
        () -> Mockito.verifyNoMoreInteractions(this.purchaseGateway));
  }
}
