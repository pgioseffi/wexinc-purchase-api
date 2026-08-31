package com.wexinc.purchase.api.component;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.tomcat.autoconfigure.servlet.TomcatServletWebServerAutoConfiguration;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(
    webEnvironment = WebEnvironment.DEFINED_PORT,
    classes = {ConsulPropertiesComponent.class, TomcatServletWebServerAutoConfiguration.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class ConsulPropertiesComponentTest {

  @Autowired private ConsulPropertiesComponent consulProperties;

  @Test
  void testGetCurrencyConversionURL() {
    Assertions.assertNotNull(
        this.consulProperties.getCurrencyConversionURL(),
        CoreTestConstants.SHOULD_NOT_HAVE_RETURNED_NULL);
  }

  @Test
  void testGetCurrencyConversionLeniencyInMonths() {
    Assertions.assertTrue(
        this.consulProperties.getCurrencyConversionLeniencyInMonths() > 0L,
        CoreTestConstants.THE_RESULT_SHOULD_HAVE_BEEN_TRUE);
  }
}
