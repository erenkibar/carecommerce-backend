package com.eren.carecommerce;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CarEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarEcommerceApplication.class, args);
    }

    @Bean
    public OpenAPI carEcommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Car E-commerce API")
                        .description("API for the car e-commerce application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }
}
