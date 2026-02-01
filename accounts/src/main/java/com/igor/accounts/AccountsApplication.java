package com.igor.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
    info =
    @Info(
        title = " Microservice API Documentation",
        version = "1.0",
        description = "API documentation for Igor Microservice Application",
        contact =
        @Contact(
            name = "Igor Support",
            email = "i@b.c",
            url = "http://igor.microservices.com"),
        license =
        @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    externalDocs =
    @ExternalDocumentation(
        description = "Igor Microservices Wiki Documentation",
        url = "http://igor.microservices.com/wiki"))
@ComponentScan(basePackages = {"com.igor.accounts", "com.igor.common.audit", "com.igor.common.exception"})
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }
}
