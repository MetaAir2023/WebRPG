package com.ohgiraffers.webrpg.user.application.dto;


import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private String name;
    private int totalHP;
    private int totalSTR;
    private int userLevel;
    private int upgradeLevel;
    private ElementalType elementalType;
}
