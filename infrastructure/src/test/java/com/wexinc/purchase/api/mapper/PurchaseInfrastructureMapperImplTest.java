package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PurchaseInfrastructureMapperImplTest {

  private static final PurchaseInfrastructureMapper MAPPER =
      Mappers.getMapper(PurchaseInfrastructureMapper.class);

  @Test
  void shouldMapEntityToDTO() {
    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        PurchaseInfrastructureMapperImplTest.MAPPER.fromEntityToDTO(
            new Purchase(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO)),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapEntityToNull() {
    Assertions.assertNull(
        PurchaseInfrastructureMapperImplTest.MAPPER.fromEntityToDTO(null),
        CoreTestConstants.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void shouldMapDTOToEntity() {
    Assertions.assertEquals(
        new Purchase(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO),
        PurchaseInfrastructureMapperImplTest.MAPPER.fromDTOToEntity(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapDTOToNull() {
    Assertions.assertNull(
        PurchaseInfrastructureMapperImplTest.MAPPER.fromDTOToEntity(null),
        CoreTestConstants.SHOULD_HAVE_RETURNED_NULL);
  }
}
