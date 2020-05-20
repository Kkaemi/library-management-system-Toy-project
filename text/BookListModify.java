package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
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
import javax.swing.border.LineBorder;

import library.bean.BookDTO;
import library.dao.BookDAO;

class AdminBookListModify {
	private JTextField bookName, name, publisher;
	private BookDTO bookDTO;

	public AdminBookListModify(BookDTO dto) {
		this.bookDTO = dto;

		JFrame frame2 = new JFrame();
		frame2.getContentPane().setBackground(Color.WHITE);
		frame2.setBounds(500, 200, 400, 650);
		frame2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("BookList Modify");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 360, 109);
		frame2.getContentPane().add(lblNewLabel);

		bookName = new JTextField(bookDTO.getName());
		bookName.setBounds(new Rectangle(3, 3, 3, 3));
		bookName.setColumns(10);
		bookName.setBounds(114, 210, 225, 33);
		bookName.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		frame2.getContentPane().add(bookName);

		JLabel label = new JLabel("도서명");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(32, 163, 67, 33);
		frame2.getContentPane().add(label);

		name = new JTextField(bookDTO.getAuthor());
		name.setBounds(new Rectangle(3, 3, 3, 3));
		name.setColumns(10);
		name.setBounds(32, 320, 307, 33);
		name.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		frame2.getContentPane().add(name);

		JLabel label_1 = new JLabel("저  자");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(32, 268, 67, 33);
		frame2.getContentPane().add(label_1);

		publisher = new JTextField(bookDTO.getPublisher());
		publisher.setBounds(new Rectangle(3, 3, 3, 3));
		publisher.setColumns(10);
		publisher.setBounds(32, 420, 307, 33);
		publisher.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		frame2.getContentPane().add(publisher);

		JLabel label_2 = new JLabel("출판사");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setBounds(32, 377, 67, 33);
		frame2.getContentPane().add(label_2);

		JButton btnNewButton = new JButton("수  정");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(78, 533, 97, 42);
		frame2.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookDTO.setName(bookName.getText());
				bookDTO.setAuthor(name.getText());
				bookDTO.setPublisher(publisher.getText());
				int su = new BookDAO().bookUpdate(bookDTO);

				if (su != 0) {
					JOptionPane.showMessageDialog(null, "정상적으로 처리되었습니다", "Info", JOptionPane.INFORMATION_MESSAGE);
				}

				// refresh
				Thread t = new Thread(AdminBookList.getInstance());
				t.start();
			}
		});

		JButton button = new JButton("취 소");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);

			}
		});

		button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button.setBounds(219, 533, 97, 42);
		frame2.getContentPane().add(button);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 129);
		panel.setBackground(new Color(176, 224, 230));
		frame2.getContentPane().add(panel);

		String[] genre = { "전체", "소설", "만화", "역사", "과학", "예술", "문학", "철학", "종교" };
		JComboBox<String> comboBox = new JComboBox<String>(genre);
		comboBox.setBounds(30, 210, 80, 33);
		comboBox.setSelectedItem(bookDTO.getGenre());
		frame2.getContentPane().add(comboBox);

		frame2.setVisible(true);

	}
}