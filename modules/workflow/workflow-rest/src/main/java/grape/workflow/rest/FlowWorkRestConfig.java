package grape.workflow.rest;

import grape.workflow.rest.flowable.ui.modeler.AppDispatcherServletConfiguration;
import grape.workflow.rest.flowable.ui.modeler.ApplicationConfiguration;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 将配置单独提出来，以实现被依赖时自动配置
 * Created by yangwei
 * Created at 2019/7/25 9:04
 */
/* 配置 ui modeler */
@Import({
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class
})
@Configuration
@ComponentScan
@EnableSwagger2
public class FlowWorkRestConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //选择controller包
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //自定义信息可按需求填写
                .title("Grape Swagger 接口文档")
                .build();
    }
}
