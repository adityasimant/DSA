package Trees;

import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    Node right;
    Node left;

    Node(int data){
        this.data = data;
        this.left = this.right = null;
    }
}



class BinaryTree{

    Node root;
    public void insert(int data){
        root = insertRecur(root, data);
    }

    private Node insertRecur(Node root, int data){
        if (root == null){
            root = new Node(data);
        }else if (data< root.data){
            root.left = insertRecur(root.left, data);
        }else if( data>root.data){
            root.right = insertRecur(root.right,data);
        }
        return root;
    }

    public void display(){
        displayRecurr(root);
    }

    private void displayRecurr(Node root){
        if (root != null){
            displayRecurr(root.left);
            System.out.println(root.data);
            displayRecurr(root.right);
        }
    }

    public void BFS(){
        BFSrecurr(root);
    }

    private void BFSrecurr(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node currentNode = queue.poll();
            System.out.println(currentNode.data);
            if (currentNode.left != null) queue.offer(currentNode.left);
            if (currentNode.right != null) queue.offer(currentNode.right);
        }
    }
}



public class TreeDemo1 {
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
}
