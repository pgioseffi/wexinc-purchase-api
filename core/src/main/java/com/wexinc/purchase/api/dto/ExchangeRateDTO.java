package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.Country;
import java.util.Collection;

/**
 * Class responsible for transporting information retrieved by the american treasury exchange rate
 * microservice through the different layers of the application.
 *
 * @param data The {@link Country} object that is associated with a given exchange rate.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record ExchangeRateDTO(Collection<ExchangeRateDataDTO> data) {}
