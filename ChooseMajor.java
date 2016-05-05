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
	public static String major;
	
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
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		JButton cs = new JButton("Computer Science");
		cs.setBounds(149, 83, 147, 36);
		JButton se = new JButton("Software Engineering");
		se.setBounds(149, 121, 147, 36);
		JLabel label = new JLabel("Select a major", JLabel.CENTER);
		label.setBounds(5, 5, 440, 16);
	
		cs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				try {
					dispose();
					major = "ComputerScience";
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
				major = "SoftwareEngineering";
				EnterClasses enter = new EnterClasses();
				enter.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton back = new JButton("Back");
		back.setBounds(149, 243, 147, 29);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChooseUser user = new ChooseUser();
				user.setVisible(true);
			}
		});
		
		cs.setPreferredSize(new Dimension(220, 50));
		se.setPreferredSize(new Dimension(220, 50));
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(cs);
		contentPane.add(se);
		contentPane.add(back);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

}