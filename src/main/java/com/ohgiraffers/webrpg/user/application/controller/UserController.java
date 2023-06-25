package com.ohgiraffers.webrpg.user.application.controller;

import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.loginDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
