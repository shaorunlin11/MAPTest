package humanevaltest.original.task140;

import java.util.*;
import java.lang.*;

class Solution {
    public String fixSpaces(String text) {
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                end += 1;
            } else {
                if (end - start > 2) {
                    sb.append('-');
                } else if (end - start > 0) {
                    for (int j = 0; j < end - start; j++) {
                        sb.append('_');
                    }
                }
                sb.append(text.charAt(i));
                start = i + 1;
                end = i + 1;
            }
        }
        if (end - start > 2) {
            sb.append('-');
        } else if (end - start > 0) {
            for (int j = 0; j < end - start; j++) {
                sb.append('_');
            }
        }
        return sb.toString();
    }
}
