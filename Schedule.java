import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//this class serves as a template for our weekly schedule and exam schedule classes
public class Schedule extends JFrame{

	private JPanel contentPane;
	
	public static void main(String[] args) {
		
		

	}
	
	public Schedule (ArrayList<Course> coursesSelected) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new GridBagLayout());
		JLabel schedule = new JLabel();
		schedule.setText("<html> CSE 201 Group F:<br> This program allows a student user"
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
		
		schedule.setSize(new Dimension(300, 200));
		schedule.setLocation(75, -5);
		
		cont.setLocation(175, 190);
		cont.setSize(new Dimension(100, 20));
		contentPane.add(schedule);
		contentPane.add(cont);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

}
