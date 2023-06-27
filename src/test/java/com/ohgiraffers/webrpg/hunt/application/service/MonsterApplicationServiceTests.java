package com.ohgiraffers.webrpg.hunt.application.service;


import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterInfoDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class MonsterApplicationServiceTests {

    @Autowired
    private MonsterApplicationService monsterApplicationService;


    @Test
    public void applicationService(){
        assertNotNull(monsterApplicationService);
    }

    @Test
    public void getMonsterInfo(){
        int sequence = 1;
        MonsterInfoDTO monsterInfoDTO = monsterApplicationService.getMonInfo(sequence);

        assertEquals("네파리안", monsterInfoDTO.getMonsterName());



    }



}
