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
@SessionAttributes(value = {"userSequence", "userName"})
public class UserController {

    private final UserApplicationService userApplicationService;
    @Autowired
    public UserController (UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("login")
    public String checkUser(HttpSession session, @ModelAttribute("login") loginDTO login) {
        UserInfoDTO user = userApplicationService.getInfo(userApplicationService.getUserByName(login.getUserName()));
        session.setAttribute("userSequence", user.getSequence());
        session.setAttribute("userName", user.getName());
        System.out.println("userSequence : " + session.getAttribute("userSequence"));

        return "redirect:/menu";
    }
    @GetMapping("info")
    public String info(HttpSession session, Model model){

        String userName= session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO=userApplicationService.getInfo(userApplicationService.getUserByName(userName));

        model.addAttribute("userTotalHp",userInfoDTO.getTotalHP());
        model.addAttribute("userLevel",userInfoDTO.getUserLevel());
        model.addAttribute("userTotalSTR",userInfoDTO.getTotalSTR());
;
        return  "hunt/huntSelectMap";
    }
    @GetMapping("type")
    public String type(HttpSession session,Model model){
        String userName= session.getAttribute("userName").toString();
        UserInfoDTO userInfoDTO=userApplicationService.getInfo(userApplicationService.getUserByName(userName));

        model.addAttribute("userType",userInfoDTO.getElementalType());

        return  "user/type";
    }
    @PostMapping("changeType")
    public String changeType(HttpSession session,@ModelAttribute("type") String type, Model model){
        System.out.println(type);
        int userSequence = (int) session.getAttribute("userSequence");
        userApplicationService.saveElementalType(userSequence,type);

        User updateUserInfo = userApplicationService.getUserBySequence(userSequence);

        model.addAttribute("userType",updateUserInfo.getElementalType());
        return "hunt/huntSelectMap";
    }
}

