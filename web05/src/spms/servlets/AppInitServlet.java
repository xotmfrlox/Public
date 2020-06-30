package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class AppInitServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AppInitServlet 준비...");
		super.init(config);
		try{
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"), sc.getInitParameter("username"), sc.getInitParameter("password"));
			//"conn"이라는 키를 사용하여 저장했기 때문에 꺼낼 때도 이 키를 사용하여 꺼내면 된다.
			sc.setAttribute("conn", conn);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("AppInitServlet 마무리....");
		super.destroy();
		Connection conn = (Connection) this.getServletContext().getAttribute("conn");
		try {
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (Exception e) {
		}

	}
}
