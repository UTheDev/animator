# Notes for Easing.java
This is the formula for linear interpolation in TypeScript (also known as "lerping"):

```
function lerp(a: number, b: number, t: number): number {
    return a + (b - a) * c
}
```

where ```a``` is your starting value, ```b``` is your ending value, and ```t``` is your alpha time.

All of the easing functions in Easing.java are based on lerping. What makes easing in and out work with functions like sine and square work is that the progression rate of lerp alpha time *t* changes with respect to clock time.

For example, lets examine the commonly used easing function, ```outQuad()```, where the progression rate of *t* slows down over time based on a quadratic function.

```
function outQuad(t: number) {
    return 1 - (1 - t) * (1 - t)
}
```

<small>Courtesy of *Easing Functions Cheat Sheet* by Andrey Sitnik and Ivan Solovev. Their article on outQuad() can be found [here](https://easings.net/#easeOutQuad).</small>

This simply makes time progress slower as the animation gets closer to the end in a quadratic fashion. As *t* approaches 1, it's subtracted by a smaller and smaller amount until the difference is 0, therefore making *t* equal to 1.

(Sorry about the somewhat confusing explaination. I'll improve on it once I find the time and motivation.)