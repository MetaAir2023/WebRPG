package com.ohgiraffers.webrpg.hunt.application.dto;


import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ElementDamageDTO {

    private double userATK;
    private int monsterATK;

}
