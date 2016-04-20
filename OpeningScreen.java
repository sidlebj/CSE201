import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Dimension;

public class OpeningScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningScreen frame = new OpeningScreen();
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
	public OpeningScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(null);
		JLabel open = new JLabel();
		open.setText("<html> CSE 201 Group F:<br> This program allows a student user"
				+ " to enter CSE courses they wish to take and checks them for time"
				+ " conflicts, exam conflicts, and also checks that the user has"
				+ " met the prerequisites and that the course meets major requirements."
				+ " It then shows the predicted final exam schedule for the user, "
				+ " highlighting any conflicts that may exist."); 
		JButton cont = new JButton("Continue");
		cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseUser user = new ChooseUser();
				user.setVisible(true);
			}
		});
		
		open.setSize(new Dimension(300, 200));
		open.setLocation(75, -5);
		
		cont.setLocation(175, 190);
		cont.setSize(new Dimension(100, 20));
		contentPane.add(open);
		contentPane.add(cont);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

}
