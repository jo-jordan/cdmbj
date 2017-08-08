<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>分类页面</title>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//当前页码
		var page = $("#page").val();
		//总页数
		var totalPage = $("#totalPage").val();
		//上一页
		var prePage = $("#prePage");
		//下一页
		var nextPage = $("#nextPage");
		//首页
		var firstPage = $("#firstPage");
		//尾页
		var lastPage = $("#lastPage");
		//当前页面
		var curPage = $("#curPage");
		if (totalPage == 0) {
			prePage.addClass("disabled");
			nextPage.addClass("disabled");
			firstPage.addClass("disabled");
			lastPage.addClass("disabled");
			$("#prePage a").attr("href", "#");
			$("#nextPage a").attr("href", "#");
			$("#firstPage a").attr("href", "#");
			$("#lastPage a").attr("href", "#");
		} else {
			if (page == 1) {
				prePage.addClass("disabled");
				firstPage.addClass("disabled");
				$("#prePage a").attr("href", "#");
				$("#firstPage a").attr("href", "#");
			} else if (page > 1 && page < totalPage) {
				curPage.addClass("active");
			} else {
				nextPage.addClass("disabled");
				lastPage.addClass("disabled");
				$("#nextPage a").attr("href", "#");
				$("#lastPage a").attr("href", "#");
			}
		}
	});
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/product.css" />
<style>
.category-second-list {
	float: left;
	margin-top: 0;
	margin-left: 0;
	padding: 0;
}

.category-second-list ul {
	padding-left: 0;
	list-style-type: none;
}

.category-second-list ul li {
	width: 10em;
}

.category-second-list ul li a:hover, a:active {
	box-shadow: 0 0 1px 1px #CCCCCC;
}

.category-second-list ul li a {
	background-color: #f5f5f5;
	border-right: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
	display: block;
	padding: 8px 13px;
	text-decoration: none;
}

.pagination {
	
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container">
		<div id="hotProduct" class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					商品分类:
					<s:property value="categorySecondList[1].category.cname" />
				</h3>
			</div>
			<div class="panel-body" style="padding-left: 0; padding-top: 0;padding-bottom:0">
				<!-- 二级分类显示 -->
				<div class="category-second-list">
					<ul>
						<s:iterator var="cs" value="categorySecondList">
							<li>
								<a
									href="${pageContext.request.contextPath}/product_findProductByCsid?csid=<s:property value="#cs.csid"/>&page=1&cid=<s:property value="cid"/>">
									<s:property value="#cs.csname" />
								</a>
							</li>
						</s:iterator>
						<s:if test="#cs==null">
							<li>暂无数据</li>
						</s:if>
					</ul>
				</div>
				<div class="container">
					<input type="hidden" id="brandId" name="brandId" value=""> <input type="hidden" id="promotionId" name="promotionId" value="">
					<input type="hidden" id="orderType" name="orderType" value=""> <input type="hidden" id="pageNumber" name="pageNumber" value="1">
					<input type="hidden" id="pageSize" name="pageSize" value="20">
					<div id="result" class="product_list">
						<ul class="product_index">
							<s:iterator var="p" value="pageBean.productList">
								<s:if test="#p!=null">
									<li>
										<div class="product_div">
											<a href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#p.pid"/>&?cid=<s:property value="cid"/>">
												<img src="${pageContext.request.contextPath}<s:property value="#p.image"/>" width="180" height="160" /> <br>
											</a>
											<span style="display: block; color: red; font-weight: bold">
												商城价： ￥
												<s:property value="#p.shop_price" />
											</span>
											<span style="display: block; color: green">
												<s:property value="#p.pname" />
											</span>
										</div>
									</li>
								</s:if>
							</s:iterator>
							<s:else>
								<li>暂无数据</li>
							</s:else>
						</ul>
						
					</div>

				</div>

			</div>
			<div style="text-align:center;margin-bottom:0;padding-bottom:0">
				<input id="page" type="hidden" value="<s:property value="pageBean.page"/>" /> <input id="totalPage" type="hidden"
					value="<s:property value="pageBean.totalPage"/>" />
				<ul class="pagination" style="margin-bottom:0;padding-bottom:0">
					<li id="firstPage">
						<a href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=1">首页</a>
					</li>
					<li id="prePage">
						<a
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>">&laquo;</a>
					</li>
					<li id="curPage">
						<a href="#">
							<s:property value="pageBean.page" />
						</a>
					</li>
					<li id="nextPage">
						<a
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&raquo;</a>
					</li>
					<li id="lastPage">
						<a
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">尾页</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
	<s:debug></s:debug>
</body>
</html>