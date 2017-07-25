<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<script type="text/javascript">
	function getCurrTime(){
		var pdate = document.getElementById("pdate");
		var date = new Date();
		var formatDateTime = function (date) {  
		    var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? ('0' + m) : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    var h = date.getHours();  
		    var minute = date.getMinutes();  
		    minute = minute < 10 ? ('0' + minute) : minute;
		    var second = date.getSeconds(); 
		    var millisecond = date.getMilliseconds();
		    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
		};
		pdate.value = formatDateTime(date);
	}
	
</script>

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
	<!-- 先遍历,提供添加操作 -->
	<s:iterator var="pb" value="pageBean">
		<table border="1" style="width: 100%;">
			<tr>
				<td>商品ID</td>
				<td>商品名称</td>
				<td>市场价格</td>
				<td>商场价格</td>
				<td>图片展示</td>
				<td>商品描述</td>
				<td>是否热门</td>
				<td>添加时间</td>
				<td>所属二级分类</td>
				<td colspan="2">操作</td>
			</tr>
			<s:iterator var="p" value="#pb.productList">
				<tr>
					<td>
						<s:property value="#p.pid" />
					</td>
					<td>
						<s:property value="#p.pname" />
					</td>
					<td>
						<s:property value="#p.market_price" />
					</td>
					<td>
						<s:property value="#p.shop_price" />
					</td>
					<td>
						<img width="30px" src="${pageContext.request.contextPath}<s:property value="#p.image"/>"><img />
					</td>
					<td>
						<s:property value="#p.pdesc" />
					</td>
					<td>
						<s:property value="#p.is_hot" />
					</td>
					<td>
						<s:property value="#p.pdate" />
					</td>
					<td>
						<s:property value="categorySecond.csname" />
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/product_goModifyProduct?pid=<s:property value="#p.pid"/>">修改</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/product_deleteProductByPid?pid=<s:property value="#p.pid"/>">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<table border="1" style="width: 100%;">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/product_manage?page=1">首页</a>
				</td>
				<s:if test="#pb.page!=1">
					<td>
						<a href="${pageContext.request.contextPath}/product_manage?page=<s:property value="#pb.page - 1"/>">上一页</a>
					</td>
				</s:if>
				<td>
					当前第
					<s:property value="#pb.page" />
					页
					<s:iterator var="i" begin="1" end="#pb.totalPage" step="1">
						<a href="${pageContext.request.contextPath}/product_manage?page=<s:property value="i"/>">
							<s:property value="i" />
						</a>
					</s:iterator>
				</td>
				<s:if test="#pb.page!=#pb.totalPage">
					<td>
						<a href="${pageContext.request.contextPath}/product_manage?page=<s:property value="#pb.page + 1"/>">下一页</a>
					</td>
				</s:if>
				<td>
					<a href="${pageContext.request.contextPath}/product_manage?page=<s:property value="#pb.totalPage"/>">尾页</a>
				</td>
			</tr>
		</table>
	</s:iterator>

	<form action="${pageContext.request.contextPath}/product_addProduct" method="post" enctype="multipart/form-data">
		<table border="1" style="width: 100%;">
			<tr>
				<td colspan="6">添加商品</td>
			</tr>
			<tr>
				<td>商品名称</td>
				<td>市场价格</td>
				<td>商场价格</td>
				<td>是否热门</td>
				<td>所属二级分类</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="pname">
				</td>
				<td>
					<input type="text" name="market_price">
				</td>
				<td>
					<input type="text" name="shop_price">
				</td>
				<td>
					<input type="text" name="is_hot">
				</td>
				<td>
					<select name="csid">
						<s:iterator var="cs" value="categorySecondList">
							<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname" /></option>
						</s:iterator>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					图片上传:<input type="file" name="upload">
				</td>
			</tr>
			<tr>
				<td colspan="6">
					商品描述:<br>
					<input type="text" name="pdesc" style="width: 200px; height: 100px" />
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<input type="submit" value="确认添加商品">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>