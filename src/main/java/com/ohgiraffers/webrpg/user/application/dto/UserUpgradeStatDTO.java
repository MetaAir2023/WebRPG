package com.ohgiraffers.webrpg.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpgradeStatDTO {
    private int upgradeLevel;
    private int totalHP;
    private int totalSTR;
}
