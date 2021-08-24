package com.internalalgorithm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashMapAlgo {
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();
        final var s1 = new Student("1", "Sourabh");
        set.add(s1);
        set.add(s1);
        System.out.println(set.size());
    }

    static class Student {
        String id;
        String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof Student) {
                if (this.id.equals((Student) o)) {
                    return true;
                }
            }
            return false;
        }


    }
}
