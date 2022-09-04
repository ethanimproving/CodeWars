package com.leetcode.tries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/">Design Add and Search Word Data Structure</a>
 */
public class DesignAddAndSearchWordDataStructure {

    static class WordDictionary {
        Node root;

        private static class Node {
            char value;
            boolean isWord;
            Node[] children;

            public Node(char value) {
                this.value = value;
                isWord = false;
                children = new Node[26];
            }
        }

        public WordDictionary() {
            root = new Node('\0');
        }

        public void addWord(String word) {
            Node curr = root;

            for (var ch : word.toCharArray()) {
                if (curr.children[ch - 'a'] == null) {
                    curr.children[ch - 'a'] = new Node(ch);
                }

                curr = curr.children[ch - 'a'];
            }

            curr.isWord = true;
        }


        // TC O(m^2)
        public boolean search(String word) {
            return searchHelper(word, root, 0);
        }

        private boolean searchHelper(String word, Node currentCharacter, int index) {
            for (int i = index; i < word.length(); i++) {
                char nextCharacter = word.charAt(i);

                if (nextCharacter == '.') { // If next character is '.', search all the children
                    for (Node child : currentCharacter.children) {
                        if (child != null && searchHelper(word, child, i + 1)) {
                            return true;
                        }
                    }

                    return false;
                }

                if (currentCharacter.children[nextCharacter - 'a'] == null) { // If there is no child character that matches the next character, word does not exist
                    return false;
                }

                currentCharacter = currentCharacter.children[nextCharacter - 'a'];
            }

            return currentCharacter.isWord;
        }
    }

    @Test
    void demo() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assertFalse(wordDictionary.search("pad"));
        assertTrue(wordDictionary.search("bad"));
        assertTrue(wordDictionary.search(".ad"));
        assertTrue(wordDictionary.search("b.."));

    }
}
