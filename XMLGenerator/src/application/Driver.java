package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * Main guts for the program. 
 * Controls the generation of XML based on the input passed in from GUI.java.
 * Controls <ref> optimization as well as coordinate repositioning.
 * @author David Martin
 */
public class Driver {
	
	//StringBuffers to compile the final output displayed in the GUI
	public StringBuffer header = new StringBuffer();
	public StringBuffer content = new StringBuffer();
	public StringBuffer finalOutput = new StringBuffer();
	
	//Holds each label
	public ArrayList<Label> labels = new ArrayList<Label>();
	
	//Holds the new coordinates for each label, <LabelName, {xPos, yPos}>
	private HashMap<String, Double[]> positions = new HashMap<String, Double[]>();
	
	//Storing the decision to use new coordinates or not
	private Boolean coords = false;

	private final boolean OPTIMIZE = true;
		
	/**
	 * Controls the generation of XML based on the user input passed in from the GUI
	 * @param userInput
	 * @param doCoords
	 */
	public void doWork(String userInput, Boolean doCoords) {
		Header.genHeader(finalOutput); 
		
		//Remove any accidental newlines in the user input
		String splitInput = splitInput(userInput);
		
		coords = doCoords;
		
		//Make each label and store it into the labels ArrayList
		generateLabels(splitInput);
		
		//Put each label's contents into the final output
		for (Label l : labels)
			content.append(l.toString());
		
		//If repositioning
		if (doCoords) {
			//Get a HashMap of the new positions for each label
			positions = Optimize.reposition(userInput);
			for (Label l : labels) {
				for (String s : positions.keySet()) {
					
					//Find the label whose coordinates we want to change
					if (l.getTitle().compareTo(s) == 0){
						l.changeCoords(positions.get(s)[0], positions.get(s)[1]);				
					}
					
				}
			}
			
			//Reset content and re-make it 
			content = new StringBuffer();
			for (Label l : labels) {
				content.append(l.toString());
			}
				
		}
		
		//Optimize
		if (OPTIMIZE)
			Optimize.init(labels); 
		
		//Print the content and the footer
		finalOutput.append(content.toString());
		finalOutput.append("</objects>");
		printResults();
	}
	
	/**
	 * Takes the user input and returns a String containing only the label
	 * names separated by newlines. 
	 * @param input
	 * @returns String consisting of only the label names
	 */
	private String splitInput(String input) {
		String in = "";
		String[] asArray = input.split("\\r?\\n");
		
		for (int i = 0; i < asArray.length; i++) {
			
			//Label names cannot contain semicolons. This is convention. 
			if (!asArray[i].contains(";")) { 
				in += asArray[i] + "\n";
			}
		}
			
		
		return in;
	}
	
	/**
	 * Calls to display the results GUI
	 */
	public void printResults() {
		
		Results.showResults(finalOutput);
	}
	
	/**
	 * Creates a new label object and adds it to the labels list
	 * @param input
	 */
	private void generateLabels(String input){
		//Get just the names of the labels
		List<String> strippedInput = stripInput(input);
		int num = 0;
		header.append("\t<arraylist len=\"0\"/>\n");
		
		//For each label..
		for (String s : strippedInput) {
			
			//Ignore coordinate lines if user did not choose to use them
			if (!coords) {
				if (s.contains(";"))
					continue;
			}
			
			num++;
			if (Optimize.DEBUG)
				System.out.println("doing "+s);
			
			//Create a new Label and add it to the label list
			Label newLabel = new Label(s, num);
			labels.add(newLabel);
		}
				
	}	
	
	/**
	 * Converts the user input into a LinkedList of just the label names
	 * Used by generateLabels()
	 * @param in
	 * @returns 
	 */
	public static List<String> stripInput(String in) {
		String[] input = in.split("\\r?\\n");
		List<String> inputList = new LinkedList<String>();

		for (int i = 0; i < input.length; i++) {
			if (!input[i].equals("")) {
				inputList.add(input[i]);
			}
		}
		
		return inputList;
	}
}
