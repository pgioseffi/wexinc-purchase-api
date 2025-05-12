package com.wexinc.purchase.api.boundary.input;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import java.util.function.UnaryOperator;

/**
 * Interface responsible for holding the signature of the method that will go to the gateway layer
 * to save a purchase in the database.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@FunctionalInterface
public interface SavePurchaseInputBoundary extends UnaryOperator<PurchaseDTO> {}
