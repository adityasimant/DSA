package GFG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class GFGmain {
    public static void main(String[] args) {
        GFGsolution sol = new GFGsolution();
        int[] input = {-8 ,-10 ,-10 ,-10, 10, 6 ,1 ,10};

        ArrayList<ArrayList<Integer>> ans = sol.getPairs(input);
        System.out.println(ans);
    }
}
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class GFGsolution {
    ArrayList<Integer> subarraySum(int[] arr, int target) {
        int start = 0;
        int sum = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];

            while (sum > target && start <= end) {
                sum -= arr[start];
                start++;
            }

            if (sum == target) {
                ans.add(start + 1);
                ans.add(end + 1);
                return ans;
            }
        }

        ans.add(-1);
        return ans;
    }

    int missingNumber(int arr[]) {
        // code here
        int N = arr.length;
        int totalSum = 0;
        int currentSum = 0;
        for (int i: arr){
            currentSum += i;
        }

        for (int i= 1; i<=N+1; i++){
            totalSum += i;
        }

        return totalSum-currentSum;
    }

    public List<Integer> findDuplicates(int[] arr) {
        // code here
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int i: arr){
            freqMap.put(i, freqMap.getOrDefault(i,0)+1);
        }
        List<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer, Integer> map : freqMap.entrySet()){
            if(map.getValue()>1) {
                ans.add(map.getKey());
            }
        }
        return ans;
    }

    public int kthSmallest(int[] arr, int k) {

        List<Integer> freqMap = new ArrayList<>();
        for (int i: arr){
            freqMap.add(i);
        }
        int count = 1;
        while(count<=k){
            int min = 0;
            if(!freqMap.isEmpty()){
                for(int i=0; i<freqMap.size(); i++){
                    if(freqMap.get(i) < freqMap.get(min)){
                        min = i;
                    }
                }

                if(count == k){
                    return freqMap.get(min);
                }
                freqMap.remove(min);
                count++;
            }else{
                return -1;
            }
        }
        return -1;
    }

    List<Integer> list = new ArrayList<>();

    boolean isBST(Node root) {
        inOrderDFS(root);
        for (int i=1; i< list.size(); i++){
            if (list.get(i)<list.get(i-1)){
                return false;
            }
        }
        return true;
    }

    public void inOrderDFS(Node root){
        if (root != null){
            inOrderDFS(root.left);
            list.add(root.data);
            inOrderDFS(root.right);
        }
    }

    static boolean isBalanced(String s) {
        // code here
        Stack<Character> stk = new Stack<>();
        for(char c : s.toCharArray()){

            if(c == '('){
                stk.push(')');

            }else if(c == '['){
                stk.push(']');

            }else if(c == '{'){
                stk.push('}');

            }else{
                if(stk.isEmpty() || stk.pop() != c){
                    return false;
                }
            }
        }
        return true;
    }


    static int inversionCount(int arr[]) {
        // Your Code Here
        int ansCount = 0;
        for(int i=0; i<arr.length; i++){
            for (int j=i; j< arr.length; j++){
                if (i==j) continue;
                if (arr[i] > arr[j]){
                    ansCount++;
                }
            }
        }
        return ansCount;
    }


    public int longestSubarray(int[] arr, int k) {
        // code here
        int max = -1;
        for(int i=0; i<arr.length; i++){
            int sum =0;
            for (int j = i; j< arr.length; j++){
                sum += arr[j];
                if (sum == k){
                    max = Math.max(max, j - i +1);
                }
            }
        }
        return max;
    }

    // Left View of Binary Tree
    ArrayList<Integer> leftView(Node root) {
        // code here
        ArrayList<Integer> sol = new ArrayList<>();
        if(root == null) return sol;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentQueueSize = queue.size();
            for (int i=0; i<currentQueueSize; i++){
                Node currentNode = queue.poll();
                if (i==0) sol.add(currentNode.data);

                if (currentNode.left!=null) queue.offer(currentNode.left);
                if (currentNode.right!=null) queue.offer(currentNode.right);
            }
        }
        return sol;
    }

    // Tree Boundary Traversal
    ArrayList<Integer> boundaryTraversal(Node root) {
        // code here
        ArrayList<Integer> sol = new ArrayList<>();
        sol.add(root.data);
        leftPreOrderTrav(root.left, sol, true);
        rightPostOrderTrav(root.right, sol, true);
        return sol;
    }

    public void leftPreOrderTrav(Node root, ArrayList<Integer> list, boolean isLeft){
        if(root != null){
            if(root.left == null  && root.right == null ) {
                list.add(root.data);
                return;
            }
            if (isLeft) list.add(root.data);

            leftPreOrderTrav(root.left, list, true);

            if (root.left == null){
                leftPreOrderTrav(root.right, list, true);
            }else{
                leftPreOrderTrav(root.right, list, false);
            }
        }
    }
    public void rightPostOrderTrav(Node root, ArrayList<Integer> list, boolean isRight){
        if(root != null){
            if(root.left == null  && root.right == null ) {
                list.add(root.data);
                return;
            }
            if (root.right == null){
                rightPostOrderTrav(root.left, list, true);
            }else{
                rightPostOrderTrav(root.left, list, false);
            }
            if (root.left == null){
                rightPostOrderTrav(root.right, list, false);
            }else{
                rightPostOrderTrav(root.right, list, true);
            }
            if (isRight) list.add(root.data);
        }
    }

    // Balanced Tree Check
    public boolean isBalanced(Node root) {
        // code here
        if (root == null ) return true;
        int rightDepth = 1;
        int leftDepth = 1;
        int leftMaxDepth = calculateTreeDepth(root.left);
        int rightMaxDepth = calculateTreeDepth(root.right);
        int difference = Math.abs(leftMaxDepth - rightMaxDepth);
        return difference < 2;
    }
    int calculateTreeDepth(Node root){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + Math.max(calculateTreeDepth(root.left), calculateTreeDepth(root.right));
    }

    // Height of Binary Tree
    int height(Node node) {
        // code here
        return calculateTreeHeightRecur(node);
    }

    int calculateTreeHeightRecur(Node node){
        if (node == null) return 0;
        if (node.right == null && node.left == null) return 1;

        return 1 + Math.max(calculateTreeHeightRecur(node.left), calculateTreeHeightRecur(node.right));
    }

    // Identical Trees
    boolean isIdentical(Node r1, Node r2) {
        // Code Here
        if (r1 == null && r2 == null){
            return true;
        }
        if(r1.data != r2.data){
            return false;
        }

        String str = "dsdasd";


        return isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
    }

    // sum tree
    boolean isSumTree(Node root) {
        // Your code here
        return root.data == isSumTreeRecurr(root.right) + isSumTreeRecurr(root.left);
    }

    int isSumTreeRecurr(Node root){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.data;

        return isSumTreeRecurr(root.left) + isSumTreeRecurr(root.right);
    }

    // BFS of graph
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        ArrayList<Integer> sol = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int poppedElement = queue.poll();
            sol.add(poppedElement);
            for (int i : adj.get(poppedElement)){
                if (!visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
        return sol;
    }

    // Equilibrium point
    public static int findEquilibrium(int arr[]) {
        // code here
        int mainSum = 0;
        int currentSum = 0;
        int N = arr.length;
        for(int i: arr){
            mainSum+=i;
        }
        int tmpMainSum = mainSum;
        for (int i=0; i<N; i++){

            tmpMainSum -= arr[i];

            if (i != 0) currentSum += arr[i-1];
            if (tmpMainSum == currentSum) return i;
        }

        return -1;
    }


    public ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        // code here
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> computedSet = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr){
            if (computedSet.contains(i) || computedSet.contains(i*-1)){
                continue;
            }

            if (set.contains(i)){
                computedSet.add(i);
                if (i>=0){
                    queue.add(i);
                }else{
                    queue.add(i*-1);
                }
            }else{
                set.add(i * -1);
            }
        }
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
        while (!queue.isEmpty()){
            ArrayList<Integer> subList =  new ArrayList<>();
            int currentElement = queue.poll();

            subList.add(currentElement*-1);
            subList.add(currentElement);
            mainList.add(subList);

        }
        return mainList;
    }



    public boolean isValid(String s) {
        Stack<Character> chStack = new Stack<>();
        for (char ch : s.toCharArray()){
            if (ch == '(' ){
                chStack.push(')');
            }else if (ch == '[' ){
                chStack.push(']');
            }else if (ch == '{' ){
                chStack.push('}');
            }else{
                if (chStack.isEmpty() || chStack.pop() != ch) return false;
            }
        }
        return chStack.isEmpty();
    }
}
