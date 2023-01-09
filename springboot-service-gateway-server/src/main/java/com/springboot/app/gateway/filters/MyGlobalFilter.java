package com.springboot.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered{
	
	private final Logger logger = LoggerFactory.getLogger(MyGlobalFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("executing pre filter");
		exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			logger.info("executing post filter");
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(v -> {
				exchange.getResponse().getHeaders().add("token", v);
			});
			exchange.getResponse().getCookies().add("colour", ResponseCookie.from("colour", "red").build());
		}));
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
