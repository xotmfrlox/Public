package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//컨텍스트 초기화 매개변수 사용 url jdbc , username root, password mysql
			ServletContext sc = this.getServletContext();
			
			//ServletContext 활용 - DB 커넥션 AppInitServlet driver, username, url 
			
			/*리스너로 대채
			 * Connection conn = (Connection) sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();*/
			
			//MemberDao 객체는 ServletContext로부터 꺼냅니다.
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			request.setAttribute("members", memberDao.selectList());
			
			response.setContentType("Text/html; charset=UTF-8");
			// jsp로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

}