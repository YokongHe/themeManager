package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import res.AppBean;
import res.ResManager;

/**
 * Servlet implementation class getIconList
 */
@WebServlet("/getIconList")
public class getIconList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getIconList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String url = "iconList";
		String themeName = request.getParameter("themeName");
		if(themeName == null){
			themeName = (String)request.getAttribute("themeName");
			System.out.println("DOPOST servlet 获取: " + themeName);
		}
		Map<String, AppBean> appMap = ResManager.getAppMap(true);
		request.setAttribute("themeName", themeName);
		request.setAttribute("appMap", appMap);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
