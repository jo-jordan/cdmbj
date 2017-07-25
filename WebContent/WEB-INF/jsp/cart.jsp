<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物车</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript">
function changeCount(iptCount,pid){
	if(iptCount.value <= 0){
		alert("别瞎填数量好吗????");
		document.getElementById("quantity").value = 1;
	}else{
		//1,获取xmlhttp对象
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		//2,设置监听,当状态代码为4和200时表示链接准备就绪
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//var data = xmlhttp.responseText;
				self.location.href="${pageContext.request.contextPath}/cart_goCart";
			}
		}
		//3,开启连接
		xmlhttp.open("GET", "${pageContext.request.contextPath}/cart_changeProductCount?pid=" + pid + "&count=" + iptCount.value, true);
		xmlhttp.send(null);
		
	}
}
</script>
</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo"></div>
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container cart">
		<div class="span24">
			<s:if test="userCartList.size()==0">
				<div class="step step1">
					你的购物车还是空的,快去
					<a href="${pageContext.request.contextPath}/index" style="color: red">添加商品</a>
					吧!!
				</div>
			</s:if>
			<s:if test="userCartList!=null">
				<s:iterator var="uList" value="userCartList">
					<table>
						<tbody>
							<tr>
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<s:iterator var="u" value="#uList.userCartItems">
								<tr>
									<td width="60">
										<input type="hidden" name="id" value="22"> <img src="${pageContext.request.contextPath}<s:property value="#u.product.image"/>">
									</td>
									<td>
										<a target="_blank">
											<s:property value="#u.product.pname" />
										</a>
									</td>
									<td>
										￥
										<s:property value="#u.product.shop_price" />
									</td>
									<td class="quantity" width="60">
										<input type="text" name="quantity" id="quantity" onchange="changeCount(this,<s:property value="#u.product.pid"/>)"
											value="<s:property value="#u.count"/>" maxlength="4">
										<div>
											<span class="increase">&nbsp;&nbsp;</span> <span class="decrease">-</span>
										</div>
									</td>
									<td width="140">
										<span class="subtotal">￥<s:property value="#u.subtotal" /></span>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/cart_deleteFromCart?pid=<s:property value="#u.product.pid"/>" class="delete">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<dl id="giftItems" class="hidden" style="display: none;">
					</dl>
					<div class="total">
						<em id="promotion"></em> <em> 登录后确认是否享有优惠 </em> 赠送积分: <em id="effectivePoint"><s:property value="userCartList.total" /></em> 商品金额: <strong
							id="effectivePrice">￥<s:property value="#uList.total" />元
						</strong>
					</div>
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/cart_clear" id="clear" class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath}/order_addToOrder" id="submit" class="submit">提交订单</a>
					</div>
				</s:iterator>
			</s:if>
			<s:else>
				<s:if test="#session.cart==null">
					<div class="step step1">
						你的购物车还是空的,快去
						<a href="${pageContext.request.contextPath}/index" style="color: red">添加商品</a>
						吧!!
					</div>
				</s:if>
				<table>
					<tbody>
						<tr>
							<th>图片(no user)</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
						<s:iterator var="ci" value="#session.cart.cartItem">
							<tr>
								<td width="60">
									<input type="hidden" name="id" value="22"> <img src="${pageContext.request.contextPath}<s:property value="#ci.product.image"/>">
								</td>
								<td>
									<a target="_blank">
										<s:property value="#ci.product.pname" />
									</a>
								</td>
								<td>
									￥
									<s:property value="#ci.product.shop_price" />
								</td>
								<td class="quantity" width="60">
									<input type="text" name="quantity" onchange="changeCount(this,<s:property value="#ci.product.pid"/>)"
										value="<s:property value="#ci.count"/>" maxlength="4">
									<div>
										<span class="increase">&nbsp;&nbsp;</span> <span class="decrease">-</span>
									</div>
								</td>
								<td width="140">
									<span class="subtotal">￥<s:property value="#ci.subtotal" /></span>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/cart_deleteFromCart?pid=<s:property value="#ci.product.pid"/>" class="delete">删除</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em> <em> 登录后确认是否享有优惠 </em> 赠送积分: <em id="effectivePoint"><s:property value="#session.cart.total" /></em> 商品金额: <strong
						id="effectivePrice">￥<s:property value="#session.cart.total" />元
					</strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/cart_clear" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/order_addToOrder" id="submit" class="submit">提交订单</a>
				</div>
			</s:else>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<s:debug></s:debug>
</body>
</html>