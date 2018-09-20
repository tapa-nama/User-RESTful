package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserRepository;
import com.thoughtworks.grad.repository.UserStorage;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {


    @Override
    public Collection<User> findUsers() {
        return UserStorage.getUsers().values();
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


}
