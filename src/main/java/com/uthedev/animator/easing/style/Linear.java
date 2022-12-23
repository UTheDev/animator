package com.uthedev.animator.easing.style;

import com.uthedev.animator.easing.BaseEasingStyle;

public class Linear extends BaseEasingStyle {
	public Linear(double start, double end, double time) {
		super(start, end, time);
	}
	
	@Override
	public double calculateIn() {
		return start + (end - start) * time;
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
