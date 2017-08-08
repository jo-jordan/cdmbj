<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转页面...</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="divcontent">
		<table style="width: 850px; border: 0; cellspacing: 0;">
			<tr>
				<td style="padding: 30px; text-align: center">
					<table style="width: 60%; border: 0; cellspacing: 0; margin-top: 70px">
						<tr>
							<td style="width: 98">
								<img src="${pageContext.request.contextPath}/images/Icon_pic.jpg" width="128" height="128" />
							</td>
							<td style="padding-top: 30px">
								<font style="font-weight: bold; color: #FF0000"><s:actionmessage /></font><br /> <br />
								<p>内部出错...sorry,您可以去</p>								
								<a href="${ pageContext.request.contextPath }/admin/jsp/manage.jsp">去管理其他项目</a>
								<a href="${ pageContext.request.contextPath }/a/admin_goLogin">去重新登录</a>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>