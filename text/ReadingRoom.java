package text;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ReadingRoom extends JFrame implements MouseListener {
   private JPanel panel, panel1, panel2;
   private JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,
   btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23,btn24,btn25,btn26,btn27,btn28,btn29;
   private JLabel titleL;
   public ReadingRoom() {
      
      getContentPane().setBackground(Color.WHITE);
      getContentPane().setForeground(Color.WHITE);
      getContentPane().setLayout(null);

      panel = new JPanel();// -------------------------------------질문
      panel.setBackground(new Color(176, 224, 230));
      panel.setBounds(0, 0,384, 132);
      getContentPane().add(panel);
      panel.setLayout(null);

      titleL = new JLabel("열람실 좌석");
      titleL.setBounds(0, 0, 384, 132);
      titleL.setHorizontalAlignment(SwingConstants.CENTER);
      titleL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      titleL.setForeground(Color.WHITE);
      panel.add(titleL);
//------------------------------------------------------------------------------------
      
      btn1 = new JButton("1");
      btn1.setBounds(35, 165, 57, 50);
      btn1.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn1);
      
      btn1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(null, 
                  "좌석을 예약 하시겠습니까?",
                  "좌석 예약",
                        JOptionPane.YES_NO_OPTION);
            if(result == 0) {
               btn1.setBackground(new Color(255, 128, 192));
            }
         }
      });
      
      
      /*
       * 
       */
//if(result == JOptionPane.YES_OPTION)System.exit(0);
      
      
      btn2 = new JButton("2");
      btn2.setBounds(35, 245, 57, 50);
      btn2.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn2);
      
      btn3 = new JButton("3");
      btn3.setBackground(new Color(176, 224, 230));
      btn3.setBounds(35, 325, 57, 50);
      getContentPane().add(btn3);
      
      btn4 = new JButton("4");
      btn4.setBounds(35, 405, 57, 50);
      btn4.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn4);
      
      btn5 = new JButton("5");
      btn5.setBounds(35, 485, 57, 50);
      btn5.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn5);
      
      btn6 = new JButton("6");
      btn6.setBounds(131, 165, 57, 50);
      btn6.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn6);
      
      btn7 = new JButton("7");
      btn7.setBackground(new Color(176, 224, 230));
      btn7.setBounds(188, 165, 57, 50);
      getContentPane().add(btn7);
      
      btn8 = new JButton("8");
      btn8.setBackground(new Color(176, 224, 230));
      btn8.setBounds(243, 165, 57, 50);
      getContentPane().add(btn8);
      
      btn9 = new JButton("9");
      btn9.setBackground(new Color(176, 224, 230));
      btn9.setBounds(299, 165, 57, 50);
      getContentPane().add(btn9);
      
      btn10 = new JButton("10");
      btn10.setBackground(new Color(176, 224, 230));
      btn10.setBounds(131, 214, 57, 50);
      getContentPane().add(btn10);
      
      btn11 = new JButton("11");
      btn11.setBackground(new Color(176, 224, 230));
      btn11.setBounds(188, 214, 57, 50);
      getContentPane().add(btn11);
      
      btn12 = new JButton("12");
      btn12.setBackground(new Color(176, 224, 230));
      btn12.setBounds(243, 214, 57, 50);
      getContentPane().add(btn12);
      
      btn13 = new JButton("13");
      btn13.setBackground(new Color(176, 224, 230));
      btn13.setBounds(299, 214, 57, 50);
      getContentPane().add(btn13);
      
      btn14 = new JButton("14");
      btn14.setBackground(new Color(176, 224, 230));
      btn14.setBounds(131, 301, 57, 50);
      getContentPane().add(btn14);
      
      btn15 = new JButton("15");
      btn15.setBackground(new Color(176, 224, 230));
      btn15.setBounds(187, 301, 57, 50);
      getContentPane().add(btn15);
      
      btn16 = new JButton("16");
      btn16.setBackground(new Color(176, 224, 230));
      btn16.setBounds(243, 301, 57, 50);
      getContentPane().add(btn16);
      
      btn17 = new JButton("17");
      btn17.setBackground(new Color(176, 224, 230));
      btn17.setBounds(299, 301, 57, 50);
      getContentPane().add(btn17);
      
      btn18 = new JButton("18");
      btn18.setBackground(new Color(176, 224, 230));
      btn18.setBounds(131, 350, 57, 50);
      getContentPane().add(btn18);
      
      btn19 = new JButton("19");
      btn19.setBounds(187, 350, 57, 50);
      btn19.setBackground(new Color(176, 224, 230));
      getContentPane().add(btn19);
      
      btn20 = new JButton("20");
      btn20.setBackground(new Color(176, 224, 230));
      btn20.setBounds(243, 350, 57, 50);
      getContentPane().add(btn20);
      
      btn21 = new JButton("21");
      btn21.setBackground(new Color(176, 224, 230));
      btn21.setBounds(299, 350, 57, 50);
      getContentPane().add(btn21);
      
      btn22 = new JButton("22");
      btn22.setBackground(new Color(176, 224, 230));
      btn22.setBounds(131, 435, 57, 50);
      getContentPane().add(btn22);
      
      btn23 = new JButton("23");
      btn23.setBackground(new Color(176, 224, 230));
      btn23.setBounds(187, 435, 57, 50);
      getContentPane().add(btn23);
      
      btn24 = new JButton("24");
      btn24.setBackground(new Color(176, 224, 230));
      btn24.setBounds(243, 435, 57, 50);
      getContentPane().add(btn24);
      
      btn25 = new JButton("25");
      btn25.setBackground(new Color(176, 224, 230));
      btn25.setBounds(299, 435, 57, 50);
      getContentPane().add(btn25);
      
      btn26 = new JButton("26");
      btn26.setBackground(new Color(176, 224, 230));
      btn26.setBounds(131, 485, 57, 50);
      getContentPane().add(btn26);
      
      btn27 = new JButton("27");
      btn27.setBackground(new Color(176, 224, 230));
      btn27.setBounds(187, 485, 57, 50);
      getContentPane().add(btn27);
      
      btn28 = new JButton("28");
      btn28.setBackground(new Color(176, 224, 230));
      btn28.setBounds(243, 485, 57, 50);
      getContentPane().add(btn28);
      
      btn29 = new JButton("29");
      btn29.setBackground(new Color(176, 224, 230));
      btn29.setBounds(299, 485, 57, 50);
      getContentPane().add(btn29);
