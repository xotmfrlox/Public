package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;

import spms.vo.Member;

@Component("memberDao") // value 생략 가능;
public class MySqlMemberDao implements MemberDao {

	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select mno, mname, email, cre_date from members order by mno asc");

			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(
						new Member()
						.setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")));

			}

			return members;
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
			rs = stmt.executeQuery("SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + " WHERE MNO=" + no);
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

	public int update(Member member) throws Exception {
		Connection connection = null;
		// 회원 정보 변경
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now() WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
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
