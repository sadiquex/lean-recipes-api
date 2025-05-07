package com.saddik.leanRecipes.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Lean Recipes API")
                    .version("1.0.0")
                    .description("API for managing lean recipes and ingredients")
                    .contact(
                        Contact()
                            .name("Ibrahim Saddik")
                            .email("ibrahim@example.com")
                            .url("https://example.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("http://springdoc.org")
                    )
            )
    }
}
