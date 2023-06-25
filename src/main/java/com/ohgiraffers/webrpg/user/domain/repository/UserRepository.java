package com.ohgiraffers.webrpg.user.domain.repository;

import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;

public interface UserRepository<T> {
    T findUserBySequence(Integer sequence);

    T findUserByName(String name);
    void saveLevelUp(Integer sequence, UserLevelUpDTO userLevelUpDTO);

    void saveMoney(Integer sequence, Money money);

    void saveElementalType(Integer sequence, ElementalType elementalType);

    void saveUpgradeLevel(Integer sequence, Integer upgradeLevel);

}
