package text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import library.dao.BookDAO;

public class BookSearch implements ActionListener {
	private JFrame jf;
	private JTextField textField;
	private JButton btnNewButton;
	private DefaultTableModel model;
	private JTable table;
	private JRadioButton bookNameSearchRadio, bookAuthorSearchRadio, bookPublisherSearchRadio;
	private JComboBox<String> genreBox;
	private String[] genre = { "장르", "소설", "만화", "역사", "과학",
			"예술", "문학", "철학", "종교"};
	
	private BookDAO bookDAO = new BookDAO();

	public BookSearch() {
		jf = new JFrame("도서 검색");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 400, 145);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Book Search");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 400, 145);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(new Rectangle(3, 3, 3, 3));
		textField.setBounds(120, 180, 160, 40);
		textField.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField.setColumns(10);
		
		bookNameSearchRadio = new JRadioButton("이름",true);
		bookNameSearchRadio.setBounds(120, 150, 60, 30);
		
		bookAuthorSearchRadio = new JRadioButton("저자");
		bookAuthorSearchRadio.setBounds(180, 150, 60, 30);
		
		bookPublisherSearchRadio = new JRadioButton("출판사");
		bookPublisherSearchRadio.setBounds(240, 150, 70, 30);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(bookAuthorSearchRadio);
		bg.add(bookNameSearchRadio);
		bg.add(bookPublisherSearchRadio);

		btnNewButton = new JButton("검색");
		btnNewButton.setBounds(290, 180, 75, 40);
		// btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		// btnNewButton.setBorderPainted(false);

		genreBox = new JComboBox(genre);
		// genreBox.setBackground(Color.WHITE);
		genreBox.setBounds(20, 180, 90, 40);

		String[] column = { "도서코드", "도서명", "저자", "출판사", "장르", "대출여부"};

		// Table 변경 불가능
		model = new DefaultTableModel(column, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setSize(380, 200);
		scroll.setLocation(10, 250);

		Container con = jf.getContentPane();
		con.setForeground(Color.WHITE);
		con.setLayout(null);

		con.add(scroll);
		con.add(btnNewButton);
		con.add(textField);
		con.add(panel);
		con.add(genreBox);
		con.add(bookNameSearchRadio);
		con.add(bookAuthorSearchRadio);
		con.add(bookPublisherSearchRadio);
		

		jf.setBounds(500, 200, 400, 650);
		jf.setVisible(true);
		jf.setResizable(false);

	}// constructor

	public void event() {
		btnNewButton.addActionListener(this);
		genreBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// btnNewButton
		if (e.getSource() == btnNewButton) {
			if (bookNameSearchRadio.isSelected()) {
				if (textField.getText().equals("") || textField.getText() == null) {

					JOptionPane.showMessageDialog(jf, "책 제목을 입력해 주세요!", "Error!",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				setRow(textField.getText());
			} else if (bookAuthorSearchRadio.isSelected()) {
				if (textField.getText().equals("") || textField.getText() == null) {

					JOptionPane.showMessageDialog(jf, "검색할 저자를 입력해 주세요!", "Error!",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				setRow(1, textField.getText());
				
			} else if (bookPublisherSearchRadio.isSelected()) {
				if (textField.getText().equals("") || textField.getText() == null) {

					JOptionPane.showMessageDialog(jf, "검색할 출판사 입력해 주세요!", "Error!",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				setRow(2,textField.getText());
			}
		}

		// genreBox
		if (e.getSource() == genreBox) {
			String select = genreBox.getSelectedItem().toString();
			if (select.equals("전체")) {
				return;
			}
			setRow(select);
		}
	}// actionPerformed

	private void setRow(String str) {
		model.setRowCount(0);

		List<BookDTO> list = (bookDAO.userBookSearch(str));

		for (BookDTO dto : list) {
			Vector<Object> v = new Vector<Object>();
			v.add(dto.getSeq());
			v.add(dto.getName());
			v.add(dto.getAuthor());
			v.add(dto.getPublisher());
			v.add(dto.getGenre());
			v.add(dto.getCheck());

			model.addRow(v);
		}
	}// setRow
	
	private void setRow(int searchOption, String str) {
		model.setRowCount(0);

		List<BookDTO> list = (bookDAO.userBookSearch(searchOption, str));

		for (BookDTO dto : list) {
			Vector<Object> v = new Vector<Object>();
			v.add(dto.getSeq());
			v.add(dto.getName());
			v.add(dto.getAuthor());
			v.add(dto.getPublisher());
			v.add(dto.getGenre());
			v.add(dto.getCheck());

			model.addRow(v);
		}
	}// setRow
	
	public static void main(String[] args) {
		new BookSearch().event();
	}

}

