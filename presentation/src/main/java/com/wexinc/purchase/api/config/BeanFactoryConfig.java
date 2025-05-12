package com.wexinc.purchase.api.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;

/**
 * Class responsible for injecting dependencies into the application.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Configuration
class BeanFactoryConfig {

  /**
   * Method responsible for injecting dependencies into the application.
   *
   * @param beanRegistry {@link ApplicationContext} - Abstraction that provides configuration for
   *     the application.
   * @return {@link BeanFactoryPostProcessor} - Factory that allows customization for application.
   * @since 1.0.0
   */
  @Bean
  static BeanFactoryPostProcessor beanFactoryPostProcessor(final ApplicationContext beanRegistry) {
    return (final var beanFactory) -> {
      final var beanDefinitionScanner =
          new ClassPathBeanDefinitionScanner(
              (BeanDefinitionRegistry)
                  ((AnnotationConfigServletWebServerApplicationContext) beanRegistry)
                      .getBeanFactory());
      beanDefinitionScanner.addIncludeFilter(
          (final var mr, final var mrf) -> {
            final var className = mr.getClassMetadata().getClassName();
            return !className.contains("model")
                && !className.contains("resource")
                && !className.contains("dto")
                && !className.contains("shared")
                && !className.contains("domain")
                && !className.contains("Test");
          });

      beanDefinitionScanner.scan("com.wexinc.purchase.api");
    };
  }
}
