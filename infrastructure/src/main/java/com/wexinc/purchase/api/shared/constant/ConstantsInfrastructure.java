package com.wexinc.purchase.api.shared.constant;

/**
 * Class responsible to hold the constants of the infrastructure module.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public final class ConstantsInfrastructure {

  /**
   * Constant holding the value {@value}.
   *
   * @since 1.0.0
   */
  public static final String PURCHASE_IDENTIFICATION_SEQUENCE_GENERATOR =
      "purchase-identification-sequence-generator";

  /**
   * Constant holding the value {@value}.
   *
   * @since 1.0.0
   */
  public static final String PURCHASE_IDENTIFICATION_SEQUENCE = "purchase_identification_sequence";

  /**
   * Default constructor that forbids the class to be instantiated.
   *
   * @throws UnsupportedOperationException with {@link ConstantsCore#CLASS_CANT_BE_INSTANTIATED}
   *     message.
   * @since 1.0.0
   */
  private ConstantsInfrastructure() {
    throw new UnsupportedOperationException(ConstantsCore.CLASS_CANT_BE_INSTANTIATED);
  }
}
