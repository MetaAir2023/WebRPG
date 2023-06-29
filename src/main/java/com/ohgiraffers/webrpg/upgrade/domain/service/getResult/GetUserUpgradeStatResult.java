package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import lombok.*;

@Builder
@Getter
public class GetUserUpgradeStatResult {
    private int upgradeLevel;
    private int totalHP;
    private int totalSTR;
}