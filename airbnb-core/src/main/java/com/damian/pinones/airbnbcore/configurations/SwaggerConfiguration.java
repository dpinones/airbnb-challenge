package com.damian.pinones.airbnbcore.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
			    .select()
			    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			    .paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfo("Airbnb API", "Microservice", "1.0", "",
						new Contact("Damian Piniones", "", "pinonesdamian@gmail.com"),
						"", "", Collections.emptyList()));

	}

}
