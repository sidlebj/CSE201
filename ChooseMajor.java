import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ChooseMajor extends JFrame {
	public static ArrayList<Course> csMajorCourses = new ArrayList<Course>();
	public static ArrayList<Course> seMajorCourses = new ArrayList<Course>();
	
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseMajor frame = new ChooseMajor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChooseMajor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		JButton cs = new JButton("Computer Science");
		JButton se = new JButton("Software Engineering");
		JLabel label = new JLabel("Select a major", JLabel.CENTER);
//		Color lightBlue = new Color(102, 178, 255);
//		contentPane.setBackground(lightBlue);
	
		cs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					dispose();
					loadCSMajorRequirements();
					EnterClasses enter = new EnterClasses();
					enter.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		se.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
				dispose();	
				loadSEMajorRequirements();
				EnterClasses enter = new EnterClasses();
				enter.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseUser user = new ChooseUser();
				user.setVisible(true);
			}
		});
		
		cs.setPreferredSize(new Dimension(220, 50));
		se.setPreferredSize(new Dimension(220, 50));
		contentPane.setLayout(new BorderLayout());
		contentPane.add(label, BorderLayout.PAGE_START);
		contentPane.add(cs, BorderLayout.LINE_START);
		contentPane.add(se, BorderLayout.LINE_END);
		contentPane.add(back, BorderLayout.SOUTH);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}
	
	public void loadCSMajorRequirements() {
		Scanner in = null;
		
		try {
			in = new Scanner(new File("CSMajorCourses.txt"));
			while (in.hasNext()) {
				 csMajorCourses.add(new Course(in.nextLine()));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
			
	}
	
	public void loadSEMajorRequirements() {
		Scanner in = null;
		
		try {
			in = new Scanner(new File("SEMajorCourses.txt"));
			while (in.hasNext()) {
				 seMajorCourses.add(new Course(in.nextLine()));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
			
		}
}
	


