package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;

import java.util.HashMap;
import java.util.Map;

public abstract class UserInMemoryDatabase {

    private static final Map<Integer, User> userMap = new HashMap<>();;
    static {
        userMap.put(1,new User(1, "소드마스터",
                1000, 100,
                new Money(0), 1,0,1,
                ElementalType.FIRE));

    }
    private UserInMemoryDatabase() {
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

    public static void saveLevel(int sequence, int level) {
        userMap.get(sequence).setLevel(level);
    }

    public static void saveEXP(int sequence, int exp) {
        userMap.get(sequence).setExperiencePoint(exp);
    }

    public static void saveMoney(int sequence, Money money) {
        userMap.get(sequence).setMoney(money);
    }
}
