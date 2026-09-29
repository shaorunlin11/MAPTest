package humanevaltest.original.task75;

import java.util.*;
import java.lang.*;

class Solution {
    public boolean isMultiplyPrime(int a) {
        class IsPrime {
            public boolean is_prime(int n) {
                for (int j = 2; j < n; j++) {
                    if (n % j == 0) {
                        return false;
                    }
                }
                return true;
            }
        }

        IsPrime primeChecker = new IsPrime();

        for (int i = 2; i < 101; i++) {
            if (!primeChecker.is_prime(i)) {
                continue;
            }
            for (int j = i; j < 101; j++) {
                if (!primeChecker.is_prime(j)) {
                    continue;
                }
                for (int k = j; k < 101; k++) {
                    if (!primeChecker.is_prime(k)) {
                        continue;
                    }
                    if (i * j * k == a) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
