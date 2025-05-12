package com.wexinc.purchase.api.delivery.rest;

import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;
import com.wexinc.purchase.api.shared.util.PurchaseRequestModelFixture;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class PurchaseRestControllerIntegrationTest {

  private static final PurchaseResponseModel RESPONSE_MODEL =
      new PurchaseResponseModel(
          Long.valueOf(1),
          Constantes.VALID_PURCHASE_DESCRIPTION,
          Constantes.FIXED_LOCAL_DATE_TIME,
          BigDecimal.TEN.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN));

  @Autowired private TestRestTemplate restTemplate;

  @Test
  @Order(1)
  void shouldSave() {
    final var response =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE,
            HttpMethod.POST,
            new HttpEntity<>(PurchaseRequestModelFixture.ACTUAL_VALID_PURCHASE_REQUEST_MODEL),
            PurchaseResponseModel.class);

    Assertions.assertEquals(
        HttpStatus.CREATED, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        PurchaseRestControllerIntegrationTest.RESPONSE_MODEL,
        response.getBody(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(2)
  void shouldFindById() {
    final var response =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
                + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
            HttpMethod.GET,
            null,
            PurchaseResponseModel.class,
            Long.valueOf(1L));

    Assertions.assertEquals(
        HttpStatus.OK, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        PurchaseRestControllerIntegrationTest.RESPONSE_MODEL,
        response.getBody(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(3)
  void shouldFindAll() {
    final ResponseEntity<List<PurchaseResponseModel>> response =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE,
            HttpMethod.GET,
            null,
            ParameterizedTypeReference.forType(
                TypeUtils.parameterize(List.class, PurchaseResponseModel.class)));

    Assertions.assertEquals(
        HttpStatus.OK, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(1, response.getBody().size(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        PurchaseRestControllerIntegrationTest.RESPONSE_MODEL,
        response.getBody().get(0),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(4)
  void shouldUpdate() {
    final var response =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
                + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
            HttpMethod.PUT,
            new HttpEntity<>(
                new PurchaseRequestModel(
                    Constantes.VALID_PURCHASE_DESCRIPTION,
                    Constantes.FIXED_LOCAL_DATE_TIME,
                    BigDecimal.ONE.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN))),
            PurchaseResponseModel.class,
            Long.valueOf(1));

    Assertions.assertEquals(
        HttpStatus.OK, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        new PurchaseResponseModel(
            Long.valueOf(1),
            Constantes.VALID_PURCHASE_DESCRIPTION,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ONE.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)),
        response.getBody(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(5)
  void shouldDelete() {
    final var response =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
                + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
            HttpMethod.DELETE,
            null,
            Void.class,
            Long.valueOf(1));

    Assertions.assertEquals(
        HttpStatus.NO_CONTENT, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(6)
  void shouldNotFindById() {
    final var response =
        this.restTemplate.getForEntity(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
                + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE,
            PurchaseResponseModel.class,
            Long.valueOf(1L));

    Assertions.assertEquals(
        HttpStatus.NOT_FOUND, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  @Order(7)
  void shouldNotFindEnhancedPurchaseById() {
    final var saveResponse =
        this.restTemplate.exchange(
            ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE,
            HttpMethod.POST,
            new HttpEntity<>(PurchaseRequestModelFixture.ACTUAL_VALID_PURCHASE_REQUEST_MODEL),
            PurchaseResponseModel.class);
    final var two = Long.valueOf(ConstantsPresentation.TWO);
    final var purchaseAmount =
        BigDecimal.TEN.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN);
    final var exchangeRateArgentina = new BigDecimal("365.5");
    final var exchangeRateBrazil = new BigDecimal("5.033");
    final var exchangeRateMexico = new BigDecimal("17.471");
    final var exchangeRateUruguay = new BigDecimal("38.45");

    Assertions.assertEquals(
        HttpStatus.CREATED, saveResponse.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        new PurchaseResponseModel(
            two,
            Constantes.VALID_PURCHASE_DESCRIPTION,
            Constantes.FIXED_LOCAL_DATE_TIME,
            purchaseAmount),
        saveResponse.getBody(),
        Constantes.EXPECTED_THE_SAME_RESULT);

    final var response =
        this.restTemplate.exchange(
            UriComponentsBuilder.fromUriString(
                    ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE
                        + ConstantsPresentation.ENHANCED_REQUEST_MAPPING_VALUE
                        + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
                .queryParam(
                    "countries",
                    String.join(
                        ",",
                        List.of(
                            Country.BRAZIL.name(),
                            Country.ARGENTINA.name(),
                            Country.MEXICO.name(),
                            Country.URUGUAY.name())))
                .encode()
                .toUriString(),
            HttpMethod.GET,
            null,
            EnhancedPurchaseResponseModel.class,
            two);

    Assertions.assertEquals(
        HttpStatus.OK, response.getStatusCode(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        new EnhancedPurchaseResponseModel(
            two,
            Constantes.VALID_PURCHASE_DESCRIPTION,
            Constantes.FIXED_LOCAL_DATE_TIME,
            purchaseAmount,
            List.of(
                new ExchangeRateDataResponseModel(
                    Country.ARGENTINA.getCapitalizedName(),
                    exchangeRateArgentina,
                    purchaseAmount
                        .multiply(exchangeRateArgentina)
                        .setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)),
                new ExchangeRateDataResponseModel(
                    Country.BRAZIL.getCapitalizedName(),
                    exchangeRateBrazil,
                    purchaseAmount
                        .multiply(exchangeRateBrazil)
                        .setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)),
                new ExchangeRateDataResponseModel(
                    Country.MEXICO.getCapitalizedName(),
                    exchangeRateMexico,
                    purchaseAmount
                        .multiply(exchangeRateMexico)
                        .setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)),
                new ExchangeRateDataResponseModel(
                    Country.URUGUAY.getCapitalizedName(),
                    exchangeRateUruguay,
                    purchaseAmount
                        .multiply(exchangeRateUruguay)
                        .setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN)))),
        response.getBody(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
