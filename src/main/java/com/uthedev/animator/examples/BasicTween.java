package com.uthedev.animator.examples;

import com.uthedev.animator.Tween;

public class BasicTween {
    public static void main(String[] args) {
        Tween tween = new Tween(1, 5, 0.5) {
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
