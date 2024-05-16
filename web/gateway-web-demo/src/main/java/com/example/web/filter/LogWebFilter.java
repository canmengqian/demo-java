package com.example.web.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.*;
import reactor.core.publisher.Mono;

import javax.annotation.sql.DataSourceDefinition;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className LogWebFilter
 * @description TODO
 * @date 2023/12/13
 */
@Slf4j
@Configuration
public class LogWebFilter implements WebFilter, WebExceptionHandler {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        log.info(LogWebFilter.class.getName());
        return chain.filter(exchange);
    }
    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
                Map<String, Object> rs= new LinkedHashMap<>();//super.getErrorAttributes(request, options);
                //rs.clear();
                rs.put("code","9999");
                rs.put("status",404);
                if((int) rs.get("status")==404){
                    throw  new ResponseStatusException(HttpStatus.BAD_GATEWAY);
                }
                return rs;
            }
        };
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return Mono.error(ex);
    }
}
