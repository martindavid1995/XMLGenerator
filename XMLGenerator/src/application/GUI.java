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
import java.awt.event.ActionEvent;

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("XML Generator");
		frame.setBounds(100, 100, 437, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		//textArea.setBounds(10, 49, 166, 260);
		//frmXmlGenerator.getContentPane().add(textArea);
		
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(10,49,166,260);
		frame.getContentPane().add(scroll);
		JLabel lblNewLabel = new JLabel("Label Names");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 24, 166, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox labelType = new JComboBox();
		labelType.setModel(new DefaultComboBoxModel(new String[] {"LabelVis_ESPCs", "LabelVis_Misc", "LabelVis_Motors", "LabelVis_PEs"}));
		labelType.setBounds(203, 48, 189, 27);
		frame.getContentPane().add(labelType);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); //quit this menu
				Driver driver = new Driver();
				driver.doWork(textArea.getText(), labelType.getSelectedItem().toString()); //do the work				
			}
		});
		btnNewButton.setBounds(203, 110, 154, 43);
		frame.getContentPane().add(btnNewButton);
		
	}
}
