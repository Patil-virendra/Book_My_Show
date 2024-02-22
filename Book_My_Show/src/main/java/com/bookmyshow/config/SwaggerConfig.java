//package com.bookmyshow.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig {
//
//    public static final String AUTHORIZATION_HEADER="Authorization";
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//    private List<SecurityContext> securityContexts(){
//        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
//    }
//    List<SecurityReference> securityReferences() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getInfo())
//                .securityContexts(securityContexts())
//                .securitySchemes(Arrays.asList(apiKey()))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    private ApiInfo getInfo() {
//        return new ApiInfo("Ticket_Booking_App","Description1","v1","TermsOfServiceUrl1",
//                new Contact("name1","website1","email1"),"licence1",
//                "licenceUrl1", Collections.emptyList());
//    }
//}
//
