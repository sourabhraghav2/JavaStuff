package com.practice;

import java.util.*;

public class Demo2 {
    static int minCntCharDeletionsfrequency(char[] arr, int l) {

        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Integer> Q = new PriorityQueue<>((x, y) -> Integer.compare(y, x));

        int ch = 0;
        for (int i = 0; i < l; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Character, Integer> it : map.entrySet()) {
            Q.add(it.getValue());
        }
        while (!Q.isEmpty()) {
            int frq = Q.poll();
            if (Q.isEmpty()) {
                return ch;
            }

            if (frq == Q.peek()) {
                if (frq > 1) {
                    Q.add(frq - 1);
                }
                ch++;
            }
        }

        return ch;
    }

    // Driver Code
    public static void main(String[] args) {
        String str = "aaabbbcc";

        // Stores length of str
        int N = str.length();
        System.out.print(minCntCharDeletionsfrequency(
                str.toCharArray(), N));
        System.out.println(anotherSolution(str.toCharArray()));
    }

    public static int anotherSolution(char[] arr) {
        int l = arr.length;
        int[] freq = new int[26];
        for (char c : arr) freq[c - 'a']++;
        Arrays.stream(freq).sorted();
        Set<Integer> found = new HashSet();
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (freq[i] != 0) {
                while (found.contains(freq[i])) {
                    res++;
                    freq[i]--;
                }
                found.add(freq[i]);
            }
        }
        return res;
    }

    public static int anotherSolutionQueue(char[] arr) {
        int l = arr.length;

        return 2;
    }
}
