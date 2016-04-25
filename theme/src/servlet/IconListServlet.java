package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import res.ResManager;

/**
 * Servlet implementation class IconListServlet
 */
@WebServlet("/IconListServlet")
public class IconListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IconListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String themeName = request.getParameter("themeName");
		
		
//		String themeName = "default";
//		List<String> appList = ResManager.APP_LIST;
//		Map<String, Object> iconFileMap = new LinkedHashMap<>();
//		Iterator<String> it = appList.iterator();
//		String tempName;
//		String tempSrc;
//		while(it.hasNext()){
//			tempName = it.next();
//			tempSrc = ResManager.FILE_NAME_MAP.get(tempName);
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
