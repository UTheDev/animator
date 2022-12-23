package com.uthedev.animator;

/**
 * An interface that can be implemented to give objects the ability to animate
 * field changes.
 * 
 * @author UTheDev
 * 
 * @param <T> The type of object to specify as a "destination".
 */
public interface AnimatableObject<T> {
	public abstract AnimBase animate(AnimationInfo animInfo, T destination);
}
