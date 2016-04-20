import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class AdminPassword extends JFrame {
	private JPasswordField password; 
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPassword frame = new AdminPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		password = new JPasswordField(10);
		password.setPreferredSize(new Dimension(10, 40));
		JButton enter = new JButton("Enter");
		JButton back = new JButton("Back");
		JLabel label = new JLabel("Enter admin password: ");
		label.setLabelFor(password);
		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isCorrect(password.getPassword())) {
					dispose();
					EnterAllCourses enter = new EnterAllCourses();
					enter.setVisible(true);
				}
				else {
					password.setText("");
					JOptionPane.showMessageDialog(contentPane, "Incorrect Password,"
							+ " try again");
				}
			}
		});
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseUser user = new ChooseUser();
				user.setVisible(true);
			}
		});
		password.setSize(200, 30);
		password.setLocation(125, 100);
		
		enter.setSize(70, 30);
		enter.setLocation(320, 100);
		back.setSize(70, 30);
		back.setLocation(380, 250);
		
		label.setLocation(129, 65);
		label.setSize(200, 50);
		contentPane.add(label);
		contentPane.add(password);
		contentPane.add(enter);
		contentPane.add(back);
		setContentPane(contentPane);
	}
	
	public boolean isCorrect (char[] password) {
		char[] correctPassword = {'M', 'U', 'c', 's', 'e'};
		boolean isCorrect;
		
		if (password.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(password, correctPassword);
		}
		
		return isCorrect;
	}

}
