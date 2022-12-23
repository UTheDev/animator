package com.uthedev.animator.easing;

/**
 * This is the base class for easing styles.
 * 
 * @author UTheDev
 *
 */
public class BaseEasingStyle {
	protected double start;
	protected double end;
	protected double time;
	
	public BaseEasingStyle(double newStart, double newEnd, double newTime) {
		start = newStart;
		end = newEnd;
		time = newTime;
	}
	
	/**
	 * Returns a calculated value based on the starting value, ending value, and current lerp time
	 * @param d The EasingDirection to use in the calculation
	 * @return The calculated value
	 */
	public double calculate(Direction d) {
		switch(d) {
		case IN:
			return calculateIn();
		case OUT:
			return calculateOut();
		case IN_OUT:
			return calculateInOut();
		}
		
		return 0;
	}
	
	public double calculateIn() {
		return 0;
	}
	public double calculateOut() {
		return 0;
	}
	public double calculateInOut() {
		return 0;
	}
}
