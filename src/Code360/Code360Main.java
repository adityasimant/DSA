package Code360;

import java.util.*;

public class Code360Main extends Code360Solution {
    public static void main(String[] args) {

        int[] arr = {5, 5 ,10 ,100 ,10 ,5};
        int[] arr2 = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(maxMoneyLooted(arr));
    }
}

class Code360Solution {

    //  Maximum Subarray Sum
    public static long maxSubarraySum(int[] arr, int n) {
        // write your code here
        //ip: -7 -8 -16 -4 -8 -5 -7 -11 -10 -12 -4 -6 -4 -16 -10
        int currentSum = 0;
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // First Missing Positive
    public static int firstMissing(int[] arr, int n) {
        // Write your code here.
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) set.add(i);

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    // Count Ways To Reach The N-th Stairs
    public static long countDistinctWayToClimbStair(int nStairs) {
        // Write your code here.
        long[] arr = new long[nStairs + 1];
        Arrays.fill(arr, -1);
        return countClimbingWays(nStairs, arr);
    }

    private static long countClimbingWays(int n, long[] arr) {
        if (n < 2) return 1;
        if (arr[n] != -1) return arr[n];
        long ans = countClimbingWays(n - 1, arr) + countClimbingWays(n - 2, arr);
        arr[n] = ans;
        return ans;
    }

    public static boolean detectCycle(Node head) {
        //Your code goes here
        HashSet<Node> set = new HashSet<>();
        Node itNode = head;

        while (itNode != null) {
            if (set.contains(itNode)) return true;
            set.add(itNode);
            itNode = itNode.next;
        }

        return false;
    }

    public static int minimumJumps(int[] arr, int N) {
        // Write your code here
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -1);
        return minimumJumpsRecur(arr, 0, N, dp);
    }

