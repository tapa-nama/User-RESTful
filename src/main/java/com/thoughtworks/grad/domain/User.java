package com.thoughtworks.grad.domain;

import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }



    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public User(int id, String name, ArrayList<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
