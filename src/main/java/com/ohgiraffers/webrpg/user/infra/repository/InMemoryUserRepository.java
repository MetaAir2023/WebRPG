package com.ohgiraffers.webrpg.user.infra.repository;

import com.ohgiraffers.webrpg.database.UserInMemoryDatabase;
import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository<T> implements UserRepository<T> {

    @Override
    public T findUserBySequence(Integer sequence) {
        return UserInMemoryDatabase.findUserBySequence(sequence);
    }

    @Override
    public T findUserSequenceByName(String name) {
        return  UserInMemoryDatabase.findUserByName(name);
    }

    @Override
    public void saveLevelUp(Integer sequence, UserLevelUpDTO userLevelUpDTO) {
        UserInMemoryDatabase.saveLevel(sequence, userLevelUpDTO.getNewLevel());
        UserInMemoryDatabase.saveEXP(sequence, userLevelUpDTO.getBalanceEXP());
    }

    @Override
    public void saveMoney(Integer sequence, Money money) {
        UserInMemoryDatabase.saveMoney(sequence, money);
    }

    @Override
    public void saveElementalType(Integer sequence, ElementalType elementalType) {
        UserInMemoryDatabase.saveElementalType(sequence, elementalType);
    }
  
    public void saveUpgradeLevel(Integer sequence, Integer upgradeLevel) {
        UserInMemoryDatabase.saveUpgradeLevel(sequence, upgradeLevel);
    }
}
