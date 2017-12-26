package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
import ${domainPackage}.${ClassName};
import com.szwcyq.d3p.common.tpl.mybatis.MybatisMapper;

import org.springframework.stereotype.Repository;

<#include "copyright.ftl"/>
@Repository
public interface ${ClassName}Mapper extends MybatisMapper<${pk.javaType!'String'},${ClassName}> {

}
