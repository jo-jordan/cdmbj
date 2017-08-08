<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>会员登录</title>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	var verCodeFlag = false;

	function checkForm() {
		//用户名的非空校验
		var username = document.getElementById("username");
		if (username.value.length < 1) {
			alert("用户名为空.");
			return false;
		}

		//密码的非空校验
		var password = document.getElementById("password");
		if (password.value.length < 1) {
			alert("密码为空.");
			return false;
		}

		//验证码的校验
		if (!verCodeFlag) {
			alert("验证码错误.");
			return false;
		}
	}

	/*基于ajax的异步校验,查询username,email,phone是否重复*/
	function checkUEP(obj) {
		if (obj.value != "") {

			//1,获取XMLHttpRequest对象,对于现在的浏览器来说,都是內建的,但还是不排除IE5,IE6
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			//2,设置监听,当状态代码为4和200时表示链接准备就绪
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					var data = xmlhttp.responseText;
					document.getElementById("checkvercodereturn").innerHTML = data;
					if (data.indexOf("red") != -1) {
						verCodeFlag = false;
					} else {
						verCodeFlag = true;
					}
				}
			}
			//3,开启连接
			xmlhttp.open("GET",
					"${pageContext.request.contextPath}/user_checkVerCode?vercode="
							+ obj.value, true);
			xmlhttp.send(null);
		}
	}

	/*验证码的更换*/
	function changeVerCodeImg() {
		var oImg = document.getElementById("captchaImage");
		oImg.src = "${pageContext.request.contextPath}/createVerCodeAndImg?"
				+ new Date().getTime();
	}

	/*实现验证码输入完成之后自动校验*/
	function callCheckUEP(obj) {
		var vercode = obj.value;
		if (vercode.length == 4) {
			return checkUEP(obj);
		}
	}
</script>
<script type="text/javascript">
	//通过cookie名称获取cookie值,cookie在客户端的存储形式"username=aaa;password=123"
	function getCookie(username) {
		if (document.cookie.length > 0) {
			var u_start = document.cookie.indexOf(username + "=");
			if (u_start != -1) {
				u_start = u_start + username.length + 1;
				var u_end = document.cookie.indexOf(";", u_start);
				if (u_end == -1) {
					u_end = document.cookie.length;
				}
				//document.getElementById("username").value = unescape(document.cookie.substring(u_start,u_end));
				return unescape(document.cookie.substring(u_start, u_end));
			}
		}
		//username.value = "";
		return "";
	}

	function setCookie(flag) {
		//alert(flag);
		if (flag) {
			var username = document.getElementById("username").value;
			var exp = new Date();
			exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);//30天
			document.cookie = "username =" + escape(username) + ";expires="
					+ exp.toGMTString();
			alert(document.cookie);
		}
	}

	function delCookie(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval = getCookie(name);
		if (cval != null)
			document.cookie = name + "=''" + ";expires=" + exp.toGMTString();
	}
</script>
</head>
<body>
	
		<%@ include file="header.jsp"%>

	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<div class="title">
						<s:actionerror />
					</div>
					<form id="loginForm" action="${ pageContext.request.contextPath }/user_login" method="post" onsubmit="return checkForm()">
						<table>
							<tbody>
								<tr>
									<th>用户名:</th>
									<td>
										<input type="text" id="username" name="username" value="${cookie.username.value}" class="text" maxlength="20" /><span><s:fielderror
												fieldName="username" /></span>
									</td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td>
										<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off" /><span><s:fielderror
												fieldName="password" /></span>
									</td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td>
										<span class="fieldSet"> <input type="text" id="captcha" name="vercode" class="text captcha" maxlength="4" autocomplete="off"
											onkeyup="callCheckUEP(this)"> <a href="#" onclick="changeVerCodeImg()">
												<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/createVerCodeAndImg">点击更换验证码
											</a></span><br> <span id="checkvercodereturn"></span>
									</td>

								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
										<label> <input type="checkbox" id="isRememberUsername" onchange="setCookie(this.checked)">记住用户名
										</label> <label> &nbsp;&nbsp;<a>找回密码</a>
										</label>
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
										<input type="submit" class="submit" value="登 录">
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！
												<a href="./会员注册.htm">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>