//----------------------------------------------------------------------------------      
      panel1 = new JPanel();
      panel1.setBackground(new Color(255, 128, 192));//---분홍이
      panel1.setBounds(173, 560, 20, 20);
      getContentPane().add(panel1);
      panel1.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("자리있음");
      lblNewLabel_1.setBounds(204, 560, 57, 15);
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      getContentPane().add(lblNewLabel_1);
      
      panel2 = new JPanel();
      panel2.setBackground(new Color(176, 224, 230));
      panel2.setBounds(275, 560, 20, 20);
      getContentPane().add(panel2);
      panel2.setLayout(null);

      
      JLabel lblNewLabel_2 = new JLabel("자리없음");
      lblNewLabel_2.setBounds(304, 560, 57, 15);
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
      getContentPane().add(lblNewLabel_2);   
      
      JButton imgBtn = new JButton(new ImageIcon("zzz.png"));
      imgBtn.setBorderPainted(false);
      imgBtn.setContentAreaFilled(false);// 배경지우는거
      imgBtn.setFocusPainted(false);//버튼눌린자국 지우기
      imgBtn.setPressedIcon(new ImageIcon("xxx.png"));
      //imgBtn.setOpaque(false);//
      imgBtn.setBounds(45, 556, 35, 35);
      getContentPane().add(imgBtn);
      
//---------------------------------------------------------------------------------         
      //btn1.addMouseListener(this);
      btn2.addMouseListener(this);
      btn3.addMouseListener(this);
      btn4.addMouseListener(this);
      btn5.addMouseListener(this);
      btn6.addMouseListener(this);
      btn7.addMouseListener(this);
      btn8.addMouseListener(this);
      btn9.addMouseListener(this);
      btn10.addMouseListener(this);
      btn11.addMouseListener(this);
      btn12.addMouseListener(this);
      btn13.addMouseListener(this);
      btn14.addMouseListener(this);
      btn15.addMouseListener(this);
      btn16.addMouseListener(this);
      btn17.addMouseListener(this);
      btn18.addMouseListener(this);
      btn19.addMouseListener(this);
      btn20.addMouseListener(this);
      btn21.addMouseListener(this);
      btn22.addMouseListener(this);
      btn23.addMouseListener(this);
      btn24.addMouseListener(this);
      btn25.addMouseListener(this);
      btn26.addMouseListener(this);
      btn27.addMouseListener(this);
      btn28.addMouseListener(this);
      btn29.addMouseListener(this);
      imgBtn.addMouseListener(this);
//---------------------------------------------------------------------------------   

      setTitle("열람실 예약");
      setBounds(500, 200, 400, 650);
      setVisible(true);
      
   }
//---------------------------------------------------------------------------------   
   @Override
   public void mouseClicked(MouseEvent e) {
      
      if(e.getSource() == btn2) {
      
         int result = JOptionPane.showConfirmDialog(null, 
               "좌석을 예약 하시겠습니까?",
               "좌석 예약",
                     JOptionPane.YES_NO_OPTION);
         if(result == 0) {
            btn2.setBackground(new Color(255, 128, 192));
         }
      }
      
      if(e.getSource() == btn3) btn3.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn4) btn4.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn5) btn5.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn6) btn6.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn7) btn7.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn8) btn8.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn9) btn9.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn10) btn10.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn11) btn11.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn12) btn12.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn13) btn13.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn14) btn14.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn15) btn15.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn16) btn16.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn17) btn17.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn18) btn18.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn19) btn19.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn20) btn20.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn21) btn21.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn22) btn22.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn23) btn23.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn24) btn24.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn25) btn25.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn26) btn26.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn27) btn27.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn28) btn28.setBackground(new Color(255, 128, 192));
      if(e.getSource() == btn29) btn29.setBackground(new Color(255, 128, 192));
   }
   @Override
   public void mouseEntered(MouseEvent e) {
   }
   @Override
   public void mouseExited(MouseEvent e) {   
   }
   @Override
   public void mousePressed(MouseEvent e) {   
   }
   @Override
   public void mouseReleased(MouseEvent e) {
   } 
 
//---------------------------------------------------------------------------------      
   
}
