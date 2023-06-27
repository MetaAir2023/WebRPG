package com.ohgiraffers.webrpg.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
@SessionAttributes(value = {"userSequence", "userName"})
public class MainController {

    @GetMapping("menu")
    public String menu() {
        return "main/menu";
    }

    @GetMapping("login")
    public String login(HttpServletRequest request, Model model) {

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if(flashMap!=null) {

            String exception = (String) flashMap.get("exception");
            model.addAttribute("exception", exception);
        }
        return "main/loginPage";
    }
}
