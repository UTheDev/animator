package com.uthedev.animator.old.style;

import com.uthedev.animator.old.Lerp;

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
