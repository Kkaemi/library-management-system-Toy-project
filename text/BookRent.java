package text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class BookRent extends JFrame { // 도서목록
   private JLabel titleL, titleL2; 
   private JButton searchBtn, updateBtn;
   private DefaultTableModel model;
   private JScrollPane scroll;
   private JTable table;
   private JPanel panel;
   private Vector<String> vector;
   private JComboBox<String> combo;
   private JTextField textField;
   
   public BookRent() {
      getContentPane().setLayout(null); //-------------------------------------질문ㅇㅇ
      getContentPane().setBackground(Color.WHITE);//-------------------------------------질문
      getContentPane().setForeground(Color.WHITE);
      
     panel = new JPanel();//-------------------------------------질문
     panel.setBackground(new Color(176, 224, 230));
     panel.setBounds(0, 0, 384, 132);
      getContentPane().add(panel);
      panel.setLayout(null);

      titleL = new JLabel("Book Rent");
      titleL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      titleL.setForeground(Color.WHITE);
      titleL.setHorizontalAlignment(SwingConstants.CENTER);
      titleL.setBounds(0, 0, 384, 132);
      panel.add(titleL);//-------------------------------------질문
      
      String[] genre = {"전체","소설", "역사", "종교", "사회", "과학", "자기개발"};
      combo = new JComboBox(genre);
      combo.setBounds(15, 160, 80, 30);
   
      
      searchBtn = new JButton("검색");
      searchBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      searchBtn.setBounds(298, 159, 70, 31);

      
      titleL2 = new JLabel("도서 목록");
      titleL2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      
      titleL2.setForeground(new Color(119, 136, 153));  //-----------------그냥 컬러 넣어봄 
      titleL2.setBounds(73, 227, 108, 30);
       
      
      updateBtn = new JButton("반납하기");
      updateBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      updateBtn.setBounds(272, 501, 100, 30);
      updateBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
         //     new BookListupdateBtn();
          }
      });
      
      
//-----------------------------------------------------------------   
      getContentPane().add(combo);getContentPane().add(searchBtn);
      getContentPane().add(titleL2);
      getContentPane().add(updateBtn);
      
//------------------------------------------------------------------JPanel tb & 타이틀

      vector = new Vector<String>();
      vector.add("도서코드");   vector.add("도서명");   vector.add("저자");  
      vector.add("출판사");   vector.add("장르");   

      model = new DefaultTableModel(vector, 12 ){ // 1로 해야 한줄 생김 
         public boolean isCellEditable(int r, int c){
            return (c != 0) ? true : false; // 타이틀 수정못하게 하는 부분   //-------------------------------------질문ㅐㅐ
         }
      };
      
//------------------------------------------------------------------
      table = new JTable(model);
      
      scroll = new JScrollPane(table); 
      scroll.setSize(375, 210);
      scroll.setLocation(5, 270);
      
   
      getContentPane().add(scroll);
      
      textField = new JTextField();
      textField.setColumns(10);
      textField.setBounds(new Rectangle(3, 3, 3, 3));
      textField.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      textField.setBounds(107, 160, 179, 30);
      getContentPane().add(textField);
      
      JLabel lblNewLabel = new JLabel(new ImageIcon("zzz.png"));
      lblNewLabel.setBounds(10, 218, 56, 30);
      
      getContentPane().add(lblNewLabel);


      setTitle("도서 관리");
      setBounds(500,200,400,650);
      setVisible(true);
      
   }

   
   public static void main(String[] args) {
      new BookRent();
   }
}

