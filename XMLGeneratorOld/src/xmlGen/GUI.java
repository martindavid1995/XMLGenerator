package xmlGen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class GUI {

	private JFrame frame;

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
	private void initialize() {
		String labelVis = "";
		
		frame = new JFrame("XML Generator");
		frame.setBounds(100, 100, 370, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JLabel uglyGUI = new JLabel("XML Generator");
		uglyGUI.setHorizontalAlignment(SwingConstants.CENTER);
		uglyGUI.setBounds(36, 11, 281, 25);
		frame.getContentPane().add(uglyGUI);
		
		JLabel enterLabels = new JLabel("Enter your labels");
		enterLabels.setBounds(20, 47, 119, 14);
		frame.getContentPane().add(enterLabels);
		
		JTextArea inputField = new JTextArea();
		inputField.setBounds(20, 72, 106, 152);
		frame.getContentPane().add(inputField);
		
				
		JButton generateBTN = new JButton("Generate XML");
		generateBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				genXML.showWindow(inputField.getText());
			}
		});
		generateBTN.setBounds(187, 166, 130, 58);
		frame.getContentPane().add(generateBTN);
		
	
		
		
	}
}
