package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.infra.exception.UserExistException;

import java.util.HashMap;
import java.util.Map;

public abstract class UserInMemoryDatabase {

    private static final Map<Integer, User> userMap = new HashMap<>();
    static {
        userMap.put(1,new User(1, "소드마스터",
                1000, 100,
                new Money(1000), 1,0,1,
                ElementalType.FIRE));

    }
    private UserInMemoryDatabase() {
    }

    public static <T> T findUserBySequence(int sequence) {
        return (T) userMap.get(sequence);
    }

    public static <T> T findUserByName(String name) throws UserExistException {
        return (T) userMap.values().stream()
                .filter( T -> T.getName().equals(name))
                .findFirst()
                .orElseThrow(UserExistException::new);
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

    public static void saveElementalType(int sequence, ElementalType elementalType) {userMap.get(sequence).setElementalType(elementalType);}

    public static void saveUpgradeLevel(Integer sequence, Integer upgradeLevel) {
        userMap.get(sequence).setUpgradeLevel(upgradeLevel);
    }
}
