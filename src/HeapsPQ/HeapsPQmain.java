package HeapsPQ;

import org.w3c.dom.ls.LSOutput;

import java.security.KeyPair;
import java.util.*;

public class HeapsPQmain {

    static class Event{
        int inTime;
        int outTime;

        Event(int inTime, int outTime){
            this.inTime = inTime;
            this.outTime = outTime;
        }
        @Override
        public String toString() {
            return "(" + inTime + ", " + outTime + ")";
        }

    }


    public static void main(String[] args) {
        List<Event> num = new ArrayList<>();
        num.add(new Event(1,4));
        num.add(new Event(1,2));
        num.add(new Event(2,4));
        num.add(new Event(1,4));
        num.add(new Event(2,3));
        num.add(new Event(4,5));
        num.add(new Event(3,5));

        num.sort((a,b) -> a.outTime - b.outTime);
        System.out.println(num);
    }
}

class HeapsPQSolution {

     //  703. Kth Largest Element in a Stream
     class KthLargest {
        private int limit;
        PriorityQueue<Integer> minHeap;

        public KthLargest(int k, int[] nums) {
            this.limit = k;
            minHeap = new PriorityQueue<>();

            for (int i: nums) {
                add(i);
            }
        }

        public int add(int val) {
            minHeap.offer(val);
            if (minHeap.size() > limit){
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }

    // last stone weight
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        for (int i: stones) maxHeap.offer(i);
        while (maxHeap.size()>1){
                int u = (maxHeap.isEmpty())?0:maxHeap.poll();
                int v = (maxHeap.isEmpty())?0:maxHeap.poll();
                if (u == v) continue;
                maxHeap.offer(Math.abs(u-v));
        }
        return (maxHeap.isEmpty())?0:maxHeap.peek();
    }

    // K Closest Points to Origin
//    public int[][] kClosest(int[][] points, int k) {
//        int[][] sol = new int[k][2];
//        HashMap<List<Integer>, Double> distanceMap = new HashMap<>();
//
//        for (int[] i : points){
//            double u = Math.pow(Math.abs(i[0]-0),2);
//            double v = Math.pow(Math.abs(i[1]-0),2);
//            double pointDistance = Math.sqrt(u+v);
//
//            distanceMap.put(new ArrayList<>(List.of(i[0], i[1])), pointDistance);
//        }
//
//        for (int i=0; i<k; i++){
//             elementToRemove = 0;
//            for (Map.Entry<List<Integer>, Double> map : distanceMap.entrySet()){
//                if (map.getValue() > max){
//                    max =
//                }
//            }
//
//        }
//    }
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i: nums){
            minHeap.offer(i);
            if (minHeap.size()>k){
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }
}
