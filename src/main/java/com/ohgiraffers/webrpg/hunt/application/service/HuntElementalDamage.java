package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import com.ohgiraffers.webrpg.hunt.domain.service.HuntExtraElemental;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntElementalDamage {

    private final HuntExtraElemental huntExtraElemental;

    @Autowired
    public HuntElementalDamage(HuntExtraElemental huntExtraElemental){
        this.huntExtraElemental = huntExtraElemental;

    }




    public double totalPercentage(int sequence, GetElementalDTO getElementalDTO){
        int userUpLevel = huntExtraElemental.getUserLevel(sequence);
        if(huntExtraElemental.compareET(getElementalDTO)){
            return 1 + (userUpLevel * 0.1);
        }
        if(huntExtraElemental.ElementCompatibility(sequence,getElementalDTO)){
            return 1;
        }
        return userUpLevel * 0.1;
    }

    public double monsterPercentage(int sequence, GetElementalDTO getElementalDTO){

        if(huntExtraElemental.compareET(getElementalDTO)){
            return 0.9;
        }
        if(huntExtraElemental.ElementCompatibility(sequence,getElementalDTO)){
            return 1;
        }
        return 1.1;
    }

}
