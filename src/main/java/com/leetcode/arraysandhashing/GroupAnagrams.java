package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://leetcode.com/problems/group-anagrams/">Group Anagrams</a>
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        var res = new ArrayList<List<String>>();
        if (strs.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            /* Store characters by their alphabetical index in "hash". */
            char[] hash = new char[26];
            for (char c : s.toCharArray()) {
                hash[c - 'a']++;
            }
            String key = new String(hash);
            /* If the key doesn't exist, insert it with an empty array list. */
            map.computeIfAbsent(key, k -> new ArrayList<>());
            /* Then add the original string. */
            map.get(key).add(s);
        }
        res.addAll(map.values());
        return res;
    }

    @Test
    void demo() {
        assertIterableEquals(of(of("tan", "nat"), of("eat", "tea", "ate"), of("bat")), groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
