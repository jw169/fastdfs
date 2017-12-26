package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign domainExcludes = 'id,parentId,name,type,status,readonly,description,createTime,updateTime,version'>
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.Serializable;
import com.szwcyq.d3p.common.base.BaseEntity;

<#include "copyright.ftl"/>
public class ${ClassName} extends BaseEntity<${pk.javaType!'String'}> implements Serializable {
	<#list fieldList as field><#if StringUtil.notInAny(field.name,domainExcludes)>
	<#if StringUtil.isNotBlank(field.comment)>/** ${field.comment} */</#if>private ${field.javaType} ${field.camel};
	</#if></#list>

	<#list fieldList as field><#if StringUtil.notInAny(field.name,domainExcludes)><#assign upperFieldName = StringUtil.firstToUpper(field.camel)>
<#if StringUtil.isNotBlank(field.comment)>	/** ${field.comment} */</#if>
	public ${field.javaType} get${upperFieldName}() {
		return ${field.camel};
	}
<#if StringUtil.isNotBlank(field.comment)>	/** ${field.comment} */</#if>
	public void set${upperFieldName}(${field.javaType} ${field.camel}) {
		this.${field.camel} = ${field.camel};
	}
	</#if></#list>

	public Map<String,Object> toMap(){
		Map<String,Object> map=new LinkedHashMap<String, Object>();
		<#list fieldList as field>
		map.put("${field.name}",${field.camel});<#if StringUtils.isNotBlank(field.comment)>//${field.comment}</#if>
		</#list>
		return map;
	}
}
