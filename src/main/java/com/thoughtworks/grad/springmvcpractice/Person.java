package com.thoughtworks.grad.springmvcpractice;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        if (this.age == other.age) {
            return this.name.compareTo(other.name);
        }
        return this.age - other.age;
    }
}
