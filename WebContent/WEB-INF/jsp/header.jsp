<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<header>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header" style="margin: 0; padding: 0">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a href="${pageContext.request.contextPath}/index" class="navbar-brand">
					<span class="glyphicon glyphicon-home"></span> 首页
				</a>
			</div>
			<div class="collapse navbar-collapse" id="navbar">
				<ul class="nav navbar-nav">
					<s:if test="#session.isExist!=null">
						<li><a href="${pageContext.request.contextPath}/user_goRegisterPage">注册</a></li>
						<li><a href="#">
								<s:property value="#session.isExist.name" />
							</a></li>
						<li><a href="${pageContext.request.contextPath}/user_quit">退出</a></li>
						<li><a href="${pageContext.request.contextPath}/cart_goCart">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/order_findOrder">我的订单</a></li>
					</s:if>
					<s:else>

				

					<li><a href="${pageContext.request.contextPath}/user_goRegisterPage">注册</a></li>
					<li><a href="${pageContext.request.contextPath}/user_goLoginPage">登录</a></li>

					<li><a href="${pageContext.request.contextPath}/cart_goCart">购物车</a></li>
					<li><a href="${pageContext.request.contextPath}/order_findOrder">我的订单</a></li>
						</s:else>
				</ul>
				<div style="float: right; padding-left: 20%; padding-top: 9px">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						展开商品分类 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" style="float: right; margin-left: 72%">
						<s:iterator var="c" value="#session.cateList">
							<li><a href="${pageContext.request.contextPath}/product_findCSByCid?cid=<s:property value="#c.cid"/>&page=1">
									<s:property value="#c.cname" />
								</a></li>
						</s:iterator>
					</ul>
				</div>
			</div>


		</div>

	</nav>
	<nav class="navbar navbar-default" role="navigation"></nav>


</header>
