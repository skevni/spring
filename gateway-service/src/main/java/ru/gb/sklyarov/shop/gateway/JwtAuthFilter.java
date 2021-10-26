package ru.gb.sklyarov.shop.gateway;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthFilter() {
        super(Config.class);
    }

    public class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!isAuthMissing(request)){
                final String token = getAuthHeader(request);
                if (jwtTokenUtil.isInvalid(token)){
                    return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                }
                fillRequestWithHeaders(exchange, token);
            }
            return chain.filter(exchange);
        };

    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Authorization")){
            return true;
        }

        return !request.getHeaders().getOrEmpty("Authorization").get(0).startsWith("Bearer ");
    }

    private void fillRequestWithHeaders(ServerWebExchange exchange, String token){
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("username", claims.getSubject())
                .build();
    }

    private String getAuthHeader(ServerHttpRequest request){
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

}
