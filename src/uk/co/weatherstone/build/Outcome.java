package uk.co.weatherstone.build;

import java.util.HashMap;
import java.util.Map;

public class Outcome {
	private float failureWeight;
	private float successWeight;
	private float bonusWeight;
	
	private Map<String, Integer> outcome;
	
	public Outcome() {
		failureWeight = 0.3f;
		successWeight = 0.5f;
		bonusWeight = 0.2f;
		outcome = new HashMap<String, Integer>();
	}
	
	public Outcome(float failure_weight, float success_weight, float bonus_weight) {
		this.failureWeight = failure_weight;
		this.successWeight = success_weight;
		this.bonusWeight = bonus_weight;
		outcome = new HashMap<String, Integer>();
	}
	
	public void addOutcome(String attribute, int value) {
		outcome.put(attribute, value);
	}
	
	public void addAllOutcomes(Map<String, Integer> outcomes) {
		this.outcome = outcomes;
	}

	public float getFailureWeight() {
		return failureWeight;
	}

	public void setFailureWeight(float failure_weight) {
		this.failureWeight = failure_weight;
	}

	public float getSuccessWeight() {
		return successWeight;
	}

	public void setSuccessWeight(float success_weight) {
		this.successWeight = success_weight;
	}

	public float getBonusWeight() {
		return bonusWeight;
	}

	public void setBonusWeight(float bonus_weight) {
		this.bonusWeight = bonus_weight;
	}

	public Map<String, Integer> getOutcome() {
		return outcome;
	}

}
