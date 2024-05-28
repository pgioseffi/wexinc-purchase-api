package com.wexinc.purchase.api.client;

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
import org.springframework.web.client.RestTemplate;

import com.wexinc.purchase.api.component.ConsulPropertiesComponent;
import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.mapper.ExchangeRateInfrastructureMapper;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;

@ExtendWith(MockitoExtension.class)
class AmericanTreasuryRateExchangeAPIWebClientTest {

	@InjectMocks
	private AmericanTreasuryRateExchangeAPIWebClient instance;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ConsulPropertiesComponent consulProperties;

	@Mock
	private ExchangeRateInfrastructureMapper exchangeRateInfrastructureMapper;

	@Test
	void shouldThrowExceptionBecauseOfNullReturnFromAPI() {
		final var countries = Set.of(Country.BRAZIL);
		final var currencyConversionURL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:2022-12-02,effective_date:lte:2023-12-02,country:in:(Brazil,Argentina,Mexico,Uruguay)&sort=country,-effective_date&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
		Mockito.when(this.consulProperties.getCurrencyConversionURL()).thenReturn(currencyConversionURL);
		final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
		Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
				.thenReturn(currencyConversionLeniencyInMonths);
		Mockito.when(this.restTemplate.getForObject(currencyConversionURL.formatted(countries,
				Constantes.FIXED_LOCAL_DATE.minusMonths(currencyConversionLeniencyInMonths.longValue()),
				Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class)).thenReturn(null);

		Assertions.assertThrows(NotFound.class,
				() -> this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionURL();
		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionLeniencyInMonths();
		Mockito.verify(this.restTemplate, Mockito.times(1))
				.getForObject(this.consulProperties.getCurrencyConversionURL().formatted(countries,
						Constantes.FIXED_LOCAL_DATE
								.minusMonths(this.consulProperties.getCurrencyConversionLeniencyInMonths()),
						Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class);
	}

	@Test
	void shouldThrowExceptionBecauseOfEmptyReturnFromAPI() {
		final var countries = Set.of(Country.BRAZIL);
		final var currencyConversionURL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:2022-12-02,effective_date:lte:2023-12-02,country:in:(Brazil,Argentina,Mexico,Uruguay)&sort=country,-effective_date&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
		Mockito.when(this.consulProperties.getCurrencyConversionURL()).thenReturn(currencyConversionURL);
		final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
		Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
				.thenReturn(currencyConversionLeniencyInMonths);
		Mockito.when(this.restTemplate.getForObject(currencyConversionURL.formatted(countries,
				Constantes.FIXED_LOCAL_DATE.minusMonths(currencyConversionLeniencyInMonths.longValue()),
				Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class))
				.thenReturn(new ExchangeRateResource(List.of()));

		Assertions.assertThrows(NotFound.class,
				() -> this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionURL();
		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionLeniencyInMonths();
		Mockito.verify(this.restTemplate, Mockito.times(1))
				.getForObject(this.consulProperties.getCurrencyConversionURL().formatted(countries,
						Constantes.FIXED_LOCAL_DATE
								.minusMonths(this.consulProperties.getCurrencyConversionLeniencyInMonths()),
						Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class);
	}

	@Test
	void shouldReturn() {
		final var countries = Set.of(Country.BRAZIL);
		final var currencyConversionURL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:2022-12-02,effective_date:lte:2023-12-02,country:in:(Brazil,Argentina,Mexico,Uruguay)&sort=country,-effective_date&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
		final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
		final var resourceResult = new ExchangeRateResource(List
				.of(new ExchangeRateDataResource(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)));
		final var methodResult = new ExchangeRateDTO(
				List.of(new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)));

		Mockito.when(this.consulProperties.getCurrencyConversionURL()).thenReturn(currencyConversionURL);
		Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
				.thenReturn(currencyConversionLeniencyInMonths);
		Mockito.when(this.restTemplate.getForObject(currencyConversionURL.formatted(countries,
				Constantes.FIXED_LOCAL_DATE.minusMonths(currencyConversionLeniencyInMonths.longValue()),
				Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class)).thenReturn(resourceResult);
		Mockito.when(this.exchangeRateInfrastructureMapper.fromResourceToDTO(resourceResult)).thenReturn(methodResult);

		Assertions.assertEquals(methodResult, this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
				Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionURL();
		Mockito.verify(this.consulProperties, Mockito.times(1)).getCurrencyConversionLeniencyInMonths();
		Mockito.verify(this.restTemplate, Mockito.times(1))
				.getForObject(this.consulProperties.getCurrencyConversionURL().formatted(countries,
						Constantes.FIXED_LOCAL_DATE
								.minusMonths(this.consulProperties.getCurrencyConversionLeniencyInMonths()),
						Constantes.FIXED_LOCAL_DATE), ExchangeRateResource.class);
		Mockito.verify(this.exchangeRateInfrastructureMapper, Mockito.times(1)).fromResourceToDTO(resourceResult);
	}
}
