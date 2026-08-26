package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import com.wexinc.purchase.api.shared.util.PurchaseRequestModelFixture;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class AbstractPurchasePresentationMapperImplTest {

  private static final AbstractPurchasePresentationMapper MAPPER =
      Mappers.getMapper(AbstractPurchasePresentationMapper.class);

  @Test
  void shouldMapDataTransferObjectToResponseModel() {
    Assertions.assertEquals(
        new PurchaseResponseModel(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO),
        AbstractPurchasePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapDataTransferObjectToToNull() {
    Assertions.assertNull(
        AbstractPurchasePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(null),
        CoreTestConstants.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void shouldMapResponseModelToDataTransferObject() {
    Assertions.assertEquals(
        new PurchaseDTO(
            null, StringUtils.EMPTY, CoreTestConstants.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
        AbstractPurchasePresentationMapperImplTest.MAPPER.fromRequestModelToDTO(
            PurchaseRequestModelFixture.ACTUAL_PURCHASE_REQUEST_MODEL),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapResponseModelToNull() {
    Assertions.assertNull(
        AbstractPurchasePresentationMapperImplTest.MAPPER.fromRequestModelToDTO(null),
        CoreTestConstants.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void shouldNotMapToEnhancedPurchase() {
    Assertions.assertNull(
        AbstractPurchasePresentationMapper.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(
            null),
        CoreTestConstants.SHOULD_HAVE_RETURNED_NULL);
  }

  @Test
  void shouldNotMapToEnhancedItem() {
    Assertions.assertEquals(
        new EnhancedPurchaseResponseModel(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO,
            null),
        AbstractPurchasePresentationMapper.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(
            new EnhancedPurchaseDTO(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO,
                null)),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void shouldMapToEnhancedPurchase() {
    Assertions.assertEquals(
        new EnhancedPurchaseResponseModel(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO,
            List.of(
                new ExchangeRateDataResponseModel(
                    Country.BRAZIL.name(), BigDecimal.ZERO, BigDecimal.ZERO))),
        AbstractPurchasePresentationMapper.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(
            new EnhancedPurchaseDTO(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO,
                List.of(
                    new ExchangeRateDataDTO(
                        Country.BRAZIL.name(),
                        BigDecimal.ZERO,
                        CoreTestConstants.FIXED_LOCAL_DATE)))),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
