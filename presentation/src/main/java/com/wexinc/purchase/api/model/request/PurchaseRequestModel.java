package com.wexinc.purchase.api.model.request;

import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class responsible for holding request model objects of a purchase that will serve as the input
 * payload of the purchase API endpoints.
 *
 * @param description Mandatory field responsible for holding the purchase description containing at
 *     most fifty characters.
 * @param transactionDate Mandatory field responsible for holding the purchase transaction date.
 *     Must be in the past.
 * @param amount Mandatory field responsible for holding the purchase transaction amount. Must be a
 *     positive decimal number.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see NotBlank
 * @see NotNull
 * @see Past
 * @see Positive
 * @see Size
 * @see LocalDateTime
 * @see BigDecimal
 */
public record PurchaseRequestModel(
    @Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_DESCRIPTION)
        @NotBlank
        @Size(max = 50)
        String description,
    @Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_TRANSACTION_DATE)
        @NotNull
        @Past
        LocalDateTime transactionDate,
    @Schema(description = ConstantsPresentation.OPENAPI_DOCUMENTATION_PURCHASE_ID)
        @NotNull
        @Positive
        BigDecimal amount) {}
