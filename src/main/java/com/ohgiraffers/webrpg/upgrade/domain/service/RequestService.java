package com.ohgiraffers.webrpg.upgrade.domain.service;


import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatusResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.MoneyMark;

public interface RequestService {
    GetUserUpgradeStatResult getUserUpgradeStats(int sequence, FlagEnum flag);

    GetUserInfoResult getUserInfo(int sequence);

    GetUserUpgradeStatusResult getUserUpgradeStatus(int sequence);

    void saveUserBalance(int sequence, int money, MoneyMark mark);

    void saveUserUpgradeLevel(int sequence, int upgradeLevel);
}
