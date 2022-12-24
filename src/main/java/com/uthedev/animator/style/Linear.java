package com.uthedev.animator.style;

import com.uthedev.animator.Lerp;

public class Linear extends Lerp {
	public Linear(double start, double end, double time) {
		super(start, end, time);
	}
	
	@Override
	public double calculateIn() {
		return lerpLinear(start, end, time);
	}
	
	@Override
	public double calculateOut() {
		return calculateIn();
	}
	
	@Override
	public double calculateInOut() {
		return calculateIn();
	}
}
