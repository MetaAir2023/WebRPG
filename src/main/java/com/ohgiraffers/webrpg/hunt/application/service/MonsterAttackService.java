package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterAttackDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MonsterAttackService {
    private final InfraRepository huntInfraRepository;

    @Autowired
    public MonsterAttackService(InfraRepository huntInfraRepository) {
        this.huntInfraRepository = huntInfraRepository;
    }

    public MonsterAttackDTO initMonsterAttackDTO(Monster monster, UserInfoDTO user){
        MonsterAttackDTO monsterAttackDTO = new MonsterAttackDTO();
        monsterAttackDTO.setMonster(monster);
        monsterAttackDTO.setMonsterCurrentHP(monster.getMonsterHp());
        monsterAttackDTO.setUser(user);
        monsterAttackDTO.setUserCurrentHP(user.getTotalHP());
        return monsterAttackDTO;
    }

    public MonsterAttackDTO attackToUser(MonsterAttackDTO monsterAttackDTO) {
        int userHpAfterAttack = monsterAttackDTO.getUserCurrentHP() - monsterAttackDTO.getMonster().getMonsterPower().getValue();
        monsterAttackDTO.setUserCurrentHP(userHpAfterAttack);
        return monsterAttackDTO;
    }
}
