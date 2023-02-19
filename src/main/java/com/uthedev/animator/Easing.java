package com.uthedev.animator;

/**
 * A class containing commonly used easing functions.
 * 
 * Huge thanks to the Easing Functions Cheat Sheet by Andrey Sitnik and Ivan
 * Solovev for providing the easing function formula sheet
 * utilized by this class (https://easings.net).
 * ()
 * 
 * @author UTheDev
 */
public class Easing {
    /**
     * Returns the linear interpolation for a given starting value, ending value,
     * and alpha time.
     * 
     * @param start The starting value
     * @param end   The ending value
     * @param time  The alpha time
     * @return The resulting interpolation
     */
    public static double linear(double start, double end, double time) {
        return start + (end - start) * time;
    }

    public static double inQuad(double start, double end, double time) {
        return Math.pow(1 - linear(start, end, time), 2);
    }

    public static double outQuad(double start, double end, double time) {
        return 1 - inQuad(start, end, time);
    }
}
