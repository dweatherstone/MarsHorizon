package uk.co.weatherstone.build;

import java.util.Scanner;

public class MarsHorizonConsole {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		MarsHorizon mh = new MarsHorizon();
		boolean canContinue = true;
		System.out.println("Welcome to the Mars Horizon mini game solver!!\n\n");
		printOptions();
		int choice = in.nextInt();
		while (canContinue) {
			switch(choice) {
				case 0:
					//mh.calculateMoves();
					System.out.println("0 pressed, now exiting");
					canContinue = false;
					break;
				case 1:
					System.out.println("1 pressed");
					break;
				case 2:
					System.out.println("2 pressed");
					break;
				case 3:
					System.out.println("3 pressed");
					break;
				case 4:
					System.out.println("4 pressed");
					break;
				case 5:
					System.out.println("5 pressed");
					break;
				case 6:
					System.out.println("6 pressed");
					break;
				case 7:
					System.out.println("7 pressed");
					break;
					
				default:
					System.out.println("Unexpected input. Please choose from the list of options!\n");
					printOptions();
			}
			if (canContinue) {
				choice = in.nextInt();
			}
		}
		in.close();
		System.out.println("\nProgram finished!");
	}
	
	private static void printOptions() {
		System.out.println("Please choose from the following options:");
		System.out.println("1. Add outcome weights");
		System.out.println("2. Add end states");
		System.out.println("3. Add a new conversion");
		System.out.println("4. Add turn numbers");
		System.out.println("5. Add initial state");
		System.out.println("6. Add end of turn actions");
		System.out.println("7. Add attributes");
		System.out.println("0. Start Calculating moves...");
	}

}
