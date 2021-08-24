package com.practice;


import lombok.Getter;

public class Builder {
    public static void main(String[] args) {
        final var student=StudentBuilder.builder()
                .name("Sourabh")
                .rollNo("1")
                .build();
        System.out.println(student.getName());
        System.out.println(student.getRollno());

    }
}

@Getter
class Student {
    private final String name;
    private final String rollno;

    Student(String name, String rn) {
        this.name = name;
        this.rollno = rn;
    }
}

class StudentBuilder {
    String name;
    String rollno;

    static StudentBuilder builder() {
        return new StudentBuilder();
    }

    StudentBuilder name(String name) {
        this.name = name;
        return this;
    }
    StudentBuilder rollNo(String rn) {
        this.rollno = rn;
        return this;
    }


    Student build() {
        return new Student(name, rollno);
    }
}