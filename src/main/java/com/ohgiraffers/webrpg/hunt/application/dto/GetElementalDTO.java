package com.ohgiraffers.webrpg.hunt.application.dto;


import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class GetElementalDTO {
    private ElementalType userET;
    private MonsterET monET;
}
