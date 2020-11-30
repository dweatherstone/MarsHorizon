package uk.co.weatherstone.build;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarsHorizon {
	
	private List<List<Integer>> turns;
	private Map<Integer, Conversion> conversions;
	private Map<String, Outcome> endings;
	private List<String> attributes;
	private EndOfTurnAction endOfTurnAction;
	private Map<String, Integer> initialState;
	private Map<String, Integer> currentState;

}
