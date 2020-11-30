package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Map;

public class Conversion {
	private Map<String, Integer> input;
	private Outcome success;
	private Outcome failure;
	private Outcome bonus;
	private Map<String, Integer> resist;
	
	public Conversion() {
		input = new HashMap<String, Integer>();
		success = new Outcome();
		failure = new Outcome();
		bonus = new Outcome();
		resist = new HashMap<String, Integer>();
	}
	
	public void addConversion(Map<String, Integer> input, Map<String, Integer> success, float success_weight) {
		this.input = input;
		this.success.addAllOutcome(success);
		this.success.setWeight(success_weight);
	}
	
	public void addInput(String key, int value) {
		input.put(key, value);
	}
	
	public void addSuccess(String key, int value) {
		success.addOutcome(key, value);
	}
	
	public void addFailure(String key, int value) {
		failure.addOutcome(key, value);
	}
	
	public void addBonus(String key, int value) {
		bonus.addOutcome(key, value);
	}
	
	public void addResist(String key, int value) {
		success.addOutcome(key, value);
	}
	
	public void reset() {
		input = new HashMap<String, Integer>();
		success = new Outcome();
		failure = new Outcome();
		bonus = new Outcome();
		resist = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getInput() {
		return input;
	}

	public Outcome getSuccess() {
		return success;
	}

	public Outcome getFailure() {
		return failure;
	}

	public Outcome getBonus() {
		return bonus;
	}

	public Map<String, Integer> getResist() {
		return resist;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (Map.Entry<String, Integer> entry : input.entrySet()) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(entry.getValue().toString() + " " + entry.getKey());
		}
		prefix = "";
		sb.append(" => ");
		sb.append(success.toString());
		return sb.toString();
	}
	
}
