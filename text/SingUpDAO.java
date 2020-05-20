package text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingUpDAO {
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "c##java";
    private String password = "bit";
    
    public SingUpDAO() {
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
    
    public int singUpInput(SingUpDTO singUpDTO) {
        int su = 0;
        getConnection();
        String sql = "insert into member values(?,?,?,?,?,?,?)";
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, singUpDTO.getId());
            pstmt.setString(2, singUpDTO.getPass());
            pstmt.setString(3, singUpDTO.getPassc());
            pstmt.setString(4, singUpDTO.getName());
            pstmt.setString(5, singUpDTO.getTel1());
            pstmt.setString(6, singUpDTO.getTel2());
            pstmt.setString(7, singUpDTO.getTel3());
            
            su = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }finally {
            
            try {
             //   if(rs != null)rs.close();
                if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                if(conn != null) conn.close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
            return su;
        
    }
       
        
        
        
        
                
    }
    
    
    
}
