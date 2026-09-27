package humanevaltest.original.task101;

import java.util.*;
import java.lang.*;

class Solution {
    public List<String> wordStrings(String s) {
        if (s.length() == 0) {
            return new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();

        for (char letter : s.toCharArray()) {
            if (letter == ',') {
                sb.append(' ');
            } else {
                sb.append(letter);
            }
        }

        return new ArrayList<>(Arrays.asList(sb.toString().split("\\s+")));
    }
}
