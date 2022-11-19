package com.mpay;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	private ApiInfo apiInfo() {
		return new ApiInfo("M-Pay API", "REST APIs for Online Payment Wallet Application. " + "\n"
				+ "The API will return the response status code, response headers, and a response body.",
				"1.0",
				"Terms of service",
				null, "License of API",
				"API license URL", Collections.emptyList());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
}
