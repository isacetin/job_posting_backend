package com.isacetin.myapplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.config.JHipsterProperties;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(JHipsterProperties jHipsterProperties) {
        return new OpenAPI()
            .info(
                new Info()
                    .title("İş İlanı API")
                    .version(jHipsterProperties.getApiDocs().getVersion())
                    .description("İş İlanı Uygulaması API Dokümantasyonu")
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            );
    }
}
