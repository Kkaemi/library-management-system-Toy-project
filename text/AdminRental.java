package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import library.bean.BookDTO;
import library.bean.MemberDTO;
import library.bean.RentDTO;
import library.dao.BookDAO;
import library.dao.MemberDAO;

public class AdminRental extends JFrame implements ActionListener { // 대여관리
	private JLabel titleL, listL, listLid, listLname, listLyear, bookL, bookLcode, bookLname, bookLwr, bookLcom;
	private JButton searchBtn1, searchBtn2, rentalBtn, returnBtn, delBtn;
	private JPanel panel, panel_1;
	private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	
	private MemberDAO memberDAO = new MemberDAO();
	private BookDAO bookDAO = new BookDAO();

	public AdminRental() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);

		panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 900, 132);
		getContentPane().add(panel);
		panel.setLayout(null);

		titleL = new JLabel("Library Management");
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		titleL.setForeground(Color.WHITE);
		titleL.setBounds(0, 0, 884, 132);
		panel.add(titleL);

		listL = new JLabel("회원 목록");
		listL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		// listL.setForeground(Color.GRAY);
		listL.setBounds(18, 151, 170, 30);

		listLid = new JLabel("회원 ID");
		listLid.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		listLid.setForeground(Color.GRAY);
		listLid.setBounds(28, 191, 50, 30);

		// 회원 검색
		searchBtn1 = new JButton("검색");
		searchBtn1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		searchBtn1.setBounds(290, 196, 70, 25);

		listLname = new JLabel("이름");
		listLname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		listLname.setForeground(Color.GRAY);
		listLname.setBounds(28, 247, 50, 30);

		listLyear = new JLabel("생년월일");
		listLyear.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		listLyear.setForeground(Color.GRAY);
		listLyear.setBounds(195, 247, 70, 30);

		// -------------------------------------------------------------------------------회원목록
		// bookL, bookLcode, bookLname, bookLwr,bookLcom;
		// bookT, bookTcode, bookTname, bookTwr,bookTcom;
		bookL = new JLabel("도서 정보");
		bookL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		// bookL.setForeground(Color.GRAY);
		bookL.setBounds(473, 151, 170, 30);

		bookLcode = new JLabel("도서코드");
		bookLcode.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		bookLcode.setForeground(Color.GRAY);
		bookLcode.setBounds(483, 178, 60, 30);

		// 도서코드 검색
		searchBtn2 = new JButton("검색");
		searchBtn2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		searchBtn2.setBounds(767, 180, 70, 25);

		bookLname = new JLabel("도서명");
		bookLname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		bookLname.setForeground(Color.GRAY);
		bookLname.setBounds(493, 217, 70, 30);

		bookLwr = new JLabel("저자");
		bookLwr.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		bookLwr.setForeground(Color.GRAY);
		bookLwr.setBounds(667, 217, 70, 30);

		bookLcom = new JLabel("출판사");
		bookLcom.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		bookLcom.setForeground(Color.GRAY);

		bookLcom.setBounds(493, 257, 70, 30);
		// -------------------------------------------------------------------------------도서정보

		rentalBtn = new JButton("대여");
		rentalBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		rentalBtn.setBounds(573, 297, 70, 30);
		rentalBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RentDTO rentDTO = new RentDTO();
				rentDTO = bookDAO.bookRent(textField.getText(), textField_3.getText());
				
				Vector<Object> vector = new Vector<Object>();
				vector.add(rentDTO.getMember_id());
				vector.add(rentDTO.getMember_name());
				vector.add(rentDTO.getBook_seq());
				vector.add(rentDTO.getBook_name());
				vector.add(rentDTO.getRentDate());
				vector.add(rentDTO.getReturnDate());
				
				// bookDTO의 Check 항목도 변경해 준다
				int check = bookDAO.rentBook(rentDTO.getBook_seq());
				if (check == 0) {
					JOptionPane.showMessageDialog(null
							, "이미 대여중인 책을 대여 할 수 없습니다!"
							, "Error"
							, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int su = bookDAO.rentInsert(rentDTO);
				if (su != 0) {
					JOptionPane.showMessageDialog(null
							, "정상적으로 처리되었습니다"
							, "Info"
							, JOptionPane.INFORMATION_MESSAGE);
				}
				
				model.addRow(vector);
			}
		});

		returnBtn = new JButton("반납");
		returnBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		returnBtn.setBounds(655, 297, 72, 30);
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bookCode = (int)(model.getValueAt(table.getSelectedRow(), 2));
				bookDAO.returnBook(bookCode);
				
				JOptionPane.showMessageDialog(null
						, "책이 정상적으로 반납되었습니다"
						, "Info"
						, JOptionPane.INFORMATION_MESSAGE);
				
				model.setRowCount(0);
				List<RentDTO> list = bookDAO.rentList();
				for (RentDTO dto : list) {
					Vector<Object> vector = new Vector<Object>();
					vector.add(dto.getMember_id());
					vector.add(dto.getMember_name());
					vector.add(dto.getBook_seq());
					vector.add(dto.getBook_name());
					vector.add(dto.getRentDate());
					vector.add(dto.getReturnDate());
					
					model.addRow(vector);
				}
			}
			});

		delBtn = new JButton("지우기");
		delBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		delBtn.setBounds(739, 297, 72, 30);
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
			}
		});

		getContentPane().setLayout(null);

		// -------------------------------------------------------------------------------
		getContentPane().add(listL);
		getContentPane().add(listLid);
		getContentPane().add(searchBtn1);
		getContentPane().add(listLname);
		getContentPane().add(listLyear);

		getContentPane().add(bookL);
		getContentPane().add(bookLcode);
		getContentPane().add(searchBtn2);
		getContentPane().add(bookLname);
		getContentPane().add(bookLwr);
		getContentPane().add(bookLcom);

		getContentPane().add(rentalBtn);
		getContentPane().add(returnBtn);
		getContentPane().add(delBtn);
		// -------------------------------------------------------------------------------
		
		String[] column = { "회원 ID", "이름", "책 코드", "책 제목", "대여일", "반납일" };

		model = new DefaultTableModel(column, 0) {
			public boolean isCellEditable(int r, int c) {return false;}
		};
		// -------------------------------------------------------------------------------
		table = new JTable(model);

		scroll = new JScrollPane(table);
		scroll.setBounds(10, 337, 880, 220);

		getContentPane().add(scroll);
		
		// 밑 배경
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 570, 900, 60);
		getContentPane().add(panel_1);

		// 회원 ID
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(new Rectangle(3, 3, 3, 3));
		textField.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField.setBounds(83, 191, 198, 33);
		getContentPane().add(textField);

		// 이름
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(new Rectangle(3, 3, 3, 3));
		textField_1.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_1.setBounds(78, 247, 110, 33);
		textField_1.setEnabled(false);
		getContentPane().add(textField_1);
		
		// 생년월일
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(new Rectangle(3, 3, 3, 3));
		textField_2.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_2.setBounds(260, 247, 116, 33);
		textField_2.setEnabled(false);
		getContentPane().add(textField_2);

		// 도서코드
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(new Rectangle(3, 3, 3, 3));
		textField_3.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_3.setBounds(555, 178, 198, 33);
		getContentPane().add(textField_3);

		// 도서명
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(new Rectangle(3, 3, 3, 3));
		textField_4.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_4.setBounds(555, 223, 100, 33);
		textField_4.setEnabled(false);
		getContentPane().add(textField_4);

		// 저자
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(new Rectangle(3, 3, 3, 3));
		textField_5.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_5.setBounds(706, 223, 132, 33);
		textField_5.setEnabled(false);
		getContentPane().add(textField_5);

		// 출판사
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(new Rectangle(3, 3, 3, 3));
		textField_6.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		textField_6.setBounds(555, 264, 155, 30);
		textField_6.setEnabled(false);
		getContentPane().add(textField_6);

		setTitle("대여 관리");
		setBounds(500, 200, 900, 650);
		setVisible(true);
		
		// 생성과 동시에 리스트를 모델에 추가한다
		List<RentDTO> list = bookDAO.rentList();
		for (RentDTO dto : list) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(dto.getMember_id());
			vector.add(dto.getMember_name());
			vector.add(dto.getBook_seq());
			vector.add(dto.getBook_name());
			vector.add(dto.getRentDate());
			vector.add(dto.getReturnDate());
			
			model.addRow(vector);
		}
		
		searchBtn1.addActionListener(this);
		searchBtn2.addActionListener(this);

	}// AdminRental Constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn1) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO = memberDAO.findMemberById(textField.getText());
			textField_1.setText(memberDTO.getName());
			textField_2.setText(memberDTO.getBirth()+"");
		} else if (e.getSource() == searchBtn2) {
			BookDTO bookDTO = new BookDTO();
			bookDTO = bookDAO.findBookByCode(textField_3.getText());
			textField_4.setText(bookDTO.getName());
			textField_5.setText(bookDTO.getAuthor());
			textField_6.setText(bookDTO.getPublisher());
		}
	}
	
	public static void main(String[] args) {
		new AdminRental();
	}

}