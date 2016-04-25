<%@page import="jdk.nashorn.internal.runtime.Context"%>
<%@page import="res.ResManager, res.AppBean"%>
<%@page import="java.util.*, java.io.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=uft-8">
<title>Insert title here</title>

</head>
<body>
<%
ServletContext context = getServletContext();
String themeName = (String)request.getAttribute("themeName");
%>
==============================================================
<br/>
主题：<%=themeName %>
<br/>
<%
Map<String, AppBean> appMap = (Map<String, AppBean>)request.getAttribute("appMap");
String iconPath = context.getInitParameter("filePath") + themeName + "\\";
Iterator<Map.Entry<String, AppBean>> it = appMap.entrySet().iterator();
while(it.hasNext()) {
	Map.Entry<String, AppBean> mapEntry= it.next();
	String appName = mapEntry.getKey();
	String iconSrc = iconPath + appName + "\\";
	Iterator<String> iconIt = mapEntry.getValue().getIconList().iterator();
	%>
	==============================================================
	<br/>
	应用：<%=appName %>
	<br/>
	<%
	while(iconIt.hasNext()) {
		String iconName = iconIt.next();
		String fileSrc = iconSrc + iconName + ".png";
		File icon = new File(fileSrc);
		String imgSrc = fileSrc.replaceAll("\\\\", "/");
		imgSrc = imgSrc.replaceFirst(":", "|");
		%>
		-----------------------------------------------------------------
		<br/>
		图表：<%=iconName %>
		<%
		if(icon.exists()){
			%>
			<img src="/themePic/<%=themeName %>/<%=appName %>/<%=iconName %>.png" width="50px" height="50px" />	
			<%
		} else {
			%>
			<img src="none.png" />	
			<%
		}
		%>
		<form action="UploadServlet" method="post" enctype="multipart/form-data">
		<input type="hidden" name="appName" value="<%=appName%>"/>
		<input type="hidden" name="iconName" value="<%=iconName %>" />
		<input type="file" name="file" size="50" />
		<input type="hidden" name="themeName" value="<%=themeName %>" />
		<input type="submit" value="替换icon" />
		</form>
		<%
	}
	
	
	
}
%>
<form action="zipDownload" method="POST">
	<input type="hidden" value="<%=themeName %>" name="themeName">
	<input type="submit" value="打包下载">
</form>
</body>
</html>