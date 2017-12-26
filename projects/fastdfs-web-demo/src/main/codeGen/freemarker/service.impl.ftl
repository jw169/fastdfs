package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign className=StringUtil.firstToLower(domainName)>
import ${domainPackage}.${ClassName};
import ${javaMapperPackage}.${ClassName}Mapper;
import ${servicePackage}.${ClassName}Service;
import com.szwcyq.d3p.common.tpl.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

<#include "copyright.ftl"/>
@Service("${className}Service")
public class ${ClassName}ServiceImpl extends BaseServiceImpl<${pk.javaType!'String'},${ClassName}> implements ${ClassName}Service{

	@Autowired ${ClassName}Mapper ${className}Mapper;

	public ${ClassName}Mapper getMapper(){
		return ${className}Mapper;
	}


}