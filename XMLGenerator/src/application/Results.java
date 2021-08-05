package application;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

public class Results {

	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showResults(StringBuffer XML) {
				
		JFrame frame = new JFrame("Generated XML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		final JTextArea textArea = new JTextArea(10,20);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroll.setBounds(0, 0, 1234, 470);
		scroll.setBounds(0, 0, 1234, 425);
		textArea.setText(XML.toString());
		textArea.setLineWrap(false);
		frame.getContentPane().add(scroll);
		
		JButton btnNewButton = new JButton("Copy to Clipboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Text Copied");
				StringSelection toCopy = new StringSelection(textArea.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(toCopy, toCopy);
			}
		});
		btnNewButton.setBounds(10, 436, 154, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setSize(1250, 509);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
}
