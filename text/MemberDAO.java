package text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "c##java";
    private String password = "bit";
    
    public MemberDAO() {
        try {
            Class.forName(driver);
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
    }
    
    public void getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    
    
    
}
