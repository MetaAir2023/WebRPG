package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.IntegrateUserAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserAttackDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserGetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserPatternDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
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






    public UserAttackDTO initUserAttackDTO(Monster monster, UserInfoDTO userInfoDTO) {
        UserAttackDTO userAttackDTO = new UserAttackDTO();
        userAttackDTO.setUserInfoDTO(userInfoDTO);
        userAttackDTO.setUserCurrentHP(userInfoDTO.getTotalHP());
        userAttackDTO.setMonster(monster);
        userAttackDTO.setMonsterCurrentHP(monster.getMonsterHp());


        return userAttackDTO;
    }

    public UserAttackDTO attackToMonster(UserAttackDTO userAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO) {
        int monsterHpAfterAttack = (int)((double)userAttackDTO.getMonsterCurrentHP() - userAttackDTO.getUserInfoDTO().getTotalSTR() * huntElementalDamage.)

        return userAttackDTO;
    }

    public UserPatternDTO initUserPatternDTO() {
        UserPatternDTO userPatternDTO = new UserPatternDTO();

        userPatternDTO.setAttackCnt(0);
        userPatternDTO.setHeal(0);

        return userPatternDTO;
    }

    public IntegrateUserAttackDTO attackPattern(IntegrateUserAttackDTO integrateUserAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO) {
        UserAttackDTO userAttackDTO = integrateUserAttackDTO.getUserAttackDTO();
        UserPatternDTO userPatternDTO = integrateUserAttackDTO.getUserPatternDTO();

        int healStandard = (int)(userAttackDTO.getUserStatDTO().getTotalHP() * 0.3);
        if(userPatternDTO.getAttackCnt() != 0 && (userPatternDTO.getAttackCnt() % 3) == 0) {
            if(userAttackDTO.getUserCurrentHP() <= healStandard && userPatternDTO.getHeal() <= 0) {
                int heal = (int)(userAttackDTO.getUserInfoDTO().getTotalHP() * 0.1);
                userAttackDTO.setUserCurrentHP(userAttackDTO.getUserCurrentHP() + heal);
                userPatternDTO.setHeal(userPatternDTO.getHeal() + 1);
            } else {
                userAttackDTO = attackToMonster(userAttackDTO , sequence, userGetElementalDTO);
                userAttackDTO = attackToMonster(userAttackDTO , sequence, userGetElementalDTO);
                userPatternDTO.setAttackCnt(userPatternDTO.getAttackCnt() + 2);
            }
        } else {
            userAttackDTO = attackToMonster(userAttackDTO, sequence , userGetElementalDTO);
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
