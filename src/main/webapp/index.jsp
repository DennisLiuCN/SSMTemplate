<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 1.jstl设置绝对路径方式
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="basePath" value="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
 -->
<%-- 2.Java设置绝对路径方式
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
 --%>
<html>
<head>
<!-- 3.<base>标签设置绝对路径方式 -->
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 
    <link type="text/css" rel="stylesheet" href="styles.css">
 -->
<style type="text/css">
lable {
	font-family: 微软雅黑, 宋体;
	font-size: 1em;
	color: red;
}
</style>
<title>首页</title>
</head>
<body>
	<img alt="favicon展示" src="images/favicon.ico" />
	<h2>
		<lable id="welcome" />
	</h2>
</body>
<!-- 对应1和2两种方式设置绝对路径时，资源的引入方式
<script type="text/javascript" src="${basePath }js/jquery-1.11.3.js"></script>
 -->
<!-- 对应3方式设置绝对路径时，资源的引入方式 -->
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#welcome").html("<spring:message code='hello'></spring:message>");
	});
</script>
</html>
