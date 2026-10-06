package com.medbill.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI medBillOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MedBill Pro API")
                        .description("Hospital invoice and revenue management platform")
                        .version("1.0.0"));
    }
}
