package com.wexinc.purchase.api.client;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.wexinc.purchase.api.boundary.output.AmericanTreasuryRateExchangeAPIClient;
import com.wexinc.purchase.api.component.ConsulProperties;
import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.mapper.ExchangeRateInfrastructureMapper;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Country;

/**
 * Class responsible for consume the treasury reporting rates exchange API in order to calculate exchange rate and
 * return a purchase amount already converted.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class AmericanTreasuryRateExchangeAPIWebClient implements AmericanTreasuryRateExchangeAPIClient {

	/**
	 * Field that will be used to consume the treasury reporting rates exchange API in order to calculate exchange rate
	 * and return a purchase amount already converted.
	 *
	 * @since 1.0.0
	 */
	private final RestTemplate restTemplate;

	/**
	 * Field that holds dynamic application properties such as the treasury reporting rates exchange API URL.
	 *
	 * @since 1.0.0
	 */
	private final ConsulProperties consulProperties;

	/**
	 * Field that holds the interface that maps purchases POJOs into one another.
	 *
	 * @since 1.0.0
	 */
	private final ExchangeRateInfrastructureMapper exchangeRateInfrastructureMapper;

	/**
	 * Class complete constructor.
	 *
	 * @param restTemplateParam                     Parameter responsible to initialize the {@link #restTemplate} field
	 *                                              of this class.
	 * @param consulPropertiesParam                 Parameter responsible to initialize the {@link #consulProperties}
	 *                                              field of this
	 * @param exchangeRateInfrastructureMapperParam Parameter responsible to initialize the
	 *                                              {@link #exchangeRateInfrastructureMapper} field of this class.
	 * @since 1.0.0
	 */
	public AmericanTreasuryRateExchangeAPIWebClient(final RestTemplate restTemplateParam,
			final ConsulProperties consulPropertiesParam,
			final ExchangeRateInfrastructureMapper exchangeRateInfrastructureMapperParam) {
		this.restTemplate = restTemplateParam;
		this.consulProperties = consulPropertiesParam;
		this.exchangeRateInfrastructureMapper = exchangeRateInfrastructureMapperParam;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.0.0
	 * @throws HttpClientErrorException.NotFound If does not find any exchange rate according to the given countries.
	 */
	@Override
	public ExchangeRateDTO apply(final PurchaseDTO purchaseDTO, final Collection<Country> countries) {
		final var date = purchaseDTO.transactionDate();
		final var exchangeRate = this.restTemplate
				.getForObject(
						this.consulProperties.getCurrencyConversionURL().formatted(
								date.minusMonths(this.consulProperties.getCurrencyConversionLeniencyInMonths())
										.toLocalDate(),
								date.toLocalDate(),
								countries.stream().map(Country::getCapitalizedName).collect(Collectors.joining(","))),
						ExchangeRateResource.class);

		if (exchangeRate == null || exchangeRate.data().isEmpty()) {
			throw HttpClientErrorException
					.create(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), null,
							"The purchase %d cannot be converted to any of the target currencies informed."
									.formatted(purchaseDTO.id()).getBytes(StandardCharsets.UTF_8),
							StandardCharsets.UTF_8);
		}

		return this.exchangeRateInfrastructureMapper.fromResourceToDTO(exchangeRate);
	}
}
