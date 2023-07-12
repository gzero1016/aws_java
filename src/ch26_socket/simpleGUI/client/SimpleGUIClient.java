package ch26_socket.simpleGUI.client;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	
	
	private CardLayout mainCardLayout;
	private JPanel mainCardPanel;
	
	
	private JPanel chattingRoomListPanel;
	private JScrollPane roomListScrollPanel;
	private DefaultListModel<String> roomListModel;
	private JList roomList;
	
	
	//채팅룸 안쪽
	private JPanel chattingRoomPanel;
	private JTextField messageTextField;
	private JTextArea chattingTextArea;
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
					RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("connection", frame.username);
					ClientSender.getInstance().send(requestBodyDto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public SimpleGUIClient() {
			
		username = JOptionPane.showInputDialog(chattingRoomPanel, "아이디를 입력하세요.");
		
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
		
		mainCardLayout = new CardLayout();
		mainCardPanel = new JPanel();
		mainCardPanel.setLayout(mainCardLayout);
		setContentPane(mainCardPanel);
		
		chattingRoomListPanel = new JPanel();
		chattingRoomListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chattingRoomListPanel.setLayout(null);
		mainCardPanel.add(chattingRoomListPanel, "chattingRoomListPanel");
		
		JButton createRoomButton = new JButton("방만들기");
		createRoomButton.setBounds(10, 10, 100, 30);	//버튼의 모양
		createRoomButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String roomName = JOptionPane.showInputDialog(chattingRoomListPanel, "방제목을 입력하세요.");
				if(Objects.isNull(roomName)) {	//취소버튼을 눌렀을 경우 null 이 들어가면서 이벤트 종료
					return;
				}
				if(roomName.isBlank()) {	//방제목에 공백이 들어갔을 시 에러메시지를 띄어주면서 리턴걸어서 빠져나감.
					JOptionPane.showMessageDialog(chattingRoomListPanel, "방제목을 입력하세요.", "방만들기 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// roomListModel 안에 roomName이 들어있는데 동일한 방제목이 있을 시 에러메시지 띄어주고 리턴(마우스 이벤트 메소드 빠져나가는 리턴)
				for(int i = 0; i < roomListModel.size(); i++) {
					if(roomListModel.get(i).equals(roomName)) {
						JOptionPane.showMessageDialog(chattingRoomListPanel, "이미 존재하는 방제목입니다.", "방만들기 실패", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				// 정상적인 방제목입력 할 시 정상 처리
				RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("createRoom", roomName);
				ClientSender.getInstance().send(requestBodyDto);
				mainCardLayout.show(mainCardPanel, "chattingRoomPanel"); //메인카드패널에서 채팅룸패널로 이동
				requestBodyDto = new RequestBodyDto<String>("join", roomName);
				ClientSender.getInstance().send(requestBodyDto);
			}
		});
		
		chattingRoomListPanel.add(createRoomButton);	//어디에 추가할지 위치
		
		roomListScrollPanel = new JScrollPane(); 
		roomListScrollPanel.setBounds(10, 50, 414, 201);
		chattingRoomListPanel.add(roomListScrollPanel);
		
		roomListModel = new DefaultListModel<String>();
		roomList = new JList<>(roomListModel);
		roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {	//두번클릭 확인
					String roomName = roomListModel.get(roomList.getSelectedIndex());	//선택된 방 인덱스번호를 가져와 모델에서 get 해서 roomName 가져옴
					mainCardLayout.show(mainCardPanel, "chattingRoomPanel"); //메인카드패널에서 채팅룸패널로 이동
					RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("join", roomName);
					ClientSender.getInstance().send(requestBodyDto);
				}
			}
		});
		
		roomListScrollPanel.setViewportView(roomList);
		
		
		
		
		chattingRoomPanel = new JPanel();
		chattingRoomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chattingRoomPanel.setLayout(null);
		mainCardPanel.add(chattingRoomPanel, "chattingRoomPanel");
		
		JScrollPane chattingTextAreaScrollPane = new JScrollPane();
		chattingTextAreaScrollPane.setBounds(12, 10, 293, 208);
		chattingRoomPanel.add(chattingTextAreaScrollPane);
		
		chattingTextArea = new JTextArea();
		chattingTextAreaScrollPane.setViewportView(chattingTextArea);
		
		messageTextField = new JTextField();
		messageTextField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					SendMessage sendMessage = SendMessage.builder()
							.fromUsername(username)
							.messageBody(messageTextField.getText())
							.build();
					
					RequestBodyDto<SendMessage> requestBodyDto = 
							new RequestBodyDto<>("sendMessage", sendMessage);
					
					ClientSender.getInstance().send(requestBodyDto);
					messageTextField.setText("");
				}
			}
		});
		
		messageTextField.setBounds(12, 228, 410, 23);
		chattingRoomPanel.add(messageTextField);
		messageTextField.setColumns(10);
		
		userListScrollPane = new JScrollPane();
		userListScrollPane.setBounds(317, 10, 105, 208);
		chattingRoomPanel.add(userListScrollPane);
		
		userListModel = new DefaultListModel<>();
		userList = new JList(userListModel);
		userListScrollPane.setViewportView(userList);
		
		
	}
}