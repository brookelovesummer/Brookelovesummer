

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SeverJffame extends JFrame {

	private JPanel contentPane;
	private JTextField SevertextField;
	private JTextArea SevertextArea;
	private SeverJffame  severjfame;
	private  PrintWriter os;
	
	public static void main(String[] args) throws Exception {
		System.out.println("love");
		new SeverJffame(5001);
	}
	
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public SeverJffame(int port) throws Exception {
		
		setTitle("\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		SevertextField = new JTextField();
		SevertextField.setColumns(10);

		JButton SeverSend = new JButton("Send");
		SeverSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("123456789");
					
					actionperformed(arg0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		SevertextArea = new JTextArea();
		SevertextArea.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(SevertextArea, Alignment.TRAILING)
						.addComponent(SevertextField, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(SeverSend)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(SevertextArea, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(SevertextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(SeverSend))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);

		ServerSocket ss = new ServerSocket(port);	
		Socket socket=ss.accept();
		
		this.os = new PrintWriter(socket.getOutputStream(),true);
		os.println("欢迎Welcome!");
		BufferedReader is= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String str=is.readLine();
			while(str!=null) {
				addappend(str);
				str=is.readLine();
				}
		is.close();
		os.close();
		ss.close();	
	}

	protected void actionperformed(ActionEvent e) {
		this.os.println(SevertextField.getText());
		this.SevertextArea.append("服务器："+SevertextField.getText()+"\n");
		this.SevertextField.setText("");
	}
	public void addappend(String str) {
		this.SevertextArea.append("客户端:"+str+"\n");
	}
}
