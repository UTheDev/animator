package com.uthedev.animator.old;

/**
 * General use interface for interpolation.
 * 
 * @author UTheDev
 */
public interface OldLerp {
	
	/**
	 * The base interpolation method (this is used for every other to avoid calculus lol).
	 * @param start The starting position
	 * @param end The ending position.
	 * @param time The time position.
	 * @return The result.
	 */
	static double linear(double start, double end, double time) {
		return start + (end - start) * time; //(1 - time) * start + time * end;
	}
	
	public abstract class EaseFunc {
		public abstract double calc(double time);
	}
	
	public class EaseHandle {
		public EaseFunc func;
		
		public double convert(EasingDirection direction, double time) {
			if (direction != null) {
				if (func != null) {
					switch(direction) {
					case IN:
						return func.calc(time);
					case IN_OUT:
						return linear(convert(EasingDirection.IN, time), convert(EasingDirection.OUT, time), time);
					case OUT:
						return func.calc(1 - time);
					default:
						return 0;
					}
				} else {
					System.err.println("Missing easing function.");
					return 0;
				}
			} else {
				System.err.println("direction is null.");
				return 0;
			}
		}
	}
	
	static EaseHandle newEaseHandler(EaseFunc f) {
		EaseHandle handler = new EaseHandle();
		handler.func = f;
		
		return handler;
	}
	
	static double exponential(double start, double end, double time, double factor, EasingDirection dir) {
		if (time != 0) {
			EaseFunc easer = new EaseFunc() {
				public double calc(double time) {
					return Math.pow(time, factor);
				}
			};
			
			EaseHandle handler = newEaseHandler(easer);
			
			return linear(end, start, handler.convert(dir, time));
		} else {
			return 0;
		}
	}
	
	static double square(double start, double end, double time, EasingDirection dir) {
		return exponential(start, end, time, 2, dir);
	}
	
	static double cubic(double start, double end, double time, EasingDirection dir) {
		return exponential(start, end, time, 3, dir);
	}
	
	/**
	static double alsoNotExponential(double start, double end, double time, double power) {
		return start; //* (end/start)^((x-a) / (b-a));
	}

	static double notExponential(double start, double end, double time, double power) {
		return start * Math.pow((end / start), (power / time));
	}
	*/
}
