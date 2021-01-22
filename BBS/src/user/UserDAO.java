package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbID = "dbID"; // 자기의 dbID
			String dbPassword = "dbPassword"; // 자기의 dbPassword
			String dbURL = "jdbc:oracle:thin:@localhost:1521:dbListener"; // 자신의 dbListener
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) { // 로그인 기능 구현
		String SQL = "SELECT userPassword FROM USERS WHERE userID = ?";
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1,userID);
			rs = pstm.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(Users user) {
		String SQL = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, user.getUserID());
			pstm.setString(2, user.getUserPassword());
			pstm.setString(3, user.getUserName());
			pstm.setString(4, user.getUserGender());
			pstm.setString(5, user.getUserEmail());
			return pstm.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}
