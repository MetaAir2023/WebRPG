package com.ohgiraffers.webrpg.upgrade.application.service;

import com.ohgiraffers.webrpg.upgrade.application.dto.UpgradeCostDTO;
import com.ohgiraffers.webrpg.upgrade.application.dto.UpgradeResultDTO;
import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.upgrade.domain.service.RequestService;
import com.ohgiraffers.webrpg.upgrade.domain.service.UpgradeDomainService;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpgradeApplicationService {

    private final UpgradeDomainService upgradeDomainService;
    private final RequestService requestService;

    @Autowired
    public UpgradeApplicationService (UpgradeDomainService upgradeDomainService,RequestService requestService ){
        this.upgradeDomainService =upgradeDomainService;
        this.requestService = requestService;
    }

    public UpgradeCostDTO getUpgradeCostAndBalance(int userSequence) {
        GetUserInfoResult user = requestService.getUserInfo(userSequence);
        int upgradeCost = upgradeDomainService.calcUpgradeCost(user.getUserLevel());
        int balance = user.getMoney().getValue();
        return new UpgradeCostDTO(balance, upgradeCost);
    }

    /**
     * 강화 결과 (성공, 실패)에 따른 스텟 변화를 보여주는 메소드
     * return Map<String,GetUserUpgradeStatResult>
     *     GetUserUpgradeStatResult
     *     - upgradeLevel
     *     - totalHP
     *     - totalSTR
     */
    public Map<String,GetUserUpgradeStatResult> getUserStatsByFlags(int userSequence) {
        Map<String,GetUserUpgradeStatResult> resultMap = new HashMap<>();
        GetUserUpgradeStatResult currentResult = requestService.getUserUpgradeStats(userSequence,FlagEnum.CURRENT);
        GetUserUpgradeStatResult successResult = requestService.getUserUpgradeStats(userSequence, FlagEnum.SUCCESS);
        GetUserUpgradeStatResult failResult = requestService.getUserUpgradeStats(userSequence,FlagEnum.FAIL);

        resultMap.put("current", currentResult);
        resultMap.put("success", successResult);
        resultMap.put("fail", failResult);
        return resultMap;
    }

    /**
     * 강화 가능 보유금을 가지고 있는지 체크하는 메소드
     * return boolean
     */
    public boolean checkUpgradeMoney(int money, int userLevel) {
        int upgradeCost = upgradeDomainService.calcUpgradeCost(userLevel);
        return upgradeDomainService.checkUpgradeMoney(money, upgradeCost);
    }

    /**
     * 강화를 수행하는 메소드
     * 해당 메소드를 수행하기 전, 반드시 checkUpgradeMoney 메소드로 강화가 가능한 돈이 있는지 체크해야 합니다.
     * return UpgradeResultDTO
     * - status, upgradeLevelResult 을 반환합니다.
     */
    public UpgradeResultDTO start(int upgradeLevel, int money, int userLevel){
        int upgradeLevelResult = upgradeDomainService.executeUpgrade(upgradeLevel);
        int upgradeCost = upgradeDomainService.calcUpgradeCost(userLevel);
        int balance = upgradeDomainService.calcBalance(money, upgradeCost);
        int status;
        if(upgradeLevelResult == upgradeLevel -1 ) {
            status = 0; // 강화 실패
        } else if (upgradeLevelResult == upgradeLevel) {
            status = 2; // 강화 max
        } else {
            status = 1; // 강화 성공
        }
        return new UpgradeResultDTO(status, upgradeLevelResult, balance);
    }

    /**
     * 강화 결과를 저장하는 메소드
     * return void
     * 사용자의 보유금과 강화 레벨을 저장합니다.
     */

    public void saveResult(int userSequence, UpgradeResultDTO upgradeResultDTO) {
        requestService.saveUserBalance(userSequence,upgradeResultDTO.getBalance());
        requestService.saveUserUpgradeLevel(userSequence, upgradeResultDTO.getResultUpgradeLevel());
    }

    /**
     * 사용자 정보를 전체 보여주는 메소드
     * return GetUserInfoResult
     * - name, totalHP, totalSTR, Money, userLevel, upgradeLevel, elementalType 을 반환합니다.
     */
    public GetUserInfoResult getUserInfo(int userSequence) {
        return requestService.getUserInfo(userSequence);
    }
}
