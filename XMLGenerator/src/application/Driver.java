package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * Keep java version on runnable export to 1.8
 * Limit in launch 4j to 1.8.0 -> 1.9.0
 * @author David Martin
 *
 */
public class Driver {
	
	public StringBuffer header = new StringBuffer();
	public StringBuffer content = new StringBuffer();
	public StringBuffer finalOutput = new StringBuffer();
	public ArrayList<Label> labels = new ArrayList<Label>();
	private HashMap<String, Double[]> positions = new HashMap<String, Double[]>();
	
	
	private final boolean OPTIMIZE = true;
		
	public void doWork(String userInput, Boolean doCoords) {
		Header.genHeader(finalOutput);  
		String splitInput = splitInput(userInput);
				
		generateLabels(splitInput);
						
		
		
		for (Label l : labels)  //add the labels
			content.append(l.toString());
		
		if (doCoords) {
			positions = Optimize.reposition(userInput);
			for (Label l : labels) {
				for (String s : positions.keySet()) {
					if (l.getTitle().compareTo(s) == 0){
						System.out.println("found a match");
						l.changeCoords(positions.get(s)[0], positions.get(s)[1]);
						System.out.println(l.getX() + " : " + l.getY());						
					}
				}
			}
			
			content = new StringBuffer();
			for (Label l : labels) {
				content.append(l.toString());
			}
				
		}
		
		if (OPTIMIZE)
			Optimize.init(labels); //optimize the labels
		
				
		finalOutput.append(content.toString());
		finalOutput.append("</objects>");
		
		//reposition
		
		printResults();
	}
	
	private String splitInput(String input) {
		String in = "";
		String[] asArray = input.split("\\r?\\n");
		
		for (int i = 0; i < asArray.length; i++) {
			if (!asArray[i].contains(";")) { 
				in += asArray[i] + "\n";
			}
		}
			
		
		return in;
	}
	
	public void printResults() {
		
		Results.showResults(finalOutput);
	}
	
	
	private void generateLabels(String input){
		List<String> strippedInput = stripInput(input);
		int num = 0;
		header.append("\t<arraylist len=\"0\"/>\n");
		for (String s : strippedInput) {
			num++;
			if (Optimize.DEBUG)
				System.out.println("doing "+s);
			Label newLabel = new Label(s, num);
			labels.add(newLabel);
			//content.append(newLabel.toString());
		}
				
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
