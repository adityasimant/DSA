package Algos.Greedy;
import java.util.*;

public class GreedyAlgoMain {
    public static void main(String[] args) {
        GreedySolution g = new GreedySolution();
        int[] arr1 = {1, 3, 0, 5, 8, 5};
        int[] arr2 = {2, 4, 6, 7, 9, 9};
        int sol = g.maxMeetings(arr1, arr2);
        System.out.println(sol);
    }
}

class Interval{
    int start;
    int end;

    Interval(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class GreedySolution{

    public int minimumMoves(String s) {

        String slidingSubWindow = "";
        int rightPtr = 2;
        int leftPtr = 0;
        int move = 0;

        while(leftPtr <= s.length()){
            slidingSubWindow += s.substring(leftPtr, rightPtr);
            if(slidingSubWindow.contains("X")) move++;
            leftPtr += 3;
            rightPtr += 3;
            slidingSubWindow = "";

            if(leftPtr >= s.length()){
                slidingSubWindow += s.substring(leftPtr, rightPtr);
                if(slidingSubWindow.contains("X")) move++;
                break;
            }
        }

        return move;
    }


    public int maxMeetings(int start[], int end[]) {
        int size = start.length;
        Interval[] intervals = new Interval[size];

        // insert all the interval values
        for(int i=0; i<size; i++){
            intervals[i] = new Interval(start[i], end[i]);
        }


        // sorting based on end time
        Arrays.sort(intervals, (a,b) -> a.end -  b.end );


        int solutionCount = 1;
        int endTime = intervals[0].end;
        Queue<Integer> departureQueue = new LinkedList<>();

        for(int i=1; i<size; i++){
            if(intervals[i].start > endTime){
                endTime = intervals[i].end;
                solutionCount++;
            }
        }

        return solutionCount;
    }
}
