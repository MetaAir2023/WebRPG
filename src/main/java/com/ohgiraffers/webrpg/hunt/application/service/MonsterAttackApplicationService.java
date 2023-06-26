package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterAttackApplicationService {
    private final InfraRepository huntInfraRepository;
    private final HuntElementalDamage huntElementalDamage;

    @Autowired
    public MonsterAttackApplicationService(InfraRepository huntInfraRepository, HuntElementalDamage huntElementalDamage) {
        this.huntInfraRepository = huntInfraRepository;
        this.huntElementalDamage = huntElementalDamage;
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

    public MonsterPatternDTO initMonsterPatternDTO(){
        MonsterPatternDTO monsterPatternDTO = new MonsterPatternDTO();
        monsterPatternDTO.setAttackCnt(0);
        monsterPatternDTO.setHeal(0);
        return monsterPatternDTO;
    }

    public IntegrateMonsterAttackDTO initIntegrateMonsterAttackDTO(MonsterAttackDTO monsterAttackDTO, MonsterPatternDTO monsterPatternDTO){
        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = new IntegrateMonsterAttackDTO();
        integrateMonsterAttackDTO.setMonsterAttackDTO(monsterAttackDTO);
        integrateMonsterAttackDTO.setMonsterPatternDTO(monsterPatternDTO);
        return integrateMonsterAttackDTO;
    }

    public MonsterAttackDTO attackToUser(MonsterAttackDTO monsterAttackDTO, int sequence, GetElementalDTO getElementalDTO) {

        int userHpAfterAttack = (int)((double)monsterAttackDTO.getUserCurrentHP() - monsterAttackDTO.getMonster().getMonsterPower() * huntElementalDamage.monsterPercentage(sequence, getElementalDTO));
        monsterAttackDTO.setUserCurrentHP(userHpAfterAttack);
        return monsterAttackDTO;
    }

    public IntegrateMonsterAttackDTO attackPattern(IntegrateMonsterAttackDTO integrateMonsterAttackDTO, int sequence, GetElementalDTO getElementalDTO){
        MonsterAttackDTO monsterAttackDTO = integrateMonsterAttackDTO.getMonsterAttackDTO();
        MonsterPatternDTO monsterPatternDTO = integrateMonsterAttackDTO.getMonsterPatternDTO();
        int healStandard = (int)(monsterAttackDTO.getMonster().getMonsterHP() * 0.3);
        if(monsterPatternDTO.getAttackCnt() != 0 && (monsterPatternDTO.getAttackCnt() % 3) == 0) {
            if (monsterAttackDTO.getMonsterCurrentHP() <= healStandard && monsterPatternDTO.getHeal() <= 0) {
                int heal = (int) (monsterAttackDTO.getMonster().getMonsterHP() * 0.1);
                monsterAttackDTO.setMonsterCurrentHP(monsterAttackDTO.getMonsterCurrentHP() + heal);
                monsterPatternDTO.setHeal(monsterPatternDTO.getHeal()+1);
            } else {
                monsterAttackDTO = attackToUser(monsterAttackDTO, sequence, getElementalDTO);
                monsterAttackDTO = attackToUser(monsterAttackDTO, sequence, getElementalDTO);
                monsterPatternDTO.setAttackCnt(monsterPatternDTO.getAttackCnt() + 2);
            }
        }
        else{
            monsterAttackDTO = attackToUser(monsterAttackDTO, sequence, getElementalDTO);
            monsterPatternDTO.setAttackCnt(monsterPatternDTO.getAttackCnt() + 1);
        }
        integrateMonsterAttackDTO.setMonsterAttackDTO(monsterAttackDTO);
        integrateMonsterAttackDTO.setMonsterPatternDTO(monsterPatternDTO);

        return integrateMonsterAttackDTO;
    }
}
