package com.wexinc.purchase.api.mapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.resource.ExchangeRateDataResource;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;

class ExchangeRateInfrastructureMapperImplTest {

	private final ExchangeRateInfrastructureMapper MAPPER = Mappers.getMapper(ExchangeRateInfrastructureMapper.class);

	@Test
	void shouldNotMap() {
		Assertions.assertNull(this.MAPPER.fromResourceToDTO(null), Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldNotMapData() {
		Assertions.assertEquals(new ExchangeRateDTO(null),
				this.MAPPER.fromResourceToDTO(new ExchangeRateResource(null)), Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldNotMapDataItem() {
		Assertions.assertEquals(new ExchangeRateDTO(Arrays.asList((ExchangeRateDataDTO) null)),
				this.MAPPER.fromResourceToDTO(new ExchangeRateResource(Arrays.asList((ExchangeRateDataResource) null))),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMap() {
		Assertions.assertEquals(
				new ExchangeRateDTO(List.of(
						new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE))),
				this.MAPPER.fromResourceToDTO(
						new ExchangeRateResource(List.of(new ExchangeRateDataResource(Country.BRAZIL.name(),
								BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE)))),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
