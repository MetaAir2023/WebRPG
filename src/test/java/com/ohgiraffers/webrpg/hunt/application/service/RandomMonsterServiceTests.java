package com.ohgiraffers.webrpg.hunt.application.service;


import com.ohgiraffers.webrpg.configuration.Application;
import com.ohgiraffers.webrpg.hunt.application.dto.RandomMonsterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
public class RandomMonsterServiceTests {
    @Autowired
    private MonsterAppearApplicationService monsterAppearApplicationService;


    @Test
    public void monsterAppearApplicationService(){
        assertNotNull(monsterAppearApplicationService);
    }
    @Test
    public void randomMonTest(){
        int num = 1;
        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(num);
        System.out.println("몬스터의 이름 : " + randomMonsterDTO.getMonsterName());
        assertNotNull(randomMonsterDTO);
    }
    @Test
    public void allRandom(){
        int num = 3;
        boolean flag = false;
        boolean[] arr = new boolean[7];
        loop : while (true) {
            RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(num);
            int sequence = randomMonsterDTO.getSequence() - 15;

            if (!arr[sequence]) {
                arr[sequence] = true;
            }
            for (int i = 0; i< arr.length; i++){
                if (!arr[i]){
                    break;
                }
                if (i == arr.length-1 && arr[i]){
                    flag = true;
                    break loop;
                }

            }

        }
        System.out.println(Arrays.toString(arr));
        assertEquals(true,flag);





    }

}
