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
ServletContext context = getServletContext();
String themeName = (String)request.getAttribute("themeName");
%>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		<h1>主题：<%=themeName %></h1>
		<table class="table">
				<thead>
					<tr>
						<th>
							应用
						</th>
						<th>
							图标
						</th>
						<th>
							图标预览
						</th>
						<th>
							选择文件
						</th>
						<th>
							替换
						</th>
					</tr>
				</thead>
				<tbody>
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
					<tr>
						<td rowspan="<%=mapEntry.getValue().getIconList().size() %>">
							<%=appName %>
						</td>
	<%
	while(iconIt.hasNext()) {
		String iconName = iconIt.next();
		String fileSrc = iconSrc + iconName + ".png";
		File icon = new File(fileSrc);
		String imgSrc = fileSrc.replaceAll("\\\\", "/");
		imgSrc = imgSrc.replaceFirst(":", "|");
		%>
						<td>
							<%=iconName %>
						</td>
		<%
		if(icon.exists()){
			%>
			<td>
				<img src="/themePic/<%=themeName %>/<%=appName %>/<%=iconName %>.png" width="50px" height="50px" />	
			</td>
			<%
		} else {
			%>
			<td>
				<img src="/defaultIcon/<%=iconName %>.png" width="50px" height="50px" />
			</td>
			<%
		}
		%>
		<td>
			<form action="UploadServlet" method="post" enctype="multipart/form-data">
				<input type="hidden" name="appName" value="<%=appName%>"/>
				<input type="hidden" name="iconName" value="<%=iconName %>" />
				<input type="file" name="file" size="50" />
				<input type="hidden" name="themeName" value="<%=themeName %>" />
		</td>
		<td>
				<input type="submit" value="替换icon" />
			</form>
		</td>
		</tr>
		
		<%
	}
}
%>
</tbody>
	</table>
	</div>
	</div>
	</div>



<form action="zipDownload" method="POST">
	<input type="hidden" value="<%=themeName %>" name="themeName">
	<input type="submit" value="打包下载" style="width:100px;height:30px;">
</form>
</body>
</html>