package com.atguigu.cpringcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        return routes
                    .route("route1_id", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                    .route("route2_id", r -> r.path("/guoji").uri("http://news.baidu.com/guoji"))
                    .build();

    }

}
