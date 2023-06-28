package com.ohgiraffers.webrpg.upgrade.application.controller;

import com.ohgiraffers.webrpg.upgrade.application.dto.UpgradeCostDTO;
import com.ohgiraffers.webrpg.upgrade.application.dto.UpgradeResultDTO;
import com.ohgiraffers.webrpg.upgrade.application.service.UpgradeApplicationService;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserInfoResult;
import com.ohgiraffers.webrpg.upgrade.domain.service.getResult.GetUserUpgradeStatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/upgrade")
@SessionAttributes("userSequence")
public class UpgradeController {

    private final UpgradeApplicationService upgradeApplicationService;

    @Autowired
    public UpgradeController (UpgradeApplicationService upgradeApplicationService) {
        this.upgradeApplicationService = upgradeApplicationService;
    }

    @GetMapping("upgrade")
    public String upgradeFlags(HttpSession session, Model model) {
        int userSequence = (Integer) session.getAttribute("userSequence");
        Map<String, GetUserUpgradeStatResult> userStatsByFlags = upgradeApplicationService.getUserStatsByFlags(userSequence);
        UpgradeCostDTO upgradeCostAndBalance = upgradeApplicationService.getUpgradeCostAndBalance(userSequence);
        model.addAttribute("success", userStatsByFlags.get("success"));
        model.addAttribute("fail", userStatsByFlags.get("fail"));
        model.addAttribute("current", userStatsByFlags.get("current"));
        model.addAttribute("upgradeCostAndBalance", upgradeCostAndBalance);

        return "upgrade/upgrade";
    }

    @PostMapping("/upgradeResult")
    public String upgradeResult(HttpSession session , Model model) {
        int userSequence = (Integer) session.getAttribute("userSequence");
        GetUserInfoResult userInfo = upgradeApplicationService.getUserInfo(userSequence);
        boolean checkUpgradeMoney = upgradeApplicationService.checkUpgradeMoney(userInfo.getMoney().getValue(), userInfo.getUserLevel());
        if(checkUpgradeMoney) {
           UpgradeResultDTO result = upgradeApplicationService.start(userInfo.getUpgradeLevel(),userInfo.getMoney().getValue(),userInfo.getUserLevel());
            upgradeApplicationService.saveResult(userSequence, result);
            GetUserInfoResult updateUserInfo = upgradeApplicationService.getUserInfo(userSequence);

            String status = null;
            switch (result.getStatus()){
                case 0 : status = "강화를 실패했습니다..."; break;
                case 2 : status = "강화 레벨이 최고 레벨 입니다!"; break;
                case 1 : status = "강화를 성공했습니다!!"; break;
            }
            model.addAttribute("status", status);
            model.addAttribute("updateUserInfo", updateUserInfo);
            return "upgrade/upgradeResult";
        }
        else {
            return "redirect:/upgrade/upgrade";
        }
    }
}
