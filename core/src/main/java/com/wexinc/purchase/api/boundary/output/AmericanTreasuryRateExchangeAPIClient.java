package com.wexinc.purchase.api.boundary.output;

import java.util.Collection;
import java.util.function.BiFunction;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.Country;

/**
 * Interface responsible for holding the signature of the method that will consume the treasury reporting rates exchange
 * API in order to calculate exchange rate and return a purchase amount already converted.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@FunctionalInterface
public interface AmericanTreasuryRateExchangeAPIClient
		extends BiFunction<PurchaseDTO, Collection<Country>, ExchangeRateDTO> {
}
