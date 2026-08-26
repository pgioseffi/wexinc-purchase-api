package com.wexinc.purchase.api.shared.fixture;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;

public final class ExchangeRateDataDTOFixture {

  public static final ExchangeRateDataDTO EXCHANGE_RATE_DATA_DTO_FIXTURE =
      new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE);

  private ExchangeRateDataDTOFixture() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
