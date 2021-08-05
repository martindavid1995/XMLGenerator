package application;

import java.util.Calendar;

public class Header {
	
	public static void genHeader(StringBuffer XML) {
		java.util.Date timestamp = Calendar.getInstance().getTime();
		XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		XML.append("<objects fpmi.archive.type=\"components\" framework.version=\"8.0.5.2019101516\" com.inductiveautomation.vision.version=\"10.0.5.0\" timestamp=\"");
		XML.append(timestamp+ "\">\n");
	}
	
}
