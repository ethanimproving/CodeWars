package com.leetcode.tries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://leetcode.com/problems/implement-trie-prefix-tree/">Implement Trie</a>
 */
public class Trie {
    Node root;

    public Trie() {
        root = new Node('\0'); //dummy node
    }

    public void insert(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) { // If current node doesn't already have a child with the current character in question
                curr.children[x - 'a'] = new Node(x); // Then add this character as a child node
            }
            curr = curr.children[x - 'a']; // Move current node one position forward
        }
        curr.isWord = true; // Mark the last character of this word
    }

    public boolean search(String word) {
        Node lastCharacter = getLast(word);
        return (lastCharacter != null && lastCharacter.isWord); // If lastCharacter is not null, and marked as last char in a word, return true
    }

    //helper method
    public Node getLast(String word) {
        Node curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) { // Search if node to see if there already exists a child character that matches x
                return null; // If not, return null
            }

            curr = curr.children[x - 'a']; // If yes, move over one and check the next node
        }
        return curr; // Finally return the last character
    }

    public boolean startsWith(String prefix) {
        Node lastCharacter = getLast(prefix);
        return lastCharacter != null; // If last character is not null, words exist with this prefix
    }

    static class Node {

        private final char value;
        private boolean isWord;
        private final Node[] children;

        public Node(char val) {
            this.value = val; // Each node is a character
            this.isWord = false; // Indicates if this character is the end of a word
            this.children = new Node[26]; // There are 26 possible characters in the alphabet that can follow this node
        }
    }

    @Test
    void demo() {
        Trie trie = new Trie();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));

    }
}
