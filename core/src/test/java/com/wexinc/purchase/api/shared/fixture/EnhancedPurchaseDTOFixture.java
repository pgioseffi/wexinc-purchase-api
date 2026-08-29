package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public final class EnhancedPurchaseDTOFixture {

  public static final EnhancedPurchaseDTO ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE =
      new EnhancedPurchaseDTO(
          CoreTestConstants.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          CoreTestConstants.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO,
          List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE));

  private EnhancedPurchaseDTOFixture() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
