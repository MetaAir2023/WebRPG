package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.hunt.domain.service.UserAttackDomainService;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.RecursiveTask;

@Service
public class UserAttackApplicationService {

    private final UserAttackDomainService userAttackDomainService;

    private final InMemoryUserRepository inMemoryUserRepository;
    private final HuntElementalDamage huntElementalDamage;

    @Autowired
    public UserAttackApplicationService(UserAttackDomainService userAttackDomainService
                                      , InMemoryUserRepository inMemoryUserRepository
                                      , HuntElementalDamage huntElementalDamage){
        this.userAttackDomainService = userAttackDomainService;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.huntElementalDamage = huntElementalDamage;
    }

    public UserInfoDTO getUserInfo(User user) {
        return new UserInfoDTO(user.getSequence(),
                                user.getName(),
                                user.getDefaultHP(),
                                user.getDefaultSTR(),
                                user.getMoney(),
                                user.getLevel(),
                                user.getUpgradeLevel(),
                                user.getElementalType());
    }




    public UserAttackDTO initUserAttackDTO(Monster monster, UserInfoDTO userInfoDTO) {
        UserAttackDTO userAttackDTO = new UserAttackDTO();
        userAttackDTO.setUserInfoDTO(userInfoDTO);
        userAttackDTO.setUserCurrentHP(userInfoDTO.getTotalHP());
        userAttackDTO.setMonster(monster);
        userAttackDTO.setMonsterCurrentHP(monster.getMonsterHp());


        return userAttackDTO;
    }

    public UserAttackDTO attackToMonster(UserAttackDTO userAttackDTO, int sequence,GetElementalDTO getElementalDTO) {
        int monsterHpAfterAttack = (int)((double)userAttackDTO.getMonsterCurrentHP().getValue() - userAttackDTO.getUserInfoDTO().getTotalSTR() * huntElementalDamage.totalPercentage(sequence, getElementalDTO));
        userAttackDTO.setMonsterCurrentHP(new MonsterHp(monsterHpAfterAttack));
        return userAttackDTO;
    }

    public UserPatternDTO initUserPatternDTO() {
        UserPatternDTO userPatternDTO = new UserPatternDTO();

        userPatternDTO.setAttackCnt(0);
        userPatternDTO.setHeal(0);

        return userPatternDTO;
    }

    public IntegrateUserAttackDTO attackPattern(IntegrateUserAttackDTO integrateUserAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO, GetElementalDTO getElementalDTO) {
        UserAttackDTO userAttackDTO = integrateUserAttackDTO.getUserAttackDTO();
        UserPatternDTO userPatternDTO = integrateUserAttackDTO.getUserPatternDTO();

        int healStandard = (int)(userAttackDTO.getUserStatDTO().getTotalHP() * 0.3);
        if(userPatternDTO.getAttackCnt() != 0 && (userPatternDTO.getAttackCnt() % 3) == 0) {
            if(userAttackDTO.getUserCurrentHP() <= healStandard && userPatternDTO.getHeal() <= 0) {
                int heal = (int)(userAttackDTO.getUserInfoDTO().getTotalHP() * 0.1);
                userAttackDTO.setUserCurrentHP(userAttackDTO.getUserCurrentHP() + heal);
                userPatternDTO.setHeal(userPatternDTO.getHeal() + 1);
            } else {
                userAttackDTO = attackToMonster(userAttackDTO , sequence, getElementalDTO);
                userAttackDTO = attackToMonster(userAttackDTO , sequence, getElementalDTO);
                userPatternDTO.setAttackCnt(userPatternDTO.getAttackCnt() + 2);
            }
        } else {
            userAttackDTO = attackToMonster(userAttackDTO, sequence , getElementalDTO);
            userPatternDTO.setAttackCnt(userPatternDTO.getAttackCnt() + 1);
        }
        integrateUserAttackDTO.setUserAttackDTO(userAttackDTO);
        integrateUserAttackDTO.setUserPatternDTO(userPatternDTO);

        return integrateUserAttackDTO;
    }

    public IntegrateUserAttackDTO initIntegrateUserAttackDTO(UserAttackDTO userAttackDTO, UserPatternDTO userPatternDTO) {
        IntegrateUserAttackDTO integrateUserAttackDTO = new IntegrateUserAttackDTO();
        integrateUserAttackDTO.setUserAttackDTO(userAttackDTO);
        integrateUserAttackDTO.setUserPatternDTO(userPatternDTO);

        return integrateUserAttackDTO;
    }
}
