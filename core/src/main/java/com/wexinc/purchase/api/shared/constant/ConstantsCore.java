package com.wexinc.purchase.api.shared.constant;

import java.util.regex.Pattern;

/**
 * Class responsible to hold the constants of the core module.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public final class ConstantsCore {

  /**
   * Constant holding the value of an {@link UnsupportedOperationException} with the message "This
   * is a utility class and cannot be instantiated.".
   *
   * @since 1.0.0
   */
  public static final UnsupportedOperationException UTILITY_CLASS_EXCEPTION =
      new UnsupportedOperationException("This is a utility class and cannot be instantiated.");

  /**
   * Constant holding the value {@value}.
   *
   * @since 1.0.0
   */
  public static final String EXCEPTION_MESSAGE_PURCHASE_NOT_FOUND =
      "No purchases found for identifier %d.";

  /**
   * Constant holding the value {@value}.
   *
   * @since 1.0.0
   */
  public static final String UNCHECKED = "unchecked";

  /**
   * Constant holding the value {@value}.
   *
   * @since 1.0.0
   */
  public static final int ZERO = 0;

  /**
   * A {@link Pattern} to compile the underscore symbol.
   *
   * @since 1.0.0
   */
  public static final Pattern PATTERN_UNDERSCORE = Pattern.compile("_");

  /**
   * Default constructor that forbids the class to be instantiated.
   *
   * @throws UnsupportedOperationException with {@link #UTILITY_CLASS_EXCEPTION} constant.
   * @since 1.0.0
   */
  private ConstantsCore() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
