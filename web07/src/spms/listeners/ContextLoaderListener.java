package spms.listeners;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import spms.context.ApplicationContext;


@WebListener
// 웹 애플리케이션의 시작과 종료 사건 처리하려면 ServletContextListener 규칙에 따라 작성해야 합니다.
public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext applicationContext;
	
	//applicationcontext 객체 얻을 때 사용
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub

		try {
			applicationContext = new ApplicationContext();
			
			String resource = "spms/dao/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			
			ServletContext sc = event.getServletContext();
			
			//web.xml에 설정된 매개변수 정보 가져옴.
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			//프런트 컨트롤러에서 사용할 수 있게 applicationContext에 저장
			applicationContext.prepareByAnnotaionObjects("");
			applicationContext.injectDependency();
			
			
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
