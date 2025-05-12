package com.wexinc.purchase.api.component;

/**
 * Interface responsible to define the methods signatures to the dynamic values shared by the
 * application coming from <a href="https://www.consul.io/" target="Consul">Consul</a>.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @version 1.0.0
 * @since 1.0.0
 * @see ConsulPropertiesComponent
 */
public sealed interface ConsulProperties permits ConsulPropertiesComponent {

  /**
   * Method responsible to return the dynamic value shared by the application coming from <a
   * href="https://www.consul.io/" target="Consul">Consul</a> that will be served as the URL to
   * calculate currency exchange rate for the already made purchases in the database.
   *
   * @return A {@link String} holding the dynamic value shared by the application coming from <a
   *     href="https://www.consul.io/" target="Consul">Consul</a> that will be served as the URL to
   *     calculate currency exchange rate for the already made purchases in the database.
   * @since 1.0.0
   */
  String getCurrencyConversionURL();

  /**
   * Method responsible to return the leniency defined to search for currency exchange rates in
   * months.
   *
   * @return The intrinsic <code>long</code> holding leniency defined to search for currency
   *     exchange rates in months.
   * @since 1.0.0
   */
  long getCurrencyConversionLeniencyInMonths();
}
