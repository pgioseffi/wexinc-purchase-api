package com.wexinc.purchase.api.client;

import com.wexinc.purchase.api.component.ConsulPropertiesComponent;
import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.mapper.ExchangeRateInfrastructureMapper;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestBodySpec;
import org.springframework.web.client.RestClient.RequestHeadersUriSpec;
import org.springframework.web.client.RestClient.ResponseSpec;

@ExtendWith(MockitoExtension.class)
class AmericanTreasuryRateExchangeAPIWebClientTest {

  @InjectMocks private AmericanTreasuryRateExchangeAPIWebClient instance;

  private final RestClient restClient;
  private final ConsulPropertiesComponent consulProperties;
  private final ExchangeRateInfrastructureMapper exchangeRateInfrastructureMapper;

  AmericanTreasuryRateExchangeAPIWebClientTest(
      @Mock final RestClient restClientParam,
      @Mock final ConsulPropertiesComponent consulPropertiesParam,
      @Mock final ExchangeRateInfrastructureMapper exchangeRateInfrastructureMapperParam) {
    this.restClient = restClientParam;
    this.consulProperties = consulPropertiesParam;
    this.exchangeRateInfrastructureMapper = exchangeRateInfrastructureMapperParam;
  }

  @SuppressWarnings(Constantes.UNCHECKED)
  @Test
  void testShouldThrowExceptionBecauseOfNullReturnFromAPI() {
    final var countries = Set.of(Country.BRAZIL);
    final var formattedCountries =
        countries.stream().map(Country::getCapitalizedName).collect(Collectors.joining(","));
    final var currencyConversionURL =
        "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:%s,effective_date:lte:%s,country:in:(%s)&sort=-effective_date,country&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
    final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
    final var mockRestClientGet = Mockito.mock(RequestHeadersUriSpec.class);
    final var requestBodySpec = Mockito.mock(RequestBodySpec.class);
    final var responseSpec = Mockito.mock(ResponseSpec.class);

    Mockito.when(this.consulProperties.getCurrencyConversionURL())
        .thenReturn(currencyConversionURL);
    Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
        .thenReturn(currencyConversionLeniencyInMonths);
    Mockito.when(this.restClient.get()).thenReturn(mockRestClientGet);
    Mockito.when(
            mockRestClientGet.uri(
                currencyConversionURL.formatted(
                    Constantes.FIXED_LOCAL_DATE.minusMonths(
                        currencyConversionLeniencyInMonths.longValue()),
                    Constantes.FIXED_LOCAL_DATE,
                    formattedCountries)))
        .thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.retrieve()).thenReturn(responseSpec);
    Mockito.when(responseSpec.body(ExchangeRateResource.class)).thenReturn(null);

