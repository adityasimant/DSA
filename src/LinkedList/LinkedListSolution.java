package LinkedList;

import java.util.HashMap;
import java.util.HashSet;

class LinkedListMain {
    public static void main(String[] args) {
        ListNode head = LinkedListSolution.createLinkedList(new int[]{1, 2, 3, 4, 5});

        System.out.print("Linked List: ");
        printLinkedList(head);

        // Check for cycle
        LinkedListSolution solution = new LinkedListSolution();
        boolean hasCycle = solution.hasCycle(head);

        System.out.println("Has Cycle? " + hasCycle);
    }

    // Utility method to print a linked list
    private static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListSolution {

    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode itNode = head;

        while(itNode != null){
            if (set.contains(itNode)) return  true;
            set.add(itNode);
            itNode = itNode.next;
        }

        return false;
    }
}
