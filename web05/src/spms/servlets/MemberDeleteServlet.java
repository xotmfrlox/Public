package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		try {
			ServletContext sc = this.getServletContext();
			
			/*Connection conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();*/
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
	         
		    memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			response.sendRedirect("list");
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("../error/Error.jsp");
			rd.forward(request, response);
		}
	}
}
