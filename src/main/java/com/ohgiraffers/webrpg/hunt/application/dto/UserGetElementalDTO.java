package com.ohgiraffers.webrpg.hunt.application.dto;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGetElementalDTO {

    private ElementalType userET;
}
