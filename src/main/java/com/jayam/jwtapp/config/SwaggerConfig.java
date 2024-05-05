package com.jayam.jwtapp.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {     
	
	private static final String BEARER_AUTH = "Bearer";
	private static final String JWT = "JWT";
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.jayam.jwtapp.web")).paths(PathSelectors.any()).build().apiInfo(apiInfo())
					.securitySchemes(securitySchemes()).securityContexts(Arrays.asList(securityContext()));
		}

	private List<SecurityScheme> securitySchemes() {
		return Arrays.asList( new ApiKey(BEARER_AUTH, "Authorization", "header"));
	}
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(bearerAuthReference())).build();
	}

	private SecurityReference bearerAuthReference() {
		return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
	}


	
	private ApiInfo apiInfo(){
        return new ApiInfo(
                "Program in java APIs",
                "APIs reference for developers",
                "1.0",
                "Terms of service",
                new Contact("Satya", "", "sprinboot@java.com"),
                "Free License",
                "API license URL",
                Collections.emptyList()
        );
    }
}