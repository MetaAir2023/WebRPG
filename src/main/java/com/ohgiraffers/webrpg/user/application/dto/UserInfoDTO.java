package com.ohgiraffers.webrpg.user.application.dto;


import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDTO {

    private String name;
    private int totalHP;
    private int totalSTR;
    private int userLevel;
    private int upgradeLevel;
    private ElementalType elementalType;
}
