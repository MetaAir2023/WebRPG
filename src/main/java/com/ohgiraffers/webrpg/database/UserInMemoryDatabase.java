package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryDatabase {

    private static final Map<Integer, User> userMap = new HashMap<>();

    public UserInMemoryDatabase() {
        userMap.put(1,new User(1, "소드마스터",
                1000, 100,
                new Money(10000), 100000,
                ElementalType.FIRE));
    }

    public static <T> T findUserBySequence(int sequence) {
        return (T) userMap.get(sequence);
    }

    public static <T> T findUserByName(String name) {
        return (T) userMap.values().stream()
                .filter(user ->user.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
