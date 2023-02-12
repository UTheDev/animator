package com.uthedev.animator;

/**
 * This class is responsible for handling one tween (also known as an animation)
 * per instance.
 * 
 * @author UTheDev
 */
public class Tween {
    private double time;

    // whatever results from the calculate() method
    private double value;

    /**
     * The starting value.
     */
    private double start;

    /**
     * The ending value.
     */
    private double end;

    /**
     * Constructs a Tween where start, end, and time are all 0.
     */
    public Tween() {
        this(0, 0, 0);
    }

    /**
     * Constructs a Tween with some initial values specified.
     * 
     * @param initStart The initial starting time.
     * @param initEnd   The initial ending time.
     * @param initTime  The initial alpha time.
     */
    public Tween(double initStart, double initEnd, double initTime) {
        start = initStart;
        end = initEnd;
        setTime(initTime);
    }

    /**
     * Interpolates the numerical value for a specific instance of Tween based on
     * the instance's starting value, ending value, and current alpha time.
     * This method is intended to be overriden by developers.
     * 
     * @return The interpolated value.
     */
    public double calculate() {
        return 0;
    }

    /**
     * This method should be overriden to react to tween steps (e.g. setting a
     * sound's volume)
     */
    public void onStep(double value) {

    }

    /**
     * 
     * @return The alpha time.
     */
    public double getTime() {
        return time;
    }

    /**
     * Sets the alpha time
     * 
     * @param t The new alpha time
     */
    public void setTime(double t) {
        if (time != t) {
            time = t;
            value = calculate();
            onStep(value);
        }
    }

    /**
     * 
     * @return The last calculated value.
    */
    public double getValue() {
        return value;
    }

    /**
     * 
     * @return The starting value.
     */
    public double getStart() {
        return start;
    }

    /**
     * Sets the starting value
     * 
     * @param t The new starting value
     */
    public void setStart(double newStart) {
        if (start != newStart) {
            start = newStart;
            setTime(time);
        }
    }

    /**
     * 
     * @return The ending value.
     */
    public double getEnd() {
        return end;
    }

    /**
     * Sets the ending value
     * 
     * @param t The new ending value
     */
    public void setEnd(double newEnd) {
        if (end != newEnd) {
            end = newEnd;
            setTime(end);
        }
    }
}
