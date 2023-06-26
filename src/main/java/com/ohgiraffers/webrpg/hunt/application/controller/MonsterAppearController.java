package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.RandomMonsterDTO;
import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("randomMonster")
    public String monsterAppear(@RequestBody String mapNum, Model model){
        int mapNumber = Integer.parseInt(mapNum);
        System.out.println(mapNumber);
        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);
        model.addAttribute("monsterHp",randomMonsterDTO.getMonsterHp());
        model.addAttribute("monsterPower",randomMonsterDTO.getMonsterPower());
        model.addAttribute("monsterName",randomMonsterDTO.getMonsterName());
        model.addAttribute("monsterElementalType",randomMonsterDTO.getMonsterElementalType());

        return "hunt/hunt1";
    }




}
