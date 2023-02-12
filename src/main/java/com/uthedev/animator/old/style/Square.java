package com.uthedev.animator.old.style;

import com.uthedev.animator.old.Lerp;

public class Square extends Lerp {
	/**
	 * Calculates a quadratic interpolation assuming we want to speed up as time increases
	 * @param leadingCoefficient The math textbooks would abbreviate this with the letter "a"
	 * @param start The starting value of the interpolation
	 * @param time The interpolation time
	 * @return The calculated interpolation
	 */
	public static double calculateInForTime(double leadingCoefficient, double start, double time) {
		return leadingCoefficient * Math.pow(time, 2) + start;
	}
	
	private double leadingCoefficient = 0;

	public Square(double start, double end, double time) {
		super(start, end, time);
		calculateLeadingCoefficient();
	}

	@Override
	public void setTime(double newTime) {
		// needed because the domain of the interpolation function has to be restricted
		// to 0 or greater in order for the interpolation function to remain a
		// one-to-one function
		time = Math.max(time, 0);
	}

	@Override
	public double calculateIn() {
		return calculateInForTime(leadingCoefficient, start, time);
	}

	@Override
	public double calculateOut() {
		return calculateInForTime(leadingCoefficient, start, 1 - time);
	}

	@Override
	public double calculateInOut() {
		return calculateIn();
	}

	private void calculateLeadingCoefficient() {
		leadingCoefficient = (end - start) / (time * time);
	}
}
