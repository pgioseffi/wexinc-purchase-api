package com.wexinc.purchase.api.model.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Object responsible for transporting complete information about a purchase, plus its exchange rate according to the
 * country through the different layers of the application.
 *
 * @param id               Mandatory field responsible for holding the positive numeric identification of a purchase.
 * @param description      Mandatory field responsible for holding the purchase description containing at most fifty
 *                         characters.
 * @param transactionDate  Mandatory field responsible for holding the purchase transaction date. Must be in the past.
 * @param amount           Mandatory field responsible for holding the purchase transaction amount. Must be a positive
 *                         decimal number.
 * @param exchangeRateData The exchange rate data such as {@link com.wexinc.purchase.api.shared.constant.Country
 *                         Country}, exchange rate value itself, and effective date that the exchange rate took effect.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record EnhancedPurchaseResponseModel(
		@Schema(description = "Mandatory field responsible for holding the positive numeric identification of a purchase.") Long id,
		@Schema(description = "Mandatory field responsible for holding the purchase description containing at most fifty characters.") String description,
		@Schema(description = "Mandatory field responsible for holding the purchase transaction date. Must be in the past.") LocalDateTime transactionDate,
		@Schema(description = "Mandatory field responsible for holding the purchase transaction amount. Must be a positive decimal number.") BigDecimal amount,
		@Schema(description = "The exchange rate data such as country, exchange rate value itself, and effective date that the exchange rate took effect.") Collection<ExchangeRateDataResponseModel> exchangeRateData) {

	/**
	 * Returns the purchase {@link #amount} rounded with two decimal digits.
	 *
	 * @return The {@link BigDecimal} object representing the purchase {@link #amount} rounded with two decimal digits.
	 * @since 1.0.0
	 * @see BigDecimal
	 * @see BigDecimal#setScale(int, RoundingMode)
	 * @see RoundingMode
	 * @see RoundingMode#HALF_EVEN
	 */
	public BigDecimal amount() {
		return this.amount.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN);
	}

	/**
	 * Returns the {@link #transactionDate transaction date} discarding milisseconds information.
	 *
	 * @return The {@link LocalDateTime} object representing the purchase {@link #transactionDate} discarding
	 *         milisseconds information.
	 * @since 1.0.0
	 * @see LocalDateTime
	 * @see LocalDateTime#truncatedTo(java.time.temporal.TemporalUnit) LocalDateTime.truncatedTo(TemporalUnit)
	 * @see ChronoUnit#SECONDS
	 */
	public LocalDateTime transactionDate() {
		return this.transactionDate.truncatedTo(ChronoUnit.SECONDS);
	}
}
