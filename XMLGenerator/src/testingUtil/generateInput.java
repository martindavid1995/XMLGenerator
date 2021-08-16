package testingUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Used for testing purposes. Generates input for the program. Also creates input with or 
 * without duplicates to test the warning functionality. 
 * @author David Martin
 *
 */
public class generateInput {
	private final static boolean DUPES = false;
	private final static boolean COORDS = true;
	private final static int ENTRIES = 1500;
	
	/**
	 * Generates a list of random input to test the program.
	 * @param args
	 */
	public static void main(String args[]) {
		ArrayList<String> dupeCheck = new ArrayList<String>();
		for (int i = 0; i < ENTRIES; i++) {
			if (DUPES)
				System.out.println(generate(COORDS));
			else {
				String generated = generate(COORDS);
				String[] split = generated.split("\\n");
				if (!dupeCheck.contains(split[1])) {
					System.out.println(generated);
					dupeCheck.add(split[1]);
				}
			}
				
		}
		
		
	}
	
	/**
	 * Generates a random label name. I'm just using numbers instead of strings
	 * for the label names because it doesn't matter what the label names look like. 
	 * @param coords
	 * @returns A randomly generated string of input representing one Label
	 */
	private static String generate(Boolean coords) {
		StringBuffer result = new StringBuffer();
		
		if (COORDS)
			generateCoords(result);
		
		result.append("ESRC_");
		result.append(ThreadLocalRandom.current().nextInt(100,999));
		result.append("_"+ThreadLocalRandom.current().nextInt(10, 99));
		
				
		return result.toString();
	}
	
	/**
	 * Inserts some "random" doubles within specified ranges for the coordinate values
	 * @param result
	 */
	private static void generateCoords(StringBuffer result) {
		DecimalFormat dFormat = new DecimalFormat("#.###");
		double a,b,c,d;
		a = ThreadLocalRandom.current().nextDouble(0,1000);
		b = ThreadLocalRandom.current().nextDouble(0,200);
		c = ThreadLocalRandom.current().nextDouble(0,50);
		d = ThreadLocalRandom.current().nextDouble(0,60);
		result.append(dFormat.format(a) + ";" + dFormat.format(b) + ";" + dFormat.format(c) + ";" + dFormat.format(d) + "\n");
	}
}
