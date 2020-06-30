package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String url;
	String username;
	String password;
	
	//Connection 객체 보관할 ArrayList 준비
	ArrayList<Connection> connList = new ArrayList<Connection>();

	//생성자에서는 DB 커넥션 생성에 필요한 값을 매개변수롤 받기
	public DBConnectionPool(String driver, String url, String username, String password) throws Exception {
		this.url = url;
		this.username = username;
		this.password = password;

		Class.forName(driver);
	}
	
	//getConnection() 메서드
	//DB 커넥션을 달라고 요청받으면, ArrayList에 들어 있는 것을 꺼내 줍니다. 
	//db 커넥션 객체도 일정 시간이 지나면 서버와의 연결이 끊어지기 때문에 유효성 체크를 한 다음에 반환합니다.
	public Connection getConnection() throws Exception {
		if (connList.size() > 0) {
			Connection conn = connList.remove(0);
			if (conn.isValid(10)) {
				return conn;
			}
		}
		//ArrayList에 보관된 객체가 없다면, DriverManager를 통해 새로 만들어 반환
		return DriverManager.getConnection(url, username, password);
	}
	
	//returnConnection() 메서드
	//커넥션 객체를 쓰고 난 다음에 이 메서드를 호출하여 커넥션 풀에 반환 그래 다음에 다시 사용 가능
	public void returnConnection(Connection conn) throws Exception {
		connList.add(conn);
	}
	
	// 웹 어플리케이션을 종료하기 전에 이 메서드 호출하여 db와 연결된 것 모두 끊기
	public void closeAll() {
		for (Connection conn : connList) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
