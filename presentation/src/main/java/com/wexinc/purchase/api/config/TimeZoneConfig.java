package com.wexinc.purchase.api.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible to set the application default timezone to Los Angeles, which is the Wex Inc timezone, in order to
 * handle date and time correctly when currency exchange rate conversion is needed.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Configuration
public class TimeZoneConfig {

	/**
	 * Method responsible to set the application default timezone to Los Angeles, which is the Wex Inc timezone, in
	 * order to handle date and time correctly when currency exchange rate conversion is needed.
	 *
	 * @return The {@link TimeZone} object set to Los Angeles.
	 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
	 * @since 1.0.0
	 */
	@Bean
	static TimeZone timeZone() {
		return TimeZone.getTimeZone("America/Los_Angeles");
	}
}
