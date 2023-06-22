package com.ohgiraffers.webrpg.hunt.domain.repository;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository {

    Monster findMonsterBySequence(int sequence);

}
