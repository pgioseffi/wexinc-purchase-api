package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.FindPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.output.AmericanTreasuryRateExchangeAPIClient;
import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.mapper.EnhancedPurchaseMapper;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.fixture.EnhancedPurchaseDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataDTOFixture;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@ExtendWith(MockitoExtension.class)
class FindEnhancedPurchaseByIdInteractorTest {

  @InjectMocks private FindEnhancedPurchaseByIdInteractor instance;

  private final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundary;
  private final AmericanTreasuryRateExchangeAPIClient americanTreasuryRateExchangeAPIClient;
  private final EnhancedPurchaseMapper enhancedPurchaseMapper;

  FindEnhancedPurchaseByIdInteractorTest(
      @Mock final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundaryParam,
      @Mock final AmericanTreasuryRateExchangeAPIClient americanTreasuryRateExchangeAPIClientParam,
      @Mock final EnhancedPurchaseMapper enhancedPurchaseMapperParam) {
    this.findPurchaseByIdInputBoundary = findPurchaseByIdInputBoundaryParam;
    this.americanTreasuryRateExchangeAPIClient = americanTreasuryRateExchangeAPIClientParam;
    this.enhancedPurchaseMapper = enhancedPurchaseMapperParam;
  }

  @Test
  void testShouldNotFindPurchaseBecauseOfInvalidID() {
    final var countries = Set.of(Country.BRAZIL);

    Mockito.when(this.findPurchaseByIdInputBoundary.apply(CoreTestConstants.LONG_MIN_VALUE))
        .thenThrow(EntityNotFoundException.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                EntityNotFoundException.class,
                () -> this.instance.apply(CoreTestConstants.LONG_MIN_VALUE, countries),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () ->
            Mockito.verify(this.findPurchaseByIdInputBoundary)
                .apply(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.enhancedPurchaseMapper));
  }

  @Test
  void testShouldNotFindPurchaseBecauseOfAmericanTreasuryAPIReturnedNothing() {
    final var countries = Set.of(Country.BRAZIL);
    Mockito.when(this.findPurchaseByIdInputBoundary.apply(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);
    Mockito.when(
            this.americanTreasuryRateExchangeAPIClient.apply(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries))
        .thenThrow(NotFound.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                NotFound.class,
                () -> this.instance.apply(CoreTestConstants.LONG_MIN_VALUE, countries),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () ->
            Mockito.verify(this.findPurchaseByIdInputBoundary)
                .apply(CoreTestConstants.LONG_MIN_VALUE),
        () ->
            Mockito.verify(this.americanTreasuryRateExchangeAPIClient)
                .apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries),
        () -> Mockito.verifyNoInteractions(this.enhancedPurchaseMapper));
  }

  @Test
  void testShouldFindEnhancedPurchase() {
    final var countries = Set.of(Country.BRAZIL);

    Mockito.when(this.findPurchaseByIdInputBoundary.apply(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);
    Mockito.when(
            this.americanTreasuryRateExchangeAPIClient.apply(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries))
        .thenReturn(ExchangeRateDTOFixture.EXCHANGE_RATE_DTO_FIXTURE);
    Mockito.when(
            this.enhancedPurchaseMapper.apply(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE)))
        .thenReturn(EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE,
                this.instance.apply(CoreTestConstants.LONG_MIN_VALUE, countries),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () ->
            Mockito.verify(this.findPurchaseByIdInputBoundary)
                .apply(CoreTestConstants.LONG_MIN_VALUE),
        () ->
            Mockito.verify(this.americanTreasuryRateExchangeAPIClient)
                .apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries),
        () ->
            Mockito.verify(this.enhancedPurchaseMapper)
                .apply(
                    PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                    List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE)));
  }

  @Test
  void testShouldFindEnhancedPurchaseWithMultipleItems() {
    final var countries = Set.of(Country.BRAZIL, Country.ITALY);
    final var data =
        List.of(
            new ExchangeRateDataDTO(
                Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE),
            new ExchangeRateDataDTO(
                Country.BRAZIL.name(),
                BigDecimal.ZERO,
                CoreTestConstants.FIXED_LOCAL_DATE.minusMonths(3)),
            new ExchangeRateDataDTO(
                Country.ITALY.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE));
    final var exchangeRateData =
        List.of(
            new ExchangeRateDataDTO(
                Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE),
            new ExchangeRateDataDTO(
                Country.ITALY.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE));
    final var expected =
        new EnhancedPurchaseDTO(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.id(),
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.description(),
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.transactionDate(),
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.amount(),
            exchangeRateData);

    Mockito.when(this.findPurchaseByIdInputBoundary.apply(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);
    Mockito.when(
            this.americanTreasuryRateExchangeAPIClient.apply(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries))
        .thenReturn(new ExchangeRateDTO(data));
    Mockito.when(
            this.enhancedPurchaseMapper.apply(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, exchangeRateData))
        .thenReturn(expected);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                expected,
                this.instance.apply(CoreTestConstants.LONG_MIN_VALUE, countries),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () ->
            Mockito.verify(this.findPurchaseByIdInputBoundary)
                .apply(CoreTestConstants.LONG_MIN_VALUE),
        () ->
            Mockito.verify(this.americanTreasuryRateExchangeAPIClient)
                .apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, countries),
        () ->
            Mockito.verify(this.enhancedPurchaseMapper)
                .apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, exchangeRateData));
  }
}
