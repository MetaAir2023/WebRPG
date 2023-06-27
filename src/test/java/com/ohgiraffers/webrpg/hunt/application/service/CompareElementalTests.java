package com.ohgiraffers.webrpg.hunt.application.service;


import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.hunt.application.dto.GetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserGetElementalDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class CompareElementalTests {

    @Autowired
    private HuntElementalDamage huntElementalDamage;

    @Test
    public void HuntElementalDamageTest(){

        assertNotNull(huntElementalDamage);

    }

    @Test
    public void compareElemental(){
        ElementalType userET = ElementalType.FIRE;
        MonsterET monET = MonsterET.WATER;
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        getElementalDTO.setUserET(userET);
        getElementalDTO.setMonET(monET);

        double result = huntElementalDamage.totalPercentage(1,getElementalDTO);

        System.out.println(result);


    }
}
