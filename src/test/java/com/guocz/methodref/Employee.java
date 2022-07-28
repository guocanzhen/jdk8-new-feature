package com.guocz.methodref;

import lombok.*;

/**
 * @author guocz
 * @date 2022/7/27 13:48
 */
@Data
@EqualsAndHashCode
@ToString
public class Employee {

    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
        System.out.println("Employee().....");
    }

    public Employee(int id) {
        this.id = id;
        System.out.println("Employee(int id).....");
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(int id, String name, int age, double salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}
