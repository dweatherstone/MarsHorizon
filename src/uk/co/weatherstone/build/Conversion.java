package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Map;

public class Conversion {
	private Map<String, Integer> input;
	private Map<String, Integer> success;
	private Map<String, Integer> failure;
	private Map<String, Integer> bonus;
	private Map<String, Integer> resist;
	
	public Conversion() {
		input = new HashMap<String, Integer>();
		success = new HashMap<String, Integer>();
		failure = new HashMap<String, Integer>();
		bonus = new HashMap<String, Integer>();
		resist = new HashMap<String, Integer>();
	}
	
	public void addConversion(Map<String, Integer> input, Map<String, Integer> success, Map<String, Integer> failure, Map<String, Integer> bonus, Map<String, Integer> resist) {
		this.input = input;
		this.success = success;
		this.failure = failure;
		this.bonus = bonus;
		this.resist = resist;
	}
	
	public void addConversion(Map<String, Integer> input, Map<String, Integer> success) {
		this.input = input;
		this.success = success;
	}
	
	public void addInput(String key, int value) {
		input.put(key, value);
	}
	
	public void addSuccess(String key, int value) {
		success.put(key, value);
	}
	
	public void addFailure(String key, int value) {
		failure.put(key, value);
	}
	
	public void addBonus(String key, int value) {
		bonus.put(key, value);
	}
	
	public void addResist(String key, int value) {
		success.put(key, value);
	}
	
	public void reset() {
		input = new HashMap<String, Integer>();
		success = new HashMap<String, Integer>();
		failure = new HashMap<String, Integer>();
		bonus = new HashMap<String, Integer>();
		resist = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getInput() {
		return input;
	}

	public Map<String, Integer> getSuccess() {
		return success;
	}

	public Map<String, Integer> getFailure() {
		return failure;
	}

	public Map<String, Integer> getBonus() {
		return bonus;
	}

	public Map<String, Integer> getResist() {
		return resist;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("INPUTS: ");
		String prefix = "";
		for (Map.Entry<String, Integer> entry : input.entrySet()) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(entry.getValue().toString() + " " + entry.getKey());
		}
		prefix = "";
		sb.append(" => ");
		for (Map.Entry<String, Integer> entry : success.entrySet()) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(entry.getValue().toString() + " " + entry.getKey());
		}
		return sb.toString();
	}
	
}
