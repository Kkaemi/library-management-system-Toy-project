package text;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import server.ChatClientObject;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Main {
    
    private JButton loginB, bookSearchB, bookRentB, readingRoonB, logoutB, memberIformationB;
    private JFrame jf;
    
    public Main() {
       jf = new JFrame();
       jf.getContentPane().setBackground(Color.WHITE);
       jf.getContentPane().setForeground(Color.WHITE);
       jf.setBounds(500,200,400,650);
       jf.getContentPane().setLayout(null);
       
       loginB = new JButton();
       loginB.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               new Login();
               jf.setVisible(false);
           }
       });
       loginB.setIcon(new ImageIcon("icon/icons8-test-account-64.png"));
       loginB.setBackground(Color.WHITE);
       loginB.setForeground(Color.WHITE);
       loginB.setBounds(64, 187, 95, 66);
       loginB.setBorderPainted(false);
       loginB.setFocusPainted(false);// 클릭했을떄 선 지움
       loginB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
    
       jf.getContentPane().add(loginB);
       
       JPanel panel = new JPanel();
       panel.setBackground(new Color(176, 224, 230));
       panel.setBounds(0, 0, 400, 132);
       jf.getContentPane().add(panel);
       panel.setLayout(null);
       
       JLabel lblNewLabel = new JLabel("Library Program");
       lblNewLabel.setBounds(12, 10, 360, 112);
       lblNewLabel.setForeground(Color.WHITE);
       lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 23));
       lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
       panel.add(lblNewLabel);
       
       bookSearchB = new JButton();
       
       bookSearchB.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent arg0) {
       	       
       		new BookSearch().event();
       	}
       });
       bookSearchB.setIcon(new ImageIcon("icon/icons8-search-64.png"));
       bookSearchB.setForeground(Color.WHITE);
       bookSearchB.setBackground(Color.WHITE);
       bookSearchB.setBounds(233, 187, 95, 66);
       bookSearchB.setBorderPainted(false);
       bookSearchB.setFocusPainted(false);// 클릭했을떄 선 지움
       bookSearchB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       bookSearchB.setEnabled(false);
       
       jf.getContentPane().add(bookSearchB);
       
       bookRentB = new JButton();
       bookRentB.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              
               new BookRent();
           }
       });
       
       bookRentB.setIcon(new ImageIcon("icon/icons8-bookmark-64.png"));
       bookRentB.setForeground(Color.WHITE);
       bookRentB.setBackground(Color.WHITE);
       bookRentB.setBounds(64, 335, 95, 66);
       bookRentB.setBorderPainted(false);
       bookRentB.setFocusPainted(false);// 클릭했을떄 선 지움
       bookRentB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       bookRentB.setEnabled(false);
       jf.getContentPane().add(bookRentB);
       
       readingRoonB = new JButton();
       readingRoonB.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	   new ChatClientObject().service();
           }
       });
       readingRoonB.setIcon(new ImageIcon("icon/icons8-myspace-64.png"));
       readingRoonB.setForeground(Color.WHITE);
       readingRoonB.setBackground(Color.WHITE);
       readingRoonB.setBounds(233, 335, 95, 66);
       readingRoonB.setBorderPainted(false);
       readingRoonB.setFocusPainted(false);// 클릭했을떄 선 지움
       readingRoonB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       readingRoonB.setEnabled(false);
       jf.getContentPane().add(readingRoonB);
       
       memberIformationB = new JButton();
       memberIformationB.setIcon(new ImageIcon("icon/icons8-picture-64.png"));
       memberIformationB.setForeground(Color.WHITE);
       memberIformationB.setBackground(Color.WHITE);
       memberIformationB.setBounds(64, 477, 95, 66);
       memberIformationB.setFocusPainted(false);// 클릭했을떄 선 지움
       memberIformationB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       memberIformationB.setBorderPainted(false);
       memberIformationB.setEnabled(false);
       jf.getContentPane().add(memberIformationB);
       
       logoutB = new JButton();
       logoutB.setIcon(new ImageIcon("icon/logout2.jpg"));
       logoutB.setForeground(Color.WHITE);
       logoutB.setBackground(Color.WHITE);
       logoutB.setBounds(233, 477, 95, 66);
       logoutB.setFocusPainted(false);// 클릭했을떄 선 지움
       logoutB.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       logoutB.setBorderPainted(false);
       logoutB.setEnabled(false);
       jf.getContentPane().add(logoutB);
       logoutB.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "", JOptionPane.YES_NO_OPTION);
              if (result == 0) {
                  jf.setVisible(false);
                  new Main();
           }
           }
        });
       
    
       jf.setVisible(true);
       jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    
    public void loginSucces() {
        
        bookSearchB.setEnabled(true);
        bookRentB.setEnabled(true);
        readingRoonB.setEnabled(true);    
        logoutB.setEnabled(true);
        memberIformationB.setEnabled(true);
        loginB.setEnabled(false);
        
    }
    
 
    public static void main(String[] args) {
        new Main();
    }


}
