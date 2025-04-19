package HashTable;
import java.util.*;

public class HashTable {
    public static void main(String[] args) {
        HashTableSolution solution = new HashTableSolution();
        String[] n1 = {"d","b","c","b","c","a"};

        boolean ans = solution.checkAlmostEquivalent("abcdeef","abaaacc" );
        System.out.println(ans);
    }
}


class HashTableSolution{

    // 2032. Two Out of Three
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        HashSet<Integer> num1Set = new HashSet<>();
        HashSet<Integer> num2Set = new HashSet<>();
        HashSet<Integer> num3Set = new HashSet<>();

        for (int i : nums1){
            num1Set.add(i);
        }
        for (int i : nums2){
            num2Set.add(i);
        }
        for (int i : nums3){
            num3Set.add(i);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i: num2Set){
            map.put(i, map.getOrDefault(i, 0) +1);
        }
        for(int i: num1Set){
            map.put(i, map.getOrDefault(i, 0) +1);
        }
        for(int i: num3Set){
            map.put(i, map.getOrDefault(i, 0) +1);
        }

        List<Integer> sol = new ArrayList<>();
        for (Map.Entry<Integer, Integer> i: map.entrySet()){
            if (i.getValue() > 1) sol.add(i.getKey());
        }
        return sol;
    }

    // 2053. Kth Distinct String in an Array
    public String kthDistinct(String[] arr, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String i: arr){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (int i=0; i<arr.length; i++){
            if (map.get(arr[i]) == 1) list.add(arr[i]);
        }
        if(list.size() < k) {
            return "";
        }
        else {
            return list.get(k-1);
        }
    }

    //  1995. Count Special Quadruplets
    public int countQuadruplets(int[] nums) {
        return 0;
    }

    // 2068. Check Whether Two Strings are Almost Equivalent
    public boolean checkAlmostEquivalent(String word1, String word2) {
        HashMap<Character, Integer> word1Map  = new HashMap<>();
        HashMap<Character, Integer> word2Map  = new HashMap<>();

        for (char i : word1.toCharArray()){
            word1Map.put(i, word1Map.getOrDefault(i, 0) + 1);
        }
        for (char i : word2.toCharArray()){
            word2Map.put(i, word2Map.getOrDefault(i, 0) + 1);
        }

        StringBuilder str = new StringBuilder();
        str.append(word1);
        str.append(word2);

        HashSet<Character> charSet = new HashSet<>();
        for (char i: str.toString().toCharArray()){
            charSet.add(i);
        }

        for (char i : charSet){
            int word1Count = word1Map.getOrDefault(i,0);
            int word2Count = word2Map.getOrDefault(i, 0);

            int difference = word2Count - word1Count;
            if(difference < -3 || difference >3) return false;
        }
        return true;
    }
}
