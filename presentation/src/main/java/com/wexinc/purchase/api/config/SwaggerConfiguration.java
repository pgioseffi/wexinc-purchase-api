package com.wexinc.purchase.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class responsible for redirecting application context entry point to Swagger documentation.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see WebMvcConfigurer
 * @see ViewControllerRegistry
 */
@Configuration
class SwaggerConfiguration implements WebMvcConfigurer {

  /**
   * {@inheritDoc}
   *
   * @version 1.0.0
   * @since 1.0.0
   */
  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/swagger-ui/index.html");
  }
}
