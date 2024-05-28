package com.wexinc.purchase.api.usecase;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.wexinc.purchase.api.boundary.input.FindPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.output.AmericanTreasuryRateExchangeAPIClient;
import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;

@ExtendWith(MockitoExtension.class)
class FindEnchancedPurchaseByIdInteractorTest {

	@InjectMocks
	private FindEnchancedPurchaseByIdInteractor instance;

	@Mock
	private FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundary;

	@Mock
	private AmericanTreasuryRateExchangeAPIClient americanTreasuryRateExchangeAPIClient;

	@Test
	void shouldNotFindPurchaseBecauseOfInvalidID() {
		Mockito.when(this.findPurchaseByIdInputBoundary.apply(Constantes.LONG_MIN_VALUE))
				.thenThrow(EntityNotFoundException.class);

		Assertions.assertThrows(EntityNotFoundException.class,
				() -> this.instance.apply(Constantes.LONG_MIN_VALUE, Set.of(Country.BRAZIL)),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.findPurchaseByIdInputBoundary, Mockito.times(1)).apply(Constantes.LONG_MIN_VALUE);
	}

	@Test
	void shouldNotFindPurchaseBecauseOfAmericanTreasuryAPIReturnedNothing() {
		final var countries = Set.of(Country.BRAZIL);
		Mockito.when(this.findPurchaseByIdInputBoundary.apply(Constantes.LONG_MIN_VALUE))
				.thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
		Mockito.when(
				this.americanTreasuryRateExchangeAPIClient.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries))
				.thenThrow(NotFound.class);

		Assertions.assertThrows(NotFound.class, () -> this.instance.apply(Constantes.LONG_MIN_VALUE, countries),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.findPurchaseByIdInputBoundary, Mockito.times(1)).apply(Constantes.LONG_MIN_VALUE);
		Mockito.verify(this.americanTreasuryRateExchangeAPIClient, Mockito.times(1))
				.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries);
	}

	@Test
	void shouldFindEnhancedPurchase() {
		final var countries = Set.of(Country.BRAZIL);
		final var exchangeRateData = List
				.of(new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE));

		Mockito.when(this.findPurchaseByIdInputBoundary.apply(Constantes.LONG_MIN_VALUE))
				.thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
		Mockito.when(
				this.americanTreasuryRateExchangeAPIClient.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries))
				.thenReturn(new ExchangeRateDTO(exchangeRateData));

		Assertions.assertEquals(
				new EnhancedPurchaseDTO(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.id(),
						PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.description(),
						PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.transactionDate(),
						PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.amount(), exchangeRateData),
				this.instance.apply(Constantes.LONG_MIN_VALUE, countries), Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.findPurchaseByIdInputBoundary, Mockito.times(1)).apply(Constantes.LONG_MIN_VALUE);
		Mockito.verify(this.americanTreasuryRateExchangeAPIClient, Mockito.times(1))
				.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries);
	}

	@Test
	void shouldFindEnhancedPurchaseWithMultipleItems() {
		final var countries = Set.of(Country.BRAZIL, Country.ITALY);
		final var data = new ArrayList<ExchangeRateDataDTO>(3);
		data.add(new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE));
		data.add(new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO,
				Constantes.FIXED_LOCAL_DATE.minusMonths(3)));
		data.add(new ExchangeRateDataDTO(Country.ITALY.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE));

		Mockito.when(this.findPurchaseByIdInputBoundary.apply(Constantes.LONG_MIN_VALUE))
				.thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
		Mockito.when(
				this.americanTreasuryRateExchangeAPIClient.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries))
				.thenReturn(new ExchangeRateDTO(data));

		Assertions.assertEquals(new EnhancedPurchaseDTO(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.id(),
				PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.description(),
				PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.transactionDate(),
				PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.amount(),
				List.of(new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE),
						new ExchangeRateDataDTO(Country.ITALY.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE))),
				this.instance.apply(Constantes.LONG_MIN_VALUE, countries), Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.findPurchaseByIdInputBoundary, Mockito.times(1)).apply(Constantes.LONG_MIN_VALUE);
		Mockito.verify(this.americanTreasuryRateExchangeAPIClient, Mockito.times(1))
				.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries);
	}
}
