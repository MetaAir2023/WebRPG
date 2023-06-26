package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;

import java.util.HashMap;
import java.util.Map;

public class MonsterDB {
    private final Map<Integer, Monster> monsterList;

    public MonsterDB(){
        this.monsterList = new HashMap<>();
        this.monsterList.put(1, new Monster(1,"네파리안", 1, 1, 1,100,MonsterET.FIRE));
        this.monsterList.put(2, new Monster(2,"크툰", 100, 100, 100, 400,MonsterET.WATER));
        // 오리지널 중간
        this.monsterList.put(3, new Monster(3,"무적의 오시리안", 5, 5, 5, 200,MonsterET.GRASS));
        this.monsterList.put(4, new Monster(4,"오닉시아", 5, 5, 5, 250,MonsterET.FIRE));
        this.monsterList.put(5, new Monster(5,"학카르", 5, 5, 5, 300,MonsterET.GRASS));
        this.monsterList.put(6, new Monster(6,"켈투자드", 5, 5, 5,1000,MonsterET.WATER));
        /// 오리지널 최종
        this.monsterList.put(7, new Monster(7,"일리단 스톰레이지", 5, 5, 5, 100,MonsterET.GRASS));
        // 불타는 성전 중간
        this.monsterList.put(8, new Monster(8,"용 학살자 그룰", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(9, new Monster(9,"마그테리돈", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(10, new Monster(10,"여군주 바쉬", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(11, new Monster(11,"줄진", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(12, new Monster(12,"공작 말체자르", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(13, new Monster(13,"파멸의 어둠", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(14, new Monster(14,"킬제덴", 5, 5, 5, 100,MonsterET.FIRE));
       /// 불타는 성전 최종
        this.monsterList.put(15, new Monster(15,"캘타스 선스트라이더", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(16, new Monster(16,"할리온", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(17, new Monster(17,"말리고스", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(18, new Monster(18,"요그사론", 5, 5, 5, 100,MonsterET.WATER));
        // 리치왕의 분노 중간
        this.monsterList.put(19, new Monster(19,"관찰자 알갈론", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(20, new Monster(20,"살타리온", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(21, new Monster(21,"리치 왕", 5, 5, 5, 100,MonsterET.WATER));
        /// 리치왕의 분노 최종
        this.monsterList.put(22, new Monster(22,"초갈", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(23, new Monster(23,"시네스트라", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(24, new Monster(24,"알아키르", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(25, new Monster(25,"라그나로스", 5, 5, 5, 100,MonsterET.FIRE));
        // 대격변 중간
        this.monsterList.put(26, new Monster(26,"데스윙", 5, 5, 5, 100,MonsterET.FIRE));
        /// 대격변 최종
        this.monsterList.put(27, new Monster(27,"셰크지르", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(28, new Monster(28,"황제의 의지", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(29, new Monster(29,"공포의 샤", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(30, new Monster(30,"레이 션", 5, 5, 5, 100,MonsterET.WATER));
        // 판다리아의 안개 중간
        this.monsterList.put(31, new Monster(31,"라덴", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(32, new Monster(32,"가로쉬 헬스크림", 5, 5, 5, 100, MonsterET.FIRE));
        this.monsterList.put(33, new Monster(33,"이샤라즈", 5, 5, 5, 100,MonsterET.GRASS));
        /// 판다리아의 안개 최종
        this.monsterList.put(34, new Monster(34,"높은군주 마르고크", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(35, new Monster(35,"블랙핸드", 5, 5, 5, 100,MonsterET.FIRE));
        // 드레노어의 전쟁군주 중간
        this.monsterList.put(36, new Monster(36,"아키몬드", 5, 5, 5, 100,MonsterET.FIRE));
        /// 드레노어의 전쟁군주 최종
        this.monsterList.put(37, new Monster(37,"자비우스", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(38, new Monster(38,"헬리아", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(39, new Monster(39,"굴단", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(40, new Monster(40,"살게라스의 무덤 킬제덴", 5, 5, 5, 100,MonsterET.FIRE));
        // 군단 중간
        this.monsterList.put(41, new Monster(41,"사멸자 아르거스", 5, 5, 5, 100,MonsterET.WATER));
        /// 군단 최종
        this.monsterList.put(42, new Monster(42,"그훈", 5, 5, 5, 100,MonsterET.GRASS));
        this.monsterList.put(43, new Monster(43,"여군주 제이나 프라우드무어", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(44, new Monster(44,"공허의 전령 우우나트", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(45, new Monster(45,"여왕 아즈샤라", 5, 5, 5, 100,MonsterET.WATER));
        // 격전의 아제로스 중간
        this.monsterList.put(46, new Monster(46,"타락자 느조스", 5, 5, 5, 100,MonsterET.WATER));
        /// 격전의 아제로스 최종
        this.monsterList.put(47, new Monster(47,"대영주 데나트리우스", 5, 5, 5, 100,MonsterET.FIRE));
        this.monsterList.put(48, new Monster(48,"실바나스 윈드러너", 5, 5, 5, 100,MonsterET.WATER));
        // 어둠땅 중간
        this.monsterList.put(49, new Monster(49,"간수-조바알", 5, 5, 5, 100,MonsterET.WATER));
        ///어둠땅 최종
        this.monsterList.put(50, new Monster(50,"폭풍포식자 라자게스", 5, 5, 5, 100,MonsterET.WATER));
        this.monsterList.put(51, new Monster(51,"비늘사령관 사카레스", 5, 5, 5, 100,MonsterET.GRASS));
        //용군단 중간
    }

    public Monster findMonsterBySequence(int sequence){
        return monsterList.get(sequence);
    }
}
