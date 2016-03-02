
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import FileParser.CourseInfo;
import FileParser.ExamConflicts;

public class ResultsWindow extends JFrame {

	private final static String newline = "\n";
	
	private ArrayList<CourseInfo> listOfClasses; // all classes
	private ArrayList<CourseInfo> listOfConflicts; // all conflicts
	private ExamConflicts ec = new ExamConflicts();
	private JTabbedPane tabbedPane;

	public ResultsWindow(ArrayList<CourseInfo> loc) {
		super("Exam Conflict Finder Results");
		this.listOfClasses = loc; // grab list of classes from FileParser
		this.listOfConflicts = null;
		setBounds(0, 0, 800, 800); // position and size
		this.setBackground(new Color(235, 235, 240));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Miami_m.png"));

		// create the main component, the tabbed pane
		tabbedPane = new JTabbedPane();
		JLabel examLabel = new JLabel("<html><font size =+2><b> Exam Times "
				+ "</b></font></html>");
        
		// Second tab, monday
		JComponent monday = new JPanel(new BorderLayout());
		monday.add(examLabel, BorderLayout.NORTH);
		JLabel mondayLabel = new JLabel("<html><font size =+2><b> Monday, Dec 7</b></font></html>");
        monday.add(mondayLabel, BorderLayout.NORTH);
        monday.add(generateMonResults(), BorderLayout.CENTER);
		tabbedPane.addTab("Monday", monday);
		
		// Third tab, tuesday
		JComponent tuesday = new JPanel(new BorderLayout());
		tuesday.add(examLabel, BorderLayout.NORTH);	
		JLabel tuesdayLabel = new JLabel("<html><font size =+2><b> Tuesday, Dec 8</b></font></html>");
		tuesday.add(tuesdayLabel, BorderLayout.NORTH);
		tuesday.add(generateTuesResults(), BorderLayout.CENTER);
		tabbedPane.addTab("Tuesday", tuesday);
		
		// Fourth tab, wednesday
		JComponent wednesday = new JPanel(new BorderLayout());
		wednesday.add(examLabel, BorderLayout.NORTH);
		JLabel wednesdayLabel = new JLabel("<html><font size =+2><b> Wednesday, Dec 9</b></font></html>");
		wednesday.add(wednesdayLabel, BorderLayout.NORTH);
		wednesday.add(generateWedResults(), BorderLayout.CENTER);
		tabbedPane.addTab("Wednesday", wednesday);
		
		// Fifth tab, thursday
		JComponent thursday = new JPanel(new BorderLayout());
		thursday.add(examLabel, BorderLayout.NORTH);	
		JLabel thursdayLabel = new JLabel("<html><font size =+2><b> Thursday, Dec 10</b></font></html>");
		thursday.add(thursdayLabel, BorderLayout.NORTH);
		thursday.add(generateThursResults(), BorderLayout.CENTER);
		tabbedPane.addTab("Thursday", thursday);
		
		// Sixth tab, friday
		JComponent friday = new JPanel(new BorderLayout());
		friday.add(examLabel, BorderLayout.NORTH);	
		JLabel fridayLabel = new JLabel("<html><font size =+2><b> Friday, Dec 11</b></font></html>");
		friday.add(fridayLabel, BorderLayout.NORTH);
		friday.add(generateFriResults(), BorderLayout.CENTER);
		tabbedPane.addTab("Friday", friday);
	
		listOfConflicts = generateConflictList(listOfClasses);
		// First tab, "All conflicts"
				JPanel allConflicts = new JPanel(new BorderLayout());
				JLabel acLabel = new JLabel("<html><font size =+2><b> All Conflicts</b></font></html>");
		        allConflicts.add(examLabel, BorderLayout.NORTH);
		        allConflicts.add(acLabel, BorderLayout.NORTH);
		        allConflicts.add(generateAllConflicts(), BorderLayout.CENTER);
		        
				tabbedPane.addTab("All Conflicts", allConflicts);
		
				
		tabbedPane.setSelectedIndex(5);		
		// add the complete tabbed pane!
		this.add(tabbedPane, BorderLayout.CENTER);
				
	}
	
