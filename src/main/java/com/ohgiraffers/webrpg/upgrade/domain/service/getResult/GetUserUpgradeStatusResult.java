package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserUpgradeStatusResult {
    private int sequence;
    private int userLevel;
    private int upgradeLevel;
    private int money;
}
