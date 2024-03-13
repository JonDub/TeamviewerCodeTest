package com.teamviewer.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfiguration {

    @Bean
    public OpenAPI restApi() {
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Teamviewer")
                .description("Teamviewer Swagger UI")
                .contact(getContact())
                .version("1.0.0");
    }

    private Contact getContact() {
        return new io.swagger.v3.oas.models.info.Contact()
                .name("Teamviewer HR")
                .email("teamviewer.hr@teamviewer.com")
                .url("https://www.teamviewer.com/en/");
    }
}
