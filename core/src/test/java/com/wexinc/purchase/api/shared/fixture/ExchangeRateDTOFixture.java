package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import java.util.List;

public final class ExchangeRateDTOFixture {

  public static final ExchangeRateDTO EXCHANGE_RATE_DTO_FIXTURE =
      new ExchangeRateDTO(List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE));

  private ExchangeRateDTOFixture() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
