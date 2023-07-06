package ch25_gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIMain2 extends JFrame {

	private JPanel mainCardPane;
	private CardLayout mainCardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain2 frame = new GUIMain2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIMain2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		/* <<< mainCardPane >>> */
		mainCardPane = new JPanel();
		mainCardLayout = new CardLayout(0, 0);
		
		mainCardPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainCardPane.setLayout(mainCardLayout);
		
		setContentPane(mainCardPane);
		
		/* <<< subPanel1 >>> */
		JPanel subPanel1 = new JPanel();
		mainCardPane.add(subPanel1, "subPanel1");
		subPanel1.setLayout(null);
		
	
		JButton subPanel2ShowBtn = new JButton("2번페이지");
		subPanel2ShowBtn.setBounds(323, 105, 91, 23);
		subPanel1.add(subPanel2ShowBtn);
		
		subPanel2ShowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainCardLayout.show(mainCardPane, "subPanel2");	//mainCardPane에 들어있는 객체 중에서 subPanel2 의 값을 들고있는친구를 보여줘라
			}
		});
		
		
		/* <<< subPanel2 >>> */
		JPanel subPanel2 = new JPanel();
		mainCardPane.add(subPanel2, "subPanel2");
		subPanel2.setLayout(null);
		
		JButton subPanel1ShowBtn = new JButton("1번페이지");
		subPanel1ShowBtn.setBounds(12, 105, 91, 23);
		subPanel2.add(subPanel1ShowBtn);
		
		subPanel1ShowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainCardLayout.show(mainCardPane, "subPanel1");	//mainCardPane에 들어있는 객체 중에서 subPanel2 의 값을 들고있는친구를 보여줘라
			}
		});
		
		JButton subPanel3ShowBtn = new JButton("3번페이지");
		subPanel3ShowBtn.setBounds(323, 105, 91, 23);
		subPanel2.add(subPanel3ShowBtn);
		
		subPanel3ShowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainCardLayout.show(mainCardPane, "subPanel3");	//mainCardPane에 들어있는 객체 중에서 subPanel2 의 값을 들고있는친구를 보여줘라
			}
		});
		
		
		/* <<< subPanel3 >>> */
		JPanel subPanel3 = new JPanel();
		mainCardPane.add(subPanel3, "subPanel3");
		subPanel3.setLayout(null);
		
		JButton subPanel2ShowBtn2 = new JButton("2번페이지");
		subPanel2ShowBtn2.setBounds(12, 105, 91, 23);
		subPanel3.add(subPanel2ShowBtn2);
		
		subPanel2ShowBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainCardLayout.show(mainCardPane, "subPanel2");	//mainCardPane에 들어있는 객체 중에서 subPanel2 의 값을 들고있는친구를 보여줘라
			}
		});
		
		
		
	}
}
