import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class CourseEntryTest extends JPanel {
	
	private JFrame content = new JFrame();
	private JSplitPane panel;
	
	private void setUp() {
		content.setBounds(100, 100, 450, 300);
		content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.setVisible(true);
	}
	
	public CourseEntryTest() {
		setUp();
		JScrollPane coursesOffered = new JScrollPane();
		panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel test = new JLabel("Test");
		panel.add(test);
		
		content.setContentPane(panel);
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseEntryTest test = new CourseEntryTest();
					test.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
