package com.shree.springapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration
public class SwaggerConfig {
    // @Bean
    // public OpenAPI apiInfo() {
    //     return new OpenAPI()
    //             .info(new Info()
    //                     .title("TasteCraft API")
    //                     .description("API documentation for My Application")
    //                     .version("1.0"));
    // }
    @Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components().addParameters("Content-Type", 
            new Parameter().in("header").schema(new StringSchema()).example("application/json")));
}

}
