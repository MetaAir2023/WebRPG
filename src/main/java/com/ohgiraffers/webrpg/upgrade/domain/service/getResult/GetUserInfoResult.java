package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserInfoResult {

    private String name;
    private int totalHP;
    private int totalSTR;
    private Money Money;
    private int userLevel;
    private int upgradeLevel;
    private ElementalType elementalType;
}
