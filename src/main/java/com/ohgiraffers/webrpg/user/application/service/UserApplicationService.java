package com.ohgiraffers.webrpg.user.application.service;

import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import com.ohgiraffers.webrpg.user.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    private final UserRepository<User> userRepository;
    private final UserDomainService userDomainService;

    @Autowired
    public UserApplicationService(UserRepository<User> userRepository,
                                       UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public int getUserSequence(String name) {
        return userRepository.findUserByName(name).getSequence();
    }

    public User getUserBySequence(int sequence) {
        return userRepository.findUserBySequence(sequence);
    }
}
