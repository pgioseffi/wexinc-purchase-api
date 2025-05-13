package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import java.util.function.Function;
import org.mapstruct.Mapper;

/**
 * Interface responsible for mapping a {@link ExchangeRateResource} object into a {@link
 * ExchangeRateDTO}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see ExchangeRateDTO
 * @see ExchangeRateResource
 */
@Mapper
@FunctionalInterface
public interface ExchangeRateInfrastructureMapper
    extends Function<ExchangeRateResource, ExchangeRateDTO> {

  /**
   * Method responsible for mapping a {@link ExchangeRateResource} object into a {@link
   * ExchangeRateDTO}.
   *
   * @param resource The resource object {@link ExchangeRateResource} containing exchange rates
   *     returned by the american treasury exchange API.
   * @return The already mapped {@link ExchangeRateDTO} object containing exchange rates returned by
   *     the american treasury exchange API ready to transport its information through the different
   *     layers of the application.
   * @since 1.0.0
   */
  @Override
  ExchangeRateDTO apply(ExchangeRateResource resource);
}
