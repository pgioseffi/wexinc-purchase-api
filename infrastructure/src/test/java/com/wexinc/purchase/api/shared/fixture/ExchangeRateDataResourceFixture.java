package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;

public class ExchangeRateDataResourceFixture {

  public static final ExchangeRateDataResource EXCHANGE_RATE_DATA_RESOURCE_FIXTURE =
      new ExchangeRateDataResource(
          Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE);

  private ExchangeRateDataResourceFixture() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
