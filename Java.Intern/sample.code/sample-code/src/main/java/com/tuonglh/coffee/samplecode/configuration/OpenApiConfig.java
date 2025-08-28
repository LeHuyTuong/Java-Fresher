package com.tuonglh.coffee.samplecode.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${open.api.title}") String title,
                                 @Value("${open.api.version}") String version ,
                                 @Value("${open.api.description}") String description,
                                 @Value("${open.api.serverUrl}") String serverUrl,
                                 @Value("${open.api.serverName}") String serverName) {
        return new OpenAPI().info(new Info().title(title)
                .version(version)
                .description(description)
                .license(new License().name("API License").url("http://domain.vn/license")))
                .servers(List.of(new Server().url(serverUrl).description(serverName)));

//                .components( // bắt thay token suốt ngày nên bỏ
//                        new Components()
//                                .addSecuritySchemes(
//                                        "bearerAuth",
//                                        new SecurityScheme()
//                                                .type(SecurityScheme.Type.HTTP)
//                                                .scheme("bearer")
//                                                .bearerFormat("JWT")
//                                )
//                ).security(List.of(new SecurityRequirement().addList("bearerAuth")));
        // tạo token thì khá là khó, nên người ta dùng cái này để mình nhập cái token vào authorize
        // request gửi kèm Beareer
    }

    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
                .group("api-service-1") // tên mặc định là API Doc
                .packagesToScan("com.tuonglh.coffee.samplecode.controller") // scan all bean controller in packages
                .build();
    }
}
