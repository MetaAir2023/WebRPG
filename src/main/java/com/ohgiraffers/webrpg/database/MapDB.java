package com.ohgiraffers.webrpg.database;

import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.HuntMap;

import java.util.HashMap;
import java.util.Map;

public class MapDB {
    private final Map<Integer, HuntMap> mapList;


    public MapDB(){
        this.mapList = new HashMap<>();
        this.mapList.put(1,new HuntMap(1, "낙스라마스",1,6));
        this.mapList.put(2,new HuntMap(2, "태양샘 고원",7,14));
        this.mapList.put(3,new HuntMap(3, "얼음왕관 성채",15,21));
        this.mapList.put(4,new HuntMap(4, "용의 영혼",22,26));
        this.mapList.put(5,new HuntMap(5, "오그리마",27,33));
        this.mapList.put(6,new HuntMap(6, "지옥불 성채",34,36));
        this.mapList.put(7,new HuntMap(7, "안토러스",37,41));
        this.mapList.put(8,new HuntMap(8, "나이알로사",42,46));
        this.mapList.put(9,new HuntMap(9, "태초의 존재의 매장터",47,49));
        this.mapList.put(10,new HuntMap(10, "아베루스",50,51));
    }
    public HuntMap findMapBySequence(int sequence){
        return mapList.get(sequence);
    }

}
