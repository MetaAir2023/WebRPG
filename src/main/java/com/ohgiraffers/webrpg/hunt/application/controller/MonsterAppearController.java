package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.service.MonsterAppearApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("monsterappear")
public class MonsterAppearController {
    private final MonsterAppearApplicationService monsterAppearApplicationService;

    @Autowired
    public MonsterAppearController(MonsterAppearApplicationService monsterAppearApplicationService){
        this.monsterAppearApplicationService = monsterAppearApplicationService;
    }





}
