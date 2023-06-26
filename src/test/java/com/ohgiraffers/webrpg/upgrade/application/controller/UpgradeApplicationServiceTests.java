package com.ohgiraffers.webrpg.upgrade.application.controller;

import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.upgrade.application.dto.UpgradeResultDTO;
import com.ohgiraffers.webrpg.upgrade.application.service.UpgradeApplicationService;
import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.upgrade.domain.service.RequestService;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class UpgradeApplicationServiceTests {

    private final UpgradeApplicationService upgradeApplicationService;
    private final RequestService requestService;

    @Autowired
    public UpgradeApplicationServiceTests(UpgradeApplicationService upgradeApplicationService,
                                          RequestService requestService) {
        this.upgradeApplicationService = upgradeApplicationService;
        this.requestService = requestService;
    }

    @Test
    public void testApplicationService(){
        assertNotNull(upgradeApplicationService);
    }

    @Test
    public void testGetUserStatsByFlagIsSuccess() {
        int userSequence = 1;
        GetUserUpgradeStatResult result = requestService.getUserUpgradeStats(userSequence,FlagEnum.SUCCESS);
        assertEquals(result.getUpgradeLevel(),2);
    }

    @Test
    public void testGetUserStatsByFlagIsFail() {
        int userSequence = 1;
        GetUserUpgradeStatResult result = requestService.getUserUpgradeStats(userSequence,FlagEnum.FAIL);
        assertEquals(result.getUpgradeLevel(),0);
    }

    @Test
    public void testGetUserStatsByFlagIsCurrent() {
        int userSequence = 1;
        GetUserUpgradeStatResult result = requestService.getUserUpgradeStats(userSequence,FlagEnum.CURRENT);
        assertEquals(result.getUpgradeLevel(),1);
    }

    @Test
    public void testCheckUpgradeMoney() {
        int money = 1000;
        int userLevel = 1;
        boolean result = upgradeApplicationService.checkUpgradeMoney(money, userLevel);
        assertEquals(result, true);
    }

    @Test
    public void testStart() {
        int upgradeLevel = 1;
        int money = 1000;
        int userLevel = 1;
        UpgradeResultDTO resultDTO = upgradeApplicationService.start(upgradeLevel, money, userLevel);
        assertEquals(2, resultDTO.getResultUpgradeLevel());
        assertEquals(990, resultDTO.getBalance());
    }

    @Test
    public void testGetUserInfo() {
        int userSequence = 1;
        GetUserInfoResult result = upgradeApplicationService.getUserInfo(userSequence);
        assertEquals("소드마스터",result.getName());
    }
}
