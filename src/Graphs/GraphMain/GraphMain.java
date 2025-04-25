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

    // 733. Flood Fill
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        boolean[][] trackMatrix = new boolean[image.length][image[0].length];
        int n = image.length;
        int currentColor = image[sr][sc];
        fillColors(image, trackMatrix, sr, sc, color, n, currentColor);
        return image;
    }

    private void fillColors(int[][] image, boolean[][] trackMatrix, int row, int col, int color, int n, int currentColor){

        if ((row<0 || row>n-1) || (col<0 || col>image[row].length-1) || trackMatrix[row][col] || image[row][col] != currentColor) return;
        trackMatrix[row][col] = true;
        image[row][col] = color;

        // up
        fillColors(image, trackMatrix, row-1, col, color, n, currentColor);

        // right
        fillColors(image, trackMatrix, row, col+1, color, n, currentColor);

        // down
        fillColors(image, trackMatrix, row+1, col, color, n, currentColor);

        // left
        fillColors(image, trackMatrix, row, col-1, color, n, currentColor);
    }

    // Number of Islands
    public int numIslands(char[][] grid) {
        int noOfIslands = 0;

        int row = grid.length;
        int col = grid[0].length;

        boolean[][] visited = new boolean[row][col];

        for (int r = 0; r<row; r++){
            for (int c = 0; c<col; c++){
                if (visited[r][c]) continue;
                if (grid[r][c] == '1'){
                    islandDFS(grid, r, c, visited);
                    noOfIslands++;
                }
            }
        }

        return noOfIslands;
    }

    private void islandDFS(char[][] grid, int row, int col, boolean[][] visited){
        // out of bound
        int n = grid.length;
        if ((row<0 || row>n-1) || (col<0 || col>grid[row].length-1)) return;
        if (visited[row][col] || grid[row][col] != '1') return;

        visited[row][col] = true;

        // up
        islandDFS(grid, row-1, col, visited);

        // right
        islandDFS(grid, row, col+1, visited);

        //down
        islandDFS(grid, row+1, col, visited);

        //left
        islandDFS(grid, row, col-1, visited);
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> visited = new HashMap<>();
        Node clone = new Node(node.val);
        visited.put(node, clone);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()){
            Node currentNode = queue.poll();

            for (Node i: currentNode.neighbors){
                if (!visited.containsKey(i)){
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

        for (int i=0; i<row; i++){
            for (int j=0; j<cols; j++){
                if (grid[i][j] == 1) freshOrangesCount++;
                if (grid[i][j] == 2){
                    queue.offer(new ArrayList<>(List.of(i,j)));
                }
            }
        }

        int time = 0;
        return 0;
    }
}