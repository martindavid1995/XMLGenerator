package application;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Handles all of the optimizations - particularly the logic of reffing tags. 
 * Also repositions the labels if the user wants to do that. 
 * @author David Martin
 *
 */
public class Optimize {
	private static HashMap<Integer, Reference> baseRefs = new HashMap<Integer, Reference>();
	private static int refIdCounter = 1;
	public final static boolean DEBUG = false;
	
	/**
	 * Initializing the optimization process.
	 * The first label of the set pushes all of its optimizable tags to the ref list because
	 * normally every label has some reffable tag that can come from the first Label. 
	 * @param labels
	 */
	public static void init(ArrayList<Label> labels) {
		if (DEBUG)
			System.out.println("~~~OPTIMIZING~~~");
		
		for (Label l : labels) {
			//Push initials for the first <ref>
			if (l.getId() == 1) {	
				addRef(l, l.getOptimizableTags().get("str").toString(), "str");
				pushInitialRefs(l);
			} else {
				addRef(l, l.getOptimizableTags().get("str").toString(), "str");
				checkLabelForReffedStrings(l);
			}
				
		}
		
		placeRefTags();
					
		
	}
	/**
	 * Generates a HashMap of information regarding how we want to reposition each label.
	 * @param userInput
	 * @returns a HashMap containing each label id as the key and the new x/y coordinates as the value
	 */
	public static HashMap<String, Double[]> reposition(String userInput) {
		String[] inputAsList = userInput.split("\\r?\\n"); 
		
		HashMap<String, Double[]> newCoords = new HashMap<String, Double[]>(); //<labelName, {new xPos, new yPos}>
		for (int i = 0; i < inputAsList.length; i++) {
			if (i % 2 == 0) { //Even line numbers hold the coordinates (starting at index = 0)
				
				//If the label doesn't have coordinates
				if (!inputAsList[i].contains(";")) 
					continue;
				
				//Split the input into an array of 4 elements, {xPos, yPos, xSize, ySize}
				String[] s = inputAsList[i].split("\\;");
				Double[] coords = new Double[2];
				coords[0] = Double.parseDouble(s[0]); //xPos
				coords[1] = Double.parseDouble(s[1]); //yPos
				newCoords.put(inputAsList[i+1], coords); //Store it into my HashMap
			}
		}
		if (DEBUG) {
			System.out.println("--REPOSITIONING--");
			for (String s : newCoords.keySet()) 
				System.out.println("Moving "+s + " to x:" + newCoords.get(s)[0]+ " y:"+newCoords.get(s)[1]);	
		}
			
		
		return newCoords;
		
	}
	
	/**
	 * If the user wants to go back and enter new data, resets all the baseRef counters and data structures
	 * so that everything starts back at the beginning without needing to terminate and re-launch the 
	 * program. 
	 */
	public static void cleanup() {
		if (DEBUG)
			System.out.println("Cleanup");
		baseRefs.clear();
		refIdCounter = 1;
	}	
	
	/**
	 * Pushes the important reffable tags from the first label. <str> is not pushed
	 * here because <str> will likely not be reusable outside of the Labels themselves.
	 * We handle the <str> refs within each label instead. 
	 * @param label
	 */
	public static void pushInitialRefs(Label label){
		HashMap<String, StringBuffer> optTags = label.getOptimizableTags();
		
		addRef(label, optTags.get("p2df").toString(), "p2df");
		addRef(label, optTags.get("r2dd").toString(), "r2dd");
		addRef(label, optTags.get("conveyor").toString(), "conveyor");
		addRef(label, optTags.get("rotation").toString(), "rotation");
			
	}
	
	
	/**
	 * Goes through the label's contents and determines if any of that label's optimizable
	 * tags are found in our baseRefs HashMap, and refs them if we find such a match. 
	 * @param l
	 */
	public static void checkLabelForReffedStrings(Label l) {
		HashMap<String, StringBuffer> labelTags = l.getOptimizableTags();
		
		for (int i : baseRefs.keySet()) {
			for (String s : labelTags.keySet()) {
				Reference ref = baseRefs.get(i);
				//If the ref found in labelTags exists in our baseRefs
				if (ref.getContents().compareTo(labelTags.get(s).toString()) == 0) {
					
					if (DEBUG) {
						System.out.println("Found a match on label "+l.getId());
						System.out.println("Matching ref id is: "+i);
						System.out.println("Matching string is: "+baseRefs.get(i).getContents());
					}
					//Mark the ref as being used, and ref the tag in Label
					ref.useRef();
					l.refTag(s, i);
					
				}
			}
		}
	
	}
	
	/**
	 * Goes through all of the baseRefs and creates the id="#" part for each label
	 */
	public static void placeRefTags() {
		for (Integer i : baseRefs.keySet()) {
			Reference ref = baseRefs.get(i);
			if (ref.isUsed()) {	
				Label l = ref.getLabel();
				l.idTag(ref.getTitle(), i);
			}
		}
	}
	
	/**
	 * Creates a reference. We keep track of which Label the reference originates from so that we can
	 * place the id tag within that label. 
	 * @param l
	 * @param s
	 * @param title
	 */
	public static void addRef(Label l, String s, String title) {
		if (DEBUG)
			System.out.println("ADDING REF: "+refIdCounter+" : "+s);
		
		Reference newRef = new Reference(l, title, s, refIdCounter);
		baseRefs.put(refIdCounter, newRef);
		refIdCounter++;
		
	}
	
	


	
	
	
	
	
}
