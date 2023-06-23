package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserUpgradeStatResult {
    private int upgradeLevel;
    private int totalHP;
    private int totalSTR;
}
