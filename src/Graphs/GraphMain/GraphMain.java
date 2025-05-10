package Graphs.GraphMain;

import java.util.*;

public class GraphMain {
    public static void main(String[] args) {
        GraphSolution gsd = new GraphSolution();
        char[][] grid = {
                {'0', '1', '1', '1', '0'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int sol = gsd.numIslands(grid);
        System.out.println(sol);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class GraphSolution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        boolean[] visitedNodes = new boolean[n];

        for (int[] i : edges) {
            int start = i[0];
            int end = i[1];

            adj.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            adj.computeIfAbsent(end, k -> new ArrayList<>()).add(start);
        }

        return findDestination(source, destination, adj, visitedNodes);
    }

    public boolean findDestination(int source, int destination, HashMap<Integer, List<Integer>> adj, boolean[] visitedNodes) {
        visitedNodes[source] = true;
        if (source == destination) return true;
        for (int nBrs : adj.get(source)) {
            if (!visitedNodes[nBrs]) {
                boolean isPresent = findDestination(nBrs, destination, adj, visitedNodes);
                if (isPresent) return true;
            }
        }
        return false;
    }

    // 733. Flood Fill
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        boolean[][] trackMatrix = new boolean[image.length][image[0].length];
        int n = image.length;
        int currentColor = image[sr][sc];
        fillColors(image, trackMatrix, sr, sc, color, n, currentColor);
        return image;
    }

    private void fillColors(int[][] image, boolean[][] trackMatrix, int row, int col, int color, int n, int currentColor) {

        if ((row < 0 || row > n - 1) || (col < 0 || col > image[row].length - 1) || trackMatrix[row][col] || image[row][col] != currentColor)
            return;
        trackMatrix[row][col] = true;
        image[row][col] = color;

        // up
        fillColors(image, trackMatrix, row - 1, col, color, n, currentColor);

        // right
        fillColors(image, trackMatrix, row, col + 1, color, n, currentColor);

        // down
        fillColors(image, trackMatrix, row + 1, col, color, n, currentColor);

        // left
        fillColors(image, trackMatrix, row, col - 1, color, n, currentColor);
    }

    // Number of Islands
    public int numIslands(char[][] grid) {
        int noOfIslands = 0;

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (visited[r][c]) continue;
                if (grid[r][c] == '1') {
                    islandDFS(grid, r, c, visited);
                    noOfIslands++;
                }
            }
        }

