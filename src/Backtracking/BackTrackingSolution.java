package Backtracking;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class BackTrackingMain{
    public static void main(String[] args) {

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
}
