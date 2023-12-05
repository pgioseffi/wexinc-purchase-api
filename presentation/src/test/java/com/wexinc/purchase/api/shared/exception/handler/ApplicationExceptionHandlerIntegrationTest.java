package com.wexinc.purchase.api.shared.exception.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationExceptionHandlerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldHandleEntityNotFoundException() {
		final ResponseEntity<PurchaseResponseModel> response = this.restTemplate.exchange(
				ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
				HttpMethod.GET, null, PurchaseResponseModel.class, Long.valueOf(Long.MAX_VALUE));

		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldHandleConstraintViolationException() {
		final ResponseEntity<PurchaseResponseModel> response = this.restTemplate.exchange(
				ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
				HttpMethod.GET, null, PurchaseResponseModel.class, Long.valueOf(-1L));

		Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldHandleMethodArgumentNotValidExceptionMultipleErrors() {
		final ResponseEntity<PurchaseResponseModel> response = this.restTemplate.exchange(
				ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE, HttpMethod.POST,
				new HttpEntity<>(new PurchaseRequestModel(StringUtils.EMPTY, LocalDateTime.now().plusYears(1),
						new BigDecimal(-1))),
				PurchaseResponseModel.class);

		Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldHandleMethodArgumentNotValidExceptionOneError() {
		final ResponseEntity<PurchaseResponseModel> response = this.restTemplate.exchange(
				ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE, HttpMethod.POST,
				new HttpEntity<>(new PurchaseRequestModel(Constantes.VALID_PURCHASE_DESCRIPTION,
						LocalDateTime.now().plusYears(1), BigDecimal.TEN)),
				PurchaseResponseModel.class);

		Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode(),
				Constantes.EXPECTED_THE_SAME_RESULT);
	}

	@Test
	void shouldNotFindEnhancedPurchaseById() {
		final var saveResponse = this.restTemplate.exchange(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE,
				HttpMethod.POST,
				new HttpEntity<>(new PurchaseRequestModel(Constantes.VALID_PURCHASE_DESCRIPTION,
						LocalDateTime.of(1500, Month.DECEMBER, 5, 5, 2, 0, 0), BigDecimal.TEN)),
				PurchaseResponseModel.class);
		final var one = Long.valueOf(1L);

		Assertions.assertEquals(HttpStatus.CREATED, saveResponse.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
		Assertions.assertEquals(
				new PurchaseResponseModel(one, Constantes.VALID_PURCHASE_DESCRIPTION,
						LocalDateTime.of(1500, Month.DECEMBER, 5, 5, 2, 0, 0),
						BigDecimal.TEN.setScale(ConstantsCore.TWO, RoundingMode.HALF_EVEN)),
				saveResponse.getBody(), Constantes.EXPECTED_THE_SAME_RESULT);

		final var response = this.restTemplate.exchange(
				UriComponentsBuilder
						.fromUriString(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
								+ ConstantsPresentation.ENHANCED_REQUEST_MAPPING_VALUE
								+ ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
						.queryParam("countries", Country.BRAZIL.name()).encode().toUriString(),
				HttpMethod.GET, null, EnhancedPurchaseResponseModel.class, one);

		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
	}
}
