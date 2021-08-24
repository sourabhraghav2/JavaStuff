package com.practice;

public class Demo {
    public int solution(String s) {
        int init = Integer.MIN_VALUE;
        for (int i = 0; i < s.length() - 1; i++)
            init = Math.max(init, Integer.parseInt(s.substring(i, i + 2)));
        return init;
    }

    public static void main(String[] args) {


        Demo obj = new Demo(); //creating object of main class because no fucntion is static
        int x = obj.solution("50552"); //calling fucntion using object of main class
        System.out.println(x); //printing output

        int x1 = obj.solution("10101"); //calling fucntion using object of main class
        System.out.println(x1); //printing output

        int x2 = obj.solution("88"); //calling fucntion using object of main class
        System.out.println(x2); //printing output
    }
}
