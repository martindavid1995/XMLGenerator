package application;
/**
 * Represents a Reference.
 * Each Reference has a "reference" to the Label it originates from, it's contents, its title (to search for it) and an ID number. 
 * @author David Martin
 *
 */
public class Reference {
	private Label label;
	private String contents;
	private String title; //str p2df or r2dd
	private int refId;
	private boolean isUsed = false;
	
	//Constructs a Reference
	public Reference(Label label, String title, String contents, int refId) {
		this.label = label;
		this.contents = contents;
		this.title = title;
		this.refId = refId;
	}
	
	//Getters
	public int getRefId() {
		return refId;
	}
	public Label getLabel() {
		return label;
	}
	public String getContents() {
		return contents;
	}
	public String getTitle() {
		return title;
	}
	public void useRef() {
		isUsed = true;
	}
	public boolean isUsed() {
		return isUsed;
	}
	
}
