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

    @Test
    @DisplayName("유저가 몬스터를 단 한 번 공격하는 속성 공격 Test - 유저의 속성이 열세인 경우, 약회된 공격")
    public void testAttackToMonster() {
        UserInfoDTO userInfoDTO = userAttackApplicationService.getUserInfo((User) inMemoryUserRepository.findUserBySequence(1));
        Monster monster = domainRepository.findMonsterBySequence(1);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        ElementalType userET  = ElementalType.FIRE;
        MonsterET monsterET = MonsterET.WATER;
        getElementalDTO.setUserET(userET);
        getElementalDTO.setMonET(monsterET);
        System.out.println("monster.getMonsterName() = " + monster.getMonsterName());
        UserAttackDTO userAttackDTO = userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, sequence, getElementalDTO);
        assertEquals(190, userAttackDTO.getMonsterCurrentHP().getValue());

    }

    @Test
    @DisplayName("유저가 몬스터를 단 한 번 공격하는 속성 공격 Test - 유저와 몬스터의 속성이 동등한 경우, 일반적인 공격")
    public void testAttackToMonster2() {
        UserInfoDTO userInfoDTO = userAttackApplicationService.getUserInfo((User) inMemoryUserRepository.findUserBySequence(1));
        Monster monster = domainRepository.findMonsterBySequence(1);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        ElementalType userET = ElementalType.FIRE;
        MonsterET monsterET = MonsterET.FIRE;
        getElementalDTO.setUserET(userET);
        getElementalDTO.setMonET(monsterET);
        UserAttackDTO userAttackDTO = userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, sequence, getElementalDTO);
        System.out.println("userAttackDTO.getUserInfoDTO().getTotalSTR() = " + userAttackDTO.getUserInfoDTO().getTotalSTR());
        System.out.println("몬스터의 체력 : " + monster.getMonsterHp().getValue());
        assertEquals(100, userAttackDTO.getMonsterCurrentHP().getValue());
    }

    @Test
    @DisplayName("유저가 몬스터를 단 한 번 공격하는 속성 공격 Test - 유저와 몬스터의 속성이 유저가 유리할 경우 - 강화 된 공격")
    public void testAttackToMonster3() {
        UserInfoDTO userInfoDTO = userAttackApplicationService.getUserInfo((User) inMemoryUserRepository.findUserBySequence(1));
        Monster monster = domainRepository.findMonsterBySequence(1);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        ElementalType userET = ElementalType.FIRE;
        MonsterET monsterET = MonsterET.GRASS;
        getElementalDTO.setUserET(userET);
        getElementalDTO.setMonET(monsterET);
        UserAttackDTO userAttackDTO = userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, sequence, getElementalDTO);
        System.out.println("userAttackDTO.getUserInfoDTO().getTotalSTR() = " + userAttackDTO.getUserInfoDTO().getTotalSTR());
        System.out.println("user의 공격력 : " + userAttackDTO.getUserInfoDTO().getTotalSTR());
        System.out.println("몬스터의 체력 : " + monster.getMonsterHp().getValue());
        assertEquals(89, userAttackDTO.getMonsterCurrentHP().getValue());
    }

    @Test
    @DisplayName("유저가 몬스터를 3번 공격한 경우, 그 다음 공격의 패턴 - 2번 공격")
    public void testUserPatternDoubleAttack() {
        UserInfoDTO userInfoDTO = userAttackApplicationService.getUserInfo((User) inMemoryUserRepository.findUserBySequence(1));
        userInfoDTO.setTotalSTR(100);
        Monster monster = domainRepository.findMonsterBySequence(2);
        MonsterDTO monsterDTO = monsterAttackApplicationService.getInfo(monster);
        UserAttackDTO userAttackDTO = userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        UserPatternDTO userPatternDTO = userAttackApplicationService.initUserPatternDTO();
        userPatternDTO.setAttackCnt(3);
        int sequence = 1;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        ElementalType userET = ElementalType.WATER;
        MonsterET monsterET = MonsterET.FIRE;
        getElementalDTO.setMonET(monsterET);
        getElementalDTO.setUserET(userET);

        IntegrateUserAttackDTO integrateUserAttackDTO = userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        integrateUserAttackDTO = userAttackApplicationService.attackPatternUser(integrateUserAttackDTO, sequence, getElementalDTO);
        assertEquals(2600, integrateUserAttackDTO.getUserAttackDTO().getMonsterCurrentHP().getValue());
    }
}
