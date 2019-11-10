

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientJfame extends JFrame {

	private JPanel contentPane;
	private JTextField ClienttextField;
	private JTextArea ClienttextArea;
	private ClientJfame clientjfame;
	private PrintWriter os;
	
	public static void main(String[] args) throws Exception {
		 new ClientJfame(5001,"127.0.0.1");
		
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ClientJfame(int port,String addr) throws Exception {
		System.out.println("88888");
		setTitle("\u5BA2\u6237\u7AEF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ClienttextField = new JTextField();
		ClienttextField.setColumns(10);
		
		JButton ClientSend = new JButton("Send");
		ClientSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actionperformedClient(arg0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		ClienttextArea = new JTextArea();
		ClienttextArea.setEditable(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(ClienttextArea, Alignment.LEADING)
						.addComponent(ClienttextField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(ClientSend)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(ClienttextArea, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClienttextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClientSend))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		Socket socket=new Socket(addr,port);
		this.os = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader is= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String str=is.readLine();
		while(str!=null) {
		addappend(str);str=is.readLine();}
		is.close();
		os.close();
		socket.close();
		
	}
	
	protected void actionperformedClient(ActionEvent e) {
		this.os.println(ClienttextField.getText());
		this.ClienttextArea.append("客户端："+ClienttextField.getText()+"\n");
		this.ClienttextField.setText("");

	}
	public void addappend(String str) {
		this.ClienttextArea.append("服务器："+str+"\n");
	}
}
