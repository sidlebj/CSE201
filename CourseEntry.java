import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CourseEntry extends JFrame {
	public ArrayList<Course> coursesAvailable = new ArrayList<Course>();
	public ArrayList<Course> majorCourses = new ArrayList<Course>();
	private ArrayList<Course> tempCourses = new ArrayList<Course>();
	
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable calendar;
	private JList<Course> courseList;
	private DefaultListModel<Course> courseListModel;
	private JScrollPane listPane;
	private JScrollPane calendarPane;
	private JLabel lblCourses;
	private JButton btnShowFinalsSchedule;
	private JButton btnBack;
	private JButton addButton;
	private JButton removeButton;
	private JLabel lblWeeklySchedule;
	private JTextField statusBar;
	private JTextField errorBar; 

	private String[] columnNames = {"Time", "M", "T", "W", "R", "F"};
	String[][] data = { {"8", "", "", "", "", ""}, 
			{"9", "","", "", "", ""},
			{"10", "","", "", "", ""},
			{"11", "","", "", "", ""},
			{"12", "","", "", "", ""},
			{"1", "","", "", "", ""},
			{"2", "","", "", "", ""},
			{"3", "","", "", "", ""},
			{"4", "","", "", "", ""},
			{"5", "","", "", "", ""},
			{"6", "","", "", "", ""},
			{"7", "","", "", "", ""}};
	
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
	
	public CourseEntry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		
		calendar = new JTable();
		model = new DefaultTableModel(data, columnNames);
		calendar.setModel(model);
		coursesAvailable = new ArrayList<Course>();
	
		Course a = new Course("CSE 174 A", "James Kiper", new MeetTimes[]{new MeetTimes('M', 1000, 1055), 
				new MeetTimes('W', 1000, 1055), new MeetTimes('R', 800, 950)}, new MeetTimes('W', 1500, 1700),
						new String[]{"None"});
		coursesAvailable.add(a);
		Course b = new Course("CSE 271 B", "Michael Stahr", new MeetTimes[]{new MeetTimes('T', 1130, 1250), 
				new MeetTimes('R', 1130, 1250)}, new MeetTimes('M', 1500, 1700),new String[]{"CSE 174"});
		coursesAvailable.add(b);
		Course c = new Course("CSE 274 C", "Prakash Duraisamy", new MeetTimes[]{new MeetTimes('T', 1130, 1250),
				new MeetTimes('R', 1130, 1250)}, new MeetTimes('W', 1500, 1700), new String[]{"CSE 271"});
		coursesAvailable.add(c);
		Course d = new Course("CSE 148 D", "Someone Irrelevant",new MeetTimes[]{new MeetTimes('M', 1000, 1055), 
				new MeetTimes('W', 1000, 1055), new MeetTimes('F', 1000, 1055)}, new MeetTimes('F', 800, 1000),
				new String[]{"None"});
		coursesAvailable.add(d);
		
		majorCourses.add(new Course("CSE 174"));
		majorCourses.add(new Course("CSE 271"));
		majorCourses.add(new Course("CSE 274"));
		
		courseListModel = new DefaultListModel<Course>();
		
		for (int i = 0; i < coursesAvailable.size(); i++) {
			courseListModel.addElement(coursesAvailable.get(i));
		}
		
		courseList = new JList(courseListModel);

		listPane = new JScrollPane(courseList);
		listPane.setBounds(25, 70, 140, 300);
		
		calendarPane = new JScrollPane(calendar);
		calendarPane.setBounds(250, 70, 400, 230);
		
		contentPane.add(listPane);
		contentPane.add(calendarPane);
		
		lblCourses = new JLabel("Courses");
		lblCourses.setBounds(66, 51, 61, 16);
		contentPane.add(lblCourses);
		
		btnShowFinalsSchedule = new JButton("Show Finals Schedule");
		btnShowFinalsSchedule.setBounds(491, 393, 178, 29);
		btnShowFinalsSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					ExamSchedule es = new ExamSchedule(tempCourses);
					es.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnShowFinalsSchedule);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(6, 393, 117, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					GetInfo gi = new GetInfo();
					gi.setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnBack);
		
		addButton = new JButton(">>");
		addButton.setBounds(177, 166, 61, 22);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List l = courseList.getSelectedValuesList();
				Course selected = (Course)l.get(0);
				
				
				if (alreadySelected(selected)) {
					JOptionPane.showMessageDialog(contentPane, "Cannot add, this course has already been added");
				}
				else if (conflicts(selected)) {
					JOptionPane.showMessageDialog(contentPane, "Cannot add " + selected.getCourseCode() + ", it"
							+ " conflicts with a course currently selected");
				}
				else {
					tempCourses.add(selected);
					addCalendarElements(selected);
					
				}	
				
			}});
		
		contentPane.add(addButton);
		
		removeButton = new JButton("<<");
		removeButton.setBounds(177, 200, 61, 22);
		contentPane.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				int row = calendar.getSelectedRow();
				int col = calendar.getSelectedColumn();
				
				if (calendar.getValueAt(row, col).equals("")) {
					
				}
				
				else {
				Object o = calendar.getValueAt(row, col);
				
				removeFromList(o);
				
				clearCalendarSelection(o);
				
				}
			}
		});
		
		lblWeeklySchedule = new JLabel("Weekly Schedule");
		lblWeeklySchedule.setBounds(397, 51, 117, 16);
		contentPane.add(lblWeeklySchedule);
		
		statusBar = new JTextField();
		statusBar.setEditable(false);
		statusBar.setBounds(250, 312, 400, 29);
		
		errorBar = new JTextField();
		errorBar.setEditable(false);
		errorBar.setBounds(250, 341, 400, 29);
		
		courseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged (ListSelectionEvent e) {
				Course c = courseList.getSelectedValue();
				
				if (c == null) {
					statusBar.setText("");
				}
				
				else
					statusBar.setText(c.importantInfo());
				
			}
		});
		
		contentPane.add(statusBar);
		contentPane.add(errorBar);
	}
	
	public void isMissing (DefaultListModel<Course> model, Object o) {
		Course c = null; 
		String genericName = o.toString().substring(0, 9).trim();
		
		for (int i = 0; i < coursesAvailable.size(); i++) {
			if (coursesAvailable.get(i).getCourseCode().equals(genericName)) {
				c = coursesAvailable.get(i);
			}
		}
		
		model.addElement(c);
		
	}
	
	public Course[] toCourseArray (List<Course> l) {
		Course[] c = new Course[l.size()];
		
		for (int i = 0; i < c.length; i++) {
			c[i] = l.get(i);
		}
		
		return c;
	}
	
	public boolean isMajorCourse(Course c) {
		Course d = new Course(c.getCourseCode().substring(0, 7));
		boolean is = false;
		for (int i = 0; i < majorCourses.size(); i++) {
			if (d.toString().substring(0, 7).equals(majorCourses.get(i).toString().substring(0, 7))) {
				is = true;
			}
		}
		return is;
		
	}
	
	public boolean conflicts (Course c) {
		boolean flag = false; 
		
		if (tempCourses.isEmpty()) {
			flag = false;
		}
		else {
			for (int i = 0; i < tempCourses.size(); i++) {
				if (c.conflictsWith(tempCourses.get(i))) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean alreadySelected (Course c) {
		boolean flag = false;
		if (tempCourses.isEmpty()) {
			flag = false;
		}
		else {
			for (int i = 0; i < tempCourses.size(); i++) {
				if (c.getCourseCode().equals(tempCourses.get(i).getCourseCode())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
		
	}
	
	public void removeFromList (Object o) {
		Course c = new Course(o.toString());
		
		for (int i = 0; i < tempCourses.size(); i++) {
			if (c.getCourseCode().equals(tempCourses.get(i).getCourseCode())) {
				tempCourses.remove(i);
			}
		}
	}
	
	public void clearCourseListModel() {
		courseListModel.clear();
	}
	
	public void addCourseElements (ListModel<Course> obj) {
		fillListModel (courseListModel, obj);
	}
	
	public void addCourseElements (Course c) {
		fillListModel(courseListModel, c);
	}
	
	public void addCalendarElements (Course c) {
		MeetTimes[] courseTimes = c.getCourseTimes();
		
		for (int i = 0; i  < courseTimes.length; i++) {
			calendar.getModel().setValueAt(c.getCourseCode(), timeToInt(courseTimes[i].getStartTime()),
					intDays(courseTimes[i].getDay()));
		}
		
		for (int i = 0; i  < courseTimes.length; i++) {
			calendar.getModel().setValueAt(c.getCourseCode(), timeToInt(courseTimes[i].getEndTime()),
					intDays(courseTimes[i].getDay()));
		}
	}

		
	
	

	public void clearCalendarSelection(Object o) {
		String genName = o.toString();
		int rowCount = calendar.getRowCount();
		int colCount = calendar.getColumnCount();
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				if (!calendar.getValueAt(i, j).toString().equals("")) {
					if (calendar.getValueAt(i, j).toString().equals(genName)) {
						calendar.setValueAt("", i, j);
					}
				}
			}
		}
	}
	
	private void fillListModel (DefaultListModel<Course> model, ListModel<Course> obj) {
		int size = obj.getSize();
		
		for (int i = 0; i < size; i++) {
			model.addElement(obj.getElementAt(i));
		}
	}
	
	private void fillListModel (DefaultListModel<Course> model, Course c) {
			model.addElement(c);
		
	}
	
	public void setCourseElements(ListModel<Course> obj) {
		clearCourseListModel();
		addCourseElements(obj);
	}
	
	public int timeToInt (int time) {
		int newTime;
		
		if (time > 1259) {
			newTime = (time - 1200) / 100;
		}
		else
			newTime = time/100;
		
		int calendarSlot = 0;
		
		if (newTime == 8) {
			calendarSlot = 0;
		}
		if (newTime == 9) {
			calendarSlot = 1;
		}
		if (newTime == 10) {
			calendarSlot = 2;
		}
		if (newTime == 11) {
			calendarSlot = 3;
		}
		if (newTime == 12) {
			calendarSlot = 4;
		}
		if (newTime == 1) {
			calendarSlot = 5;
		}
		if (newTime == 2) {
			calendarSlot = 6;
		}
		if (newTime == 3) {
			calendarSlot = 7;
		}
		if (newTime == 4) {
			calendarSlot = 8;
		}
		if (newTime == 5) {
			calendarSlot = 9;
		}
		if (newTime == 6) {
			calendarSlot = 10;
		}
		if (newTime == 7) {
			calendarSlot = 11;
		}
		
		return calendarSlot;
		
		
	}
	
	public int intDays (char day) {
		if (day == 'M') {
			return 1;
		}
		if (day == 'T') {
			return 2;
		}
		if (day == 'W') {
			return 3;
		}
		if (day == 'R') {
			return 4;
		}
		if (day == 'F') {
			return 5;
		}
		else
			return 0;
	
	}
	
}
