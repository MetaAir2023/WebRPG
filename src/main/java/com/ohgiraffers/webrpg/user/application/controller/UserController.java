package com.ohgiraffers.webrpg.user.application.controller;

import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.UserStatDTO;
import com.ohgiraffers.webrpg.user.application.dto.loginDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@SessionAttributes("userSequence")
public class UserController {

    private final UserApplicationService userApplicationService;
    @Autowired
    public UserController (UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("login")
    public String login(){
        return "main/loginPage";
    }

    @PostMapping("login")
    public String checkUser(HttpSession session, @ModelAttribute("login") loginDTO login) {
        System.out.println(login);
        UserInfoDTO user = userApplicationService.getInfo(userApplicationService.getUserByName(login.getUserName()));
        session.setAttribute("userSequence", user.getSequence());
        session.setAttribute("userName", user.getName());
        System.out.println("userSequence : " + session.getAttribute("userSequence"));

        return "main/menu";
    }
    @GetMapping("info")
    public String info(HttpSession session, Model mv){
//        System.out.println(session.getAttribute("userName"));
        String userName= session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO=userApplicationService.getInfo(userApplicationService.getUserByName(userName));
//        mv.addAttribute("userInfoDTO",userInfoDTO);
            mv.addAttribute("userTotalHp",userInfoDTO.getTotalHP());
        mv.addAttribute("userLevel",userInfoDTO.getUserLevel());
       mv.addAttribute("userTotalSTR",userInfoDTO.getTotalSTR());
//        System.out.println("userInfoDTO = " + userInfoDTO.getTotalHP());
        return  "hunt/huntSelectMap";
    }


}
