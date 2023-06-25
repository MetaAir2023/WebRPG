package com.ohgiraffers.webrpg.user.application.dto;


import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
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
    private Money money;
    private int userLevel;
    private int upgradeLevel;
    private ElementalType elementalType;
}
