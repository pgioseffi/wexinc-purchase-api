package com.wexinc.purchase.api.shared.exception.handler;

import com.wexinc.purchase.api.shared.constant.Constantes;
import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
class CustomErrorAttributesTest {

  @InjectMocks private CustomErrorAttributes instance;

  private final WebRequest request;

  CustomErrorAttributesTest(@Mock final WebRequest requestParam) {
    this.request = requestParam;
  }

  @Test
  void testShouldReturnErrorAttributes() {
    final var exceptionMessage = "Test exception";
    final var statusCode = Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    final var exception = new RuntimeException(exceptionMessage);

    // Mock the error and its attributes
    Mockito.when(
            this.request.getAttribute(
                DefaultErrorAttributes.class.getName() + ".ERROR", RequestAttributes.SCOPE_REQUEST))
        .thenReturn(exception);
    Mockito.when(
            this.request.getAttribute(
                RequestDispatcher.ERROR_STATUS_CODE, RequestAttributes.SCOPE_REQUEST))
        .thenReturn(statusCode);

    // Call the method under test
    final var result =
        this.instance.getErrorAttributes(
            this.request,
            ErrorAttributeOptions.of(
                Include.STATUS,
                Include.MESSAGE,
                Include.ERROR,
                Include.EXCEPTION,
                Include.BINDING_ERRORS));

    // Verify the expected attributes
    Assertions.assertEquals(statusCode, result.get("status"), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        exceptionMessage, result.get("message"), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        RuntimeException.class.getName(),
        result.get("exception"),
        Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        result.get("error"),
        Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertNotNull(result.get("timestamp"), Constantes.SHOULD_NOT_HAVE_RETURNED_NULL);
  }
}
