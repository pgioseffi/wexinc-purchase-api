package com.wexinc.purchase.api.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Class responsible to hold the dynamic values shared by the application coming from <a
 * href="https://www.consul.io/" target="Consul">Consul</a>.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @version 1.0.0
 * @since 1.0.0
 * @see ConsulProperties
 */
@RefreshScope
public non-sealed class ConsulPropertiesComponent implements ConsulProperties {

  /**
   * Field responsible to hold the dynamic value shared by the application coming from <a
   * href="https://www.consul.io/" target="Consul">Consul</a> that will be served as the URL to
   * calculate currency exchange rate for the already made purchases in the database.
   *
   * @since 1.0.0
   */
  private final String currencyConversionURL;

  /**
   * Field responsible to hold the dynamic value shared by the application coming from <a
   * href="https://www.consul.io/" target="Consul">Consul</a> that will be served as currency
   * conversion leniency in months.
   *
   * @since 1.0.0
   */
  private final long currencyConversionLeniencyInMonths;

  /**
   * Class complete constructor.
   *
   * @param currencyConversionURLParam Parameter responsible to populate the {@link
   *     #currencyConversionURL} field with its value.
   * @param currencyConversionLeniencyInMonthsParam Parameter responsible to populate the {@link
   *     #currencyConversionLeniencyInMonths} field with its value.
   * @since 1.0.0
   */
  public ConsulPropertiesComponent(
      @Value("${currency.conversion.URL}") final String currencyConversionURLParam,
      @Value("${currency.conversion.leniencyin.months}")
          final long currencyConversionLeniencyInMonthsParam) {
    this.currencyConversionURL = currencyConversionURLParam;
    this.currencyConversionLeniencyInMonths = currencyConversionLeniencyInMonthsParam;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public String getCurrencyConversionURL() {
    return this.currencyConversionURL;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public long getCurrencyConversionLeniencyInMonths() {
    return this.currencyConversionLeniencyInMonths;
  }
}
