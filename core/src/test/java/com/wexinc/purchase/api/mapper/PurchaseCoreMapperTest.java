package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

class PurchaseCoreMapperTest {

  private static final PurchaseCoreMapper MAPPER = Mappers.getMapper(PurchaseCoreMapper.class);

  @ParameterizedTest
  @MethodSource("generateArgumentsForTestShouldMapFromIdAndDTOToDTO")
  void testShouldMapFromIdAndDTOToDTO(
      final PurchaseDTO expected, Long newId, PurchaseDTO purchaseDTO) {
    Assertions.assertEquals(
        expected,
        PurchaseCoreMapperTest.MAPPER.fromIdAndDTOToDTO(newId, purchaseDTO),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> generateArgumentsForTestShouldMapFromIdAndDTOToDTO() {
    return Stream.of(
        Arguments.of(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
            Constantes.LONG_MIN_VALUE,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Arguments.of(
            new PurchaseDTO(Constantes.LONG_MIN_VALUE, null, null, null),
            Constantes.LONG_MIN_VALUE,
            null),
        Arguments.of(
            new PurchaseDTO(
                null, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
            null,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Arguments.of(null, null, null));
  }
}
