package com.ohgiraffers.webrpg.upgrade.infra.service;

import com.ohgiraffers.webrpg.upgrade.domain.service.RequestService;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
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
    public GetUserUpgradeStatResult getUserUpgradeStats(int sequence, String flag) {
        User user = userApplicationService.getUserBySequence(sequence);
        UserUpgradeStatDTO userUpgradeStatDTO = userApplicationService.getUpgradeStatByFlag(user,flag);
        return new GetUserUpgradeStatResult(
                userUpgradeStatDTO.getUpgradeLevel(),
                userUpgradeStatDTO.getTotalHP(),
                userUpgradeStatDTO.getTotalSTR()
        );
    }

    @Override
    public GetUserInfoResult getUserInfo(int sequence) {
        User user = userApplicationService.getUserBySequence(sequence);
        UserInfoDTO userInfoDTO = userApplicationService.getInfo(user);
        return new GetUserInfoResult(
                userInfoDTO.getName(),
                userInfoDTO.getTotalHP(),
                userInfoDTO.getTotalSTR(),
                userInfoDTO.getUserLevel(),
                userInfoDTO.getUpgradeLevel(),
                userInfoDTO.getElementalType()
        );
    }

    @Override
    public GetUserResult saveUserBalance(int sequence, int money) {
        userApplicationService.saveMoneyReward(sequence, money);
        User user = userApplicationService.getUserBySequence(sequence);
        return new GetUserResult(
                user.getSequence(),
                user.getLevel(),
                user.getUpgradeLevel(),
                user.getMoney().getValue()
        );
    }
}
