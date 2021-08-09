package application;

import java.util.HashMap;

public class Label {
	
	private StringBuffer contents;
	private String title;
	private int idNum;
	private HashMap<String, StringBuffer> optimizableTags;
	private StringBuffer p2df = new StringBuffer("<p2df>66.0;21.0</p2df>");
	private StringBuffer r2dd = new StringBuffer("<r2dd>341.0;411.0;66.0;21.0</r2dd>");
	private StringBuffer str1, str2;
	
	public Label(String title, int n) {
		this.title = title;
		this.idNum = n;
		this.str1 = new StringBuffer("<str>"+title+"</str>");
		this.str2 = str1;
		
		this.optimizableTags = new HashMap<String, StringBuffer>();
		
		contents = new StringBuffer();
		generateContents();
		
		optimizableTags.put("str", str1);
		optimizableTags.put("p2df", p2df);
		optimizableTags.put("r2dd", r2dd);
	}
	
	private void generateContents() {
		
		tab(1);contents.append("<c cls=\"com.inductiveautomation.factorypmi.application.components.PMILabel\">\n");
		tab(2);contents.append("<c-comm>\n");
		tab(3);contents.append(p2df + "\n");
		tab(3);contents.append(r2dd + "\n");
		tab(3);contents.append(str1 + "\n");
		tab(3);contents.append("<lc>341.0;411.0;16;0;-;-</lc>\n");
		tab(2);contents.append("</c-comm>\n");
		tab(2);contents.append("<c-c m=\"setBackground\" s=\"1;clr\"><clr>-328965</clr></c-c>\n");
		tab(2);contents.append("<c-c m=\"setText\" s=\"1;str\">"+str2+"</c-c>\n");
		tab(1);contents.append("</c>\n");
		
		
		
	}
	
	private void tab(int n) {
		for (int i = 0; i < n; i++)
			contents.append("\t");
	}
	
	public void doStrSwap(int refNum) {
		this.str1 = new StringBuffer("<str id=\""+refNum+"\">"+title+"</str>");
		this.str2 = new StringBuffer("<ref>"+refNum+"</ref>");
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
	}
	
	public void refTag(String tagType, int refNum) {
		String ref = "<ref>"+refNum+"</ref>";
		
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.replace(0, Integer.MAX_VALUE, ref);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.replace(0, Integer.MAX_VALUE, ref);
		//else if (tagType.compareTo("str") == 0)
		//	this.str1.replace(0, Integer.MAX_VALUE, ref);
		
		contents.delete(0, Integer.MAX_VALUE); //clear the contents and re-generate
		generateContents();
	}
	
	public void idTag(String tagType, int refNum) {
		String tag = " id=\""+refNum+"\"";
		if (tagType.compareTo("r2dd") == 0)
			this.r2dd.insert(5, tag);
		else if (tagType.compareTo("p2df") == 0)
			this.p2df.insert(5, tag);
		//else if (tagType.compareTo("str") == 0)
		//	this.str1.insert(4, tag);
		
		contents.delete(0, Integer.MAX_VALUE);
		generateContents();
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
