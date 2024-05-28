package com.wexinc.purchase.api.mapper;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;

class PurchaseInfrastructureMapperImplTest {

	private static final PurchaseInfrastructureMapper MAPPER = Mappers.getMapper(PurchaseInfrastructureMapper.class);

	@Test
	void shouldMapEntityToDTO() {
		Assertions.assertEquals(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
				PurchaseInfrastructureMapperImplTest.MAPPER.fromEntityToDTO(new Purchase(Constantes.LONG_MIN_VALUE,
						StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO)),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapEntityToNull() {
		Assertions.assertNull(PurchaseInfrastructureMapperImplTest.MAPPER.fromEntityToDTO(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}

	@Test
	void shouldMapDTOToEntity() {
		Assertions.assertEquals(
				new Purchase(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME,
						BigDecimal.ZERO),
				PurchaseInfrastructureMapperImplTest.MAPPER.fromDTOToEntity(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldMapDTOToNull() {
		Assertions.assertNull(PurchaseInfrastructureMapperImplTest.MAPPER.fromDTOToEntity(null),
				Constantes.SHOULD_HAVE_RETURNED_NULL);
	}
}
