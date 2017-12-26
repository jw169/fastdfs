/*package com.szwcyq.ggw.share.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class Swagger2Config{

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				//.apis(RequestHandlerSelectors.basePackage("com.ggw.shzwy.console.web"))
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build();

		.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder()
										.code(500)
										.message("500 message")
										.responseModel(new ModelRef("Error"))
										.build(),
								new ResponseMessageBuilder()
										.code(403)
										.message("Forbidden!")
										.build()))
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("顺德新港 APIs")
				//.description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
				//.termsOfServiceUrl("http://blog.didispace.com/")
				//.contact("程序猿DD")
				.version("0.1")
				.build();
	}

}*/