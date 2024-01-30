package com.example.groundguardians.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Ground Guardians API Document")
                .description("One Peace 팀의 플랫폼 Ground Guardians의 API 명세서입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
