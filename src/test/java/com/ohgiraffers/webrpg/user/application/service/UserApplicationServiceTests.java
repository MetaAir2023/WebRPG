package com.ohgiraffers.webrpg.user.application.service;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class UserApplicationServiceTests {

    @Autowired
    private UserApplicationService userApplicationService;

    @Test
    public void testApplicationService(){
        assertNotNull(userApplicationService);

    }

    @Test
    public void testGetInfo() {
        int sequence = 1;
        User user = userApplicationService.getUserBySequence(sequence);
        UserInfoDTO userInfoDTO = userApplicationService.getInfo(user);
        assertEquals(userInfoDTO.getUserLevel(), 1);
        assertEquals(userInfoDTO.getUpgradeLevel(), 1);
        assertEquals(userInfoDTO.getElementalType(), ElementalType.FIRE);
        assertEquals(userInfoDTO.getTotalHP(), 1100);
        assertEquals(userInfoDTO.getTotalSTR(), 200);
    }

    @Test
    public void testSaveEXPReward(){
        int sequence = 1;
        int exp = 100;
        userApplicationService.saveEXPReward(sequence, exp);
        User user = userApplicationService.getUserBySequence(sequence);
        assertEquals(user.getLevel(), 2);
        assertEquals(user.getExperiencePoint(), 50);

    }

    @Test
    public void testSaveMoneyReward() {
        int sequence = 1;
        int money = 100;
        userApplicationService.saveMoneyReward(sequence, money);
        User user = userApplicationService.getUserBySequence(sequence);
        assertEquals(user.getMoney().getValue(), 100);

    }
    @Test
    public void testGetUpgradeStatByFlagSuccess() {
        int sequence = 1;
        User user = userApplicationService.getUserBySequence(sequence);
        UserUpgradeStatDTO userUpgradeStatDTO = userApplicationService.getUpgradeStatByFlag(user, FlagEnum.SUCCESS);
        assertEquals(userUpgradeStatDTO.getUpgradeLevel(), 2);
        assertEquals(userUpgradeStatDTO.getTotalHP(), 4400);
        assertEquals(userUpgradeStatDTO.getTotalSTR(), 800);
    }

    @Test
    public void testGetUpgradeStatByFlagFail() {
        int sequence = 1;
        User user = userApplicationService.getUserBySequence(sequence);
        UserUpgradeStatDTO userUpgradeStatDTO = userApplicationService.getUpgradeStatByFlag(user, FlagEnum.FAIL);
        assertEquals(userUpgradeStatDTO.getUpgradeLevel(), 0);
        assertEquals(userUpgradeStatDTO.getTotalHP(), 1100);
        assertEquals(userUpgradeStatDTO.getTotalSTR(), 200);
    }
}
