package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign className=StringUtil.firstToLower(domainName)>
import ${domainPackage}.${ClassName};
import com.szwcyq.d3p.common.tpl.service.BaseService;

<#include "copyright.ftl"/>
public interface ${ClassName}Service extends BaseService<${pk.javaType!'String'},${ClassName}> {

}