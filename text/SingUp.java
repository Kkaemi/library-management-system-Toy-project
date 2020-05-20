package text;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SingUp implements ActionListener, Runnable{
    private JTextField idT, nameT, phoneNumberT, phoneNumber2T;
    private JButton inputB, cancelB;
    private JComboBox comboBox;
    private SingUpDAO singUpDAO = new SingUpDAO();
    private SingUpDTO singUpDTO = new SingUpDTO();
    private JTextField textField;
    private JPasswordField pwT, pwcT;
    private JLabel jlId, jlPass; 
    
    private Thread t;
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "c##java";
    private String password = "bit";
    
    
    public SingUp() {
        
        try {                                               //"OracleDriver.class" 생성
            Class.forName(driver);//파일을 클레스 타입으로 생성 모든 파일 경로를 표시해야함
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {            
            e.printStackTrace();
        }    
        
        
    JFrame frame2 = new JFrame();
    frame2.getContentPane().setBackground(Color.WHITE);
    frame2.setBounds(500,200,400,650);
    frame2.getContentPane().setLayout(null);
    frame2.setTitle("회원가입");
    
    
    
    JLabel lblNewLabel = new JLabel("Member Sign Up");
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(0, 0, 384, 145);
    frame2.getContentPane().add(lblNewLabel);
    
    JLabel imgL = new JLabel(new ImageIcon("icon\\pw.png"));
    imgL.setBounds(40, 150, 30, 30);
    frame2.getContentPane().add(imgL);
    
    JLabel titleL = new JLabel("회원가입");
   titleL.setBounds(80, 160, 130, 18);
   titleL.setFont(new Font("맑은 고딕", Font.BOLD, 18));
   frame2.getContentPane().add(titleL);
    
   JLabel lblNewLabel_1 = new JLabel("I    D");
    lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    
    lblNewLabel_1.setBounds(70, 185, 70, 33);
    frame2.getContentPane().add(lblNewLabel_1);
   
    idT = new JTextField("");
    idT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));   
    idT.setBounds(85, 213, 230, 30);   
    frame2.getContentPane().add(idT);
    idT.setColumns(10);
    idT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         idT.setText("");
      }
   });
    
    JLabel label = new JLabel("Pass Word");
   
    label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    label.setBounds(70, 240, 130, 33);
    frame2.getContentPane().add(label);
    
    pwT = new JPasswordField();
    
    pwT.setColumns(10);
    pwT.setBounds(85, 270, 230, 30);
    pwT.setBorder(new LineBorder(new Color(209,209,209)));
    frame2.getContentPane().add(pwT);
    pwT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         pwT.setText("");
      }
   });
    
    JLabel label_1 = new JLabel("Pass Word 확인");
    
    label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    label_1.setBounds(70, 295, 150, 33);
    frame2.getContentPane().add(label_1);
    
    pwcT = new JPasswordField();
  
    pwcT.setColumns(10);
    pwcT.setBorder(new LineBorder(new Color(209,209,209)));
    pwcT.setBounds(85, 325,  230, 30);
    

    frame2.getContentPane().add(pwcT);
    pwcT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         pwcT.setText("");
      }
   });
    
    JLabel label_2 = new JLabel("이   름");
    label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    label_2.setBounds(70, 355, 70, 33);
    frame2.getContentPane().add(label_2);
    
    nameT = new JTextField();
    nameT.setFont(new Font("함초롬돋움", Font.PLAIN, 16)); 
    nameT.setBorder(new LineBorder(new Color(209,209,209)));
    nameT.setColumns(10);
    nameT.setBounds(85, 385,  230, 30);
    frame2.getContentPane().add(nameT);
    nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         nameT.setText("");
      }
   });
    
    JLabel label_4 = new JLabel("휴대폰 번호");
    //label_4.setHorizontalAlignment(SwingConstants.CENTER);
    label_4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    label_4.setBounds(70, 415, 100, 33);
    frame2.getContentPane().add(label_4);
    
    String phone[] = {"010","019","018","017"};
    comboBox = new JComboBox(phone);
    comboBox.setBounds(85, 450, 60, 33);
    comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    frame2.getContentPane().add(comboBox);
    
    JLabel phoneL1 = new JLabel("-");
    phoneL1.setBounds(170, 455, 20, 30);
    frame2.getContentPane().add(phoneL1);
    
    phoneNumberT = new JTextField("입력");
    phoneNumberT.setBorder(new LineBorder(new Color(209,209,209)));
    phoneNumberT.setColumns(10);
    phoneNumberT.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
    phoneNumberT.setBounds(183, 450, 50, 33);
   
    frame2.getContentPane().add(phoneNumberT);
    phoneNumberT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         phoneNumberT.setText("");
      }
   });
    
    JLabel phoneL2 = new JLabel("-");
    phoneL2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
    phoneL2.setBounds(245, 455, 20, 30);
    frame2.getContentPane().add(phoneL2);
    
    phoneNumber2T = new JTextField("입력");
    phoneNumber2T.setBorder(new LineBorder(new Color(209,209,209)));
    phoneNumber2T.setColumns(10);
    phoneNumber2T.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
    phoneNumber2T.setBounds(265, 450, 50, 33);
    frame2.getContentPane().add(phoneNumber2T);
    phoneNumber2T.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
      @Override
      public void mouseClicked(MouseEvent e) {
         phoneNumber2T.setText("");
      }
   });
    
    
    
    inputB = new JButton("확 인");
    inputB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
    inputB.setBounds(80, 495, 100, 40);
    inputB.setBorderPainted(false);
    frame2.getContentPane().add(inputB);
    
    cancelB = new JButton("취 소");
    cancelB.setBorderPainted(false);
    cancelB.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame2.setVisible(false);
            
        }
    });
    
    cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
    cancelB.setBounds(215, 495, 100, 40);
    frame2.getContentPane().add(cancelB);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 400, 140);
    panel.setBackground(new Color(176, 224, 230));
    frame2.getContentPane().add(panel);
    
    JPanel panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBackground(new Color(176, 224, 230));
      panel_1.setBounds(0, 547, 384, 56);
      frame2.getContentPane().add(panel_1);
    
    
    
    
    
    jlId = new JLabel();
    jlId.setHorizontalAlignment(SwingConstants.CENTER);
    jlId.setBounds(170, 185, 145, 30);
    frame2.getContentPane().add(jlId);
    
    jlPass = new JLabel();
    jlPass.setBounds(220,295, 150, 30);
    frame2.getContentPane().add(jlPass);
    frame2.setVisible(true);
    
    inputB.addActionListener(this);
    
    t = new Thread(this);
    t.start();
    
    }
    
    public void getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);//url 주소 ,오라클 아이디 , 오라클 비번
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inputB) {       
            
            if(idT.getText() == null || idT.getText().length() == 0 ) {
                JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .","회원가입",JOptionPane.ERROR_MESSAGE);
                
            }else if(!(idT.getText() == null || idT.getText().length() == 0) && pwT.getPassword() == null || pwT.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요 .","회원가입",JOptionPane.ERROR_MESSAGE);
            }else {
                
                String id = idT.getText();
                String pass = new String(pwT.getPassword());
                String passc = new String(pwcT.getPassword());
                String name = nameT.getText();
                String tel1 = (String)comboBox.getSelectedItem();
                String tel2 = phoneNumberT.getText();
                String tel3 = phoneNumber2T.getText();
                
                SingUpDTO singUpDTO = new SingUpDTO();
                singUpDTO.setId(id);
                singUpDTO.setPass(pass);
                singUpDTO.setPassc(passc);
                singUpDTO.setName(name);
                singUpDTO.setTel1(tel1);
                singUpDTO.setTel2(tel2);
                singUpDTO.setTel3(tel3);
                
                int su = singUpDAO.singUpInput(singUpDTO);
                
                System.out.println(su+"명 등록 되었습니다.");
                
                JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.","회원가입",JOptionPane.INFORMATION_MESSAGE);
                new Login();
            }
            
                   
            idT.setText("");
            pwT.setText("");
            pwcT.setText("");
            nameT.setText("");
            comboBox.setSelectedItem("010");
            phoneNumberT.setText("");
            phoneNumber2T.setText("");
            
        }//if
    }//action
    
    public void run() {
        
       while(true) {
           
           idCheck();
           
          
           passWordCheck();
           
          
       }
    }
    
    

    public void idCheck(){//id 중복체크
        
        getConnection();
        
        String sql ="select id from member";
        
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                
               if(idT.getText() == null || idT.getText().length() == 0) {
                    jlId.setText("");
                   return;
                }
                
                
                if(idT.getText().equals(rs.getString("id"))) {
                    jlId.setForeground(Color.red);
                    jlId.setText("id가 중복됩니다.");
                    break;
                }else if(!idT.getText().equals(rs.getString("id"))){
                    jlId.setForeground(Color.blue);
                    jlId.setText("사용 가능한 id입니다.");
                    
                }
                
            
            }

            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }finally {
            
            try {
                if(rs != null)rs.close();           //rs도 닫아줘야함
                if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                if(conn != null) conn.close();
            } catch (SQLException e2) {
                
                e2.printStackTrace();
            }
        
        }
        
    }//idCheck
    public void passWordCheck() {//비밀번호 중복체크
        
        if(pwT.getPassword() == null || 
                pwT.getPassword().length == 0 || 
                pwcT.getPassword() == null || 
                pwcT.getPassword().length == 0) {
                jlPass.setText("");
            return;
        }else {
        
        
            if(new String(pwT.getPassword()).equals(new String(pwcT.getPassword()))) {
                jlPass.setText("PassWord가 같습니다.");
                jlPass.setForeground(Color.blue);
                             
            }else if(!new String(pwT.getPassword()).equals(new String(pwcT.getPassword()))) {
                jlPass.setText("PassWord가 다릅니다.");
                jlPass.setForeground(Color.red);
 
        }
        }
        
    }
    
 
}//SingUp