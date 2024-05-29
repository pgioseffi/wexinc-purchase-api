package com.wexinc.purchase.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Class responsible to expose a {@link RestClient} into a {@link Bean} to be shared and used by the application.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see RestClient
 * @see Bean
 */
@Configuration
class RestClientConfiguration {

	/**
	 * Method responsible to expose a {@link RestClient} into a {@link Bean} to be shared and used by the application.
	 *
	 * @return The exposed {@link RestClient} object as a {@link Bean} ready to be shared and used by the application.
	 * @since 1.0.0
	 */
	@Bean
	static RestClient restClient() {
		return RestClient.create();
	}
}
