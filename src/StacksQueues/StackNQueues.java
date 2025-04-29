package StacksQueues;


import java.nio.channels.FileLock;
import java.util.*;

public class StackNQueues {
    public static void main(String[] args) {
        StksAndQueues sol = new StksAndQueues();

        int[] nums = {2,1,5,6,2,3};
        System.out.println(sol.largestRectangleArea(nums));
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

    // 84. Largest Rectangle in Histogram

    // Brute Force
    /*public int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        for (int i=0; i<heights.length; i++){
            int minHeight = Integer.MAX_VALUE;
            int area = 0;
            for (int j = i; j<heights.length; j++){
                minHeight = Math.min(minHeight, heights[j]);
                area = (j - i +1) * minHeight;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }*/

    public int largestRectangleArea(int[] heights) {

        int N = heights.length;
        int[] ltr = new int[N];
        int[] rtl = new int[N];

        Stack<Integer> stk = new Stack<>();
        for (int i=0; i<N; i++){
            while (!stk.isEmpty() && heights[i] < heights[stk.peek()]){
                int index = stk.pop();
                ltr[index] = i - index;
            }
            stk.push(i);
        }
        stk.clear();

        for (int i=N-1; i>-1; i--){
            while (!stk.isEmpty() && heights[i] < heights[stk.peek()]){
                int index = stk.pop();
                rtl[index] = Math.abs(i - index);
            }
            stk.push(i);
        }
        int maxArea = 0;
        for (int i=0; i<heights.length; i++){
            int left = rtl[i] == 0 ? i : rtl[i] - 1;
            int right = ltr[i] == 0 ? (N-1-i) : (ltr[i]-1);
            int width = left + right + 1;
            int height = heights[i];
            int area = height * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
