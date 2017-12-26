/*package com.github.tobato.fastdfs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;


//mybatis的mapper
@MapperScan(basePackages = {"com.szwcyq.ggw.share.platform.dao"})
//以下不用改
@Configuration
@ComponentScan(value={"com.szwcyq.d3p.common.spring"},
		excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION,value=Configuration.class)})
@Import({ValidatorConfig.class,SpringWebConfig.class, DruidDataSourceConfig.class, MybatisConfig.class})
//@ImportAutoConfiguration
public class ImportConfig{

}*/