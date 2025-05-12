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
  void shouldNotMapFromDTOToResponseModel() {
    Assertions.assertNull(
        ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(null, null),
        Constantes.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void shouldMapJustAmount() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(null, null, null),
        ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(BigDecimal.TEN, null),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapFromDTOToResponseModel() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(
            Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, BigDecimal.ZERO),
        ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(
            BigDecimal.TEN,
            new ExchangeRateDataDTO(
                Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapFromDTOToResponseModels() {
    Assertions.assertEquals(
        new ExchangeRateDataResponseModel(
            Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, null),
        ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(
            null,
            new ExchangeRateDataDTO(
                Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
