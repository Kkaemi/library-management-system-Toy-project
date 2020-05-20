package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class Member extends JFrame {
    private JPanel panel1, panel2;
    private JLabel titleL, imgL, updateImg, updateL, btnImg, btnL;
    private JTextField nameT, idT, phoneT;
    private JButton updateBtn,  btn2;
    
    String driver = "oracle.jdbc.driver.OracleDriver";
       private String url = "jdbc:oracle:thin:@localhost:1521:xe";
       private String username = "c##java";
       private String password = "bit";

       private Connection conn;
       private PreparedStatement pstmt;
       private ResultSet rs;
      

    public Member() {
        
        try { // "OracleDriver.class" 생성
             Class.forName(driver);// 파일을 클레스 타입으로 생성 모든 파일 경로를 표시해야함
             System.out.println("드라이버 로딩 성공");
          } catch (ClassNotFoundException e) {
             e.printStackTrace();
          }
        //-------------------------------------------------------------------------
        panel1 = new JPanel();
        panel1.setBackground(new Color(176, 224, 230));
        panel1.setBounds(0, 0, 382, 145);
        getContentPane().add(panel1);
        panel1.setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(176, 224, 230));
        panel2.setBounds(0, 547, 384, 56);
        getContentPane().add(panel2);

        titleL = new JLabel("Membership");
        titleL.setBounds(0, 0, 384, 145);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setForeground(Color.WHITE);
        titleL.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        panel1.add(titleL);
        //-------------------------------------------------------------------------
        imgL = new JLabel(new ImageIcon("icon\\member.jpg"));
        imgL.setBounds(40,190,129,121);
        getContentPane().add(imgL);
        
        nameT = new JTextField("이름");
        nameT.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        nameT.setColumns(10);
        nameT.setBorder(new EmptyBorder(0, 0, 0, 0));
        nameT.setBounds(200, 190, 140, 30);
        getContentPane().add(nameT);
        
        
        idT = new JTextField("아이디");
        idT.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        idT.setColumns(10);
        idT.setBorder(new EmptyBorder(0, 0, 0, 0));
        idT.setBounds(200, 230, 140, 30);
        getContentPane().add(idT);
        
        
        phoneT = new JTextField("연락처");
        phoneT.setFont(new Font("맑은 고딕", Font.BOLD, 17));
        phoneT.setColumns(10);
        phoneT.setBorder(new EmptyBorder(0, 0, 0, 0));
        phoneT.setBounds(200, 270, 140, 30);
        getContentPane().add(phoneT);
        
        //-------------------------------------------------------------------------
        
        updateImg = new JLabel(new ImageIcon("icon\\update.jpg"));
        updateImg.setBounds(55, 387, 30, 30);
        getContentPane().add(updateImg);
        
        updateL = new JLabel("회원정보 수정");
        updateL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        updateL.setBounds(94, 375, 117, 30);
        getContentPane().add(updateL);
        
        updateBtn = new JButton("    비밀번호 등 내정보를 변경하세요.");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InformationModify();
            }
        });
        updateBtn.setBackground(Color.GRAY);
        updateBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        updateBtn.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
        //updateBtn.setBorderPainted(false);//-------------------------버튼 테두리 지움 
        updateBtn.setFocusPainted(false);// 클릭했을떄 선 지움
        updateBtn.setBounds(0, 365, 382, 90);
        getContentPane().add(updateBtn);
        
        btnImg = new JLabel(new ImageIcon("icon\\bbb.jpg"));
        btnImg.setBounds(55, 482, 30, 30);
        getContentPane().add(btnImg);
        
        btnL = new JLabel("회원탈퇴");
        btnL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnL.setBounds(93, 470, 117, 30);
        getContentPane().add(btnL);
        
        btn2 = new JButton("          더 이상 이용하지 않을 경우 진행해주세요.");
        btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        btn2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btn2.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
        btn2.setBorderPainted(false);//-------------------------버튼 테두리 지움 
        btn2.setFocusPainted(false);// 클릭했을떄 선 지움
        btn2.setBounds(0, 470, 382, 70);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String passWord = JOptionPane.showInputDialog(null,"탈퇴하시려면 비밀번호를 입력하세요","회원 탈퇴");
               
               
               
            }
         });
        getContentPane().add(btn2);
        getContentPane().setLayout(null);
        
        
        
        //-------------------------------------------------------------------------
        
        getContentPane().setBackground(Color.WHITE);
        setTitle("회원정보");
        setVisible(true);
        setBounds(500, 200, 400, 650);
        getContentPane().setLayout(null);


    }//Member()
    public void getConnection() {
          try {
             conn = DriverManager.getConnection(url, username, password);// url 주소 ,오라클 아이디 , 오라클 비번
             System.out.println("접속 성공");

          } catch (SQLException e) {

             e.printStackTrace();
          }
       }
    public static void main(String[] args) {
        new Member();
    }
}

