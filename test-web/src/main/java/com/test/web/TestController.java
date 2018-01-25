package com.test.web;

import com.account.common.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 2018/1/19.
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/index")
    public Object index(@AuthenticationPrincipal Account account){
        log.info("data******************");
        return account;
    }
}

