package com.wexinc.purchase.api.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Class responsible to set the application default locale to United States, which is the Wex Inc
 * country, in order to handle date and time correctly when currency exchange rate conversion is
 * needed.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see SessionLocaleResolver
 * @see WebMvcConfigurer
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

  /**
   * Method responsible to set the application default locale to United States, which is the Wex Inc
   * country, in order to handle date and time correctly when currency exchange rate conversion is
   * needed.
   *
   * @return The {@link SessionLocaleResolver} object with the default locale set to United States.
   * @since 1.0.0
   */
  @Bean
  static SessionLocaleResolver localeResolver() {
    final var resolver = new SessionLocaleResolver();
    resolver.setDefaultLocale(Locale.US);
    return resolver;
  }

  /**
   * Method responsible to intercept a request and change its locale based upon the lang request
   * header.
   *
   * @param registry The {@link InterceptorRegistry} object that will intercept the request and
   *     change the {@link Locale}.
   * @since 1.0.0
   */
  @Override
  // TODO: Seems to me a bad idea to allow user to change the locale, since we have to perform a
  // dollar based exchange
  // conversion. Must check it later.
  public void addInterceptors(final InterceptorRegistry registry) {
    final var localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
  }
}
