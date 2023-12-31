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
import com.ohgiraffers.webrpg.user.domain.aggregate.vo.Money;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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



    private UserApplicationService userApplicationService;

    private InfraRepository infraRepository;

    @Autowired
    public HuntIntegratedController(UserAttackApplicationService userAttackApplicationService
            , MonsterAttackApplicationService monsterAttackApplicationService, InfraRepository infraRepository, UserApplicationService userApplicationService) {
        this.userAttackApplicationService = userAttackApplicationService;
        this.monsterAttackApplicationService = monsterAttackApplicationService;
        this.infraRepository = infraRepository;
        this.userApplicationService = userApplicationService;

    }

    @GetMapping("initHunting")
    public String initHuntProcess(@RequestParam String mapId, @RequestParam String monsterSequence ,Model model, HttpSession session){
        model.addAttribute("monsterSequence", monsterSequence);
        int monsterSeq =  Integer.parseInt(monsterSequence);
        String userName = (String) model.getAttribute("userName");

        UserAttackDTO userAttackDTO = initUserAttackToMonster(monsterSeq, userName);


        UserPatternDTO userPatternDTO = initUserPatternDTO();
        IntegrateUserAttackDTO integrateUserAttackDTO = userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);

        MonsterAttackDTO monsterAttackDTO = initMonsterAttackToUser(monsterSeq, userName);
        MonsterPatternDTO monsterPatternDTO = initMonsterPatternDTO();
        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = monsterAttackApplicationService.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
        model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);

        model.addAttribute("userAttackCnt", 0);
        model.addAttribute("monsterAttackCnt", 0);
        return String.format("redirect:/hunt/huntMap/%d", Integer.valueOf(mapId));
    }

    @PostMapping("attackButton")
    public String huntProcess(@RequestParam String mapId,
                              HttpServletRequest request,
                              HttpSession session,
                              RedirectAttributes rttr){

        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = (IntegrateMonsterAttackDTO) session.getAttribute("integrateMonsterAttackDTO");
        IntegrateUserAttackDTO integrateUserAttackDTO = (IntegrateUserAttackDTO) session.getAttribute("integrateUserAttackDTO");
        int userAttackCnt = (Integer) session.getAttribute("userAttackCnt");
        int monsterAttackCnt = (Integer) session.getAttribute("monsterAttackCnt");

        int monsterSequence =integrateMonsterAttackDTO.getMonsterAttackDTO().getMonster().getMonsterSequence();
        int userSequence =  integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getSequence();


        int userCnt = userAttackCnt;
        int monsterCnt = monsterAttackCnt;

        ElementalType userGetElemental = integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getElementalType();
        MonsterET monsterET = integrateMonsterAttackDTO.getMonsterAttackDTO().getMonster().getElementalType();
        GetElementalDTO getElementalDTO = new GetElementalDTO();
        getElementalDTO.setMonET(monsterET); getElementalDTO.setUserET(userGetElemental);
        StringBuilder logSb = new StringBuilder();
        logSb.append(">>> - 전 투 로 그 - <<<\n");

        while(true) {
            //유저 공격
            if (0 < userCnt && userCnt % 3 == 0) {
                integrateUserAttackDTO = userAttackApplicationService.attackPatternUser(integrateUserAttackDTO, userSequence, getElementalDTO);
            } else {
                UserAttackDTO userAttackDTO = integrateUserAttackDTO.getUserAttackDTO();
                userAttackDTO = userAttackApplicationService.attackToMonster(userAttackDTO, userSequence, getElementalDTO);
                integrateUserAttackDTO.setUserAttackDTO(userAttackDTO);
            }
            logSb.append(integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getName()).append("(이)가 ").append(integrateUserAttackDTO.getUserAttackDTO().getMonster().getMonsterName()).append("(을)를 공격하였습니다.\n");
            userCnt++;

            //몬스터가 죽었을 경우, 보상 적용
            if (integrateUserAttackDTO.getUserAttackDTO().getMonsterCurrentHP().getValue() <= 0) {
                logSb.append(integrateUserAttackDTO.getUserAttackDTO().getMonster().getMonsterName()).append("(이)가 죽었습니다!\n");
                integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().setMoney(new Money(integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getMoney().getValue() + integrateUserAttackDTO.getUserAttackDTO().getMonster().getRewardMoney().getValue()));
                integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().setUserLevel(integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getUserLevel() + integrateUserAttackDTO.getUserAttackDTO().getMonster().getRewardExp().getValue());
                userApplicationService.saveRewardMoney(userSequence, integrateUserAttackDTO.getUserAttackDTO().getMonster().getRewardMoney().getValue());
                userApplicationService.saveEXPReward(userSequence, integrateUserAttackDTO.getUserAttackDTO().getMonster().getRewardExp().getValue());
                session.setAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);
                session.setAttribute("integrateUserAttackDTO", integrateUserAttackDTO);
                session.setAttribute("huntLog", String.valueOf(logSb));
                return "redirect:result";
            }
            if (0 < monsterCnt && monsterCnt % 3 == 0) {
                integrateMonsterAttackDTO = monsterAttackApplicationService.attackPattern(integrateMonsterAttackDTO, monsterSequence, getElementalDTO);
            } else {
                MonsterAttackDTO monsterAttack = integrateMonsterAttackDTO.getMonsterAttackDTO();
                monsterAttack = monsterAttackApplicationService.attackToUser(monsterAttack, userSequence, getElementalDTO);
                integrateMonsterAttackDTO.setMonsterAttackDTO(monsterAttack);
            }
            monsterCnt++;
            logSb.append(integrateUserAttackDTO.getUserAttackDTO().getMonster().getMonsterName()).append("(이)가 ").append(integrateUserAttackDTO.getUserAttackDTO().getUserInfoDTO().getName()).append("을(를) 공격하였습니다!\n");

            if (integrateMonsterAttackDTO.getMonsterAttackDTO().getUserCurrentHP() <= 0) {
                logSb.append(integrateMonsterAttackDTO.getMonsterAttackDTO().getUser().getName()).append("(이)가 죽었습니다!\n");
                session.setAttribute("huntLog", String.valueOf(logSb));
                return "redirect:fail";
            }

            session.setAttribute("integrateUserAttackDTO", integrateUserAttackDTO);
            session.setAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);
        }
    }

    @GetMapping("result")
    public String result() {
        return "hunt/huntResult";
    }

    @GetMapping("fail")
    public String fail() {
        return "hunt/huntfail";
    }


    public UserAttackDTO initUserAttackToMonster(int monsterSequence, String userName) {
        Monster monster = infraRepository.findMonsterBySequence(monsterSequence);

        UserInfoDTO user = userApplicationService.getInfo(userApplicationService.getUserByName(userName));
        return userAttackApplicationService.initUserAttackDTO(monster, user);
    }

    public UserPatternDTO initUserPatternDTO() {

        return userAttackApplicationService.initUserPatternDTO();
    }

    public IntegrateUserAttackDTO initIntegrateUserAttackDTO(UserAttackDTO userAttackDTO, UserPatternDTO userPatternDTO) {
        return userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
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

}
