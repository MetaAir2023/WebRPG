package com.ohgiraffers.webrpg.hunt.infra.service;

import com.ohgiraffers.webrpg.hunt.application.dto.GetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterStrDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.NoEtDmgDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcExtraService {
    private final UserRepository<User> userRepository;

    private final UserApplicationService userApplicationService;
    @Autowired
    private CalcExtraService(UserRepository<User> userRepository,
                             UserApplicationService userApplicationService){


        this.userRepository = userRepository;
        this.userApplicationService = userApplicationService;

    }

    public GetElementalDTO getET(User user, Monster monster){
        GetElementalDTO playerET = new GetElementalDTO();
        ElementalType userET = user.getElementalType();
        MonsterET monET = monster.getMonElement();
        playerET.setMonET(monET);
        playerET.setUserET(userET);
        return playerET;

    }

    public NoEtDmgDTO getDMGStat(int sequence, MonsterStrDTO monsterStrDTO){
        User user = getUser(sequence);
        NoEtDmgDTO damageDTO = new NoEtDmgDTO();
        int monsterATK = monsterStrDTO.getMonsterSTR();
        int userATK = userApplicationService.getInfo(user).getTotalSTR();
        damageDTO.setUserATK(userATK);
        damageDTO.setMonsterATK(monsterATK);

        return damageDTO;
    }
    public User getUser(int sequence){
       User user = userRepository.findUserBySequence(sequence);
        return user;
    }
    public int getUserUpgrade(int sequence){
        User user = userRepository.findUserBySequence(sequence);
        int upLevel = user.getUpgradeLevel();
        return upLevel;
    }

}
