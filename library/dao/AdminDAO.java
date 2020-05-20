package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "library_manager";
    private String password = "asdf";

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public AdminDAO() {
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

    public int adminLogin(String id, char[] pw) {

        int sw = 0;

        getConnection();

        String sql = "select admin_id, admin_pw from admin_table";

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (id.equals(rs.getString("admin_id")) && new String(pw).equals(rs.getString("admin_pw"))) {
                    sw = 1;

                } else if (!(id.equals(rs.getString("admin_id")))
                        || !(new String(pw).equals(rs.getString("admin_pw")))) {
                    sw = 2;

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
    }

}
