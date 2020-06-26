package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.MySqlMemberDao;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;

	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
		
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub 
		//MySqlMemberDao memberDao = (MySqlMemberDao)model.get("memberDao");

		Integer no = (Integer)model.get("no");
	   memberDao.delete(no);
		
	    return "redirect:list.do";
	}
	//원하는 데이터 물어보기, 호출 규칙 정의하기
	// 프런트 컨트롤러 입장에서는 이 규칙을 준수하는 페이지 컨트롤러를 호출할 때만 VO 객체 준비
	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
				"no", Integer.class
		};
	}

}
