package com.wexinc.purchase.api.resource;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;

class ExchangeRateDataResourceTest {

	private static final ExchangeRateDataResource ACTUAL = new ExchangeRateDataResource(Country.BRAZIL.name(),
			BigDecimal.ZERO, Constantes.FIXED_LOCAL_DATE);

	@Test
	void testCountry() {
		Assertions.assertEquals(Country.BRAZIL.name(), ExchangeRateDataResourceTest.ACTUAL.country(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testExchangeRate() {
		Assertions.assertEquals(BigDecimal.ZERO, ExchangeRateDataResourceTest.ACTUAL.exchangeRate(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testEffectiveDate() {
		Assertions.assertEquals(Constantes.FIXED_LOCAL_DATE, ExchangeRateDataResourceTest.ACTUAL.effectiveDate(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
