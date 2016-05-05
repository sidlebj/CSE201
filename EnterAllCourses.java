import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterAllCourses extends JFrame {

	private JPanel contentPane;
	public static ArrayList<Course> coursesOffered = new ArrayList<Course>();
	private JTextField textField;
	private JRadioButton fall;
	private JRadioButton summer;
	private JRadioButton spring;
	private JRadioButton winter;
	private ButtonGroup group; 
	private String urlDetail; 
	private JButton btnEnter;
	private JTextField urlText;
	private JLabel lblNewUrl;
	private String newURL;
	private String year;
	private String term; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterAllCourses frame = new EnterAllCourses();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EnterAllCourses() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		
		
		spring = new JRadioButton("Spring");
		spring.setBounds(35, 100, 141, 23);
		contentPane.add(spring);
		spring.setSelected(true);
		
		summer = new JRadioButton("Summer");
		summer.setBounds(35, 124, 141, 23);
		contentPane.add(summer);
		
		fall = new JRadioButton("Fall");
		fall.setBounds(35, 148, 141, 23);
		contentPane.add(fall);
		
		winter = new JRadioButton("Winter (J-Term)");
		winter.setBounds(35, 173, 141, 23);
		contentPane.add(winter);
		
		
		group = new ButtonGroup();
		group.add(spring);
		group.add(summer);
		group.add(fall);
		group.add(winter);
		
		textField = new JTextField();
		textField.setText("2016");
		textField.setBounds(237, 122, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		setNewURL("http://ws.miamioh.edu/courseSectionV2/201620.xml?courseSubjectCode=CSE");
		
		JLabel lblEnterAYear = new JLabel("Enter a year");
		lblEnterAYear.setBounds(259, 104, 87, 16);
		contentPane.add(lblEnterAYear);
		
		JLabel lblToUpdateThe = new JLabel("<html>To update the courses available, select a term and enter a year.");
		lblToUpdateThe.setBounds(30, 22, 414, 23);
		contentPane.add(lblToUpdateThe);
		
		JLabel lblNoteYouCannot = new JLabel("Note: You cannot update the courses more than two semesters in advance. ");
		lblNoteYouCannot.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNoteYouCannot.setBounds(35, 45, 374, 16);
		contentPane.add(lblNoteYouCannot);
		
		
		btnEnter = new JButton("Enter");
		btnEnter.setBounds(377, 126, 67, 23);
		contentPane.add(btnEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setYear(textField.getText());
				String term = "";
				if (spring.isSelected()) {
					term = "20";
				}
				else if (summer.isSelected()) {
					term = "30";
				}
				else if (fall.isSelected()) {
					term = "10";
				}
				else if (winter.isSelected()) {
					term = "15";
				}
			
				setNewURL("http://ws.miamioh.edu/courseSectionV2/" + getYear() + term + ".xml?courseSubjectCode=CSE");
				
				try {
					start(newURL);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				urlText.setText(newURL);
			}
		});
		
		urlText = new JTextField();
		urlText.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		urlText.setBounds(30, 218, 402, 28);
		contentPane.add(urlText);
		urlText.setColumns(10);
		urlText.setEditable(false);
		urlText.setText("http://ws.miamioh.edu/courseSectionV2/201620.xml?courseSubjectCode=CSE");
		
		lblNewUrl = new JLabel("New URL");
		lblNewUrl.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewUrl.setBounds(205, 244, 61, 16);
		contentPane.add(lblNewUrl);
		
		
	}
	
	public static void start() throws Exception {
		
		URL url = new URL("http://ws.miamioh.edu/courseSectionV2/201620.xml?courseSubjectCode=CSE");
		URLConnection connection = url.openConnection();
		
		Document doc = parseXML(connection.getInputStream());
		NodeList crns = doc.getElementsByTagName("courseId");
		NodeList courseCodes = doc.getElementsByTagName("courseCode");
		NodeList credits = doc.getElementsByTagName("creditHoursHigh");
		NodeList schedules = doc.getElementsByTagName("courseSchedules");
		
		
	
		
		for (int i = 0; i < courseCodes.getLength(); i++) {
		
		
		String courseCode = courseCodes.item(i).getTextContent();
		int crn = Integer.parseInt(crns.item(i).getTextContent());
		int creditHours = Integer.parseInt(credits.item(i).getTextContent());
		
		NodeList test = schedules.item(i).getChildNodes();
		int num = test.getLength() / 2;
		MeetTimes finalTime = new MeetTimes();
		MeetTimes[] mt = new MeetTimes[] {new MeetTimes('N', 9999, 9999)};
		
		if (num == 0) {
			mt = new MeetTimes[] {new MeetTimes('N', 9999, 9999)};
			finalTime = new MeetTimes('N', 9999, 9999);
		}
		if (num == 1) {
			NodeList test1 = test.item(1).getChildNodes();
			String days = test1.item(15).getTextContent();
			if (days.length() == 0) {
				mt = new MeetTimes[]{ new MeetTimes('N', 9999, 9999)};
				finalTime = new MeetTimes('N', 9999, 9999);
			}
			else {
				mt = new MeetTimes[] {new MeetTimes(days.charAt(0), 9999, 9999)};
				finalTime = new MeetTimes('N', 9999, 9999);
				}
			}
		
		if (num == 2) {
			NodeList test1 = test.item(1).getChildNodes();
			NodeList test2 = test.item(3).getChildNodes();
			
			String days = test1.item(15).getTextContent();
			mt = new MeetTimes[days.length()];
			
			if (mt.length == 1) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			
			if (mt.length == 2) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[1] = new MeetTimes (days.charAt(1), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			if (mt.length == 3) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[1] = new MeetTimes (days.charAt(1), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[2] = new MeetTimes (days.charAt(2), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			
			
			finalTime = new MeetTimes (toChar(test2.item(15)),removeColon(test2.item(5)), removeColon(test2.item(7)));
		}
		
		if (num == 3) {
			NodeList test1 = test.item(1).getChildNodes();
			NodeList test2 = test.item(3).getChildNodes();
			NodeList test3 = test.item(5).getChildNodes();
			
			String days = test1.item(15).getTextContent() + test2.item(15).getTextContent();
			mt = new MeetTimes[days.length()];
			
			
			if (mt.length == 2) {
				mt[1] = new MeetTimes(days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[0] = new MeetTimes(days.charAt(1), removeColon(test2.item(5)), removeColon(test2.item(7)));
			}
			if (mt.length == 3) {
				mt[2] = new MeetTimes(days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[0] = new MeetTimes(days.charAt(1), removeColon(test2.item(5)), removeColon(test2.item(7)));
				mt[1] = new MeetTimes(days.charAt(2), removeColon(test2.item(5)), removeColon(test2.item(7)));
			}
				
		    finalTime = new MeetTimes (toChar(test3.item(15)),removeColon(test3.item(5)), removeColon(test3.item(7)));
		}
		
		coursesOffered.add(new Course(crn, courseCode, creditHours, mt, finalTime));
		}
	
	}
	
	public static void start(String newURL) throws Exception {
		
		URL url = new URL(newURL);
		URLConnection connection = url.openConnection();
		
		Document doc = parseXML(connection.getInputStream());
		NodeList crns = doc.getElementsByTagName("courseId");
		NodeList courseCodes = doc.getElementsByTagName("courseCode");
		NodeList credits = doc.getElementsByTagName("creditHoursHigh");
		NodeList schedules = doc.getElementsByTagName("courseSchedules");
		
		
	
		
		for (int i = 0; i < courseCodes.getLength(); i++) {
		
		
		String courseCode = courseCodes.item(i).getTextContent();
		int crn = Integer.parseInt(crns.item(i).getTextContent());
		int creditHours = Integer.parseInt(credits.item(i).getTextContent());
		
		NodeList test = schedules.item(i).getChildNodes();
		int num = test.getLength() / 2;
		MeetTimes finalTime = new MeetTimes();
		MeetTimes[] mt = new MeetTimes[] {new MeetTimes('N', 9999, 9999)};
		
		if (num == 0) {
			mt = new MeetTimes[] {new MeetTimes('N', 9999, 9999)};
			finalTime = new MeetTimes('N', 9999, 9999);
		}
		if (num == 1) {
			NodeList test1 = test.item(1).getChildNodes();
			String days = test1.item(15).getTextContent();
			if (days.length() == 0) {
				mt = new MeetTimes[]{ new MeetTimes('N', 9999, 9999)};
				finalTime = new MeetTimes('N', 9999, 9999);
			}
			else {
				mt = new MeetTimes[] {new MeetTimes(days.charAt(0), 9999, 9999)};
				finalTime = new MeetTimes('N', 9999, 9999);
				}
			}
		
		if (num == 2) {
			NodeList test1 = test.item(1).getChildNodes();
			NodeList test2 = test.item(3).getChildNodes();
			
			String days = test1.item(15).getTextContent();
			mt = new MeetTimes[days.length()];
			
			if (mt.length == 1) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			
			if (mt.length == 2) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[1] = new MeetTimes (days.charAt(1), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			if (mt.length == 3) {
				mt[0] = new MeetTimes (days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[1] = new MeetTimes (days.charAt(1), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[2] = new MeetTimes (days.charAt(2), removeColon(test1.item(5)), removeColon(test1.item(7)));
			}
			
			
			finalTime = new MeetTimes (toChar(test2.item(15)),removeColon(test2.item(5)), removeColon(test2.item(7)));
		}
		
		if (num == 3) {
			NodeList test1 = test.item(1).getChildNodes();
			NodeList test2 = test.item(3).getChildNodes();
			NodeList test3 = test.item(5).getChildNodes();
			
			String days = test1.item(15).getTextContent() + test2.item(15).getTextContent();
			mt = new MeetTimes[days.length()];
			
			
			if (mt.length == 2) {
				mt[1] = new MeetTimes(days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[0] = new MeetTimes(days.charAt(1), removeColon(test2.item(5)), removeColon(test2.item(7)));
			}
			if (mt.length == 3) {
				mt[2] = new MeetTimes(days.charAt(0), removeColon(test1.item(5)), removeColon(test1.item(7)));
				mt[0] = new MeetTimes(days.charAt(1), removeColon(test2.item(5)), removeColon(test2.item(7)));
				mt[1] = new MeetTimes(days.charAt(2), removeColon(test2.item(5)), removeColon(test2.item(7)));
			}
				
		    finalTime = new MeetTimes (toChar(test3.item(15)),removeColon(test3.item(5)), removeColon(test3.item(7)));
		}
		
		coursesOffered.add(new Course(crn, courseCode, creditHours, mt, finalTime));
		}
	
	}
	
	
	
	public static int removeColon (Node n) {
		String s = n.getTextContent();
		if (s.length() != 5) {
			return 0;
		}
		else {
			String temp = s.substring(0, 2) + s.substring(3);
			return Integer.parseInt(temp);
		}
	}
	
	public static char toChar (Node n) {
		String s = n.getTextContent();
		if (s.length() > 1) {
			return '*';
		}
		return s.charAt(0);
	}
	
	private static Document parseXML (InputStream stream) {
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			
			doc = objDocumentBuilder.parse(stream);
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}
	
	private String getURLDetail () {
		return this.urlDetail;
	}
	
	private void setURLDetail(String urlDetail) {
		this.urlDetail = urlDetail;
	}
	
	private String newURL () {
		return this.newURL;
	}
	
	private void setNewURL(String newURL) {
		this.newURL = newURL;
	}
	
	private void setYear (String year) {
		this.year = year;
	}
	
	private String getYear() {
		return this.year;
	}
	
	private String getTerm() {
		return this.term;
	}
	
	private String setTerm(String term) {
		return this.term;
	}
}


