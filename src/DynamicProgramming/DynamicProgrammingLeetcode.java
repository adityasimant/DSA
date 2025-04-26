package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DynamicProgrammingLeetcode {
    public static void main(String[] args) {

        /* Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] memo = new int[n+1];
//        Arrays.fill(memo, -1);
//        System.out.println(findFibo(n, memo));
        memo[0] = 0;
        memo[1] = 1;
        for (int i=2; i<=n; i++){
            memo[i] = memo[i-1] + memo[i-2];
        }
        System.out.println(memo[n]);
    }
    public static int findFibo(int n, int[] memo){
        if (n<2) return n;
        if (memo[n] != -1) return memo[n];

        int ans = findFibo(n-1, memo) + findFibo(n-2, memo);
        memo[n] = ans;

        return ans;
    }
}

class MainSolution{

    public int climbStairs(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return  climbStairsRecurr(n, map);
    }

    private int climbStairsRecurr(int n, HashMap<Integer, Integer> map){
        if (map.containsKey(n)) return  map.get(n);
        if(n == 2) return 2;
        if(n == 1) return 1;
        int recurSolution = climbStairsRecurr(n-1, map) + climbStairsRecurr(n-2, map);
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
        if (n<=1) return n;
        int prev1 = 0;
        int prev2 = 1;
        int ans = 0;
        for (int currentState=2; currentState<n; currentState++){
            ans = prev1 + prev2;
            prev1 = prev2;
            prev2 = ans;
        }
        return ans;
    }

    private int recurrFibo(int n){
        if (n<=1) return n;
        if (map.containsKey(n)) return map.get(n);
        int res = recurrFibo(n-1) + recurrFibo(n-1);
        map.put(n, res);
        return res;
    }

    // 198. House Robber
    public int rob(int[] nums) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        return robRecurr(nums, nums.length - 1, memo);
    }

    private int robRecurr(int[] nums, int n, HashMap<Integer, Integer> memo){
        if (map.containsKey(n) ) return map.get(n);
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
        int[] memo = new int[cost.length+1];
        Arrays.fill(memo, -1);
        int jump1 = findMinCostRecur(cost,0, 0, memo);
        int jump2 = findMinCostRecur(cost,1, 0, memo);

        return Math.min(jump2, jump1);
    }

    int findMinCostRecur(int[] cost, int index, int jumpSum, int[] memo){
        int n = cost.length;
        if (index>=n) return jumpSum;
        if(memo[index] != -1) return memo[index];

        int jump1 = findMinCostRecur(cost, index+1, jumpSum+cost[index], memo);
        int jump2 = findMinCostRecur(cost, index+2, jumpSum+cost[index], memo);

        memo[index] = Math.min(jump2, jump1);

        return memo[index];
    }





}
