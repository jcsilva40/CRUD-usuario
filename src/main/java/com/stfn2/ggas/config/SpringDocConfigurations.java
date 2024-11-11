package com.stfn2.ggas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                    .info(new Info()
                        .title("GGAS V2")
                        .version("v2")
                        .description("API do Sistema de Gestão Comercial de Serviços de Distribuição de Gás.")
                       );
    }
}
