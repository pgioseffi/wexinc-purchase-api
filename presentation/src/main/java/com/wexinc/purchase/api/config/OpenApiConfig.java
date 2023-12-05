package com.wexinc.purchase.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Class responsible for holding project information used by Swagger/OpenAPI.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Configuration
class OpenApiConfig {

	/**
	 * Method responsible for holding some of the application information used by Swagger/OpenAPI.
	 *
	 * @return {@link OpenAPI} object containing some of the application's documentation information.
	 *
	 * @since 1.0.0
	 */
	@Bean
	OpenAPI create() {
		return new OpenAPI().info(new Info().title("WEX Inc API - Presentation Module - Recruitment Test")
				.description(
						"""
								<p>
									<h3>Introduction</h3>
									Wex Inc Purchase API - Presentation module containing API entry endpoints - Recruitment Test.
								</p>""")
				.version("1.0.0").contact(new Contact().name("Philippe Gioseffi").email("pgioseffi@gmail.com")));
	}
}
