package Recursion;

import java.util.Stack;

class Recursion extends RecursionSolution{
    public static void main(String[] args) {
        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        stk.push(5);
        reverseStack(stk);
        System.out.println(stk);
    }
}

class RecursionSolution{
    public static void reverseStack(Stack<Integer> stack) {
        // write your code here
        if (stack.isEmpty()) return;

        int top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    private static void insertAtBottom(Stack<Integer> stack, int top){
        if (stack.isEmpty()){
            stack.push(top);
            return;
        }

        int currentTop = stack.pop();
        insertAtBottom(stack, top);
        stack.push(currentTop);
    }
}
