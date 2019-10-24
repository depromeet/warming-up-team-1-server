package com.depromeet.warmup1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import com.depromeet.warmup1.interceptor.AuthInterceptor;
import com.depromeet.warmup1.service.impl.JwtFactory;

import lombok.Getter;

@Configuration
@PropertySource("classpath:jwtSetting.properties")
public class JwtConfig implements WebMvcConfigurer {

	@Value("${jwt.tokenIssuer}")
	private String tokenIssuer;

	@Value("${jwt.tokenSigningKey}")
	private String tokenSigningKey;

	@Value("${jwt.tokenExpirationTime}")
	private Integer tokenExpirationTime;

	@Bean
	public JWTVerifier jwtVerifier() {
		Verification verification = JWT.require(Algorithm.HMAC256(tokenSigningKey));
		return verification.build();
	}

	@Bean
	public JwtSettings jwtSettings() {
		return JwtSettings.of(tokenIssuer, tokenSigningKey, tokenExpirationTime);
	}

	@Bean
	public JwtFactory jwtFactory() {
		return new JwtFactory(jwtVerifier(), jwtSettings());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor(jwtFactory())).addPathPatterns("/api/**")
				.excludePathPatterns("/api/members/login");
	}

	@Getter
	public static class JwtSettings {
		private final String tokenIssuer;
		private final String tokenSigningKey;
		private final Integer tokenExpirationTime;

		private JwtSettings(String tokenIssuer, String tokenSigningKey, Integer tokenExpirationTime) {
			this.tokenIssuer = tokenIssuer;
			this.tokenSigningKey = tokenSigningKey;
			this.tokenExpirationTime = tokenExpirationTime;
		}

		public static JwtSettings of(String tokenIssuer, String tokenSigningKey, Integer tokenExpirationTime) {
			return new JwtSettings(tokenIssuer, tokenSigningKey, tokenExpirationTime);
		}
	}
}
