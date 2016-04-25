import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JList;


public class EnterClasses extends JFrame {
	
	private JList courseList;
	private JList selectionsList;
	private DefaultListModel courseListModel;
	private DefaultListModel selectionsListModel;
	private JScrollPane coursePane;
	private JScrollPane selectionsPane;
	private JPanel contentPane;
	private JButton nextButton; 
	private JButton backButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton addAllButton;
	private JButton removeAllButton;
	private JLabel description;
	private Course[] prerequisites;
	
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
		contentPane = new JPanel(null);
		initializePrerequisites();
		
		courseListModel = new DefaultListModel();
		for (int i = 0; i < prerequisites.length; i++) {
			courseListModel.addElement(prerequisites[i]);
		}
		
		selectionsListModel = new DefaultListModel();
		
		selectionsList = new JList(selectionsListModel);
		
		selectionsPane = new JScrollPane(selectionsList);
		selectionsPane.setBounds(285, 30, 135, 180);
	
		contentPane.add(selectionsPane);
		
		addButton = new JButton(">>");
		addButton.addActionListener(new AddButtonListener());
		addButton.setBounds(183, 87, 86, 29);
		contentPane.add(addButton);
		
		removeButton = new JButton("<<");
		removeButton.addActionListener(new RemoveButtonListener());
		
		removeButton.setBounds(183, 128, 86, 29);
		contentPane.add(removeButton);
		
		courseList = new JList(courseListModel);
		coursePane = new JScrollPane(courseList);
		coursePane.setBounds(35, 30, 135, 180);
		contentPane.add(coursePane);
		
		addAllButton  = new JButton("Add all");
		addAllButton.addActionListener(new AddAllButtonListener());
		
		addAllButton.setBounds(65, 214, 72, 23);
		contentPane.add(addAllButton);
		
		removeAllButton = new JButton("Remove all");
		removeAllButton.setBounds(305, 211, 99, 29);
		removeAllButton.addActionListener(new RemoveAllButtonListener());
		contentPane.add(removeAllButton);
		
		description= new JLabel("Select courses you have previously taken from the list on the left.");
		description.setBounds(12, 6, 438, 16);
		contentPane.add(description);
		
		nextButton = new JButton("Next");
		nextButton.setBounds(327, 243, 117, 29);
		contentPane.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < selectionsListModel.size(); i++) {
					classesTaken.add(toCourse(selectionsListModel.getElementAt(i)));
				}
				
				try {
					dispose();
					GetInfo gi = new GetInfo();
					gi.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				dispose();
				ChooseUser cu = new ChooseUser();
				cu.setVisible(true);
			}
		});
		backButton.setBounds(6, 243, 117, 29);
		contentPane.add(backButton);
		setContentPane(contentPane);
		
	}
	
	public void initializePrerequisites() {
		ArrayList<Course> temp = new ArrayList<Course>();
		temp.add(new Course("CSE 102"));
		temp.add(new Course("CSE 153"));
		temp.add(new Course("CSE 163"));
		temp.add(new Course("CSE 174"));
		temp.add(new Course("CSE 201"));
		temp.add(new Course("CSE 271"));
		temp.add(new Course("CSE 274"));
		temp.add(new Course("CSE 278"));
		temp.add(new Course("CSE 283"));
		temp.add(new Course("CSE 383"));
		temp.add(new Course("CSE 385"));
		temp.add(new Course("CSE 386"));
		temp.add(new Course("CSE 448"));
		temp.add(new Course("CSE 606"));
		temp.add(new Course("MTH 151"));
		temp.add(new Course("MTH 222"));
		temp.add(new Course("MTH 231"));
		temp.add(new Course("MTH 245"));
		temp.add(new Course("MTH 251"));
		temp.add(new Course("MTH 347"));
		temp.add(new Course("ECE 278"));
		temp.add(new Course("ECE 287"));
		temp.add(new Course("ECE 387"));
		temp.add(new Course("STA 368"));
		temp.add(new Course("STA 401"));
		temp.add(new Course("STA 462"));
		temp.add(new Course("BOT 116"));
		temp.add(new Course("BOT 342"));
		temp.add(new Course("ENG 111"));
		
		prerequisites = new Course[temp.size()];
		for (int i = 0; i < prerequisites.length; i++) {
			prerequisites[i] = new Course(temp.get(i).toString());
		}
		
		
	}
	
	public void clearSelectionsModel() {
		selectionsListModel.clear();
	}
	
	public Course toCourse(Object o) {
		return new Course(o.toString());
	}
	
	public void clearCoursesModel() {
		courseListModel.clear();
	}
	
	public void addCourseElements (ListModel obj) {
		fillListModel (courseListModel, obj);
	}
	
	public void addCourseElements (Object[] objs) {
		fillListModel(courseListModel, objs);
	}
	
	public void addSelectionElements (Object[] objs) {
		fillListModel(selectionsListModel, objs);
	}
	
	public void addSelectionElements (ListModel obj) {
		fillListModel(selectionsListModel, obj);
	}
	
	private void fillListModel (DefaultListModel model, ListModel obj) {
		int size = obj.getSize();
		
		for (int i = 0; i < size; i++) {
			model.addElement(obj.getElementAt(i));
		}
	}
	
	private void fillListModel (DefaultListModel model, Object[] obj) {
		for (int i = 0; i < obj.length; i++) {
			model.addElement(obj[i]);
		}
	}
	
	private void clearCourseSelected () {
		List l = courseList.getSelectedValuesList();
		Object[] selected = l.toArray();
		
		for (int i = 0; i < selected.length; i++) {
			courseListModel.removeElementAt(i);
		}
		
		courseList.getSelectionModel().clearSelection();
	}
	
	private void clearSelectionSelected () {
		List l = selectionsList.getSelectedValuesList();
		Object[] selected = l.toArray();
		
		for (int i = 0; i < selected.length; i++) {
				selectionsListModel.removeElementAt(i);
		}
		
		selectionsList.getSelectionModel().clearSelection();
	}
	
	public void setCourseElements(ListModel obj) {
		clearCoursesModel();
		addCourseElements(obj);
	}
	
	private class AddButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			List l = courseList.getSelectedValuesList();
			Object[] selected = l.toArray();
			
			addSelectionElements(selected);
			clearCourseSelected();
			
		}
	}
	
	private class RemoveButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			List l = selectionsList.getSelectedValuesList();
			Object[] selected = l.toArray();
			
			addCourseElements(selected);
			clearSelectionSelected();
		}
	}
	
	private class AddAllButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			ListModel l = courseList.getModel();
			
			addSelectionElements(l);
			clearCoursesModel();
			
		}
	}
	
	private class RemoveAllButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			ListModel l = selectionsList.getModel();
			
			addCourseElements(l);
			clearSelectionsModel();
		}
	}
	
	
}
