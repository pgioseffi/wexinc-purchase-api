package com.wexinc.purchase.api.shared.constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class Constantes {

	public static final String THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED = "This is a utility class and cannot be instantiated.";

	public static final String EXPECTED_THE_SAME_RESULT = "Expected the same result.";
	public static final String EXPECTED_DIFFERENT_RESULT = "Expected different results.";
	public static final String THE_EXCEPTION_WAS_NOT_THROWN = "The exception was not thrown.";
	public static final String CONSTRUCTOR_IS_NOT_PRIVATE = "Constructor is not private.";
	public static final String CONSTRUCTOR_CAN_BE_INITIALIZED = "Constructor can be initialized";
	public static final String SHOULD_NOT_HAVE_RETURNED_NULL = "Should not have returned null.";
	public static final String SHOULD_HAVE_RETURNED_NULL = "Should have returned null.";

	public static final Long LONG_MIN_VALUE = Long.valueOf(Long.MIN_VALUE);

	public static final LocalDateTime NOW_AS_LOCAL_DATE_TIME = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	public static final LocalDate NOW_AS_LOCAL_DATE = LocalDate.now();

	public static final String VALID_PURCHASE_DESCRIPTION = "Description";

	private Constantes() {
		throw new UnsupportedOperationException(Constantes.THIS_IS_A_UTILITY_CLASS_AND_CANNOT_BE_INSTANTIATED);
	}
}
