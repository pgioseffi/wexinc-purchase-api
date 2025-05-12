package com.wexinc.purchase.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wexinc.purchase.api.shared.constant.Country;
import java.util.Collection;

/**
 * Class responsible for holding information retrieved by the american treasury exchange rate
 * microservice.
 *
 * @param data The {@link Country} object that is associated with a given exchange rate.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see com.wexinc.purchase.api.persistence.repository.PurchaseRepository PurchaseRepository
 */
@JsonNaming(SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateResource(Collection<ExchangeRateDataResource> data) {}
