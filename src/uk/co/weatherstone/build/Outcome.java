package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Map;

public class Outcome {
	private float weight;
	
	private Map<String, Integer> outcome;
	
	public Outcome() {
		weight = 0.3f;
		outcome = new HashMap<String, Integer>();
	}
	
	public Outcome(float weight, String key, int value) {
		this.weight = weight;
		outcome = new HashMap<String, Integer>();
		outcome.put(key, value);
	}
	
	public Outcome(float weight, Map<String, Integer> outcome) {
		this.weight = weight;
		this.outcome = outcome;
	}
	
	public void addOutcome(String attribute, int value) {
		outcome.put(attribute, value);
	}
	
	public void addAllOutcome(Map<String, Integer> outcome) {
		this.outcome = outcome;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float failure_weight) {
		this.weight = failure_weight;
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
