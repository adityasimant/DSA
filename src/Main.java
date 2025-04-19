import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Random rand = new Random();
//        int[] numbers = {-1,2,-3,3};
//
////        for (int i = 0; i < numbers.length; i++) {
////            numbers[i] = rand.nextInt(100);
////        }
//
//        System.out.println("Before:");
//        printArray(numbers);
//
////        mergeSort(numbers);
//        Solution s = new Solution();
//
//        System.out.println("\nAfter:");
//        System.out.println(s.findMaxK(numbers));
////        printArray(numbers);


        String[][] nums = new String[3][3];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = String.valueOf(i + j);
            }
//            System.out.println();
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Printing Horizontally");
        System.out.println();
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Printing Vertically");
        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(arr[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Printing sum");
        System.out.println();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
        System.out.println();
        System.out.println("Printing sum (row wise)");
        System.out.println();
        int rowSum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                rowSum += arr[i][j];
            }
            System.out.println(rowSum);
        }
        System.out.println();
        System.out.println("Printing sum (Colums wise)");
        System.out.println();
        int colSum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                colSum += arr[j][i];
            }
            System.out.println(colSum);
        }
        System.out.println();
        System.out.println("Max & Min (row wise)");
        System.out.println();

        for (int i = 0; i < nums.length; i++) {
            int maxRow = Integer.MIN_VALUE;
            int minRow = Integer.MAX_VALUE;

            for (int j = 0; j < nums[i].length; j++) {
                minRow = Math.min(arr[i][j], minRow);
                maxRow = Math.max(arr[i][j], maxRow);
            }
            System.out.print("Min in row " + minRow);
            System.out.println("Max in row " + maxRow);
        }

        System.out.println();
        System.out.println("Border");
        System.out.println();

        for (int i = 0; i < arr[0].length; i++) {
            System.out.print(arr[0][i] + " ");
        }
        for (int i = 1; i < arr.length; i++) {
            System.out.print(arr[i][arr.length - 1] + " ");
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            System.out.print(arr[arr.length - 1][i] + " ");
        }
        for (int i = arr[0].length - 2; i >= 0; i--) {
            System.out.print(arr[i][0] + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("ZigZag");
        System.out.println();
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printZigZag(mat);
        System.out.println();
        System.out.println("Equal to transpose");
        System.out.println();
        int[][] mtx = {
                {1, 2, 3},
                {4, 5, 6}
        };
        printTranspose(mtx);
    }

    public static void printTranspose(int[][] mat){
        List<List<Integer>> sol = new ArrayList<>();
        for (int i=0; i<mat[0].length; i++){
            List<Integer> currentList = new ArrayList<>();
            for (int j=0; j< mat.length; j++){
                currentList.add(mat[j][i]);
            }
            sol.add(currentList);
        }
        System.out.println(sol);
    }

    public static void printZigZag(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        List<Integer> result = new ArrayList<>();

        for (int d = 0; d < rows + cols - 1; d++) {
            int row, col;

            if (d % 2 == 0) {
                // Move upwards
                row = (d < rows) ? d : rows - 1;
                col = d - row;
                while (row >= 0 && col < cols) {
                    result.add(mat[row][col]);
                    row--;
                    col++;
                }
            } else {
                // Move downwards
                col = (d < cols) ? d : cols - 1;
                row = d - col;
                while (col >= 0 && row < rows) {
                    result.add(mat[row][col]);
                    row++;
                    col--;
                }
            }
        }

        // Print the result
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) System.out.print(", ");
        }
    }

    private static void mergeSort(int[] inputArr) {
        int N = inputArr.length;

        if (N < 2) return;

        int midPoint = N / 2;
        int[] leftPart = new int[midPoint];
        int[] rightPart = new int[N - midPoint];

        for (int i = 0; i < midPoint; i++) leftPart[i] = inputArr[i];
        for (int i = midPoint; i < N; i++) {
            rightPart[i - midPoint] = inputArr[i];
        }
        mergeSort(leftPart);
        mergeSort(rightPart);

        merge(inputArr, leftPart, rightPart);
    }


    private static void merge(int[] inputArr, int[] leftPart, int[] rightPart) {

        int rightLen = rightPart.length;
        int leftLen = leftPart.length;
        int N = inputArr.length;

        int i = 0, j = 0, k = 0;

        while (i < leftLen && j < rightLen) {
            if (leftPart[i] <= rightPart[j]) {
                inputArr[k] = leftPart[i];
                i++;
            } else {
                inputArr[k] = rightPart[j];
                j++;
            }
            k++;
        }

        while (i < leftLen) {
            inputArr[k] = leftPart[i];
            i++;
            k++;
        }
        while (j < rightLen) {
            inputArr[k] = rightPart[j];
            j++;
            k++;
        }
    }


    private static void printArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
    }


}

class Solution {
    public int findMaxK(int[] nums) {
        HashSet<Integer> numsNegs = new HashSet<>();
        for (int i : nums) {
            if (i < 0) numsNegs.add(i);
        }
        int res = -1;

        for (int i : nums) {
            if (i < 0) continue;
            if (i > res && numsNegs.contains(i * -1)) {
                res = i;
            }
        }
        return res;
    }
}
