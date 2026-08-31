package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import java.util.Collection;
import org.mapstruct.Javadoc;
import org.mapstruct.Mapper;

/**
 * Interface responsible for mapping a {@link ExchangeRateDataResource} object into a {@link
 * ExchangeRateDataDTO}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see Collection
 * @see ExchangeRateDataResource
 * @see ExchangeRateDataDTO
 */
@Mapper
@Javadoc(
"""
Concrete implementation type responsible for mapping a {@link ExchangeRateDataResource} object into a {@link ExchangeRateDataDTO}.

@author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
@since 1.0.0
@version 1.0.0
@see Collection
@see ExchangeRateDataResource
@see ExchangeRateDataDTO""")
public interface ExchangeRateDataInfrastructureMapper {

  /**
   * Method responsible for mapping a {@link ExchangeRateDataResource} object into a {@link
   * ExchangeRateDataDTO}.
   *
   * @param resource The resource object {@link ExchangeRateDataDTO} containing exchange rates
   *     returned by the American treasury exchange API.
   * @return The already mapped {@link ExchangeRateDataDTO} object containing exchange rates
   *     returned by the American treasury exchange API ready to transport its information through
   *     the different layers of the application.
   * @since 1.0.0
   */
  ExchangeRateDataDTO fromResourceToDTO(ExchangeRateDataResource resource);

  /**
   * Method responsible for mapping a {@link Collection collection} of {@link
   * ExchangeRateDataResource} object into a {@link ExchangeRateDataDTO}.
   *
   * @param resources The resource {@link Collection collection} of {@link ExchangeRateDataDTO}
   *     containing exchange rates returned by the American treasury exchange API.
   * @return The already mapped {@link Collection collection} of {@link ExchangeRateDataDTO}
   *     containing exchange rates returned by the American treasury exchange API ready to transport
   *     its information through the different layers of the application.
   * @since 1.0.0
   */
  Collection<ExchangeRateDataDTO> fromCollectionResourceToCollectionDTO(
      Collection<ExchangeRateDataResource> resources);
}
