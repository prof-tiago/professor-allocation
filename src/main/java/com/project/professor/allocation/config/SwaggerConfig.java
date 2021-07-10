package com.project.professor.allocation.config;

import com.project.professor.allocation.ProfessorAllocationApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(ProfessorAllocationApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, Collections.emptyList())
                .globalResponses(HttpMethod.HEAD, Collections.emptyList())
                .globalResponses(HttpMethod.POST, Collections.emptyList())
                .globalResponses(HttpMethod.PUT, Collections.emptyList())
                .globalResponses(HttpMethod.PATCH, Collections.emptyList())
                .globalResponses(HttpMethod.DELETE, Collections.emptyList())
                .globalResponses(HttpMethod.OPTIONS, Collections.emptyList())
                .globalResponses(HttpMethod.TRACE, Collections.emptyList())
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Professor Allocation")
                .description("Professor Allocation Rest Server")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
