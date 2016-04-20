import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CourseEntry extends JFrame {
	public static ArrayList<Course> coursesOffered = new ArrayList<Course>();
	public static ArrayList<Course> coursesSelected = new ArrayList<Course>();
	private JPanel contentPane;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseEntry frame = new CourseEntry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CourseEntry() throws Exception{
		loadCoursesOffered();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		JComboBox<Course> courseSelect1 = new JComboBox<Course>();
		for (int i = 0; i < coursesOffered.size(); i++) {
			courseSelect1.addItem(coursesOffered.get(i));
		}
		coursesSelected.add((Course)courseSelect1.getSelectedItem());
		
		JComboBox<Course> courseSelect2 = new JComboBox<Course>();
		for (int i = 0; i < coursesOffered.size(); i++) {
			courseSelect2.addItem(coursesOffered.get(i));
		}
		coursesSelected.add((Course) courseSelect2.getSelectedItem());
//		
//		courseSelect2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (! checkConflicts(coursesSelected.get(0), coursesSelected.get(1))) {
//					 JLabel error = new JLabel("Courses conflcit");
//					 contentPane.add(error);
//					 contentPane.revalidate();
//					 contentPane.repaint();
//				}
//			}
//		});
		
		JComboBox<Course> courseSelect3 = new JComboBox<Course>();
		for (int i = 0; i < coursesOffered.size(); i++) {
			courseSelect3.addItem(coursesOffered.get(i));
		}
		coursesSelected.add((Course) courseSelect3.getSelectedItem());
		
		JButton getFinalsSchedule = new JButton("Show Final Exam Schedule");
		// Add ActionListener to get finals schedule
		JButton back = new JButton ("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try { 
				coursesSelected.clear();	
				ChooseMajor majorChoice = new ChooseMajor();
				majorChoice.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			}});
	
		contentPane.add(courseSelect1);
		contentPane.add(courseSelect2);
		contentPane.add(courseSelect3);
		contentPane.add(getFinalsSchedule);
		contentPane.add(back);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
	}
	
	public static void loadCoursesOffered () {
		
		Scanner in = null;
		
		try {
			in = new Scanner(new File("CoursesOffered.txt"));
			coursesOffered.clear();
			
			while (in.hasNext()) {
				String[] info = in.nextLine().split("\t");
				coursesOffered.add(
						new Course(info[0], info[1], Integer.parseInt(info[2]), 
								Integer.parseInt(info[3]), info[4], info[5]));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			if (in != null) in.close();
		}
	}
	
	public static void printCourses() {
		for (Course c : coursesOffered) {
			System.out.println(c);
		}
	}
	
	public boolean checkConflicts(Course a, Course b) {
		boolean flag = false;
		char[] dayConflicts = dayConflicts(a, b);
		
		
			if (noConflicts(dayConflicts) == true) {
				flag = true;
			}
			if (noConflicts(dayConflicts) == false) {
				if (a.getTimeEnd() < b.getTimeStart() ||
						(a.getTimeStart() > b.getTimeEnd())) {
				flag = true;
			}
		
		}
		return flag;
	}
	
	public char[] dayConflicts(Course a, Course b) {
		char[] aDays = a.getDays();
		char[] bDays = b.getDays();
		ArrayList<Character> conflicts = new ArrayList<Character>();
		
		for (int i = 0; i < aDays.length; i++) {
			for (int j = 0; j < bDays.length; j++) {
				if(aDays[i] == bDays[j]) {
					conflicts.add(aDays[i]);
				}
			}
		}
		char[] dayConflicts = new char[conflicts.size()];
		for (int i = 0; i < dayConflicts.length; i++) {
			dayConflicts[i] = conflicts.get(i);
		}
		return dayConflicts;
	}
	
	public boolean noConflicts (char[] arr) {
		if (arr.length == 0) {
			return true;
		}
		else
			return false;
	}

}
