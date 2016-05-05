import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

//this class serves as a template for our weekly schedule and exam schedule classes
public class ExamSchedule extends JFrame{

	private ArrayList<Course> courses = new ArrayList<Course>();
	private JPanel contentPane;
	private String[] columnNames = {"Time", "M", "T", "W", "R", "F"};
	String[][] data = { {"8:00-10:00AM", "", "", "", "", ""}, 
			{"10:15AM-12:15PM", "","", "", "", ""},
			{"12:45-2:45PM", "","", "", "", ""},
			{"3:00-5:00PM", "","", "", "", ""},
			{"5:30-7:30PM", "","", "", "", ""},
			{"7:45-9:45PM", "","", "", "", ""}};
	
	private GridBagConstraints c = new GridBagConstraints();
	private JTable examTable = new JTable(data, columnNames);
	
	
	public static void main(String[] args) {
		
		ExamSchedule gooby = new ExamSchedule();
		gooby.setVisible(true);

	}
	
	public ExamSchedule (){
		setTitle("Finals Schedule for User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 250);
		
		contentPane = new JPanel(new GridBagLayout());
		
		//Create the top label
		JLabel schedule = new JLabel("Finals Schedule", SwingConstants.CENTER);
				
		//schedule.setSize(new Dimension(300, 200));
		//schedule.setLocation(contentPane.getWidth() / 2, -5);
				
		schedule.setText("<html> Finals Schedule"); 
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 4;
		contentPane.add(schedule, c);
		
		//Create a table that will hold our exams
		
		
		c.ipadx = contentPane.getWidth();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		contentPane.add(examTable.getTableHeader(), c);
		
		c.gridy = 2;
		contentPane.add(examTable, c);
		
		//Create the error bar
		JTextField errorbar = new JTextField();
		Color noGo = new Color(200, 0, 0);
		errorbar.setBackground(noGo);
		errorbar.setForeground(Color.lightGray);
		errorbar.setText("ERROR: CSE 148 and CSE 256 CONFLICT -- 10:15 W");
		c.ipadx = 0;
		c.insets = new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		contentPane.add(errorbar, c);
		
		//Create Back button
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CourseEntry entry = new CourseEntry();
				entry.setVisible(true);
			}
		});

		c.insets = new Insets(10, 0, 0, 0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.SOUTHWEST;
		c.gridwidth = 1;
		contentPane.add(back, c);

		//Add blank space between buttons
		JLabel blank = new JLabel();

	    c.gridx = 1;
	    c.gridy = 4;
	    c.gridheight = 1;
	    c.gridwidth = 1;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    c.anchor = GridBagConstraints.SOUTH;
	    contentPane.add(blank, c);
	    
	  //Add save button
	  		JButton save = new JButton("Save");
	  		save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			  		exportTxt();
					//this should save courses added to a .txt file
					
				}
			});
	  	    c.gridx = 2;
	  	    c.gridy = 4;
	  	    c.gridheight = 1;
	  	    c.gridwidth = 1;
	  	    c.weightx = 1.0;
	  	    c.weighty = 1.0;
	  	    c.anchor = GridBagConstraints.SOUTH;
	  	    contentPane.add(save, c);
		
		
		
		//Create Finish button
		JButton finish = new JButton("Finish");
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				//this should save courses added to a .txt file
				
			}
		});
		
		//c.ipadx = 30;  
		//c.ipady = 10;  
		c.gridx = 3;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridwidth = 1;
		contentPane.add(finish, c);


		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	}
	
	public ExamSchedule (ArrayList<Course> coursesSelected) {
		this();
		
		
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 3;
		contentPane.remove(examTable.getTableHeader());
		
		c.gridy = 2;
		contentPane.remove(examTable);
		
		populateTable(coursesSelected);
		
		c.gridy = 1;
		contentPane.add(examTable.getTableHeader());
		
		c.gridy = 2;
		contentPane.add(examTable);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void populateTable(ArrayList<Course> courses){
		MeetTimes slot;
		int time, column = -1, row = -1; 
		char day;
		
		
		for(int i = 0; i < courses.size(); i++){
			slot = courses.get(i).getFinalTime();
			day = slot.getDay();
			time = slot.getStartTime();
			//determine the row and column of current course
			switch(day){
			case 'M':
				column = 1;
				break;
			case 'T':
				column = 2;
				break;
			case 'W':
				column = 3;
				break;
			case 'R':
				column = 4;
				break;
			case 'F':
				column = 5;
				break;
			}
			
			switch(time){
			case 800:
				row = 1;
				break;
			case 1015:
				row = 2;
				break;
			case 1245:
				row = 3;
				break;
			case 1500:
				row = 4;
				break;
			case 1730:
				row = 5;
				break;
			case 1945:
				row = 6;
				break;
			}
			
			data[row][column] = courses.get(i).getCourseCode();
			
		}
		
	}
	
	public void exportTxt(){



		JFileChooser fc = new JFileChooser();


		//set file filter

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv file", "csv");

		fc.setAcceptAllFileFilterUsed(false);

		fc.addChoosableFileFilter(filter);

		fc.setFileFilter(filter);

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);


		//let user pick where to save the file

		int returnVal = fc.showSaveDialog(ExamSchedule.this);


		if(returnVal == JFileChooser.APPROVE_OPTION){

		try{

		FileWriter fw = new FileWriter(fc.getSelectedFile()+".csv");

		fw.write("CRN,Course Name,Day,Start,End,Final Time\n");
		System.out.println(courses.size());
		for(int i = 0; i<courses.size(); i++){

		//write to the file
		        fw.write(courses.get(i).toString());
		        fw.write("\n");
		}
		

		        fw.close();

		} catch(Exception e){

		e.printStackTrace();

		}

		}


		}
	
	public class CourseTableModel extends AbstractTableModel {

	    private ArrayList<Course> courses;

	    public void CourseTableModel(ArrayList<Course> courses) {
	        this.courses = new ArrayList<>(courses);
	    }

	    public int getRowCount() {
	        return 7;
	    }

	    @Override
	    public int getColumnCount() {
	        return 6;
	    }

	    @Override
	    public String getColumnName(int column) {
	        String name = "??";
	        switch (column) {
	            case 0:
	                name = "Times";
	                break;
	            case 1:
	                name = "Monday";
	                break;
	            case 2:
	            	name = "Tuesday";
	            	break;
	            case 3:
	            	name = "Wednesday";
	            	break;
	            case 4:
	            	name = "Thursday";
	            	break;
	            case 5:
	            	name = "Friday";
	            	break;
	        }
	        return name;
	    }

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        Class type = String.class;
	        switch (columnIndex) {
	            case 0:
	            case 1:
	                type = Integer.class;
	                break;
	        }
	        return type;
	    }

	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Course course = courses.get(rowIndex);
	        Object value = null;
	        switch (columnIndex) {
	            case 0:
	                break;
	            case 1:
	                break;
	        }
	        return value;
	    }            
	}


	
}

