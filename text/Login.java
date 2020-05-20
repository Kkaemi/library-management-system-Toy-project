package text;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import javax.swing.JCheckBox;

public class Login implements ActionListener{
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    String driver = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "library_manager";
    private String password = "asdf";
     
    private JTextField id;
    private JButton b1;
    private JCheckBox adminCheckBox;
    private JFrame frame;
    private JPasswordField pass;
       
    private Main main = new Main();
    
    public Login() {
        
        try {                                               //"OracleDriver.class" 생성
            Class.forName(driver);//파일을 클레스 타입으로 생성 모든 파일 경로를 표시해야함
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {            
            e.printStackTrace();
        }    
               
    frame = new JFrame();
    frame.getContentPane().setForeground(Color.WHITE);
    frame.getContentPane().setBackground(Color.WHITE);
    frame.setBounds(500,200,400,650);
    frame.getContentPane().setLayout(null);
    
    JLabel lbtitle_1 = new JLabel("I  D");
    lbtitle_1.setHorizontalAlignment(SwingConstants.CENTER);
    lbtitle_1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
    lbtitle_1.setBounds(55, 190, 50, 33);
    frame.getContentPane().add(lbtitle_1);
    
    id = new JTextField("  아이디를 입력해주세요.");//------------------------마우스 클릭시 글 사라지는거);
    id.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
    
    id.setColumns(10);
    id.setOpaque(false);//텍스트필드 컬러 삭제
    id.setBorder(new EmptyBorder(0, 0, 0, 0));

    id.setBounds(130, 190, 200, 33);
    frame.getContentPane().add(id);
    id.addMouseListener(new MouseAdapter(){//ㅇ-------------여기부터 추가
        @Override
        public void mouseClicked(MouseEvent e){
           id.setText("");
        }
    });  
    JLabel lbtitle_2 = new JLabel("P W");
    lbtitle_2.setHorizontalAlignment(SwingConstants.CENTER);
    lbtitle_2.setFont(new Font("함초롬돋움", Font.BOLD, 17));
    lbtitle_2.setBounds(55, 230, 50, 33);
    frame.getContentPane().add(lbtitle_2);
    
    pass = new JPasswordField();//------------------------마우스 클릭시 글 사라지는거)
    pass.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
    pass.setColumns(10);
    pass.setOpaque(false);//텍스트필드 컬러 삭제
    pass.setBorder(new EmptyBorder(0, 0, 0, 0));
    pass.setBounds(130, 230, 200, 33);
    pass.setEchoChar('*');//이부분 어쩌지
  //--------------------------------------------------------------------  
    JCheckBox chckbxNewCheckBox = new JCheckBox("자동 로그인");
    chckbxNewCheckBox.setBackground(Color.WHITE);
    chckbxNewCheckBox.setBounds(55, 278, 100, 25);
    frame.getContentPane().add(chckbxNewCheckBox);
    
    adminCheckBox = new JCheckBox("관리자 모드");
    adminCheckBox.setBackground(Color.WHITE);
    adminCheckBox.setBounds(160, 278, 140, 25);
    frame.getContentPane().add(adminCheckBox);
    //--------------------------------------------------------------------  
    b1 = new JButton("Login");
    b1.setFont(new Font("함초롬5움", Font.BOLD, 17));
    b1.setBounds(55, 320, 275, 40);
    b1.setBorderPainted(false);
    frame.getContentPane().add(b1);
    
    JButton button = new JButton("회원 가입");
    button.setFont(new Font("함초롬돋움", Font.BOLD, 17));
    button.setBorderPainted(false);
    button.setBounds(55, 370, 275, 40);
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new SingUp();
    }
    });
    frame.getContentPane().add(button);
    //--------------------------------------------------------------------  
    JLabel lblNewLabel = new JLabel("              로그인 정보를 잊으셨나요?");
    lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
    lblNewLabel.setBounds(55, 440, 275, 20);
    frame.getContentPane().add(lblNewLabel);
    
    
    JButton button_1 = new JButton("ID 찾기");
    button_1.setFont(new Font("함초롬돋움", Font.BOLD, 16));
    button_1.setBorderPainted(false);
    button_1.setBounds(90, 475, 100, 40);
    button_1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new IdFind();
        }
    });
    frame.getContentPane().add(button_1);

    JButton button_2 = new JButton("PW 찾기");
    button_2.setFont(new Font("함초롬돋움", Font.BOLD, 16));
    button_2.setBorderPainted(false);
    button_2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new PwFind();
        }
    });
    button_2.setBounds(200, 475, 100, 40);
    frame.getContentPane().add(button_2);
  //--------------------------------------------------------------------   
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(new Color(176, 224, 230));
    panel.setBounds(0, 0, 384, 145);
    frame.getContentPane().add(panel);
    
    JLabel label = new JLabel("Login");
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 37));
    label.setBounds(0, 0, 384, 145);
    panel.add(label);
    
 
    JPanel panel_1 = new JPanel();
    panel_1.setLayout(null);
    panel_1.setBackground(new Color(176, 224, 230));
    panel_1.setBounds(0, 547, 384, 65);
    frame.getContentPane().add(panel_1);
   
    frame.getContentPane().add(pass);
    
    frame.setVisible(true);
    b1.addActionListener(this);

    }//Login
    
    public void getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);//url 주소 ,오라클 아이디 , 오라클 비번
            System.out.println("접속 성공");
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }//getConnection
   
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == b1) {
         if(adminCheckBox.isSelected()) {
             
             if(id.getText() == null || id.getText().length() == 0) {
                 JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .","로그인",JOptionPane.ERROR_MESSAGE);
                 return;
             }
             
            getConnection();
            String sql = "select * from admin_table where admin_id=? and admin_pw=?";
           
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id.getText());
                pstmt.setString(2, new String(pass.getPassword()));
                rs = pstmt.executeQuery();
                
                if(rs.next()) {
                    if(id.getText().equals(rs.getString("admin_id")) && new String(pass.getPassword()).equals(rs.getString("admin_pw"))) {                  
                        frame.setVisible(false);
                        new Admin();
                        JOptionPane.showMessageDialog(null,"관리자로 로그인 되었습니다.","관리자",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"관리자의 아이디 혹은 비밀번호가 틀렸습니다.","관리자",JOptionPane.ERROR_MESSAGE);
                    id.setText("");
                    pass.setText(""); 
                }
                
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }finally {
                
                try {
                    if(rs != null)rs.close();           //rs도 닫아줘야함
                    if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                    if(conn != null) conn.close();
                } catch (SQLException e2) {
                    
                    e2.printStackTrace();
                }
            
            } 
        }else {
            if(id.getText() == null || id.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .","로그인",JOptionPane.ERROR_MESSAGE);
                return;
            }

            
               
                	
                	
             if(black() == 1) {
                		
                		           		
             }else{
                		
                		
                        getConnection();
                        String sql = "select * from member_table where member_id=? and member_pw=?";
//                        String sql2 = "select * from black_table where member_id=? and member_pw=?";
                        try {
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, id.getText());
                            pstmt.setString(2, new String(pass.getPassword()));
                            rs = pstmt.executeQuery();
                            
                        
           		
                	
                if(rs.next()) {
                	
   
                	if(id.getText().equals(rs.getString("member_id")) && new String(pass.getPassword()).equals(rs.getString("member_pw"))) {
           
                    	
                    	JOptionPane.showMessageDialog(null, "회원 로그인 되었습니다.","로그인",JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        main.loginSucces(); 
                    
                    
                    }
                    
                    
                              
                	else {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀립니다.","로그인",JOptionPane.ERROR_MESSAGE);
                    id.setText("");
                    pass.setText("");                                                  
                	}
                	
                
                	
                	
                	}//rs.next()
                	
                
                
            } catch (SQLException e1) {
                
                e1.printStackTrace();
            }finally {
                
                try {
                    if(rs != null)rs.close();           //rs도 닫아줘야함
                    if(pstmt != null) pstmt.close();    //닫아주기 거꾸로
                    if(conn != null) conn.close();
                } catch (SQLException e2) {
                    
                    e2.printStackTrace();
                }
            
            }
            
            
        
        
        
        }//else
         
         
       }//if 
       }
         
    }//action
    


    
    public int black() {
    	
    	
    	   getConnection();

           String sql = "select * from black_table where member_id=?";
         
               try {
            	   
				   pstmt = conn.prepareStatement(sql);
	               pstmt.setString(1, id.getText());

	               rs = pstmt.executeQuery();
	               
	               if(rs.next()) {
	            	
	                   if(id.getText().equals(rs.getString("member_id"))) {
	                       JOptionPane.showMessageDialog(null, "3개월 이상 연체로 블랙리스트 처리된 계정입니다.\n 관리자에게 문의해주세요","블랙",JOptionPane.INFORMATION_MESSAGE);
	                       id.setText("");
	                       pass.setText("");  
	                       return 1;
	                      
	                   }
	                      
//	                   		}else {su = 2;}


	               }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
               
               
               
               return -1;
            
               

    
           }//black
    }