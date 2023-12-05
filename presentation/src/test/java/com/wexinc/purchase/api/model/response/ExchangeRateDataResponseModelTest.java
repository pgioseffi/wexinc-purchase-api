package com.wexinc.purchase.api.model.response;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.Country;

class ExchangeRateDataResponseModelTest {

	private static final ExchangeRateDataResponseModel ACTUAL = new ExchangeRateDataResponseModel(Country.BRAZIL.name(),
			BigDecimal.ZERO);

	@Test
	void testCountry() {
		Assertions.assertEquals(Country.BRAZIL.name(), ExchangeRateDataResponseModelTest.ACTUAL.country(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testDescription() {
		Assertions.assertEquals(BigDecimal.ZERO, ExchangeRateDataResponseModelTest.ACTUAL.exchangeRate(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
