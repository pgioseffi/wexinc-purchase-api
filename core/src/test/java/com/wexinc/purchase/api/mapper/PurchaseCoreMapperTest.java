package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class PurchaseCoreMapperTest {

  private static final PurchaseCoreMapper MAPPER = Mappers.getMapper(PurchaseCoreMapper.class);

  @Test
  void testShouldMap() {
    Assertions.assertEquals(
        PurchaseCoreMapperTest.MAPPER.fromIdAndDTOToDTO(
            Constantes.LONG_MIN_VALUE, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldMapOnlyId() {
    Assertions.assertEquals(
        PurchaseCoreMapperTest.MAPPER.fromIdAndDTOToDTO(Constantes.LONG_MIN_VALUE, null),
        new PurchaseDTO(Constantes.LONG_MIN_VALUE, null, null, null),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldNotMapId() {
    Assertions.assertEquals(
        PurchaseCoreMapperTest.MAPPER.fromIdAndDTOToDTO(
            null, PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        new PurchaseDTO(null, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldMapToNull() {
    Assertions.assertNull(
        PurchaseCoreMapperTest.MAPPER.fromIdAndDTOToDTO(null, null),
        Constantes.SHOULD_HAVE_RETURNED_NULL);
  }
}
