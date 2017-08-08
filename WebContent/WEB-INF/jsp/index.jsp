<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>一个剁手平台 - 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/product.css" />
<style type="text/css">
</style>
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="container">
		<div id="hotProduct" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">热门商品</h3>
			</div>
			<div class="panel-body">
				<ul class="product_index">
					<s:iterator var="hp" value="#session.hotProductList">
						<li>
							<div class="product_div">
								<a href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#hp.pid"/>">
									<img src="${pageContext.request.contextPath}<s:property value="#hp.image"/>" style="display: block;" />
								</a>
								<p style="font-size: 12px; font-weight: bold; color: red">
									￥
									<s:property value="#hp.shop_price" />
								</p>
								<p>
									商品名称：
									<s:property value="#hp.pname" />
								</p>
							</div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="newProduct" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">最新商品</h3>
			</div>
			<div class="panel-body">
				<ul class="product_index" style="display: block;">
					<s:iterator var="np" value="#session.newProductList">
						<li>
							<div class="product_div">
								<a href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#hp.pid"/>">
									<img src="${pageContext.request.contextPath}<s:property value="#np.image"/>" style="display: block;" />
								</a>
								<p style="font-size: 12px; font-weight: bold; color: red">
									￥
									<s:property value="#hp.shop_price" />
								</p>
								<p>
									商品名称：
									<s:property value="#hp.pname" />
								</p>
							</div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div id="friendlyLink" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">新手指南</h3>
			</div>
			<div style="text-align: center">
				<a target="_blank">支付方式</a>
				|
				<a target="_blank">配送方式</a>
				|
				<a target="_blank">售后服务</a>
				|
				<a target="_blank">购物帮助</a>
				|
				<a>更多</a>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<s:debug></s:debug>

</body>
</html>