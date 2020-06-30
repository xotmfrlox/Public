package spms.listeners;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
// 웹 애플리케이션의 시작과 종료 사건 처리하려면 ServletContextListener 규칙에 따라 작성해야 합니다.
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub

		try {
			ServletContext sc = event.getServletContext();
			
			//톰캣 서버에서 자원을 찾기 위해 InitialContext 객체를 생성
			InitialContext initialContext = new InitialContext();
			//InitalContext의 lookup() 메서드를 이용하면, JNDI 이름으로 등록되어 있는 서버 자원을 찾을 수 있다.
			//lookup()이 리턴하는 자원이 DataSource이기 때문에 형변환하기
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/sample");

			MemberDao memberDao = new MemberDao();
			// DataSource를 주이하는 셋터 호출
			memberDao.setDataSource(ds);

			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//톰캣 서버에서 자동으로 해제하라고 설정되어있다. => closeMethod="close"
	}

}
