package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/map")
@SessionAttributes(value = {"userSequence", "userName"})
public class ChoiceMapController {

    private final UserApplicationService userApplicationService;
    private final MonsterAppearApplicationService monsterAppearApplicationService;
    private final HuntIntegratedController huntIntegratedController;

    @Autowired
    public ChoiceMapController(UserApplicationService userApplicationService,
                               MonsterAppearApplicationService monsterAppearApplicationService,
                               HuntIntegratedController huntIntegratedController) {
        this.userApplicationService = userApplicationService;
        this.monsterAppearApplicationService = monsterAppearApplicationService;
        this.huntIntegratedController = huntIntegratedController;
    }

    @GetMapping ("")
    public String allMap(HttpSession session, Model model) {
        String userName = session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO = userApplicationService.getInfo(userApplicationService.getUserByName(userName));
        model.addAttribute("userTotalHp",userInfoDTO.getTotalHP());
        model.addAttribute("userLevel",userInfoDTO.getUserLevel());
        model.addAttribute("userTotalSTR",userInfoDTO.getTotalSTR());
        model.addAttribute("userType",userInfoDTO.getElementalType());

        return "hunt/huntSelectMap";
    }

    @GetMapping ("{mapId}")
    public String monsterAppear(HttpSession session , @PathVariable String mapId, Model model) {
        System.out.println(mapId);
        String userName = session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO = userApplicationService.getInfo(userApplicationService.getUserByName(userName));

        int mapNumber = Integer.parseInt(mapId);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        UserAttackDTO userAttackDTO = huntIntegratedController.initUserAttackToMonster(randomMonsterDTO.getSequence(), userName);

        UserPatternDTO userPatternDTO = huntIntegratedController.initUserPatternDTO();
        IntegrateUserAttackDTO integrateUserAttackDTO = huntIntegratedController.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        model.addAttribute("integrateUserAttackDTO", integrateUserAttackDTO);

        session.setAttribute("integrateUserAttackDTO", integrateUserAttackDTO);

        MonsterAttackDTO monsterAttackDTO = huntIntegratedController.initMonsterAttackToUser(randomMonsterDTO.getSequence(), userName);
        MonsterPatternDTO monsterPatternDTO = huntIntegratedController.initMonsterPatternDTO();

        IntegrateMonsterAttackDTO integrateMonsterAttackDTO = huntIntegratedController.initIntegrateMonsterAttackDTO(monsterAttackDTO, monsterPatternDTO);
        model.addAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);
        session.setAttribute("integrateMonsterAttackDTO", integrateMonsterAttackDTO);


        model.addAttribute("userAttackCnt", 0);
        session.setAttribute("userAttackCnt", 0);

        model.addAttribute("monsterAttackCnt", 0);
        session.setAttribute("monsterAttackCnt", 0);

        return String.format("hunt/huntmaps/hunt%d", Integer.valueOf(mapId));
    }
}