    private static int minimumJumpsRecur(int[] arr, int currIndex, int N, int[] dp) {
        if (currIndex >= N - 1) {
            return 0;
        }

        int currentIndexElement = arr[currIndex];
        if (currentIndexElement == 0) {
            return -1;
        }
        if (dp[currIndex] != -1) {
            return dp[currIndex];
        }
        int minJump = Integer.MAX_VALUE;

        for (int i = 1; i <= currentIndexElement; i++) {
            int j = minimumJumpsRecur(arr, currIndex + i, N, dp);
            if (j == -1) continue;
            minJump = Math.min(minJump, 1 + j);
        }
        dp[currIndex] = (minJump == Integer.MAX_VALUE) ? -1 : minJump;
        return dp[currIndex];
    }


    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        int[][] dp = new int[w + 1][n + 1];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        return calculateMaxProfit(n, profit, weight, w, 0, dp);
    }

    private static int calculateMaxProfit(int n, int[] profit, int[] weight, int w, int index, int[][] dp) {
        if (index > n - 1) {
            dp[w][index] = 0;
            return dp[w][index];
        }

        if (dp[w][index] != -1) {
            return dp[w][index];
        }

        if (w - weight[index] >= 0) {
            int pick = profit[index] + calculateMaxProfit(n, profit, weight, w - weight[index], index, dp);
            int noPick = calculateMaxProfit(n, profit, weight, w, index + 1, dp);

            dp[w][index] = Math.max(pick, noPick);
            return dp[w][index];
        } else {
            return calculateMaxProfit(n, profit, weight, w, index + 1, dp);
        }
    }

    public static List<String> generateString(int N) {
        // Write your code here.
        List<String> sol = new ArrayList<>();
        generateStringRecur(0, N, new StringBuilder(), sol);
        return sol;
    }

    private static void generateStringRecur(int index, int N, StringBuilder curStr, List<String> sol) {
        if (curStr.length() >= N) {
            sol.add(curStr.toString());
            return;
        }

        if (index == 0 || curStr.charAt(index - 1) != '1') {
            // add 1
            curStr.append('1');
            generateStringRecur(index + 1, N, curStr, sol);

            // backtrack
            curStr.deleteCharAt(index);
        }
        // add 0
        curStr.append('0');
        generateStringRecur(index + 1, N, curStr, sol);
        curStr.deleteCharAt(curStr.length() - 1);
    }

    public static int calculateMinPatforms(int at[], int dt[], int n) {
        // Write your code here.
        if (n < 1) return 0;
        List<Train> trainList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Train train = new Train(at[i], dt[i]);
            trainList.add(train);
        }

        Collections.sort(trainList, (a, b) -> a.outTime - b.outTime);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int maxPlatforms = 0;
        for (Train train : trainList) {
            while (!queue.isEmpty() && train.inTime >= queue.peek()) {
                queue.poll();
            }
            queue.offer(train.outTime);
            maxPlatforms = Math.max(maxPlatforms, queue.size());
        }
        return maxPlatforms;
    }

    public static char kThCharaterOfDecryptedString(String s, Long k) {

        String str = "";
        long i = 0;
        long n = s.length();
        long len = 0;
        while (i < n) {
            StringBuilder a = new StringBuilder();

            while (i < n && Character.isLetter(s.charAt((int) i))) {
                a.append(s.charAt((int) i));
                i++;
            }
            StringBuilder b = new StringBuilder();

            while (i < n && Character.isDigit(s.charAt((int) i))) {
                b.append(s.charAt((int) i));
                i++;
            }
            long d = Long.parseLong(b.toString());
            long seglen = a.length() * d;

            if (k <= seglen) {
                return a.charAt((int) ((k - 1) % a.length()));
            } else {
                k -= seglen;
            }
        }

        return '\0';
    }



    public static int maxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w) {
        // Write your code here.
        int[][] dp = new int[n][w+1];
        for (int[] i: dp) {
            Arrays.fill(i, -1);
        }
        return findMaxProfit(values, weights, n, w, 0, dp);
    }

    private static int findMaxProfit(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int w, int index, int[][] dp){

        if (index >= n-1){
            dp[index][w] = 0;
            return dp[index][w];
        }

        if (dp[index][w] != -1) return dp[index][w];

        if (w - weights.get(index) >= 0){
            // pick or no pick
            int pick = values.get(index) + findMaxProfit(values, weights, n, w - weights.get(index), index+1 , dp);
            int noPick = findMaxProfit(values, weights, n, w, index+1, dp );

            dp[index][w] = Math.max(pick, noPick);
            return dp[index][w];
        }else{
            // no pick
            dp[index][w] = findMaxProfit(values, weights, n, w, index+1, dp );
            return dp[index][w];
        }
    }

    public static boolean validateBST(BinaryTreeNode root) {
        // Write your code here
        return checkPartialBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean checkPartialBST(BinaryTreeNode root, int min, int max){
        if (root == null) return true;
        if (root.data < min || root.data > max) return false;

        boolean right = checkPartialBST(root.right, root.data, max);
        boolean left = checkPartialBST(root.left, min, root.data);

        return right&&left;
    }

    public static int maxMoneyLooted(int[] houses) {
        //Your code goes here
        int[] dp = new int[houses.length+1];
        Arrays.fill(dp, -1);
        return robHouses(houses, houses.length-1, dp);
    }

    private static int robHouses(int[] houses, int index, int[] dp){
        if (index < 0){
            return 0;
        }
        if (index == 0){
            return houses[0];
        }
        if (dp[index] != -1){
            return dp[index];
        }

        int pick = houses[index] + robHouses(houses, index-2, dp);
        int notPick = robHouses(houses, index-1, dp);
        dp[index] = Math.max(pick, notPick);
        return dp[index];
    }

    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode n1, BinaryTreeNode n2) {
        // Write your code here.
        List<BinaryTreeNode> list1 = findParentList(n1);
        List<BinaryTreeNode> list2 = findParentList(n2);

        BinaryTreeNode lca = null;

        if (list1.isEmpty()){
            return n1;
        } else if (list2.isEmpty()) {
            return n2;
        }

        Collections.reverse(list1);
        Collections.reverse(list2);

        int i=0;
        while (i<list2.size() && i<list1.size()){
            if (list1.get(i) == list2.get(i)){
                lca = list1.get(i);
            }else{
                break;
            }
            i++;
        }

        return lca;
    }

    private static List<BinaryTreeNode> findParentList(BinaryTreeNode child){
        List<BinaryTreeNode> list = new ArrayList<>();
        while (child != null){
            list.add(child);
            child = child.parent;
        }
        return list;
    }
}


//class BinaryTreeNode<Integer> {
//    int data;
//    BinaryTreeNode<Integer> left;
//    BinaryTreeNode<Integer> right;
//
//    public BinaryTreeNode(int data) {
//        this.data = data;
//    }
//}

class BinaryTreeNode {

    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode parent;

    BinaryTreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }
}

class Train {
    int inTime;
    int outTime;

    Train(int inTime, int outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }
}


class Node {
    public int data;
    public Node next;

    Node() {
        this.data = 0;
        this.next = null;
    }

    Node(int data) {
        this.data = data;
        this.next = null;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
