import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EnterClasses extends JFrame {

	private JPanel contentPane;
	public ArrayList<Course> classesTaken = new ArrayList<Course>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterClasses frame = new EnterClasses();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EnterClasses() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS ));
		JLabel instructions = new JLabel();
		instructions.setText("<html> Enter all courses taken according to the example:"
				+ "<br>CSE174<br>CSE271<br>CSE274");
		contentPane.add(instructions);
		setContentPane(contentPane);
		contentPane.setAlignmentY(TOP_ALIGNMENT);
		JTextField text = new JTextField(250);
		contentPane.add(text);
		JButton cont = new JButton("Continue");
		cont.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {CourseEntry entry = new CourseEntry();
				entry.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				classesTaken.clear();
				ChooseMajor chooseMajor = new ChooseMajor();
				chooseMajor.setVisible(true);
			}
		});
	
		contentPane.add(cont);
		contentPane.add(back);
	}
}
