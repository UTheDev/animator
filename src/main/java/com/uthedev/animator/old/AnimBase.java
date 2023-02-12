package com.uthedev.animator.old;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.uthedev.animator.old.signals.*;

/**
 * The base class for the animator objects.
 */
public abstract class AnimBase {
	// Animation variables
	private boolean destroyOnFinish = false;
	
	protected double timeDelta = 0; // Time increment based on the AnimationInfo provided
	protected double start = 0; // Starting value
	protected double end = 0; // Ending value
	protected double frameDur = 1; // Amount of time to wait until the next frame is done (in milliseconds)
	protected boolean isRunning = false;

	protected AnimationInfo info;

	protected ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	protected ScheduledFuture<?> future;

	protected double time = 0;

	protected Runnable animRunner;

	protected abstract void runAnimation();

	// Setup events

	/**
	 * See {@link OnStep}.
	 */
	public OnStep onStep;

	/**
	 * See {@link OnFinish}.
	 */
	public OnFinish onFinish;

	/**
	 * See {@link OnStop}.
	 */
	public OnStop onStop;

	/**
	 * The numeric value affected by this class
	 */
	public double value = 0; // Current position in the animation

	/**
	 * private void fireEvent(Object r) { if (r != null) { r.run(); } }
	 **/

	protected double getTimeDelta(double totalDur) {
		timeDelta = (1 / totalDur) * (frameDur * 0.001);
		return timeDelta;
	}

	protected void incrementTime() {
		setTime(time + timeDelta);
	}

	protected void stopAnimExecution() {
		if (future != null) {
			future.cancel(false);
			future = null;
		}

		scheduler.shutdown();

		onStep = null;
		onFinish = null;
		onStop = null;

		isRunning = false;
		info = null;
	}

	protected void indicateAnimFinish() {
		// False is passed so onFinish can be fired
		future.cancel(false);
		isRunning = false;

	}

	/**
	 * 
	 * @return Whether or not the animation is running.
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * 
	 * @return The animation's time position.
	 */
	public double getTime() {
		return time;
	}

	/**
	 * Sets the time position of the animation.
	 * 
	 * @param t A time position between 0 and 1
	 */
	public void setTime(double t) {
		time = Utility.clamp(0, 1, t);
	}

	/**
	 * @return The frame duration in milliseconds.
	 */
	public double getFrameDuration() {
		return frameDur;
	}

	/**
	 * Sets how long to wait before animating the next frame with respect to
	 * animation time. The default is 1.
	 * 
	 * @param time The duration in milliseconds (the minimum is 0).
	 */
	public void setFrameDuration(double time) {
		frameDur = Math.max(0, time);
	}

	/**
	 * For setting the two ends of the animation.
	 * 
	 * @param a Starting position
	 * @param b Ending position
	 */
	public void setPoints(double a, double b) {
		start = a;
		value = a;
		end = b;
	}

	/**
	 * For setting the animation info used to do the animation.
	 * 
	 * @param a     The AnimationInfo used.
	 * @param start The starting number.
	 * @param end   The ending number.
	 */
	public void setInfo(AnimationInfo a, double start, double end) {
		info = a;
		getTimeDelta(a.duration);
		this.setPoints(start, end);
	}

	/**
	 * Stops the animation and fires the onStop event, if the animation is playing.
	 */
	public void stop() {
		if (isRunning == true) {
			future.cancel(false);
			isRunning = false;

			if (onStop != null) {
				onStop.run();
			}
		}
	}

	/**
	 * Starts the animation.
	 */
	public void play() {
		if (isRunning == false) {
			if (info != null) {
				isRunning = true;

				// To be called every millisecond
				future = scheduler.scheduleAtFixedRate(new Runnable() {
					public void run() {
						timeDelta = (1 / info.duration) * (frameDur * 0.001);
						runAnimation();
						
						if (destroyOnFinish == true && time >= 1) {
							// Destroy on animation finish
							destroy();
						}
					}
				}, 0, (long) frameDur, TimeUnit.MILLISECONDS);
			} else {
				System.err.println("Cannot start animation because no animation info was set.");
			}
		}
	}

	/**
	 * Sets if the animation object is to be destroyed once it finishes.
	 * 
	 * @param isDestroyed Whether or not to destroy on finish.
	 */
	public void destroyOnFinish(boolean isDestroyed) {
		destroyOnFinish = isDestroyed;
	}

	public abstract void destroy();
}
