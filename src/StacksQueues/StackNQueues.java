package StacksQueues;


import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

public class StackNQueues {
    public static void main(String[] args) {
        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();
        stk1.add(1);
        stk1.add(6);
        stk1.add(4);
        stk1.add(2);
        stk1.add(4);


        stk2.add(5);
        stk2.add(8);
        stk2.add(1);
        stk2.add(2);

        StksAndQueues s = new StksAndQueues();
        int ans = s.maximumValuesCanRemove(stk1, stk2, 10);
        System.out.println(ans);
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

//class Stack {
//    Stack (int capacity)
//    int size()
//    boolean isEmpty()
//    int top()
//    void push(int element)
//    void pop()
//}




class MinStack {
    Stack<Integer> stk = new Stack<>();
    int min;
    MinStack(){
        min = Integer.MAX_VALUE;
    }
    void push(int x) {
        if (x<min) min = x;
        stk.push(x);
    }
    void pop() {
        stk.pop();
    }
    int top() {
        return stk.peek();
    }
    int getMin() {
        return  min;
    }
}


class StksAndQueues {

    public int maximumValuesCanRemove(Stack<Integer> stk1, Stack<Integer> stk2, int target) {
        int count = 0;
        int currentSum = 0;

        while (!stk1.isEmpty() && !stk2.isEmpty()) {
            int currentSumStk1 = stk1.peek() + currentSum;
            int currentSumStk2 = stk2.peek() + currentSum;
            if (currentSumStk1 > target && currentSumStk2 <= target) {
                currentSum += stk2.pop();
                count++;
            }
            if (currentSumStk1 <= target && currentSumStk2 > target) {
                currentSum += stk1.pop();
                count++;
            }
            if (currentSumStk1 <= target && currentSumStk2 < target) {
                if (currentSumStk1 < currentSumStk2) {
                    currentSum += stk1.pop();
                    count++;
                } else {
                    currentSum += stk2.pop();
                    count++;
                }
            }
            if (currentSumStk1 >= target && currentSumStk2 >= target) break;
        }

        while (!stk1.isEmpty()) {
            if (currentSum + stk1.peek() < target) {
                currentSum += stk1.pop();
                count++;
            } else {
                break;
            }
        }

        while (!stk2.isEmpty()) {
            if (currentSum + stk2.peek() < target) {
                currentSum += stk2.pop();
                count++;
            } else {
                break;
            }
        }

        return count;
    }


    ListNode removekthElement(ListNode head, int k) {
        int nodeCounter = 0;
        ListNode itNode = head;
        ListNode prevNode = null;

        if (k==1){
            head = head.next;
            return head;
        }

        while (itNode != null) {
            nodeCounter++;
            if (nodeCounter == k) {
                prevNode.next = itNode.next;
            }else{
                prevNode = itNode;
            }
            itNode = itNode.next;
        }
        return head;
    }


    class Queue {

        int[] queueArray;
        int size;
        int capacity;
        int start;
        int rear;


        Queue (int capacity) {
            queueArray = new int[capacity];
            this.capacity = capacity;
            start =0;
            rear = -1;
            size =0;
        }

        boolean isEmpty() {
            return size == 0;
        }

        int size() {
            return  size;
        }

        int front() {
            if (isEmpty()){
                throw new IllegalStateException("Queue is empty");
            }
            return queueArray[start];
        }

        int back() {
            if (isEmpty()){
                throw new IllegalStateException("Queue is empty");
            }
            return queueArray[rear];
        }

        void push(int element) {
            if (size == capacity){
                throw  new IllegalStateException("Queue is full");
            }

            rear = rear +1;
            queueArray[rear] = element;
            size++;
        }

        void pop() {
            if (size == 0){
                throw  new IllegalStateException("Queue is empty");
            }
            start = start+1;
            size--;
        }
    }

    boolean isBalancedParentheses(String str) {

        Stack<Character> bracketStk = new Stack<>();
        for(int i=0; i<str.length(); i++){
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{'){
                if (str.charAt(i) == '('){
                    bracketStk.add(')');
                }
                if (str.charAt(i) == '['){
                    bracketStk.add(']');
                }
                if (str.charAt(i) == '{'){
                    bracketStk.add('}');
                }
            }else{
                if (bracketStk.isEmpty() || bracketStk.pop() != str.charAt(i) ){
                    return false;
                }
            }
        }
        return bracketStk.isEmpty();
    }

    // Car Fleet
    class Car{
        int speed;
        int position;

        public Car(int speed, int position){
            this.position = position;
            this.speed = speed;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        HashSet<Float> fleet = new HashSet<>();
        float prevTime = -1;
        ArrayList<Car> carList = new ArrayList<>();
        for(int i=0; i<position.length; i++){
            carList.add(new Car(speed[i], position[i]));
        }
        carList.sort(Comparator.comparingInt((Car car) -> car.position));
        for (int i=carList.size()-1; i>-1; i--){
            int currentPosition = carList.get(i).position;
            int currentSpeed = carList.get(i).speed;

            float timeToReach = (float) (target - currentPosition) /currentSpeed;
            if (timeToReach >= prevTime){
                fleet.add(timeToReach);
                prevTime = timeToReach;
            }
        }

        return fleet.size();
    }

}
