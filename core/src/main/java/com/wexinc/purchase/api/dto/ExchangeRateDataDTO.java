package com.wexinc.purchase.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class responsible for transporting information retrieved by the american treasury exchange rate microservice through
 * the different layers of the application.
 *
 * @param country       The country that is associated with a given exchange rate.
 * @param exchangeRate  Exchange rate at which any foreign currency unit will be valued, and reported at, against the
 *                      U.S. Dollar.
 * @param effectiveDate The date that a given exchange rate took effect.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record ExchangeRateDataDTO(String country, BigDecimal exchangeRate, LocalDate effectiveDate) {
}
