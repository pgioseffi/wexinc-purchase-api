package com.wexinc.purchase.api.dto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wexinc.purchase.api.shared.constant.Constantes;

class ExchangeRateDTOTest {

	private static final ExchangeRateDTO ACTUAL = new ExchangeRateDTO(List.of());

	@Test
	void testData() {
		Assertions.assertEquals(List.of(), ExchangeRateDTOTest.ACTUAL.data(), Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
