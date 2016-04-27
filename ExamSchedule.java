import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//this class serves as a template for our weekly schedule and exam schedule classes
public class ExamSchedule extends JFrame{

	private JPanel contentPane;
	private String[] columnNames = {"Time", "M", "T", "W", "R", "F"};
	String[][] data = { {"8", "", "", "", "", "CSE 102"}, 
			{"9", "","", "", "", "CSE 102"},
			{"10", "","", "CONFLICT", "", ""},
			{"11", "","", "", "", ""},
			{"12", "","", "", "", ""},
			{"1", "","", "", "", ""},
			{"2", "","", "", "", ""},
			{"3", "","", "", "", ""},
			{"4", "","", "", "", ""},
			{"5", "","", "", "", ""},
			{"6", "","", "", "", ""},
			{"7", "","", "", "", ""}};
	
	private GridBagConstraints c = new GridBagConstraints();
	private JTable examTable = new JTable(data, columnNames);
	
	public static void main(String[] args) {
		
		ExamSchedule gooby = new ExamSchedule();
		gooby.setVisible(true);

	}
	
	public ExamSchedule (){
		setTitle("Finals Schedule for User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		
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
		c.gridwidth = 3;
		contentPane.add(schedule, c);
		
		//Create a table that will hold our exams
		
		
		c.ipadx = contentPane.getWidth();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 3;
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
				Tester3 entry = new Tester3();
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
		c.gridx = 2;
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
		
	}
	
}


