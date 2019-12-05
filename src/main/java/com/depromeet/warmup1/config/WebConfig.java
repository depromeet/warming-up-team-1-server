package com.depromeet.warmup1.config;

import com.depromeet.warmup1.interceptor.AuthInterceptor;
import com.depromeet.warmup1.service.impl.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtFactory jwtFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(jwtFactory)).addPathPatterns("/api/**")
                .excludePathPatterns("/api/members/login");
    }
}
