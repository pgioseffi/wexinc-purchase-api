package com.wexinc.purchase.api.model.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Object responsible for returning complete information about a purchase.
 *
 * @param id              Mandatory field responsible for holding the purchase positive numeric identification.
 * @param description     Mandatory field responsible for holding the purchase description containing at most fifty
 *                        characters.
 * @param transactionDate Mandatory field responsible for holding the purchase transaction date. Must be in the past.
 * @param amount          Mandatory field responsible for holding the purchase transaction amount. Must be a positive
 *                        decimal number.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record PurchaseResponseModel(
		@Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_ID) Long id,
		@Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_DESCRIPTION) String description,
		@Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_TRANSACTION_DATE) LocalDateTime transactionDate,
		@Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_AMOUNT) BigDecimal amount) {

	/**
	 * Return the purchase amount rounded with two decimal digits.
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
	 * Return the transaction date discarding milisseconds information.
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
