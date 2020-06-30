package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		// <form> 태그의 action 속성은 실행할 서블릿의 URL 주소입니다. 상대 경로 'add'를 사용했기 때문에 실제 URL은
		// http://localhost:9999/web04/member/add 입니다.
		// <form> 태그의 method 속성은 서버에 요청하는 방식을 지정합니다. 기본값은 'get'입니다.
		out.println("<form action='add' method='post'>");
		out.println("이름: <input type='text' name='name'><br>");
		out.println("이메일: <input type='text' name='email'><br>");
		// <form> 태그의 입력 항목은 총 세개가 있습니다. 암호는 보이지 않게 '*'가 대신 나타나도록 input 태그의 type 속성을
		// 'password'라고 설정.
		out.println("암호: <input type='password' name='password'><br>");
		// 서블릿에 입력폼의 값을 전달하는 방법은 간단합니다. 'submit' 타입의 <input> 태그를 사용하면 서버에 요청을 보내는 버튼을 만들
		// 수 있습니다.
		out.println("<input type='submit' value='추가'>");
		// 'reset' 타입의 <input> 태그는 입력폼을 초기화시키는 버튼을 생성합니다.
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sample", // JDBC URL
					"root", // DBMS 사용자 아이디
					"mysql"); // DBMS 사용자 암호
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(?,?,?,NOW(), NOW())");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			stmt.executeUpdate();
			
			//리다이렉트 메서드
			response.sendRedirect("list");
			
			//HTML을 출력하는 코드 주석
			/*response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			out.println("<body>");
			out.println("<p>등록 성공입니다!</p>");
			out.println("</body></html>");
			
			// 리프래시 정보를 으답 헤더에 추가
			response.addHeader("Refresh", "1;url=list");*/
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {

			try {
				if (stmt != null)
					;
				stmt.close();
			} catch (Exception e) {
			}

			try {
				if (conn != null)
					;
				conn.close();
			} catch (Exception e) {
			}

		}
	}

}
