package com.uthedev.animator;

/**
 * Provides the information needed by the animator methods.
 */
public class AnimationInfo {
	public double duration = 1;
	public EasingStyle style = EasingStyle.LINEAR;
	public EasingDirection direction = EasingDirection.OUT;
	
	public AnimationInfo() {}
	public AnimationInfo(double dur, EasingStyle easeStyle, EasingDirection direct) {
		duration = dur;
		style = easeStyle;
		direction = direct;
	}
}