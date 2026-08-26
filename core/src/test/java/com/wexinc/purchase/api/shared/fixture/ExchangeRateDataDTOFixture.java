package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;

public final class ExchangeRateDataDTOFixture {

  public static final ExchangeRateDataDTO EXCHANGE_RATE_DATA_DTO_FIXTURE =
      new ExchangeRateDataDTO(
          Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE);

  private ExchangeRateDataDTOFixture() {
    throw new UnsupportedOperationException(
        CoreTestConstants.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
  }
}
