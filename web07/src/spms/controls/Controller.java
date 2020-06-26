package spms.controls;

import java.util.Map;

//페이지 컨트롤러를 위한 인터페이스 정의
public interface Controller {
	  String execute(Map<String, Object> model) throws Exception;
	}
