package com.ohgiraffers.webrpg.hunt.domain.service;

import com.ohgiraffers.webrpg.hunt.application.dto.GetElementalDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.MonsterStrDTO;
import com.ohgiraffers.webrpg.hunt.application.dto.NoEtDmgDTO;
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



    public double ElementCompatibility(int sequence ,GetElementalDTO getElementalDTO){
            Enum monET =getElementalDTO.getMonET();
            Enum userET = getElementalDTO.getUserET();
            int userUpLevel = getUserLevel(sequence);
        if (userET == ElementalType.FIRE && monET== MonsterET.FIRE){

            return 0;
        }if (userET == ElementalType.WATER && monET == MonsterET.WATER){

            return 0;
        }if (userET == ElementalType.GRASS && monET == MonsterET.GRASS){

            return 0;
        }
        return userUpLevel * 0.1;
    }
    public boolean compareET(GetElementalDTO getElementalDTO){
        Enum userET = getElementalDTO.getUserET();
        Enum monET = getElementalDTO.getMonET();
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
