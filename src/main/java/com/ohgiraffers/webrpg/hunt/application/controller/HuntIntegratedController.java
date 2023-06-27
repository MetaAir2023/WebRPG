package com.ohgiraffers.webrpg.hunt.application.controller;


import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.application.service.UserAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller("HuntIntegratedController")
@RequestMapping("/attack")
public class HuntIntegratedController {
    private UserAttackApplicationService userAttackApplicationService;
    private MonsterAttackApplicationService monsterAttackApplicationService;
    private InMemoryUserRepository inMemoryUserRepository;

    private InfraRepository infraRepository;

    @Autowired
    private HuntIntegratedController(UserAttackApplicationService userAttackApplicationService
            , InMemoryUserRepository inMemoryUserRepository, MonsterAttackApplicationService monsterAttackApplicationService, InfraRepository infraRepository) {
        this.userAttackApplicationService = userAttackApplicationService;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.monsterAttackApplicationService = monsterAttackApplicationService;
        this.infraRepository = infraRepository;
    }

    public UserAttackDTO initUserAttackToMonster(Model model) {
        Monster monster = infraRepository.findMonsterBySequence((Integer) model.getAttribute("monsterSequence"));
        UserInfoDTO user = userAttackApplicationService.getUserInfo((User) model.getAttribute("user"));
        return userAttackApplicationService.initUserAttackDTO(monster, user);
    }

    public UserPatternDTO initUserPatternDTO() {

        return userAttackApplicationService.initUserPatternDTO();
    }

    public IntegrateUserAttackDTO initIntegrateUserAttackDTO(UserAttackDTO userAttackDTO, UserPatternDTO userPatternDTO) {
        return userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
    }

    public IntegrateUserAttackDTO userAttackToMonster(IntegrateUserAttackDTO integrateUserAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO, GetElementalDTO getElementalDTO) {
        userAttackApplicationService.attackPattern(integrateUserAttackDTO, sequence, userGetElementalDTO, getElementalDTO);

        return integrateUserAttackDTO;
    }

    //Monster ATK To User
    public MonsterAttackDTO initMonsterAttackToUser(Model model, HttpSession session){
        Monster monsterEntity = infraRepository.findMonsterBySequence((Integer) model.getAttribute("monsterSequence"));
        MonsterDTO monster = new MonsterDTO(monsterEntity.getMonsterName(), monsterEntity.getMonsterHp().getValue(), monsterEntity.getMonsterPower().getValue(), monsterEntity.getRewardExp().getValue(), monsterEntity.getRewardMoney().getValue(), monsterEntity.getMonElement());
        UserInfoDTO user = userAttackApplicationService.getUserInfo((User) model.getAttribute("user"));
        return monsterAttackApplicationService.initMonsterAttackDTO(monster, user);
    }
    public MonsterPatternDTO initMonsterPatternDTO(){
        return monsterAttackApplicationService.initMonsterPatternDTO();
    }

    public IntegrateMonsterAttackDTO initIntegrateMonsterAttackDTO(MonsterAttackDTO monsterAttackDTO, MonsterPatternDTO monsterPatternDTO){
        return monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
    }
    public IntegrateMonsterAttackDTO monsterAttackToUser(IntegrateMonsterAttackDTO integrateMonsterAttackDTO, int sequence, GetElementalDTO getElementalDTO){
        monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO, sequence, getElementalDTO);
        return integrateMonsterAttackDTO;
    }
}
