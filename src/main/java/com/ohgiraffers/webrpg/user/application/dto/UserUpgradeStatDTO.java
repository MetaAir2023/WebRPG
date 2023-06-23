package com.ohgiraffers.webrpg.user.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpgradeStatDTO {
    private int upgradeLevel;
    private int totalHP;
    private int totalSTR;
}
