package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frmXmlGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmXmlGenerator.setVisible(true);
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
		frmXmlGenerator = new JFrame();
		frmXmlGenerator.setTitle("XML Generator");
		frmXmlGenerator.setBounds(100, 100, 437, 397);
		frmXmlGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXmlGenerator.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 49, 166, 260);
		frmXmlGenerator.getContentPane().add(textArea);
		
		JLabel lblNewLabel = new JLabel("Label Names");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 24, 166, 14);
		frmXmlGenerator.getContentPane().add(lblNewLabel);
		
		JComboBox labelType = new JComboBox();
		labelType.setModel(new DefaultComboBoxModel(new String[] {"LabelVis_ESPCs", "LabelVis_Misc", "LabelVis_Motors", "LabelVis_PEs"}));
		labelType.setBounds(203, 48, 189, 27);
		frmXmlGenerator.getContentPane().add(labelType);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmXmlGenerator.setVisible(false); //quit this menu
				Driver driver = new Driver();
				driver.doWork(textArea.getText(), labelType.getSelectedItem().toString()); //do the work				
			}
		});
		btnNewButton.setBounds(203, 110, 154, 43);
		frmXmlGenerator.getContentPane().add(btnNewButton);
		
	}
}
