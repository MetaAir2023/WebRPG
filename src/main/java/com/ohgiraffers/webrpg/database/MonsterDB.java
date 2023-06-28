package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;

import java.util.HashMap;
import java.util.Map;

public class MonsterDB {
    private final Map<Integer, Monster> monsterList;

    public MonsterDB(){
        this.monsterList = new HashMap<>();
        this.monsterList.put(1, new Monster(1,"네파리안", 20, 20, 1,1,MonsterET.FIRE));
        this.monsterList.put(2, new Monster(2,"크툰", 300, 300, 50, 50,MonsterET.WATER));
        // 오리지널 중간
        this.monsterList.put(3, new Monster(3,"무적의 오시리안", 50, 50, 5, 5,MonsterET.GRASS));
        this.monsterList.put(4, new Monster(4,"오닉시아", 70, 70, 7, 7,MonsterET.FIRE));
        this.monsterList.put(5, new Monster(5,"학카르", 150, 150, 15, 15,MonsterET.GRASS));
        this.monsterList.put(6, new Monster(6,"켈투자드", 500, 500, 70,70,MonsterET.WATER));
        /// 오리지널 최종
        this.monsterList.put(7, new Monster(7,"일리단 스톰레이지", 700, 700, 100, 100,MonsterET.GRASS));
        // 불타는 성전 중간
        this.monsterList.put(8, new Monster(8,"용 학살자 그룰", 200, 200, 20, 20,MonsterET.FIRE));
        this.monsterList.put(9, new Monster(9,"마그테리돈", 300, 300, 30, 30,MonsterET.GRASS));
        this.monsterList.put(10, new Monster(10,"여군주 바쉬", 400, 400, 40, 40,MonsterET.GRASS));
        this.monsterList.put(11, new Monster(11,"줄진", 500, 500, 50, 50,MonsterET.GRASS));
        this.monsterList.put(12, new Monster(12,"공작 말체자르", 600, 600, 60, 60,MonsterET.GRASS));
        this.monsterList.put(13, new Monster(13,"파멸의 어둠", 700, 700, 70, 70,MonsterET.FIRE));
        this.monsterList.put(14, new Monster(14,"킬제덴", 1500, 1500, 200, 200,MonsterET.FIRE));
       /// 불타는 성전 최종
        this.monsterList.put(15, new Monster(15,"캘타스 선스트라이더", 1000, 1000, 100, 100,MonsterET.FIRE));
        this.monsterList.put(16, new Monster(16,"할리온", 1200, 1200, 120, 120,MonsterET.FIRE));
        this.monsterList.put(17, new Monster(17,"말리고스", 1500, 1500, 150, 150,MonsterET.WATER));
        this.monsterList.put(18, new Monster(18,"요그사론", 3000, 3000, 500, 500,MonsterET.WATER));
        // 리치왕의 분노 중간
        this.monsterList.put(19, new Monster(19,"관찰자 알갈론", 2000, 2000, 200, 200,MonsterET.WATER));
        this.monsterList.put(20, new Monster(20,"살타리온", 2500, 2500, 250, 250,MonsterET.FIRE));
        this.monsterList.put(21, new Monster(21,"리치 왕", 7000, 7000, 900, 900,MonsterET.WATER));
        /// 리치왕의 분노 최종
        this.monsterList.put(22, new Monster(22,"초갈", 4000, 4000, 400, 400,MonsterET.FIRE));
        this.monsterList.put(23, new Monster(23,"시네스트라", 7000, 7000, 700, 700,MonsterET.GRASS));
        this.monsterList.put(24, new Monster(24,"알아키르", 10000, 10000, 1000, 1000,MonsterET.WATER));
        this.monsterList.put(25, new Monster(25,"라그나로스", 15000, 10000, 1800, 1800,MonsterET.FIRE));
        // 대격변 중간
        this.monsterList.put(26, new Monster(26,"데스윙", 25000, 25000, 2500, 2500,MonsterET.FIRE));
        /// 대격변 최종
        this.monsterList.put(27, new Monster(27,"셰크지르", 15000, 15000, 1500, 1500,MonsterET.GRASS));
        this.monsterList.put(28, new Monster(28,"황제의 의지", 20000, 20000, 2000, 2000,MonsterET.GRASS));
        this.monsterList.put(29, new Monster(29,"공포의 샤", 30000, 30000, 3000, 3000,MonsterET.FIRE));
        this.monsterList.put(30, new Monster(30,"레이 션", 50000, 50000, 6000, 6000,MonsterET.WATER));
        // 판다리아의 안개 중간
        this.monsterList.put(31, new Monster(31,"라덴", 40000, 40000, 4000, 4000,MonsterET.GRASS));
        this.monsterList.put(32, new Monster(32,"가로쉬 헬스크림", 50000, 50000, 5000, 5000, MonsterET.FIRE));
        this.monsterList.put(33, new Monster(33,"이샤라즈", 80000, 80000, 10000, 10000,MonsterET.GRASS));
        /// 판다리아의 안개 최종
        this.monsterList.put(34, new Monster(34,"높은군주 마르고크", 60000, 60000, 6000, 6000,MonsterET.GRASS));
        this.monsterList.put(35, new Monster(35,"블랙핸드", 120000, 120000, 15000, 15000,MonsterET.FIRE));
        // 드레노어의 전쟁군주 중간
        this.monsterList.put(36, new Monster(36,"아키몬드", 150000, 150000, 20000, 20000,MonsterET.FIRE));
        /// 드레노어의 전쟁군주 최종
        this.monsterList.put(37, new Monster(37,"자비우스", 90000, 90000, 9000, 9000,MonsterET.FIRE));
        this.monsterList.put(38, new Monster(38,"헬리아", 150000, 150000, 15000, 15000,MonsterET.WATER));
        this.monsterList.put(39, new Monster(39,"굴단", 200000, 200000, 20000, 20000,MonsterET.FIRE));
        this.monsterList.put(40, new Monster(40,"살게라스의 무덤 킬제덴", 350000, 350000, 50000, 50000,MonsterET.FIRE));
        // 군단 중간
        this.monsterList.put(41, new Monster(41,"사멸자 아르거스", 500000, 500000, 70000, 70000,MonsterET.WATER));
        /// 군단 최종
        this.monsterList.put(42, new Monster(42,"그훈", 300000, 300000, 30000, 30000,MonsterET.GRASS));
        this.monsterList.put(43, new Monster(43,"여군주 제이나 프라우드무어", 500000, 500000, 50000, 50000,MonsterET.WATER));
        this.monsterList.put(44, new Monster(44,"공허의 전령 우우나트", 800000, 800000, 80000, 80000,MonsterET.FIRE));
        this.monsterList.put(45, new Monster(45,"여왕 아즈샤라", 1200000, 1200000, 130000, 130000,MonsterET.WATER));
        // 격전의 아제로스 중간
        this.monsterList.put(46, new Monster(46,"타락자 느조스", 1500000, 1500000, 170000, 170000,MonsterET.WATER));
        /// 격전의 아제로스 최종
        this.monsterList.put(47, new Monster(47,"대영주 데나트리우스", 1300000, 1300000, 130000, 130000,MonsterET.FIRE));
        this.monsterList.put(48, new Monster(48,"실바나스 윈드러너", 2000000, 2000000, 220000, 220000,MonsterET.WATER));
        // 어둠땅 중간
        this.monsterList.put(49, new Monster(49,"간수-조바알", 3000000, 3000000, 400000, 400000,MonsterET.WATER));
        ///어둠땅 최종
        this.monsterList.put(50, new Monster(50,"폭풍포식자 라자게스", 2500000, 2500000, 250000, 250000,MonsterET.WATER));
        this.monsterList.put(51, new Monster(51,"비늘사령관 사카레스", 5000000, 5000000, 0, 2000000,MonsterET.GRASS));
        //용군단 중간
    }

    public Monster findMonsterBySequence(int sequence){
        return monsterList.get(sequence);
    }
}
