package text;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewPw extends JFrame implements ActionListener, Runnable {// implements ActionListener, Runnable
   private JLabel newL1, newL2, passL;
   // private JTextField newT1, newT2;
   private JButton okBtn, cancelBtn;
   private SingUpDAO singUpDAO = new SingUpDAO();
   private SingUpDTO singUpDTO = new SingUpDTO();
   private JPasswordField newT1, newT2;
   private String id;

   private Thread t;

   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;

   String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String username = "c##java";
   private String password = "bit";
   
   private PwFind pwFind;

   public NewPw(PwFind pwFind) {
      this.pwFind = pwFind;

      try { // "OracleDriver.class" 생성
         Class.forName(driver);// 파일을 클레스 타입으로 생성 모든 파일 경로를 표시해야함
         System.out.println("드라이버 로딩 성공");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      newL1 = new JLabel("새 비밀번호 ");
      newL1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      newL1.setBounds(40, 40, 155, 30);
      getContentPane().add(newL1);

      newT1 = new JPasswordField();
      newT1.setOpaque(false);
      newT1.setColumns(10);
      newT1.setBounds(75, 75, 225, 30);
      getContentPane().add(newT1);
      newT1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            newT1.setText("");
         }
      });

      newL2 = new JLabel("새비밀번호 확인 ");
      newL2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      newL2.setBounds(40, 110, 155, 30);
      getContentPane().add(newL2);

      newT2 = new JPasswordField();
      newT2.setOpaque(false);
      newT2.setColumns(10);
      newT2.setBounds(75, 150, 225, 30);
      getContentPane().add(newT2);
      newT2.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            newT2.setText("");
         }
      });

      passL = new JLabel();
      passL.setBounds(175, 110, 225, 30);
      getContentPane().add(passL);

      okBtn = new JButton("확인");
      okBtn.setBounds(80, 200, 100, 40);
      okBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
      okBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
      getContentPane().add(okBtn);
      okBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            pwFind.setVisible(false);
         }
      });

      cancelBtn = new JButton("취소");
      cancelBtn.setBounds(210, 200, 100, 40);
      cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
      cancelBtn.setBorderPainted(false);
      getContentPane().add(cancelBtn);

      okBtn.addActionListener(this);

      t = new Thread(this);
      t.start();

      getContentPane().setBackground(Color.WHITE);
      setBounds(600, 500, 380, 350);
      getContentPane().setLayout(null);
      setTitle("새비밀번호");
      setVisible(true);

   }

   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);// url 주소 ,오라클 아이디 , 오라클 비번

      } catch (SQLException e) {

         e.printStackTrace();
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String pass = new String(newT1.getPassword());
      String passc = new String(newT2.getPassword());
      if (e.getSource() == okBtn) {

         if (newT1.getPassword() == null || pass.length() == 0 && newT2.getPassword() == null
               || passc.length() == 0) {
            JOptionPane.showMessageDialog(null, "    정보를 입력하세요 .", "비밀번호", JOptionPane.ERROR_MESSAGE);

         } else {
            SingUpDTO singUpDTO = new SingUpDTO();

            singUpDTO.setId(id);
            singUpDTO.setPass(pass);
            singUpDTO.setPassc(passc);

            int su = singUpDAO.pwUpdate(singUpDTO);

            JOptionPane.showMessageDialog(null, "비밀번호 변경이 완료되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
         }

         newT1.setText("");
         newT2.setText("");

      } // if
   }// action

   public void run() {
      while (true) {
         passWordCheck();
      }
   }

   public void passWordCheck() {// 비밀번호 중복체크

      if (newT1.getPassword() == null || newT1.getPassword().length == 0 || newT2.getPassword() == null
            || newT2.getPassword().length == 0) {
         passL.setText("");
         return;
      } else {

         if (new String(newT1.getPassword()).equals(new String(newT2.getPassword()))) {
            passL.setText("PassWord가 같습니다.");
            passL.setForeground(Color.blue);

         } else if (!new String(newT1.getPassword()).equals(new String(newT2.getPassword()))) {
            passL.setText("PassWord가 다릅니다.");
            passL.setForeground(Color.red);

         }
      }

   }

   public void setId(String id) {
      this.id = id;
   }

}
