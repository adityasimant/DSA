package BinarySearch;


import java.util.*;

class BinarySearchMain {
    public static void main(String[] args) {

        int[] arr = {1, 3};
        int[] a1 = {2, 4};
        BinarySearchSol s = new BinarySearchSol();
    }
}

public class BinarySearchSol {

    // koko eating banana
    public int minEatingSpeed(int[] piles, int h) {
        int end = piles[0];
        for(int i: piles){
            end = Math.max(i, end);
        }

        int start = 1;
        int result = -1;

        while(start<=end){
            int mid = (start+end)/2;
            int totalTimeToEat = calculateTotalTimeToEat(piles, mid);

            if(totalTimeToEat<=h){
                result = mid;
                end = mid -1;
            }else if(totalTimeToEat>h){
                start = mid +1;
            }
        }
        return result;
    }


    public int calculateTotalTimeToEat(int[] nums, int rate){

        int totalTime = 0;
        for(int i: nums){
            totalTime += (int) Math.ceil((double)i/rate);
        }

        return totalTime;
    }

    // Median of 2 sorted arrays
    /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Ptr = 0, num2Pts = 0;
        int N= nums1.length, M= nums2.length;
        ArrayList<Integer> arrList = new ArrayList<>();

        while (num1Ptr< N && num2Pts < M){
            if (nums1[num1Ptr]<=nums2[num2Pts]){
                arrList.add(nums1[num1Ptr]);
                num1Ptr++;
            }else{
                arrList.add(nums2[num2Pts]);
                num2Pts++;
            }
        }

        while (num1Ptr<N){
            arrList.add(nums1[num1Ptr]);
            num1Ptr++;
        }

        while (num2Pts<M){
            arrList.add(nums2[num2Pts]);
            num2Pts++;
        }


        int start = 0;
        int end = arrList.size()-1;
        int mid = (start+end)/2;

        if (arrList.size() %2 ==0){
            //even len
            double sum = arrList.get(mid) +  arrList.get(mid+1);
            return (sum/2);
        }else {
            // odd len
            return arrList.get(mid);
        }
    }*/

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//    }
}


class TSPair {
    public int timeStamp;
    public String value;

    TSPair(int timeStamp, String value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }
}

class TimeMap {

    HashMap<String, List<TSPair>> timeMap;

    public TimeMap() {
        timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TSPair pair = new TSPair(timestamp, value);
        List<TSPair> currentList = timeMap.getOrDefault(key, new ArrayList<>());
        currentList.add(pair);
        timeMap.put(key, currentList);
    }

    public void mapToString() {
        for (Map.Entry<String, List<TSPair>> curMap : timeMap.entrySet()) {
            System.out.println(curMap.getKey());
            System.out.println();
            for (TSPair pair : curMap.getValue()) {
                System.out.println(pair.value + " " + pair.timeStamp);
            }
        }
    }

    public String get(String key, int timestamp) {

        List<TSPair> list = timeMap.getOrDefault(key, new ArrayList<>());
        if (list.isEmpty()) return "";
        if (list.size() == 1){
            if (list.get(0).timeStamp < timestamp) return list.get(0).value;
            return "";
        }

        int N = list.size();
        int start =0;
        int end = N-1;
        String result = "";
        while (start<=end){
            int mid = (start+end)/2;
            if (list.get(mid).timeStamp == timestamp){
                return list.get(mid).value;
            }else if (list.get(mid).timeStamp > timestamp){
                end = mid -1;
            }else {
                result = list.get(mid).value;
                start = mid+1;
            }
        }
        return result;
    }
}
