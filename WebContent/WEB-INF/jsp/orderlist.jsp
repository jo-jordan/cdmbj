<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/address.js"></script>
<script type="text/javascript">
	function checkNull() {
		var useraddr = document.getElementById("useraddr").value;
		var userusername = document.getElementById("userusername").value;
		var userphone = document.getElementById("userphone").value;
		var streetaddr = document.getElementById("streetaddr").value;
		useraddr = document.getElementById('cmbProvince').value
				+ document.getElementById('cmbCity').value
				+ document.getElementById('cmbArea').value + streetaddr;
		if (streetaddr == "") {
			alert("街道地址为空!");
			return false;
		}
		if (userusername == "") {
			alert("收货人为空!");
			return false;
		}
		if (userphone == "") {
			alert("联系方式为空!");
			return false;
		}
	}
</script>
</head>
<body>
	<s:debug></s:debug>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="http://localhost:8080/mango/">
					<img src="./image/logo.gif" alt="Mango商城" />
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="./image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container cart">
		<div class="span24">
			<div class="step step1">
				<ul>
					<li class="current"></li>
					<li>生成订单成功</li>
				</ul>
			</div>
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
					<s:iterator var="o" value="order.orderItem">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22" /> <img src="<s:property value="o.product.image"/>" />
							</td>
							<td>
								<a target="_blank">
									<s:property value="product.pname" />
								</a>
							</td>
							<td>
								<s:property value="product.shop_price" />
							</td>
							<td class="quantity" width="60">
								<input type="text" name="count" value="<s:property value="#ci.count"/>" maxlength="4" />
								<div>
									<span id="increase" class="increase">&nbsp;</span> <span id="decrease" class="decrease">&nbsp;</span>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#ci.subtotal" /></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart_deleteFromCart?pid=<s:property value="product.pid"/>" class="delete">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em> 商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.total" />元
				</strong>
			</div>
			<form id="orderForm" action="${pageContext.request.contextPath}/order_payOrder" method="post" onsubmit="return checkNull()">
				<input type="hidden" name="order.oid" value="" />
				<div class="span24">
					<p>
						<input id="useraddr" name="order.user.addr" type="hidden" /> 收货地址： <select id="cmbProvince" name="cmbProvince"></select> <select
							id="cmbCity" name="cmbCity"></select> <select id="cmbArea" name="cmbArea"></select>
						<script type="text/javascript">
							addressInit('cmbProvince', 'cmbCity', 'cmbArea');
						</script>
						街道地址： <input id="streetaddr" type="text" value="" style="width: 550px" /> <br /> 收货人&nbsp;&nbsp;&nbsp;： <input id="userusername"
							name="order.user.username" type="text" value="<s:property value="order.user.username"/>" style="width: 150px" /> <br /> 联系方式： <input
							id="userphone" name="order.user.phone" type="text" value="<s:property value="order.user.phone"/>" style="width: 150px" />
					</p>
					<hr />
					<p>
						选择银行：<br /> <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked" /> 工商银行 <img src="./bank_img/icbc.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="pd_FrpId" value="BOC-NET-B2C" /> 中国银行 <img src="./bank_img/bc.bmp"
							align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="pd_FrpId" value="ABC-NET-B2C" /> 农业银行 <img src="./bank_img/abc.bmp"
							align="middle" /> <br /> <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" /> 交通银行 <img src="./bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" /> 平安银行 <img src="./bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" /> 建设银行 <img src="./bank_img/ccb.bmp" align="middle" /> <br /> <input
							type="radio" name="pd_FrpId" value="CEB-NET-B2C" /> 光大银行 <img src="./bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" /> 招商银行 <img src="./bank_img/cmb.bmp" align="middle" />
					</p>
					<hr />
					<p style="text-align: right">
						<%-- <a href="javascript:document.getElementById('orderForm').submit();"> <img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51"
							border="0" />
						</a> --%>
						<input type="submit" value=""
							style="display:inline-block;width:200px;height:55px;background-image:url(${pageContext.request.contextPath}/images/finalbutton.gif);">
					</p>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>