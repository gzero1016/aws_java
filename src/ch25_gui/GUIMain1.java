package ch25_gui;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUIMain1 extends JFrame {
	
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "1234";

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private CardLayout mainCardLayout;


	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		GUIMain1 frame = new GUIMain1();
		frame.setVisible(true);

	}
	/**
	 * Create the frame.
	 */
	public GUIMain1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 358);
		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(148, 93, 240, 37);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JButton signinBtn = new JButton("Login");
		signinBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameTextField.getText();
				String password= passwordTextField.getText();
				if(!username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) {	//둘중 하나라도 틀리면 사용자 정보가 일치하지 않다라고 출력
					JOptionPane.showMessageDialog(contentPane, "사용자 정보가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
													// 어느위치에 띄울거냐, 내용, 제목, 아이콘
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "환영합니다.", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
												// 어느위치에 띄울거냐, 내용, 제목, 아이콘				
			}
		});
		
		signinBtn.setBounds(148, 236, 240, 37);
		contentPane.add(signinBtn);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(146, 141, 242, 37);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);
	}
}
