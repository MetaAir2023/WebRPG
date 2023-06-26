package com.ohgiraffers.webrpg.upgrade.domain.service.getResult;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import lombok.*;

//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Builder
@Getter
public class GetUserInfoResult {

    private String name;
    private int totalHP;
    private int totalSTR;
    private Money Money;
    private int userLevel;
    private int upgradeLevel;
    private ElementalType elementalType;
}
