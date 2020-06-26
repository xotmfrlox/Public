package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {
	
	//sqlsessionfactory를 저장할 인스턴스 변수와 셋터 메서드 선언
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Project> selectList() throws Exception {
		// TODO Auto-generated method stub
		//sqlsession은 sql 실행하는 도구
		//sqlsession의 주요 메서드 selectList(), selectOne(), insert(), update(), delete()
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ProjectDao.selectList");
		}finally {
			sqlSession.close();
		}
	}

			/*rs = stmt.executeQuery(
					"select pno, pname, sys_date, end_date, state, cre_date from projects order by pno desc");
			

			ArrayList<Project> projects = new ArrayList<Project>();

			while (rs.next()) {
				projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("SYS_DATE")).setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE")));
			}

			return projects;*/

	

	@Override
	public int insert(Project project) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count =  sqlSession.insert("spms.dao.ProjectDao.insert", project);
			sqlSession.commit();
			return count; 
		}finally {
			sqlSession.close();
		}
	}

			//기존의 insert문
			/*stmt = connection.prepareStatement(
			        "INSERT INTO PROJECTS"
			        + "(PNAME,CONTENT,SYS_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
			        + " VALUES (?,?,?,?,0,NOW(),?)");*/
			
			//변경 insert문
			/*stmt = connection.prepareStatement(
			"insert into projects"
					+"(PNAME,CONTENT,SYS_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
					+"values(MDB_StringEnc('POLICY001', '*', '*', '*', ? , CONNECTION_ID()),? , ? , ? ,0,now(),?)");
			      stmt.setString(1, project.getTitle());
			      stmt.setString(2, project.getContent());
			      stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			      stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			      stmt.setString(5, project.getTags());
			      return stmt.executeUpdate();*/
			

	@Override
	public Project selectOne(int no) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ProjectDao.selectOne",no);
		}finally {
			sqlSession.close();
		}
	}

		/*rs = stmt.executeQuery(
		        "SELECT PNO,PNAME,CONTENT,SYS_DATE,END_DATE,STATE,CRE_DATE,TAGS"
		        + " FROM PROJECTS WHERE PNO=" + no);  
		//rs = stmt.executeQuery("select pno, pname, content, sys_date, end_date, state, tags from projects"+ "where pno="+no);
		if(rs.next()) {
			return new Project()
					.setNo(rs.getInt("pno"))
					.setTitle(rs.getString("pname"))
					.setContent(rs.getString("content"))
					.setStartDate(rs.getDate("sys_date"))
					.setEndDate(rs.getDate("end_date"))
					.setState(rs.getInt("state"))
					.setCreatedDate(rs.getDate("cre_date"))
					.setTags(rs.getString("tags"));*/


	@Override
	public int update(Project project) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count =  sqlSession.update("spms.dao.ProjectDao.update", project);
			sqlSession.commit();
			return count; 
		}finally {
			sqlSession.close();
		}
	}
		
			/*stmt = connection.prepareStatement("UPDATE projects SET"
					+ " pname=?,"
					+ "content=?,"
					+ "sys_date=?, "
					+ "end_date=?,"
					+ "state=?,"
					+ "tags=?"
					+ "WHERE pno=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setInt(5, project.getState());
			stmt.setString(6, project.getTags());
			stmt.setInt(7, project.getNo());*/
			

	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count =  sqlSession.delete("spms.dao.ProjectDao.delete", no);
			sqlSession.commit();
			return count; 
		}finally {
			sqlSession.close();
		}
		
		//stmt = connection.prepareStatement("delete from projects where pno="+no);

	}
}

