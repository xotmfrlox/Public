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

import spms.dao.MySqlMemberDao;
import spms.vo.Member;


@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		try {
			ServletContext sc = this.getServletContext();
			
			MySqlMemberDao memberDao = (MySqlMemberDao)sc.getAttribute("memberDao");
	        /*Member member = (Member) request.getAttribute("member");*/
	        
		    memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
		    request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("../error/Error.jsp");
			rd.forward(request, response);
		}
	}
}
