<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- 
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="basePath" value="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
 --%>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登陆</title>
</head>
<body>
	<!-- 通过Controller跳转JSP界面能够直接获取HttpSession对象，Model对象中会自动添加
		  而普通的不通过Controller跳转访问的JSP界面是无法获取到的 -->
	<!-- 此处不能像itemsList.jsp那样直接使用${username } -->
	<c:if test="${not empty sessionScope.username }">
		<label>当前用户${sessionScope.username }已登录，</label>
		<!-- 1.使用jstl标签时的href链接设置 -->
		<%-- 
		<a href="${basePath }/loginout/logout">退出</a>
		 --%>
		<!-- 2.使用<base>标签时的href链接设置 -->
		<a href="loginout/logout">退出</a>
	</c:if>
	<c:if test="${empty sessionScope.username }">
		<!-- 1.使用jstl标签时的action链接设置 -->
		<%-- 
		<form action="${basePath }/loginout/login" method="post">
		 --%>
		<!-- 2.使用<base>标签时的action链接设置 -->
		<form action="loginout/login" method="post">
			<label>用户账号：</label> <input type="text" name="username" /><br /> <label>用户密码：</label>
			<input type="password" name="password" /><br /> <input
				type="submit" value="登陆" />
		</form>
	</c:if>
</body>
</html>