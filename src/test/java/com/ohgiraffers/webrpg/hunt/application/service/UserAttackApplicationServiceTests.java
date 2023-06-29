package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.database.MonsterDB;
import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = Application.class)
public class UserAttackApplicationServiceTests {

    @Autowired
    private UserAttackApplicationService userAttackApplicationService;

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private InMemoryUserRepository inMemoryUserRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private MonsterAttackApplicationService monsterAttackApplicationService;

    @Test
    @DisplayName("sequence로 유저 이름 조회하기 Test")
    public void findUserBySequence() {
        int sequence = 1;
        UserInfoDTO userInfoDTO = userApplicationService.getInfo((User) inMemoryUserRepository.findUserBySequence(sequence));
        User user = userApplicationService.getUserBySequence(1);
        System.out.println("user.getName() = " + user.getName());
        assertEquals("소드마스터", userInfoDTO.getName());
    }

    @Test
    @DisplayName("참조하고 있는 클래스 테스트")
    public void userAttackApplicationServiceTest() {
        assertNotNull(userAttackApplicationService);
        assertNotNull(userApplicationService);

    }

    @Test
    @DisplayName("최초의 설정 된 유저와 몬스터의 정보를 받아보기")
    public void testInitUserAttackDTO() {
        UserInfoDTO userInfoDTO = userAttackApplicationService.getUserInfo((User) inMemoryUserRepository.findUserBySequence(1));
        Monster monster = domainRepository.findMonsterBySequence(1);
        UserAttackDTO userAttackDTO = userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        assertEquals("소드마스터", userAttackDTO.getUserInfoDTO().getName());
        assertEquals("네파리안", monster.getMonsterName());
    }




}
