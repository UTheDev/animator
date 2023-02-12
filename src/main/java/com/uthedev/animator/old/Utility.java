package com.uthedev.animator.old;

//import com.uthedev.animator.signals.*;

/**
 * A base interface for animating/interpolating numeric values.
 * 
 * @author UTheDev
 * 
 */
public interface Utility {
	
	/**
	 * Constrains a number and returns the result.
	 * 
	 * @param min Minimum number.
	 * @param max Maximum number.
	 * @param n   The number to constrain.
	 * @return The result from constraining the number.
	 */
	static double clamp(double min, double max, double n) {
		double result;

		if (n > max) {
			result = max;
		} else if (n < min) {
			result = min;
		} else {
			result = n;
		}

		return result;
	}

	/**
	 * Constructs and returns an AnimationInfo. Use this constructor function to
	 * instantiate one with less lines.
	 * 
	 * @param duration  Animation length.
	 * @param style     Animation easing style.
	 * @param direction Animation easing "direction".
	 * @return The created AnimationInfo.
	 */
	public static AnimationInfo newAnimationInfo(double duration, EasingStyle style, EasingDirection direction) {
		AnimationInfo newInfo = new AnimationInfo();

		newInfo.duration = duration;
		newInfo.style = style;
		newInfo.direction = direction;

		return newInfo;
	}

	/**
	 * Calculates the position for the given time.
	 * 
	 * @param info
	 * @param start
	 * @param end
	 * @param time
	 * 
	 * @return The position.
	 */
	public static double calculateValue(AnimationInfo info, double start, double end, double time) {
		// Use time manipulation for easing directions
		EasingStyle styleRequest = info.style;

		switch(styleRequest) {
			case LINEAR:
			return OldLerp.linear(start, end , time);

			case SQUARE:
			return OldLerp.square(start, end, time, info.direction);

			default:
			return 0;
		}

		/*
		EasingStyle[] styleVals = EasingStyle.values();

		// Get the corresponding interpolation
		
		int index = 0;
		for (EasingStyle i : styleVals) {
			index++;
			if (i == styleRequest) {
				return Lerp.exponential(start, end, time, index, info.direction);
			}
		}
		*/

		//return 0;
		/**
		 * if (info.direction == EasingDirection.OUT) { return start + (result - start)
		 * * time; } else { return result; }
		 */
	}
	
	

	// Method to destroy the animator when it finishes.
	/*
	public static void setAnimForGc(Animator a) {
		a.onFinish = new OnFinish() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				a.destroy();
			}
			
		};
	}
	*/

	
}
