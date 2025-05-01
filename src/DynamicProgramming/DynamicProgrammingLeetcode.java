package DynamicProgramming;

import java.util.*;

public class DynamicProgrammingLeetcode {
    public static void main(String[] args) {

        DpOptimisedSolution sol = new DpOptimisedSolution();
        int[] arr = {1, 5, 10};
        System.out.println( sol.tribonacci(5));
    }

    public static int findFibo(int n, int[] memo) {
        if (n < 2) return n;
        if (memo[n] != -1) return memo[n];

        int ans = findFibo(n - 1, memo) + findFibo(n - 2, memo);
        memo[n] = ans;

        return ans;
    }
}

class MainSolution {

    public int climbStairs(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return climbStairsRecurr(n, map);
    }

    private int climbStairsRecurr(int n, HashMap<Integer, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        if (n == 2) return 2;
        if (n == 1) return 1;
        int recurSolution = climbStairsRecurr(n - 1, map) + climbStairsRecurr(n - 2, map);
        map.put(n, recurSolution);
        return recurSolution;
    }

//    public int minCostClimbingStairs(int[] cost) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        return minCostRecurr(cost.length, cost, map);
//    }
//
//    private static int minCostRecurr(int n,int[] cost, HashMap<Integer, Integer> map){
//        if (map.containsKey(n)) return map.get(n);
//        if (n == 1 || n==0) return 0;
//        int oneStep = minCostRecurr(n-1, cost, map) + cost[n-1];
//        int secondStep = minCostRecurr(n-2, cost, map) + cost[n-2];
//
//        int result = Math.min(oneStep, secondStep);
//        map.put(n, result);
//
//        return result;
//    }

    // 509. Fibonacci Number
    static HashMap<Integer, Integer> map = new HashMap<>();

    public int fib(int n) {
        if (n <= 1) return n;
        int prev1 = 0;
        int prev2 = 1;
        int ans = 0;
        for (int currentState = 2; currentState < n; currentState++) {
            ans = prev1 + prev2;
            prev1 = prev2;
            prev2 = ans;
        }
        return ans;
    }

    private int recurrFibo(int n) {
        if (n <= 1) return n;
        if (map.containsKey(n)) return map.get(n);
        int res = recurrFibo(n - 1) + recurrFibo(n - 1);
        map.put(n, res);
        return res;
    }

    // 198. House Robber
    public int rob(int[] nums) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        return robRecurr(nums, nums.length - 1, memo);
    }

    private int robRecurr(int[] nums, int n, HashMap<Integer, Integer> memo) {
        if (map.containsKey(n)) return map.get(n);
        if (n < 0) return 0;
        int pick = nums[n] + robRecurr(nums, n - 2, memo);
        int noPick = robRecurr(nums, n - 1, memo);
        int ans = Math.max(pick, noPick);
        memo.put(n, ans);
        return ans;
    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        Integer[][] dp = new Integer[n][maxWeight + 1];
        return findMaxWeight(weight, value, n, maxWeight, 0, 0, dp);
    }

    static int findMaxWeight(int[] weight, int[] value, int n, int maxWeight, int index, int currWeight, Integer[][] dp) {
        if (index == n) return 0;

        if (dp[index][currWeight] != null) return dp[index][currWeight];

        int noPick = findMaxWeight(weight, value, n, maxWeight, index + 1, currWeight, dp);
        int pick = 0;
        if (currWeight + weight[index] <= maxWeight) {
            pick = value[index] + findMaxWeight(weight, value, n, maxWeight, index + 1, currWeight + weight[index], dp);
        }

        return dp[index][currWeight] = Math.max(pick, noPick);
    }


    public int minCostClimbingStairs(int[] cost) {
        int[] memo = new int[cost.length + 1];
        Arrays.fill(memo, -1);
        int jump1 = findMinCostRecur(cost, 0, 0, memo);
        int jump2 = findMinCostRecur(cost, 1, 0, memo);

        return Math.min(jump2, jump1);
    }

    int findMinCostRecur(int[] cost, int index, int jumpSum, int[] memo) {
        int n = cost.length;
        if (index >= n) return jumpSum;
        if (memo[index] != -1) return memo[index];

        int jump1 = findMinCostRecur(cost, index + 1, jumpSum + cost[index], memo);
        int jump2 = findMinCostRecur(cost, index + 2, jumpSum + cost[index], memo);

        memo[index] = Math.min(jump2, jump1);

        return memo[index];
    }

}


class DpOptimisedSolution {

    /*
    Climbing Stairs
    */

    /* Brute Force
    public int climbStairs(int nStairs) {
        return returnVariations(nStairs) ;
    }

    private int returnVariations(int n){
        if (n<2) return 1;
        return returnVariations(n-1) + returnVariations(n-2);
    }
    */

