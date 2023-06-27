package com.ohgiraffers.webrpg.user.application.controller;

import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.application.dto.loginDTO;
import com.ohgiraffers.webrpg.user.application.service.UserApplicationService;
import com.ohgiraffers.webrpg.user.domain.aggregate.entity.User;
import com.ohgiraffers.webrpg.user.infra.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String checkUser(HttpSession session, @ModelAttribute("login") loginDTO login, RedirectAttributes rttr) {
        try {
            User user = userApplicationService.getUserByName(login.getUserName());
            UserInfoDTO userInfo = userApplicationService.getInfo(user);
            session.setAttribute("userSequence", userInfo.getSequence());
            session.setAttribute("userName", userInfo.getName());
            return "redirect:/menu";
        } catch (Exception e) {
            if(e.getClass() == UserExistException.class) {
                rttr.addFlashAttribute("exception", "사용자가 존재하지 않습니다 !!");
            }
            return "redirect:/login";
        }
    }
    @GetMapping("info")
    public String info(HttpSession session, Model model) throws UserExistException {
        
      String userName= session.getAttribute("userName").toString();
      
      UserInfoDTO userInfoDTO = userApplicationService.getInfo(userApplicationService.getUserByName(userName));
      model.addAttribute("userTotalHp",userInfoDTO.getTotalHP());
      model.addAttribute("userLevel",userInfoDTO.getUserLevel());
      model.addAttribute("userTotalSTR",userInfoDTO.getTotalSTR());
      model.addAttribute("userType",userInfoDTO.getElementalType());
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
        int userSequence = (int) session.getAttribute("userSequence");
        userApplicationService.saveElementalType(userSequence,type);

        User updateUserInfo = userApplicationService.getUserBySequence(userSequence);

        model.addAttribute("userType",updateUserInfo.getElementalType());
        return "hunt/huntSelectMap";
    }
}

