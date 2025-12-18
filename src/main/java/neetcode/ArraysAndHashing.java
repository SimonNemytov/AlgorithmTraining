package neetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArraysAndHashing {

    public static boolean hasDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        return set.size() == nums.length;
    }

    public static boolean isReversed(String s, String t) {
        char[] string = s.toCharArray();
        char[] telegram = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = t.length() - 1; j > 0; j--) {
                if (string[i] != telegram[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  An anagram is a string that contains the exact same
     *  characters as another string,
     *  but the order of the characters can be different.
     */

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer result = map.get(c);
            if (result == null) {
                map.put(c,
                        1);
            } else {
                map.put(c,
                        result + 1);
            }
        }
        for (char c : t.toCharArray()) {
            Integer result = map.get(c);
            if (result == null) {
                return false;
            } else {
                map.put(c,
                        result - 1);
                if (result - 1 < 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
