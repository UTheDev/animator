package com.uthedev.animator.examples;

import com.uthedev.animator.Lerp;

public class BasicTween {
    public static void main(String[] args) {
        Lerp tween = new Lerp(1, 5, 0.5) {
            public double calculate() {
                return getStart() + (getEnd() - getStart()) * getTime(); // linear interpolation
            }
        };

        System.out.println(
                    "[tween]:\nstarting value: " + tween.getStart()
                    + "\nending value: " + tween.getEnd()
                    + "\nvalue: " + tween.getValue()
                );
    }
}
