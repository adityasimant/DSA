package Code360;

import java.util.Arrays;
import java.util.HashSet;

public class Code360Main {
    public static void main(String[] args) {
        int a = -7;
        System.out.println(Math.max(a, Integer.MIN_VALUE+a));
    }
}

class Code360Solution{

    //  Maximum Subarray Sum
    public static long maxSubarraySum(int[] arr, int n) {
        // write your code here
        //ip: -7 -8 -16 -4 -8 -5 -7 -11 -10 -12 -4 -6 -4 -16 -10
        int currentSum = 0;
        int maxSum = 0;

        for(int i=0; i<arr.length; i++){
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // First Missing Positive
    public static int firstMissing(int[] arr, int n) {
        // Write your code here.
        HashSet<Integer> set = new HashSet<>();
        for (int i: arr) set.add(i);

        for (int i=1; i<Integer.MAX_VALUE; i++){
            if (!set.contains(i)){
                return i;
            }
        }
        return -1;
    }

    // Count Ways To Reach The N-th Stairs
    public static long countDistinctWayToClimbStair(int nStairs) {
        // Write your code here.
        long[] arr = new long[nStairs+1];
        Arrays.fill(arr, -1);
        return countClimbingWays(nStairs, arr);
    }

    private static long countClimbingWays(int n, long[] arr){
        if (n < 2) return 1;
        if (arr[n] != -1) return arr[n];
        long ans = countClimbingWays(n-1, arr) + countClimbingWays(n-2, arr);
        arr[n] = ans;
        return ans;
    }
}