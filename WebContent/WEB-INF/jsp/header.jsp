<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
header .header-stackup {
	position: fixed;
	width: 100%;
	top: 0;
	left: 0;
	background: #fff;
	z-index: 100;
	min-width: 700px;
	box-shadow: 0 0 5px #888
}

.center {
	position: relative;
	cursor: pointer;
	font-size: 12px;
	height: 50px;
	text-decoration: underline;
	line-height: 50px
}
</style>
<header data-click="{&quot;mod&quot;:&quot;header&quot;}">
	<div class="header-stackup" data-scroll-reveal="enter from the top over 0.66s" data-scroll-reveal-initialized="true"
		data-scroll-reveal-complete="true">
		<div class="center">
			<div class="span10 last">
				<div class="topNav clearfix">
					<ul>
						<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/index">首页</a>|</li>
						<s:if test="#session.isExist!=null">
							<li id="headerLogin" class="headerLogin" style="display: list-item;"><s:property value="#session.isExist.name" />|</li>
							<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/user_quit">[退出]</a>|</li>
							<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/cart_goCart">购物车</a>|</li>
							<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/order_findOrder">我的订单</a>|</li>
						</s:if>
						<s:else>
							<li id="headerLogin" class="headerLogin" style="display: list-item;"><a href="${pageContext.request.contextPath}/user_goLoginPage">登录</a>|
							</li>
							<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/cart_goCart">购物车</a>|</li>
							<li id="headerLogout" class="headerLogout" style="display: list-item;"><a href="${pageContext.request.contextPath}/order_findOrder">我的订单</a>|</li>
							<li id="headerRegister" class="headerRegister" style="display: list-item;"><a
									href="${pageContext.request.contextPath}/user_goRegisterPage">注册</a>|</li>
						</s:else>
						<li id="headerUsername" class="headerUsername"></li>
						<li><a>会员中心</a> |</li>
						<li><a>关于我们</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>
