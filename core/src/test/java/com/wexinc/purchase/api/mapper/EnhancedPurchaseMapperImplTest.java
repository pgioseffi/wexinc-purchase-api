package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.EnhancedPurchaseDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataDTOFixture;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

class EnhancedPurchaseMapperImplTest {

  private static final EnhancedPurchaseMapper MAPPER =
      Mappers.getMapper(EnhancedPurchaseMapper.class);

  @ParameterizedTest
  @MethodSource("testToEnhancedPurchaseDTOArgumentsProvider")
  void testToEnhancedPurchaseDTO(
      final EnhancedPurchaseDTO expected,
      final PurchaseDTO purchaseDTO,
      final Collection<ExchangeRateDataDTO> exchangeRateData) {
    Assertions.assertEquals(
        expected,
        EnhancedPurchaseMapperImplTest.MAPPER.apply(purchaseDTO, exchangeRateData),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> testToEnhancedPurchaseDTOArgumentsProvider() {
    return Stream.of(
        Arguments.of(null, null, null),
        Arguments.of(
            EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
            List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE)),
        Arguments.of(
            new EnhancedPurchaseDTO(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO,
                null),
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
            null),
        Arguments.of(
            new EnhancedPurchaseDTO(
                null,
                null,
                null,
                null,
                List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE)),
            null,
            List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE)));
  }
}
