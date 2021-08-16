package application;

import java.util.Calendar;
/**
 * Generates the header for the XML document.
 * @author David Martin
 *
 */
public class Header {
	
	public static void genHeader(StringBuffer XML) {
		//Pull the current date and time
		java.util.Date timestamp = Calendar.getInstance().getTime();
		XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		XML.append("<objects fpmi.archive.type=\"components\" framework.version=\"8.0.5.2019101516\" com.inductiveautomation.vision.version=\"10.0.5.0\" timestamp=\"");
		XML.append(timestamp+ "\">\n");
		XML.append("\t<arraylist len=\"0\"/>\n"); //This is always set to length 0
	}
	
}
