<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择你要管理的页面</title>
<style type="text/css">
header .header-stackup {
	position: fixed;
	width: 100%;
	top: 0;
	left: 0;
	background: #fff;
	z-index: 100;
	min-width: 700px;
	box-shadow: 0 0 5px #888
}

.center {
	position: relative;
	cursor: pointer;
	font-size: 12px;
	height: 50px;
	text-decoration: underline;
	line-height: 50px
}
</style>
</head>
<body>
	<header data-click="{&quot;mod&quot;:&quot;header&quot;}">
		<div class="header-stackup" data-scroll-reveal="enter from the top over 0.66s" data-scroll-reveal-initialized="true"
			data-scroll-reveal-complete="true">
			<div>
				<a href="${pageContext.request.contextPath}/user_manage">用户管理</a>
				<a href="${pageContext.request.contextPath}/category_manage">一级分类管理</a>
				<a href="${pageContext.request.contextPath}/categorySecond_manage">二级分类管理</a>
				<a href="${pageContext.request.contextPath}/product_manage">商品管理</a>
				<a href="${pageContext.request.contextPath}/order_manage">订单管理</a>
				当前用户:
				<s:property value="#session.adminUser.adminname" />
			</div>
		</div>
	</header>
</body>
</html>