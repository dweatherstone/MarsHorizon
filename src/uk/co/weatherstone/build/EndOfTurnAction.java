package uk.co.weatherstone.build;

import java.util.Random;

public class EndOfTurnAction {

	private int heatMin;
	private int heatMax;
	private int heatChangeMin;
	private int heatChangeMax;
	private int thrustChange;
	private int thrustChangeToValue;
	private int driftChange;
	private int driftChangeToValue;
	private boolean useRandomHeatChange;

	private static final int MIN_REFERENCE = -999999;
	private static final int MAX_REFERENCE = 999999;
	private Random random;

	public EndOfTurnAction() {
		heatMin = MIN_REFERENCE;
		heatMax = MAX_REFERENCE;
		heatChangeMin = 0;
		heatChangeMax = 0;
		thrustChange = 0;
		driftChange = 0;
		thrustChangeToValue = MIN_REFERENCE;
		driftChangeToValue = MIN_REFERENCE;
		useRandomHeatChange = false;
		random = new Random();
	}

	public void addHeat(int heatMin, int heatMax, int heatChange) {
		this.heatMin = heatMin;
		this.heatMax = heatMax;
		this.heatChangeMin = heatChange;
		this.heatChangeMax = heatChange;
	}

	public void addHeat(int heatMin, int heatMax, int heatChangeMin, int heatChangeMax) {
		this.heatMin = heatMin;
		this.heatMax = heatMax;
		this.heatChangeMin = heatChangeMin;
		this.heatChangeMax = heatChangeMax;
	}

	public void addHeatOnlyMin(int heatMin, int heatChange) {
		this.heatMin = heatMin;
		this.heatMax = MAX_REFERENCE;
		this.heatChangeMin = heatChange;
		this.heatChangeMax = heatChange;
	}

	public void addHeatOnlyMin(int heatMin, int heatChangeMin, int heatChangeMax) {
		this.heatMin = heatMin;
		this.heatMax = MAX_REFERENCE;
		this.heatChangeMin = heatChangeMin;
		this.heatChangeMax = heatChangeMax;
	}

	public void addHeatOnlyMax(int heatMax, int heatChange) {
		this.heatMin = MIN_REFERENCE;
		this.heatMax = heatMax;
		this.heatChangeMin = heatChange;
		this.heatChangeMax = heatChange;
	}

	public void addHeatOnlyMax(int heatMax, int heatChangeMin, int heatChangeMax) {
		this.heatMin = MIN_REFERENCE;
		this.heatMax = heatMax;
		this.heatChangeMin = heatChangeMin;
		this.heatChangeMax = heatChangeMax;
	}

	public void addThrustChange(int thrustChange) {
		this.thrustChange = thrustChange;
	}

	public void addThrustReturnToValue(int thrustValue) {
		this.thrustChangeToValue = thrustValue;
	}

	public void addDriftReturnToValue(int driftValue) {
		this.driftChangeToValue = driftValue;
	}

	public void addDriftChange(int driftChange) {
		this.driftChange = driftChange;
	}

	public int getHeatChange() {
		if (useRandomHeatChange) {
			return random.nextInt(heatChangeMax - heatChangeMin) + heatChangeMin;
		} else {
			return heatChangeMax;
		}
	}

	public boolean heatOK(int currentHeat) {
		return heatMin <= currentHeat && currentHeat <= heatMax;
	}

	public int getThrustChange() {
		if (thrustChangeToValue == MIN_REFERENCE) {
			return thrustChange;
		} else {
			return thrustChangeToValue;
		}
	}

	public int getDriftChange() {
		if (driftChangeToValue == MIN_REFERENCE) {
			return driftChange;
		} else {
			return driftChangeToValue;
		}
	}

	public void setRandomHeatChange(boolean useRandomHeatChange) {
		this.useRandomHeatChange = useRandomHeatChange;
	}

}
