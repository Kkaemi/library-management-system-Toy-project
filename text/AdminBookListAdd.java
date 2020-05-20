package text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.bean.BookDTO;
import library.dao.BookDAO;

public class AdminBookListAdd extends JFrame { // ID 찾기 창
	private JLabel titleL, bookL, bookLwr, bookLcom;
	private JTextField bookT, bookTwr, bookTcom;
	private JButton addBtn, cancelBtn;
	private JPanel panel;
	private JComboBox<String> combo;
	private String[] genre = { "소설", "만화", "역사", "과학", "예술", "문학", "철학", "종교" };

	private BookDAO bookDAO = new BookDAO();

	public AdminBookListAdd() {

		panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 400, 132);
		panel.setLayout(null);

		titleL = new JLabel("Add Book");
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleL.setForeground(Color.WHITE);
		titleL.setBounds(0, 0, 400, 132);
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

		combo = new JComboBox<String>(genre);
		combo.setBounds(80, 400, 180, 40);

		addBtn = new JButton("추가");
		addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		addBtn.setBounds(80, 470, 100, 40);
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(bookDAO.getSeq());
				bookDTO.setName(bookT.getText());
				bookDTO.setAuthor(bookTwr.getText());
				bookDTO.setPublisher(bookTcom.getText());
				bookDTO.setGenre((String) combo.getSelectedItem());
				bookDTO.setCheck(0);

				int su = bookDAO.bookInsert(bookDTO);

				if (su == 0) {
					JOptionPane.showMessageDialog(null, "값을 제대로 입력해 주세요", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "정상적으로 처리되었습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
				Thread t = new Thread(AdminBookList.getInstance());
				t.start();

				bookT.setText("");
				bookTwr.setText("");
				bookTcom.setText("");
				combo.setSelectedIndex(0);
			}
		});

		cancelBtn = new JButton("취소");
		cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		cancelBtn.setBounds(215, 470, 100, 40);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminBookListAdd.this.setVisible(false);
			}
		});

		Container con = getContentPane();
		con.setLayout(null);
		con.setBackground(Color.WHITE);
		con.setForeground(Color.WHITE);
		con.add(bookL);
		con.add(bookT);
		con.add(bookLwr);
		con.add(bookTwr);
		con.add(bookLcom);
		con.add(bookTcom);
		con.add(combo);
		con.add(addBtn);
		con.add(cancelBtn);
		con.add(panel);

		setTitle("도서 추가");
		setBounds(500, 200, 400, 650);
		setVisible(true);

	}
}