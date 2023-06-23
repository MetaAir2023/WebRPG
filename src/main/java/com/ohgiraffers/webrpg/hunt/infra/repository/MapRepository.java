package com.ohgiraffers.webrpg.hunt.infra.repository;


import com.ohgiraffers.webrpg.database.MapDB;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.HuntMap;
import com.ohgiraffers.webrpg.hunt.domain.repository.DomainMapRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MapRepository implements DomainMapRepository {

    private static final MapDB mapDB;
    static {mapDB = new MapDB();}

    @Override
    public HuntMap findMapBySequence(int sequence) {
        return mapDB.findMapBySequence(sequence);
    }


}
