package garbage;

import java.util.Random;

public class makeupshit {
	public static void main(String args[]) {
		
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print((char)randInt(97,122));
			}
			System.out.println();
		}
			
		
	}
	
	public static int randInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
