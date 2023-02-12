package com.uthedev.animator.old;

/**
 * The class used to animate stuff.
 */
public class Animator extends AnimBase {

	protected void runAnimation() {
		if (isRunning == true) {
			if (time < 1) {
				incrementTime();

				value = Utility.calculateValue(info, start, end, time);
				if (onStep != null) {
					onStep.run(value);
				}
			} else {
				indicateAnimFinish();
			}
		}
	}

	/**
	 * Sets up animator values for garbage collection. Callback connections will be
	 * destroyed. The animation will be stopped.
	 */
	public void destroy() {
		stopAnimExecution();
	}
}
