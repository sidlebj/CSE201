package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import FileParser.CourseInfo;
import FileParser.ExamConflicts;
import FileParser.FileParser;

public class MainFrame extends JFrame implements ActionListener {

	static private final String newline = "\n";
	
	private boolean disableGoButton;
	public JButton goButton;
	private JButton openButton;
	private JTextArea log;
	final JFileChooser fc = new JFileChooser();
	public File csvFile;
	public FileParser fp;
	public ExamConflicts conflicts = new ExamConflicts();
	public ArrayList<CourseInfo> allConflicts;
	public JList<String> teachers;

	public MainFrame() {
		super("Exam Conflict Finder");

		this.csvFile = null; // we'll assign this when user gives file

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5,20);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		

		disableGoButton = true; 
		
		setBounds(0, 0, 700, 700); // position and size
		// Add all buttons, labels, and graphics
		openButton = new JButton("<html><font size=+1>Upload a .csv file...</font></html>",
				new ImageIcon("Open16.gif"));
		goButton = new JButton("<html><font size =+2>Go</font></html>");
		goButton.setEnabled(false);
		add(generateTop(), BorderLayout.NORTH); // Generate greeting and instructions
		add(generateBottom(goButton), BorderLayout.SOUTH); // Generate directions and Go button
		add(generateCenter(openButton), BorderLayout.CENTER); // Generate Open button
		this.setBackground(new Color(235, 235, 240));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Miami_m.png"));
		
	}

	// Generate the center of the border layout,
	// including the "upload" button
	private JPanel generateCenter(JButton openButton) {
		JPanel centerPanel = new JPanel(new BorderLayout());

		openButton.setToolTipText("Upload course list file");
		openButton.addActionListener(this);
		openButton.setPreferredSize(new Dimension(300, 60));

		JPanel openPanel = new JPanel();
		openPanel.add(openButton);
		centerPanel.add(openPanel, BorderLayout.CENTER);

		centerPanel.setBackground(new Color(235, 235, 240));
		return centerPanel;
	}

	// Generate the south of the border layout,
	// including the "go" button and the "instructions" label
	public JPanel generateBottom(JButton goButton) {
		JPanel southPanel = new JPanel(new BorderLayout());
		goButton.setPreferredSize(new Dimension(100, 60));
		goButton.setToolTipText("Launch program");
		goButton.addActionListener(this);
		southPanel.setBackground(new Color(235, 235, 240));
		southPanel.add(goButton, BorderLayout.SOUTH);

		JLabel instructions = new JLabel("<html><font size =+2>Instructions:</font><ol>"
				+ "<li><font size =+1>Upload your file above.</font></li><li><font size=+1>"
				+ "Press the 'Go' button.</font></li></ol>");
		southPanel.add(instructions, BorderLayout.NORTH);

		return southPanel;
	}

	// Generate the south of the border layout,
	// including the "Welcome" and Miami logos, as well as the purpose label
	public JPanel generateTop() {
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(new JLabel(new ImageIcon("Welcome.png")), BorderLayout.CENTER);
		northPanel.setBackground(new Color(235, 235, 240));
		JLabel instructions = new JLabel("<html><font size =+2>The purpose of this software is to:</font><ul><li>"
				+ "<font size =+1>Find any final exam"
				+ " conflicts among scheduled Miami University classes.</font></li><li>"
				+ "<font size =+1>Present a list of these conflicts.</font></li></ul></html>");
		northPanel.add(instructions, BorderLayout.SOUTH);
		northPanel.add(new JLabel(new ImageIcon("Miami_m.png")), BorderLayout.NORTH);

		return northPanel;

	}

	// Here we create the "Teacher" filter
	public JScrollPane generateFilters() {
		DefaultListModel<String> listModel = new DefaultListModel<>();

		for(int i = 0; i < FileParser.arrayOfClasses.size(); i++) {
			if(!listModel.contains(FileParser.arrayOfClasses.get(i).getName())) {
				listModel.addElement(FileParser.arrayOfClasses.get(i).getName());
			}
		}

		teachers = new JList<>(listModel);
		teachers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teachers.setLayoutOrientation(JList.VERTICAL);

		JScrollPane listScroller = new JScrollPane(teachers);
		listScroller.setPreferredSize(new Dimension(250, 80));

		return listScroller;
	}

	// Button listener
	public void actionPerformed(ActionEvent e) {
		//Handle open button action.
		if (e.getSource() == openButton) {
			System.out.println("OPENING FILE");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(MainFrame.this);

			// Get the file from the user
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				this.csvFile = fc.getSelectedFile();
				
				// Check if the fle is valid, and if so, parse it
				if (this.csvFile != null) {
					System.out.println(csvFile.getName());
					FileParser fp = new FileParser(csvFile);
					fp.parse();
					FileParser.arrayOfClasses = new ArrayList<>(fp.getClasses());
					openButton.setText("<html><font size=+1>File Selected</font></html>");
					add(generateFilters(),BorderLayout.EAST);
				}
				// Only allow the "Go" button to enable if the file is a .csv
				if (this.csvFile.getName().contains(".csv")) {
				goButton.setEnabled(true); }

				log.append("Opening: " + csvFile.getName() + "." + newline);
			} else {
				log.append("Open command cancelled by user." + newline);
			}
		}

		// Handle the "Go" button
		if (e.getSource() == goButton) {
			// Apply the Teacher filter choice to the classes file
			String lsv = teachers.getSelectedValue();
			System.out.println(lsv);

			ArrayList<CourseInfo> tmp = new ArrayList<>();
			
			if(lsv != null){
				for(int i = 0; i < FileParser.arrayOfClasses.size(); i++) {
					if(FileParser.arrayOfClasses.get(i).getName().equals(lsv)) {
						tmp.add(FileParser.arrayOfClasses.get(i));
					}
				}
				FileParser.arrayOfClasses = tmp;
			}
			
			// Launch the results window
			JFrame resultsFrame = new ResultsWindow(FileParser.arrayOfClasses);
			resultsFrame.setVisible(true);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			resultsFrame.setLocation(dim.width/2-resultsFrame.getSize().width/2, dim.height/2-resultsFrame.getSize().height/2);
			resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose(); // end the first window

		}
	}

	public static void main(String[] args) {
		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				// Launch the initial GUI
				JFrame frame = new MainFrame();
				frame.setVisible(true);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();

			}
		});
	}

	/////////// GETTERS AND SETTERS /////////////
	
	public void setGoButton(boolean b) {
		this.disableGoButton = b;
	}

	public boolean isGoDisabled() {
		return disableGoButton;
	}

}
