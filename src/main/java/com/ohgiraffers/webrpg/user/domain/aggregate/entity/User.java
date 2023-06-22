package com.ohgiraffers.webrpg.user.domain.aggregate.entity;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;

public class User {

    // TODO VO를 통한 level값에 따른 유저 HP, STR, defensivePower 값 처리 [#20]
    private final int sequence;
    private final String name;
    private int defaultHP;
    private int defaultSTR;
    private Money money;
    private int upgradeLevel;
    private int level;
    private int experiencePoint;
    private ElementalType elementalType;

    public User(int sequence, String name, int defaultHP, int defaultSTR, Money money, int upgradeLevel, ElementalType elementalType) {
        this.sequence = sequence;
        this.name = name;
        this.defaultHP = defaultHP;
        this.defaultSTR = defaultSTR;
        this.money = money;
        this.upgradeLevel = upgradeLevel;
        this.elementalType = elementalType;
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public ElementalType getElementalType() {
        return elementalType;
    }

    public int getDefaultSTR() {
        return defaultSTR;
    }

    public String getName() {
        return name;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public int getSequence() {
        return sequence;
    }

    public Money getMoney() {
        return money;
    }

    public void setDefaultHP(int defaultHP) {
        this.defaultHP = defaultHP;
    }

    public void setDefaultSTR(int defaultSTR) {
        this.defaultSTR = defaultSTR;
    }

    public void setElementalType(ElementalType elementalType) {
        this.elementalType = elementalType;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public int getLevel() {
        return level;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User {" +
                "sequence =" + sequence +
                "name = " + name +
                "defaultHP =" + defaultHP +
                "defaultSTR =" + defaultSTR +
                "money ="  + money+
                "upgradeLevel =" + upgradeLevel +
                "elementalType =" + elementalType +
                '}';
    }
}
