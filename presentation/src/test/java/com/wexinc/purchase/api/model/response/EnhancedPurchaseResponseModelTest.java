package com.wexinc.purchase.api.model.response;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wexinc.purchase.api.shared.constant.Constantes;

class EnhancedPurchaseResponseModelTest {

	private static final EnhancedPurchaseResponseModel ACTUAL = new EnhancedPurchaseResponseModel(
			Constantes.LONG_MIN_VALUE, StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO, List.of());

	@Test
	void testId() {
		Assertions.assertEquals(Constantes.LONG_MIN_VALUE, EnhancedPurchaseResponseModelTest.ACTUAL.id(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testDescription() {
		Assertions.assertEquals(StringUtils.EMPTY, EnhancedPurchaseResponseModelTest.ACTUAL.description(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testTransactionDate() {
		Assertions.assertEquals(Constantes.FIXED_LOCAL_DATE_TIME,
				EnhancedPurchaseResponseModelTest.ACTUAL.transactionDate(), Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testAmount() {
		Assertions.assertEquals(BigDecimal.ZERO, EnhancedPurchaseResponseModelTest.ACTUAL.amount(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void testExchangeRate() {
		Assertions.assertEquals(List.of(), EnhancedPurchaseResponseModelTest.ACTUAL.exchangeRateData(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
