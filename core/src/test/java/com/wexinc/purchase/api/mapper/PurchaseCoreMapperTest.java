package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
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
  @MethodSource("generateArgumentsForTestShouldMapApply")
  void testShouldMapApply(
      final PurchaseDTO expected, final Long newId, final PurchaseDTO purchaseDTO) {
    Assertions.assertEquals(
        expected,
        PurchaseCoreMapperTest.MAPPER.apply(newId, purchaseDTO),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> generateArgumentsForTestShouldMapApply() {
    return Stream.of(
        Arguments.of(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
            CoreTestConstants.LONG_MIN_VALUE,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
        Arguments.of(
            new PurchaseDTO(CoreTestConstants.LONG_MIN_VALUE, null, null, null),
            CoreTestConstants.LONG_MIN_VALUE,
            null),
        Arguments.of(
            new PurchaseDTO(
                null, StringUtils.EMPTY, CoreTestConstants.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
            null,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
        Arguments.of(null, null, null));
  }
}
