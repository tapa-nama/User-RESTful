package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.Contact;
import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserRepository;
import com.thoughtworks.grad.repository.UserStorage;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public Collection<User> findUsers() {
        return UserStorage.getUsers();
    }

    @Override
    public User createUser(User newUser) {
        return UserStorage.save(newUser);
    }

    @Override
    public User update(User user) {
        return UserStorage.update(user);
    }

    @Override
    public void delete(int id) {
        UserStorage.delete(id);
    }

    @Override
    public User addContact(int userId, Contact contact) {
        return UserStorage.addContact(userId, contact);
    }

    @Override
    public User findContacts(int userId) {
        return UserStorage.findContacts(userId);
    }

    @Override
    public User updateContact(int userId, Contact contact) {
        return UserStorage.updateContact(userId, contact);
    }

    @Override
    public void deleteContactOfUser(int userId, int contactId) {
        UserStorage.getUserById(userId).getContacts().remove(contactId);
    }

    @Override
    public Contact findContactByName(String userName, String contactName) {
        return UserStorage.findContactByName(userName, contactName);
    }


}
