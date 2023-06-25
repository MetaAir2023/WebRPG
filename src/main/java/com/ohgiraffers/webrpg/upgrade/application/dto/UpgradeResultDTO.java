package com.ohgiraffers.webrpg.upgrade.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpgradeResultDTO {
    private int status;
    private int resultUpgradeLevel;
    private int balance;
}
