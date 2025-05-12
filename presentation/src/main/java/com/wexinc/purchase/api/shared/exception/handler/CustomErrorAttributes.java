package com.wexinc.purchase.api.shared.exception.handler;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

/**
 * Default implementation of {@link ErrorAttributes}. Provides the following attributes when
 * possible:
 *
 * <ul>
 *   <li>timestamp - The time that the errors were extracted
 *   <li>status - The status code
 *   <li>error - The error reason
 *   <li>message - The exception message (if configured)
 *   <li>errors - Any {@link ObjectError}s from a {@link BindingResult} exception (if configured)
 *   <li>path - The URL path when the exception was raised
 * </ul>
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see DefaultErrorAttributes
 * @see ErrorAttributes
 */
public class CustomErrorAttributes extends DefaultErrorAttributes {

  /**
   * Field responsible to hold the application logger.
   *
   * @since 1.0.0
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public Map<String, Object> getErrorAttributes(
      final WebRequest request, final ErrorAttributeOptions options) {
    final var e = this.getError(request);
    CustomErrorAttributes.LOGGER.error("Error found in the application: {}.", e.getMessage(), e);
    return super.getErrorAttributes(
        request, options.including(Include.BINDING_ERRORS, Include.MESSAGE));
  }
}
