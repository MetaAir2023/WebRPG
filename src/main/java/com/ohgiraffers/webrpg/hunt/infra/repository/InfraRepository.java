package com.ohgiraffers.webrpg.hunt.infra.repository;

import com.ohgiraffers.webrpg.database.MonsterDB;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.vo.MonsterHp;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InfraRepository implements DomainRepository {

    private static final MonsterDB monsterDB;
    static {monsterDB = new MonsterDB();}


    @Override
    public Monster findMonsterBySequence(int sequence) {
        return monsterDB.findMonsterBySequence(sequence);
    }
    public MonsterHp getMonsterDefaultHpBySequence(int sequence){
        return monsterDB.findMonsterBySequence(sequence).getMonsterHp();
    }
}
