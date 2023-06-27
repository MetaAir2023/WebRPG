package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.MonsterDTO;
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
@RequestMapping("hunt")
public class MonsterAppearController {
    private final MonsterAppearApplicationService monsterAppearApplicationService;
    private final UserApplicationService userApplicationService;
    @Autowired
    public MonsterAppearController(MonsterAppearApplicationService monsterAppearApplicationService,UserApplicationService userApplicationService){
        this.monsterAppearApplicationService = monsterAppearApplicationService;
        this.userApplicationService=userApplicationService;
    }

    @GetMapping ("huntMap/{mapId}")
    public String monsterAppear(HttpSession session ,@PathVariable String mapId, Model model){
        String userName= session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO=userApplicationService.getInfo(userApplicationService.getUserByName(userName));

        int mapNumber = Integer.parseInt(mapId);

        RandomMonsterDTO randomMonsterDTO = monsterAppearApplicationService.randomMonster(mapNumber);

        model.addAttribute("mapId",mapId);
        model.addAttribute("userInfoDTO",userInfoDTO);
        model.addAttribute("monsterDTO",randomMonsterDTO);


        return String.format("hunt/huntmaps/hunt%d", Integer.valueOf(mapId));
    }

}





