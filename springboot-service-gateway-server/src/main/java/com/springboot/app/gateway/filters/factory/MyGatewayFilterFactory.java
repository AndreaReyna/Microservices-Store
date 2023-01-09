package com.springboot.app.gateway.filters.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Configuracion>{

	private final Logger logger = LoggerFactory.getLogger(MyGatewayFilterFactory.class);
	
	
	public MyGatewayFilterFactory() {
		super(Configuracion.class);
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		return (exchange, chain) -> {
	
			logger.info("executing pre gateway filter factory: " + config.message);
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				Optional.ofNullable(config.cookieValue).ifPresent(cookie -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
				});
				
				logger.info("executing post gateway filter factory: " + config.message);
			}));
		};
	}
	

	@Override
	public String name() {
		return "ExampleCookie";
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("mensaje", "cookieName", "cookieValue");
	}

	public static class Configuracion {

		private String message;
		private String cookieValue;
		private String cookieName;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getCookieValue() {
			return cookieValue;
		}
		public void setCookieValue(String cookieValue) {
			this.cookieValue = cookieValue;
		}
		public String getCookieName() {
			return cookieName;
		}
		public void setCookieName(String cookieName) {
			this.cookieName = cookieName;
		}
		
	}

}
