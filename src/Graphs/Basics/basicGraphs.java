package Graphs.Basics;

import java.util.*;

public class basicGraphs {

    static Map<Integer, List<Integer>> adj = new HashMap<>();
    static HashSet<Integer> visitedSet = new HashSet<>();

    public static void main(String[] args) {


        adj.put(101, Arrays.asList(204, 305));
        adj.put(204, Arrays.asList(101, 402));
        adj.put(305, Arrays.asList(101, 509));
        adj.put(509, Arrays.asList(305));
        adj.put(402, Arrays.asList(204));

        dfsGraph(101);
    }

    public static void dfsGraph(int node){
        visitedSet.add(node);
        System.out.println(node);

        for (int nBrs : adj.getOrDefault(node, new ArrayList<>())){
            if (!visitedSet.contains(nBrs)){
                dfsGraph(nBrs);
            }
        }
    }
}
