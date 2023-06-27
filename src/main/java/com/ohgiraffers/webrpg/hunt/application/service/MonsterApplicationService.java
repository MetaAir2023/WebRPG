package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterInfoDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonsterApplicationService {

    private final DomainRepository domainRepository;

    @Autowired
    private MonsterApplicationService(DomainRepository domainRepository){
        this.domainRepository = domainRepository;
    }


    public Monster getMonsterBySequence(int sequence){
        return domainRepository.findMonsterBySequence(sequence);
    }

    public MonsterInfoDTO getMonInfo(int sequence){
        Monster monster = getMonsterBySequence(sequence);
        return new MonsterInfoDTO(monster.getSequence(),
                monster.getMonsterName(),
                monster.getMonsterHp().getValue(),
                monster.getMonsterPower().getValue(),
                monster.getRewardExp().getValue(),
                monster.getRewardMoney().getValue(),
                monster.getMonElement()
        );
    }



}
