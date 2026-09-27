package humanevaltest.original.task105;

import java.util.*;
import java.lang.*;

class Solution {
    public List<String> byLength(List<Integer> arr) {
        List<Integer> sorted_arr = new ArrayList<>(arr);
        sorted_arr.sort(Collections.reverseOrder());
        List<String> new_arr = new ArrayList<>();
        for (int var : sorted_arr) {
            if (var >= 1 && var <= 9) {
                switch (var) {
                    case 1:
                        new_arr.add("One");
                        break;
                    case 2:
                        new_arr.add("Two");
                        break;
                    case 3:
                        new_arr.add("Three");
                        break;
                    case 4:
                        new_arr.add("Four");
                        break;
                    case 5:
                        new_arr.add("Five");
                        break;
                    case 6:
                        new_arr.add("Six");
                        break;
                    case 7:
                        new_arr.add("Seven");
                        break;
                    case 8:
                        new_arr.add("Eight");
                        break;
                    case 9:
                        new_arr.add("Nine");
                        break;
                }
            }
        }
        return new_arr;
    }
}
