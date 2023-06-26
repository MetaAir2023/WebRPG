package com.ohgiraffers.webrpg.hunt.application.controller;

import com.ohgiraffers.webrpg.hunt.application.dto.*;
import com.ohgiraffers.webrpg.hunt.application.service.UserAttackApplicationService;
import com.ohgiraffers.webrpg.hunt.domain.aggregate.entity.Monster;
import com.ohgiraffers.webrpg.user.application.dto.UserInfoDTO;
import com.ohgiraffers.webrpg.user.infra.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userattck")
public class UserAttackController {
        /* 유저가 몬스터를 공격하는 구문을 최종 작성한다. */
        private UserAttackApplicationService userAttackApplicationService;

        private InMemoryUserRepository inMemoryUserRepository;

        @Autowired
        private UserAttackController(UserAttackApplicationService userAttackApplicationService
                                    , InMemoryUserRepository inMemoryUserRepository) {
            this.userAttackApplicationService = userAttackApplicationService;
            this.inMemoryUserRepository = inMemoryUserRepository;
        }

        public UserAttackDTO initUserAttackToMonster(Monster monster, UserInfoDTO userInfoDTO) {

            return userAttackApplicationService.initUserAttackDTO(monster, userInfoDTO);
        }

        public UserPatternDTO initUserPatternDTO() {

            return userAttackApplicationService.initUserPatternDTO();
        }

        public IntegrateUserAttackDTO initIntegrateUserAttackDTO(UserAttackDTO userAttackDTO, UserPatternDTO userPatternDTO) {
            return userAttackApplicationService.initIntegrateUserAttackDTO(userAttackDTO, userPatternDTO);
        }

        public IntegrateUserAttackDTO userAttackToMonster(IntegrateUserAttackDTO integrateUserAttackDTO, int sequence, UserGetElementalDTO userGetElementalDTO) {
            userAttackApplicationService.attackPattern(integrateUserAttackDTO, sequence, userGetElementalDTO);

            return integrateUserAttackDTO;
        }

}

