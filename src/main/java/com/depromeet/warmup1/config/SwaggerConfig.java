package com.depromeet.warmup1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		// Authentication header 처리를 위해 사용
				List global = new ArrayList();
				global.add(new ParameterBuilder().name("Bearer").description("jwt token").parameterType("header")
						.required(false).modelRef(new ModelRef("string")).build());

				return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(global).select()
						.apis(RequestHandlerSelectors.any()) 
						.paths(PathSelectors.ant("/**")).build(); 
	}
}
