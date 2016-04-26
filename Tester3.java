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

public class Tester3 extends JFrame {
	public ArrayList<Course> coursesAvailable;

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
					Tester3 frame = new Tester3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Tester3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		
		calendar = new JTable();
		model = new DefaultTableModel(data, columnNames);
		calendar.setModel(model);
		coursesAvailable = new ArrayList<Course>();
		coursesAvailable.add(new Course("CSE 174 A", "James Kiper", 800, 925, new char[]{'M', 'W', 'F'}, 
				new String[]{"None"}));
		coursesAvailable.add(new Course("CSE 271 B", "Michael Stahr", 1300, 1450, new char[]{'T', 'R'},
				new String[]{"CSE 174"}));
		coursesAvailable.add(new Course("CSE 274 C", "Prakash Duraisamy", 1130, 1250, new char[]{'T', 'F'},
				new String[]{"CSE 271"}));
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
		contentPane.add(btnShowFinalsSchedule);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(6, 393, 117, 29);
		contentPane.add(btnBack);
		
		addButton = new JButton(">>");
		addButton.setBounds(177, 166, 61, 22);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Course> l = courseList.getSelectedValuesList();
				Course selected = l.get(0);
				
//				if (selected == null) {
//					
//				}
//				
//				else if ()
				
				addCalendarElements(selected, timeToInt(selected.getTimeStart()), selected.getDays());
				clearCourseSelected();
			}
		});
		
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
				
				isMissing(courseListModel, o);
				clearCalendarSelection(o);
				}
			}
		});
		
		lblWeeklySchedule = new JLabel("Weekly Schedule");
		lblWeeklySchedule.setBounds(397, 51, 117, 16);
		contentPane.add(lblWeeklySchedule);
		
		statusBar = new JTextField();
		statusBar.setEditable(false);
		statusBar.setBounds(279, 312, 340, 16);
		
		courseList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged (ListSelectionEvent e) {
				Course c = courseList.getSelectedValue();
				if (c == null) {
					
				}
			
				statusBar.setText(c.toString());
				
			}
		});
		
		contentPane.add(statusBar);
		
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
	
	public void clearCourseListModel() {
		courseListModel.clear();
	}
	
	public void addCourseElements (ListModel<Course> obj) {
		fillListModel (courseListModel, obj);
	}
	
	public void addCourseElements (Course c) {
		fillListModel(courseListModel, c);
	}
	
	public void addCalendarElements (ListModel<Course> obj, int time, char[] days) {
		String stringDays = new String(days);
		
		if (days.length == 1) {
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(0)));
		}
		if (days.length == 2) {
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(1)));
			
		}
		if (days.length == 3) {
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(2)));
		}
		
		if (days.length == 4) {
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(2)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(3)));
		}
		if (days.length == 5) {
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(2)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(3)));
			calendar.getModel().setValueAt(obj.toString(), time, intDays(stringDays.charAt(4)));
		}
		
		
	}
	
	public void addCalendarElements (Course obj, int time, char[] days) {
		String stringDays = new String(days);
		
		if (days.length == 1) {
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(0)));
		}
		if (days.length == 2) {
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(1)));
			
		}
		if (days.length == 3) {
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(2)));
		}
		
		if (days.length == 4) {
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(2)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(3)));
		}
		if (days.length == 5) {
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(0)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(1)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(2)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(3)));
			calendar.getModel().setValueAt(obj.toString(), timeToCalendar(time), intDays(stringDays.charAt(4)));
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
	private void clearCourseSelected () {
		List<Course> l = courseList.getSelectedValuesList();
		Course selected = l.get(0);
		
		int index = courseListModel.indexOf(selected);
		courseListModel.removeElementAt(index);
		courseList.getSelectionModel().clearSelection();
	}
	
	public void setCourseElements(ListModel<Course> obj) {
		clearCourseListModel();
		addCourseElements(obj);
	}
	
	public int timeToInt (int time) {
		if (time > 1259) {
			return (time - 1200) / 100;
		}
		else
			return time/100;
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
	
	
	
	public int timeToCalendar(int n) {
		int calendarSlot = 0;
		if (n == 8) {
			calendarSlot = 0;
		}
		if (n == 9) {
			calendarSlot = 1;
		}
		if (n == 10) {
			calendarSlot = 2;
		}
		if (n == 11) {
			calendarSlot = 3;
		}
		if (n == 12) {
			calendarSlot = 4;
		}
		if (n == 1) {
			calendarSlot = 5;
		}
		if (n == 2) {
			calendarSlot = 6;
		}
		if (n == 3) {
			calendarSlot = 7;
		}
		if (n == 4) {
			calendarSlot = 8;
		}
		if (n == 5) {
			calendarSlot = 9;
		}
		if (n == 6) {
			calendarSlot = 10;
		}
		if (n == 7) {
			calendarSlot = 11;
		}
		
		return calendarSlot;

	}
}
