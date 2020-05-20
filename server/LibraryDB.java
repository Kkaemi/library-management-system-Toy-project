package server;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class LibraryDB {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "library_manager";
	private String password = "asdf";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	   public void LibraryDB(){
			try {
				Class.forName(driver);
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
		
		public int clickToDB(InfoDTO libraryDTO) {
			
			int su=0;
			
			getConnection();
			
			String sql = "update java_seat_table set seat_check=?"
					   + ", member_id=? where seat_num = ?";
			
			
	
				try {
					pstmt = conn.prepareStatement(sql);
					
					
					
					
					pstmt.setInt(1,libraryDTO.getSeatCheck());
					pstmt.setString(2,libraryDTO.getMemberId());
					pstmt.setInt(3,libraryDTO.getSeatNum());
					
					
					
					su = pstmt.executeUpdate();
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			
			
			return su; //su가 1이면 dialog 출력!
				
			
			
		}
		
		
		
		
		
		public List<DbDTO> getLibDB() {
			List<DbDTO> list = new ArrayList<DbDTO>();
			
			getConnection();
			String sql = "select * from java_seat_table";
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					DbDTO dbDTO = new DbDTO();
//					

					dbDTO.setSeatCheck(rs.getInt("seat_check"));
					dbDTO.setSeatNum(rs.getInt("seat_num"));
					
					list.add(dbDTO);
					
				}
				
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				list = null; 
			}finally {
	            try {
	                if(pstmt != null) pstmt.close();
	                if(conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
			
			
			
			
			
			return list;
		
		}
		

		
		
		
		

}
