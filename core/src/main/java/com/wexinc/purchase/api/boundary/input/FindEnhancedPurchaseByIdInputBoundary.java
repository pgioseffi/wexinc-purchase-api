package com.wexinc.purchase.api.boundary.input;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.shared.constant.Country;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 * Interface responsible for holding the signature of the method that conains the business rules to
 * search for a purchase and enhance its information with its exchange rate according to the
 * countries given.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@FunctionalInterface
public interface FindEnhancedPurchaseByIdInputBoundary
    extends BiFunction<Long, Collection<Country>, EnhancedPurchaseDTO> {}