    Assertions.assertThrows(
        NotFound.class,
        () -> this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.consulProperties).getCurrencyConversionURL();
    Mockito.verify(this.consulProperties).getCurrencyConversionLeniencyInMonths();
    Mockito.verify(this.restClient).get();
    Mockito.verify(mockRestClientGet)
        .uri(
            currencyConversionURL.formatted(
                Constantes.FIXED_LOCAL_DATE.minusMonths(
                    currencyConversionLeniencyInMonths.longValue()),
                Constantes.FIXED_LOCAL_DATE,
                formattedCountries));
    Mockito.verify(requestBodySpec).accept(MediaType.APPLICATION_JSON);
    Mockito.verify(requestBodySpec).retrieve();
    Mockito.verify(responseSpec).body(ExchangeRateResource.class);
  }

  @SuppressWarnings(Constantes.UNCHECKED)
  @Test
  void testShouldThrowExceptionBecauseOfEmptyReturnFromAPI() {
    final var countries = Set.of(Country.BRAZIL);
    final var formattedCountries =
        countries.stream().map(Country::getCapitalizedName).collect(Collectors.joining(","));
    final var currencyConversionURL =
        "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:%s,effective_date:lte:%s,country:in:(%s)&sort=-effective_date,country&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
    final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
    final var mockRestClientGet = Mockito.mock(RequestHeadersUriSpec.class);
    final var requestBodySpec = Mockito.mock(RequestBodySpec.class);
    final var responseSpec = Mockito.mock(ResponseSpec.class);

    Mockito.when(this.consulProperties.getCurrencyConversionURL())
        .thenReturn(currencyConversionURL);
    Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
        .thenReturn(currencyConversionLeniencyInMonths);
    Mockito.when(this.restClient.get()).thenReturn(mockRestClientGet);
    Mockito.when(
            mockRestClientGet.uri(
                currencyConversionURL.formatted(
                    Constantes.FIXED_LOCAL_DATE.minusMonths(
                        currencyConversionLeniencyInMonths.longValue()),
                    Constantes.FIXED_LOCAL_DATE,
                    formattedCountries)))
        .thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.retrieve()).thenReturn(responseSpec);
    Mockito.when(responseSpec.body(ExchangeRateResource.class))
        .thenReturn(new ExchangeRateResource(List.of()));

    Assertions.assertThrows(
        NotFound.class,
        () -> this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.consulProperties).getCurrencyConversionURL();
    Mockito.verify(this.consulProperties).getCurrencyConversionLeniencyInMonths();
    Mockito.verify(this.restClient).get();
    Mockito.verify(mockRestClientGet)
        .uri(
            currencyConversionURL.formatted(
                Constantes.FIXED_LOCAL_DATE.minusMonths(
                    currencyConversionLeniencyInMonths.longValue()),
                Constantes.FIXED_LOCAL_DATE,
                formattedCountries));
    Mockito.verify(requestBodySpec).accept(MediaType.APPLICATION_JSON);
    Mockito.verify(requestBodySpec).retrieve();
    Mockito.verify(responseSpec).body(ExchangeRateResource.class);
  }

  @SuppressWarnings(Constantes.UNCHECKED)
  @Test
  void testShouldReturn() {
    final var countries = Set.of(Country.BRAZIL);
    final var formattedCountries =
        countries.stream().map(Country::getCapitalizedName).collect(Collectors.joining(","));
    final var currencyConversionURL =
        "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange?filter=effective_date:gte:%s,effective_date:lte:%s,country:in:(%s)&sort=-effective_date,country&page[number]=1&page[size]=10000&fields=country,exchange_rate,effective_date";
    final var currencyConversionLeniencyInMonths = Long.valueOf(6L);
    final var resourceResult =
        new ExchangeRateResource(
            List.of(
                new ExchangeRateDataResource(
                    Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)));
    final var methodResult =
        new ExchangeRateDTO(
            List.of(
                new ExchangeRateDataDTO(
                    Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)));
    final var mockRestClientGet = Mockito.mock(RequestHeadersUriSpec.class);
    final var requestBodySpec = Mockito.mock(RequestBodySpec.class);
    final var responseSpec = Mockito.mock(ResponseSpec.class);

    Mockito.when(this.consulProperties.getCurrencyConversionURL())
        .thenReturn(currencyConversionURL);
    Mockito.when(Long.valueOf(this.consulProperties.getCurrencyConversionLeniencyInMonths()))
        .thenReturn(currencyConversionLeniencyInMonths);
    Mockito.when(this.restClient.get()).thenReturn(mockRestClientGet);
    Mockito.when(
            mockRestClientGet.uri(
                currencyConversionURL.formatted(
                    Constantes.FIXED_LOCAL_DATE.minusMonths(
                        currencyConversionLeniencyInMonths.longValue()),
                    Constantes.FIXED_LOCAL_DATE,
                    formattedCountries)))
        .thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestBodySpec);
    Mockito.when(requestBodySpec.retrieve()).thenReturn(responseSpec);
    Mockito.when(responseSpec.body(ExchangeRateResource.class)).thenReturn(resourceResult);
    Mockito.when(this.exchangeRateInfrastructureMapper.apply(resourceResult))
        .thenReturn(methodResult);

    Assertions.assertEquals(
        methodResult,
        this.instance.apply(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, countries),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.consulProperties).getCurrencyConversionURL();
    Mockito.verify(this.consulProperties).getCurrencyConversionLeniencyInMonths();
    Mockito.verify(this.restClient).get();
    Mockito.verify(mockRestClientGet)
        .uri(
            currencyConversionURL.formatted(
                Constantes.FIXED_LOCAL_DATE.minusMonths(
                    currencyConversionLeniencyInMonths.longValue()),
                Constantes.FIXED_LOCAL_DATE,
                formattedCountries));
    Mockito.verify(requestBodySpec).accept(MediaType.APPLICATION_JSON);
    Mockito.verify(requestBodySpec).retrieve();
    Mockito.verify(responseSpec).body(ExchangeRateResource.class);
    Mockito.verify(this.exchangeRateInfrastructureMapper).apply(resourceResult);
  }
}
