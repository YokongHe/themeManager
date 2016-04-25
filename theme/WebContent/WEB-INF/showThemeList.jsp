<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
		<h1>主题列表：</h1>
		<br/>
		<%
		while(it.hasNext()){
			themeName = it.next();
			
			%>
			<br>
			<form action="getIconList" method="post">
			<%= themeName %>
				<input type="hidden" value="<%=themeName %>" name="themeName" />
				<input type="submit" value="选择" />
			</form>
			<br>
			<%
		}
	}else {
		System.out.println("未传递主题列表到jsp页面");
		
	}
	
%>
<form action="getThemeList" method="post">
<input type="text" name="themeName">
<input type="submit" value="新增主题" />
</form>
</body>
</html>