package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import lombok.*;

@Builder
@Getter
public class GetUserUpgradeStatusResult {
    private int sequence;
    private int userLevel;
    private int upgradeLevel;
    private int money;
}
