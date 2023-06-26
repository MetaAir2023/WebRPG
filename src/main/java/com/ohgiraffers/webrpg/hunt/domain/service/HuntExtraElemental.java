package com.ohgiraffers.webrpg.hunt.domain.service;

import com.ohgiraffers.webrpg.hunt.application.dto.GetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterStrDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.NoEtDmgDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.UserGetElementalDTO;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.infra.service.CalcExtraService;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntExtraElemental {

    private final CalcExtraService calcExtraService;
    @Autowired
    public HuntExtraElemental(CalcExtraService calcExtraService){
        this.calcExtraService = calcExtraService;
    }



    public boolean ElementCompatibility(int sequence ,GetElementalDTO getElementalDTO){
            MonsterET monET =getElementalDTO.getMonET();
            ElementalType userET = getElementalDTO.getUserET();
            int userUpLevel = getUserLevel(sequence);
        if (userET == ElementalType.FIRE && monET== MonsterET.FIRE){

            return true;
        }if (userET == ElementalType.WATER && monET == MonsterET.WATER){

            return true;
        }if (userET == ElementalType.GRASS && monET == MonsterET.GRASS){

            return true;
        }
        return false;
    }
    public boolean compareET(GetElementalDTO getElementalDTO){
        ElementalType userET = getElementalDTO.getUserET();
        MonsterET monET = getElementalDTO.getMonET();
        if (userET == ElementalType.FIRE && monET== MonsterET.GRASS){

            return true;
        }if (userET == ElementalType.WATER && monET == MonsterET.FIRE){

            return true;
        }if (userET == ElementalType.GRASS && monET == MonsterET.WATER){

            return true;
        }
        return false;
    }

    public NoEtDmgDTO TotalDmg(int sequence, MonsterStrDTO monsterStrDTO){
        NoEtDmgDTO totalDmgDTO = calcExtraService.getDMGStat(sequence,monsterStrDTO);

        return totalDmgDTO;

    }
    public int getUserLevel(int sequence){
        int userUpLevel = calcExtraService.getUserUpgrade(sequence);
        return userUpLevel;
    }


}
