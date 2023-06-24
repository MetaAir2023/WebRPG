package com.ohgiraffers.webrpg.upgrade.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UpgradeDomainService {
    @Value("${rate:1}")
    private double sensitivityRate;
    @Value("${MaxUpgradeLevel:10}")
    private int MaxUpgradeLevel;
    @Value("${upgradeConstantNumber:10}")
    private int upgradeConstantNumber;

    public int calcUpgradeCost(int userLevel) {
        return userLevel * upgradeConstantNumber;
    }

    public boolean checkUpgradeMoney(int money, int UpgradeCost){
        return money > UpgradeCost;
    }

    public int calcBalance(int money, int UpgradeCost){
        return money - UpgradeCost;
    }

    private boolean checkMaxUpgradeLevel(int upgradeLevel) {
        return upgradeLevel < MaxUpgradeLevel;
    }

    public double getRandomValue(int upgradeLevel) {
        Random random = new Random();
        double updateStochasticSeed = upgradeLevel * sensitivityRate;
        double gaussianRandomNumber = random.nextGaussian();
        return gaussianRandomNumber * updateStochasticSeed;
    }


    private boolean checkExistProbabilityDensity(int upgradeLevel) {
        double updateStochastic = getRandomValue(upgradeLevel);
        return updateStochastic <= 1 && updateStochastic >= -1;
    }

    public int calculateUpgradeProbability(int upgradeLevel) {
        boolean checkMaxUpgradeLevel = checkMaxUpgradeLevel(upgradeLevel);
        if (!checkMaxUpgradeLevel) {
            return upgradeLevel;
        }
        boolean successUpgrade = checkExistProbabilityDensity(upgradeLevel);
        return successUpgrade ? upgradeLevel + 1 : 0;
    }
}
