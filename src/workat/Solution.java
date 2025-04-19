package workat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

class WorkAtTechMain{
    public static void main(String[] args) {

        int[] arr = {5,4, 3, 2, 1};
        Solution s = new Solution();
        s.mergeSort(arr);
        for (int i: arr){
            System.out.println(i);
        }
//        System.out.println(sol);
    }
}

class ListNode {
    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}


public class Solution {

    void mergeSort (int[] arr) {

        int arrLen = arr.length;

        if (arrLen < 2) return;

        int midLen = arrLen/2;

        int leftArr[] = new int[midLen];
        int rightArr[] = new int[arrLen - midLen];

        for (int i=0; i<midLen; i++){
            leftArr[i] = arr[i];
        }
        for (int i=midLen; i<arrLen; i++){
            rightArr[i - midLen] = arr[i];
        }

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(arr, leftArr, rightArr);

    }

    void merge(int[] arr, int[] left, int[]right){
        int i = 0,j = 0,k= 0;
        while (i < left.length && j < right.length){
            if (left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        if (j < right.length){
            while (j < right.length){
                arr[k] = right[j];
                j++;
                k++;
            }
        }

        if (i < left.length){
            while (i < left.length){
                arr[k] = left[i];
                i++;
                k++;
            }
        }
    }

    int[] searchRange (int[] arr, int key) {
        int start = -1;
        int end = -1;

        for (int i=0; i<arr.length; i++){
            if (arr[i] == key){
                if (start == -1){
                    start = i;
                }

                if (end<i){
                    end = i;
                }
            }
        }
        int[] sol = new int[2];
        sol[0] = start;
        sol[1] = end;
        return sol;
    }

    ListNode getIntersectionNode(ListNode A, ListNode B) {
        HashSet<ListNode> NodeSet = new HashSet<>();
        if (A == null) return new ListNode(-1);

        ListNode itNode = A;
        while(itNode != null){
            NodeSet.add(itNode);
            itNode = itNode.next;
        }
        itNode = B;
        while (itNode != null){
            if (NodeSet.contains(itNode)){
                return itNode;
            }

            itNode = itNode.next;
        }

        return new ListNode(-1);
    }

//    ListNode reverseLinkedList (ListNode head) {
//        Stack<ListNode> stk = new Stack<>();
//
//        ListNode itNode;
//        itNode = head;
//        while (itNode != null){
//            stk.add(itNode);
//            itNode = itNode.next;
//        }
//        ListNode currentNode;
//        ListNode itCNode = null;
//        ListNode headNode = null;
//        while (!stk.isEmpty()){
//            currentNode = stk.pop();
//            if (headNode == null){
//                headNode = currentNode;
//                itCNode = currentNode;
//                continue;
//            }
//            itCNode.next = currentNode;
//            itCNode = currentNode;
//        }
//
//        return headNode;
//    }

    ListNode reverseLinkedList (ListNode head) {
        ListNode itNode = head;
        ListNode headNode = head;
        ListNode tmpNext;
        ListNode tmpPrev = null;

        while (itNode != null){
            tmpNext = itNode.next;
            itNode.next = tmpPrev;
            tmpPrev = itNode;
            itNode = tmpNext;
            if (itNode.next == null) headNode = itNode;
        }
        return headNode;
    }

    ListNode mergeTwoSortedList (ListNode firstList, ListNode secondList) {
        ListNode currentFist = firstList;
        ListNode currentSecond = secondList;
        ListNode head = null;
        ListNode solList = null;

        while (currentFist != null && currentSecond != null){
            if (solList == null){
                if (currentFist.data<= currentSecond.data){
                    solList = currentFist;
                    currentFist = currentFist.next;
                }else {
                    solList = currentSecond;
                    currentSecond = currentSecond.next;
                }
                head = solList;
                continue;
            }

            if (currentFist.data<= currentSecond.data){
                solList.next = currentFist;
                solList = currentFist;
                currentFist = currentFist.next;
            }else {
                solList.next = currentSecond;
                solList = currentSecond;
                currentSecond = currentSecond.next;
            }

        }

        while (currentFist != null){
            solList.next = currentFist;
            solList = currentFist;
            currentFist = currentFist.next;
        }

        while (currentSecond != null){
            solList.next = currentSecond;
            solList = currentSecond;
            currentFist = currentFist.next;
        }

        return head;
    }

    int getMiddleElementOfLinkedList (ListNode list) {
        int initialCounter = 0;
        ListNode current = list;
        while (current != null){
            initialCounter++;
            current = current.next;
        }
        if (initialCounter == 1) return list.data;

        int midPoint = initialCounter/2;
        int finalCounter = 0;
        current = list;
        while (current != null){
            finalCounter++;
            if (finalCounter == midPoint) return current.data;
            current = current.next;
        }
        return -1;
    }


    boolean isPalindrome(ListNode list) {
        ListNode current;
        current = list;
        int listLength =0;
        while (current != null){
            listLength ++;
            current = current.next;
        }
        boolean isEven;
        if (listLength%2 == 0){
            isEven = true;
        }else{
            isEven = false;
        }

        int midPoint = 1+ listLength/2;

        Stack<ListNode> stk = new Stack<>();
        current = list;
        int currentPtr = 0;
        while (current != null){
            currentPtr++;
            if (!isEven && currentPtr == midPoint){
                current = current.next;
                continue;
            }
            if (currentPtr > midPoint){
                ListNode poppedNode = stk.pop();
                if (poppedNode.data != current.data) return false;
            }else{
                stk.add(current);
            }
            current = current.next;
        }
        return stk.isEmpty();
    }
}
