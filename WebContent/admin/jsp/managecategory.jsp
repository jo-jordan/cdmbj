<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一级分类管理</title>
<script type="text/javascript">
function modifyCategory(ipt_cname,cid){
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
			self.location.href = "${pageContext.request.contextPath}/category_manage";
		}
	}
	//3,开启连接
	xmlhttp.open("POST",
			"${pageContext.request.contextPath}/category_modifyCategory?cname="
					+ ipt_cname.value + "&cid=" + cid, true);
	xmlhttp.send(null);
	
}
</script>
</head>
<body>
	<s:debug></s:debug>
	<s:include value="manage.jsp"></s:include>
	<table border="1" style="width: 100%;">
		<tr>
			<td>一级分类唯一标识符</td>
			<td>一级分类名称</td>
			<td>操作</td>
		</tr>
		<s:iterator var="c" value="category">
			<tr>
				<td>
					<s:property value="#c.cid" />
				</td>
				<td>
					<input type="text" onchange="modifyCategory(this,<s:property value="#c.cid" />)" value="<s:property value="#c.cname"/>" />
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/category_deleteCategoryByCid?cid=<s:property value="#c.cid"/>">删除</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<form action="${pageContext.request.contextPath}/category_addCategory" method="post">
		<table border="1" style="width: 100%;">
			<tr>
				<td colspan="2">
					<p>
						添加一级分类
						<s:actionmessage></s:actionmessage>
					</p>
				</td>
			</tr>
			<tr>
				<td>二级分类名称</td>
				<td>操作</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="cname" />
				</td>
				<td>
					<input type="submit" value="确认提交">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>