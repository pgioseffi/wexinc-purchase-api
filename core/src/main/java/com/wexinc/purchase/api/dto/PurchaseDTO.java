package com.wexinc.purchase.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Object responsible for transporting complete information about a purchase through the different
 * layers of the application.
 *
 * @param id Mandatory field responsible for holding the positive numeric identification of a
 *     purchase.
 * @param description Mandatory field responsible for holding the purchase description containing at
 *     most fifty characters.
 * @param transactionDate Mandatory field responsible for holding the purchase transaction date.
 *     Must be in the past.
 * @param amount Mandatory field responsible for holding the purchase transaction amount. Must be a
 *     positive decimal number.
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public record PurchaseDTO(
    Long id, String description, LocalDateTime transactionDate, BigDecimal amount) {}
