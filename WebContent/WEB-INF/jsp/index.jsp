<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>一个剁手平台 - 首页</title>
<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="http://localhost:8080/mango/">
					<img src="./image/logo.gif" alt="Mango商城" />
				</a>
			</div>
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container index">
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
				<div class="title">
					<strong>热门商品</strong>
					<!-- <a  target="_blank"></a> -->
				</div>
				<ul class="tab">
					<li class="current"><a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a></li>
					<li><a target="_blank"></a></li>
					<li><a target="_blank"></a></li>
				</ul>
				<!-- 					<div class="hotProductAd">
			<img src="${pageContext.request.contextPath}/image/a.jpg" width="260" height="343" alt="热门商品" title="热门商品">
</div> -->
				<ul class="tabContent" style="display: block;">
					<s:iterator var="hp" value="#session.hotProductList">
						<li><a target="_blank" href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#hp.pid"/>">
								<img src="${pageContext.request.contextPath}<s:property value="#hp.image"/>" style="display: block;" />
							</a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
				<div class="title">
					<strong>最新商品</strong>
					<a target="_blank"></a>
				</div>
				<ul class="tab">
					<li class="current"><a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a></li>
				</ul>
				<!-- 					<div class="newProductAd">
									<img src="${pageContext.request.contextPath}/image/q.jpg" width="260" height="343" alt="最新商品" title="最新商品">
						</div>
						 -->
				<ul class="tabContent" style="display: block;">
					<s:iterator var="np" value="#session.newProductList">
						<li><a target="_blank" href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#hp.pid"/>">
								<img src="${pageContext.request.contextPath}<s:property value="#np.image"/>" style="display: block;" />
							</a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>新手指南</dt>
					<dd>
						<a target="_blank">支付方式</a>
						|
					</dd>
					<dd>
						<a target="_blank">配送方式</a>
						|
					</dd>
					<dd>
						<a target="_blank">售后服务</a>
						|
					</dd>
					<dd>
						<a target="_blank">购物帮助</a>
						|
					</dd>
					<dd>
						<a target="_blank">蔬菜卡</a>
						|
					</dd>
					<dd>
						<a target="_blank">礼品卡</a>
						|
					</dd>
					<dd>
						<a target="_blank">银联卡</a>
						|
					</dd>
					<dd>
						<a target="_blank">亿家卡</a>
						|
					</dd>
					<dd class="more">
						<a>更多</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<s:debug></s:debug>
</body>
</html>