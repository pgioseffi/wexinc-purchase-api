package com.wexinc.purchase.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Class responsible to expose a {@link RestTemplate} into a {@link Bean} to be shared and used by the application.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see RestTemplate
 * @see Bean
 */
@Configuration
class RestTemplateConfiguration {

	/**
	 * Method responsible to expose a {@link RestTemplate} into a {@link Bean} to be shared and used by the application.
	 *
	 * @return The exposed {@link RestTemplate} object as a {@link Bean} ready to be shared and used by the application.
	 * @since 1.0.0
	 */
	@Bean
	static RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
