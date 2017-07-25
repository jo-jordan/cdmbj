<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script type="text/javascript">
	function modifyUser(param,uid) {
		//alert(param.name + "::::" +param.value );
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
				//self.location.href = "${pageContext.request.contextPath}/order_findOrder";
				self.location.href = "${pageContext.request.contextPath}/user_manage";
			}
		}
		//3,开启连接
		xmlhttp.open("POST",
				"${pageContext.request.contextPath}/user_modifyUser?"+param.name+"="
						+ param.value + "&uid=" + uid, true);
		xmlhttp.send(null); 

	}
</script>
</head>
<body>
	<s:debug></s:debug>
	<s:include value="manage.jsp"></s:include>
	<table border="1" style="width: 100%;">
		<tr>
			<td>用户唯一标识符</td>
			<td>用户名</td>
			<td>姓名</td>
			<td>邮箱</td>
			<td>手机</td>
			<td>地址</td>
			<td>性别</td>
			<td>激活状态</td>
			<td>注册码</td>
			<td>删除</td>
		<tr>
			<s:iterator var="uL" value="userList">
				<tr>
					<td>
						<input readonly name="uid" value="<s:property value="#uL.uid" />" />
					</td>
					<td>
						<s:property value="#uL.username" />
					</td>
					<td>
						<s:property value="#uL.name" />
					</td>
					<td>
						<input type="text" name="email" onchange="modifyUser(this,<s:property value="#uL.uid" />)" value="<s:property value="#uL.email" />">
					</td>
					<td>
						<input type="text" name="phone" onchange="modifyUser(this,<s:property value="#uL.uid" />)" value="<s:property value="#uL.phone" />">
					</td>
					<td>
						<input type="text" name="addr" onchange="modifyUser(this,<s:property value="#uL.uid" />)" value="<s:property value="#uL.addr" />">
					</td>
					<td>
						<input type="text" name="sex" onchange="modifyUser(this,<s:property value="#uL.uid" />)" value="<s:property value="#uL.sex" />">
					</td>
					<td>
						<input type="text" name="state" onchange="modifyUser(this,<s:property value="#uL.uid" />)" value="<s:property value="#uL.state" />">
					</td>
					<td>
						<s:property value="#uL.code" />
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/user_deleteUserByUid?uid=<s:property value="#uL.uid"/>">删除</a>
					</td>
				<tr>
			</s:iterator>
	</table>

</body>
</html>