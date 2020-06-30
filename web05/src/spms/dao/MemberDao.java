package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.util.DBConnectionPool;
import spms.vo.Member;

public class MemberDao {
	// MemberDao 클래스에서 ServlerContext에 접근할 수 없기 때문에,
	// ServletContext에 보관된 DB Connection 객체를 꺼낼 수 없습니다.
	/*Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}*/
	//이제는 커넥션 객체 직접 보관 X 대신 DBConnectionPool 객체를 저장하는 인스턴스 변수와 셋터 메서드 추가
	
	
	//Connection 객체를 담기 위한 로컬 변수를 선언하고, DBConnectionPool로부터 커넥션 객체를 꺼냅니다.
	//Connection connection = null;
	//connection = connPool.getConnection();
	/*DBConnectionPool connPool;
	public void setDbConnectionPool(DBConnectionPool connPool) {
		this.connPool = connPool;
	}*/
	
	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		connection = ds.getConnection();

		stmt = connection.createStatement();
		rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

		ArrayList<Member> members = new ArrayList<Member>();

		while (rs.next()) {
			members.add(new Member().setNo(rs.getInt("MNO")).setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL")).setCreatedDate(rs.getDate("CRE_DATE")));
	
		}
		
		return members;

	}

	public int delete(int no) throws Exception {
		Connection connection = null;
		// 회원 삭제
		PreparedStatement stmt = null;
		
		connection = ds.getConnection();
		stmt = connection.prepareStatement("delete from members where mno=" + no);
		stmt.executeUpdate();
		return no;
	}

	public Member selectOne(int no) throws Exception {
		Connection connection = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery(
	          "SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
	              " WHERE MNO=" + no);    
	      if (rs.next()) {
	        return new Member()
	        .setNo(rs.getInt("MNO"))
	        .setEmail(rs.getString("EMAIL"))
	        .setName(rs.getString("MNAME"))
	        .setCreatedDate(rs.getDate("CRE_DATE"));

	      } else {
	        throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
	      }

	    } catch (Exception e) {
	      throw e;
	    } finally {
	      try {if (rs != null) rs.close();} catch(Exception e) {}
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }

	public int update(Member member) throws Exception {
		Connection connection = null;
		// 회원 정보 변경
		Statement stmt = null;
		ResultSet rs = null;

		connection = ds.getConnection();
		stmt = connection.createStatement();

		rs = stmt.executeQuery("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + " WHERE MNO=" + member.getNo());

		while (rs.next()) {

			member.setNo(rs.getInt("MNO")).setNo(rs.getInt("MNO")).setEmail(rs.getString("EMAIL"))
					.setName(rs.getString("MNAME")).setCreatedDate(rs.getDate("CRE_DATE"));
		}
		return 0;
	}

	public int insert(Member member) throws Exception {
		Connection connection = null;
		// 회원 등록
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(?,?,?,NOW(), NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Member exist(String email, String password) throws Exception {
		// 있으면 Member 객체 리턴, 없으면 null 리턴
		return null;

	}
}
