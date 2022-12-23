/**
 * For backwards compatibility
 */

package com.uthedev.animator.signals;

public abstract class OnStep implements Runnable {
	/**
	 * Called every time the animator comes across a frame
	 * @param onStep
	 */
	public abstract void run(double onStep);
}
