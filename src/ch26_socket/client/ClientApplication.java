package ch26_socket.client;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientApplication extends JFrame {
	
	private Socket socket;	//전역변수

	private JPanel mainPanel;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JScrollPane connectedUserListscrollPane;
	private JTextField messageTextField;
	private JTextArea chatTestArea;
	private JButton messageSendButton;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApplication frame = new ClientApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ClientApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 899);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		// << 채팅내용 >>
		JScrollPane chatTextAreaScrollPane = new JScrollPane();
		chatTextAreaScrollPane.setBounds(12, 23, 609, 759);
		mainPanel.add(chatTextAreaScrollPane);
		
		JTextArea chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);	//채팅 못하게함
		chatTextAreaScrollPane.setViewportView(chatTextArea);
		
		// << 채팅연결 >>
		ipTextField = new JTextField();
		ipTextField.setBounds(633, 23, 181, 35);
		mainPanel.add(ipTextField);
		ipTextField.setColumns(10);
		
		
		portTextField = new JTextField();
		portTextField.setBounds(633, 68, 181, 35);
		mainPanel.add(portTextField);
		portTextField.setColumns(10);
		
		JButton conectionButton = new JButton("접속");
		conectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String serverIp = ipTextField.getText();
				String serverPort = portTextField.getText();
				
				if(serverIp.isBlank() || serverPort.isBlank()) {
					JOptionPane.showMessageDialog(mainPanel, 
							"IP와 PORT번호를 입력해주세요.", 
							"접속 오류", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					socket = new Socket(serverIp, Integer.parseInt(serverPort));
					JOptionPane.showMessageDialog(
							mainPanel,
							"서버와의 연결에 성공하였습니다.",
							"접속 완료",
							JOptionPane.PLAIN_MESSAGE);
					messageTextField.setEditable(true);	//false로 주면 입력안됨
					messageSendButton.setEnabled(true);
					
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		conectionButton.setBounds(633, 113, 181, 35);
		mainPanel.add(conectionButton);
		
		// << 접속자 >>
		JScrollPane connectedUserListscrollPane = new JScrollPane();
		connectedUserListscrollPane.setBounds(633, 158, 181, 621);
		mainPanel.add(connectedUserListscrollPane);
		
		JList connectedUserList = new JList();
		connectedUserListscrollPane.setViewportView(connectedUserList);
		
		// << 메세지입력 및 전송 >>
		messageTextField = new JTextField();
		messageTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { 
				//keyPressed : 입력이 일어날때마다 키 이벤트라는 객체로 매개변수 전달
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("전송");
				}
			}
		});
		messageTextField.setBounds(12, 792, 677, 60);
		messageTextField.setEditable(false);
		mainPanel.add(messageTextField);
		messageTextField.setColumns(10);
		 
		messageSendButton = new JButton("전송");	//선언을 위로올리고 생성만 밑에서
//		선언이 밑에 있으면 위에서 setEnabled를 true로 만들 수 없다.
		messageSendButton.setBounds(701, 789, 112, 63);
		messageSendButton.setEnabled(false);
		mainPanel.add(messageSendButton);
	}
}