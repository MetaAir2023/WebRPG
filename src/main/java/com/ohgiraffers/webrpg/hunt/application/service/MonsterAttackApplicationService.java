package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterAttackApplicationService {
    private final InfraRepository huntInfraRepository;
    @Autowired
    public MonsterAttackApplicationService(InfraRepository huntInfraRepository) {
        this.huntInfraRepository = huntInfraRepository;
    }

    public MonsterDTO getInfo(Monster monster) {
        return new MonsterDTO(
                monster.getMonsterName(),
                monster.getMonsterHp().getValue(),
                monster.getMonsterPower().getValue(),
                monster.getRewardExp().getValue(),
                monster.getRewardMoney().getValue(),
                monster.getMonElement()
        );
    }
    public MonsterAttackDTO initMonsterAttackDTO(MonsterDTO monster, UserInfoDTO user){
        MonsterAttackDTO monsterAttackDTO = new MonsterAttackDTO();
        monsterAttackDTO.setMonster(monster);
        monsterAttackDTO.setMonsterCurrentHP(monster.getMonsterHP());
        monsterAttackDTO.setUser(user);
        monsterAttackDTO.setUserCurrentHP(user.getTotalHP());
        return monsterAttackDTO;
    }

    public MonsterAttackDTO attackToUser(MonsterAttackDTO monsterAttackDTO) {
        int userHpAfterAttack = monsterAttackDTO.getUserCurrentHP() - monsterAttackDTO.getMonster().getMonsterPower();
        monsterAttackDTO.setUserCurrentHP(userHpAfterAttack);
        return monsterAttackDTO;
    }
}
