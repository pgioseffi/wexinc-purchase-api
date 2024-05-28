package com.wexinc.purchase.api.dto;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;

class ExchangeRateDataDTOTest {

	private static final ExchangeRateDataDTO ACTUAL = new ExchangeRateDataDTO(Country.BRAZIL.name(), BigDecimal.ZERO,
			Constantes.FIXED_LOCAL_DATE);

	@Test
	void testCountry() {
		Assertions.assertEquals(Country.BRAZIL.name(), ExchangeRateDataDTOTest.ACTUAL.country(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testDescription() {
		Assertions.assertEquals(Constantes.FIXED_LOCAL_DATE, ExchangeRateDataDTOTest.ACTUAL.effectiveDate(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testExchangeRate() {
		Assertions.assertEquals(BigDecimal.ZERO, ExchangeRateDataDTOTest.ACTUAL.exchangeRate(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
