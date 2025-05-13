package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ExchangeRatePresentationMapperImplTest {

  private static final ExchangeRatePresentationMapper MAPPER =
      Mappers.getMapper(ExchangeRatePresentationMapper.class);

  @Test
  void testShouldNotMapApply() {
    Assertions.assertNull(
        ExchangeRatePresentationMapperImplTest.MAPPER.apply(null, null),
        Constantes.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void testShouldMapJustAmount() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(null, null, null),
        ExchangeRatePresentationMapperImplTest.MAPPER.apply(BigDecimal.TEN, null),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldMapApply() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(
            Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, BigDecimal.ZERO),
        ExchangeRatePresentationMapperImplTest.MAPPER.apply(
            BigDecimal.TEN,
            new ExchangeRateDataDTO(
                Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldMapFromDTOToResponseModels() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(
            Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, null),
        ExchangeRatePresentationMapperImplTest.MAPPER.apply(
            null,
            new ExchangeRateDataDTO(
                Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
