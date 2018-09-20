package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    static final Map<Integer, User> USERS = new HashMap<>();

    static {
        USERS.put(1, new User(1, "zhou"));
        USERS.put(2, new User(2, "lan"));
    }

    public static Collection<User> getUsers() {
        return USERS.values();
    }


}
