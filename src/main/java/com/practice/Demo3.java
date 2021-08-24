package com.practice;

public class Demo3 {

    public static void main(String[] args) {
        System.out.println(solution1(new int[]{5, 2, 4, 6, 3, 7}));
        System.out.println(solution(new int[]{5, 2, 4, 6, 3, 7}));
    }

    public static int solution(int[] A) {
        if (A.length < 5)
            return -1;
        int min = A[1];
        int prev = A[2];
        int minSum = A[1] + A[3];

        for (int i = 4; i <= A.length - 2; i++) {
            min = Math.min(min, prev);
            minSum = Math.min(minSum, (min + A[i]));
            prev = A[i - 1];
        }

        return minSum;
    }

    public static int solution1(int[] A) {
        if(A.length < 5)
            return -1;

        int minSum = Integer.MAX_VALUE;

        for(int i = 1 ; i < A.length - 3; i++)
            for(int j = i+2 ; j < A.length -1 ; j++){
                if(minSum > (A[i]+A[j])){
                    minSum = A[i]+A[j];
                }
            }
        return minSum;
    }
}
