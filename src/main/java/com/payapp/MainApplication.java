package com.payapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class MainApplication{

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}


	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("PayApp")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("PayApp")
				.description("An app to reduce the hustle of paying fees")
				.termsOfServiceUrl("http://cannotcontainallthisawesomeness.com")
				.contact("danagbemava@gmail.com")
				.license("Apache License Version 7.0")
				.licenseUrl("http://cannotcontainallthisawesomeness.com/LICENSE")
				.version("7.0")
				.build();

	}
}
