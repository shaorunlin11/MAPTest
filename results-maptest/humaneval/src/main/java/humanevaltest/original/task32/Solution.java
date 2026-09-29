package humanevaltest.original.task32;

import java.util.*;
import java.lang.*;

class Solution {
    /**
    Evaluates polynomial with coefficients xs at point x.
    return xs[0] + xs[1] * x + xs[1] * x^2 + .... xs[n] * x^n
     */
    public double poly(List<Double> xs, double x) {
        double result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i) * Math.pow(x, i);
        }
        return result;
    }
    
    public double findZero(List<Double> xs) {
        double begin = -1, end = 1;
        while (poly(xs, begin) * poly(xs, end) > 0) {
            begin *= 2;
            end *= 2;
        }
        while (end - begin > 1e-10) {
            double center = (begin + end) / 2;
            if (poly(xs, begin) * poly(xs, center) > 0) {
                begin = center;
            } else {
                end = center;
            }
        }
        return begin;
    }
}
