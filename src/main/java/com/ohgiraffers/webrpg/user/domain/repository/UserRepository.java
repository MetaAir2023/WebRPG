package com.ohgiraffers.webrpg.user.domain.repository;

public interface UserRepository<T> {
    T findUserBySequence(Integer sequence);

    T findUserByName(String name);
}
