package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.User;

import java.util.Collection;

public interface UserRepository {

    Collection<User> findUsers();


    User createUser(User newUser);

    User update(User user);
}

