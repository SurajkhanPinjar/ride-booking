package com.uber.ridebooking.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customerOpenAPI(){
                return new OpenAPI().info(
                        new Info()
                                .title("Account Service API")
                                .version("1.0")
                                .description("Banking Account Microservices APIs")
                );
        }
}
