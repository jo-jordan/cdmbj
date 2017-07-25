<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${pageContext.request.contextPath}/index">首页</a> |</li>
		<s:iterator var="c" value="#session.cateList">
			<li><a href="${pageContext.request.contextPath}/product_findCSByCid?cid=<s:property value="#c.cid"/>&page=1">
					<s:property value="#c.cname" />
				</a> |</li>
		</s:iterator>
	</ul>
</div>