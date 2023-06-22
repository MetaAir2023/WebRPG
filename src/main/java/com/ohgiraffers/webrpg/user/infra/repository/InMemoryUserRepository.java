package com.ohgiraffers.webrpg.user.infra.repository;

import com.ohgiraffers.webrpg.database.UserInMemoryDatabase;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository<T> implements UserRepository<T> {

    @Override
    public T findUserBySequence(Integer sequence) {
        return UserInMemoryDatabase.findUserBySequence(sequence);
    }

    @Override
    public T findUserByName(String name) {
        return UserInMemoryDatabase.findUserByName(name);
    }
}
