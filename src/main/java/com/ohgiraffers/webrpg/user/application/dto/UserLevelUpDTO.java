package com.ohgiraffers.webrpg.user.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLevelUpDTO {
    private int balanceEXP;
    private int newLevel;
}
