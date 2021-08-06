package application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The first element is never going to be reffed. Lets take the contents of it and store it in a hash map 
 * Never ref lc, each tag is guaranteed one ref id because <str> tag is printed twice.
 * 
 * @author David Martin
 *
 */
public class Optimize {
	private static HashMap<Integer, Reference> baseRefs = new HashMap<Integer, Reference>();
	private static int refIdCounter = 0;
	public final static boolean DEBUG = true;
	
	
	public static void init(ArrayList<Label> labels) {
		if (DEBUG)
			System.out.println("~~~OPTIMIZING~~~");
		
		
		
		for (Label l : labels) {
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
	
	public static void cleanup() {
		if (DEBUG)
			System.out.println("Cleanup");
		baseRefs.clear();
		refIdCounter = 0;
	}

	
	public static void placeRefTags() {
		for (Integer i : baseRefs.keySet()) {
			Reference ref = baseRefs.get(i);
			if (ref.isUsed()) {
				
				Label l = ref.getLabel();
				
				//System.out.println(ref.getTitle() + " : "+ref.getContents());
				l.idTag(ref.getTitle(), i);
			}
		}
	}
	
		
	public static void pushInitialRefs(Label label){
		HashMap<String, StringBuffer> optTags = label.getOptimizableTags();
		
		addRef(label, optTags.get("p2df").toString(), "p2df");
		addRef(label, optTags.get("r2dd").toString(), "r2dd");
			
	}
	
	
	
	public static void checkLabelForReffedStrings(Label l) {
		HashMap<String, StringBuffer> labelTags = l.getOptimizableTags();
		
		for (int i : baseRefs.keySet()) {
			for (String s : labelTags.keySet()) {
				Reference ref = baseRefs.get(i);			
				if (ref.getContents().compareTo(labelTags.get(s).toString()) == 0) {
								
					/**
					 * We found a tag that wants to get reffed. Only problem is that we now need to know
					 * what label is getting the id for our new ref. So each ref needs to somehow be associated
					 * to a label. I need to go back and ID the label that the ref is coming from. 
					 */
					
					
					if (DEBUG) {
						System.out.println("Found a match on label "+l.getId());
						System.out.println("Matching ref id is: "+i);
						System.out.println("Matching string is: "+baseRefs.get(i).getContents());
					}
					ref.useRef();
					l.refTag(s, i);
					
				}
			}
		}
	
	}
	
	public static void addRef(Label l, String s, String title) {
		//baseRefs.put(refIdCounter, s);
		if (DEBUG)
			System.out.println("ADDING REF: "+refIdCounter+" : "+s);
		
		Reference newRef = new Reference(l, title, s, refIdCounter);
		baseRefs.put(refIdCounter, newRef);
		refIdCounter++;
		
		if (title.compareTo("str") == 0) {
			l.doStrSwap(newRef.getRefId());
		}
		
	}
	
	
	
	
	
}