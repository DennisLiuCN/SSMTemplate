<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
</head>
<body>
	<input type="button" onclick="requestJson()" value="请求json，输出是json"></input>
	<input type="button" onclick="responseJson()" value="请求key/value，输出是json"></input>
</body>
<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript">
	function requestJson() {
		$.ajax({
			type : 'post',
			url : 'json/requestJson',
			contentType : 'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data : '{"name":"手机","price":999}',
			success : function(data) {//返回json结果
				var type = typeof data;
				if (type == 'object') {
					alert("商品名称：" + data.name + "\n商品价格：" + data.price)
				} else {
					alert("返回数据格式错误！");
				}
			}
		});
	}

	function responseJson() {
/* 		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath }/responseJson',
			// 请求是key/value这里不需要指定contentType，因为默认就 是key/value类型
			// contentType:'application/json;charset=utf-8',
			// 数据格式是json串，商品信息
			data : 'name=手机&price=999',
			success : function(data) {//返回json结果
				var type = typeof data;
				if (type == 'object') {
					alert("商品名称：" + data.name + "\n商品价格：" + data.price)
				} else {
					alert("返回数据格式错误！");
				}
			}
		});
 */
		$.post('json/responseJson', {
			name : '手机',
			price : 999
		}, function(data) {//返回json结果
			var type = typeof data;
			if (type == 'object') {
				alert("商品名称：" + data.name + "\n商品价格：" + data.price)
			} else {
				alert("返回数据格式错误！");
			}
		});
	}
</script>
</html>