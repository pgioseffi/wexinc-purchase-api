package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import java.util.Collection;
import java.util.function.BiFunction;
import org.mapstruct.Javadoc;
import org.mapstruct.Mapper;

/**
 * Interface responsible for mapping a {@link EnhancedPurchaseDTO} from a {@link PurchaseDTO} and a
 * {@link Collection collection} of {@link ExchangeRateDataDTO}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see Collection
 * @see EnhancedPurchaseDTO
 * @see PurchaseDTO
 */
@Mapper
@Javadoc(
"""
Concrete implementation type responsible for mapping a {@link EnhancedPurchaseDTO} from a {@link PurchaseDTO} and a {@link Collection collection} of {@link ExchangeRateDataDTO}.

@author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
@since 1.0.0
@version 1.0.0
@see Collection
@see EnhancedPurchaseDTO
@see PurchaseDTO""")
@FunctionalInterface
public interface EnhancedPurchaseMapper
    extends BiFunction<PurchaseDTO, Collection<ExchangeRateDataDTO>, EnhancedPurchaseDTO> {

  /**
   * Method responsible for mapping a {@link EnhancedPurchaseDTO} from a {@link PurchaseDTO} and a
   * {@link Collection collection} of {@link ExchangeRateDataDTO}.
   *
   * @param exchangeRateData A {@link Collection collection} of {@link ExchangeRateDataDTO} to be
   *     copied.
   * @param purchaseDTO The {@link PurchaseDTO} object with all the information to be copied.
   * @return A new {@link EnhancedPurchaseDTO} object with all the information copied plus the
   *     {@link Collection collection} of {@link ExchangeRateDataDTO}.
   * @since 1.0.0
   * @see Collection
   * @see EnhancedPurchaseDTO
   * @see PurchaseDTO
   */
  @Override
  EnhancedPurchaseDTO apply(
      PurchaseDTO purchaseDTO, Collection<ExchangeRateDataDTO> exchangeRateData);
}
