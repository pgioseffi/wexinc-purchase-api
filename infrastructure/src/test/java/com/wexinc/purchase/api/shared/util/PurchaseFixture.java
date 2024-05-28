package com.wexinc.purchase.api.shared.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.shared.constant.Constantes;

public final class PurchaseFixture {

	public static final Purchase ACTUAL_PURCHASE = new Purchase(Constantes.LONG_MIN_VALUE, StringUtils.EMPTY,
			Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO);

	private PurchaseFixture() {
		throw new UnsupportedOperationException(Constantes.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
	}
}
