package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.User;

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
}
