package application;

import java.util.HashMap;
/**
 * Represents a Label. 
 * Holds the contents, position, and other elements of a Label.
 * Each label has a contents StringBuffer which is it's "plaintext" XML code. We generate
 * that code in this class. 
 * @author David Martin
 *
 */
public class Label {
	
	private StringBuffer contents;
	private String title;
	private int idNum;
	
	//Base coordinates if user does not elect to reposition labels. 
	private double x = 341.0;
	private double y = 411.0;
	
	//Tags (these are all optimizable)
	private StringBuffer p2df = new StringBuffer("<p2df>80.0;21.0</p2df>");
	private StringBuffer r2dd = new StringBuffer("<r2dd>"+x+";"+y+";80.0;21.0</r2dd>");
	private StringBuffer str;
	private StringBuffer rotationAngle = new StringBuffer("<str>RotationAngle</str>");
	private StringBuffer conveyorLabel = new StringBuffer("<str>ConveyorLabel</str>");
	
	//Keeping track of the tags that we allow to be turned into a <ref> 
	private HashMap<String, StringBuffer> optimizableTags;
	
	/**
	 * Label constructor. Creates a Label and stores it's optmiziable tags. 
	 * @param title
	 * @param n
	 */
	public Label(String title, int n) {
		this.title = title;
		this.idNum = n;
		this.str = getLabel();
		
		this.optimizableTags = new HashMap<String, StringBuffer>();
		
		contents = new StringBuffer();
		generateContents();
		
		//These tags are reffable 
		optimizableTags.put("p2df", p2df);
		optimizableTags.put("r2dd", r2dd);
		optimizableTags.put("str", str);
		optimizableTags.put("rotation", rotationAngle);
		optimizableTags.put("conveyor", conveyorLabel);
	}
	
	/**
	 * Changes the x and y coordinates of a label's <r2dd> tag. Used for
	 * repositioning labels when the user specifies coordinates. 
	 * @param x position
	 * @param y position
	 */
	public void changeCoords(double x, double y) {
		this.r2dd = new StringBuffer("<r2dd>"+x+";"+y+";80.0;21.0</r2dd>");
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
	}
	
	/**
	 * Generates the content for each label. 
	 */
	private void generateContents() {
		
		tab(1);contents.append("<c cls=\"com.inductiveautomation.factorypmi.application.components.template.TemplateHolder\">\n");
		tab(2);contents.append("<c-comm>\n");
		tab(3);contents.append(p2df + "\n");
		tab(3);contents.append(r2dd + "\n");
		tab(3);contents.append(str + "\n");
		tab(3);contents.append("<lc>341.0;411.0;16;0;-;-</lc>\n");
		tab(2);contents.append("</c-comm>\n");
		tab(2);contents.append("<c-c m=\"setParameterValues\" s=\"1;java.util.Map\">\n");
		tab(3);contents.append("<o cls=\"java.util.HashMap\">\n");
		tab(4);contents.append("<o-c m=\"put\" s=\"2;O;O\">\n");
		tab(5);contents.append(rotationAngle+"\n");
		tab(5);contents.append("<int>0</int>\n");
		tab(4);contents.append("</o-c>\n");
		tab(4);contents.append("<o-c m=\"put\" s=\"2;O;O\">\n");
		tab(5);contents.append(conveyorLabel+"\n");
		tab(5);contents.append("<str>"+title+"</str>\n");
		tab(4);contents.append("</o-c>\n");
		tab(3);contents.append("</o>\n");
		tab(2);contents.append("</c-c>\n");
		
		
		tab(2);contents.append("<c-c m=\"setTemplatePath\" s=\"1;str\">");
		
		//The first label id's the <ref>0</ref> which is just the <str>Label</str> tag
		//Every subsequent label uses this tag, so we just hard-code it in 
		if (idNum == 1) {
			contents.append("<str id=\"0\">Label</str>");
		}else {
			contents.append("<ref>0</ref>");
		}
				
		contents.append("</c-c>\n");
		tab(1);contents.append("</c>\n");
			
	}
	
	/**
	 * Adds tabs. Very helpful for formatting. 
	 * @param number of tabs
	 */
	private void tab(int n) {
		for (int i = 0; i < n; i++)
			contents.append("\t");
	}
	
	/**
	 * Used for inserting a <ref>#</ref> into a Label's contents.
	 * Finds the tag to be reffed, rewrites it to it's <ref> tags, and 
	 * reinserts it into the contents for that particular Label. 
	 * @param tagType
	 * @param refNum
	 */
	public void refTag(String tagType, int refNum) {
		String ref = "<ref>"+refNum+"</ref>";
		
		//Check the tagType, wipe it's contents and replace it with ref 
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("rotation") == 0)
			this.rotationAngle.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("conveyor") == 0)
			this.conveyorLabel.replace(0, Integer.MAX_VALUE, ref);
		
		//Clear the contents and regenerate with the new tag contents
		contents.delete(0, Integer.MAX_VALUE); 
		generateContents();
	}
	/**
	 * Used for inserting a <tag id="#"> for a label tag that is going to be reffed.
	 * Works similar to the above, just inserts the "id="#" bit into that particular tag. 
	 * @param tagType
	 * @param refNum
	 */
	public void idTag(String tagType, int refNum) {
		String tag = " id=\""+refNum+"\"";
		
		//Check for the tagType and insert the tag into it
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.insert(5, tag);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.insert(5, tag);
		else if (tagType.compareTo("rotation") == 0)
			this.rotationAngle.insert(4, tag);
		else if (tagType.compareTo("conveyor") == 0)
			this.conveyorLabel.insert(4, tag);
		
		//Clear the contents and regenerate with the new reffed tag
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
	}
	
	/**
	 * Getters and toString()
	 */
	public String getTitle() {
		return title;
	}
	private StringBuffer getLabel() {	
		return new StringBuffer("<str>lbl_"+title+"</str>");
	}
	public Double getX() {
		return x;
	}
	public Double getY() {
		return y;
	}
	public String toString() {
		return contents.toString();
	}
	public HashMap<String,StringBuffer> getOptimizableTags() {
		return optimizableTags;
	}
	public int getId() {
		return idNum;
	}
	
	
}
