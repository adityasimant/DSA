package core;


import java.util.*;
import java.util.List;




class LinkedNode{
    int data;
    LinkedNode next;

    LinkedNode(int data){
        this.data = data;
    }
}

public class SinglyLinkedList {

    private LinkedNode head;
    private LinkedNode tail;
    int size;
    SinglyLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    public int get(int i){
        if (head == null) return -1;
        LinkedNode node = head;
        int nodeCounter = 0;
        while (node != null){
            if(nodeCounter == i){
                return node.data;
            }
            nodeCounter++;
            node = node.next;
        }
        return -1;
    }

    public ArrayList<Integer> getValues(){
        ArrayList<Integer> list = new ArrayList<>();
        if (head == null) return list;
        LinkedNode node = head;
        int nodeCounter = 0;
        while (node != null){
            list.add(node.data);

            nodeCounter++;
            node = node.next;
        }
        return list;
    }

    public void insertHead(int val){
        insertNode(val, false);
    }

    public void insertTail(int val){
        insertNode(val, true);
    }

    public boolean remove(int i){
        LinkedNode prev = null;
        if (head == null) return false;
        LinkedNode node = head;
        int nodeCounter = 0;
        while (node != null){
            if(nodeCounter == i){
                if (prev==null){
                    if (head == tail){
                        head = null;
                        tail = null;
                    }else {
                        head = head.next;
                    }
                }else {
                    if(node == tail){
                        tail = prev;
                    }
                    if (node == head){
                        head = node.next;
                    }
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            nodeCounter++;
            prev = node;
            node = node.next;
        }

        return false;
    }

    private void insertNode(int val, boolean insertTail){
        LinkedNode currentNode = new LinkedNode(val);
        if (head == null){
            head = currentNode;
            tail = head;
        }else{
            if (insertTail){
                tail.next = currentNode;
                tail = currentNode;
            }else {
                currentNode.next = head;
                head = currentNode;
            }
        }
        size++;
    }
}

class LinkedMain{
    public static void main(String[] args) {
//        SinglyLinkedList list =  new SinglyLinkedList();
//        list.insertTail(1);
//        list.insertTail(2);
//        System.out.println(list.get(1));
//        list.remove(1);
//        System.out.println(Arrays.toString(list.getValues()));
//        list.insertTail(2);
//        System.out.println(list.get(1));
//        System.out.println(list.get(0));
//        System.out.println(Arrays.toString(list.getValues()));
    }
}
