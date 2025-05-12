package com.wexinc.purchase.api.shared.util;

import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.shared.constant.Constantes;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

public class PurchaseRequestModelFixture {

  public static final PurchaseRequestModel ACTUAL_PURCHASE_REQUEST_MODEL =
      new PurchaseRequestModel(
          StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO);

  public static final PurchaseRequestModel ACTUAL_VALID_PURCHASE_REQUEST_MODEL =
      new PurchaseRequestModel(
          Constantes.VALID_PURCHASE_DESCRIPTION, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.TEN);

  public PurchaseRequestModelFixture() {
    throw new UnsupportedOperationException(
        Constantes.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
  }
}
