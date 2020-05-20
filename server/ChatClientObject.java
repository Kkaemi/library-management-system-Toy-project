package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChatClientObject extends JFrame implements ActionListener, Runnable {
	private JPanel panel, panel1, panel2;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13, btn14, btn15,
			btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29;
	private JLabel titleL;

	private Socket socket;
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private static ReturnDTO reDTO = new ReturnDTO();
	private int seat;
	InfoDTO infoDTO = null;

//		private List<JButton>list;

	public ChatClientObject() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(null);

		panel = new JPanel();// -------------------------------------질문
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 384, 132);
		getContentPane().add(panel);
		panel.setLayout(null);

		titleL = new JLabel("열람실 좌석");
		titleL.setBounds(0, 0, 384, 132);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleL.setForeground(Color.WHITE);
		panel.add(titleL);
		// ------------------------------------------------------------------------------------

		btn1 = new JButton("1");
		btn1.setBounds(25, 165, 57, 50);
		btn1.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn1);

		btn2 = new JButton("2");
		btn2.setBounds(25, 245, 57, 50);
		btn2.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn2);

		btn3 = new JButton("3");
		btn3.setBackground(new Color(176, 224, 230));
		btn3.setBounds(25, 325, 57, 50);
		getContentPane().add(btn3);

		btn4 = new JButton("4");
		btn4.setBounds(25, 405, 57, 50);
		btn4.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn4);

		btn5 = new JButton("5");
		btn5.setBounds(25, 485, 57, 50);
		btn5.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn5);

		btn6 = new JButton("6");
		btn6.setBounds(131, 175, 57, 50);
		btn6.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn6);

		btn7 = new JButton("7");
		btn7.setBackground(new Color(176, 224, 230));
		btn7.setBounds(188, 175, 57, 50);
		getContentPane().add(btn7);

		btn8 = new JButton("8");
		btn8.setBackground(new Color(176, 224, 230));
		btn8.setBounds(243, 175, 57, 50);
		getContentPane().add(btn8);

		btn9 = new JButton("9");
		btn9.setBackground(new Color(176, 224, 230));
		btn9.setBounds(299, 175, 57, 50);
		getContentPane().add(btn9);

		btn10 = new JButton("10");
		btn10.setBackground(new Color(176, 224, 230));
		btn10.setBounds(131, 224, 57, 50);
		getContentPane().add(btn10);

		btn11 = new JButton("11");
		btn11.setBackground(new Color(176, 224, 230));
		btn11.setBounds(188, 224, 57, 50);
		getContentPane().add(btn11);

		btn12 = new JButton("12");
		btn12.setBackground(new Color(176, 224, 230));
		btn12.setBounds(243, 224, 57, 50);
		getContentPane().add(btn12);

		btn13 = new JButton("13");
		btn13.setBackground(new Color(176, 224, 230));
		btn13.setBounds(299, 224, 57, 50);
		getContentPane().add(btn13);

		btn14 = new JButton("14");
		btn14.setBackground(new Color(176, 224, 230));
		btn14.setBounds(131, 291, 57, 50);
		getContentPane().add(btn14);

		btn15 = new JButton("15");
		btn15.setBackground(new Color(176, 224, 230));
		btn15.setBounds(187, 291, 57, 50);
		getContentPane().add(btn15);

		btn16 = new JButton("16");
		btn16.setBackground(new Color(176, 224, 230));
		btn16.setBounds(243, 291, 57, 50);
		getContentPane().add(btn16);

		btn17 = new JButton("17");
		btn17.setBackground(new Color(176, 224, 230));
		btn17.setBounds(299, 291, 57, 50);
		getContentPane().add(btn17);

		btn18 = new JButton("18");
		btn18.setBackground(new Color(176, 224, 230));
		btn18.setBounds(131, 340, 57, 50);
		getContentPane().add(btn18);

		btn19 = new JButton("19");
		btn19.setBounds(187, 340, 57, 50);
		btn19.setBackground(new Color(176, 224, 230));
		getContentPane().add(btn19);

		btn20 = new JButton("20");
		btn20.setBackground(new Color(176, 224, 230));
		btn20.setBounds(243, 340, 57, 50);
		getContentPane().add(btn20);

		btn21 = new JButton("21");
		btn21.setBackground(new Color(176, 224, 230));
		btn21.setBounds(299, 340, 57, 50);
		getContentPane().add(btn21);

		btn22 = new JButton("22");
		btn22.setBackground(new Color(176, 224, 230));
		btn22.setBounds(131, 415, 57, 50);
		getContentPane().add(btn22);

		btn23 = new JButton("23");
		btn23.setBackground(new Color(176, 224, 230));
		btn23.setBounds(187, 415, 57, 50);
		getContentPane().add(btn23);

		btn24 = new JButton("24");
		btn24.setBackground(new Color(176, 224, 230));
		btn24.setBounds(243, 415, 57, 50);
		getContentPane().add(btn24);

		btn25 = new JButton("25");
		btn25.setBackground(new Color(176, 224, 230));
		btn25.setBounds(299, 415, 57, 50);
		getContentPane().add(btn25);

		btn26 = new JButton("26");
		btn26.setBackground(new Color(176, 224, 230));
		btn26.setBounds(131, 464, 57, 50);
		getContentPane().add(btn26);

		btn27 = new JButton("27");
		btn27.setBackground(new Color(176, 224, 230));
		btn27.setBounds(187, 464, 57, 50);
		getContentPane().add(btn27);

		btn28 = new JButton("28");
		btn28.setBackground(new Color(176, 224, 230));
		btn28.setBounds(243, 464, 57, 50);
		getContentPane().add(btn28);

		btn29 = new JButton("29");
		btn29.setBackground(new Color(176, 224, 230));
		btn29.setBounds(299, 464, 57, 50);
		getContentPane().add(btn29);
		// ----------------------------------------------------------------------------------
		panel1 = new JPanel();
		panel1.setBackground(new Color(255, 128, 192));// ---분홍이
		panel1.setBounds(173, 560, 20, 20);
		getContentPane().add(panel1);
		panel1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("예약중");
		lblNewLabel_1.setBounds(204, 560, 57, 15);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		getContentPane().add(lblNewLabel_1);

		panel2 = new JPanel();
		panel2.setBackground(new Color(176, 224, 230));
		panel2.setBounds(275, 560, 20, 20);
		getContentPane().add(panel2);
		panel2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("사용가능");
		lblNewLabel_2.setBounds(304, 560, 57, 15);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		getContentPane().add(lblNewLabel_2);
		// ---------------------------------------------------------------------------------

		// ---------------------------------------------------------------------------------

		setTitle("열람실 예약");
		setBounds(500, 200, 400, 650);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				InfoDTO infoDTO = new InfoDTO();
				infoDTO.setCommand(Info.EXIT);
				try {
					writer.writeObject(infoDTO);
					writer.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}

			}
		});
	}

	public void service() {
		// 서버IP
		String serverIP = "127.0.0.1";

		try {
			// 소켓 생성
			socket = new Socket(serverIP, 9500);

			reader = new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}

		// 서버로 보내기
		InfoDTO infoDTO = new InfoDTO();

		// main에서 클릭하면 바로 join 보내기
		infoDTO.setCommand(Info.JOIN);
		System.out.print("hey!");

		try {
			writer.writeObject(infoDTO);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread t = new Thread(this);// 스레드 생성
		t.start();// 스레드 시작 - 스레드 실행

		// event

		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);
		btn10.addActionListener(this);
		btn11.addActionListener(this);
		btn12.addActionListener(this);
		btn13.addActionListener(this);
		btn14.addActionListener(this);
		btn15.addActionListener(this);
		btn16.addActionListener(this);
		btn17.addActionListener(this);
		btn18.addActionListener(this);
		btn19.addActionListener(this);
		btn20.addActionListener(this);
		btn21.addActionListener(this);
		btn22.addActionListener(this);
		btn23.addActionListener(this);
		btn24.addActionListener(this);
		btn25.addActionListener(this);
		btn26.addActionListener(this);
		btn27.addActionListener(this);
		btn28.addActionListener(this);
		btn29.addActionListener(this);
	}

	@Override
	public void run() {
		// 서버로부터 받기
		InfoDTO infoDTO = null;
		ReturnDTO returnDTO = null;

		while (true) {
			try {
				infoDTO = (InfoDTO) reader.readObject();

				if (infoDTO.getCommand() == Info.EXIT) {
					reader.close();
					writer.close();
					socket.close();

					break;

//					System.exit(0);

				}

				this.setBack(infoDTO);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} // while
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 서버로 보내기
//		   int seat;
		InfoDTO infoDTO = new InfoDTO();

//--서버로 보내기---------------------------------------------------------------

		if (e.getSource() == btn1)
			this.seatCheck(infoDTO, 1);
		else if (e.getSource() == btn2)
			this.seatCheck(infoDTO, 2);
		else if (e.getSource() == btn3)
			this.seatCheck(infoDTO, 3);
		else if (e.getSource() == btn4)
			this.seatCheck(infoDTO, 4);
		else if (e.getSource() == btn5)
			this.seatCheck(infoDTO, 5);
		else if (e.getSource() == btn6)
			this.seatCheck(infoDTO, 6);
		else if (e.getSource() == btn7)
			this.seatCheck(infoDTO, 7);
		else if (e.getSource() == btn8)
			this.seatCheck(infoDTO, 8);
		else if (e.getSource() == btn9)
			this.seatCheck(infoDTO, 9);
		else if (e.getSource() == btn10)
			this.seatCheck(infoDTO, 10);
		else if (e.getSource() == btn11)
			this.seatCheck(infoDTO, 11);
		else if (e.getSource() == btn12)
			this.seatCheck(infoDTO, 12);
		else if (e.getSource() == btn13)
			this.seatCheck(infoDTO, 13);
		else if (e.getSource() == btn14)
			this.seatCheck(infoDTO, 14);
		else if (e.getSource() == btn15)
			this.seatCheck(infoDTO, 15);
		else if (e.getSource() == btn16)
			this.seatCheck(infoDTO, 16);
		else if (e.getSource() == btn17)
			this.seatCheck(infoDTO, 17);
		else if (e.getSource() == btn18)
			this.seatCheck(infoDTO, 18);
		else if (e.getSource() == btn19)
			this.seatCheck(infoDTO, 19);
		else if (e.getSource() == btn20)
			this.seatCheck(infoDTO, 20);
		else if (e.getSource() == btn21)
			this.seatCheck(infoDTO, 21);
		else if (e.getSource() == btn22)
			this.seatCheck(infoDTO, 22);
		else if (e.getSource() == btn23)
			this.seatCheck(infoDTO, 23);
		else if (e.getSource() == btn24)
			this.seatCheck(infoDTO, 24);
		else if (e.getSource() == btn25)
			this.seatCheck(infoDTO, 25);
		else if (e.getSource() == btn26)
			this.seatCheck(infoDTO, 26);
		else if (e.getSource() == btn27)
			this.seatCheck(infoDTO, 27);
		else if (e.getSource() == btn28)
			this.seatCheck(infoDTO, 28);
		else if (e.getSource() == btn29)
			this.seatCheck(infoDTO, 29);

	}

	public void seatCheck(InfoDTO infoDTO, int seat) {

		if (reDTO.getReturnCheck() == 1) {
			int result2 = JOptionPane.showConfirmDialog(null, "좌석을 반납 하시겠습니까?", "좌석 반납", JOptionPane.YES_NO_OPTION);
			if (result2 == 0) {

				infoDTO.setSeatNum(seat);
				infoDTO.setSeatCheck(-1);
				reDTO.setReturnCheck(0);

			}

		} else if (reDTO.getReturnCheck() == 0) {
			int result = JOptionPane.showConfirmDialog(null, "좌석을 예약 하시겠습니까?", "좌석 예약", JOptionPane.YES_NO_OPTION);
			if (result == 0) {

				infoDTO.setSeatNum(seat);
				infoDTO.setSeatCheck(1);
				reDTO.setReturnCheck(1);

			}

		}
		try {
			writer.writeObject(infoDTO);
			writer.flush();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	public void setBack(InfoDTO infoDTO) {

		// for - array 처리 가능?

		if (infoDTO.getSeatNum() == 1 && infoDTO.getSeatCheck() == 2)
			btn1.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 40 && infoDTO.getSeatCheck() == -1)
			btn1.setBackground(new Color(176, 224, 230));

		if (infoDTO.getSeatNum() == 2 && infoDTO.getSeatCheck() == 2) {
			btn2.setBackground(new Color(255, 128, 192));
		} else if (infoDTO.getSeatNum() == 2 && infoDTO.getSeatCheck() == -1)
			btn2.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 3 && infoDTO.getSeatCheck() == 2)
			btn3.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 3 && infoDTO.getSeatCheck() == -1)
			btn3.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 4 && infoDTO.getSeatCheck() == 2)
			btn4.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 4 && infoDTO.getSeatCheck() == -1)
			btn4.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 5 && infoDTO.getSeatCheck() == 2)
			btn5.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 5 && infoDTO.getSeatCheck() == -1)
			btn5.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 6 && infoDTO.getSeatCheck() == 2)
			btn6.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 6 && infoDTO.getSeatCheck() == -1)
			btn6.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 7 && infoDTO.getSeatCheck() == 2)
			btn7.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 7 && infoDTO.getSeatCheck() == -1)
			btn7.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 8 && infoDTO.getSeatCheck() == 2)
			btn8.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 8 && infoDTO.getSeatCheck() == -1)
			btn8.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 9 && infoDTO.getSeatCheck() == 2)
			btn9.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 9 && infoDTO.getSeatCheck() == -1)
			btn9.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 10 && infoDTO.getSeatCheck() == 2)
			btn10.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 10 && infoDTO.getSeatCheck() == -1)
			btn10.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 11 && infoDTO.getSeatCheck() == 2)
			btn11.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 11 && infoDTO.getSeatCheck() == -1)
			btn11.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 12 && infoDTO.getSeatCheck() == 2)
			btn12.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 12 && infoDTO.getSeatCheck() == -1)
			btn12.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 13 && infoDTO.getSeatCheck() == 2)
			btn13.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 13 && infoDTO.getSeatCheck() == -1)
			btn13.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 14 && infoDTO.getSeatCheck() == 2)
			btn14.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 14 && infoDTO.getSeatCheck() == -1)
			btn14.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 15 && infoDTO.getSeatCheck() == 2)
			btn15.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 15 && infoDTO.getSeatCheck() == -1)
			btn15.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 16 && infoDTO.getSeatCheck() == 2)
			btn16.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 16 && infoDTO.getSeatCheck() == -1)
			btn16.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 17 && infoDTO.getSeatCheck() == 2)
			btn17.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 17 && infoDTO.getSeatCheck() == -1)
			btn17.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 18 && infoDTO.getSeatCheck() == 2)
			btn18.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 18 && infoDTO.getSeatCheck() == -1)
			btn18.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 19 && infoDTO.getSeatCheck() == 2)
			btn19.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 19 && infoDTO.getSeatCheck() == -1)
			btn19.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 20 && infoDTO.getSeatCheck() == 2)
			btn20.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 20 && infoDTO.getSeatCheck() == -1)
			btn20.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 21 && infoDTO.getSeatCheck() == 2)
			btn21.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 21 && infoDTO.getSeatCheck() == -1)
			btn21.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 22 && infoDTO.getSeatCheck() == 2)
			btn22.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 22 && infoDTO.getSeatCheck() == -1)
			btn22.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 23 && infoDTO.getSeatCheck() == 2)
			btn23.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 23 && infoDTO.getSeatCheck() == -1)
			btn23.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 24 && infoDTO.getSeatCheck() == 2)
			btn24.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 24 && infoDTO.getSeatCheck() == -1)
			btn24.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 25 && infoDTO.getSeatCheck() == 2)
			btn25.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 25 && infoDTO.getSeatCheck() == -1)
			btn25.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 26 && infoDTO.getSeatCheck() == 2)
			btn26.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 26 && infoDTO.getSeatCheck() == -1)
			btn26.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 27 && infoDTO.getSeatCheck() == 2)
			btn27.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 27 && infoDTO.getSeatCheck() == -1)
			btn27.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 28 && infoDTO.getSeatCheck() == 2)
			btn28.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 28 && infoDTO.getSeatCheck() == -1)
			btn28.setBackground(new Color(176, 224, 230));

		else if (infoDTO.getSeatNum() == 29 && infoDTO.getSeatCheck() == 2)
			btn29.setBackground(new Color(255, 128, 192));
		else if (infoDTO.getSeatNum() == 29 && infoDTO.getSeatCheck() == -1)
			btn29.setBackground(new Color(176, 224, 230));

	}/// setback

//	    @Override
//	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//	        btn1.setEnabled(!isChecked);
//	        btn2.setEnabled(!isChecked);
//	    }

//	public static void main(String[] args) {
//		new ChatClientObject().service();
//	}
}
