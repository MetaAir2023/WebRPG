package com.ohgiraffers.webrpg.upgrade.infra.service;

import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.upgrade.domain.service.RequestService;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatusResult;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.MoneyMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestService implements RequestService {

    private final UserApplicationService userApplicationService;

    @Autowired
    public UserRequestService(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }


    @Override
    public GetUserUpgradeStatResult getUserUpgradeStats(int sequence, FlagEnum flag) {
        User user = userApplicationService.getUserBySequence(sequence);
        UserUpgradeStatDTO userUpgradeStatDTO = userApplicationService.getUpgradeStatByFlag(user,flag);
        return GetUserUpgradeStatResult.builder()
                .upgradeLevel(userUpgradeStatDTO.getUpgradeLevel())
                .totalHP(userUpgradeStatDTO.getTotalHP())
                .totalSTR(userUpgradeStatDTO.getTotalSTR())
                .build();
    }

    @Override
    public GetUserInfoResult getUserInfo(int sequence) {
        User user = userApplicationService.getUserBySequence(sequence);
        UserInfoDTO userInfoDTO = userApplicationService.getInfo(user);

        return GetUserInfoResult.builder()
                .name(userInfoDTO.getName())
                .userLevel(userInfoDTO.getUserLevel())
                .upgradeLevel(userInfoDTO.getUpgradeLevel())
                .totalHP(userInfoDTO.getTotalHP())
                .totalSTR(userInfoDTO.getTotalSTR())
                .elementalType(userInfoDTO.getElementalType())
                .Money(userInfoDTO.getMoney())
                .build();
    }

    @Override
    public GetUserUpgradeStatusResult getUserUpgradeStatus(int sequence) {
        User user = userApplicationService.getUserBySequence(sequence);

        return GetUserUpgradeStatusResult.builder()
                .sequence(user.getSequence())
                .userLevel(user.getLevel())
                .upgradeLevel(user.getUpgradeLevel())
                .money(user.getMoney().getValue())
                .build();
    }

    @Override
    public void saveUserBalance(int sequence, int money, MoneyMark mark) {
        if(mark == MoneyMark.REWARD) {
            userApplicationService.saveRewardMoney(sequence, money);
        } else {
            userApplicationService.saveSpendMoney(sequence, money);
        }
    }

    @Override
    public void saveUserUpgradeLevel(int sequence, int upgradeLevel) {
        userApplicationService.saveUpgradeLevel(sequence, upgradeLevel);
    }
}
