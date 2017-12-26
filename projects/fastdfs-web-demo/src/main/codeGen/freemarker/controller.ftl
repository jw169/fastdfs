package ${package};

<#assign ClassName=StringUtil.firstToUpper(domainName)>
<#assign className=StringUtil.firstToLower(domainName)>
import ${domainPackage}.${ClassName};
import ${servicePackage}.${ClassName}Service;


import javax.servlet.http.HttpServletRequest;

import com.szwcyq.d3p.common.base.JsonResponse;
import com.szwcyq.d3p.common.base.Parameter;
import com.szwcyq.d3p.common.base.Page;

import com.szwcyq.d3p.common.util.RequestUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

<#include "copyright.ftl"/>
@RestController
@RequestMapping("/${className}")
public class ${ClassName}Controller{
	private static final Logger log = LoggerFactory.getLogger(${ClassName}Controller.class);
	@Autowired ${ClassName}Service service;

	@ApiOperation(value="新建", notes="新建:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", required = true, dataType = "${table.pk.javaType!'String'}", paramType = "form"),
		@ApiImplicitParam(name = "name", required = false, dataType = "String", paramType = "form"),
	})
	@RequestMapping(value="/create")
	public String create(HttpServletRequest request){
		int type=RequestUtil.getInt(request, "type", -1);
		Parameter params=Parameter.newHashMap();
		params.put("name",RequestUtil.getString(request,"name",null));
		params.put("type",type);
		//todo 自己做验证
		if(type<1){
			return JsonResponse.fail("无效的type:"+type);
		}
		//todo 注释掉,下面是偷懒的方法
		//params=RequestUtil.toParameter(request,"name","type");
		try{
			//todo
			service.create(params);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("create ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}

	@ApiOperation(value="get", notes="获取:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", required = true, dataType = "${table.pk.javaType!'String'}", paramType = "query"),
	})
	@RequestMapping(value = "/get")
	public JsonResponse get(HttpServletRequest request){
		JsonResponse response=new JsonResponse();
		<#if table.pk.javaType =='Long'>
		long id=RequestUtil.get(request, "id", 0L);
		if(id<1)return response.fail().message("id必须大于0");
		<#else>
		String id=RequestUtil.getString(request, "id", null);
		if(StringUtils.isBlank(id))return response.fail().message("id不能为空");
		</#if>

		Object obj=service.get(id);
		if(obj==null){
			return response.fail().message("获取失败");
		}else{
			return response.ok().entity(obj);
		}
	}

	@ApiOperation(value="更新", notes="更新:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", required = true, dataType = "${table.pk.javaType!'String'}", paramType = "query"),
	})
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request){
		<#if table.pk.javaType =='Long'>
		long id=RequestUtil.get(request, "id", 0L);
		if(id<1)return JsonResponse.fail("id必须大于0");
		<#else>
		String id=RequestUtil.getString(request, "id", null);
		if(StringUtils.isBlank(id))return JsonResponse.fail("id不能为空");
		</#if>

		Parameter params=Parameter.newHashMap();
		params.add("id",id);
		params.put("name",RequestUtil.getString(request,"name",null));
		params.put("type",RequestUtil.getInt(request,"type",null));
		//todo 自己做验证

		//todo 注释掉,下面是偷懒的方法
		//params=RequestUtil.toParameter(request,"id","name","type");

		try{
			service.update(1,params);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("update ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}
	@ApiOperation(value="删除", notes="删除:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", required = true, dataType = "${table.pk.javaType!'String'}", paramType = "query"),
	})
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request){
		<#if table.pk.javaType =='Long'>
		long id=RequestUtil.get(request, "id", 0L);
		if(id<1)return JsonResponse.fail("id必须大于0");
		<#else>
		String id=RequestUtil.getString(request, "id", null);
		if(StringUtils.isBlank(id))return JsonResponse.fail("id不能为空");
		</#if>

		Parameter params=Parameter.newHashMap();
		params.add("id",id);
		params.put("name",RequestUtil.getString(request,"name",null));
		params.put("type",RequestUtil.getInt(request,"type",null));
		//todo 自己做验证

		//todo 注释掉,下面是偷懒的方法
		//params=RequestUtil.toParameter(request,"id","name","type");
		try{
			service.update(1,params);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("delete ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}
	@ApiOperation(value="查询列表", notes="查询列表:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "totalCount",value = "上次相同查询条件的总数", required = false, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageNo",value = "当前页", required = false, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "pageSize",value = "每页数量", required = false, dataType = "int", paramType = "query")
	})
	@RequestMapping(value = "/list")
	public JsonResponse list(HttpServletRequest request){
		JsonResponse response=new JsonResponse();

		Page<${ClassName}> page=new Page<>();
		page.setTotalCount(RequestUtil.get(request, "totalCount", 0L));
		page.setPageNo(RequestUtil.get(request, "pageNo", 1L));
		page.setPageSize(RequestUtil.get(request, "pageSize", 20L));// default 20

		//todo 自己加参数
		page.setFilter(RequestUtil.toParameter(request,"id","name","type"));
		try{
			service.page( page);
		}catch(Exception e){
			RequestUtil.debugParam(request);
			log.error("list ${className} error",e);
		}

		page.getFilter().clear();
		response.ok().page(page);

		return response;
	}

	@ApiOperation(value="切换状态", notes="切换状态:${table.name!} ${table.comment!}",httpMethod ="POST")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", required = true, dataType = "${table.pk.javaType!'String'}", paramType = "query"),
	})
	@RequestMapping(value = "/toggle")
	public String toggle(HttpServletRequest request){
		<#if table.pk.javaType =='Long'>
		long id=RequestUtil.get(request, "id", 0L);
		if(id<1)return JsonResponse.fail("id必须大于0");
		<#else>
		String id=RequestUtil.getString(request, "id", null);
		if(StringUtils.isBlank(id))return JsonResponse.fail("id不能为空");
		</#if>

		try{
			service.toggle( id);
			return JsonResponse.STRING_OK;
		}catch(Exception e){
			log.error("toggle ${className} error",e);
			return JsonResponse.STRING_FAIL;
		}
	}
}