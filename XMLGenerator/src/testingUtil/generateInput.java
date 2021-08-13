package testingUtil;

import java.util.concurrent.ThreadLocalRandom;

public class generateInput {
	
	public static void main(String args[]) {
	
		for (int i = 0; i < 1500; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(ThreadLocalRandom.current().nextInt(0, 4));
			}
			System.out.println();		
		}
		
		
	}
}
