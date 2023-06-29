package com.ohgiraffers.webrpg.user.domain.aggregate.entity;

import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Password;

public class User {

    private final int sequence;
    private final String name;
    private final Password password;
    private int defaultHP;
    private int defaultSTR;
    private Money money;
    private int upgradeLevel;
    private int level;
    private int experiencePoint;
    private ElementalType elementalType;

    public User(int sequence, String name, Password password, int defaultHP, int defaultSTR, Money money,int level, int  experiencePoint, int upgradeLevel, ElementalType elementalType) {
        this.sequence = sequence;
        this.name = name;
        this.password = password;
        this.defaultHP = defaultHP;
        this.defaultSTR = defaultSTR;
        this.money = money;
        this.upgradeLevel = upgradeLevel;
        this.level = level;
        this.experiencePoint = experiencePoint;
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

    public Password getPassword() {
        return password;
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
                "sequence =" + sequence + " " +
                "name = " + name + " " +
                "password = " + password + " "+
                "defaultHP =" + defaultHP + " " +
                "defaultSTR =" + defaultSTR + " " +
                "money ="  + money.getValue()+ " " +
                "Level =" + level + " " +
                "experiencePoint =" + experiencePoint + " " +
                "upgradeLevel =" + upgradeLevel + " " +
                "elementalType =" + elementalType + " " +
                '}';
    }
}
