package com.wexinc.purchase.api.shared.util;

import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

public final class PurchaseFixture {

  public static final Purchase ACTUAL_PURCHASE =
      new Purchase(
          CoreTestConstants.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          CoreTestConstants.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO);

  private PurchaseFixture() {
    throw new UnsupportedOperationException(
        CoreTestConstants.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
  }
}
