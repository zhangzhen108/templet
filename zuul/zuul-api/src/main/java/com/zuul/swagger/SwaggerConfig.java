package com.zuul.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by zhiwen on 2017/8/4.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${info.app.name}")
    private String serviceName;
    @Value("${info.app.desc}")
    private String serviceDesc;
    @Value("${security.oauth2.client.client-id}")
    String clientId;
    @Value("${security.oauth2.client.client-secret}")
    String clientSecret;
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("com.zuul")))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(oauth()))
                .securityContexts(Lists.newArrayList(securityContext()))
                ;
    }

    private Predicate<String> postPaths() {
        return regex("/*");
    }

    private Predicate<String> springBootActuatorJmxPaths() {
        return regex("^/(?!env|restart|pause|resume|refresh).*$");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(serviceName).description(serviceDesc).build();
    }

    @Bean
    List<GrantType> grantTypes() {
        String tokenUrl = "uaa/oauth/token", authorizeUrl = "uaa/oauth/authorize", loginUrl = "/login";
        List<GrantType> grantTypes = new ArrayList<>();
        ClientCredentialsGrant clientCredentialsGrant = new ClientCredentialsGrant(tokenUrl);
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant =
                new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
        AuthorizationCodeGrant authorizationCodeGrant = new AuthorizationCodeGrant(new TokenRequestEndpoint(authorizeUrl
                , "clientId", "clientSecret"), new TokenEndpoint(tokenUrl, "access_token"));
        ImplicitGrant implicitGrant = new ImplicitGrant(new LoginEndpoint(tokenUrl), "access_token");
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        grantTypes.add(implicitGrant);
        grantTypes.add(authorizationCodeGrant);
        grantTypes.add(clientCredentialsGrant);
        return grantTypes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/**"))//配置哪些url需要做oauth2认证
                .build();
    }

    List<SecurityReference> defaultAuth() {
        return Lists.newArrayList(
                new SecurityReference("oauth2", scopes().toArray(new AuthorizationScope[0])));
    }

    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("oauth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }

    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> list = new ArrayList();
        list.add(new AuthorizationScope("read", "Grants read access"));
        list.add(new AuthorizationScope("write", "Grants write access"));
        return list;
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId, clientSecret, "realm",
                "auth-server", "access_token", ApiKeyVehicle.HEADER, "access_token", ",");
    }

}