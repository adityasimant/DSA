import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Node {
    int data;
    Node right;
    Node left;

    Node(int data){
        this.data = data;
        this.right = this.left = null;
    }
}


class BinaryTree {

    Node root;
    public void insert(int data){
        root = insertRec(root, data);
    }

    private  Node insertRec(Node root, int data){
        if (root == null){
            root = new Node(data);
        } else if (data < root.data) {
            root.left = insertRec(root.left, data);
        }else if( data > root.data){
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    public void inOrder(){
        inOrderRec(root);
    }

    private void inOrderRec(Node root){
        if (root!=null){
            inOrderRec(root.left);
            inOrderRec(root.right);
            System.out.println(root.data);
        }
    }

    public void maxDepth(){
        int max = maxDepthRec(root);
        System.out.println(max);
    }

    private int maxDepthRec(Node root){
        if (root == null){
            return  0;
        }
        return 1 + Math.max(maxDepthRec(root.left), maxDepthRec(root.right));
    }

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    public void BFS(){
        BFSearch(root);
    }

    private void BFSearch(Node root){

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node currentNode = queue.poll();
            System.out.println(currentNode.data);
            if (currentNode.left != null){
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null){
                queue.offer(currentNode.right);
            }
        }
    }
}

public class BTreeRec {


    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.insert(8);
        tree.insert(7);
        tree.insert(12);
        tree.insert(15);
        tree.insert(2);
        tree.insert(5);

        tree.BFS();
    }

    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }
}
