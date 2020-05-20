package text;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class IdFind extends JFrame {
   private JLabel nameL, phoneL, phoneL1, phoneL2, lblNewLabel_1;
   private JButton okBtn, cancelBtn;
   private JPanel panel;
   private JTextField nameT, phoneT1, phoneT2;
   private JComboBox<String> combo;
   

   String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String username = "c##java";
   private String password = "bit";

   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   

   public IdFind() {
      try { // "OracleDriver.class" 생성
         Class.forName(driver);// 파일을 클레스 타입으로 생성 모든 파일 경로를 표시해야함
         System.out.println("드라이버 로딩 성공");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      getContentPane().setBackground(Color.WHITE);

      panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(176, 224, 230));
      panel.setBounds(0, 0, 384, 145);
      getContentPane().add(panel);

      JLabel label = new JLabel("Id Search");
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setForeground(Color.WHITE);
      label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
      label.setBounds(0, 0, 384, 145);
      panel.add(label);
      //--------------------------------------------------
      JLabel imgL = new JLabel(new ImageIcon("icon\\id.png"));
         imgL.setBounds(40, 180, 30, 30);
         getContentPane().add(imgL);
      
      lblNewLabel_1 = new JLabel("아이디 찾기");
      lblNewLabel_1.setForeground(Color.DARK_GRAY);
      lblNewLabel_1.setBounds(80, 190, 118, 18);
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      getContentPane().add(lblNewLabel_1);

      nameL = new JLabel("이    름");
      nameL.setBounds(70, 240, 70, 33);
      nameL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      nameL.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(nameL);

      nameT = new JTextField(" 이름을 입력해주세요.");
      nameT.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      nameT.setColumns(10);
      nameT.setOpaque(false);// 텍스트필드 컬러 삭제
      nameT.setBorder(new EmptyBorder(0, 0, 0, 0));
      //nameT.setBounds(new Rectangle(3, 3, 3, 3));
      //nameT.setBorder(new LineBorder(new Color(190,190,190)));
      nameT.setBounds(100, 280, 200, 33);
      getContentPane().add(nameT);
      nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            nameT.setText("");
         }
      });

      phoneL = new JLabel("핸드폰 번호");
      phoneL.setBounds(70, 330, 100, 33);
      phoneL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      phoneL.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(phoneL);

      String[] phone = { "010", "019", "018", "017" };
      combo = new JComboBox(phone);
      combo.setBounds(100, 380, 55, 30);
      getContentPane().add(combo);
      
      phoneL1 = new JLabel("-");
      phoneL1.setBounds(170, 380, 20, 30);
      getContentPane().add(phoneL1);

      phoneT1 = new JTextField("번호");
      phoneT1.setColumns(5);
      phoneT1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      phoneT1.setOpaque(false);// 텍스트필드 컬러 삭제
      phoneT1.setBorder(new EmptyBorder(0, 0, 0, 0));
      // phoneT1.setBounds(new Rectangle(3, 3, 3, 3));
      // phoneT1.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      phoneT1.setBounds(190, 380, 53, 33);
      getContentPane().add(phoneT1);
      phoneT1.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            phoneT1.setText("");
         }
      });
      
      phoneL2 = new JLabel("-");
      phoneL2.setBounds(240, 380, 20, 30);
      getContentPane().add(phoneL2);

      phoneT2 = new JTextField("입력");
      phoneT2.setColumns(5);
      phoneT2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      phoneT2.setOpaque(false);// 텍스트필드 컬러 삭제
      phoneT2.setBorder(new EmptyBorder(0, 0, 0, 0));
      // phoneT2.setBounds(new Rectangle(3, 3, 3, 3));
      // phoneT2.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      phoneT2.setBounds(260, 380, 53, 33);
      getContentPane().add(phoneT2);
      phoneT2.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            phoneT2.setText("");
         }
      });
      

      okBtn = new JButton("확인");
      okBtn.setBounds(80, 465, 100, 40);
      okBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
      okBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
      getContentPane().add(okBtn);
      okBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getConnection();
            String sql = "select * from member";

            try {
               pstmt = conn.prepareStatement(sql);
               rs = pstmt.executeQuery();

               int sw = 0;
               while (rs.next()) {
                  System.out.println(nameT.getText() + "," + combo.getSelectedItem() + "," + phoneT1.getText()
                        + "," + phoneT2.getText());
                  System.out.println(rs.getString("id"));
                  if (nameT.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "정보를 입력해주세요 .", "아이디 찾기", JOptionPane.ERROR_MESSAGE);
                     return;// 돌아가
                  } else if (rs.getString("name").equals(nameT.getText())
                        && rs.getString("tel1").equals(combo.getSelectedItem())
                        && rs.getString("tel2").equals(phoneT1.getText())
                        && rs.getString("tel3").equals(phoneT2.getText())) {
                     JOptionPane.showMessageDialog(null,
                           "        " + rs.getString("name") + "님의 아이디는 " + rs.getString("id") + " 입니다.",
                           "아이디 찾기", JOptionPane.PLAIN_MESSAGE);
                     sw = 1;// 정보가 들어왔으니 1,

                     new Login();
                     break;
                  }
               }

               if (sw == 0)
                  JOptionPane.showMessageDialog(null, "잘못입력하셨습니다.", "아이디 찾기", JOptionPane.YES_NO_OPTION);

            } catch (SQLException e1) {

               e1.printStackTrace();
            } finally {

               try {
                  if (rs != null)
                     rs.close(); // rs도 닫아줘야함
                  if (pstmt != null)
                     pstmt.close(); // 닫아주기 거꾸로
                  if (conn != null)
                     conn.close();
               } catch (SQLException e2) {

                  e2.printStackTrace();
               }

            }

         }
      });

      cancelBtn = new JButton("취소");
      cancelBtn.setBounds(210, 465, 100, 40);
      cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
      cancelBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
      getContentPane().add(cancelBtn);
      cancelBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?", "아이디 찾기", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
               new Login();
            }
         }
      });

      JPanel panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBackground(new Color(176, 224, 230));
      panel_1.setBounds(0, 547, 384, 56);
      getContentPane().add(panel_1);

      getContentPane().setLayout(null);
      
      
      setTitle("아이디 찾기");
      setBounds(500, 200, 400, 650);
      setVisible(true);
      

   }// IdFind

   public void getConnection() {
      try {
         conn = DriverManager.getConnection(url, username, password);// url 주소 ,오라클 아이디 , 오라클 비번
         System.out.println("접속 성공");

      } catch (SQLException e) {

         e.printStackTrace();
      }
   }
   
   
}
