
package com.zuul.swagger;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
@Configuration
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public DocumentationConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("default", "/v2/api-docs","2.0"));
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
        });
        return resources;
    }

//    @Override
//        public List<SwaggerResource> get() {
//            List resources = new ArrayList<>();
//            resources.add(swaggerResource("外接设备系统", "/management-device/v2/api-docs", "1.0"));
//            resources.add(swaggerResource("设备管理系统", "/management-equip/v2/api-docs", "1.0"));
//            return resources;
//        }




    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
