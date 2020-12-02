package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Scanner;

public class MarsHorizonConsole {

	private static final String INVALID_FORMAT = "Invalid Format! Please try again!";
	private static MarsHorizon mh;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		mh = new MarsHorizon();
		boolean canContinue = true;
		int choice = -1;
		System.out.println("Welcome to the Mars Horizon mini game solver!!\n\n");
		while (canContinue) {
			printOptions();
			if (in.hasNextInt()) {
				choice = in.nextInt();
				switch (choice) {
				case 0:
					System.out.println("0 pressed, now exiting");
					// mh.calculateMoves();
					canContinue = false;
					break;
				case 1:
					System.out.println("1 (add outcome weights) pressed");
					setConversionWeights(in);
					break;
				case 2:
					System.out.println("2 (add end states) pressed");
					addEndStates(in);
					break;
				case 3:
					System.out.println("3 (add new conversion) pressed");
					addConversion(in);
					break;
				case 4:
					System.out.println("4 (add turn numbers) pressed");
					setTurns(in);
					break;
				case 5:
					System.out.println("5 (add initial state) pressed");
					addInitialStates(in);
					break;
				case 6:
					System.out.println("6 (add end of turn actions) pressed");
					addEndOfTurnActions(in);
					break;
				case 7:
					System.out.println("7 (add attributes) pressed");
					addAttributes(in);
					break;
				case 8:
					System.out.println("8 (print current state) pressed");
					printMh();
					break;
				default:
					System.out.println("Unexpected input. Please choose from the list of options!\n");
					printOptions();
				}
			}
		}
		in.close();
		System.out.println("\nProgram finished!");
	}

	private static void addConversion(Scanner in) {
		if (!mh.hasWeights()) {
			setConversionWeights(in);
		}
		System.out.println("Add a new conversion. At any time, type 'q' to quit, or 'c' to change weights.");
		HashMap<String, Integer> inputsMap = getAttributes("Add the INPUTS (in the form '1 Comms, 2 Data'): ", in,
				true);
		HashMap<String, Integer> successMap = getAttributes(
				"Add the SUCCESSFUL OUTPUTS (in the form '1 Comms, 2 Data'): ", in, true);

		mh.addConversion(inputsMap, successMap);
		return;
	}
	
	private static void addInitialStates(Scanner in) {
		System.out.println("Add the initial state for the game. At any time, type 'q' to quit.");
		HashMap<String, Integer> initialState = getAttributes("Add attributes at the start of the mini-game: ", in, false);
		mh.setInitialState(initialState);
	}

	private static void addEndStates(Scanner in) {
		System.out.println("Add the required end states for the game. At any time, type 'q' to quit.");
		HashMap<String, Integer> successMap = getAttributes("Add the requirements for SUCCESS: ", in, false);
		HashMap<String, Integer> bonusMap = getAttributes("Add the requirements for BONUS: ", in, false);
		mh.addEndings(successMap, bonusMap);
	}

	private static HashMap<String, Integer> getAttributes(String message, Scanner in, boolean changeWeightOption) {
		System.out.print(message);
		String input = in.next().strip();
		if (input == "q") {
			return null;
		}
		if (input == "c" && changeWeightOption) {
			setConversionWeights(in);
			return null;
		}
		try {
			HashMap<String, Integer> attributes_map = getAttributesMap(input);
			System.out.println();
			return attributes_map;
		} catch (NumberFormatException ex) {
			System.out.println(INVALID_FORMAT);
			return null;
		}
	}

	private static HashMap<String, Integer> getAttributesMap(String consoleInput) throws NumberFormatException {
		String[] attributes_inputs = consoleInput.split(",");
		HashMap<String, Integer> attributes_map = new HashMap<String, Integer>();
		for (String attribute_input : attributes_inputs) {
			attribute_input = attribute_input.strip();
			String[] parts = attribute_input.split(" ");
			if (parts.length != 2) {
				throw new NumberFormatException();
			}
			attributes_map.put(parts[1].strip(), Integer.parseInt(parts[0].strip()));
		}
		return attributes_map;
	}
	
	private static void addAttributes(Scanner in) {
		System.out.println("Please enter a comma separated list of all attributes in this mini-game: ");
		Scanner in2 = new Scanner(System.in).useDelimiter("\r\n");
		String attributesFull = in2.next();
		in2.close();
		String[] attributes = attributesFull.split(",");
		String[] attributesClean = new String[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			attributesClean[i] = attributes[i].strip();
		}
		mh.setAttributes(attributesClean);
	}

	private static void setConversionWeights(Scanner in) {
		System.out.println("We need to set the weights for a successful, failed and bonus turn.");
		System.out.print("Please enter the FAIL chance percentage: ");
		int fail = in.nextInt();
		System.out.print("Please enter the BONUS chance percentage: ");
		int bonus = in.nextInt();
		int success = 100 - fail - bonus;
		mh.setWeights(success, fail, bonus);
	}
	
	private static void setTurns(Scanner in) {
		int numTurns, numTasks, tasksFirstTurn;
		System.out.println("This section sets the number of turns available and the number of tasks per turn. Press 'q' at any time to quit this section.");
		System.out.print("Please enter the number of turns: ");
		String input = in.next().strip();
		if (input == "q") {
			return;
		}
		try {
			numTurns = Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			System.out.println(INVALID_FORMAT);
			return;
		}
		System.out.print("Please enter the number of tasks per turn: ");
		input = in.next().strip();
		if (input == "q") {
			return;
		}
		try {
			numTasks = Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			System.out.println(INVALID_FORMAT);
			return;
		}
		System.out.print("Please enter the number of tasks in the FIRST turn (or 0 if the same as every other turn): ");
		input = in.next().strip();
		if (input == "q") {
			return;
		}
		try {
			tasksFirstTurn = Integer.parseInt(input);
			if (tasksFirstTurn == 0) {
				tasksFirstTurn = numTasks;
			}
		} catch (NumberFormatException ex) {
			System.out.println(INVALID_FORMAT);
			return;
		}
		mh.addTurns(numTurns, numTasks, tasksFirstTurn);
	}
	
	private static void addEndOfTurnActions(Scanner in) {
		System.out.println("This section adds any actions that occur at the end of each turn. Press 'q' to quit at any time.");
		boolean canContinue = true;
		while(canContinue) {
			printEndOfTurnActions();
			String input = in.next().strip();
			switch (input) {
			case "q":
				canContinue = false;
				break;
			case "1":
				canContinue = addHeat(in);
				break;
			case "2":
				canContinue = addThrust(in);
				break;
			case "3":
				canContinue = addDrift(in);
				break;
			default:
				System.out.println(INVALID_FORMAT);
				break;
			}
		}
	}
	
	private static boolean addHeat(Scanner in) {
		System.out.print("Does the heat limit have a M(A)X, M(I)N or (B)OTH? ");
		String heatChange;
		String input = in.next().strip();
		switch (input) {
		case "q":
		case "Q":
			return false;
		case "a":
		case "A":
			System.out.print("Please supply the values in the form 'max, change (min, change max): ");
			heatChange = in.next().strip();
			mh.addHeatEndOfTurnAction(EndOfTurnAction.Type.MAX_ONLY, heatChange);
			break;
		case "i":
		case "I":
			System.out.print("Please supply the values in the form 'max, change (min, change max): ");
			heatChange = in.next().strip();
			mh.addHeatEndOfTurnAction(EndOfTurnAction.Type.MIN_ONLY, heatChange);
			break;
		case "b":
		case "B":
			System.out.print("Please supply the values in the form 'max, change (min, change max): ");
			heatChange = in.next().strip();
			mh.addHeatEndOfTurnAction(EndOfTurnAction.Type.MIN_AND_MAX, heatChange);
			break;
		default:
			System.out.println(INVALID_FORMAT);
			break;
		}
		return true;
	}
	
	private static boolean addThrust(Scanner in) {
		System.out.print("Is the thrust change a RETURN TO VALUE one Y/N? ");
		String thrustChange;
		String input = in.next().strip();
		switch(input) {
		case "q":
		case "Q":
			return false;
		case "y":
		case "Y":
			System.out.print("Please enter the value to return to: ");
			thrustChange = in.next().strip();
			mh.addThrustEndOfTurnAction(EndOfTurnAction.Type.RETURN_TO_VALUE, thrustChange);
			break;
		case "n":
		case "N":
			System.out.print("Please enter the change value: ");
			thrustChange = in.next().strip();
			mh.addThrustEndOfTurnAction(EndOfTurnAction.Type.CHANGE, thrustChange);
			break;
		default:
			System.out.println(INVALID_FORMAT);
			break;
		}
		return true;
	}
	
	private static boolean addDrift(Scanner in) {
		System.out.print("Is the drift change a RETURN TO VALUE one Y/N? ");
		String driftChange;
		String input = in.next().strip();
		switch(input) {
		case "q":
		case "Q":
			return false;
		case "y":
		case "Y":
			System.out.print("Please enter the value to return to: ");
			driftChange = in.next().strip();
			mh.addDriftEndOfTurnAction(EndOfTurnAction.Type.RETURN_TO_VALUE, driftChange);
			break;
		case "n":
		case "N":
			System.out.print("Please enter the change value: ");
			driftChange = in.next().strip();
			mh.addDriftEndOfTurnAction(EndOfTurnAction.Type.CHANGE, driftChange);
			break;
		default:
			System.out.println(INVALID_FORMAT);
			break;
		}
		return true;
	}

	private static void printEndOfTurnActions() {
		System.out.println("Please choose from the following options of which end of turn action needs to be added:");
		System.out.println("1. Add heat");
		System.out.println("2. Add thrust");
		System.out.println("3. Add drift");
	}
	
	private static void printMh() {
		System.out.println(mh.print());
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
		System.out.println("8. Print current state");
		System.out.println("0. Start Calculating moves...");
	}

}
