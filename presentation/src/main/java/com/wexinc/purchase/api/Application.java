package com.wexinc.purchase.api;

import java.io.Closeable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Class responsible to start/stop the application.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @see SpringBootApplication
 * @see EnableDiscoveryClient
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Application implements Closeable {

	/**
	 * Field that contains the application context abstraction in order to start and shut it down.
	 *
	 * @since 1.0.0
	 * @see ConfigurableApplicationContext
	 */
	private static ConfigurableApplicationContext run;

	/**
	 * Method responsible to start the application.
	 *
	 * @param args Mandatory {@link String} array by the Java specification.
	 * @since 1.0.0
	 */
	public static void main(final String[] args) {
		Application.run = SpringApplication.run(Application.class, args);
	}

	/**
	 * Method responsible to shut the application down.
	 *
	 * @since 1.0.0
	 */
	@Override
	public void close() {
		Application.run.close();
	}
}
