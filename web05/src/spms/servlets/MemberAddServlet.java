package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

import java.sql.Connection;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 한글 깨지지 않게
		request.setCharacterEncoding("UTF-8");
		response.setContentType("Text/html; charset=UTF-8");
		
		try {
			//컨텍스트 초기화 매개변수 값 얻을려면 ServletContext 객체가 필요합니다.
			//이 객체를 통해 getInitParameter()를 호출하면 web.xml에 선언된 컨텍스트 초기화 매개변수 값을 얻을 수 있습니다.
			ServletContext sc = this.getServletContext();
			//MemberDao에서는 ServletContext에 접근할 수 없기 때문에 DB Connection 객체를 꺼낼 수 없습니다.
			//외부로부터 connection 객체를 주입받기 위한 셋터 메서드와 인스턴스 변수 준비
			/*Connection conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();*/
			
			//MemberDao를 사용하기 전에, 셋터를 먼저 호출하여 DB 커넥션 객체 주입
			MemberDao memberDao =(MemberDao)sc.getAttribute("memberDao");
			
			memberDao.insert(new Member()
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name")));
			
			response.sendRedirect("list");

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("../error/Error.jsp");
			rd.forward(request, response);
		}
	}

}
