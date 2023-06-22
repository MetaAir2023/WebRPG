package com.ohgiraffers.webrpg.user.application.service;

import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserUpgradeStatDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.repository.UserRepository;
import com.ohgiraffers.webrpg.user.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String,Integer> getStat(int userDefaultSTR, int userDefaultHp , int userLevel, int userUpgradeLevel) {
        Map<String,Integer> result = new HashMap<>();
        int userTotalSTR = userDomainService.calcTotalStrikingPower(userDefaultSTR, userLevel, userLevel);
        int userTotalHP = userDomainService.calcTotalHP(userDefaultHp, userLevel, userUpgradeLevel);
        result.put("totalSTR", userTotalSTR);
        result.put("totalHP", userTotalHP);
        return result;
    }

    public UserInfoDTO getInfo(User user) {
        Map<String,Integer> userStat = getStat(user.getDefaultSTR(), user.getDefaultHP() ,user.getLevel(), user.getUpgradeLevel());
        return new UserInfoDTO(
                user.getName(),
                userStat.get("totalSTR"),
                userStat.get("totalHP"),
                user.getLevel(),
                user.getUpgradeLevel(),
                user.getElementalType()
        );
    }

    public UserUpgradeStatDTO getUpgradeStatByFlag(User user, String flag) {
        int branch;
        switch (flag) {
            case "success" : branch = 1; break;
            case "fail" : branch = -1; break;
            default:
                branch = 0; break;
        }
        Map<String,Integer> userStat = getStat(user.getDefaultSTR(), user.getDefaultHP() ,user.getLevel(), user.getUpgradeLevel() + branch);
        return new UserUpgradeStatDTO(
                user.getUpgradeLevel(),
                userStat.get("totalSTR"),
                userStat.get("totalHP")
        );
    }
}
