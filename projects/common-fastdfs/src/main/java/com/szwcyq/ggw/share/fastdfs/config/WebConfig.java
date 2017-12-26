/*package com.github.tobato.fastdfs.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@AutoConfigureOrder(-2147483638)
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextAware{
	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);
	@Value("${sso.origin:}")String origin="http://localhost:3000";
	@Bean
	@Autowired(required = false)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper mapper=new ObjectMapper();
		// 忽略json字符串中不识别的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//默认非空不输出
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);//不要引号
		//mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//单引号
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);//字段可以不带引号

		// 字段和值都加引号
		//mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 数字也加引号
		//mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		//mapper.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		// 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
		//objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
		//mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);//数字也返回字符串
		//objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//时间格式
		mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);//忽略 transient 字段
		if(builder!=null){
			builder.configure(mapper);
			return builder.createXmlMapper(false).build();
		}else {
			return mapper;
		}
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*//**");
	}*//*
	http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/rest*//**", "/api*//**", "*//**")
	.and().csrf().disable();*//*

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/html*//**").addResourceLocations("classpath:/html/");
		super.addResourceHandlers(registry);
	}*//*

	@Bean
	public FilterRegistrationBean regSpringSessionRepositoryFilter(){
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(new DelegatingFilterProxy());
		registrationBean.setUrlPatterns(Lists.newArrayList(""));
		//registrationBean.setOrder(1);
		return registrationBean;
	}
	@Bean
	@ConditionalOnProperty(name = "sso.debug",havingValue = "true")
	public FilterRegistrationBean corsFilterRegBean(){
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(new Filter(){
			public void init(FilterConfig filterConfig) throws ServletException{}
			public void destroy(){}

			public void doFilter(ServletRequest request, ServletResponse rsp, FilterChain chain) throws IOException, ServletException{
				log.debug("{}",request.getLocalAddr());
				if(StringUtils.isNotBlank(origin)){
					HttpServletResponse response=(HttpServletResponse)rsp;
					//response.setHeader("Access-Control-Allow-Origin", "*");
					response.setHeader("Access-Control-Allow-Credentials", "true");//request.getHeader("Origin")
					response.setHeader("Access-Control-Allow-Origin", origin);//request.getHeader("Origin")
					log.info(response.getHeader("Access-Control-Allow-Origin")+"--------Access-Control-Allow-Origin--------------"+origin);
				}
				chain.doFilter(request,rsp);
			}

		});
		registrationBean.setUrlPatterns(Lists.newArrayList("/*"));
		registrationBean.setOrder(1);
		//registrationBean.setOrder(1);//在 PermissionFilter 之前
		return registrationBean;
	}
	@Override
	public void setServletContext(ServletContext application){
		application.setAttribute("_path", application.getContextPath());
		//application.setAttribute("base", application.getContextPath());
		try {
			application.setAttribute("_staticPath", "http://7xp08d.com1.z0.glb.clouddn.com/smart-static");
			application.setAttribute("_systemName", ConfigUtils.getProperty("system.name"));
			application.setAttribute("_systemAdminName", ConfigUtils.getProperty("system.admin.name"));
			application.setAttribute("_companyName", ConfigUtils.getProperty("system.company.name"));
			application.setAttribute("_companyPhone", ConfigUtils.getProperty("system.company.phone"));
			application.setAttribute("_copyRight", ConfigUtils.getProperty("system.copy.right"));
		}
		catch (Exception e) {
			log.error("系统初始化参数配置有误", e);
		}
	}


	@Bean
	@ConditionalOnMissingBean(name="requestContextListener")
	public ServletListenerRegistrationBean<RequestContextListener> requestContextListener(){
		<listener>
		<listener-class>org.springframework.web.context.request.RequestContextListener</listener-class>
		</listener>
		ServletListenerRegistrationBean<RequestContextListener> registrationBean=new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new RequestContextListener());
		//		registrationBean.setOrder(1);
		return registrationBean;
	}
		@Bean
	public FilterRegistrationBean getDemoFilter(){
		DemoFilter demoFilter=new DemoFilter();
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(demoFilter);
		List<String> urlPatterns=new ArrayList<String>();
		urlPatterns.add("");//拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	@Bean
	public ServletRegistrationBean getDemoServlet(){
		DemoServlet demoServlet=new DemoServlet();
		ServletRegistrationBean registrationBean=new ServletRegistrationBean();
		registrationBean.setServlet(demoServlet);
		List<String> urlMappings=new ArrayList<String>();
		urlMappings.add("/demoservlet");////访问，可以添加多个
		registrationBean.setUrlMappings(urlMappings);
		registrationBean.setLoadOnStartup(1);
		return registrationBean;
	}
	@Bean
	public ServletListenerRegistrationBean<EventListener> getDemoListener(){
		ServletListenerRegistrationBean<EventListener> registrationBean
				=new ServletListenerRegistrationBean<>();
		registrationBean.setListener(new DemoListener());
		//		registrationBean.setOrder(1);
		return registrationBean;
	}
}
*/