package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import java.util.List;

public class ExchangeRateResourceFixture {

  public static final ExchangeRateResource EXCHANGE_RATE_RESOURCE_FIXTURE =
      new ExchangeRateResource(
          List.of(ExchangeRateDataResourceFixture.EXCHANGE_RATE_DATA_RESOURCE_FIXTURE));

  private ExchangeRateResourceFixture() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
