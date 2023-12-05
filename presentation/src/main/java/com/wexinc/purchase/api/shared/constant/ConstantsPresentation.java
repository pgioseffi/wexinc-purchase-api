package com.wexinc.purchase.api.shared.constant;

/**
 * Class responsible to hold the constants of the presentation module.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public final class ConstantsPresentation {

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String PURCHASE_REQUEST_MAPPING_VALUE = "/v1/api/purchase/";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String ID_REQUEST_PATH_VARIABLE = "{id}";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String ENHANCED_REQUEST_MAPPING_VALUE = "enhanced/";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_STATUS = "status";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_PATH = "path";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_MESSAGE = "message";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_MESSAGES = "messages";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_ERROR = "error";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 * @since 1.0.0
	 */
	public static final String APPLICATION_EXCEPTION_HANDLER_TIMESTAMP = "timestamp";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_200 = "200";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_201 = "201";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_400 = "400";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_404 = "404";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_422 = "422";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_STATUS_CODE_500 = "500";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_DESCRIPTION_STATUS_CODE_400 = "Invalid payload.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_DESCRIPTION_STATUS_CODE_422 = "Valid payload, but with invalid content.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String RESPONSE_DESCRIPTION_STATUS_CODE_500 = "Internal server error.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String OPENAPI_DOCUMENTATION_PURCHASE_ID = "Mandatory field responsible for holding purchase identification information.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String OPENAPI_DOCUMENTATION_PURCHASE_TRANSACTION_DATE = "Mandatory field responsible for holding the purchase transaction date. Must be in the past.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String OPENAPI_DOCUMENTATION_PURCHASE_DESCRIPTION = "Mandatory field responsible for holding the purchase description containing at most fifty characters. Must not be null, empty or filled with spaces only.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final String OPENAPI_DOCUMENTATION_PURCHASE_AMOUNT = "Mandatory field responsible for holding the purchase transaction amount. Must be a positive decimal number.";

	/**
	 * Constant holding the value {@value}.
	 *
	 * @since 1.0.0
	 */
	public static final int TWO = 2;

	/**
	 * Default constructor that forbids the class to be instantiated.
	 *
	 * @throws UnsupportedOperationException with {@link ConstantsCore#CLASS_CANT_BE_INSTANTIATED} message.
	 * @since 1.0.0
	 */
	private ConstantsPresentation() {
		throw new UnsupportedOperationException(ConstantsCore.CLASS_CANT_BE_INSTANTIATED);
	}
}
