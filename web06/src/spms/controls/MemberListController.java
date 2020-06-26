package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MemberDao;
import spms.dao.MySqlMemberDao;

@Component("/member/list.do")
public class MemberListController implements Controller{
	MemberDao memberDao;
	
	//memberDao가 필요하다.
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		
		model.put("members", memberDao.selectList());

		return "/member/MemberList.jsp";
	}

}
