package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.RandomMonsterDTO;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
@RequestMapping("monsterappear")
public class MonsterAppearController {
    private final MonsterAppearApplicationService monsterAppearApplicationService;
    private final UserApplicationService userApplicationService;
    @Autowired
    public MonsterAppearController(MonsterAppearApplicationService monsterAppearApplicationService,UserApplicationService userApplicationService){
        this.monsterAppearApplicationService = monsterAppearApplicationService;
        this.userApplicationService=userApplicationService;
    }

    @GetMapping ("randomMonster1")
    public String monsterAppear1(HttpSession session ,@RequestParam String mapNum, Model model){
        String userName= session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO=userApplicationService.getInfo(userApplicationService.getUserByName(userName));

        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp().getValue());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower().getValue());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());
        model.addAttribute("monsterSequence",randomMonsterDTO.getSequence());
        model.addAttribute("userName",userInfoDTO.getName());
        model.addAttribute("userTotalHp",userInfoDTO.getTotalHP());
        model.addAttribute("userTotalStr",userInfoDTO.getTotalSTR());
        model.addAttribute("userLevel",userInfoDTO.getUserLevel());
        model.addAttribute("userType",userInfoDTO.getElementalType());
        return "hunt/huntmaps/hunt1";
    }
    @GetMapping ("randomMonster2")
    public String monsterAppear2(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt2";
    }
    @GetMapping ("randomMonster3")
    public String monsterAppear3(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt3";
    }
    @GetMapping ("randomMonster4")
    public String monsterAppear4(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt4";
    }
    @GetMapping ("randomMonster5")
    public String monsterAppear5(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt5";
    }
    @GetMapping ("randomMonster6")
    public String monsterAppear6(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt6";
    }
    @GetMapping ("randomMonster7")
    public String monsterAppear7(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt7";
    }
    @GetMapping ("randomMonster8")
    public String monsterAppear8(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt8";
    }
    @GetMapping ("randomMonster9")
    public String monsterAppear9(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt9";
    }
    @GetMapping ("randomMonster10")
    public String monsterAppear10(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt10";
    }




}
