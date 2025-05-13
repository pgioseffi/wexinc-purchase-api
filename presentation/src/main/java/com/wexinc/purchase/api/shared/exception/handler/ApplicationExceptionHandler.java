package com.wexinc.purchase.api.shared.exception.handler;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.UnknownContentTypeException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * A class with an {@code @ExceptionHandler} method that handles all Spring MVC raised exceptions by
 * returning a {@link ResponseEntity} with RFC 7807 formatted error details in the body.
 *
 * <p>Convenient as a base class of an {@link ControllerAdvice @ControllerAdvice} for global
 * exception handling in an application. Subclasses can override individual methods that handle a
 * specific exception, override {@link #handleExceptionInternal} to override common handling of all
 * exceptions, or override {@link #createResponseEntity} to intercept the final step of creating the
 * {@link ResponseEntity} from the selected HTTP status code, headers, and body.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Field responsible to hold the application logger.
   *
   * @since 1.0.0
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

  /**
   * Field responsible to hold the Jackson {@link ObjectMapper} bean in order to handle JSON errors
   * responses coming from HTTP calls with statues 4xx or 5xx via {@link
   * org.springframework.web.client.RestTemplate RestTemplate}.
   *
   * @since 1.0.0
   * @see ObjectMapper
   * @see org.springframework.web.client.RestTemplate RestTemplate
   */
  private final ObjectMapper objectMapper;

  /**
   * Class complete constructor responsible to initialize all its fields.
   *
   * @param objectMapperParam Parameter responsible to initialize {@link #objectMapper} class field.
   * @since 1.0.0
   */
  public ApplicationExceptionHandler(final ObjectMapper objectMapperParam) {
    super();
    this.objectMapper = objectMapperParam;
  }

  /**
   * Customize the handling of {@link ConstraintViolationException}.
   *
   * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}.
   *
   * @param e the {@link ConstraintViolationException} to handle.
   * @param request The current request.
   * @return A {@code ResponseEntity} for the response to use, possibly {@code null} when the
   *     response is already committed.
   * @since 1.0.0
   */
  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Object> handleConstraintViolationException(
      final ConstraintViolationException e, final WebRequest request) {
    final var erros =
        e.getConstraintViolations().stream()
            .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
            .toList();

    return this.handleExceptionInternal(
        e,
        erros.size() == 1
            ? Map.of(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE, erros.get(0))
            : Map.of(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGES, erros),
        null,
        HttpStatus.UNPROCESSABLE_ENTITY,
        request);
  }

  /**
   * Customize the handling of {@link EntityNotFoundException}.
   *
   * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}.
   *
   * @param e the {@link EntityNotFoundException} to handle.
   * @param request The current request.
   * @return A {@code ResponseEntity} for the response to use, possibly {@code null} when the
   *     response is already committed.
   * @since 1.0.0
   */
  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntidadeNaoEncontradaException(
      final EntityNotFoundException e, final WebRequest request) {
    return this.handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, request);
  }

  /**
   * Customize the handling of {@link DataIntegrityViolationException}.
   *
   * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}.
   *
   * @param e the {@link DataIntegrityViolationException} to handle.
   * @param request The current request.
   * @return A {@code ResponseEntity} for the response to use, possibly {@code null} when the
   *     response is already committed.
   * @since 1.0.0
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Object> handleDataIntegrityViolationException(
      final DataIntegrityViolationException e, final WebRequest request) {
    return this.handleExceptionInternal(
        e, e.getMessage(), null, HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException e,
      final HttpHeaders headers,
      @SuppressWarnings("unused") final HttpStatusCode status,
      final WebRequest request) {
    final var erros = e.getAllErrors();

    return this.handleExceptionInternal(
        e,
        erros.size() == 1
            ? Map.of(
                ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE,
                erros.get(0) instanceof final FieldError fieldError
                    ? fieldError.getField() + ' ' + fieldError.getDefaultMessage()
                    : erros.get(0).toString())
            : Map.of(
                ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGES,
                Stream.concat(
                        e.getGlobalErrors().stream().map(ObjectError::toString),
                        e.getFieldErrors().stream()
                            .map(error -> (error.getField() + ' ' + error.getDefaultMessage())))
                    .sorted()
                    .toList()),
        headers,
        HttpStatus.UNPROCESSABLE_ENTITY,
        request);
  }

  /**
   * Customize the handling of {@link RestClientException}.
   *
   * <p>This method logs a warning and delegates to {@link #handleExceptionInternal}.
   *
   * @param e the {@link RestClientException} to handle.
   * @param request The current request.
   * @return A {@code ResponseEntity} for the response to use, possibly {@code null} when the
   *     response is already committed.
   * @since 1.0.0
   */
  @ExceptionHandler(RestClientException.class)
  protected ResponseEntity<Object> handleRestClientException(
      final RestClientException e, final WebRequest request) {
    if (e instanceof final RestClientResponseException other) {
      final var responseBodyAsString = other.getResponseBodyAsString(StandardCharsets.UTF_8);
      final var responseHeaders = other.getResponseHeaders();

      final var statusCode = other.getStatusCode();
      if (responseHeaders != null) {
        final var contentType = responseHeaders.getContentType();

        return MediaType.TEXT_HTML.equals(contentType)
                || contentType != null
                    && MediaType.TEXT_HTML_VALUE.equals(
                        contentType.getType() + '/' + contentType.getSubtype())
            ? new ResponseEntity<>(responseBodyAsString, responseHeaders, statusCode)
            : this.handleExceptionInternal(
                other,
                MediaType.APPLICATION_JSON.equals(contentType)
                    ? this.retornarRespostaAsJSON(responseBodyAsString)
                    : responseBodyAsString,
                responseHeaders,
                statusCode,
                request);
      }

      return this.handleExceptionInternal(
          other, responseBodyAsString, responseHeaders, statusCode, request);
    }

    if (e instanceof final UnknownContentTypeException other) {
      final var responseBodyAsString = other.getResponseBodyAsString();
      final var responseHeaders = other.getResponseHeaders();
      return this.handleExceptionInternal(
          other,
          responseHeaders != null
                  && MediaType.APPLICATION_JSON.equals(responseHeaders.getContentType())
              ? this.retornarRespostaAsJSON(responseBodyAsString)
              : responseBodyAsString,
          other.getResponseHeaders(),
          other.getStatusCode(),
          request);
    }

    return this.handleExceptionInternal(
        e, e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  /**
   * Method responsible handle JSON errors responses coming from HTTP calls with statues 4xx or 5xx
   * via {@link org.springframework.web.client.RestTemplate RestTemplate}.
   *
   * @param json A possible well-formed JSON {@link String} error response.
   * @return A {@link JsonNode} deserealized ready to be handled by {@link #handleExceptionInternal}
   *     method.
   * @since 1.0.0
   */
  private JsonNode retornarRespostaAsJSON(final String json) {
    try {
      return this.objectMapper.readTree(json);
    } catch (final JacksonException _) {
      return null;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @SuppressWarnings("unchecked")
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      final Exception e,
      @Nullable final Object body,
      final HttpHeaders headers,
      final HttpStatusCode status,
      final WebRequest request) {
    ApplicationExceptionHandler.LOGGER.error(
        "Error found in the application: {}.", e.getMessage(), e);

    final var httpHeaders =
        headers != null
            ? headers
            : new HttpHeaders(
                CollectionUtils.toMultiValueMap(
                    Map.of(
                        HttpHeaders.CONTENT_TYPE,
                        List.of(MediaType.APPLICATION_JSON_VALUE),
                        HttpHeaders.DATE,
                        List.of(Long.toString(System.currentTimeMillis())),
                        HttpHeaders.ACCEPT_CHARSET,
                        List.of(StandardCharsets.UTF_8.name()),
                        HttpHeaders.ACCEPT_LANGUAGE,
                        List.of(Locale.getDefault().toString()))));

    if (body instanceof final ObjectNode newBody) {
      return new ResponseEntity<>(
          newBody
              .put(
                  ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_PATH,
                  ((ServletWebRequest) request).getRequest().getRequestURI())
              .put(
                  ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_STATUS,
                  Integer.valueOf(status.value()))
              .put(
                  ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_ERROR,
                  status instanceof final HttpStatus novoStatus
                      ? novoStatus.getReasonPhrase()
                      : null)
              .put(
                  ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_TIMESTAMP,
                  Long.valueOf(System.currentTimeMillis())),
          httpHeaders,
          status);
    }

    final var newBody = new LinkedHashMap<String, Object>();
    newBody.put(
        ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_PATH,
        ((ServletWebRequest) request).getRequest().getRequestURI());
    newBody.put(ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_TIMESTAMP, LocalDateTime.now());
    newBody.put(
        ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_STATUS,
        Integer.valueOf(status.value()));
    newBody.put(
        ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_ERROR,
        status instanceof final HttpStatus novoStatus ? novoStatus.getReasonPhrase() : null);

    if (body instanceof Map) {
      newBody.putAll((Map<String, Object>) body);
    } else {
      newBody.put(
          ConstantsPresentation.APPLICATION_EXCEPTION_HANDLER_MESSAGE,
          body instanceof String || body instanceof byte[]
              ? body
              : StringUtils.isBlank(e.getMessage()) ? "No available message." : e.getMessage());
    }

    if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, RequestAttributes.SCOPE_REQUEST);
    }

    return new ResponseEntity<>(newBody, httpHeaders, status);
  }
}
