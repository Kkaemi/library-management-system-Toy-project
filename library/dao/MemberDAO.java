package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.bean.MemberDTO;

public class MemberDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "library_manager";
	private String password = "asdf";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// constructor
	public MemberDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// getConnection
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// mebmerLogin
	public int memberLogin(String id, char[] pw) {
		int sw = 0;
		getConnection();
		String sql = "select member_id, member_pw from member_table";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (id.equals(rs.getString("member_id")) && new String(pw).equals(rs.getString("member_pw"))) {
					sw = 1;
					break;
				} else if (id.equals(rs.getString("member_id")) && !(new String(pw).equals(rs.getString("member_pw")))
						&& !(new String(pw).equals(""))) {
					sw = 2;

				} else if (id.equals("")) {
					sw = 3;
					break;
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {

				e2.printStackTrace();
			}

		} // try catch finally
		return sw;
	}// memberLogin

	// getSeq
	public int getSeq() {
		int seq = 0;

		getConnection();

		String sql = "select seq_member.nextval from dual";

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				seq = rs.getInt("nextval");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} // try catch finally
		return seq;
	}

	// insertMember
	public int insertMember(MemberDTO memberDTO) {
		int su = 0;
		getConnection();
		String sql = "insert into member_table values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberDTO.getSeq());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPw());
			pstmt.setString(4, memberDTO.getName());
			pstmt.setInt(5, memberDTO.getGender());
			pstmt.setInt(6, memberDTO.getBirth());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());

			su = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return su;
	}// insertMember
	
	// findMemberById
	public MemberDTO findMemberById(String id) {
		MemberDTO memberDTO = new MemberDTO();
		
		getConnection();
		
		String sql = "select member_name, member_birth from member_table where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberDTO.setName(rs.getString("member_name"));
				memberDTO.setBirth(rs.getInt("member_birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberDTO;
	}

	// idCheck
	public int idCheck(String id) {// id 중복체크
		int sw = 0;

		getConnection();

		String sql = "select member_id from member_table";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.getRow() == 0 && !(id == null || id.length() == 0)) {
				sw = 1;
			}

			while (rs.next()) {
				if (id == null || id.length() == 0) {
					sw = 0;
				} else {
					if (id.equals(rs.getString("member_id"))) {
						sw = 2;
						break;
					} else if (!id.equals(rs.getString("member_id"))) {
						sw = 1;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} // try catch finally
		return sw;
	}// idCheck

	// pwCheck
	public int pwCheck(char[] pw1, char[] pw2) {
		int sw = 0;

		if (pw1 == null || pw1.length == 0 || pw2 == null || pw2.length == 0) {
			return sw;
		} else {

			for (int i = 0; i < pw1.length; i++) {
				if (pw1.length == pw2.length) {
					if (pw1[i] == pw2[i]) {
						sw = 1;
					} else {
						sw = 2;
						break;
					}
				} else {
					sw = 2;
				}
			}
		}
		return sw;

	}// passWordCheck

}

