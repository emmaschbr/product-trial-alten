package com.productrial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${server.port:8443}")
    private String serverPort;

    //TODO: faire marcher l'interface
    @Bean
    public OpenAPI api() {
        Server httpsServer = new Server();
        httpsServer.setUrl("https://localhost:" + serverPort);

        return new OpenAPI().info(new Info().title("PRODUCTRIAL -  REST API")
                .description("API Productrial")
                .version("v1.0.0"));
    }

}
