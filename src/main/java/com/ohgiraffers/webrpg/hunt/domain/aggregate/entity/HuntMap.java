package com.ohgiraffers.webrpg.hunt.domain.aggregate.entity;

public class HuntMap {
    private int sequence;
    private String mapName;
    private int startMonNum;
    private int endMonNum;



    public HuntMap(int sequence,String mapName, int startMonNum,int endMonNum){
        this.sequence = sequence;
        this.mapName = mapName;
        this.startMonNum = startMonNum;
        this.endMonNum = endMonNum;
    }

    public int getStartMonNum() {
        return startMonNum;
    }

    public int getEndMonNum() {
        return endMonNum;
    }
}
