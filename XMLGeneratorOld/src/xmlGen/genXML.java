package xmlGen;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**Remove refs, and put everything in plaintext first and get it working
 * Create radio buttons for the different 
 * 
 * @author David Martin
 *
 */
public class genXML {
	
	private static StringBuffer XML = new StringBuffer();
	
	//Default coords. Would you like me to have these increment slightly to make them offset from each other?
	private static final double[] p2df = {66.0, 21.0};
	private static final double[] r2dd = {45.0, 35.0, 66.0, 21.0};
	private static final double[] lc = {45.0, 35.0, 16, 0, 0.18181819, 0.5714286};
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showWindow(String in) {
		XML = generateXMLDriver(in);
		
		JFrame frame = new JFrame("Generated XML");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTextArea textArea = new JTextArea(10,20);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setText(XML.toString());
		textArea.setLineWrap(false);
		frame.add(scroll);
		frame.setSize(1250, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
	
	private static StringBuffer generateXMLDriver(String input){
		List<String> strippedInput = stripInput(input);
		int idNum = 0;
		generateHeader();
		tab(1);
		XML.append("<arraylist len=\""+ strippedInput.size() + "\">\n");
		
		for (String s : strippedInput) {
			idNum++;
			//generate XML for each item
			generateXMLComponent(s, idNum);
		}
		XML.append("</objects>\n");
		
		return XML;
	}
	
	private static void generateXMLComponent(String componentName, int idNum) {
		
		
		
		
	}
	
	private static void doCoords(double[] coords) {
		for (int i = 0; i < coords.length; i++) {
			XML.append(coords[i]);
			if (i != coords.length-1)
				XML.append(";");
		}
			
	}
	
	
	private static void date(int date) {
		XML.append("<date>"+date+"</date>\n");//Can this have leading 0s?
	}

	
	private static void tab(int num) {
		for (int i = 0; i < num; i++) XML.append("\t");
	}
		
	
	
	
	private static void generateHeader() {
		java.util.Date timestamp = java.util.Calendar.getInstance().getTime();
		
		XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		XML.append("<objects fpmi.archive.type=\"components\" framework.version=\"8.0.5.2019101516\" com.inductiveautomation.vision.version=\"10.0.5.0\" timestamp=\"");
		XML.append(timestamp + "\">\n");
		
		
	}
	
	private static List<String> stripInput(String in) {
		String[] input = in.split("\\r?\\n");
		List<String> inputList = new LinkedList<String>();
		
		//Strip out any unintentional newlines
		for (int i = 0; i < input.length; i++) {
			if (!input[i].equals("")) {
				inputList.add(input[i]);
			}
		}
		
		return inputList;
	}
	
}

