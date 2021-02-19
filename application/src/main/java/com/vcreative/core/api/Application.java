package com.vcreative.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Application.
 */

@SpringBootApplication
@RestController
@PropertySource(value = { "classpath:git.properties" }, ignoreResourceNotFound = true)
public class Application {

    /**
     * Main method.
     *
     * @param args - arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
