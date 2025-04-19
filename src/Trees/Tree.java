package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Tree {
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {2, 3, 4, 5, 6};

        // Insert values into BST
        for (int val : values) {
            root = insertIntoBST(root, val);
        }

        // Print the tree (level order)
        System.out.println("Binary Search Tree:");
        printLevelOrder(root);

        TreeSolution sol = new TreeSolution();
        int result = sol.minDepth(root);
        System.out.println("solution: " + result);
    }

    // Insert function for BST
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
    }

    // Print level-order traversal (BFS)
    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                System.out.print(currentNode.val + " ");

                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            System.out.println(); // New line for each level
        }
    }
}

class TreeSolution {


    // 993. Cousins in Binary Tree
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean xVerified = false;
            boolean yVerified = false;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.val == x) xVerified = true;
                if (currentNode.val == y) yVerified = true;
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);

                if (xVerified && yVerified) return true;
            }
        }
        return false;
    }


    // 530. Minimum Absolute Difference in BST
    public int getMinimumDifference(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        inOrderrecur(root, list);
        int minValue = Integer.MAX_VALUE;
        int currentValue = 0;

        for (int i = 1; i < list.size(); i++) {
            currentValue = list.get(i) - list.get(i - 1);
            if (currentValue < minValue) minValue = currentValue;
        }

        return minValue;
    }

    private void inOrderrecur(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrderrecur(root.left, list);
            list.add(root.val);
            inOrderrecur(root.right, list);
        }
    }


    // 111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int currentQueueLen = queue.size();
            for (int i = 0; i < currentQueueLen; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left == null && currentNode.right == null) return depth;

                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            depth++;
        }
        return depth;
    }

    // 1609. Even Odd Tree
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0;
        boolean isEven = true;
        while (!queue.isEmpty()) {
            size = queue.size();
            int prevValue = isEven ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();
                if (isEven && (!(currentNode.val > prevValue) || (currentNode.val % 2 != 0))) {
                    return false;
                } else if (!isEven && (!(currentNode.val < prevValue) || (currentNode.val % 2 == 0))) {
                    return false;
                }
                prevValue = currentNode.val;

                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            isEven = !isEven;
        }

        return true;
    }

    // 404. Sum of Left Leaves
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftTreeRecur(root, false);
    }

    private int sumOfLeftTreeRecur(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        if (root.right == null && root.left == null && isLeft) {
            return root.val;
        }
        return sumOfLeftTreeRecur(root.left, true) + sumOfLeftTreeRecur(root.right, false);
    }

    // 129. Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        String ans = sumNumbersRecurr(root);
        return Integer.parseInt(ans);
    }

    private String sumNumbersRecurr(TreeNode root){
        if (root == null) return "";
        if (root.left == null && root.right == null) return String.valueOf(root.val);

        return String.valueOf(root.val) + sumNumbersRecurr(root.left) + sumNumbersRecurr(root.right);
    }

    // 623. Add One Row to Tree
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        int injectLevel = depth - 2;
        if (depth == 1){

            // Left
            TreeNode tmpLeftTree = new TreeNode(val);
            tmpLeftTree.left = root;
            return  tmpLeftTree;

        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentTreeDepth = 0;

        while (!queue.isEmpty()){
            int currentQueueSize = queue.size();

            for (int i=0; i< currentQueueSize; i++){
                TreeNode currentNode = queue.poll();
                if (injectLevel == currentTreeDepth){

                    // left tree
                    TreeNode tmpLeftTree = currentNode.left;
                    currentNode.left = new TreeNode(val);
                    currentNode.left.left = tmpLeftTree;

                    // right tree
                    TreeNode tmpRightTree = currentNode.right;
                    currentNode.right = new TreeNode(val);
                    currentNode.right.right = tmpRightTree;

                    // return the root
                    return  root;
                }

                if(currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            currentTreeDepth++;
        }
        return root;
    }

    // Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode currentNode = queue.poll();

            TreeNode tmpNode = currentNode.left;
            currentNode.left = currentNode.right;
            currentNode.right = tmpNode;

            if (currentNode.left != null) queue.offer(currentNode.left);
            if (currentNode.right != null) queue.offer(currentNode.right);
        }
        return root;
    }

    // LC 236. Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findCommonParentNode(root, p, q);
    }

    private TreeNode findCommonParentNode(TreeNode root, TreeNode p, TreeNode q){
        if (root==null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        TreeNode leftSubTree = findCommonParentNode(root.left, p, q);
        TreeNode rightSubTree = findCommonParentNode(root.right, p, q);

        if (rightSubTree != null && leftSubTree != null) return root;
        if (rightSubTree!= null && leftSubTree == null) return rightSubTree;
        if (rightSubTree == null && leftSubTree != null) return leftSubTree;

        return null;
    }

    // Diameter of Binary Tree
    public int diameterOfBinaryTree(TreeNode root) {
        int[] sol = new int[1];
        calculateSubTreeDepth(root, sol);
        return sol[0];
    }

    private int calculateSubTreeDepth(TreeNode root, int[] num){
        if (root == null) return 0;
        int maxHeightLeft = calculateSubTreeDepth(root.left, num);
        int maxHeightRight = calculateSubTreeDepth(root.right, num);
        num[0] = Math.max(num[0], maxHeightRight+maxHeightLeft);

        return 1+ Math.max(maxHeightLeft, maxHeightRight);
    }

    // Balanced Binary Tree
    public boolean isBalanced(TreeNode root) {
       return findMaxDepthDFS(root) != -1;
    }

    private int findMaxDepthDFS(TreeNode root){
        if (root == null) return 0;

        int leftSubtree = findMaxDepthDFS(root.left);
        if (leftSubtree == -1) return -1;

        int rightSubtree = findMaxDepthDFS(root.right);
        if (rightSubtree == -1) return -1;

        if (Math.abs(rightSubtree - leftSubtree) > 1) return -1;

        return 1 + Math.max(leftSubtree, rightSubtree);
    }

    //
}