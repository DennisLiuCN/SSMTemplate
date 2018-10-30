<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function queryItems() {
		// 提交form
		document.itemsForm.action = "items/queryItems";
		document.itemsForm.submit();
	}
	function deleteItems() {
		// 提交form
		document.itemsForm.action = "items/deleteItems";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
	<!-- 通过Controller跳转JSP界面能够直接获取HttpSession对象，Model对象中会自动添加
		  而普通的不通过Controller跳转访问的JSP界面是无法获取到的 -->
	<label>当前用户：${username }</label>
	<c:if test="${username==null }">
		<a href="loginout/login">登录</a>
	</c:if>
	<c:if test="${username!=null }">
		<label>，</label>
		<a href="loginout/logout">退出</a>
	</c:if>
	<form name="itemsForm" action="items/queryItems" method="post">
		<label>查询条件：</label>
		<table width="100%" border=1>
			<tr>
				<td>
					<label>商品名称：</label>
					<input name="name" />
					<label>商品类型：</label>
					<select name="itemtype">
						<c:forEach items="${itemtypes }" var="itemtype">
							<option value="${itemtype.key }">${itemtype.value }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="button" value="查询" onclick="queryItems()" />
					<input type="button" value="批量删除" onclick="deleteItems()"/>
				</td>
			</tr>
		</table>
		<label>商品列表：</label>
		<table width="100%" border=1>
			<tr>
				<th>选择</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>生产日期</th>
				<th>商品描述</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${itemsList }" var="item">
				<tr>
					<td><input type="checkbox" name="items_id" value="${item.id }"/></td>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<td><fmt:formatDate value="${item.createtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${item.detail }</td>
					<td><a href="items/editItems?id=${item.id }">修改</a></td>
				</tr>
			</c:forEach>

		</table>
	</form>
</body>

</html>