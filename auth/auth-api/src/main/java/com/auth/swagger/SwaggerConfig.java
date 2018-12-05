package com.auth.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by zhiwen on 2017/8/4.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private Predicate<String> postPaths() {
        return regex("/*");
    }

    private Predicate<String> springBootActuatorJmxPaths() {
        return regex("^/(?!env|restart|pause|resume|refresh).*$");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("app").description("app").build();
    }
    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("client_2", "123456", "realm",
                "auth-server", "access_token", ApiKeyVehicle.HEADER, "access_token", ",");
    }

}