package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import com.wexinc.purchase.api.shared.fixture.PurchaseFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

class PurchaseInfrastructureMapperImplTest {

  private static final PurchaseInfrastructureMapper MAPPER =
      Mappers.getMapper(PurchaseInfrastructureMapper.class);

  @ParameterizedTest
  @MethodSource("testFromEntityToDTOArgumentsProvider")
  void testFromEntityToDTO(final PurchaseDTO expected, final Purchase purchase) {
    Assertions.assertEquals(
        expected,
        PurchaseInfrastructureMapperImplTest.MAPPER.fromEntityToDTO(purchase),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @ParameterizedTest
  @MethodSource("testFromDTOToEntityArgumentsProvider")
  void testFromDTOToEntity(final Purchase expected, final PurchaseDTO purchaseDTO) {
    Assertions.assertEquals(
        expected,
        PurchaseInfrastructureMapperImplTest.MAPPER.fromDTOToEntity(purchaseDTO),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  private static Stream<Arguments> testFromEntityToDTOArgumentsProvider() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE, PurchaseFixture.ACTUAL_PURCHASE));
  }

  private static Stream<Arguments> testFromDTOToEntityArgumentsProvider() {
    return Stream.of(
        Arguments.of(null, null),
        Arguments.of(
            PurchaseFixture.ACTUAL_PURCHASE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE));
  }
}
