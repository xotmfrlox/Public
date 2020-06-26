package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;

import spms.vo.Member;

@Component("memberDao") // value 생략 가능;
public class MySqlMemberDao implements MemberDao {

	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	//HashMap<String, Object> paramMap
	public List<Member> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.MemberDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	// rs = stmt.executeQuery("select mno, mname, email, cre_date from members order
	// by mno asc");

	/*
	 * while (rs.next()) { members.add( new Member() .setNo(rs.getInt("MNO"))
	 * .setName(rs.getString("MNAME")) .setEmail(rs.getString("EMAIL"))
	 * .setCreatedDate(rs.getDate("CRE_DATE")));
	 * 
	 * }
	 */

	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("spms.dao.MemberDao.delete", no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
		// stmt = connection.prepareStatement("delete from members where mno=" + no);
	}

	public Member selectOne(int no) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * rs = stmt.executeQuery("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" +
	 * " WHERE MNO=" + no); if (rs.next()) { return new Member()
	 * .setNo(rs.getInt("MNO")) .setEmail(rs.getString("EMAIL"))
	 * .setName(rs.getString("MNAME")) .setCreatedDate(rs.getDate("CRE_DATE"));
	 */

	public int update(Member member) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("spms.dao.MemberDao.update", member);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	/*
	 * stmt = connection.
	 * prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now() WHERE MNO=?"
	 * ); stmt.setString(1, member.getEmail()); stmt.setString(2, member.getName());
	 * stmt.setInt(3, member.getNo());
	 */

	public int insert(Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count =  sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
			return count; 
		}finally {
			sqlSession.close();
		}

			/*stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(?,?,?,NOW(), NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());*/
			
	}

/*	public Member exist(String email, String password) throws Exception {
		// 있으면 Member 객체 리턴, 없으면 null 리턴
		return null;

	}*/
}
