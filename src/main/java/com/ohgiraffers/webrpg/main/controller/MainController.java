package com.ohgiraffers.webrpg.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes(value = {"userSequence", "userName"})
public class MainController {

    @GetMapping("menu")
    public String menu(){return "main/menu";}

    @GetMapping("login")
    public String login(){
        return "main/loginPage";
    }
}
