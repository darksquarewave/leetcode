package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        List<Character> current = new ArrayList<>();
        current.add(s.charAt(0));
        int maxSize = 0;

        for (int j = 1; j < s.length(); j++) {
            char c = s.charAt(j);

            for (int i = 0; i < current.size(); i++) {
                if (current.get(i).equals(c)) {
                    if (maxSize < current.size()) {
                        maxSize = current.size();
                    }
                    List<Character> newCurrent = new ArrayList<>();
                    while (++i < current.size()) {
                        newCurrent.add(current.get(i));
                    }

                    current = newCurrent;
                }
            }

            current.add(c);
        }

        if (maxSize < current.size()) {
            maxSize = current.size();
        }

        return maxSize;
    }
}
