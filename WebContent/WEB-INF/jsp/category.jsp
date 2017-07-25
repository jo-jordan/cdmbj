<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>分类页面</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango" />
<link href="./css/common.css" rel="stylesheet" type="text/css" />
<link href="./css/product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/jquery.lazyload.js"></script>
<script type="text/javascript" src="./js/common.js"></script>
</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo"></div>
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<!-- 二级分类显示 -->
				<dl>
					<s:iterator var="cs" value="categorySecondList">
						<dt>
							<a
								href="${pageContext.request.contextPath}/product_findProductByCsid?csid=<s:property value="#cs.csid"/>&page=1&cid=<s:property value="cid"/>">
								<s:property value="#cs.csname" />
							</a>
						</dt>
					</s:iterator>
					<s:if test="#cs==null">
						<dt>暂无数据</dt>
					</s:if>
				</dl>
			</div>
		</div>
		<div class="span18 last">
			<form id="productForm" action="#" method="get">
				<input type="hidden" id="brandId" name="brandId" value=""> <input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value=""> <input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="p" value="pageBean.productList">
							<s:if test="#p!=null">
								<li><a
										href="${pageContext.request.contextPath}/product_findProductByPid?pid=<s:property value="#p.pid"/>&?cid=<s:property value="cid"/>">
										<img src="${pageContext.request.contextPath}<s:property value="#p.image"/>" width="180" height="160" /> <span style='color: green'>
											<s:property value="#p.pname" />
										</span> <span class="price"> 商城价： ￥<s:property value="#p.shop_price" />
										</span>
									</a></li>
							</s:if>
						</s:iterator>
					</ul>
					<s:else>
						<ul>
							<li>暂无数据</li>
						</ul>
					</s:else>
				</div>
				<div class="pagination">
					第
					<s:property value="pageBean.page" />
					/
					<s:property value="pageBean.totalPage" />
					页
					<s:if test="pageBean.page != 1">
						<a href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=1" class="firstPage">&nbsp;</a>
						<a
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>"
							class="previousPage">&nbsp;</a>
					</s:if>
					<s:iterator var="i" begin="1" end="pageBean.totalPage" step="1">
						<s:if test="pageBean.page==#i">
							<span class="currentPage"><s:property value="#i" /></span>
						</s:if>
						<s:else>
							<a href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">
								<s:property value="#i" />
							</a>
						</s:else>
					</s:iterator>
					<s:if test="pageBean.page != pageBean.totalPage">
						<a class="nextPage"
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
						<a class="lastPage"
							href="${ pageContext.request.contextPath }/product_findCSByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
					</s:if>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>