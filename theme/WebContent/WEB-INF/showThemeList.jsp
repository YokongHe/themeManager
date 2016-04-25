<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<% 
	List<String> themeList = (List<String>)request.getAttribute("themeList");
	if(themeList != null){
		System.out.println("传递到jsp页面");
		String themeName;
		Iterator<String> it = themeList.iterator();
		System.out.println(it.hasNext());
		%>
		<div class="container-fluid">
			<div class="row-fluid">
			<div class="span12">
			<h1>主题列表</h1>
			<form action="getThemeList" method="post">
				<input type="text" name="themeName">
				<input type="submit" value="新增主题" />
			</form>
			<table class="table">
				<thead>
					<tr>
						<th>
							主题名
						</th>
						<th>
							选择
						</th>
					</tr>
				</thead>
				<tbody>
		<%
		while(it.hasNext()){
			themeName = it.next();
			
			%>
			<tr>
				<td>
					<%= themeName %>
				</td>
				<td>
					<form action="getIconList" method="post">
						<input type="hidden" value="<%=themeName %>" name="themeName" />
						<input type="submit" value="选择" />
					</form>
				</td>
			</tr>
			<%
		}
	}else {
		System.out.println("未传递主题列表到jsp页面");
		
	}
	
%>
</tbody>
</table>
</div>
</div>
</div>

</body>
</html>