package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
/**
 * Main Menu GUI
 * Instructs the user how to use the application by providing directions, a field for input entry, mode select, manages warning messages, etc. 
 * @author David Martin
 *
 */
public class GUI {

	private JFrame frame;
	
	private String directionsLabels = "Paste a newline-separated list of labels to be generated\r\n\n"
			+ "The only allowed special character is the underscore '_' \n\nAll other special characters (*, %, $, etc) are prohibited\r\n\n"
			+ "Duplicate labels are prohibited";
	private String directionsCoords = "Enter a semicolon-separated list of x/y coordinates for a \nlabel followed by a newline that has the label name.\n\nRepeat for all labels. Format should be:\r\n"
			+ "28.0;94.0;10.0;30.0\r\n"
			+ "ERSC_202_30\r\n\n"
			+ "The only allowed special character is the underscore '_' \n\nAll other special characters (*, %, $, etc) are prohibited\r\n\n"
			+ "Duplicate labels are prohibited";
	private JTextArea directionsField;
	
	private ArrayList<String> dupeNames = new ArrayList<String>();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("XML Generator");
		frame.setBounds(100, 100, 568, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10,49,166,357);
		frame.getContentPane().add(scroll);
		JLabel lblNewLabel = new JLabel("Label Names");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 24, 166, 14);
		frame.getContentPane().add(lblNewLabel);
		
		directionsField = new JTextArea();
		directionsField.setBounds(203, 129, 328, 223);
		directionsField.setOpaque(false);
		frame.getContentPane().add(directionsField);
		directionsField.setColumns(10);
		directionsField.setLineWrap(true);
		directionsField.setText(directionsLabels);
		
		JComboBox labelType = new JComboBox();
		labelType.addActionListener(new ActionListener() {
			
			//Displaying the different instructions depending on which "mode" is selected.
			public void actionPerformed(ActionEvent e) {
				if (labelType.getSelectedItem() == "Labels + Coordinates") {
					directionsField.setText(directionsCoords);
				}else {
					directionsField.setText(directionsLabels);
				}
			}
		});
		labelType.setModel(new DefaultComboBoxModel(new String[] {"Label Names Only", "Labels + Coordinates"}));
		labelType.setBounds(203, 48, 328, 29);
		frame.getContentPane().add(labelType);
		
		
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Driver driver = new Driver();	
				if (checkForDupes(textArea.getText())) { //Checks for duplicates and pops the warning if they are found
					Warning.run(dupeNames);	
				}else {
					frame.setVisible(false); //quit this menu
					driver.doWork(textArea.getText(), labelType.getSelectedItem().toString().compareTo("Labels + Coordinates") == 0); //do the work	
				}
				
				
			}
		});
		btnNewButton.setBounds(203, 363, 154, 43);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("DIRECTIONS:");
		lblNewLabel_1.setBounds(203, 105, 154, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
				
		
		
	}
	
	/**
	 * Used to check input for duplicate labelNames so that we can pop a warning instead of 
	 * creating XML with duplicate labels (which surely would break things)
	 * @param input
	 * @return T/F
	 */
	private boolean checkForDupes(String input) {
		//Turn the block input into a List separated by each newline
		List<String> strippedInput = Driver.stripInput(input); 
		
		for (String s : strippedInput) {
			
			if (s.contains(";")) //Coordinate strings don't count (dupes are allowed)
				continue;
			
			if (Collections.frequency(strippedInput, s) > 1) 
				dupeNames.add(s);
							
		}
		
		return dupeNames.size() > 0 ? true : false;
	}
	
	
	
		
}
