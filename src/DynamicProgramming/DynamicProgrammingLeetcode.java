package DynamicProgramming;

import java.util.HashMap;

public class DynamicProgrammingLeetcode {
    public static void main(String[] args) {
        MainSolution sol = new MainSolution();
        int[] arr = {1,100,1,1,1,100,1,1,100,1};
        int ans = sol.minCostClimbingStairs(arr);
        System.out.println(ans);
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

    public int minCostClimbingStairs(int[] cost) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return minCostRecurr(cost.length, cost, map);
    }

    private static int minCostRecurr(int n,int[] cost, HashMap<Integer, Integer> map){
        if (map.containsKey(n)) return map.get(n);
        if (n == 1 || n==0) return 0;
        int oneStep = minCostRecurr(n-1, cost, map) + cost[n-1];
        int secondStep = minCostRecurr(n-2, cost, map) + cost[n-2];

        int result = Math.min(oneStep, secondStep);
        map.put(n, result);

        return result;
    }

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
}
