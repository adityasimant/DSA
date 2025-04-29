package Backtracking;

import org.w3c.dom.ls.LSInput;

import java.util.*;

class BackTrackingMain{
    public static void main(String[] args) {
        BackTrackingSolution sol = new BackTrackingSolution();

        int[] arr = {8, 10, 20, 7, 15, 15, 5, 10, 9, 18};

        System.out.println(sol.optimalStrategyOfGame(arr, arr.length));
    }
}



public class BackTrackingSolution {

    // LC78. Subsets, NC Subsets
    List<List<Integer>> subsets = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        findAllSubsets(nums, 0, new ArrayList<>());
        return subsets;
    }

    private void findAllSubsets(int[] nums, int index, List<Integer> subList){

        if (index == nums.length){
            subsets.add(new ArrayList<>(subList));
            return;
        }

        // pick
        subList.add(nums[index]);
        findAllSubsets(nums, index+1, subList);
        subList.remove(subList.size() - 1);

        // no pick
        findAllSubsets(nums, index+1, subList);
    }

    // NC Generate Parentheses
    List<String> parentheses = new ArrayList<String>();
    public List<String> generateParenthesis(int n) {
        generateAllValues("", 0,0, n);
        return parentheses;
    }

    private void generateAllValues(String currentStr, int left, int right, int n){
        if (currentStr.length() == n*2){
            parentheses.add(currentStr);
            return;
        }
        if (left < n){
            currentStr += '(';
            generateAllValues(currentStr, left+1, right, n);
        }
        if (left > right){
            currentStr += ')';
            generateAllValues(currentStr, left, right+1, n);
        }
    }

    List<List<Integer>> dupSubsets = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        findDupSubsets(nums, 0, new ArrayList<>());
        return dupSubsets;
    }

    private void findDupSubsets(int[] nums, int index, List<Integer> subList){
        if (index == nums.length){
            dupSubsets.add(new ArrayList<>(subList));
            return;
        }
        // pick
        subList.add(nums[index]);
        findDupSubsets(nums, index+1, subList);

        subList.remove(subList.size()-1);
        // no pick
        findDupSubsets(nums, index+1, subList);
    }

    // Permutations
    List<List<Integer>> permutations = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        findALLPermutations(nums, 0);
        return permutations;
    }

    private void findALLPermutations(int[] nums, int index){
        if (index == nums.length){
            List<Integer> subList = new ArrayList<>();
            for (int i: nums) subList.add(i);
            permutations.add(new ArrayList<>(subList));
        }

        for (int i=index; i< nums.length; i++){
            swap(nums, index, i);
            findALLPermutations(nums, index+1);

            // backtrack
            swap(nums, index, i);
        }
    }

    void swap(int[] nums, int u, int v){
        int tmp = nums[u];
        nums[u] = nums[v];
        nums[v] = tmp;
    }

    // LC 17: Letter Combinations of a Phone Number
    public List<String> letterCombinations(String digits) {

        HashMap<Character, String> numberMap = new HashMap<>();
        numberMap.put('2', "abc");
        numberMap.put('3', "def");
        numberMap.put('4', "ghi");
        numberMap.put('5', "jkl");
        numberMap.put('6', "mno");
        numberMap.put('7', "pqrs");
        numberMap.put('8', "tuv");
        numberMap.put('9', "wxyz");


        List<String> letterCombinations = new ArrayList<>();
        if (digits.isEmpty()) return letterCombinations;
        findLetterCombinations(digits, new StringBuilder(),0, letterCombinations, numberMap);
        return letterCombinations;

    }

    private void findLetterCombinations(String digits, StringBuilder cur, int index, List<String> combinations, HashMap<Character, String> map){
        if (index == digits.length()){
            combinations.add(cur.toString());
            return;
        }

        String currentLetterStr = map.get(digits.charAt(index));
        for (int i=0; i<currentLetterStr.length(); i++){
            cur.append(currentLetterStr.charAt(i));
            findLetterCombinations(digits, cur, index + 1, combinations, map);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    // LC 39: Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinationSum = new ArrayList<>();
        calculateCombinationSum(candidates, 0, target, new ArrayList<>(), combinationSum, 0);
        return combinationSum;
    }

    private void calculateCombinationSum(int[] candidates, int sum, int target, List<Integer> currentList , List<List<Integer>> combinationSum, int index){
        if (sum == target){
            // add n return
            combinationSum.add(new ArrayList<>(currentList));
            return;
        } else if (sum > target) {
            return;
        }

        for (int i=index; i<candidates.length; i++){
            sum += candidates[i];
            currentList.add(candidates[i]);
            calculateCombinationSum(candidates, sum, target, currentList, combinationSum, index);
            sum -= candidates[i];
            currentList.remove(currentList.size() -1);
        }
    }

    // LC 39: Combination Sum II
    static List<List<Integer>> combinationSum2 = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> cur = new ArrayList<>();
        for(int i: candidates) cur.add(i);
        findLetterCombinations2(candidates, target, 0, 0, cur);
        return combinationSum2;
    }

    private void findLetterCombinations2(int[] candidates, int target, int sum, int index, List<Integer> currentList){
        // Base condition
        if (sum == target) {
            combinationSum2.add(new ArrayList<>(currentList));
            return;
        }
        if (sum > target) return;

        for (int i = 0; i<candidates.length; i++){
            sum += currentList.get(i);
            currentList.remove(i);
            // call recur
            findLetterCombinations2(candidates, target, sum, index , currentList);
            // backtrack
            currentList.add(i, candidates[i]);
            sum -= currentList.get(i);
        }
    }


    public int optimalStrategyOfGame(int[] coins, int n) {
        // Write your code here.
        int start = 0;
        int end = n-1;
        int maxAmount = Math.max(collectMaxAmount(coins, start+1, end, coins[start]), collectMaxAmount(coins, start, end-1, coins[end]));
        return maxAmount;
    }

    public static int collectMaxAmount(int[] coins, int start, int end, int sum){
        // base case
        if (start>=end) return sum;

        // Ninjax will collect coin
        if (coins[start]>coins[end]){
            start++;
        }else {
            end--;
        }

        int pickLeft = collectMaxAmount(coins, start+1, end, sum+coins[start]);
        int pickRight = collectMaxAmount(coins, start, end-1, sum+coins[end]);

        return Math.max(pickRight, pickLeft);
    }

}
