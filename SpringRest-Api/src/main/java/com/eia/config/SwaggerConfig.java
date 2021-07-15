package com.eia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket getDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
						 .apiInfo(myApiInfo())
						 .select().apis(RequestHandlerSelectors.basePackage("com.eia.controller")) //designando los controllers que únicamente se mostrarán
						 .paths(PathSelectors.any()).build()
						 .useDefaultResponseMessages(false); //elimina los estados de respuesta que esten x defecto(x ej. 200 ok 404 not found)
						 
						 //.paths(PathSelectors.ant("/users/*")).build(); //pathselectors indica que se mostrarán aquellos microservicios con /users 
	}
	
	private ApiInfo myApiInfo() {
		
		return new ApiInfoBuilder().title("E&A api")
								   .version("1.0")
								   .license("Apache")
								   .contact(new Contact("E&A", "eia@hotmail.com", "www.eia.com"))
								   .build();
									   
								   
								   
				   
	}
}
