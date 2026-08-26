package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataResourceFixture;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

class ExchangeRateDataInfrastructureMapperImplTest {

  private static final ExchangeRateDataInfrastructureMapper MAPPER =
      Mappers.getMapper(ExchangeRateDataInfrastructureMapper.class);

  @ParameterizedTest
  @MethodSource("testFromResourceToDTOArgumentsProvider")
  void testFromResourceToDTO(
      final ExchangeRateDataDTO expected, final ExchangeRateDataResource resource) {
    Assertions.assertEquals(
        expected,
        ExchangeRateDataInfrastructureMapperImplTest.MAPPER.fromResourceToDTO(resource),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @ParameterizedTest
  @MethodSource("testFromCollectionResourceToCollectionDTOArgumentsProvider")
  void testFromCollectionExchangeRateDataResourceToCollectionExchangeRateDataDTO(
      final Collection<ExchangeRateDataDTO> expected,
      final Collection<ExchangeRateDataResource> resources) {
    Assertions.assertIterableEquals(
        expected,
        ExchangeRateDataInfrastructureMapperImplTest.MAPPER.fromCollectionResourceToCollectionDTO(
            resources),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> testFromResourceToDTOArgumentsProvider() {
    return Stream.of(
        Arguments.of(
            ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE,
            ExchangeRateDataResourceFixture.EXCHANGE_RATE_DATA_RESOURCE_FIXTURE),
        Arguments.of(null, null));
  }

  private static Stream<Arguments> testFromCollectionResourceToCollectionDTOArgumentsProvider() {
    return Stream.of(
        Arguments.of(
            List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE),
            List.of(ExchangeRateDataResourceFixture.EXCHANGE_RATE_DATA_RESOURCE_FIXTURE)),
        Arguments.of(null, null));
  }
}
