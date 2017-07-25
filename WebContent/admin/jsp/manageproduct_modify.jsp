<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改</title>
<style type="text/css">
input: {
	width: 35px
}
</style>

</head>
<body>
	<s:debug></s:debug>
	<s:include value="manage.jsp"></s:include>
	<!-- 先遍历,提供添加操作 -->
	<form action="${pageContext.request.contextPath}/product_modifyProductByPid" enctype="multipart/form-data" method="post">
		<table border="1" style="width: 100%;">
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>市场价格</td>
				<td>商场价格</td>
				<td>图片展示</td>
				<td>商品描述</td>
				<td>是否热门</td>
				<td>所属二级分类</td>
				<td>操作</td>
			</tr>
			<s:iterator var="p" value="product">
				<tr>
					<td>
						<input readonly name="pid" value="<s:property value="#p.pid" />">
					</td>
					<td>
						<input type="text" name="pname" value="<s:property value="#p.pname" />">
					</td>
					<td>
						<input type="text" name="market_price" value="<s:property value="#p.market_price" />">
					</td>
					<td>
						<input type="text" name="shop_price" value="<s:property value="#p.shop_price" />">
					</td>
					<td>
						<img width="30px" src="${pageContext.request.contextPath}<s:property value="#p.image"/>"><img /> 修改商品图片请直接上传:<input type="file"
							name="upload">
					</td>
					<td>
						<input type="text" name="pdesc" value="<s:property value="#p.pdesc" />">
					</td>
					<td>
						<input type="text" name="is_hot" value="<s:property value="#p.is_hot" />">
					</td>
					<td>
						<select name="csid">
							<s:iterator var="cs" value="categorySecondList">
								<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname" /></option>
							</s:iterator>
						</select>
					</td>
					<td>
						<input type="submit" value="确认提交" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</form>
</body>
</html>