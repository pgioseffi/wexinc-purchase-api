package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.Constantes;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public final class PurchaseDTOFixture {

  public static final PurchaseDTO ACTUAL_PURCHASE_DTO =
      new PurchaseDTO(
          Constantes.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          Constantes.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO);

  public static final EnhancedPurchaseDTO ACTUAL_ENHANCED_PURCHASE_DTO =
      new EnhancedPurchaseDTO(
          Constantes.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          Constantes.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO,
          List.of());

  private PurchaseDTOFixture() {
    throw new UnsupportedOperationException(
        Constantes.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
  }
}
