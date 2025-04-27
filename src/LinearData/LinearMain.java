package LinearData;

import java.lang.reflect.Type;
import java.util.*;

public class LinearMain {
    public static void main(String[] args) {


    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class LinearSolution {
    public static int NthRoot(int n, int m) {
        int low = 1, high = m;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midPow = multiply(mid, n, m);
            if (midPow == 1) {
                return mid;
            } else if (midPow == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Helper function
    private static int multiply(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) {
                return 2;  // mid^n > m
            }
        }
        if (ans == m) return 1; // mid^n == m
        return 0;               // mid^n < m
    }

    public int maxScore(String s) {
        int leftCounter = 0;
        int rightCounter = 0;
        int maxScore = 0;

        for (char c : s.toCharArray()) {
            if (c == '1') rightCounter++;
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftCounter++;
            } else if (s.charAt(i) == '1') {
                rightCounter--;
            }

            maxScore = Math.max(leftCounter + rightCounter, maxScore);
        }
        return maxScore;
    }


    public int pivotInteger(int n) {
        int fullSum = 0;

        for (int i = 1; i <= n; i++) {
            fullSum += i;
        }

        int tmpSum = 0;
        int ans = -1;

        for (int i = 1; i <= n; i++) {
            fullSum -= i;
            if (fullSum == tmpSum) {
                ans = i;
            }
            tmpSum += i;
        }
        return ans;
    }


    public int maxScore(int[] cardPoints, int k) {
        int N = cardPoints.length;
        int totalSum = 0;
        for (int i : cardPoints) totalSum += i;

        if (N == k) return totalSum;

        int windowSize = N - k;
        int windowSum = 0;

        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }

        int minWindowSum = windowSum;

        for (int i = windowSize; i < N; i++) {
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        return totalSum - minWindowSum;
    }

    class NumsCount {
        int num;
        int count;

        NumsCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int[] sol;
        PriorityQueue<NumsCount> numsFreq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.count, p1.count));
        for (int i : nums) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> map : freqMap.entrySet()) {
            numsFreq.add(new NumsCount(map.getKey(), map.getValue()));
        }

        if (numsFreq.size() < k) {
            sol = new int[1];
            sol[0] = -1;
            return sol;
        }
        sol = new int[k];
        for (int i = 0; i < k; i++) {
            sol[i] = numsFreq.poll().num;
        }
        return sol;
    }

    // Binary Search
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + end / 2;

            if (nums[mid] == target) return mid;
            else if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            }
        }

        return -1;
    }

    //  Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;
            if (matrix[mid][0] == target) return true;
            else if (matrix[mid][0] < target) {
                start = mid + 1;
            } else if (matrix[mid][0] > target) {
                end = mid - 1;
            }
        }

        int toFindIndex = mid;
        start = 0;
        end = matrix[toFindIndex].length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (matrix[toFindIndex][mid] == target) return true;
            else if (matrix[toFindIndex][mid] < target) {
                start = mid + 1;
            } else if (matrix[toFindIndex][mid] > target) {
                end = mid - 1;
            }
        }

        return false;
    }

    // Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid - 1;
            }
        }
        return end;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode nodePtr1 = list1;
        ListNode nodePtr2 = list2;
        ListNode solList = new ListNode(-1);

        ListNode current = solList;
        System.out.println("Pre Loop");
        printList(current);

        while (nodePtr1 != null && nodePtr2 != null) {
            if (nodePtr2.val > nodePtr1.val) {
                current.next = nodePtr1;
                current = current.next;

                nodePtr1 = nodePtr1.next;
            } else {
                current.next = nodePtr2;
                current = current.next;

                nodePtr2 = nodePtr2.next;
            }
        }
        System.out.println("Post Loop");
        printList(current);

        if (nodePtr2 != null) {
            solList.next = nodePtr2;
        }
        if (nodePtr1 != null) {
            current.next = nodePtr1;
        }
        return solList.next;
    }

    public void printList(ListNode node) {
        ListNode tmpNode = node;

        while (tmpNode != null) {
            System.out.print(tmpNode.val + " ");
            tmpNode = tmpNode.next;
        }
        System.out.println();
    }

    // Linked List Cycle Detection
    public boolean hasCycle(ListNode head) {
        int counter = 1;
        HashSet<ListNode> positionMap = new HashSet<>();
        ListNode itNode = head;
        while (itNode != null) {
            if (positionMap.contains(itNode)) {
                return true;
            } else {
                positionMap.add(itNode);
            }
            itNode = itNode.next;
        }

        return false;
    }

    // Reverse Linked List
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        ListNode next = current.next;

        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if (next != null) next = next.next;
        }
        return prev;
    }

    // Remove Node From End of Linked List
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int counter = 0;
        ListNode itNode = head;
        while (itNode != null) {
            counter++;
            itNode = itNode.next;
        }

        int toRemoveElement = counter - n + 1;
        ListNode prevNode = null;
        counter = 0;
        itNode = head;
        if (toRemoveElement == 1) {
            head = head.next;
            return head;
        }

        while (itNode != null) {
            counter++;
            if (toRemoveElement == counter) {
                prevNode.next = itNode.next;
                return head;
            }
            prevNode = itNode;
            itNode = itNode.next;
        }
        return head;
    }

    // Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }

    // Find the Duplicate Number
    public int findDuplicate1(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        for (int i : nums) {
            if (numSet.contains(i)) return i;
            else numSet.add(i);
        }
        return -1;
    }

    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

        }
        return -1;
    }

    // LRU Cache
    class Cache {
        int key;
        int value;
        int transactions;

        Cache(int key, int value, int transactions) {
            this.key = key;
            this.value = value;
            this.transactions = transactions;

        }

        public void incrementTransaction() {

        }
    }

    class LRUCache {

        public LRUCache(int capacity) {

        }

        public int get(int key) {
            return 0;
        }

        public void put(int key, int value) {

        }
    }

    // Search in Rotated Sorted Array
    public int search1(int[] nums, int target) {


        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;


            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    // search in sorted array (left)
                    end = mid - 1;
                } else {
                    // search in un sorted part
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    // search in sorted array (right)
                    start = mid + 1;
                } else {
                    // search in un sorted part
                    end = mid - 1;
                }
            }

        }
        return -1;
    }


    public static class KeyValueStore {
        // key
        String key;
        // default value to return if key is not found
        String defaultValue;
        int defaultTSTMP;

        // timestamp, value
        HashMap<Integer, String> timestampMap;

        KeyValueStore() {
            timestampMap = new HashMap<>();
            key = "";
            defaultValue = "";
            defaultTSTMP = -1;
        }

        public void setData(String key, String value, int timeStamp) {
            this.key = key;
            this.defaultValue = value;
            this.defaultTSTMP = timeStamp;
            timestampMap.put(timeStamp, value);
        }

        public String getKey() {
            return this.key;
        }

        public String getData(String key, int timeStamp) {
            if (!key.equals(this.key)) return "";
            if (!timestampMap.containsKey(timeStamp)) {
                if (defaultTSTMP <= timeStamp) return defaultValue;
                for (Map.Entry<Integer, String> map : timestampMap.entrySet()) {
                    if (map.getKey() <= timeStamp) return map.getValue();
                }
                return "";
            }
            return timestampMap.get(timeStamp);
        }
    }

    class TimeMap {
        KeyValueStore ky;

        public TimeMap() {
            ky = new KeyValueStore();
        }

        public void set(String key, String value, int timestamp) {
            ky.setData(key, value, timestamp);
        }

        public String get(String key, int timestamp) {
            return ky.getData(key, timestamp);
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double med1 = getMedian(nums1);
        System.out.println("nums1: " + Arrays.toString(nums1) + " res " + med1);
        double med2 = getMedian(nums2);
        System.out.println("nums2: " + Arrays.toString(nums2) + " res " + med2);

        return (double) (med1 + med2) / 2;
    }

    private double getMedian(int[] arr) {
        int len = arr.length;
        if (len == 1) return arr[0];
        int mid = (len / 2) - 1;
        double median;
        if ((arr.length % 2) == 0) {
            median = (double) (arr[mid] + arr[mid + 1]) / 2;
        } else {
            median = (double) arr[mid] / 2;
        }
        return median;
    }

    // Maximum Subarray Sum
    public static long maxSubarraySum(int[] arr, int n) {
        // write your code here
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(sum, max);
            }
            sum = 0;
        }

        return max;
    }

    // Maximum Subarray
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // Jump Game
    /*
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;

        int counter = 0;
        for (int i = nums.length-2; i>-1; i--){
            counter++;
            if (counter <= nums[i]){
                counter = 0;
            }
        }
        return counter==0;
    }
     */
    public boolean canJump(int[] nums) {
        return canJumpToIndex(nums, 0);
    }

    private boolean canJumpToIndex(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true;
        }
        if (nums[index] == 0) return false;
        for (int i = 1; i <= nums[index]; i++) {
            boolean res = canJumpToIndex(nums, index + i);
            if (res) return res;
        }
        return false;
    }


    /*
     *  Jump Game II
     * */

    /*
    public int jump(int[] nums) {
        return finMinJumps(nums, 0, 0);
    }

    private int finMinJumps(int[] nums, int index, int jumps){
        if (index>=nums.length-1){
            return jumps;
        }
        if (nums[index] == 0) return -1;
        int minJump = Integer.MAX_VALUE;
        for(int i=1; i<=nums[index]; i++){
            int sol = finMinJumps(nums, index+i, jumps+1);
            if (sol != -1){
                minJump = Math.min(minJump, sol);
            }
        }
        return minJump;
    }
    */
    public int jump(int[] nums) {
        int farthest = 0;
        int currentEnd = 0;
        int jumps = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }

    // 2144. Minimum Cost of Buying Candies With Discount
    public int minimumCost(int[] cost) {
        int buyingCost = 0;
        Arrays.sort(cost);
        int index = cost.length - 1;
        int count = 0;
        while (index >= 0) {
            count++;
            if (count != 3) {
                buyingCost += cost[index];
            } else {
                count = 0;
            }
            index--;
        }
        return buyingCost;
    }

    public int countLargestGroup(int n) {
        HashMap<Integer, List<Integer>> sumMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int num = i;
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
            if (sumMap.containsKey(sum)) {
                List<Integer> cur = sumMap.get(sum);
                cur.add(i);
                sumMap.put(sum, cur);
            } else {
                sumMap.put(sum, new ArrayList<>(List.of(i)));
            }
        }

        int max = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, List<Integer>> map : sumMap.entrySet()) {
            if (map.getValue().size() > max) {
                max = map.getValue().size();
                maxCount = 0;
            }

            if (map.getValue().size() == max){
                maxCount++;
            }

        }

        return maxCount;
    }

    // Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int maxLen = 0;
        HashSet<Character> currentSet = new HashSet<>();
        for (int end=0; end<s.length(); end++){
            if (currentSet.contains(s.charAt(end))){
                // increment start till it is removed
                while (currentSet.contains(s.charAt(end))){
                    currentSet.remove(s.charAt(start));
                    start++;
                }
            }
            currentSet.add(s.charAt(end));
            maxLen = Math.max(maxLen, currentSet.size());
        }

        return maxLen;
    }

    public int characterReplacement(String s, int k) {

        // find char with the least freq
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        char minFreqChar = s.charAt(0);
        for (Map.Entry<Character, Integer> map : freqMap.entrySet()){
            if (map.getValue() < freqMap.get(minFreqChar)) minFreqChar = map.getKey();
        }
        if(freqMap.size() < 2) return s.length();
        // AAABAABBAAAAAAAA k = 2
        // MIN char = B

        int balance = k;
        int start = 0;
        int maxLen = 0; //6
        Queue<Integer> removedIndex = new LinkedList<>();
        for (int end = 0; end<s.length(); end++){
            if (s.charAt(end) == minFreqChar && balance>0){
                balance --;
                removedIndex.offer(end);
            }else if (s.charAt(end) == minFreqChar && balance == 0){
                if (!removedIndex.isEmpty()){
                    start = removedIndex.poll() +1;
                    balance++;
                }else{
                    start = end+1;
                    continue;
                }
            }
            maxLen = Math.max(maxLen, end - start +1);
        }
        return maxLen;
    }

    // 3392. Count Subarrays of Length Three With a Condition
    public int countSubarrays(int[] nums) {
        if(nums.length<3) return 0;
        int solCount =0;
        int start = 0;

        for (int end = 2; end<nums.length; end++){
            if (2*(nums[start] + nums[end]) == nums[end-1]){
                solCount++;
            }
            start++;
        }
        return solCount;
    }
}
