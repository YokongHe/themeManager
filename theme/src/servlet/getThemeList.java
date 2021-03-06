package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import res.ResManager;

/**
 * Servlet implementation class getThemeList
 */
@WebServlet("/getThemeList")
public class getThemeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getThemeList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//传递主题列表
		request.setAttribute("themeList", ResManager.getThemeList(true));
		String url = "themeList";
		//跳转到选择主题页面
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setAttribute("themeList", IconFileName.THEME_LIST);
//		String url = "themeList";
//		
//		request.getRequestDispatcher(url).forward(request, response);
		String url ="themeList";
		String themeName = request.getParameter("themeName");
		themeName = new String(themeName.getBytes("iso-8859-1"),"utf-8"); 
		List<String> themeList = ResManager.getThemeList(false);
		if(!themeList.contains(themeName)){
			ResManager.insertTheme(themeName);
		}
		request.setAttribute("themeList", ResManager.getThemeList(true));
		request.getRequestDispatcher(url).forward(request, response);
	}

}
