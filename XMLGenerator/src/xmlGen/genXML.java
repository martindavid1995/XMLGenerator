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
		
		tab(2);XML.append("<o id=\"");XML.append(idNum-1+"\" "+"cls=\"com.inductiveautomation.factorypmi.application.binding.SimpleBoundPropertyAdapter\">\n");
		tab(3);XML.append("<o-c m=\"setInteraction\" s=\"1;com.inductiveautomation.factorypmi.application.binding.PropertyInteractionDescriptor\">\n");
		tab(4);XML.append("<o cls=\"com.inductiveautomation.factorypmi.application.binding.PropertyInteractionDescriptor\">\n");
		tab(5);XML.append("<o-c m=\"setAdapter\" s=\"1;com.inductiveautomation.factorypmi.application.binding.Adapter\">");ref(0);XML.append("</o-c>\n");
		tab(5);XML.append("<o-c m=\"setListener\" s=\"1;java.beans.PropertyChangeListener\">");ref(0);XML.append("</o-c>\n");
		tab(5);XML.append("<o-c m=\"_setSymbolicSourcePath\" s=\"1;str\"><str>LabelTemplate</str></o-c>\n");
		tab(5);XML.append("<o-c m=\"setSourceProperty\" s=\"1;str\"><str>"+"~~LABEL~~"+"</str></o-c>\n");
		tab(4);XML.append("</o>\n");
		tab(3);XML.append("</o-c>\n");
		tab(3);XML.append("<o-c m=\"setQValue\" s=\"1;com.inductiveautomation.ignition.common.model.values.QualifiedValue\">\n");
		tab(4);XML.append("<o cls=\"com.inductiveautomation.ignition.common.model.values.BasicQualifiedValue\">\n");
		tab(5);XML.append("<o-ctor s=\"3;O;com.inductiveautomation.ignition.common.model.values.QualityCode;date\">\n");
		tab(6);XML.append("<true/>\n");
		tab(6);XML.append("<qc>192</qc>\n");
		tab(6);date(1234567890); //make this whatever he had it as before
		tab(5);XML.append("</o-ctor>\n");
		tab(4);XML.append("</o>\n");
		tab(3);XML.append("</o-c>\n");
		tab(3);XML.append("<o-c m=\"setTarget\" s=\"1;java.awt.Component\">\n");
		tab(4);XML.append("<c id=\""+idNum+"\" cls=\"com.inductiveautomation.factorypmi.application.components.template.TemplateHolder\">\n");
		tab(5);XML.append("<c-comm>\n");
		tab(6);XML.append("<p2df>");doCoords(p2df);XML.append("</p2df>\n");
		tab(6);XML.append("<r2dd>");doCoords(r2dd);XML.append("</r2dd>\n");
		tab(6);XML.append("<str>lbl_"+componentName+"</str>\n");
		tab(6);XML.append("<lc>");doCoords(lc);XML.append("</lc>\n");
		tab(5);XML.append("</c-comm>\n");
		tab(5);XML.append("<c-c m=\"putClientProperty\" s=\"2;O;O\">\n");
		tab(6);XML.append("<str>vision.custom.functions</str>\n");
		tab(6);XML.append("<o cls=\"java.util.LinkedHashMap\"/>\n");
		tab(5);XML.append("</c-c>\n");
		tab(5);XML.append("<c-c m=\"setParameterValues\" s=\"1;java.util.Map\">\n");
		tab(6);XML.append("<o cls=\"java.util.HashMap\">\n");
		tab(7);XML.append("<o-c m=\"put\" s=\"2;O;O\">\n");
		tab(8);XML.append("<str>ConveyorLabel</str>\n");
		tab(8);XML.append("<str>"+componentName+"</str>\n");
		tab(7);XML.append("</o-c>\n");
		tab(6);XML.append("</o>\n");
		tab(5);XML.append("</c-c>\n");
		tab(5);XML.append("<c-c m=\"setTemplatePath\" s=\"1;str\"><str>Label</str></c-c>\n");
		tab(4);XML.append("</c>\n");
		tab(3);XML.append("</o-c>\n");
		tab(3);XML.append("<o-c m=\"setTargetPropertyName\" s=\"1;str\"><str>visible</str></o-c>\n");
		tab(3);XML.append("<o-c m=\"setValueClass\" s=\"1;java.lang.Class\"><class>b</class></o-c>\n");
		tab(2);XML.append("</o>\n");
		tab(1);XML.append("</arraylist>\n");
		tab(1);ref(1);XML.append("\n");
		
		
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
	private static void ref(int id) {
		XML.append("<ref>" + id + "</ref>");
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

