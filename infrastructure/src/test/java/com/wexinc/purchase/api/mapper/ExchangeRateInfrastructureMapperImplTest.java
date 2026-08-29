package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateResourceFixture;
import java.util.Collections;
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
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> generateArgumentsForTestShouldApply() {
    return Stream.of(
        Arguments.of(new ExchangeRateDTO(null), new ExchangeRateResource(null)),
        Arguments.of(
            new ExchangeRateDTO(Collections.singletonList(null)),
            new ExchangeRateResource(Collections.singletonList(null))),
        Arguments.of(
            ExchangeRateDTOFixture.EXCHANGE_RATE_DTO_FIXTURE,
            ExchangeRateResourceFixture.EXCHANGE_RATE_RESOURCE_FIXTURE),
        Arguments.of(null, null));
  }
}
