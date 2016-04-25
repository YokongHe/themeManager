package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name="UploadServlet" ,urlPatterns={"/UploadServlet"},loadOnStartup=1  )  
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private boolean isMultipart;
	private String filePath;
	private String tempPath;
	private int maxFileSize = 10 * 1014 * 1024;
	private int maxMemSize = 1024 * 1024;
	private File file;
	private String themeName;
	private String appName;
	private String iconName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ServletContext context = getServletContext();
		filePath = context.getInitParameter("filePath");
		tempPath = context.getInitParameter("tempPath");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取文件要储存的位置
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
		//检查上传文件请求
		isMultipart = ServletFileUpload.isMultipartContent(request);
		 //  从 HTTP servlet 获取 fileupload 组件需要的内容 
        RequestContext requestContext = new ServletRequestContext(request);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (!isMultipart) {
			out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Servlet upload</title>");  
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<p>No file uploaded</p>"); 
	        out.println("</body>");
	        out.println("</html>");
	        return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 文件大小的最大值将被存储在内存中
	    factory.setSizeThreshold(maxMemSize);
	    // Location to save data that is larger than maxMemSize.
	    factory.setRepository(new File(tempPath));
	    
	    //创建一个文件上传程序
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    //允许上传文件大小的最大值
	    upload.setSizeMax(maxFileSize);
	    
	    try {
	    	//解析请求，获取文件项
	    	List fileItems = upload.parseRequest(requestContext);
	    	
	    	//处理上传的文件
	    	Iterator i = fileItems.iterator();
	    	
	    	out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Servlet upload</title>");  
	        out.println("</head>");
	        out.println("<body>");
	        
	        while (i.hasNext()) {
	        	FileItem fi = (FileItem)i.next();
	        	if (fi.isFormField()){
	        		if(((String)fi.getFieldName()).equals("appName")){
	        			appName = fi.getString().trim();
	        			appName = new String(appName.getBytes("iso-8859-1"),"utf-8"); 
	        		}
	        		if(((String)fi.getFieldName()).equals("themeName")){
	        			themeName = fi.getString().trim();
	        			themeName = new String(themeName.getBytes("iso-8859-1"),"utf-8"); 
	        		}
	        		if(((String)fi.getFieldName()).equals("iconName")){
	        			iconName = fi.getString().trim();
	        			iconName = new String(iconName.getBytes("iso-8859-1"),"utf-8"); 
	        			System.out.println("iconName: " + iconName);
	        		}
	        		continue;
	        	}
	        	if (!fi.isFormField()) {
	        		//获取上传文件的参数
	        		String fieldName = fi.getFieldName();
	        		String fileName = fi.getName();
	                String contentType = fi.getContentType();
	                boolean isInMemory = fi.isInMemory();
	                long sizeInBytes = fi.getSize();
	                //写入文件
	                file = new File(filePath + fileName);
	                File temFile = new File(filePath);
	                if(!temFile.exists())
	                	temFile.mkdirs();
	                fi.write(file);
	                out.println("Uploaded FileName: " + fileName + "<br>");
	        	}
	        }
//	    	out.println("</body>");
//	    	out.println("</html>");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    //更改刚刚上传的文件名为正确的文件名并更改到正确的位置
	    String newFileName = iconName + file.getName().substring(file.getName().lastIndexOf("."));
	    File tempFile = new File(filePath + themeName + "\\" + appName + "\\" + newFileName);
	    File folder = new File(filePath + themeName + "\\" + appName + "\\");
	    if(!folder.exists()){
	    	System.out.println("是否创建文件夹成功： " + folder.mkdirs());
	    }
	    if(tempFile.exists()){
	    	tempFile.delete();
	    }
	    System.out.println("正确的文件路径：" + filePath + themeName + "\\" + appName + "\\" + newFileName);
	    file.renameTo(tempFile);
	    tempFile.mkdirs();
	   	ServletContext context = getServletContext();
	   	System.out.println("文件路径： " + context.getRealPath(tempFile.getPath()));
	   	System.out.println("文件是否存在： " + tempFile.exists());
	    //删除多余文件
	    File exFile = new File(filePath + file.getName());
	    if(exFile.exists()) file.delete();
//	    out.println("上传成功！<form action='getIconList' method='get'><input type='hidden' value='" + themeName + "' name='themeName'/>"
//	    		+ "<input type='submit' value='返回'/>");
	    out.println("</body>");
    	out.println("</html>");
//    	response.setCharacterEncoding("utf-8");
//    	request.setAttribute("themeName", themeName);
//	    request.getRequestDispatcher("getIconList").forward(request, response);
	    System.out.println("================LOG=================");
	    System.out.println("update icon " + newFileName);
	    System.out.println("====================================");
	    
	}

}
