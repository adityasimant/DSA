package Graphs.GraphMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GraphMain {
    public static void main(String[] args) {

    }
}

class GraphSolution{

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        boolean[]  visitedNodes = new boolean[n];

        for(int[] i : edges){
            int start = i[0];
            int end = i[1];

            adj.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            adj.computeIfAbsent(end, k -> new ArrayList<>()).add(start);
        }

        return findDestination(source, destination, adj, visitedNodes);
    }

    public boolean findDestination(int source, int destination, HashMap<Integer, List<Integer>> adj,  boolean[]  visitedNodes ){
        visitedNodes[source] = true;
        if (source == destination) return true;
        for (int nBrs : adj.get(source)){
            if (!visitedNodes[nBrs]){
                boolean isPresent = findDestination(nBrs, destination, adj, visitedNodes);
                if (isPresent) return true;
            }
        }
        return false;
    }
}
