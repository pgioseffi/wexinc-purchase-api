package com.wexinc.purchase.api.dto;

import java.util.Collection;

/**
 * Class responsible for transporting information retrieved by the American treasury exchange rate
 * microservice through the different layers of the application.
 *
 * @param data The {@link ExchangeRateDataDTO effetive data} that is associated with a given
 *     exchange rate.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record ExchangeRateDTO(Collection<ExchangeRateDataDTO> data) {}
