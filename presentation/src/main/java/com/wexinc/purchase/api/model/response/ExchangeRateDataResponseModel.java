package com.wexinc.purchase.api.model.response;

import java.math.BigDecimal;

import com.wexinc.purchase.api.shared.constant.Country;

/**
 * Class responsible for transporting information retrieved by the american treasury exchange rate microservice through
 * the different layers of the application.
 *
 * @param country         The {@link Country} object that is associated with a given exchange rate.
 * @param exchangeRate    Exchange rate at which any foreign currency unit will be valued, and reported at, against the
 *                        U.S. Dollar.
 * @param convertedAmount The purchase amount multiplied by the {@link #exchangeRate}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see com.wexinc.purchase.api.persistence.repository.PurchaseRepository PurchaseRepository
 */
public record ExchangeRateDataResponseModel(String country, BigDecimal exchangeRate, BigDecimal convertedAmount) {
}
