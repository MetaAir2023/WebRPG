package com.ohgiraffers.webrpg.hunt.application.service;

import com.ohgiraffers.webrpg.hunt.application.dto.ElementDamageDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.GetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterStrDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.NoEtDmgDTO;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import com.ohgiraffers.webrpg.hunt.domain.service.HuntExtraElemental;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntElementalDamage {

    private final HuntExtraElemental huntExtraElemental;
    private final UserRepository userRepository;
    private final DomainRepository domainRepository;
    @Autowired
    public HuntElementalDamage(HuntExtraElemental huntExtraElemental, UserRepository userRepository, DomainRepository domainRepository){
        this.huntExtraElemental = huntExtraElemental;
        this.userRepository = userRepository;
        this.domainRepository = domainRepository;
    }



    public ElementDamageDTO extraDamage(int sequence ,NoEtDmgDTO noEtDmg,
                                        GetElementalDTO getElementalDTO,
                                        MonsterStrDTO monsterStrDTO){
        ElementDamageDTO damageDTO = new ElementDamageDTO();
        int monATK = noEtDmg.getMonsterATK();
        double userATK = noEtDmg.getUserATK() * totalPercentage(sequence,getElementalDTO);

        damageDTO.setMonsterATK(monATK);
        damageDTO.setUserATK(userATK);


        return damageDTO;

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


}
