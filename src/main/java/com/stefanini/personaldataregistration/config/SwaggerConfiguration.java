package com.stefanini.personaldataregistration.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket swaggerPersonApi10() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("person-api-1.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("pl.piomin.services.versioning.controller"))
	            .paths(regex("/person/v1.0.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("1.0").title("Person API").description("Documentation Person API v1.0").build());
	}

	@Bean
	public Docket swaggerPersonApi11() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("person-api-2.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("pl.piomin.services.versioning.controller"))
	            .paths(regex("/person/v2.0*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("2.0").title("Person API").description("Documentation Person API v2.0").build());
	}
	

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

}
