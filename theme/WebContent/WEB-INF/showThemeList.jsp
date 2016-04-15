<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
	List themeList = (List)request.getAttribute("themeList");
	if(themeList != null){
		%>
		传递成功!
		<%
	}
	String themeName;
	Iterator it = themeList.iterator();
	System.out.println(it.hasNext());
	while(it.hasNext()){
		themeName = (String)it.next();
		System.out.println(themeName);
		%>
		<br>
		<%= themeName %>
		<br>
		<%
	}
%>
</body>
</html>