package com.ohgiraffers.webrpg.hunt.domain.repository;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.HuntMap;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainMapRepository {
    HuntMap findMapBySequence(int sequence);
}
