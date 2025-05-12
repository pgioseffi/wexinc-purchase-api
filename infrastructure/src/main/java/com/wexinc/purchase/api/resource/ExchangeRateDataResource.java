package com.wexinc.purchase.api.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class responsible for holding information retrieved by the american treasury exchange rate
 * microservice.
 *
 * @param country The country that is associated with a given exchange rate.
 * @param exchangeRate Exchange rate at which any foreign currency unit will be valued, and reported
 *     at, against the U.S. Dollar.
 * @param effectiveDate The date that a given exchange rate took effect.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see com.wexinc.purchase.api.persistence.repository.PurchaseRepository PurchaseRepository
 */
@JsonNaming(SnakeCaseStrategy.class)
public record ExchangeRateDataResource(
    String country, BigDecimal exchangeRate, LocalDate effectiveDate) {}
