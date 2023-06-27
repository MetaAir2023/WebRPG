package com.ohgiraffers.webrpg.hunt.application.controller;


import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.application.service.UserAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.enumtype.MonsterET;
import com.ohgiraffers.webrpg.hunt.infra.repository.InfraRepository;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.loginDTO;
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
@SessionAttributes(value = {"userSequence", "userName"})
public class HuntIntegratedController {
    private UserAttackApplicationService userAttackApplicationService;
    private MonsterAttackApplicationService monsterAttackApplicationService;
    private MonsterAppearController monsterAppearController;

    private InMemoryUserRepository inMemoryUserRepository;

    private UserApplicationService userApplicationService;

    private InfraRepository infraRepository;

    @Autowired
    public HuntIntegratedController(UserAttackApplicationService userAttackApplicationService
            , InMemoryUserRepository inMemoryUserRepository, MonsterAttackApplicationService monsterAttackApplicationService, InfraRepository infraRepository, UserApplicationService userApplicationService,MonsterAppearController monsterAppearController) {
        this.userAttackApplicationService = userAttackApplicationService;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.monsterAttackApplicationService = monsterAttackApplicationService;
        this.infraRepository = infraRepository;
        this.userApplicationService = userApplicationService;
        this.monsterAppearController = monsterAppearController;
    }

    @GetMapping("initHunting")
    public String initHuntProcess(@RequestParam String mapId, @RequestParam String monsterSequence ,Model model, HttpSession session){
        System.out.println("monsterSequence = " + monsterSequence);
        model.addAttribute("monsterSequence", monsterSequence);
        int monsterSeq =  Integer.parseInt(monsterSequence);
        String userName = (String) model.getAttribute("userName");
        System.out.println(userName);
        UserAttackDTO userAttackDTO = initUserAttackToMonster(monsterSeq, userName);

        System.out.println("userAttackDTO = " +userAttackDTO);
        UserPatternDTO userPatternDTO = initUserPatternDTO();
        System.out.println("userPatternDTO = " +userPatternDTO);
        IntegrateUserAttackDTO integrateUserAttackDTO = userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        System.out.println("integrateUserAttackDTO = " +integrateUserAttackDTO);
        model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);

        MonsterAttackDTO monsterAttackDTO = initMonsterAttackToUser(monsterSeq, userName);
        System.out.println("monsterAttackDTO = " +monsterAttackDTO);
        MonsterPatternDTO monsterPatternDTO = initMonsterPatternDTO();
        System.out.println("monsterPatternDTO = " +monsterPatternDTO);
        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
        System.out.println("integrateMonsterAttackDTO = " +integrateMonsterAttackDTO);
        model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);

        model.addAttribute("userAttackCnt", 0);
        model.addAttribute("monsterAttackCnt", 0);
        return String.format("redirect:/hunt/huntMap/%d", Integer.valueOf(mapId));
    }

    @PostMapping("attackButton")
    public String huntProcess(@RequestParam String mapId,
                              @ModelAttribute("integrateMonsterAttackDTO") IntegrateMonsterAttackDTO integrateMonsterAttackDTO,
                              @ModelAttribute("integrateUserAttackDTO") IntegrateUserAttackDTO integrateUserAttackDTO,
                              @ModelAttribute("userAttackCnt") Integer userAttackCnt,
                              @ModelAttribute("monsterAttackCnt") Integer monsterAttackCnt,
                              HttpSession session){
//    public Map huntProcess(@RequestParam String mapId, Model model, HttpSession session){
        //몬스터 시퀸스
        System.out.println("HuntIntegratedController attackButton");
        System.out.println("integrateMonsterAttackDTO = " + integrateMonsterAttackDTO);
        int sequence =integrateMonsterAttackDTO.getMonsterAttackDTO().getMonster().getMonsterSequence();
        System.out.println("sequence = " + sequence);

        int userCnt = userAttackCnt;
        int monsterCnt = monsterAttackCnt;

        ElementalType userGetElemental = integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getElementalType();
        MonsterET monsterET = integrateMonsterAttackDTO.getMonsterAttackDTO().getMonster().getElementalType();
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        getElementalDTO.setMonET(monsterET); getElementalDTO.setUserET(userGetElemental);

        //유저 공격
        if(0 < userCnt && userCnt % 3 == 0){
            integrateUserAttackDTO = userAttackApplicationService.attackPatternUser(integrateUserAttackDTO, sequence, getElementalDTO);
        }else{
            UserAttackDTO userAttackDTO = integrateUserAttackDTO.getUserAttackDTO();
            userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, sequence, getElementalDTO);
            integrateUserAttackDTO.setUserAttackDTO(userAttackDTO);
        }
        //model.addAttribute("userAttackCnt", userCnt + 1);

        //몬스터가 죽었을 경우,
        if(integrateUserAttackDTO.getUserAttackDTO().getMonsterCurrentHP().getValue() <= 0){
            return "hunt/huntResult";
        }

        if(0 < monsterCnt && monsterCnt % 3 == 0){
            integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO, sequence, getElementalDTO);
        }else{
            MonsterAttackDTO monsterAttack = integrateMonsterAttackDTO.getMonsterAttackDTO();
            monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, sequence, getElementalDTO);
            integrateMonsterAttackDTO.setMonsterAttackDTO(monsterAttack);
        }
        //model.addAttribute("monsterAttackCnt", monsterCnt + 1);


        if(integrateMonsterAttackDTO.getMonsterAttackDTO().getUserCurrentHP() <= 0){
            return "hunt/huntfail";
        }

        //model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);
       // model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("integratedUserAttackDTO", integrateUserAttackDTO);
//        map.put("integratedMonsterAttackDTO", integrateMonsterAttackDTO);
        return String.format("redirect:/hunt/huntmaps/hunt%d", Integer.valueOf(mapId));
//        return map;
    }

//    public UserAttackDTO initUserAttackToMonster(Model model, HttpSession session) {
    public UserAttackDTO initUserAttackToMonster(int monsterSequence, String userName) {
        Monster monster = infraRepository.findMonsterBySequence(monsterSequence);
//        Monster monster = infraRepository.findMonsterBySequence((Integer) model.getAttribute("monsterSequence"));
//        String userName = session.getAttribute("userName").toString();
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
    public MonsterAttackDTO initMonsterAttackToUser(int monsterSequence, String userName){
        Monster monsterEntity = infraRepository.findMonsterBySequence(monsterSequence);
        MonsterDTO monster = new MonsterDTO(monsterEntity.getSequence(),monsterEntity.getMonsterName(), monsterEntity.getMonsterHp().getValue(), monsterEntity.getMonsterPower().getValue(), monsterEntity.getRewardExp().getValue(), monsterEntity.getRewardMoney().getValue(), monsterEntity.getMonElement());

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
