package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

class ExchangeRateInfrastructureMapperImplTest {

  private static final ExchangeRateInfrastructureMapper MAPPER =
      Mappers.getMapper(ExchangeRateInfrastructureMapper.class);

  @ParameterizedTest
  @MethodSource("generateArgumentsForTestShouldApply")
  void testShouldApply(final ExchangeRateDTO expected, final ExchangeRateResource resource) {
    Assertions.assertEquals(
        expected,
        ExchangeRateInfrastructureMapperImplTest.MAPPER.apply(resource),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> generateArgumentsForTestShouldApply() {
    return Stream.of(
        Arguments.of(new ExchangeRateDTO(null), new ExchangeRateResource(null)),
        Arguments.of(
            new ExchangeRateDTO(Collections.singletonList(null)),
            new ExchangeRateResource(Collections.singletonList(null))),
        Arguments.of(
            new ExchangeRateDTO(
                List.of(
                    new ExchangeRateDataDTO(
                        Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE))),
            new ExchangeRateResource(
                List.of(
                    new ExchangeRateDataResource(
                        Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)))),
        Arguments.of(null, null));
  }
}
