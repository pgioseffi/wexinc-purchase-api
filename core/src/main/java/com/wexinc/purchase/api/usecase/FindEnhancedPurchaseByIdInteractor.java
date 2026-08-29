package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.FindEnhancedPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.input.FindPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.output.AmericanTreasuryRateExchangeAPIClient;
import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.mapper.EnhancedPurchaseMapper;
import com.wexinc.purchase.api.shared.constant.Country;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Concrete class containing the business rules to search for a purchase and enhance its information
 * with its exchange rate according to the countries given.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class FindEnhancedPurchaseByIdInteractor implements FindEnhancedPurchaseByIdInputBoundary {

  /**
   * Field responsible to hold the implementation of the interactor that finds a purchase by its
   * numeric positive identifier.
   *
   * @since 1.0.0
   */
  private final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundary;

  /**
   * Field responsible to hold the implementation of the interactor that finds a purchase by its
   * numeric positive identifier.
   *
   * @since 1.0.0
   */
  private final AmericanTreasuryRateExchangeAPIClient americanTreasuryRateExchangeAPIClient;

  /**
   * Field that will perform a mapping action from a {@link PurchaseDTO}, plus a {@link Collection
   * collection} of {@link ExchangeRateDataDTO} into a {@link EnhancedPurchaseDTO}.
   *
   * @since 1.0.0
   * @see EnhancedPurchaseMapper
   * @see EnhancedPurchaseMapper#apply(PurchaseDTO, Collection)
   * @see Collection
   * @see PurchaseDTO
   * @see EnhancedPurchaseDTO
   */
  private final EnhancedPurchaseMapper enhancedPurchaseMapper;

  /**
   * Class complete constructor.
   *
   * @param findPurchaseByIdInputBoundaryParam Parameter responsible for holding the gateway that
   *     will initialize the {@link #findPurchaseByIdInputBoundary} field of this class.
   * @param americanTreasuryRateExchangeAPIClientParam Parameter responsible for holding the gateway
   *     that will initialize the {@link #americanTreasuryRateExchangeAPIClient} field of this
   *     class.
   * @param enhancedPurchaseMapperParam Parameter responsible for holding the mapper that will
   *     initialize the {@link #americanTreasuryRateExchangeAPIClient} field of this class.
   * @since 1.0.0
   */
  public FindEnhancedPurchaseByIdInteractor(
      final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundaryParam,
      final AmericanTreasuryRateExchangeAPIClient americanTreasuryRateExchangeAPIClientParam,
      final EnhancedPurchaseMapper enhancedPurchaseMapperParam) {
    super();
    this.findPurchaseByIdInputBoundary = findPurchaseByIdInputBoundaryParam;
    this.americanTreasuryRateExchangeAPIClient = americanTreasuryRateExchangeAPIClientParam;
    this.enhancedPurchaseMapper = enhancedPurchaseMapperParam;
  }

  /**
   * {@inheritDoc}
   *
   * @throws com.wexinc.purchase.api.shared.exception.EntityNotFoundException If this method does
   *     not find the purchase.
   * @since 1.0.0
   */
  @Override
  public EnhancedPurchaseDTO apply(final Long id, final Collection<Country> countries) {
    final var purchaseDTO = this.findPurchaseByIdInputBoundary.apply(id);
    final var modifiableAndOrderedList =
        this.americanTreasuryRateExchangeAPIClient.apply(purchaseDTO, countries).data().stream()
            .sorted(
                Comparator.comparing(ExchangeRateDataDTO::country)
                    .thenComparing(ExchangeRateDataDTO::effectiveDate, Comparator.reverseOrder()))
            .collect(Collectors.toList());

    String country = null;
    for (final var iterator = modifiableAndOrderedList.iterator(); iterator.hasNext(); ) {
      final var item = iterator.next();
      if (item.country().equals(country)) {
        iterator.remove();
      } else {
        country = item.country();
      }
    }

    return this.enhancedPurchaseMapper.apply(purchaseDTO, modifiableAndOrderedList);
  }
}
