package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

public class ApplicationContext {
	
	//객체의 보관: 프로퍼티에 설정된 대로 객체 준비, 객체 보관소가 필요한데 이를 위해 해시 테이블 준비
	//해시 테이블에서 객체를 꺼낼(getter) 메서드도 정의
	Hashtable<String, Object> objTable = new Hashtable<String, Object>();

	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	//ApplicationContext 생성자가 호출되면 매개변수로 지정도니 프로퍼티 파일의 내용을 로딩해야 합니다.
	public ApplicationContext(String propertiesPath) throws Exception {
		
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));

		prepareObjects(props);
		prepareAnnotaionObjects();
		injectDependency();
	}

	private void prepareAnnotaionObjects() throws Exception {
		// TODO Auto-generated method stub
		Reflections reflector = new Reflections("");
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key = null;
		for(Class<?> clazz:list) {
			key = clazz.getAnnotation(Component.class).value();
			objTable.put(key, clazz.newInstance());
		}
		
	}

	private void prepareObjects(Properties props) throws Exception {
		// TODO Auto-generated method stub
		//먼저 jndi 객체 찾을 때 사용할 InitialContext를 준비
		Context ctx = new InitialContext();
		String key = null;
		String value = null;

		for (Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			if (key.startsWith("jndi.")) {
				objTable.put(key, ctx.lookup(value));
			} else {
				objTable.put(key, Class.forName(value).newInstance());
			}
		}
	}

	private void injectDependency() throws Exception {
		// TODO Auto-generated method stub
		//만약 프로퍼티의 키가 "jndi."로 시작한다면 객체 생성하지 않고, InitialContext를 통해 얻기
		for (String key : objTable.keySet()) {
			if (!key.startsWith("jndi.")) {
				callSetter(objTable.get(key));
			}
		}

	}
	
	//매개변수로 주어진 객체에 대해 셋터 메서드를 찾아서 호출하는일
	private void callSetter(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Object dependency = null;
		//먼저 셋터 메서드 찾기
		for (Method m : obj.getClass().getMethods()) {
			if (m.getName().startsWith("set")) {
				
				//셋터 메서드의 매개변수와 타입이 일치하는 개체를 objTable에서 찾기
				dependency = findObjectByType(m.getParameterTypes()[0]);
				//의존 객체 찾았으면, 셋터 메서드 호출
				if (dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}

	private Object findObjectByType(Class<?> type) {
		for (Object obj : objTable.values()) {
			//셋터 메서드의 매개변수 타입과 일치하는 객체를 찾았으면 그 객체의 주소를 리턴.
			if (type.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}
