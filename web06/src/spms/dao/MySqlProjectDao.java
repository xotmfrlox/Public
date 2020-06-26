package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

	// DriverManager를 대체할 수 있는 DataSource
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Project> selectList() throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select pno, pname, sys_date, end_date, state, cre_date from projects order by pno desc");
			

			ArrayList<Project> projects = new ArrayList<Project>();

			while (rs.next()) {
				projects.add(new Project().setNo(rs.getInt("PNO")).setTitle(rs.getString("PNAME"))
						.setStartDate(rs.getDate("SYS_DATE")).setEndDate(rs.getDate("END_DATE"))
						.setState(rs.getInt("STATE")));
			}

			return projects;
			
			

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}

	}

	@Override
	public int insert(Project project) throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			
			//기존의 insert문
			/*stmt = connection.prepareStatement(
			        "INSERT INTO PROJECTS"
			        + "(PNAME,CONTENT,SYS_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
			        + " VALUES (?,?,?,?,0,NOW(),?)");*/
			
			//변경 insert문
			stmt = connection.prepareStatement(
			"insert into projects"
					+"(PNAME,CONTENT,SYS_DATE,END_DATE,STATE,CRE_DATE,TAGS)"
					+"values(MDB_StringEnc('POLICY001', '*', '*', '*', ? , CONNECTION_ID()),? , ? , ? ,0,now(),?)");
			      stmt.setString(1, project.getTitle());
			      stmt.setString(2, project.getContent());
			      stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			      stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			      stmt.setString(5, project.getTags());
			      return stmt.executeUpdate();
			//date 에러 뜸 ㅎ
			
			 /*stmt = connection.prepareStatement("insert into projects(pname, content," +
			 "sys_date, end_date, cre_date, tags) values(?,?,?,?,0,now(),?)");
			//stmt = connection.prepareStatement(
					//"insert into projects(pname,content,sys_date,end_date,state,cre_date,tags) values (?,?,now(),now(),0,now(),?);");

			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setString(5, project.getTags());*/
			

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}

	}

	@Override
	public Project selectOne(int no) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		connection = ds.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(
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
					.setTags(rs.getString("tags"));
		}else {
			throw new Exception("해당 번호의 회원을 찾을 수 없습니다");
		}
		}catch(Exception e) {
			throw e;
		}finally {
			try {if (rs!=null) rs.close();}catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	@Override
	public int update(Project project) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		// 회원 정보 변경
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE projects SET"
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
			stmt.setInt(7, project.getNo());
			
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
			
		}
	}

	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement stmt = null;
		
		connection = ds.getConnection();
		stmt = connection.prepareStatement("delete from projects where pno="+no);
		stmt.executeUpdate();
		return no;
	}
}