        return noOfIslands;
    }

    private void islandDFS(char[][] grid, int row, int col, boolean[][] visited) {
        // out of bound
        int n = grid.length;
        if ((row < 0 || row > n - 1) || (col < 0 || col > grid[row].length - 1)) return;
        if (visited[row][col] || grid[row][col] != '1') return;

        visited[row][col] = true;

        // up
        islandDFS(grid, row - 1, col, visited);

        // right
        islandDFS(grid, row, col + 1, visited);

        //down
        islandDFS(grid, row + 1, col, visited);

        //left
        islandDFS(grid, row, col - 1, visited);
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> visited = new HashMap<>();
        Node clone = new Node(node.val);
        visited.put(node, clone);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (Node i : currentNode.neighbors) {
                if (!visited.containsKey(i)) {
                    visited.put(i, new Node(i.val));
                    queue.offer(i);
                }
                visited.get(currentNode).neighbors.add(visited.get(i));
            }
        }

        return visited.get(node);
    }


    // Rotting Fruit
    public int orangesRotting(int[][] grid) {
        // count fresh oranges
        int row = grid.length;
        int cols = grid[0].length;
        int freshOrangesCount = 0;
        Queue<List<Integer>> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) freshOrangesCount++;
                if (grid[i][j] == 2) {
                    queue.offer(new ArrayList<>(List.of(i, j)));
                }
            }
        }

        int time = 0;
        return 0;
    }

    public static boolean detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.

        // create adj map
        HashMap<Integer, ArrayList<Integer>> AdjMap = new HashMap<>();
        for (ArrayList<Integer> list : edges) {
            // this list will always be 2
            ArrayList<Integer> currentList;
            if (AdjMap.containsKey(list.get(0))) {
                currentList = AdjMap.get(list.get(0));
                currentList.add(list.get(1));
                AdjMap.put(list.get(0), currentList);
            } else {
                currentList = new ArrayList<>();
                currentList.add(list.get(1));
                AdjMap.put(list.get(0), currentList);
            }
        }

        return doesContainCycle(AdjMap, new HashSet<>(), edges.get(0).get(0));
    }

    private static boolean doesContainCycle(HashMap<Integer, ArrayList<Integer>> adjMap, HashSet<Integer> visited, int currentNode) {
        visited.add(currentNode);
        for (int i : adjMap.get(currentNode)) {
            if (visited.contains(i)) return true;
            doesContainCycle(adjMap, visited, i);
        }
        return false;
    }


    public int minimumEffortPath(int[][] heights) {
        return getBestPathDFS(heights, 0, 0, 0, 0);
    }

    private int getBestPathDFS(int[][] heights, int r, int c, int prev, int difference) {

        // check out of bound
        int rowLen = heights.length;
        int colLen = heights[r].length;
        if ((r < 0 || r >= rowLen) || (c < 0 || c >= colLen) || heights[r][c] < 0) {
            return Integer.MAX_VALUE;
        }

        difference = Math.max(difference, heights[r][c] - prev);
        heights[r][c] *= -1;
        if ((r == rowLen - 1) && (c == colLen - 1)) {
            return difference;
        }

        // up
        int up = getBestPathDFS(heights, r - 1, c, heights[r][c], difference);

        // right
        int right = getBestPathDFS(heights, r, c + 1, heights[r][c], difference);

        // down
        int down = getBestPathDFS(heights, r + 1, c, heights[r][c], difference);

        // left
        int left = getBestPathDFS(heights, r, c - 1, heights[r][c], difference);

        int minVal = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                minVal = Math.min(minVal, up);
            }
            if (i == 1) {
                minVal = Math.min(minVal, right);
            }
            if (i == 2) {
                minVal = Math.min(minVal, down);
            }
            if (i == 3) {
                minVal = Math.min(minVal, left);
            }
        }
        return minVal;
    }

    // GFG Undirected Graph Cycle
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        for(int[] nums : edges){
            int n1 = nums[0];
            int n2 = nums[1];

            List<Integer> n1List = adj.getOrDefault(n1, new ArrayList<>());
            List<Integer> n2List = adj.getOrDefault(n2, new ArrayList<>());

            n1List.add(n2);
            n2List.add(n1);

            adj.put(n1, n1List);
            adj.put(n2, n2List);
        }
        boolean[] visited = new boolean[V];
        for (int i=0; i<visited.length; i++){
            if(!visited[i] && containsCycle(adj, i, visited)){
                return true;
            }
        }

        return  false;
    }

    private boolean containsCycle(HashMap<Integer, List<Integer>> adj, int start, boolean[] visited){

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(List.of(start, -1));
        visited[start] = true;

        while (!queue.isEmpty()){
            List<Integer> currentPair = queue.poll();

            int currentElement = currentPair.get(0);
            int currentParent = currentPair.get(1);

            List<Integer> neighbors = adj.get(currentElement);

            for (int i : neighbors){
                if(visited[i] && i != currentParent){
                    return true;
                }
                if(i!=currentParent){
                    queue.offer(List.of(i, currentElement));
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    // DFS Traversal
    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        boolean[] visited = new boolean[v];
        ArrayList<ArrayList<Integer>> searchedNodes = new ArrayList<>();
        HashMap<Integer, List<Integer>> adjMap = new HashMap<>();

        // create adj map
        for (List<Integer> list : edges){
            int n1 = list.get(0);
            int n2 = list.get(1);

            List<Integer> n1List = adjMap.getOrDefault(n1, new ArrayList<>());
            n1List.add(n2);
            adjMap.put(n1, n1List);

            List<Integer> n2List = adjMap.getOrDefault(n2, new ArrayList<>());
            n2List.add(n1);
            adjMap.put(n2, n2List);
        }

        for (int i=0; i<visited.length; i++){
            if (!visited[i]){
                ArrayList<Integer> subList = new ArrayList<>();
                getAllOccurancesDFS(i, visited, adjMap, subList);
                searchedNodes.add(subList);
            }
        }
        return searchedNodes;
    }

    private static void getAllOccurancesDFS(int index, boolean[] visited, HashMap<Integer, List<Integer>> adjMap, ArrayList<Integer> subList){

        visited[index] = true;
        subList.add(index);
        List<Integer> neighbours = adjMap.getOrDefault(index, new ArrayList<>());
        for (int i: neighbours){
            if (!visited[i]){
                getAllOccurancesDFS(i, visited, adjMap, subList);
            }
        }
    }
}