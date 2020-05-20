package text;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

class AdminBlackList {
	private JTextField textField;
    private DefaultTableModel model;
    private Vector<String> vector;
    private JTable table;
	
	public AdminBlackList() {
	JFrame jf = new JFrame();
	
	jf.getContentPane().setBackground(Color.WHITE);
    jf.getContentPane().setForeground(Color.WHITE);
    jf.getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBackground(new Color(176, 224, 230));
    panel.setBounds(0, 0, 884, 120);
    jf.getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("BlackList");
    lblNewLabel.setBounds(12, 10, 860, 100);
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 41));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);
    
    JButton btnNewButton = new JButton("새로고침");
    btnNewButton.setFont(new Font("굴림", Font.BOLD, 12));
    btnNewButton.setBounds(557, 166, 97, 36);
    jf.getContentPane().add(btnNewButton);
    
    JButton button = new JButton("문자 보내기");
    button.setFont(new Font("굴림", Font.BOLD, 10));
    button.setBounds(666, 167, 97, 36);
    jf.getContentPane().add(button);
    
    JButton button_1 = new JButton("모두 보내기");
    button_1.setFont(new Font("굴림", Font.BOLD, 10));
    button_1.setBounds(775, 167, 97, 36);
    jf.getContentPane().add(button_1);
    
    vector = new Vector<String>();
    vector.addElement("ID");   vector.add("이름");   vector.add("번호");  
    vector.add("도서코드");   vector.add("도서이름");   vector.add("연체일");

    model = new DefaultTableModel(vector, 1){ // 1로 해야 한줄 생김 
       public boolean isCellEditable(int r, int c){
      	 return (c != 0) ? true : false; // 타이틀 수정못하게 하는 부분 
       }
    };
    
    JTable table = new JTable(model);
    
    JScrollPane scroll = new JScrollPane(table); 
    scroll.setSize(860, 290);
    scroll.setLocation(12, 213);
    jf.getContentPane().add(scroll);
    
    JPanel panel_1 = new JPanel();
    panel_1.setLayout(null);
    panel_1.setBackground(new Color(176, 224, 230));
    panel_1.setBounds(0, 555, 884, 56);
    jf.getContentPane().add(panel_1);
    
	jf.setVisible(true);
	jf.setBounds(500, 200, 900, 650);
	
	}
    
}
