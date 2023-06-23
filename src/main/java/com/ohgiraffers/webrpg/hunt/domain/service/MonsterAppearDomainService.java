package com.ohgiraffers.webrpg.hunt.domain.service;


import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.HuntMap;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainMapRepository;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import com.ohgiraffers.webrpg.hunt.infra.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MonsterAppearDomainService {


    private final DomainMapRepository domainMapRepositoryRepository;
    private final DomainRepository domainRepository;

    @Autowired
    private MonsterAppearDomainService(DomainMapRepository domainMapRepository, DomainRepository domainRepository){
        this.domainMapRepositoryRepository = domainMapRepository;
        this.domainRepository = domainRepository;
    }



    public Monster randomMapMonster(int mapNum){
        HuntMap map = domainMapRepositoryRepository.findMapBySequence(mapNum);
        double dNum = map.getStartMonNum() +
                (Math.random() * (map.getEndMonNum() - map.getStartMonNum() + 1));
        int iNum = (int) dNum;
        Monster monster = domainRepository.findMonsterBySequence(iNum);
        return monster;

    }



}
