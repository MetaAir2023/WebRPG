package com.ohgiraffers.webrpg.user.application.service;

import com.ohgiraffers.webrpg.upgrade.domain.aggregate.enumtype.FlagEnum;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserLevelUpDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserStatDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
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
        return userRepository.findUserSequenceByName(name);
    }

    public User getUserBySequence(int sequence) {
        return userRepository.findUserBySequence(sequence);
    }


    // TODO 강사님께 질문 : Map<String,Integer> VS DTO
    private UserStatDTO getStat(int userDefaultSTR, int userDefaultHp , int userLevel, int userUpgradeLevel) {
        int userTotalSTR = userDomainService.calcTotalStrikingPower(userDefaultSTR, userLevel, userUpgradeLevel);
        int userTotalHP = userDomainService.calcTotalHP(userDefaultHp, userLevel, userUpgradeLevel);
        return new UserStatDTO(userTotalHP, userTotalSTR,userLevel, userUpgradeLevel);
    }

    public UserInfoDTO getInfo(User user) {
        UserStatDTO userStat = getStat(user.getDefaultSTR(), user.getDefaultHP() ,user.getLevel(), user.getUpgradeLevel());
        return new UserInfoDTO(
                user.getSequence(),
                user.getName(),
                userStat.getTotalHP(),
                userStat.getTotalSTR(),
                user.getMoney(),
                user.getLevel(),
                user.getUpgradeLevel(),
                user.getElementalType()
        );
    }

    public UserUpgradeStatDTO getUpgradeStatByFlag(User user, FlagEnum flag) {
        int afterUpgradeLevel;
        switch (flag) {
            case SUCCESS : afterUpgradeLevel = user.getUpgradeLevel() + 1; break;
            case FAIL : afterUpgradeLevel = user.getUpgradeLevel() -1; break;
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

    public void saveElementalType(int sequence, String elemental) {
        ElementalType elementalType = ElementalType.valueOf(elemental);
        userRepository.saveElementalType(sequence, elementalType);
    }

    public void saveEXPReward(int sequence, int exp) {
        User user = userRepository.findUserBySequence(sequence);
        int possessionEXP = userDomainService.calcPossessionEXP(user.getExperiencePoint(),exp);
        UserLevelUpDTO userLevelUpDTO = userDomainService.calLevelUp(user.getLevel(), possessionEXP);
        userRepository.saveLevelUp(user.getSequence(), userLevelUpDTO);
    }

    public void saveUpgradeLevel(int sequence, int upgradeLevel) {
        userRepository.saveUpgradeLevel(sequence, upgradeLevel);
    }

    public void saveRewardMoney(int sequence, int money) {
        User user = userRepository.findUserBySequence(sequence);
        Money balance = userDomainService.calcIncreaseMoney(user.getMoney(), money);
        userRepository.saveMoney(user.getSequence(), balance);
    }

    public void saveSpendMoney(int sequence, int money) {
        User user = userRepository.findUserBySequence(sequence);
        int decreaseMoney = user.getMoney().getValue() - money;
        Money balance = userDomainService.calcDecreaseMoney(user.getMoney(), decreaseMoney);
        userRepository.saveMoney(user.getSequence(), balance);
    }
}
