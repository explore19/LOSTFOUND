package com.lost_found.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.AuthorizationScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;


/**
        * @description: Swgger2配置
        * @author: ashe
        * @create: 2019-11-16 19:36
        **/


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.lost_found.controller"))
                .paths(PathSelectors.any())
                .build();
//                .securitySchemes(securitySchemes());
    }

//    private List<ApiKey> securitySchemes(){
//        List<ApiKey> apiKeyList = new ArrayList<ApiKey>();
//        apiKeyList.add(new ApiKey("token","token","header"));
//        return apiKeyList;
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("失物招领api文档")
                .description("失物招领系统的所有api接口以及详细信息")
                .version("1.0")
                .build();
    }

}
