package application;

import java.util.HashMap;

public class Label {
	
	private StringBuffer contents;
	private String title;
	private int idNum;
	private double x = 341.0;
	private double y = 411.0;
	private HashMap<String, StringBuffer> optimizableTags;
	private StringBuffer p2df = new StringBuffer("<p2df>80.0;21.0</p2df>");
	private StringBuffer r2dd = new StringBuffer("<r2dd>"+x+";"+y+";80.0;21.0</r2dd>");
	//private StringBuffer str1, str2; 
	private StringBuffer str;
	private StringBuffer rotationAngle = new StringBuffer("<str>RotationAngle</str>");
	private StringBuffer conveyorLabel = new StringBuffer("<str>ConveyorLabel</str>");
	
	public Label(String title, int n) {
		this.title = title;
		this.idNum = n;
		//this.str1 = new StringBuffer("<str>"+title+"</str>");
		//this.str2 = str1;
		this.str = getLabel();
		
		this.optimizableTags = new HashMap<String, StringBuffer>();
		
		contents = new StringBuffer();
		generateContents();
		
		//optimizableTags.put("str", str1);
		optimizableTags.put("p2df", p2df);
		optimizableTags.put("r2dd", r2dd);
		optimizableTags.put("str", str);
		optimizableTags.put("rotation", rotationAngle);
		optimizableTags.put("conveyor", conveyorLabel);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void changeCoords(double x, double y) {
		this.r2dd = new StringBuffer("<r2dd>"+x+";"+y+";80.0;21.0</r2dd>");
		contents.delete(0, Integer.MAX_VALUE); //clear the contents and re-generate
		generateContents();
	}
	
	private void generateContents() {
		
		tab(1);contents.append("<c cls=\"com.inductiveautomation.factorypmi.application.components.template.TemplateHolder\">\n");
		tab(2);contents.append("<c-comm>\n");
		tab(3);contents.append(p2df + "\n");
		tab(3);contents.append(r2dd + "\n");
		tab(3);contents.append(str + "\n");
		tab(3);contents.append("<lc>341.0;411.0;16;0;-;-</lc>\n");
		tab(2);contents.append("</c-comm>\n");
		//here goes the <c-c>
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
		
		if (idNum == 1) {
			contents.append("<str id=\"0\">Label</str>");
		}else {
			contents.append("<ref>0</ref>");
		}
		
		
		contents.append("</c-c>\n");
		tab(1);contents.append("</c>\n");
			
	}
	private StringBuffer getLabel() {
		/*if (idNum == 1)
			return new StringBuffer("<str>Label</str>");
		else
			return new StringBuffer("<str>Label "+(idNum-1)+"</str>"); 	
		*/
		return new StringBuffer("<str>lbl_"+title+"</str>");
	}
	
	private void tab(int n) {
		for (int i = 0; i < n; i++)
			contents.append("\t");
	}
	
	/*
	public void doStrSwap(int refNum) {
		this.str1 = new StringBuffer("<str id=\""+refNum+"\">"+title+"</str>");
		this.str2 = new StringBuffer("<ref>"+refNum+"</ref>");
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
	}
	*/
	
	public void refTag(String tagType, int refNum) {
		String ref = "<ref>"+refNum+"</ref>";
		
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("rotation") == 0)
			this.rotationAngle.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("conveyor") == 0)
			this.conveyorLabel.replace(0, Integer.MAX_VALUE, ref);
		
		
		contents.delete(0, Integer.MAX_VALUE); //clear the contents and re-generate
		generateContents();
	}
	
	public void idTag(String tagType, int refNum) {
		String tag = " id=\""+refNum+"\"";
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.insert(5, tag);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.insert(5, tag);
		else if (tagType.compareTo("rotation") == 0)
			this.rotationAngle.insert(4, tag);
		else if (tagType.compareTo("conveyor") == 0)
			this.conveyorLabel.insert(4, tag);
		
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
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
