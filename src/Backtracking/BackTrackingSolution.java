package Backtracking;

import org.w3c.dom.ls.LSInput;

import java.util.*;

class BackTrackingMain {
    public static void main(String[] args) {
        BackTrackingSolution sol = new BackTrackingSolution();

        int[] arr = {2, 5, 6, 9};

        System.out.println(sol.subsequences("abc"));
    }
}


public class BackTrackingSolution {

    // LC78. Subsets, NC Subsets
    List<List<Integer>> subsets = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        findAllSubsets(nums, 0, new ArrayList<>());
        return subsets;
    }

    private void findAllSubsets(int[] nums, int index, List<Integer> subList) {

        if (index == nums.length) {
            subsets.add(new ArrayList<>(subList));
            return;
        }

        // pick
        subList.add(nums[index]);
        findAllSubsets(nums, index + 1, subList);
        subList.remove(subList.size() - 1);

        // no pick
        findAllSubsets(nums, index + 1, subList);
    }

    // NC Generate Parentheses
    List<String> parentheses = new ArrayList<String>();

    public List<String> generateParenthesis(int n) {
        generateAllValues("", 0, 0, n);
        return parentheses;
    }

    private void generateAllValues(String currentStr, int left, int right, int n) {
        if (currentStr.length() == n * 2) {
            parentheses.add(currentStr);
            return;
        }
        if (left < n) {
            currentStr += '(';
            generateAllValues(currentStr, left + 1, right, n);
        }
        if (left > right) {
            currentStr += ')';
            generateAllValues(currentStr, left, right + 1, n);
        }
    }

    // Permutations
    List<List<Integer>> permutations = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        findALLPermutations(nums, 0);
        return permutations;
    }

    private void findALLPermutations(int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> subList = new ArrayList<>();
            for (int i : nums) subList.add(i);
            permutations.add(new ArrayList<>(subList));
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            findALLPermutations(nums, index + 1);

            // backtrack
            swap(nums, index, i);
        }
    }

    void swap(int[] nums, int u, int v) {
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
        findLetterCombinations(digits, new StringBuilder(), 0, letterCombinations, numberMap);
        return letterCombinations;

    }

    private void findLetterCombinations(String digits, StringBuilder cur, int index, List<String> combinations, HashMap<Character, String> map) {
        if (index == digits.length()) {
            combinations.add(cur.toString());
            return;
        }

        String currentLetterStr = map.get(digits.charAt(index));
        for (int i = 0; i < currentLetterStr.length(); i++) {
            cur.append(currentLetterStr.charAt(i));
            findLetterCombinations(digits, cur, index + 1, combinations, map);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    // LC 39: Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinationSum = new ArrayList<>();
        calculateCombinationSum(candidates, 0, target, new ArrayList<>(), combinationSum, 0);
        return combinationSum;
    }

    private void calculateCombinationSum(int[] candidates, int sum, int target, List<Integer> currentList, List<List<Integer>> combinationSum, int index) {
        if (sum == target) {
            // add n return
            combinationSum.add(new ArrayList<>(currentList));
            return;
        } else if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            currentList.add(candidates[i]);
            calculateCombinationSum(candidates, sum, target, currentList, combinationSum, i);
            sum -= candidates[i];
            currentList.remove(currentList.size() - 1);
        }
    }

    // LC 39: Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> sol = new ArrayList<>();
        Arrays.sort(candidates);
        calculateCombinationSumII(candidates, target, 0, 0, new ArrayList<>(), sol);
        return sol;
    }

    private void calculateCombinationSumII(int[] candidates, int target, int index, int curSum, List<Integer> curList, List<List<Integer>> sol) {

        if (curSum == target) {
            // add n return
            sol.add(new ArrayList<>(curList));
            return;
        } else if (curSum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            curSum += candidates[i];
            curList.add(candidates[i]);
            calculateCombinationSumII(candidates, target, i + 1, curSum, curList, sol);
            curSum -= candidates[i];
            curList.remove(curList.size() - 1);
        }
    }


    public int optimalStrategyOfGame(int[] coins, int n) {
        // Write your code here.
        int start = 0;
        int end = n - 1;
        int maxAmount = Math.max(collectMaxAmount(coins, start + 1, end, coins[start]), collectMaxAmount(coins, start, end - 1, coins[end]));
        return maxAmount;
    }

    public static int collectMaxAmount(int[] coins, int start, int end, int sum) {
        // base case
        if (start >= end) return sum;

        // Ninjax will collect coin
        if (coins[start] > coins[end]) {
            start++;
        } else {
            end--;
        }

        int pickLeft = collectMaxAmount(coins, start + 1, end, sum + coins[start]);
        int pickRight = collectMaxAmount(coins, start, end - 1, sum + coins[end]);

        return Math.max(pickRight, pickLeft);
    }

    // subset II
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> sol = new ArrayList<>();
        Arrays.sort(nums);

        findAllSubsets(nums, 0, new ArrayList<>(), sol);
        return sol;
    }

    private void findAllSubsets(int[] nums, int start, List<Integer> subSet, List<List<Integer>> sol) {

        sol.add(new ArrayList<>(subSet));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;

            subSet.add(nums[i]);
            findAllSubsets(nums, start + 1, subSet, sol);
            subSet.remove(subSet.size() - 1);
        }
    }


    public boolean exist(char[][] board, String word) {

        int row = board.length;
        int col = board[0].length;

        int visited[][] = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == word.charAt(0)) {
                    boolean res = findWordBacktrack(board, visited, word, 0, r, c);
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean findWordBacktrack(char[][] board, int[][] visited, String word, int index, int row, int col) {
        if (index == word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        if (visited[row][col] != 0) return false;
        if (word.charAt(index) != board[row][col]) return false;

        visited[row][col] = 1;
        // up
        boolean up = findWordBacktrack(board, visited, word, index + 1, row - 1, col);

        // right
        boolean right = findWordBacktrack(board, visited, word, index + 1, row, col + 1);

        //down
        boolean down = findWordBacktrack(board, visited, word, index + 1, row + 1, col);

        // left
        boolean left = findWordBacktrack(board, visited, word, index + 1, row, col - 1);

        visited[row][col] = 0;

        return up || right || down || left;
    }


    // Subsequences of String
    public ArrayList<String> findSubsequence(String mainStr, String subStr){
        ArrayList<String> sol = new ArrayList<>();
        findSubsequenceBT(mainStr, subStr, 0, new StringBuilder(), sol);
        return sol;
    }

    private void findSubsequenceBT(String mainStr, String subStr, int index, StringBuilder cur, List<String> sol){
        if (index>=mainStr.length()){
            if (subStr.contains(cur.toString()) && !cur.isEmpty()){
                sol.add(cur.toString());
            }
            return;
        }

        // pick
        cur.append(mainStr.charAt(index));
        findSubsequenceBT(mainStr, subStr, index+1, cur, sol);

        // backtrack
        // no pick
        cur.deleteCharAt(cur.length()-1);
        findSubsequenceBT(mainStr, subStr, index+1, cur, sol);
    }

    public static ArrayList<String> subsequences(String str) {
        // Write your code here
        ArrayList<String> sol = new ArrayList<>();
        findAllSubsequences(str, 0, new StringBuilder(), sol);
        return sol;
    }

    private static void findAllSubsequences(String str, int index, StringBuilder builder, ArrayList<String> sol){
        if (index >= str.length()){
            if (!builder.isEmpty()){
                sol.add(builder.toString());
            }
            return;
        }

        builder.append(str.charAt(index));
        findAllSubsequences(str, index+1, builder, sol);

        // backtrack
        builder.deleteCharAt(builder.length() -1);
        findAllSubsequences(str, index+1, builder, sol);
    }






















}
