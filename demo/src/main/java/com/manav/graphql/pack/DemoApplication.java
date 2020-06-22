package com.manav.graphql.pack;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SPRING_WEB)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())     
          //.paths(PathSelectors.any())
          .paths(PathSelectors.ant("/api/*"))
//          .apis(RequestHandlerSelectors.basePackage("com.manav.graphql.pack.controller"))
          .build()
          .apiInfo(getApiInfo());                                           
    }

	private ApiInfo getApiInfo()
	{
		return new ApiInfo("Intregated Release Tool API's",
				"it is used to create modify and delete the images",
				"IRT 2.0",
				"it is in continuatione of 2010",
				new springfox.documentation.service.Contact("IRT TEAM", "http://irt.cisco.com", "irt@cisco.com"),
				"Api license"
				,"empty list");
	}

}

