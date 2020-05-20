package text;

import java.awt.*;
import javax.swing.*;

public class BookListaddBtn extends JFrame { // ID 찾기 창
   private JLabel titleL, bookL, bookLwr, bookLcom;
   private JTextField bookT, bookTwr, bookTcom;
   private JButton addBtn, cancelBtn;
   private JPanel panel;
   private JComboBox<String> combo;

   public BookListaddBtn() {
      getContentPane().setBackground(Color.WHITE);
      getContentPane().setForeground(Color.WHITE);

      panel = new JPanel();// -------------------------------------질문
      panel.setBackground(new Color(176, 224, 230));
      panel.setBounds(0, 0, 384, 132);
      getContentPane().add(panel);
      panel.setLayout(null);

      titleL = new JLabel("도서 추가");
      titleL.setHorizontalAlignment(SwingConstants.CENTER);
      titleL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
      titleL.setForeground(Color.WHITE);
      titleL.setBounds(0, 0, 384, 132);
      panel.add(titleL);

      bookL = new JLabel("도서명");
      bookL.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      bookL.setHorizontalAlignment(SwingConstants.CENTER);
      bookL.setBounds(50, 180, 80, 30);

      bookT = new JTextField();
      bookT.setBounds(150, 180, 180, 30);

      bookLwr = new JLabel("저자명 ");
      bookLwr.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      bookLwr.setHorizontalAlignment(SwingConstants.CENTER);
      bookLwr.setBounds(30, 250, 130, 30);

      bookTwr = new JTextField();
      bookTwr.setBounds(150, 250, 180, 30);

      bookLcom = new JLabel("출판사 ");
      bookLcom.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      bookLcom.setHorizontalAlignment(SwingConstants.CENTER);
      bookLcom.setBounds(28, 350, 130, 30);

      bookTcom = new JTextField();
      bookTcom.setBounds(150, 350, 180, 30);
      
      String[] genre = {"소설", "역사", "종교", "사회", "과학", "자기개발"};
      combo = new JComboBox<String> (genre);
      combo.setBounds(80, 400, 180, 40);
      
      addBtn = new JButton("추가");
      addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      addBtn.setBounds(80, 470, 100, 40);

      cancelBtn = new JButton("취소");
      cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
      cancelBtn.setBounds(215, 470, 100, 40);
      
      getContentPane().setLayout(null);

      // -------------------------------------------------------------------------------
      getContentPane().add(bookL);
      getContentPane().add(bookT);
      getContentPane().add(bookLwr);
      getContentPane().add(bookTwr);
      getContentPane().add(bookLcom);
      getContentPane().add(bookTcom);
      getContentPane().add(combo);

      getContentPane().add(addBtn);
      getContentPane().add(cancelBtn);
      // -------------------------------------------------------------------------------

      setTitle("도서 추가");
      setBounds(500, 200, 400, 650);
      setVisible(true);
      
   }
}
