package com.depromeet.warmup1.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Verification;
import com.depromeet.warmup1.interceptor.AuthInterceptor;
import com.depromeet.warmup1.service.impl.JwtFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:jwtSetting.properties")
public class JwtConfig implements WebMvcConfigurer {

    @Value("${jwt.tokenIssuer}")
    private String tokenIssuer;

    @Value("${jwt.tokenSigningKey}")
    private String tokenSigningKey;

    @Value("${jwt.tokenExpirationTime}")
    private Integer tokenExpirationTime;

    @Value("${jwt.refreshTokenExpTime}")
    private Integer refreshTokenExpTime;


    @Bean
    public JWTVerifier jwtVerifier() {
        Verification verification = JWT.require(Algorithm.HMAC256(tokenSigningKey));
        return verification.build();
    }

    @Bean
    public JwtSettings jwtSettings() {
        return JwtSettings.of(tokenIssuer, tokenSigningKey, tokenExpirationTime, refreshTokenExpTime);
    }

    @Bean
    public JwtFactory jwtFactory() {
        return new JwtFactory(jwtVerifier(), jwtSettings());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtFactory()))
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/members/login");

    }


    @Getter
    public static class JwtSettings {
        private final String tokenIssuer;
        private final String tokenSigningKey;
        private final Integer tokenExpirationTime;
        private final Integer refreshTokenExpTime;

        private JwtSettings(String tokenIssuer,
                            String tokenSigningKey,
                            Integer tokenExpirationTime,
                            Integer refreshTokenExpTime) {
            this.tokenIssuer = tokenIssuer;
            this.tokenSigningKey = tokenSigningKey;
            this.tokenExpirationTime = tokenExpirationTime;
            this.refreshTokenExpTime = refreshTokenExpTime;
        }

        public static JwtSettings of(String tokenIssuer,
                                     String tokenSigningKey,
                                     Integer tokenExpirationTime,
                                     Integer refreshTokenExpTime) {
            return new JwtSettings(tokenIssuer, tokenSigningKey, tokenExpirationTime, refreshTokenExpTime);
        }
    }
}
