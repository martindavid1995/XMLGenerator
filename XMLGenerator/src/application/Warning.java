package application;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * GUI of a Warning message used to alert the user that they have supplied
 * invalid duplicate label names. Shows the user which label names are duplicates,
 * and how many duplicates of each label name exist (in parenthesis). Also allows
 * the user to go back and edit their existing list to correct it, instead of having to
 * paste in a new list of input from scratch. 
 * @author David Martin
 *
 */
public class Warning {

	
	private JPanel contentPane;
	private static ArrayList<String> dupeList;
	private JTextArea txtrTheFollowingLabels;
	private JFrame frame;
	private static TreeMap<String, Integer> dupeMap = new TreeMap<String, Integer>();
	
	/**
	 * Launch the application. We pass in the ArrayList of duplicate names
	 * from the main GUI.java
	 */
	public static void run(ArrayList<String> dupeNames) {
		dupeList = dupeNames;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Warning warning = new Warning();
					warning.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Warning() {
		init();
	}
	
	/**
	 * The frame itself.
	 */
	private void init() {
		frame = new JFrame();
		frame.setTitle("Warning - Duplicate Labels");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel();
		label.setBounds(5, 11, 302, 29);
		label.setText("Warning: Duplicate Label Names Detected!\n\n ");
		contentPane.add(label);
				
		
		txtrTheFollowingLabels = new JTextArea();
		txtrTheFollowingLabels.setLineWrap(true);
		txtrTheFollowingLabels.setText("Duplicate labels are not accepted and must be removed\r\nin order for this application to properly function.\nThe following duplicate labels exist in your input with the (number of instances):");
		txtrTheFollowingLabels.setWrapStyleWord(true);
		txtrTheFollowingLabels.setBounds(5, 33, 495, 58);
		txtrTheFollowingLabels.setOpaque(false);
		contentPane.add(txtrTheFollowingLabels);
		txtrTheFollowingLabels.setColumns(10);
		
		JTextArea dupePrint = new JTextArea();
		dupePrint.setWrapStyleWord(true);
		dupePrint.setLineWrap(true);
		dupePrint.setOpaque(false);
		
		//Pull the list of duplicate label names
		dupePrint.setText(makePretty());
		
		
		JScrollPane scroll = new JScrollPane(dupePrint, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(5, 90, 467, 185);
		contentPane.add(scroll);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dupeList.clear();
				frame.setVisible(false);
			}
		});
		
		btnGoBack.setBounds(5, 278, 137, 23);
		contentPane.add(btnGoBack);
	}
	
	/**
	 * Generates a string of the Label names that are duplicates, followed by how many times
	 * each label is duplicated. For example, an invalid input as such:
	 * 
	 * one
	 * two
	 * one
	 * three
	 * two
	 * one
	 * 
	 * results in: 
	 * one(3), two(2)
	 * @returns formatted String of which labels are duplicates
	 */
	private String makePretty() {
		StringBuffer result = new StringBuffer();
		
		for (String s : dupeList) {
			if (!dupeMap.containsKey(s)) {
				dupeMap.put(s, 1);
			}else {
				dupeMap.put(s, dupeMap.get(s) + 1);
			}
		}
		
		for (String s : dupeMap.keySet()) {
			result.append(s + " ("+dupeMap.get(s)+")");	
			if (!dupeMap.lastKey().equals(s)) { //All labels except for the last one get the comma
				result.append(", ");
			}
		}
		
		return result.toString();
	}

	


}
