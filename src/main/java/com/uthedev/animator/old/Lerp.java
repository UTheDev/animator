package com.uthedev.animator.old;

/**
 * This is the base class for numeric interpolation based on easing styles such
 * as linear and square.
 * 
 * @author UTheDev
 *
 */
public class Lerp {
	/**
	 * Performs linear interpolation between 2 numbers.
	 * This method is intended to be built upon by subclasses of Lerp
	 * as many easing style algorithms are based on linear interpolation.
	 * 
	 * @param start The starting value
	 * @param end The ending value
	 * @param time The interpolation time
	 * @return The calculated result based on the interpolation parameters
	 */
	public static double lerpLinear(double start, double end, double time) {
		return start + (end - start) * time;
	}
	
	/**
	 * The starting value of the interpolation
	 */
	protected double start;
	
	/**
	 * The ending value of the interpolation
	 */
	protected double end;
	
	/**
	 * The interpolation time
	 */
	protected double time;

	/**
	 * Creates an instance of Lerp where the alpha start value, the alpha end value,
	 * and the interpolation time are all 0
	 */
	public Lerp() {
		this(0, 0, 0);
	}

	/**
	 * Creates a new object that can be used for interpolation
	 * 
	 * @param newStart The initial alpha starting time
	 * @param newEnd   The initial alpha ending time
	 * @param newTime  The initial interpolation time
	 */
	public Lerp(double newStart, double newEnd, double newTime) {
		start = newStart;
		end = newEnd;
		time = newTime;
	}

	/**
	 * Returns a calculated value based on the starting value, ending value, and
	 * current lerp time
	 * 
	 * @param d The EasingDirection to use in the calculation
	 * @return The calculated value
	 */
	public double calculate(EasingDirection d) {
		switch (d) {
		case IN:
			return calculateIn();
		case OUT:
			return calculateOut();
		case IN_OUT:
			return calculateInOut();
		}

		return 0;
	}

	/**
	 * 
	 * @return The current interpolation time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Sets the interpolation time
	 * 
	 * @param newTime The new interpolation time
	 */
	public void setTime(double newTime) {
		time = newTime;
	}
	
	
	/**
	 * 
	 * @return The starting value of the interpolation
	 */
	public double getStart() {
		return start;
	}
	
	/**
	 * Sets the starting value of the interpolation
	 * 
	 * @param newStart The new starting value
	 */
	public void setStart(double newStart) {
		start = newStart;
	}
	
	/**
	 * 
	 * @return The ending value of the interpolation
	 */
	public double getEnd() {
		return end;
	}
	
	/**
	 * Sets the ending value of the interpolation
	 * @param newEnd The new ending value
	 */
	public void setEnd(double newEnd) {
		end = newEnd;
	}

	/**
	 * 
	 * @return The interpolated value if the animation is supposed to speed up as
	 *         the animation progresses
	 */
	public double calculateIn() {
		return 0;
	}

	/**
	 * 
	 * @return The interpolated value if the animation is supposed to slow down as
	 *         the animation progresses
	 */
	public double calculateOut() {
		return 0;
	}

	/**
	 * 
	 * @return The interpolated value if the animation is supposed to speed up as the
	 *         animation goes from the beginning to the halfway point, then slow
	 *         down as it goes from the halfway point to the end
	 */
	public double calculateInOut() {
		return 0;
	}
}
