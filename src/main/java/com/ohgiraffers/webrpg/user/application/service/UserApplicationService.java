package com.ohgiraffers.webrpg.user.application.service;

import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserStatDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import com.ohgiraffers.webrpg.user.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    private final UserRepository<User> userRepository;
    private final UserDomainService userDomainService;

    @Autowired
    public UserApplicationService(UserRepository<User> userRepository,
                                       UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }

    public User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public int getUserSequence(String name) {
        return userRepository.findUserByName(name).getSequence();
    }

    public User getUserBySequence(int sequence) {
        return userRepository.findUserBySequence(sequence);
    }


    // TODO 강사님께 질문 : Map<String,Integer> VS DTO
    private UserStatDTO getStat(int userDefaultSTR, int userDefaultHp , int userLevel, int userUpgradeLevel) {
        int userTotalSTR = userDomainService.calcTotalStrikingPower(userDefaultSTR, userLevel, userUpgradeLevel);
        int userTotalHP = userDomainService.calcTotalHP(userDefaultHp, userLevel, userUpgradeLevel);
        return new UserStatDTO(userTotalHP, userTotalSTR);
    }

    public UserInfoDTO getInfo(User user) {
        UserStatDTO userStat = getStat(user.getDefaultSTR(), user.getDefaultHP() ,user.getLevel(), user.getUpgradeLevel());
        return new UserInfoDTO(
                user.getName(),
                userStat.getTotalHP(),
                userStat.getTotalSTR(),
                user.getLevel(),
                user.getUpgradeLevel(),
                user.getElementalType()
        );
    }

    public UserUpgradeStatDTO getUpgradeStatByFlag(User user, String flag) {
        int afterUpgradeLevel;
        switch (flag) {
            case "success" : afterUpgradeLevel = user.getUpgradeLevel() + 1; break;
            case "fail" : afterUpgradeLevel = user.getUpgradeLevel() -1; break;
            default:
                afterUpgradeLevel = user.getUpgradeLevel(); break;
        }
        UserStatDTO userStat = getStat(user.getDefaultSTR(), user.getDefaultHP() ,user.getLevel(), afterUpgradeLevel);
        return new UserUpgradeStatDTO(
                afterUpgradeLevel,
                userStat.getTotalHP(),
                userStat.getTotalSTR()
        );
    }

    public void saveEXPReward(int sequence, int exp) {
        User user = userRepository.findUserBySequence(sequence);
        int possessionEXP = userDomainService.calcPossessionEXP(user.getExperiencePoint(),exp);
        UserLevelUpDTO userLevelUpDTO = userDomainService.calLevelUp(user.getLevel(), possessionEXP);
        userRepository.saveLevelUp(user.getSequence(), userLevelUpDTO);
    }

    public void saveMoneyReward(int sequence, int money) {
        User user = userRepository.findUserBySequence(sequence);
        Money balance = userDomainService.calcBalanceMoney(user.getMoney(), money);
        userRepository.saveMoney(user.getSequence(), balance);
    }
}
