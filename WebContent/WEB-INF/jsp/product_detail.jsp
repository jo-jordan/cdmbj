<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>分类页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/cart_addToCart" method="post">
		<div class="container header">
			<div class="span5">
				<div class="logo"></div>
			</div>
			<div class="span9">
				<div class="headerAd"></div>
			</div>
			<%@ include file="header.jsp"%>
		</div>
		<div class="container productContent">
			<s:iterator var="p" value="product">
				<div class="span18 last" style="width: 950px">
					<div class="productImage">
						<a title="" style="outline-style: none; text-decoration: none;" id="zoom"
							href="${pageContext.request.contextPath}<s:property value="#p.image"/>" rel="gallery"> </a>
						<div class="zoomPad">
							<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath}<s:property value="#p.image"/>" />
							<div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoomPup"></div>
							<div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomWindow">
								<div style="width: 368px;" class="zoomWrapper">
									<div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div>
									<div style="width: 0%; height: 0px;" class="zoomWrapperImage">
										<img src="${pageContext.request.contextPath}<s:property value="#p.image"/>"
											style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;" />
									</div>
								</div>
							</div>
							<div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">Loading zoom</div>
						</div>
					</div>
					<div class="name">
						<s:property value="#p.pname" />
					</div>
					<div class="sn">
						<div>
							编号：
							<s:property value="#p.pid" />
						</div>
					</div>
					<div class="info">
						<dl>
							<dt>血亏价:</dt>
							<dd>
								<strong>￥：<s:property value="#p.shop_price" />元/份
								</strong> 参 考 价：
								<del>
									￥
									<s:property value="#p.market_price" />
									元/份
								</del>
							</dd>
						</dl>
						<dl>
							<dt>促销:</dt>
							<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
							</dd>
						</dl>
						<dl>
							<dt></dt>
							<dd>
								<span> </span>
							</dd>
						</dl>
					</div>
					<input type="hidden" name="pid" value="<s:property value="#p.pid"/>" />
					<div class="action">
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="quantity" name="count" value="1" maxlength="4" type="text" />
								<div>
									<span id="increase" class="increase">&nbsp;</span> <span id="decrease" class="decrease">&nbsp;</span>
								</div>
							</dd>
							<dd>件</dd>
						</dl>
						<div class="buy">
							<input id="addCart" class="addCart" value="加入购物车" type="submit" />
						</div>
					</div>
					<div id="bar" class="bar" style="width: 950px;">
						<ul style="width: 950px;">
							<li id="introductionTab"><a href="#introduction">商品介绍</a></li>
						</ul>
					</div>
					<div id="introduction" class="introduction" style="width: 950px">
						<div class="title" style="width: 950px">
							<strong>商品介绍</strong>
						</div>
						<div>
							<img src="${pageContext.request.contextPath}<s:property value="#p.image"/>" /> <br>
							<s:property value="#p.pdesc" />
						</div>
					</div>
				</div>
			</s:iterator>
		</div>
		<%@ include file="footer.jsp"%>
	</form>
</body>
</html>