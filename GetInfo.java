import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class GetInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public int completedHours;
	private JCheckBox highSchool;
	private JCheckBox seniorBox;
	public boolean seniorStanding;
	public boolean completedHighSchool;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetInfo frame = new GetInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GetInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(null);
		
		setContentPane(contentPane);
		
		highSchool = new JCheckBox("Completed High School trigonometry and algebra");
		highSchool.setBounds(38, 50, 363, 23);
		contentPane.add(highSchool);
		
		seniorBox = new JCheckBox("Senior standing");
		seniorBox.setBounds(38, 85, 174, 23);
		contentPane.add(seniorBox);
		
		textField = new JTextField();
		textField.setBounds(38, 175, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblEnterNumberOf = new JLabel("Enter number of credit hours completed:");
		lblEnterNumberOf.setBounds(38, 152, 267, 16);
		contentPane.add(lblEnterNumberOf);
		
		JLabel lblCompleteTheFollowing = new JLabel("Check the boxes if the following applies to you:");
		lblCompleteTheFollowing.setBounds(27, 22, 338, 16);
		contentPane.add(lblCompleteTheFollowing);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(327, 243, 117, 29);
		contentPane.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Please enter the number of credit hours you "
							+ "have completed");
				} 
				else {
					completedHours = Integer.parseInt(textField.getText());
					try {
						if (highSchool.isSelected()) {
							completedHighSchool = true;
						} else {
							completedHighSchool = false;
						}
						if (seniorBox.isSelected()) {
							seniorStanding = true;
						}
						else {
							seniorStanding = false;
						}
						dispose();
						CourseEntry ce = new CourseEntry();
						ce.setVisible(true);
				
					} catch (Exception x) {
						x.printStackTrace();
					}
				}}});
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(6, 243, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					dispose();
					EnterClasses ec = new EnterClasses();
					ec.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	
}
