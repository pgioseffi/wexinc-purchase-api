package com.wexinc.purchase.api.shared.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownContentTypeException;
import org.springframework.web.context.request.ServletWebRequest;

@ExtendWith(MockitoExtension.class)
class ApplicationExceptionHandlerTest {

  private static final Type TYPE = String.class.getGenericSuperclass();

  @InjectMocks private ApplicationExceptionHandler instance;

  @Mock private ObjectMapper objectMapper;

  @Mock private ServletWebRequest request;

  @Mock private HttpServletRequest httpRequest;

  @Test
  void testShouldHandleAnyExceptionWithNoMessage() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        this.instance
            .handleExceptionInternal(
                new RuntimeException(), null, null, HttpStatus.UNPROCESSABLE_ENTITY, this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleAnyExceptionWithMessage() {
    final var errorMessage = "Error";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        this.instance
            .handleExceptionInternal(
                new RuntimeException(errorMessage),
                errorMessage.getBytes(StandardCharsets.UTF_8),
                null,
                HttpStatus.UNPROCESSABLE_ENTITY,
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleMethodArgumentNotValid() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    final var exception = Mockito.mock(MethodArgumentNotValidException.class);
    Mockito.when(exception.getAllErrors())
        .thenReturn(List.of(new ObjectError("test", "testMessage")));

    final var response =
        this.instance.handleMethodArgumentNotValid(
            exception, null, HttpStatus.UNPROCESSABLE_ENTITY, this.request);

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        response.getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    @SuppressWarnings("unchecked")
    final var body = (Map<String, Object>) response.getBody();

    Assertions.assertEquals(
        "Error in object 'test': codes []; arguments []; default message [testMessage]",
        body.get(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleSingleConstraintViolation() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    final var exception = Mockito.mock(ConstraintViolationException.class);
    final var violation = Mockito.mock(ConstraintViolation.class);
    final Set<ConstraintViolation<?>> violations = Set.of(violation);
    Mockito.when(exception.getConstraintViolations()).thenReturn(violations);
    Mockito.when(violation.getPropertyPath())
        .thenReturn(PathImpl.createPathFromString("propertyPath"));
    Mockito.when(violation.getMessage()).thenReturn("message");

    final var response = this.instance.handleConstraintViolationException(exception, this.request);

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        response.getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    @SuppressWarnings("unchecked")
    final var body = (Map<String, Object>) response.getBody();

    Assertions.assertEquals(
        "propertyPath: message",
        body.get(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleSingleConstraintViolationWithNullValue() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    final var exception = Mockito.mock(ConstraintViolationException.class);
    final Set<ConstraintViolation<?>> violations =
        new HashSet<>(Arrays.asList((ConstraintViolation<?>) null));
    Mockito.when(exception.getConstraintViolations()).thenReturn(violations);

    final var response = this.instance.handleConstraintViolationException(exception, this.request);

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        response.getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    @SuppressWarnings("unchecked")
    final var body = (Map<String, Object>) response.getBody();

    Assertions.assertEquals(
        "null",
        body.get(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleMultipleConstraintViolations() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    final var exception = Mockito.mock(ConstraintViolationException.class);
    final var violation01 = Mockito.mock(ConstraintViolation.class);
    final var violation02 = Mockito.mock(ConstraintViolation.class);
    final Set<ConstraintViolation<?>> violations = Set.of(violation01, violation02);

    Mockito.when(exception.getConstraintViolations()).thenReturn(violations);
    Mockito.when(violation01.getPropertyPath())
        .thenReturn(PathImpl.createPathFromString("propertyPath01"));
    Mockito.when(violation01.getMessage()).thenReturn("message01");
    Mockito.when(violation02.getPropertyPath())
        .thenReturn(PathImpl.createPathFromString("propertyPath02"));
    Mockito.when(violation02.getMessage()).thenReturn("message02");

    final var response = this.instance.handleConstraintViolationException(exception, this.request);

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        response.getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    @SuppressWarnings("unchecked")
    final var body = (Map<String, Object>) response.getBody();

    @SuppressWarnings("unchecked")
    final var messages =
        (List<String>) body.get(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGES);

    Assertions.assertLinesMatch(
        List.of("propertyPath01: message01", "propertyPath02: message02").stream()
            .sorted()
            .toList(),
        messages.stream().sorted().toList(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    org.assertj.core.api.Assertions.assertThat(messages)
        .containsExactlyInAnyOrder("propertyPath01: message01", "propertyPath02: message02");
  }

  @Test
  void testShouldHandleDataIntegrityViolationException() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.UNPROCESSABLE_ENTITY,
        this.instance
            .handleDataIntegrityViolationException(
                new DataIntegrityViolationException("Error"), this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientException() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.INTERNAL_SERVER_ERROR,
        this.instance
            .handleRestClientException(new RestClientException("Error"), this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientResponseExceptionWithHTMLHeaders() {
    Assertions.assertEquals(
        HttpStatus.SERVICE_UNAVAILABLE,
        this.instance
            .handleRestClientException(
                HttpClientErrorException.create(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(HttpHeaders.CONTENT_TYPE, List.of(MediaType.TEXT_HTML_VALUE)))),
                    """
														<html>
															<head>
																<title>Status 503 - Service Unavailable</title>
															</head>
															<body>
																<h1>Status 503 - Service Unavailable</h1>
															</body>
														</html>
														"""
                        .getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientResponseExceptionWithNoContentType() {
    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.SERVICE_UNAVAILABLE,
        this.instance
            .handleRestClientException(
                HttpClientErrorException.create(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(HttpHeaders.ACCEPT, List.of(MediaType.TEXT_HTML_VALUE)))),
                    "Erro".getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientResponseExceptionWithHTMLHeadersTypeAndSubType() {
    Assertions.assertEquals(
        HttpStatus.SERVICE_UNAVAILABLE,
        this.instance
            .handleRestClientException(
                HttpClientErrorException.create(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(),
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(
                                HttpHeaders.CONTENT_TYPE,
                                List.of(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")))),
                    """
										<html>
											<head>
												<title>Status 503 - Service Unavailable</title>
											</head>
											<body>
												<h1>Status 503 - Service Unavailable</h1>
											</body>
										</html>
										"""
                        .getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientResponseExceptionWithJSONResponse()
      throws JsonMappingException, JsonProcessingException {
    final var mapper = new ObjectMapper();
    final var mappedJSON =
        mapper
            .createObjectNode()
            .set("error", mapper.convertValue("Service unavailable", JsonNode.class));
    final var errorMessage = "{\"error\":\"Service unavailable\"}";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');
    Mockito.when(this.objectMapper.readTree(errorMessage)).thenReturn(mappedJSON);

    Assertions.assertEquals(
        HttpStatus.BAD_REQUEST,
        this.instance
            .handleRestClientException(
                HttpClientErrorException.create(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(
                                HttpHeaders.CONTENT_TYPE,
                                List.of(MediaType.APPLICATION_JSON_VALUE)))),
                    errorMessage.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleRestClientResponseExceptionWithTextResponse() {
    final var errorMessage = "Error";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatus.BAD_REQUEST,
        this.instance
            .handleRestClientException(
                HttpClientErrorException.create(
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(HttpHeaders.CONTENT_TYPE, List.of(MediaType.TEXT_PLAIN_VALUE)))),
                    errorMessage.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleUnknownContentTypeExceptionWithNullHeaders() {
    final var invalidStatusCode = 999;
    final var invalidStatusCodeMessage = "Invalid status code";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatusCode.valueOf(invalidStatusCode),
        this.instance
            .handleRestClientException(
                new UnknownContentTypeException(
                    ApplicationExceptionHandlerTest.TYPE,
                    MediaType.APPLICATION_JSON,
                    invalidStatusCode,
                    invalidStatusCodeMessage,
                    null,
                    invalidStatusCodeMessage.getBytes(StandardCharsets.UTF_8)),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleUnknownContentTypeExceptionWithJsonResponse()
      throws JsonMappingException, JsonProcessingException {
    final var invalidStatusCode = 999;
    final var invalidStatusCodeMessage = "{\"error\":\"Invalid status code\"}";
    final var mapper = new ObjectMapper();
    // Do not use mocked ObjectMapper, it will not perform the operation.
    final var mappedJSON =
        mapper
            .createObjectNode()
            .set("error", mapper.convertValue("Invalid status code", JsonNode.class));

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');
    Mockito.when(this.objectMapper.readTree(invalidStatusCodeMessage)).thenReturn(mappedJSON);

    Assertions.assertEquals(
        HttpStatusCode.valueOf(invalidStatusCode),
        this.instance
            .handleRestClientException(
                new UnknownContentTypeException(
                    ApplicationExceptionHandlerTest.TYPE,
                    MediaType.APPLICATION_JSON,
                    invalidStatusCode,
                    invalidStatusCodeMessage,
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(
                                HttpHeaders.CONTENT_TYPE,
                                List.of(MediaType.APPLICATION_JSON_VALUE)))),
                    invalidStatusCodeMessage.getBytes(StandardCharsets.UTF_8)),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleUnknownContentTypeExceptionWithInvalidJsonResponse()
      throws JsonMappingException, JsonProcessingException {
    final var invalidStatusCode = 999;
    final var invalidStatusCodeMessage = "{\"error\":\"Invalid status code\"}";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');
    Mockito.when(this.objectMapper.readTree(invalidStatusCodeMessage))
        .thenThrow(JsonMappingException.class);

    Assertions.assertEquals(
        HttpStatusCode.valueOf(invalidStatusCode),
        this.instance
            .handleRestClientException(
                new UnknownContentTypeException(
                    ApplicationExceptionHandlerTest.TYPE,
                    MediaType.APPLICATION_JSON,
                    invalidStatusCode,
                    invalidStatusCodeMessage,
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(
                                HttpHeaders.CONTENT_TYPE,
                                List.of(MediaType.APPLICATION_JSON_VALUE)))),
                    invalidStatusCodeMessage.getBytes(StandardCharsets.UTF_8)),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldHandleUnknownContentTypeExceptionWithHTMLHeaders() {
    final var invalidStatusCode = 999;
    // Yes, I do know that this would come as 503 status code. This is just for testing purposes, it
    // could be any valid HTML response, for example or no HTML at all.
    final var invalidStatusCodeMessage =
        """
				<html>
					<head>
						<title>Status 503 - Service Unavailable</title>
					</head>
					<body>
						<h1>Status 503 - Service Unavailable</h1>
					</body>
				</html>
				""";

    Mockito.when(this.request.getRequest()).thenReturn(this.httpRequest);
    Mockito.when(this.httpRequest.getRequestURI())
        .thenReturn(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + '1');

    Assertions.assertEquals(
        HttpStatusCode.valueOf(invalidStatusCode),
        this.instance
            .handleRestClientException(
                new UnknownContentTypeException(
                    ApplicationExceptionHandlerTest.TYPE,
                    MediaType.TEXT_HTML,
                    invalidStatusCode,
                    invalidStatusCodeMessage,
                    new HttpHeaders(
                        CollectionUtils.toMultiValueMap(
                            Map.of(HttpHeaders.CONTENT_TYPE, List.of(MediaType.TEXT_HTML_VALUE)))),
                    invalidStatusCodeMessage.getBytes(StandardCharsets.UTF_8)),
                this.request)
            .getStatusCode(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
