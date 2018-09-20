package com.thoughtworks.grad.domain;

public class Contact {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String number;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Contact() {
    }

    public int getAge() {
        return age;
    }

    public String getNumber() {
        return number;
    }

    public Contact(int id, String name, String gender, int age, String number) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.number = number;
    }
}
