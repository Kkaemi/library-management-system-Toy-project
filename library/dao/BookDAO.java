package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import library.bean.BookDTO;
import library.bean.RentDTO;
import text.BlackDTO;

public class BookDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "library_manager";
	private String password = "asdf";
	private String[] genre = { "전체", "소설", "만화", "역사", "과학", "예술", "문학", "철학", "종교" };

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BookDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// constructor

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// getConnection

	// getSeq
	public int getSeq() {
		int seq = 0;

		getConnection();

		String sql = "select seq_book.nextval from dual";

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

	public List<BookDTO> userBookSearch(String str) {
		List<BookDTO> list = new ArrayList<BookDTO>();
		getConnection();
		String sql = "select * from book_table";
		if (str.equals("전체")) {
			list = bookList();
			return list;
		}
		if (Arrays.asList(genre).contains(str))
			sql += " where book_genre = ?";
		else {
			sql += " where book_name like ?";
			str = "%" + str + "%";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			rs = pstmt.executeQuery();

			while (rs.next()) {// record가 더이상 없을 때 까지 true를 반환한다
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("book_seq"));
				bookDTO.setName(rs.getString("book_name"));
				bookDTO.setAuthor(rs.getString("book_author"));
				bookDTO.setPublisher(rs.getString("book_publisher"));
				bookDTO.setGenre(rs.getString("book_genre"));
				bookDTO.setCheck(rs.getInt("book_check"));

				list.add(bookDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return list;
	}// userBookSearch
	
	public List<BookDTO> userBookSearch(int searchOption, String str) {
		List<BookDTO> list = new ArrayList<BookDTO>();
		getConnection();
		String sql = "select * from book_table";
		if (str.equals("전체")) {
			list = bookList();
			return list;
		}
		if (Arrays.asList(genre).contains(str))
			sql += " where book_genre = ?";
		else if (searchOption == 1){
			sql += " where book_author like ?";
			str = "%" + str + "%";
		} else if (searchOption == 2) {
			sql += " where book_publisher like ?";
			str = "%" + str + "%";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			rs = pstmt.executeQuery();

			while (rs.next()) {// record가 더이상 없을 때 까지 true를 반환한다
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("book_seq"));
				bookDTO.setName(rs.getString("book_name"));
				bookDTO.setAuthor(rs.getString("book_author"));
				bookDTO.setPublisher(rs.getString("book_publisher"));
				bookDTO.setGenre(rs.getString("book_genre"));
				bookDTO.setCheck(rs.getInt("book_check"));

				list.add(bookDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return list;
	}// userBookSearch

	public List<BookDTO> bookList() {
		List<BookDTO> list = new ArrayList<BookDTO>();
		getConnection();
		String sql = "select * from book_table";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {// record가 더이상 없을 때 까지 true를 반환한다
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("book_seq"));
				bookDTO.setName(rs.getString("book_name"));
				bookDTO.setAuthor(rs.getString("book_author"));
				bookDTO.setPublisher(rs.getString("book_publisher"));
				bookDTO.setGenre(rs.getString("book_genre"));
				bookDTO.setCheck(rs.getInt("book_check"));

				list.add(bookDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return list;
	}

	public int bookDelete(int seq) {
		int su = 0;
		
		BookDTO bookDTO = new BookDTO();
		bookDTO = findBookByCode(seq+"");
		su = deletedBookInsert(bookDTO);
		
		getConnection();
		String sql = "delete book_table where book_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return su;
	}

	private int deletedBookInsert(BookDTO bookDTO) {
		int su = 0;
		getConnection();
		String sql = "insert into deleted_books values(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookDTO.getSeq());
			pstmt.setString(2, bookDTO.getName());
			pstmt.setString(3, bookDTO.getAuthor());
			pstmt.setString(4, bookDTO.getPublisher());
			pstmt.setString(5, bookDTO.getGenre());
			pstmt.setInt(6, bookDTO.getCheck().equals("대여가능") ? 0 : 1);
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return su;
	}

	public int bookInsert(BookDTO bookDTO) {
		int su = 0;
		getConnection();
		String sql = "insert into book_table values(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookDTO.getSeq());
			pstmt.setString(2, bookDTO.getName());
			pstmt.setString(3, bookDTO.getAuthor());
			pstmt.setString(4, bookDTO.getPublisher());
			pstmt.setString(5, bookDTO.getGenre());
			pstmt.setInt(6, bookDTO.getCheck().equals("대여가능") ? 0 : 1);

			su = pstmt.executeUpdate();

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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return su;
	}

	public int bookUpdate(BookDTO bookDTO) {
		int su = 0;

		getConnection();

		String sql = "update book_table set book_name = ?" + ", book_author = ?" + ", book_publisher = ?"
				+ ", book_genre = ? where book_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookDTO.getName());
			pstmt.setString(2, bookDTO.getAuthor());
			pstmt.setString(3, bookDTO.getPublisher());
			pstmt.setString(4, bookDTO.getGenre());
			pstmt.setInt(5, bookDTO.getSeq());

			su = pstmt.executeUpdate();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return su;
	}

	public BookDTO findBookByCode(String code) {
		BookDTO bookDTO = new BookDTO();

		getConnection();

		String sql = "select * from book_table where book_seq = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				bookDTO.setSeq(rs.getInt("book_seq"));
				bookDTO.setName(rs.getString("book_name"));
				bookDTO.setAuthor(rs.getString("book_author"));
				bookDTO.setPublisher(rs.getString("book_publisher"));
				bookDTO.setGenre(rs.getString("book_genre"));
				bookDTO.setCheck(rs.getInt("book_check"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally

		return bookDTO;
	}

	// bookRent
	public RentDTO bookRent(String member_id, String book_seq) {
		RentDTO rentDTO = new RentDTO();

		getConnection();

		String sql = "select member_id, member_name, book_seq, book_name, to_char(sysdate,'yyyy.mm.dd') as rent_date"
				+ ", to_char(sysdate+7,'yyyy.mm.dd') as return_date" + " from member_table, book_table"
				+ " where member_id = ? and book_seq = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, book_seq);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rentDTO.setMember_id(rs.getString("member_id"));
				rentDTO.setMember_name(rs.getString("member_name"));
				rentDTO.setBook_seq(rs.getInt("book_seq"));
				rentDTO.setBook_name(rs.getString("book_name"));
				rentDTO.setRentDate(rs.getString("rent_date"));
				rentDTO.setReturnDate(rs.getString("return_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return rentDTO;
	}

	// rentList
	public List<RentDTO> rentList() {
		List<RentDTO> list = new ArrayList<RentDTO>();
		
		getConnection();
		
		String sql = "select * from rental_table";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {// record가 더이상 없을 때 까지 true를 반환한다
				RentDTO rentDTO = new RentDTO();
				rentDTO.setMember_id(rs.getString("member_id"));
				rentDTO.setMember_name(rs.getString("member_name"));
				rentDTO.setBook_seq(rs.getInt("book_seq"));
				rentDTO.setBook_name(rs.getString("book_name"));
				rentDTO.setRentDate(rs.getString("rental_rental"));
				rentDTO.setReturnDate(rs.getString("rental_return"));

				list.add(rentDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return list;
	}

	// rentInsert
	public int rentInsert(RentDTO rentDTO) {
		int su = 0;
		
		getConnection();
		
		String sql = "insert into rental_table values(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rentDTO.getMember_id());
			pstmt.setString(2, rentDTO.getMember_name());
			pstmt.setInt(3, rentDTO.getBook_seq());
			pstmt.setString(4, rentDTO.getBook_name());
			pstmt.setString(5, rentDTO.getRentDate());
			pstmt.setString(6, rentDTO.getReturnDate());
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		
		return su;
	}

	public int rentBook(int book_seq) {
		int su = 0;
		
		int check = rentCheck(book_seq);
		
		if (check == 1) {
			return su;
		}
		
		getConnection();
		
		String sql = "update book_table set book_check = 1 where book_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_seq);
			
			su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return su;
	}

	private int rentCheck(int book_seq) {
		int check  = 0;
		
		getConnection();
		
		String sql = "select book_check from book_table where book_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_seq);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				check = rs.getInt("book_check");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return check;
	}

	public void returnBook(int bookCode) {
		
		getConnection();
		
		String sql = "update book_table set book_check = 0 where book_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookCode);
			
			int su = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		deleteRentTableRow(bookCode);
	}

	private void deleteRentTableRow(int bookCode) {
		getConnection();
		
		String sql = "delete rental_table where book_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookCode);
			
			int su = pstmt.executeUpdate();
			
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<BookDTO> deletedBookList() {
		List<BookDTO> list = new ArrayList<BookDTO>();
		getConnection();
		String sql = "select * from deleted_books";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {// record가 더이상 없을 때 까지 true를 반환한다
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(rs.getInt("deleted_book_seq"));
				bookDTO.setName(rs.getString("deleted_book_name"));
				bookDTO.setAuthor(rs.getString("deleted_book_author"));
				bookDTO.setPublisher(rs.getString("deleted_book_publisher"));
				bookDTO.setGenre(rs.getString("deleted_book_genre"));
				bookDTO.setCheck(rs.getInt("deleted_book_check"));

				list.add(bookDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {// 반드시 close를 해준다!
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // try catch finally
		return list;
	}
	
    public List<BlackDTO> blackList() {
    	List<BlackDTO> list = new ArrayList<BlackDTO>();
    	
    	
    	getConnection();
    	
    	 String sql = "select * from black_table";
    	 try {
    	 
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
        	 BlackDTO blackDTO = new BlackDTO();
        	 blackDTO.setId(rs.getString("member_id"));
        	 blackDTO.setName(rs.getString("member_name"));
        	 blackDTO.setBookSeq(rs.getInt("book_seq"));
        	 blackDTO.setMemSeq(rs.getInt("member_seq"));
        	 blackDTO.setBookName(rs.getString("book_name"));
        	 blackDTO.setOverdue(rs.getInt("overdue_days"));
        	 
        	 list.add(blackDTO);
        	 
         	}
    	 }catch (SQLException e) {
             e.printStackTrace();
         } finally {// 반드시 close를 해준다!
             try {
                 if (rs != null)
                     rs.close();
                 if (pstmt != null)
                     pstmt.close();
                 if (conn != null)
                     conn.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         } 
    	
    	
    	
    	
    	
    	return list;
    }
}
