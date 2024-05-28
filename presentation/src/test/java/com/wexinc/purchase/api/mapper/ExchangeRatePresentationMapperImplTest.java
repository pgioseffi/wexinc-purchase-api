package com.wexinc.purchase.api.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;

class ExchangeRatePresentationMapperImplTest {

	private static final ExchangeRatePresentationMapper MAPPER = Mappers
			.getMapper(ExchangeRatePresentationMapper.class);

	@Test
	void shouldNotMapFromResourceToDTO() {
		Assertions.assertNull(ExchangeRatePresentationMapperImplTest.MAPPER.fromResourceToDTO(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldNotMapFromResourceDataToDTOData() {
		Assertions.assertEquals(new ExchangeRateDTO(null),
				ExchangeRatePresentationMapperImplTest.MAPPER.fromResourceToDTO(new ExchangeRateResource(null)),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldNotMapFromResourceDataItemToDTODataItem() {
		Assertions.assertEquals(new ExchangeRateDTO(Arrays.asList((ExchangeRateDataDTO) null)),
				ExchangeRatePresentationMapperImplTest.MAPPER
						.fromResourceToDTO(new ExchangeRateResource(Arrays.asList((ExchangeRateDataResource) null))),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapFromResourceToDTO() {
		Assertions.assertEquals(
				new ExchangeRateDTO(List.of(
						new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE))),
				ExchangeRatePresentationMapperImplTest.MAPPER.fromResourceToDTO(
						new ExchangeRateResource(List.of(new ExchangeRateDataResource(Country.BRAZIL.name(),
								BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)))),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldNotMapFromDTOToResponseModel() {
		Assertions.assertNull(ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(null, null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldMapJustAmount() {
		Assertions.assertEquals(new ExchangeRateDataResponseModel(null, null, null),
				ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(BigDecimal.TEN, null),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapFromDTOToResponseModel() {
		Assertions.assertEquals(
				new ExchangeRateDataResponseModel(Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO,
						BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN)),
				ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(BigDecimal.TEN,
						new ExchangeRateDataDTO(Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO,
								Constantes.FIXED_LOCAL_DATE)),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapFromDTOToResponseModels() {
		Assertions.assertEquals(
				new ExchangeRateDataResponseModel(Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, null),
				ExchangeRatePresentationMapperImplTest.MAPPER.fromDTOToResponseModel(null, new ExchangeRateDataDTO(
						Country.BRAZIL.getCapitalizedName(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
