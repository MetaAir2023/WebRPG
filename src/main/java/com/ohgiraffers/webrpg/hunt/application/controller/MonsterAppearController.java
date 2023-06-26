package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.RandomMonsterDTO;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
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

    @Autowired
    public MonsterAppearController(MonsterAppearApplicationService monsterAppearApplicationService){
        this.monsterAppearApplicationService = monsterAppearApplicationService;
    }

    @GetMapping ("randomMonster1")
    public String monsterAppear1(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt1";
    }@GetMapping ("randomMonster2")
    public String monsterAppear2(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt2";
    }@GetMapping ("randomMonster3")
    public String monsterAppear3(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt3";
    }@GetMapping ("randomMonster4")
    public String monsterAppear4(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt4";
    }@GetMapping ("randomMonster5")
    public String monsterAppear5(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt5";
    }@GetMapping ("randomMonster6")
    public String monsterAppear6(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt6";
    }@GetMapping ("randomMonster7")
    public String monsterAppear7(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt7";
    }@GetMapping ("randomMonster8")
    public String monsterAppear8(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt8";
    }@GetMapping ("randomMonster9")
    public String monsterAppear9(@RequestParam String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt9";
    }@GetMapping ("randomMonster10")
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
