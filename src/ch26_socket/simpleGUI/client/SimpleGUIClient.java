package ch26_socket.simpleGUI.client;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ch26_socket.simpleGUI.client.dto.RequestBodyDto;
import ch26_socket.simpleGUI.client.dto.SendMessage;
import lombok.Getter;

@Getter
public class SimpleGUIClient extends JFrame {
	
	private static SimpleGUIClient instance;
	public static SimpleGUIClient getInstance() {
		if(instance == null) {
			instance = new SimpleGUIClient();
		}
		return instance;
	}
	
	//맴버변수
	private String username;
	private Socket socket;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	
	private JScrollPane userListScrollPane;
	private DefaultListModel<String> userListModel;
	private JList userList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleGUIClient frame = SimpleGUIClient.getInstance();	//싱글톤 getInstance로 꺼내야함
					frame.setVisible(true);
					
					ClientReceiver clientReceiver = new ClientReceiver();
					clientReceiver.start();
					
					//어떤 클라이언트가 로그인 했는지 확인요청
					RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("Join", frame.username);
					ClientSender.getInstance().send(requestBodyDto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SimpleGUIClient() {
			
		username = JOptionPane.showInputDialog(contentPane, "아이디를 입력하세요.");
		
		if(Objects.isNull(username)) {
			System.exit(0);	//빈값일 경우 프로그램 종료
		}
		
		if(username.isBlank()) {
			System.exit(0);	//빈칸일 경우 프로그램 종료
		}
		
		try {
			socket = new Socket("127.0.0.1", 8000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 293, 208);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					SendMessage sendMessage = SendMessage.builder()
							.fromUsername(username)
							.messageBody(textField.getText())
							.build();
					
					RequestBodyDto<SendMessage> requestBodyDto = 
							new RequestBodyDto<>("sendMessage", sendMessage);
					
					ClientSender.getInstance().send(requestBodyDto);
					textField.setText("");
				}
			}
		});
		
		textField.setBounds(12, 228, 410, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		userListScrollPane = new JScrollPane();
		userListScrollPane.setBounds(317, 10, 105, 208);
		contentPane.add(userListScrollPane);
		
		userListModel = new DefaultListModel<>();
		userList = new JList(userListModel);
		userListScrollPane.setViewportView(userList);
		
	}
}