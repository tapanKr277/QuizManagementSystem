package com.gyanpath.apigateway.security;

import com.netflix.spectator.impl.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

//@Component
//public class AuthenticationPreFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {


//    @Autowired
//    private RouterValidator routerValidator;
//
//    public AuthenticationPreFilter(){
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(AuthenticationFilter.Config config) {
//        return ((exchange, chain)->{
//            if(routerValidator.isSecured.test(exchange.getRequest())){
//                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
//                    throw new RuntimeException("authrization not found");
//                }
//                String authHeader = exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION).get(0);
//                if(authHeader!=null && authHeader.startsWith("Bearer ")){
//                    authHeader = authHeader.substring(7);
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
//
//    public static class config{
//
//    }
//}
