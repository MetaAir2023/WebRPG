package com.ohgiraffers.webrpg.upgrade.domain.service;


import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;

public interface RequestService {
    GetUserUpgradeStatResult getUserUpgradeStats(int sequence, String flag);

    GetUserInfoResult getUserInfo(int sequence);

    GetUserResult saveUserBalance(int sequence, int money);
}
