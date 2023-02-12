package com.uthedev.animator.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.uthedev.animator.old.signals.OnStep;

/**
 * For the interpolation of multiple values at the same time.
 * <p>
 * Interpolation data for each string is stored in an ArrayList associated with
 * it.
 * <p>
 * 0 = Start | 1 = End
 * 
 * @param <T> The data type of the HashMap keys used by an instance of AnimationSet
 */
public class AnimationSet<T> extends AnimBase {
	private HashMap<T, ArrayList<Double>> valueEnds = new HashMap<T, ArrayList<Double>>();
	private HashMap<T, OnStep> updaters = new HashMap<T, OnStep>();
	private HashMap<T, Double> values = new HashMap<T, Double>();

	private Animator setAnimator;
	
	/**
	 * Runs the numeric animations currently added to the AnimationSet instance
	 */
	protected void runAnimation() {
		if (info != null && setAnimator != null) {

			if (time < 1) {
				incrementTime();

				for (Entry<T, ArrayList<Double>> i : valueEnds.entrySet()) {
					// Update info
					T key = i.getKey();
					ArrayList<Double> infoArray = i.getValue();
					double finalVal = 0;

					if (infoArray != null) {
						double start = infoArray.get(0);
						double end = infoArray.get(1);

						finalVal = Utility.calculateValue(info, start, end, time);
						values.put(key, finalVal);
					}

					// Call the corresponding updater method
					OnStep step = updaters.get(key);
					if (step != null) {
						step.run(finalVal);
					}
				}
				
				/*
				if (onStep != null) {
					onStep.run(time);
				}
				*/
			} else {
				indicateAnimFinish();
			}
		} else {
			System.err.println("Cannot play animation set, missing either the AnimationInfo or the Animator.");
		}
	}

	/**
	 * Adds a value to be interpolated by the set.
	 * 
	 * @param index   An index to associate the value with.
	 * @param start   The starting value.
	 * @param end     The ending value.
	 * @param updater The OnStep abstract class that provides an update method
	 *                for the value. See {@link OnStep}.
	 */
	public void addValue(T index, double start, double end, OnStep updater) {
		ArrayList<Double> valEnds = new ArrayList<Double>();
		valEnds.add(0, start);
		valEnds.add(1, end);

		values.put(index, start);
		valueEnds.put(index, valEnds);
		updaters.put(index, updater);
	}

	/**
	 * Removes the value from being interpolated by the set.
	 * 
	 * @param index The object
	 */
	public void removeValue(T index) {
		values.remove(index);
		valueEnds.remove(index);
		updaters.remove(index);
	}

	/**
	 * 
	 * @param index The object referencing the value.
	 * @return The value associated with the object (or null if it doesn't exist).
	 */
	public double getValue(T index) {
		return values.get(index);
	}

	/**
	 * 
	 * @return Returns the Animator associated with the set.
	 */
	public Animator getBoundAnimator() {
		return setAnimator;
	}

	/**
	 * Registers the set to animate with the provided AnimationInfo.
	 * 
	 * @param animInfo The AnimationInfo to use.
	 */
	public void setInfo(AnimationInfo animInfo) {
		info = animInfo;
		getTimeDelta(info.duration);

		Animator a = new Animator();
		a.setInfo(info, 0, 1);
		setAnimator = a;
	}

	/**
	 * Frees the memory used by this set. Events will be destroyed.
	 */
	public void destroy() {
		stopAnimExecution();

		valueEnds.clear();
		updaters.clear();
		values.clear();

		valueEnds = null;
		updaters = null;
		values = null;

		if (setAnimator != null) {
			setAnimator.destroy();
			setAnimator = null;

			info = null;
		}
	}
	
	public AnimationSet() {}
	public AnimationSet(HashMap<T, ArrayList<Double>> valueMap) {
		valueEnds = valueMap;
	}
	public AnimationSet(HashMap<T, Double> from, HashMap<T, Double> to) {
		for (Entry<T, Double> i : from.entrySet()) {
			T fromKey = i.getKey();
			Double destValue = to.get(fromKey);
			if (destValue != null) {
				addValue(fromKey, i.getValue(), destValue, null);
			}
		}
	}
	
	/**
	 * Constructs an AnimationSet from property dictionaries.
	 * 
	 * @param from The old properties.
	 * @param to The new properties.
	 */
	public static AnimationSet<String> fromProperties(HashMap<String, Double> from, HashMap<String, Double> to) {
		return new AnimationSet<String>(from, to);
	}
}