    /* top to bottom memoization
    public int climbStairs(int nStairs) {
        int dp[] = new int[nStairs+1];
        Arrays.fill(dp, -1);
        return returnVariations(nStairs, dp) ;
    }

    private int returnVariations(int n, int[] dp){
        if (n<2) return 1;
        if (dp[n] != -1) return dp[n];

        dp[n] = returnVariations(n-1, dp) + returnVariations(n-2, dp);
        return dp[n];
    }*/

    /* bottom-up
    public int climbStairs(int nStairs) {
        int dp[] = new int[nStairs+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i<=nStairs; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[nStairs];
    }*/

    public int climbStairs(int nStairs) {
        if (nStairs < 2) return 1;

        int prev2 = 1;
        int prev1 = 1;
        int current = 0;

        for (int i = 2; i <= nStairs; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

/*

    Coin Change

*/

    public int coinChange(int[] coins, int amount) {
        return findMinChange(coins, amount, 0, 0, 0);
    }

    private int findMinChange(int[] coins, int amount, int sum, int index, int transactionCount) {
        if (sum == amount) {
            return transactionCount;
        }
        if (sum > amount || index >= coins.length) {
            return -1;
        }

        int minChange = Integer.MAX_VALUE;
        for (int i = index; i < coins.length; i++) {
            sum += coins[i];
            int res = findMinChange(coins, amount, sum, i, transactionCount + 1);
            if (res != -1) {
                minChange = Math.min(res, minChange);
            }
            sum -= coins[i];
        }

        return minChange;
    }

    /*

        118. Pascal's Triangle

    */


    /* Brute Force
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>();

        List<Integer> subList = new ArrayList<>();
        subList.add(1);
        output.add(subList);
        generateAllRows(numRows, 1, subList, output);
        return output;
    }

    private void generateAllRows(int numRows, int index, List<Integer> currentList, List<List<Integer>> output){
        if (index == numRows){
            return;
        }

        List<Integer> rowList = new ArrayList<>();
        for (int i=0; i<currentList.size(); i++){
            if (i==0){
                rowList.add(1);
                continue;
            }
            rowList.add(currentList.get(i-1) + currentList.get(i));
        }
        rowList.add(1);

        output.add(rowList);
        generateAllRows(numRows, index+1, rowList, output);
    }*/

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>();

        List<Integer> subList = new ArrayList<>();
        subList.add(1);
        output.add(subList);
        generateAllRows(numRows, 1, subList, output);
        return output;
    }

    private void generateAllRows(int numRows, int index, List<Integer> currentList, List<List<Integer>> output){
        if (index == numRows){
            return;
        }

        List<Integer> rowList = new ArrayList<>();
        for (int i=0; i<currentList.size(); i++){
            if (i==0){
                rowList.add(1);
                continue;
            }
            rowList.add(currentList.get(i-1) + currentList.get(i));
        }
        rowList.add(1);

        output.add(rowList);
        generateAllRows(numRows, index+1, rowList, output);
    }

    public int[] countBits(int n) {
        int[] bits = new int[n+1];

        int oneCount = 0;
        int capacity = 1;

        bits[0] = 0;

        for (int i=1; i<=n; i++){
            String binary = Integer.toBinaryString(i);
            if (oneCount < capacity){
                // Just add one
                oneCount++;
            }else if(oneCount == capacity){
                // increase the capacity by 1
                // one count becomes 1;
                capacity++;
                oneCount = 1;
            }
            bits[i] = oneCount;
        }
        return bits;
    }

    public boolean isSubsequence(String s, String t) {

        int sStart = 0;
        int tStart = 0;

        while (sStart <= s.length()-1 && tStart<= t.length()-1){
            if (s.charAt(sStart) == t.charAt(tStart)){
                tStart++;
                sStart++;
            }else {
                tStart++;
            }
        }

        if (tStart < t.length()-1 || sStart < s.length()-1) return false;
        return true;
    }

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        int prev1 = 0;
        int prev2 = 1;
        int prev3 = 1;


        for (int state=3; state<=n; state++){
            int current = prev3 + prev2 + prev1;
            prev1 = prev2;
            prev2 = prev3;
            prev3 = current;
        }
        return prev3;
    }

    int getTribonacciNumber(int n, int[] dp){
        if (n<3){
            return dp[n];
        }
        if (dp[n] != -1) return dp[n];

        dp[n] =  getTribonacciNumber(n-1, dp) + getTribonacciNumber(n-2, dp) + getTribonacciNumber(n-3, dp);
        return dp[n];
    }

    // Maximum Product Subarray
    public int maxProduct(int[] nums) {

    }
}