	// Produces a list of classes flagged as "conflicts" only
	private ArrayList<CourseInfo> generateConflictList(ArrayList<CourseInfo> cl) {
		ArrayList<CourseInfo> temp = new ArrayList<CourseInfo>(20);
		
		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).getConflict() == true) {
			temp.add(cl.get(i));
			}
		}
		return temp;
	}
	
	// place the "monday" classes in a scrollpane
	public JScrollPane generateMonResults() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendMonday(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	}
	
	// place the "tuesday" classes in a scrollpane
	public JScrollPane generateTuesResults() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendTuesday(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	}
	
	// place the "wednesday" classes in a scrollpane
	public JScrollPane generateWedResults() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendWednesday(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	
	}
	
	// place the "thursday" classes in a scrollpane
	public JScrollPane generateThursResults() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendThursday(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	}
		
	// place the "friday" classes in a scrollpane
	public JScrollPane generateFriResults() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendFriday(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	}
	
	// place the "conflict" classes in a scrollpane
	private JScrollPane generateAllConflicts() {
		JTextArea textArea = new JTextArea(10, 1);
		textArea = appendCourseList(textArea, this.listOfConflicts);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		return scrollPane;
	}

	
	// Parses the full list of classes and adds only those on Mondays
	public JTextArea appendMonday(JTextArea txt) {
		JTextArea temp = txt;
		// using ExamConflicts object, group the classes by exam day and time
		ArrayList<CourseInfo> monClasses3 = ec.mon1245(listOfClasses);
		ArrayList<CourseInfo> monClasses4 = ec.mon1500(listOfClasses);
		ArrayList<CourseInfo> monClasses6 = ec.mon1945(listOfClasses);
		
		
		temp.append("8:00-10:00 Group Exam #1" + newline);
		temp.append(newline+"10:15-12:15 Group Exam #2" + newline);
		temp.append(newline+"12:45-2:45 Exams: " + newline);
		temp = appendCourseList(temp, monClasses3);
		temp.append(newline+"3:00-5:00 Exams: " + newline);
		temp = appendCourseList(temp, monClasses4);
		temp.append(newline+"5:30-7:30 Group Exam #3" + newline);
		temp.append(newline+"7:45-9:45 Exams: " + newline);
		temp = appendCourseList(temp, monClasses6);

		return temp;
	}

	// Parses the full list of classes and adds only those on tuesdays
	public JTextArea appendTuesday(JTextArea txt) {
		JTextArea temp = txt;
		// using ExamConflicts object, group the classes by exam day and time
		ArrayList<CourseInfo> tuesClasses1 = ec.tues800(listOfClasses);
		ArrayList<CourseInfo> tuesClasses3 = ec.tues1245(listOfClasses);
		ArrayList<CourseInfo> tuesClasses4 = ec.tues1500(listOfClasses);
		ArrayList<CourseInfo> tuesClasses5 = ec.tues1730(listOfClasses);
		ArrayList<CourseInfo> tuesClasses6 = ec.tues1945(listOfClasses);

		temp.append("8:00-10:00 Exams: " + newline);
		temp = appendCourseList(temp, tuesClasses1);
		temp.append(newline+"10:15-12:15 Group Exam #4" + newline);
		temp.append(newline+"12:45-2:45 Exams: " + newline);
		temp = appendCourseList(temp, tuesClasses3);
		temp.append(newline+"3:00-5:00 Exams: " + newline);
		temp = appendCourseList(temp, tuesClasses4);
		temp.append(newline+"5:30-7:50 Exams: " + newline);
		temp = appendCourseList(temp, tuesClasses5);
		temp.append(newline+"7:45-9:45 Exams: " + newline);
		temp = appendCourseList(temp, tuesClasses6);
			
		return temp;
	}
	
	// Parses the full list of classes and adds only those on wednesdays
	public JTextArea appendWednesday(JTextArea txt) {
		JTextArea temp = txt;
		// using ExamConflicts object, group the classes by exam day and time
		ArrayList<CourseInfo> wed1 = ec.wed800(listOfClasses);
		ArrayList<CourseInfo> wed2 = ec.wed1015(listOfClasses);
		ArrayList<CourseInfo> wed4 = ec.wed1500(listOfClasses);
		ArrayList<CourseInfo> wed5 = ec.wed1730(listOfClasses);
		ArrayList<CourseInfo> wed6 = ec.wed1945(listOfClasses);
		
		temp.append(newline+"8:00-10:00 Exams: " + newline);
		temp = appendCourseList(temp, wed1);
		temp.append(newline+"10:15-12:15 Exams: " + newline);
		temp = appendCourseList(temp, wed2);
		temp.append(newline+"Group Exam #5" + newline);
		temp.append(newline+"3:00-5:00 Exams: " + newline);
		temp = appendCourseList(temp, wed4);
		temp.append(newline+"5:30-7:30 Exams: " + newline);
		temp = appendCourseList(temp, wed5);
		temp.append(newline+"7:45-9:45 Exams: " + newline);
		temp = appendCourseList(temp, wed6);
		
		return temp;
	}
	
	// Parses the full list of classes and adds only those on thursdays
	public JTextArea appendThursday(JTextArea txt) {
		JTextArea temp = txt;
		// using ExamConflicts object, group the classes by exam day and time
		ArrayList<CourseInfo> thur1 = ec.thurs800(listOfClasses);
		ArrayList<CourseInfo> thur3 = ec.thurs1245(listOfClasses);
		ArrayList<CourseInfo> thur4 = ec.thurs1500(listOfClasses);
		ArrayList<CourseInfo> thur6 = ec.thurs1945(listOfClasses);
	
		temp.append(newline+"8:00-10:00 Exams: " + newline);
		temp = appendCourseList(temp, thur1);
		temp.append(newline+"Group Exam #6 " + newline);
		temp.append(newline+"12:45-2:45 Exams: " + newline);
		temp = appendCourseList(temp, thur3);
		temp.append(newline+"3:00-5:00 Exams: " + newline);
		temp = appendCourseList(temp, thur4);
		temp.append(newline+"Group Exam #7 " + newline);
		temp.append(newline+"7:45-9:45 Exams: " + newline);
		temp = appendCourseList(temp, thur6);
		
		return temp;
	}
	
	// Parses the full list of classes and adds only those on fridays
	public JTextArea appendFriday(JTextArea txt) {
		JTextArea temp = txt;
		// using ExamConflicts object, group the classes by exam day and time
		ArrayList<CourseInfo> wed10 = ec.fri1015(listOfClasses);
		
		temp.append(newline+"Group Exam #8" + newline);
		temp.append(newline+"10:15-12:15 Exams: " + newline);
		temp = appendCourseList(temp, wed10);
		temp.append(newline+"Group Exam #9" + newline);
				
		return temp;
	}
	

	// helper method that parses an ArrayList of classes and
	// appends them onto a JTextArea
	private JTextArea appendCourseList(JTextArea txt, ArrayList<CourseInfo> cl) {
		JTextArea temp = txt;
		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).getConflict() == true) {
			temp.append(newline+"!!!! CONFLICT !!!!"+newline+"CRN: " + cl.get(i).getCRN() + ", Subject/CourseNum: " + cl.get(i).getSubject() + " "
						+ cl.get(i).getCourseNum() + ", Section: " + cl.get(i).getSection() + ", Title: "
						+ cl.get(i).getTitle() + ". Instructor: " + cl.get(i).getName() + ". Days: "
						+cl.get(i).getDays()+newline+"!!!! CONFLICT !!!!"+newline+newline);
						}
			else {
			temp.append("CRN: " + cl.get(i).getCRN() + ", Subject/CourseNum: " + cl.get(i).getSubject() + " "
					+ cl.get(i).getCourseNum() + ", Section: " + cl.get(i).getSection() + ", Title: "
					+ cl.get(i).getTitle() + ". Instructor: " + cl.get(i).getName() + ". Days: " +cl.get(i).getDays()+newline);
			}
		}
		return temp;
	}

}
