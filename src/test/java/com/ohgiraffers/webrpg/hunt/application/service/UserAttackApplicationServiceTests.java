package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class UserAttackApplicationServiceTests {

    @Autowired
    private UserAttackApplicationService userAttackApplicationService;

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private InMemoryUserRepository inMemoryUserRepository;

    @Test
    @DisplayName("sequence로 유저 이름 조회하기 Test")
    public void findUserBySequence() {
        int sequence = 1;
        UserInfoDTO userInfoDTO = userApplicationService.getInfo((User) inMemoryUserRepository.findUserBySequence(sequence));
        User user = userApplicationService.getUserBySequence(1);
        System.out.println("user.getName() = " + user.getName());
        assertEquals("소드마스터", userInfoDTO.getName());
    }

 

}
