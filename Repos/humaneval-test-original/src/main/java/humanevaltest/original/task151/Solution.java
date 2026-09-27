package humanevaltest.original.task151;

import java.util.*;
import java.lang.*;

class Solution {
    public int doubleTheDifference(List<Object> lst) {
        return lst.stream()
                .filter(i -> i instanceof Integer && (Integer) i > 0 && (Integer) i % 2 != 0)
                .map(i -> (Integer) i * (Integer) i)
                .reduce(Integer::sum)
                .orElse(0);
    }
}

