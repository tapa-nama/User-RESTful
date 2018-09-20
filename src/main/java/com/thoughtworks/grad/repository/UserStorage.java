package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.Contact;
import com.thoughtworks.grad.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static Collection<User> getUsers() {
        return USERS.values();
    }


    public static User save(User newUser) {

        USERS.put(newUser.getId(), newUser);
        return USERS.get(newUser.getId());
    }

    public static void clear() {
        USERS.clear();
    }

    public static User update(User user) {
        User beforeUser = USERS.get(user.getId());
        beforeUser.setName(user.getName());
        return beforeUser;
    }

    public static void delete(int id) {
        USERS.remove(id);
    }

    public static User getUserById(int id) {
        return USERS.get(id);
    }

    public static User addContact(int userId, Contact contact) {
        USERS.get(userId).getContacts().add(contact);
        return USERS.get(userId);

    }

    public static User findContacts(int userId) {
        return USERS.get(userId);
    }

    public static User updateContact(int userId, Contact contact) {
        User user = USERS.get(userId);
        ArrayList<Contact> contacts = user.getContacts();
        int contactId = contact.getId();
        contacts.stream().filter(oldContact -> oldContact.getId() == contactId).forEach(oldContact -> {
            oldContact.setName(contact.getName());
            oldContact.setAge(contact.getAge());
            oldContact.setGender(contact.getGender());
            oldContact.setNumber(contact.getNumber());
        });
        return user;


    }

    public static Contact findContactByName(String userName, String contactName) {
        return USERS.entrySet().stream()
                .filter(user -> user.getValue().getName().equals(userName))
                .flatMap(user -> user.getValue().getContacts().stream())
                .filter(contact -> contact.getName().equals(contactName))
                .findFirst().orElse(null);
    }
}
