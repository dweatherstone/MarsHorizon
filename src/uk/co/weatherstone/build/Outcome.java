package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Map;

public class Outcome {
	private Map<String, Integer> outcome;
	
	public Outcome() {
		outcome = new HashMap<String, Integer>();
	}
	
	public Outcome(String key, int value) {
		outcome = new HashMap<String, Integer>();
		outcome.put(key, value);
	}
	
	public Outcome(Map<String, Integer> outcome) {
		this.outcome = outcome;
	}
	
	public void addOutcome(String attribute, int value) {
		outcome.put(attribute, value);
	}
	
	public void addAllOutcome(Map<String, Integer> outcome) {
		this.outcome = outcome;
	}
	public Map<String, Integer> getOutcome() {
		return outcome;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (Map.Entry<String, Integer> entry : outcome.entrySet()) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(entry.getValue().toString() + " " + entry.getKey());
		}
		return sb.toString();
	}

}
