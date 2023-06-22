package com.ohgiraffers.webrpg.user.domain.aggregate.entity;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;

public class User {

    private int sequence;
    private String name;
    private int defaultHP;
    private int defaultSTR;
    private Money money;
    private int upgradeLevel;
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
