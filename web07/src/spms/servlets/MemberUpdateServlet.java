package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MySqlMemberDao;
import spms.vo.Member;

/*@WebServlet("/member/update.do")*/
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub

		try {
			ServletContext sc = this.getServletContext();
			MySqlMemberDao memberDao = (MySqlMemberDao) sc.getAttribute("memberDao");

			Member member = memberDao.selectOne(Integer.parseInt(request.getParameter("no")));
			request.setAttribute("member", member);

			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
		} catch (Exception e) {

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			ServletContext sc = this.getServletContext();

			MySqlMemberDao memberDao = (MySqlMemberDao) sc.getAttribute("memberDao");

			Member member = (Member) request.getAttribute("member");
			memberDao.update(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}
}
