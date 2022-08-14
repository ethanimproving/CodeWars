package com.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/">Merge Two Sorted Lists</a>
 */
public class MergeTwoLinkedLists {
    public Node mergeTwoLists(Node list1, Node list2) {
        Node head;

        if (list1 == null && list2 == null) return null;
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            head = list1;
            list1 = list1.next;
        }
        head.next = mergeTwoLists(list1, list2);
        return head;
    }


    @Test
    void demo() {
        assertEquals(getNodeList(1, 1, 2, 3, 4, 4).toList(), mergeTwoLists(getNodeList(1, 2, 4), getNodeList(1, 3, 4)).toList());
    }

    private Node getNodeList(int... numbers) {
        Node currentHead = null;
        Node previousHead = null;
        for (var number : numbers) {
            previousHead = currentHead;
            currentHead = new Node(previousHead, number, null);
            if (previousHead != null)
                previousHead.next = currentHead;
        }
        while (currentHead.prev != null) {
            currentHead = currentHead.prev;
        }
        return currentHead;
    }

    private static class Node {
        int val;
        Node next;
        Node prev;

        Node(Node prev, int element, Node next) {
            this.val = element;
            this.next = next;
            this.prev = prev;
        }

        private List<Integer> toList() {
            var list = new ArrayList<Integer>();
            var currentNode = this;
            do {
                list.add(currentNode.val);
                currentNode = currentNode.next;

            } while (currentNode.next != null);
            list.add(currentNode.val);
            return list;
        }
    }
}


