package text;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MemberDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private MemberDTO memberDTO = new MemberDTO();
    
    
    
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
    
    public int memberDelete(MemberDTO memberDTO) {
        int su = 0;
        
        getConnection();
        
        String sql = "delete member where id = ? and pass= ?";
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberDTO.getId());
            pstmt.setString(2, memberDTO.getPass());
           
            
            su = pstmt.executeUpdate();
            System.out.println(su+"개의 행이 삭제하였습니다.");
            
         if(su == 0) {
             JOptionPane.showMessageDialog(null,"회원의 아이디 혹은 비밀번호가 틀렸습니다.","관리자",JOptionPane.ERROR_MESSAGE);
             return su;
         }else {
             JOptionPane.showMessageDialog(null,"회원을 탈퇴하였습니다.","관리자",JOptionPane.INFORMATION_MESSAGE);
             
             JOptionPane.showMessageDialog(null,"시스템이 종료됩니다.","관리자",JOptionPane.INFORMATION_MESSAGE);
              System.exit(0);
              
            
         }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }finally {//오류 안나면 그냥 리스트 값이 리턴된다.
            
            try {
                if(rs != null)rs.close();         //rs도 닫아줘야함
                if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                if(conn != null) conn.close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        
    }
        return su;
    }
    
    public MemberDTO memberSearch(MemberDTO memberDTO) {
        int sw = 0;
        
        getConnection();
        
        String sql = "select id, name, tel1, tel2, tel3 from member where id = ? ";
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, memberDTO.getId());
            
            
            rs = pstmt.executeQuery();
            
  
            while(rs.next()) {
            memberDTO.setId(rs.getString("id"));
            memberDTO.setName(rs.getString("name"));
            memberDTO.setTel1(rs.getString("tel1"));
            memberDTO.setTel2(rs.getString("tel2"));
            memberDTO.setTel3(rs.getString("tel3"));
            
            
              
            }
            
            if(memberDTO.getId() == null) {
                return memberDTO;
            }
            
           if(memberDTO.getName() == null) {
               JOptionPane.showMessageDialog(null, "아이디가 없습니다 .","회원정보",JOptionPane.ERROR_MESSAGE);
           }
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }finally {
            
            try {
                if(rs != null)rs.close();
                if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                if(conn != null) conn.close();
            } catch (SQLException e) {
                
                e.printStackTrace();
            }
        
        }
        return memberDTO;
    }
    

    
}
