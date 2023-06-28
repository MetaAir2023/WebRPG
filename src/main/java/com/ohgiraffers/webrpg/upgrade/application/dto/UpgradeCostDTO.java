package com.ohgiraffers.webrpg.upgrade.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpgradeCostDTO {
    private int balance;
    private int upgradeCost;
}
