package text;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import library.bean.RentDTO;
import library.dao.BookDAO;

public class AdminBookList extends JFrame implements Runnable { // 도서목록

	// 관리자 도서목록 프레임 싱글턴화
	private static AdminBookList abl;

	private JLabel titleL, titleL2, bookL;
	private JButton searchBtn, addBtn, deleteBtn, updateBtn;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private JTable table;
	private JPanel panel;
	private JComboBox<String> combo;
	private JTextField textField, textField_1, textField_2, textField_3, textField_4;
	private JRadioButton bookNameSearchRadio, bookAuthorSearchRadio, bookPublisherSearchRadio;

	private BookDAO bookDAO = new BookDAO();

	public static AdminBookList getInstance() {
		if (abl == null) {
			abl = new AdminBookList();
		}
		return abl;
	}

	public AdminBookList() {

		panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 900, 132);
		panel.setLayout(null);

		titleL = new JLabel("Library Management");
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 37));
		titleL.setForeground(Color.WHITE);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setBounds(0, 0, 900, 132);
		panel.add(titleL);

		String[] genre = { "전체", "소설", "만화", "역사", "과학", "예술", "문학", "철학", "종교"};
		combo = new JComboBox<String>(genre);
		combo.setBounds(15, 160, 110, 30);
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = combo.getSelectedItem().toString();
				setRow(select);
			}
		});

		searchBtn = new JButton("검색");
		searchBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		searchBtn.setBounds(802, 159, 70, 28);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bookNameSearchRadio.isSelected()) {
					if (textField.getText().equals("") || textField.getText() == null) {

						JOptionPane.showMessageDialog(AdminBookList.this, "책 제목을 입력해 주세요!", "Error!",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					setRow(textField.getText());
				} else if (bookAuthorSearchRadio.isSelected()) {
					if (textField.getText().equals("") || textField.getText() == null) {

						JOptionPane.showMessageDialog(AdminBookList.this, "검색할 저자를 입력해 주세요!", "Error!",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					setRow(1, textField.getText());
					
				} else if (bookPublisherSearchRadio.isSelected()) {
					if (textField.getText().equals("") || textField.getText() == null) {

						JOptionPane.showMessageDialog(AdminBookList.this, "검색할 출판사 입력해 주세요!", "Error!",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					setRow(2,textField.getText());
				}
			}
		});

		addBtn = new JButton("추가");
		addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		addBtn.setBounds(700, 233, 56, 25);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminBookListAdd();
			}
		});

		deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		deleteBtn.setBounds(760, 233, 56, 25);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector v = model.getDataVector();
				List<BookDTO> list = new ArrayList<BookDTO>();
				for (int i = 0; i < v.size(); i++) {
					Vector<Object> vector = new Vector<Object>();
					vector = (Vector) v.get(i);
					for (int j = 0; j < vector.size(); j += vector.size()) {
						BookDTO bookDTO = new BookDTO();
						bookDTO.setSeq(Integer.parseInt(vector.get(j).toString()));
						bookDTO.setName(vector.get(j + 1).toString());
						bookDTO.setAuthor(vector.get(j + 2).toString());
						bookDTO.setPublisher(vector.get(j + 3).toString());
						bookDTO.setGenre(vector.get(j + 4).toString());
						bookDTO.setCheck(vector.get(j + 5).toString().equals("대여가능") ? 0 : 1);

						list.add(bookDTO);
					}
				}
				int su = bookDAO.bookDelete(list.get(table.getSelectedRow()).getSeq());
				if (su != 0) {
					JOptionPane.showMessageDialog(AdminBookList.this, "삭제를 완료하였습니다", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					Thread t = new Thread(AdminBookList.this);
					t.start();
				}
			}
		});

		titleL2 = new JLabel("도서 목록");
		titleL2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		titleL2.setForeground(Color.GRAY);
		titleL2.setBounds(18, 228, 160, 30);

		bookL = new JLabel("도서코드");
		bookL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		bookL.setHorizontalAlignment(SwingConstants.CENTER);
		bookL.setForeground(Color.GRAY);
		bookL.setBounds(608, 506, 60, 25);

		updateBtn = new JButton("수정하기");
		updateBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		updateBtn.setBounds(772, 503, 100, 30);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setSeq(Integer.parseInt(textField_1.getText()));
				bookDTO.setName(model.getValueAt(table.getSelectedRow(), 1).toString());
				bookDTO.setAuthor(model.getValueAt(table.getSelectedRow(), 2).toString());
				bookDTO.setPublisher(model.getValueAt(table.getSelectedRow(), 3).toString());
				bookDTO.setGenre(model.getValueAt(table.getSelectedRow(), 4).toString());
				bookDTO.setCheck(model.getValueAt(table.getSelectedRow(), 5).toString().equals("대여가능") ? 0 : 1);
				new AdminBookListModify(bookDTO);
			}
		});

		String[] column = { "도서코드", "도서명", "저자", "출판사", "장르", "대출여부" };

		model = new DefaultTableModel(column, 0) { // 1로 해야 한줄 생김
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_1.setText((model.getValueAt(table.getSelectedRow(), 0)).toString());
			}
		});
		scroll = new JScrollPane(table);
		scroll.setBounds(20, 270, 860, 210);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 555, 900, 80);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(new Rectangle(3, 3, 3, 3));
		textField.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField.setBounds(140, 160, 650, 33);

		bookNameSearchRadio = new JRadioButton("이름", true);
		bookNameSearchRadio.setBounds(140, 130, 60, 30);

		bookAuthorSearchRadio = new JRadioButton("저자");
		bookAuthorSearchRadio.setBounds(200, 130, 60, 30);

		bookPublisherSearchRadio = new JRadioButton("출판사");
		bookPublisherSearchRadio.setBounds(260, 130, 70, 30);

		ButtonGroup bg = new ButtonGroup();
		bg.add(bookAuthorSearchRadio);
		bg.add(bookNameSearchRadio);
		bg.add(bookPublisherSearchRadio);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_1.setBounds(new Rectangle(3, 3, 3, 3));
		textField_1.setBounds(680, 506, 80, 25);

		JLabel label = new JLabel("도서개수");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(15, 506, 60, 25);
		getContentPane().add(label);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(new Rectangle(3, 3, 3, 3));
		textField_2.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_2.setBounds(87, 506, 80, 25);
		getContentPane().add(textField_2);

		JLabel label_1 = new JLabel("대여중인 책");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(211, 506, 80, 25);
		getContentPane().add(label_1);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(new Rectangle(3, 3, 3, 3));
		textField_3.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_3.setBounds(303, 506, 80, 25);
		getContentPane().add(textField_3);

		JLabel label_2 = new JLabel("폐기된 책");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_2.setBounds(405, 506, 79, 25);
		getContentPane().add(label_2);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(new Rectangle(3, 3, 3, 3));
		textField_4.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_4.setBounds(496, 506, 80, 25);
		getContentPane().add(textField_4);

		Container con = this.getContentPane();
		con.add(combo);
		con.add(searchBtn);
		con.add(addBtn);
		con.add(deleteBtn);
		con.add(titleL2);
		con.add(bookL);
		con.add(updateBtn);
		con.setLayout(null);
		con.setBackground(Color.WHITE);
		con.setForeground(Color.WHITE);
		con.add(panel);
		con.add(scroll);
		con.add(panel_1);
		con.add(textField);
		con.add(textField_1);
		con.add(bookNameSearchRadio);
		con.add(bookAuthorSearchRadio);
		con.add(bookPublisherSearchRadio);

		setTitle("관리자 도서관리");
		setBounds(500, 200, 900, 650);
		setVisible(true);
		setResizable(false);

		// 관리자 도서관리 창이 실행됨과 동시에 전체 도서목록을 테이블에 뿌려준다
		showAllBook();

		List<RentDTO> rentList = bookDAO.rentList();
		textField_3.setText(rentList.size() + "");

		List<BookDTO> deletedBookList = bookDAO.deletedBookList();
		textField_4.setText(deletedBookList.size() + "");

	}// constructor

	private void showAllBook() {
		List<BookDTO> list = bookDAO.bookList();

		textField_2.setText(list.size() + "");

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
	}

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

	@Override
	public void run() {
		if (textField.getText() == null || textField.getText().length() == 0) {
			String select = combo.getSelectedItem().toString();
			setRow(select);
		} else {
			setRow(textField.getText());
		}

		List<BookDTO> bookList = bookDAO.bookList();
		textField_2.setText(bookList.size() + "");

		List<RentDTO> rentList = bookDAO.rentList();
		textField_3.setText(rentList.size() + "");

		List<BookDTO> deletedBookList = bookDAO.deletedBookList();
		textField_4.setText(deletedBookList.size() + "");
	}
}