package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//애노테이션 유지 정책: 애노테이션 정보를 언제까지 유지할 것인지
//runtime: 실행 중에도 언제든지 @component 애노테이션의 속성값을 참조할 수 있음.
@Retention(RetentionPolicy.RUNTIME)

//interface 문법과 비슷 interface 키워드 앞에 @가 붙음.
public @interface Component {
	//객체 이름을 저장하는 용도로 사용할 'value'라는 기본 속성 정의
  String value() default "";
}
