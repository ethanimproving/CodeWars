package com.leetcode.arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @see <a href="https://www.lintcode.com/problem/659/">Encode and Decode Strings</a>
 */
public class EncodeAndDecodeStrings {
    public String encode(List<String> strs) {
        var encodedString = new StringBuilder();
        for (var str : strs) {
            encodedString
                    .append(str.length()) // Add number of characters to parse after delimiter
                    .append("#") // Add delimiter
                    .append(str); // Add string
        }
        return encodedString.toString();
    }

    public List<String> decode(String str) {
        List<String> list = new ArrayList<>();
        int endingWordIndex = 0;
        while (endingWordIndex < str.length()) { // Iterate over each character
            int beginningWordIndex = endingWordIndex; // Begin index of this word at last index of previous word
            while (str.charAt(beginningWordIndex) != '#')
                beginningWordIndex++; // Number before '#' indicates how many characters the following string is

            int length = Integer.parseInt(str.substring(endingWordIndex, beginningWordIndex)); // Get length of string to parse
            endingWordIndex = ++beginningWordIndex + length; // Get ending index of current word
            list.add(str.substring(beginningWordIndex, endingWordIndex));
        }
        return list;
    }

    @Test
    void demo() {
        assertIterableEquals(List.of("lint", "code", "love", "you"), decode(encode(List.of("lint", "code", "love", "you"))));
        assertIterableEquals(List.of("we", "say", ":", "yes"), decode(encode(List.of("we", "say", ":", "yes"))));
    }
}