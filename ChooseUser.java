import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ChooseUser extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseUser frame = new ChooseUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ChooseUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setBounds(170, 123, 117, 29);
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EnterClasses ec = new EnterClasses();
				ec.setVisible(true);
			}
		});
		
		
		contentPane.add(btnStudent);
		
		JButton btnAdministrator = new JButton("Administrator");
		btnAdministrator.setBounds(170, 151, 117, 29);
		contentPane.add(btnAdministrator);
		btnAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				dispose();
				AdminPassword ap = new AdminPassword();
				ap.setVisible(true);
			}
		});
		
		JLabel lblChooseAUser = new JLabel("<html>Choose a user. Student to optimize course scheduling,<br> administrator to"
				+ " update course information.");
		lblChooseAUser.setBounds(69, 74, 351, 37);
		contentPane.add(lblChooseAUser);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 243, 117, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				OpeningScreen os = new OpeningScreen();
				os.setVisible(true);	
				
			}
		});
		contentPane.add(btnBack);
	}

}
