package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
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
	
	public String getDate() {
		String SQL = "SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') FROM DUAL";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}
	
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1; // 그 다음 게시글의 번호가 들어갈 수 있게 1을 더함
			}
			return 1; // 첫 번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, getNext());
			pstm.setString(2, bbsTitle);
			pstm.setString(3, userID);
			pstm.setString(4, getDate());
			pstm.setString(5, bbsContent);
			pstm.setInt(6, 1); // 글이 보여지는 형태가 되야하므로 bbsAvailable은 1임
			return pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM (SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC) WHERE ROWNUM <= 10";
		// 삭제가 되지 않아 bbsAvailable이 1인 글만 가져옴, 글을 10개 까지만 가져옴
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) { // 페이징 처리를 위한 함수
		String SQL = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstm.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Bbs getBbs(int bbsID) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, bbsID);
			rs = pstm.executeQuery();
			if (rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				return bbs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setString(1, bbsTitle);
			pstm.setString(2, bbsContent);
			pstm.setInt(3, bbsID);
			return pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	public int delete(int bbsID) {
		String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
		try {
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, bbsID);
			return pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
}
