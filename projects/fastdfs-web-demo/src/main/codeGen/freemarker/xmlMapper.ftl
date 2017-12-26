<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#assign ClassName=StringUtil.firstToUpper(domainName)>
<mapper namespace="${javaMapperPackage}.${ClassName}Mapper" >
	<resultMap id="RM_${ClassName}" type="${domainPackage}.${ClassName}" >
	<#list fieldList as field>
		<#if (field.pk)><id column="${field.name}" property="${field.name}" jdbcType="${field.jdbcType}" />
		<#else><result column="${field.name}" property="${field.camel}" jdbcType="${field.jdbcType}" /></#if>
	</#list>
	</resultMap>

	<insert id="create">
		insert into ${tableName} (<#list fieldList as field>${field.name}${field_has_next?string(",","")}</#list>)
		values (<#list fieldList as field>${r"#{"+field.camel+",jdbcType="+field.jdbcType+"}"} ${field_has_next?string(",","")}</#list>)
	</insert>

	<select id="get" resultMap="RM_${ClassName}">
		select * from ${tableName} where id = ${r"#{_parameter}"}
	</select>

	<select id="list" resultMap="RM_${ClassName}" >
		select * from ${tableName}
		<where>
		<#list fieldList as field>
			<if test="${field.camel}!=null">and ${field.name}=${r"#{"+field.camel+",jdbcType="+field.jdbcType+"}"}</if>
		</#list>
		</where>
		<choose>
			<when test="pageable==0"></when>
			<when test="pageSize!=null and offset!=null">limit ${r"#{offset},#{pageSize}"}</when>
			<otherwise>limit 0,20</otherwise>
		</choose>
	</select>
	<select id="count" resultType="long">
		select count(*) from ${tableName}
		<where>
		<#list fieldList as field>
			<if test="${field.camel}!=null">and ${field.name}=${r"#{"+field.camel+",jdbcType="+field.jdbcType+"}"}</if>
		</#list>
		</where>
	</select>

	<update id="toggle">
		update ${tableName} set status=IF(status=1,0,1),updateTime=sysdate() where id = ${r"#{_parameter}"}
	</update>

	<delete id="delete" parameterType="java.lang.Long" >
		update ${tableName} set status=0,updateTime=sysdate() where id = ${r"#{_parameter}"}
	</delete>

	<update id="update">
		update ${tableName}
		<set>
		<#list fieldList as field>
			<if test="${field.camel}!=null">${field.name}=${r"#{"+field.camel+",jdbcType="+field.jdbcType+"}"}, </if>
		</#list>
		</set>
		where id = ${r"#{id}"}
	</update>




</mapper>