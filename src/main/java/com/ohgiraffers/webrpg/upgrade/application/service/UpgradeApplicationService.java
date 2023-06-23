package com.ohgiraffers.webrpg.upgrade.application.service;

import com.ohgiraffers.webrpg.upgrade.domain.service.RequestService;
import com.ohgiraffers.webrpg.upgrade.domain.service.UpgradeDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpgradeApplicationService {

    private UpgradeDomainService upgradeDomainService;
    private RequestService requestService;

    @Autowired
    public UpgradeApplicationService (UpgradeDomainService upgradeDomainService,RequestService requestService ){
        this.upgradeDomainService =upgradeDomainService;
        this.requestService = requestService;
    }
}
