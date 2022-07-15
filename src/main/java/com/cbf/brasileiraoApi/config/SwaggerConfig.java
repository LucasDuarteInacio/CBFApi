package com.cbf.brasileiraoApi.config;



import com.cbf.brasileiraoApi.constants.OpenApiConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.core.env.Environment;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:version.properties")
public class SwaggerConfig {

    private final Environment env;

    @Bean
    public OpenAPI configApi(){
        return new OpenAPI()
                .info(openApiInfo());
    }

    private Info openApiInfo(){
        final String projectVersion = env.getProperty(OpenApiConstants.API_VERSION);
        return new Info()
                .title(OpenApiConstants.API_TITLE)
                .description(OpenApiConstants.API_DESCRIPTION)
                .version(projectVersion)
                .license(new License().name(OpenApiConstants.API_LICENSE));
    }


}
