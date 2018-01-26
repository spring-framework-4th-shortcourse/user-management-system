package com.kshrd.springbootdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
 
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.kshrd.springbootdemo.rest"))
	      .paths(PathSelectors.ant("/api/**"))
	      .build()
	      .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "User Management REST API", 
	       "Some custom description of API.", 
	       "Version 1.0.0", 
	       "Terms of service", 
	       new Contact("Phearun Rath", "https://www.rathphearun.com", "rathphearun123@gmail.com"), 
	       "License of API", "https://www.rathphearun.com/license");
	}
	
}
