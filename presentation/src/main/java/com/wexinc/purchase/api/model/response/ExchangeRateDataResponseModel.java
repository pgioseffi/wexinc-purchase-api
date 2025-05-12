package com.wexinc.purchase.api.model.response;

import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class responsible for transporting information retrieved by the american treasury exchange rate
 * microservice through the different layers of the application.
 *
 * @param country The {@link Country} object that is associated with a given exchange rate.
 * @param exchangeRate Exchange rate at which any foreign currency unit will be valued, and reported
 *     at, against the U.S. Dollar.
 * @param convertedAmount The converted amount multiplied by the {@link #exchangeRate}.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see com.wexinc.purchase.api.persistence.repository.PurchaseRepository PurchaseRepository
 */
public record ExchangeRateDataResponseModel(
    @Schema(description = "The country object that is associated with a given exchange rate.")
        String country,
    @Schema(
            description =
                "Exchange rate at which any foreign currency unit will be valued, and reported at, against the U.S. Dollar.")
        BigDecimal exchangeRate,
    @Schema(description = "The purchase amount multiplied by the exchange rate.")
        BigDecimal convertedAmount) {

  /**
   * Returns the purchase {@link #convertedAmount converted amount} rounded with two decimal digits.
   *
   * @return The {@link BigDecimal} object representing the purchase {@link #convertedAmount
   *     converted amount} rounded with two decimal digits.
   * @since 1.0.0
   * @see BigDecimal
   * @see BigDecimal#setScale(int, RoundingMode)
   * @see RoundingMode
   * @see RoundingMode#HALF_EVEN
   */
  public BigDecimal convertedAmount() {
    return this.convertedAmount.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN);
  }
}
