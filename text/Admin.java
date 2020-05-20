package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Admin {
	
   public Admin() {
	   
      JFrame jf = new JFrame();
      
      jf.getContentPane().setBackground(Color.WHITE);
       jf.getContentPane().setForeground(Color.WHITE);
       jf.getContentPane().setLayout(null);
       
       JPanel panel = new JPanel();
       panel.setBounds(0, 0, 400, 145);
       panel.setBackground(new Color(176, 224, 230));
       jf.getContentPane().add(panel);
       panel.setLayout(null);
       
       JLabel lblNewLabel = new JLabel("Administrator");
       lblNewLabel.setBounds(0, 0, 400, 145);
       lblNewLabel.setForeground(Color.WHITE);
       lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 38));
       lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
       panel.add(lblNewLabel);
       
       JButton btnNewButton = new JButton();
       btnNewButton.setIcon(new ImageIcon("icon/icons8-search-64.png"));
       btnNewButton.setBounds(143, 180, 97, 70);
       btnNewButton.setForeground(Color.WHITE);
       btnNewButton.setBackground(Color.WHITE);
       btnNewButton.setBorderPainted(false);
       btnNewButton.setFocusPainted(false);// 클릭했을떄 선 지움
       btnNewButton.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       btnNewButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               AdminBookList.getInstance().setVisible(true);;
           }
       });
       
       jf.getContentPane().add(btnNewButton);
       
       JButton button = new JButton();
       button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
              new AdminRental();
          }
       });
       button.setBounds(143, 310, 97, 70);
       button.setIcon(new ImageIcon("icon/icons8-bookmark-64.png"));
       button.setForeground(Color.WHITE);
       button.setBackground(Color.WHITE);
       button.setBorderPainted(false);
       button.setFocusPainted(false);// 클릭했을떄 선 지움
       button.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       jf.getContentPane().add(button);
       
       JButton button_1 = new JButton();
       button_1.setIcon(new ImageIcon("icon/b1.jpg"));
       button_1 .setForeground(Color.WHITE);
       button_1 .setBackground(Color.WHITE);
       button_1 .setBorderPainted(false);
       button_1 .setFocusPainted(false);// 클릭했을떄 선 지움
       button_1 .setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
       button_1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             new AdminBlackList();
          }
       });
       button_1.setBounds(143, 440, 97, 70);
       
		jf.getContentPane().add(button_1);

      JPanel panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBackground(new Color(176, 224, 230));
      panel_1.setBounds(0, 547, 400, 70);
       jf.getContentPane().add(panel_1);
      
      
      jf.setVisible(true);
      jf.setBounds(500,200,400,630);
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void main(String[] args) {
        new Admin();
    }
}