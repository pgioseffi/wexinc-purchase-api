package com.wexinc.purchase.api.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;
import com.wexinc.purchase.api.shared.util.PurchaseRequestModelFixture;

class PurchasePresentationMapperImplTest {

	private static final PurchasePresentationMapper MAPPER = Mappers.getMapper(PurchasePresentationMapper.class);

	@Test
	void shouldMapDataTransferObjectToResponseModel() {
		Assertions.assertEquals(
				new PurchaseResponseModel(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY,
						Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
				PurchasePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(
						PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapDataTransferObjectToToNull() {
		Assertions.assertNull(PurchasePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldMapResponseModelToDataTransferObject() {
		Assertions.assertEquals(
				new PurchaseDTO(null, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO),
				PurchasePresentationMapperImplTest.MAPPER
						.fromRequestModelToDTO(PurchaseRequestModelFixture.ACTUAL_PURCHASE_REQUEST_MODEL),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapResponseModelToNull() {
		Assertions.assertNull(PurchasePresentationMapperImplTest.MAPPER.fromRequestModelToDTO(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldNotMapToEnhancedPurchase() {
		Assertions.assertNull(PurchasePresentationMapper.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldNotMapToEnhancedItem() {
		Assertions.assertEquals(
				new EnhancedPurchaseResponseModel(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY,
						Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO, null),
				PurchasePresentationMapper.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(
						new EnhancedPurchaseDTO(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY,
								Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO, null)),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapToEnhancedPurchase() {
		Assertions
				.assertEquals(
						new EnhancedPurchaseResponseModel(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY,
								Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO,
								List.of(new ExchangeRateDataResponseModel(Country.BRAZIL.name(), BigDecimal.ZERO,
										BigDecimal.ZERO.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)))),
						PurchasePresentationMapper
								.fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(new EnhancedPurchaseDTO(
										Constantes.LONG_MIN_VALUE, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME,
										BigDecimal.ZERO, List.of(new ExchangeRateDataDTO(Country.BRAZIL.name(),
												BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)))),
						Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
