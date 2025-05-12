package com.wexinc.purchase.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

  @Test
  void testMain() {
    try (MockedStatic<SpringApplication> mocked = Mockito.mockStatic(SpringApplication.class)) {
      mocked
          .when(() -> SpringApplication.run(Application.class, "wexinc"))
          .thenReturn(Mockito.mock(ConfigurableApplicationContext.class));

      Application.main(new String[] {"wexinc"});

      mocked.verify(() -> SpringApplication.run(Application.class, "wexinc"));
    }
  }

  @Test
  void testBeanFactoryPostProcessor() {
    Assertions.assertEquals(
        0,
        SpringApplication.exit(
            SpringApplication.run(Application.class, "--spring.output.ansi.enabled=always"),
            () -> 0),
        "Different codes of Spring Application shutdown");
  }
}
