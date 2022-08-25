package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/valid-anagram/">Valid Anagram</a>
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        return sort(s).equals(sort(t));
    }

    private String sort(String s) {
        var chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    void demo() {
        assertTrue(isAnagram("anagram", "nagaram"));
        assertFalse(isAnagram("rat", "car"));
    }
}
