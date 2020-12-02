package uk.co.weatherstone.build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MarsHorizon {
	
	private ArrayList<ArrayList<Integer>> turns;
	private HashMap<Integer, Conversion> conversions;
	private HashMap<String, Outcome> endings;
	// private List<String> attributes;
	private EndOfTurnAction endOfTurnAction;
	private HashMap<String, Integer> initialState;
	private HashMap<String, Integer> currentState;
	private int successWeight;
	private int failWeight;
	private int bonusWeight;
	
	public MarsHorizon() {
		turns = new ArrayList<ArrayList<Integer>>();
		conversions = new HashMap<Integer, Conversion>();
		endings = new HashMap<String, Outcome>();
		// attributes = new ArrayList<String>();
		endOfTurnAction = new EndOfTurnAction();
		initialState = new HashMap<String, Integer>();
		currentState = new HashMap<String, Integer>();
		successWeight = -1;
		failWeight = -1;
		bonusWeight = -1;
	}
	
	public void addTurns(int numTurns, int numTasksPerTurn) {
		for (int turn = 0; turn < numTurns; turn++ ) {
			ArrayList<Integer> tasksThisTurn = new ArrayList<Integer>();
			for (int task = 0; task < numTasksPerTurn; task++) {
				tasksThisTurn.add(task);
			}
			turns.add(tasksThisTurn);
		}
	}
	
	public void addTurns(int numTurns, int numTasksPerTurn, int numTasksFirstTurn) {
		ArrayList<Integer> tasksThisTurn = new ArrayList<Integer>();
		for (int task = 0; task < numTasksFirstTurn; task++) {
			tasksThisTurn.add(task);
		}
		turns.add(tasksThisTurn);
		for (int turn = 1; turn < numTurns; turn++) {
			tasksThisTurn = new ArrayList<Integer>();
			for (int task = 0; task < numTasksPerTurn; task++) {
				tasksThisTurn.add(task);
			}
			turns.add(tasksThisTurn);
		}
	}
	
	public ArrayList<ArrayList<Integer>> getTurns() {
		return turns;
	}

	public boolean hasWeights() {
		if (conversions.isEmpty() || successWeight == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setWeights(int success, int fail, int bonus) {
		this.successWeight = success;
		this.failWeight = fail;
		this.bonusWeight = bonus;
	}
	
	public void addConversion(HashMap<String, Integer> inputsMap, HashMap<String, Integer> successMap) {
		int key;
		try {
			key = Collections.max(conversions.keySet());
		} catch (NoSuchElementException ex) {
			key = 0;
		}
		key++;
		conversions.put(key, new Conversion(inputsMap, successMap));
	}
	
	public void addEndings(HashMap<String, Integer> successMap, HashMap<String, Integer> bonusMap) {
		endings.put("Success", new Outcome(successMap));
		endings.put("Bonus", new Outcome(bonusMap));
	}
	
	public void setInitialState(HashMap<String, Integer> initialState) {
		this.initialState.putAll(initialState);
		this.currentState = deepCopy(this.initialState);
	}
	
	public void setAttributes(String[] attributes) {
		for (String attribute : attributes) {
			if (!initialState.containsKey(attribute)) {
				initialState.put(attribute, 0);
			}
		}
		currentState = deepCopy(initialState);
	}
	
	private HashMap<String, Integer> deepCopy(HashMap<String, Integer> original) {
		HashMap<String, Integer> copy = new HashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : original.entrySet()) {
			copy.put(entry.getKey(), entry.getValue());
		}
		return copy;
	}
	
	public void addHeatEndOfTurnAction(EndOfTurnAction.Type type, String attributes) {
		String[] attrs = attributes.split("\\s*,\\s*");
		int[] intAttrs = new int[attrs.length];
		try {
			for (int i = 0; i < attrs.length; i++) {
				intAttrs[i] = Integer.parseInt(attrs[i]);
			}
		} catch (NumberFormatException ex) {
			System.out.println("There's something that's not a number. Oh dear!");
			return;
		}
		switch(type) {
		case MAX_ONLY:
			if (intAttrs.length == 2) {
				endOfTurnAction.addHeatOnlyMax(intAttrs[0], intAttrs[1]);
			} else if (intAttrs.length == 3) {
				endOfTurnAction.addHeatOnlyMax(intAttrs[0], intAttrs[1], intAttrs[2]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;
		case MIN_ONLY:
			if (intAttrs.length == 2) {
				endOfTurnAction.addHeatOnlyMin(intAttrs[0], intAttrs[1]);
			} else if (intAttrs.length == 3) {
				endOfTurnAction.addHeatOnlyMin(intAttrs[0], intAttrs[1], intAttrs[2]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;
		case MIN_AND_MAX:
			if (intAttrs.length == 4) {
				endOfTurnAction.addHeat(intAttrs[0], intAttrs[1], intAttrs[2], intAttrs[3]);
			} else if (intAttrs.length == 3) {
				endOfTurnAction.addHeat(intAttrs[0], intAttrs[1], intAttrs[2]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;
		default:
			System.out.println("Wrong ENUM... Check the code!!");	
		}
	}
	
	public void addThrustEndOfTurnAction(EndOfTurnAction.Type type, String attributes) {
		String[] attrs = attributes.split("\\s*,\\s*");
		int[] intAttrs = new int[attrs.length];
		try {
			for (int i = 0; i < attrs.length; i++) {
				intAttrs[i] = Integer.parseInt(attrs[i]);
			}
		} catch (NumberFormatException ex) {
			System.out.println("There's something that's not a number. Oh dear!");
			return;
		}
		switch(type) {
		case RETURN_TO_VALUE:
			if (intAttrs.length == 1) {
				endOfTurnAction.addThrustReturnToValue(intAttrs[0]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;
		case CHANGE:
			if (intAttrs.length == 1) {
				endOfTurnAction.addThrustChange(intAttrs[0]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;	
		default:
			System.out.println("Wrong ENUM... Check the code!!");
		}
	}

	public void addDriftEndOfTurnAction(EndOfTurnAction.Type type, String attributes) {
		String[] attrs = attributes.split("\\s*,\\s*");
		int[] intAttrs = new int[attrs.length];
		try {
			for (int i = 0; i < attrs.length; i++) {
				intAttrs[i] = Integer.parseInt(attrs[i]);
			}
		} catch (NumberFormatException ex) {
			System.out.println("There's something that's not a number. Oh dear!");
			return;
		}
		switch(type) {
		case RETURN_TO_VALUE:
			if (intAttrs.length == 1) {
				endOfTurnAction.addDriftReturnToValue(intAttrs[0]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;
		case CHANGE:
			if (intAttrs.length == 1) {
				endOfTurnAction.addDriftChange(intAttrs[0]);
			} else {
				System.out.println("Wrong number of attributes provided. Oh dear!");
				return;
			}
			break;	
		default:
			System.out.println("Wrong ENUM... Check the code!!");
		}
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder("TURNS:\n");
		for (int i = 0; i < turns.size(); i++) {
			sb.append(String.format("Turn {} > {} commands\n", i+1, turns.get(i).size()));
		}
		sb.append("\nCURRENT:\n");
		for (Map.Entry<String, Integer> entry : currentState.entrySet()) {
			sb.append(entry.getKey());
			sb.append(":\t");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		sb.append("\nREQUIRED:\n");
		sb.append(endings.get("Bonus"));
		return sb.toString();
	}
}
