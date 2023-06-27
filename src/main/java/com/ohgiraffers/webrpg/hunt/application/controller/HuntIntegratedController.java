package com.ohgiraffers.webrpg.hunt.application.controller;


import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.application.service.UserAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.domain.aggregate.enumtype.ElementalType;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller("HuntIntegratedController")
@RequestMapping("/attack")
public class HuntIntegratedController {
    private UserAttackApplicationService userAttackApplicationService;
    private MonsterAttackApplicationService monsterAttackApplicationService;
    private InMemoryUserRepository inMemoryUserRepository;

    private UserApplicationService userApplicationService;

    private InfraRepository infraRepository;

    @Autowired
    private HuntIntegratedController(UserAttackApplicationService userAttackApplicationService
            , InMemoryUserRepository inMemoryUserRepository, MonsterAttackApplicationService monsterAttackApplicationService, InfraRepository infraRepository, UserApplicationService userApplicationService) {
        this.userAttackApplicationService = userAttackApplicationService;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.monsterAttackApplicationService = monsterAttackApplicationService;
        this.infraRepository = infraRepository;
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("initHunting")
    public String initHuntProcess(@RequestParam String mapId, Model model, HttpSession session){
        UserAttackDTO userAttackDTO = initUserAttackToMonster(model, session);
        UserPatternDTO userPatternDTO = initUserPatternDTO();
        IntegrateUserAttackDTO integrateUserAttackDTO = userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);

        MonsterAttackDTO monsterAttackDTO = initMonsterAttackToUser(model, session);
        MonsterPatternDTO monsterPatternDTO = initMonsterPatternDTO();
        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
        model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);

        model.addAttribute("userAttackCnt", 0);
        model.addAttribute("monsterAttackCnt", 0);
        return String.format("hunt/huntmaps/hunt%d", Integer.valueOf(mapId));
    }

    @PostMapping("attackButton")
    @ResponseBody
    public String huntProcess(@RequestParam String mapId, Model model, HttpSession session){
//    public Map huntProcess(@RequestParam String mapId, Model model, HttpSession session){
        //몬스터 시퀸스
        int sequence = (int) model.getAttribute("sequence");

        int userCnt = (int) model.getAttribute("userAttackCnt");
        int monsterCnt = (int) model.getAttribute("monsterAttackCnt");

        ElementalType userGetElemental = ((UserInfoDTO) model.getAttribute("userInfoDTO")).getElementalType();
        MonsterET monsterET = ((MonsterDTO)model.getAttribute("monsterDTO")).getElementalType();
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        getElementalDTO.setMonET(monsterET); getElementalDTO.setUserET(userGetElemental);

        //유저 공격
        IntegrateUserAttackDTO integrateUserAttackDTO = (IntegrateUserAttackDTO) model.getAttribute("integrateUserAttackDTO");
        if(0 < userCnt && userCnt % 3 == 0){
            integrateUserAttackDTO = userAttackApplicationService.attackPatternUser(integrateUserAttackDTO, sequence, getElementalDTO);
        }else{
            UserAttackDTO userAttackDTO = integrateUserAttackDTO.getUserAttackDTO();
            userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, sequence, getElementalDTO);
            integrateUserAttackDTO.setUserAttackDTO(userAttackDTO);
        }
        model.addAttribute("userAttackCnt", userCnt + 1);

        //몬스터가 죽었을 경우,
        if(integrateUserAttackDTO.getUserAttackDTO().getMonsterCurrentHP().getValue() <= 0){
            return "hunt/huntResult";
        }

        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = (IntegrateMonsterAttackDTO) model.getAttribute("integrateMonsterAttackDTO");
        if(0 < monsterCnt && monsterCnt % 3 == 0){
            integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO, sequence, getElementalDTO);
        }else{
            MonsterAttackDTO monsterAttack = integrateMonsterAttackDTO.getMonsterAttackDTO();
            monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, sequence, getElementalDTO);
            integrateMonsterAttackDTO.setMonsterAttackDTO(monsterAttack);
        }
        model.addAttribute("monsterAttackCnt", monsterCnt + 1);


        if(integrateMonsterAttackDTO.getMonsterAttackDTO().getUserCurrentHP() <= 0){
            return "hunt/huntfail";
        }

        model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);
        model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("integratedUserAttackDTO", integrateUserAttackDTO);
//        map.put("integratedMonsterAttackDTO", integrateMonsterAttackDTO);
        return String.format("hunt/huntmaps/hunt%d", Integer.valueOf(mapId));
//        return map;
    }

    public UserAttackDTO initUserAttackToMonster(Model model, HttpSession session) {
        Monster monster = infraRepository.findMonsterBySequence((Integer) model.getAttribute("monsterSequence"));
        String userName = session.getAttribute("userName").toString();
        UserInfoDTO user = userApplicationService.getInfo(userApplicationService.getUserByName(userName));
        return userAttackApplicationService.initUserAttackDTO(monster, user);
    }

    public UserPatternDTO initUserPatternDTO() {

        return userAttackApplicationService.initUserPatternDTO();
    }

    public IntegrateUserAttackDTO initIntegrateUserAttackDTO(UserAttackDTO userAttackDTO, UserPatternDTO userPatternDTO) {
        return userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
    }

    public IntegrateUserAttackDTO userAttackToMonster(IntegrateUserAttackDTO integrateUserAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO, GetElementalDTO getElementalDTO) {
        userAttackApplicationService.attackPatternUser(integrateUserAttackDTO, sequence, getElementalDTO);

        return integrateUserAttackDTO;
    }

    //Monster ATK To User
    public MonsterAttackDTO initMonsterAttackToUser(Model model, HttpSession session){
        Monster monsterEntity = infraRepository.findMonsterBySequence((Integer) model.getAttribute("monsterSequence"));
        MonsterDTO monster = new MonsterDTO(monsterEntity.getMonsterName(), monsterEntity.getMonsterHp().getValue(), monsterEntity.getMonsterPower().getValue(), monsterEntity.getRewardExp().getValue(), monsterEntity.getRewardMoney().getValue(), monsterEntity.getMonElement());

        String userName = session.getAttribute("userName").toString();
        UserInfoDTO user = userApplicationService.getInfo(userApplicationService.getUserByName(userName));

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
