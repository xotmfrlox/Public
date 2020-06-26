package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.listeners.ContextLoaderListener;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// service를 사용한 이유는 다양한 요청방식에 대응하기 위해서
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		//이전에는 페이지 컨트롤러가 ServletContext에 저장되었기 때문에 이 객체 준비 ApplicationContext를 도입하면서 필요 없어짐.
		//ServletContext sc = this.getServletContext();
		ApplicationContext ctx = ContextLoaderListener.getApplicationContext();

		HashMap<String, Object> model = new HashMap<String, Object>();
		model.put("session", request.getSession());

		// 회원 목록을 처리할 controller 저장 그래야만 add, update등의 객체 주소도 저장할 수 있다.
		//Controller pageController = (Controller) sc.getAttribute(servletPath);
		Controller pageController = (Controller) ctx.getBean(servletPath);
		
		// 페이지 컨트롤러로 위임 조건문이 사라졌다.
		//데이터 준비 자동
		if(pageController == null) {
			try {
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pageController instanceof DataBinding) {
			prepareRequestData(request, model, (DataBinding) pageController);
		}

		String viewUrl;
		try {
			viewUrl = pageController.execute(model);
		

		for (String key : model.keySet()) {
			request.setAttribute(key, model.get(key));
		}

		if (viewUrl.startsWith("redirect:")) {
			response.sendRedirect(viewUrl.substring(9));
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
			rd.include(request, response);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model,
			DataBinding dataBinding) {
		// TODO Auto-generated method stub
		//데이터 준비
		//배열에서 꺼낸 값을 보관할 임시 변수 준비 데이터 이름(String), 데이터 타입(Class), 데이터 객체(Object)를 위한 참조변수
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		
		//데이터 이름과 데이터 타입을 꺼내기 쉽게 2씩 증가하면서 반복문 돌리기
		for(int i =0; i < dataBinders.length; i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			//ServletRequsetDataBinder 클래스의 bind()메서드 호출
			//이 메서드는 dataName과 일치하는 요청 매개변수를 찾고 dataType을 통해 해당 클래스의 인스턴스를 생성합니다.
			//찾은 매개변수 값을 인스턴스에 저장하며 그 인스턴스를 반환합니다.
			try {
				dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//bind() 메서드가 반환한 데이터 객체는 Map 객체에 담습니다.
			//이 작업을 통해 페이지 컨트롤러가 사용할 데이터 준비
			model.put(dataName, dataObj);
		}
		
	}

}
