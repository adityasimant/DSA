package DynamicProgramming;

import java.util.HashMap;

public class DpMainInit {

    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int ans = fib(6);
        System.out.println(ans);
    }

    public static int fib(int n){
        if(map.containsKey(n)){
            return map.get(n);
        }
        if (n<=1) {
            return n;
        }
        int res = fib(n-1) + fib(n-2);
        map.put(n, res);
        return res;
    }
}
