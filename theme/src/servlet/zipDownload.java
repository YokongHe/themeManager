package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * Servlet implementation class zipDownload
 */
@WebServlet("/zipDownload")
public class zipDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String themePath;
	
	private String zipPath;
	
	private File zipFile;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public zipDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	ServletContext context = getServletContext();
    	themePath = context.getInitParameter("filePath");
    	zipPath = context.getInitParameter("zipPath");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String themeName = request.getParameter("themeName");
		themeName = new String(themeName.getBytes("iso-8859-1"),"utf-8");
		compressExe(themeName);
		
		//开始下载
		//设置文件MIME类型  
		String fileName = zipFile.getName();
        response.setContentType(getServletContext().getMimeType(fileName));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
//        String fullFileName = getServletContext().getRealPath(fileName);  
        //System.out.println(fullFileName);  
        //读取文件  
        InputStream in = new FileInputStream(zipFile);  
        OutputStream out = response.getOutputStream();  
          
        //写文件  
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b);  
        }  
          
        in.close();  
        out.close();  
		
		
	}
	
	 /** 
     * 执行压缩操作 
     * @param srcPathName 需要被压缩的文件/文件夹 ; 
     */  
    public void compressExe(String themeName) {    
    	String srcPathName = themePath + themeName;
    	zipFile = new File(zipPath + themeName + ".zip");
    	zipFile.mkdirs();
        File srcdir = new File(srcPathName);    
        if (!srcdir.exists()){  
            throw new RuntimeException(srcPathName + "不存在！");    
        }   
            
        Project prj = new Project();    
        Zip zip = new Zip();    
        zip.setProject(prj);    
        zip.setDestFile(zipFile);    
        FileSet fileSet = new FileSet();    
        fileSet.setProject(prj);    
        fileSet.setDir(srcdir);    
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");    
        //fileSet.setExcludes(...); //排除哪些文件或文件夹    
        zip.addFileset(fileSet);    
        zip.execute();    
    }    

}
