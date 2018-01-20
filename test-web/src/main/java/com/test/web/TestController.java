package com.test.web;

import com.account.common.Account;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by apple on 2018/1/19.
 */
@RestController
public class TestController {

    @GetMapping("/data")
    public Object index(@AuthenticationPrincipal Account account){
        return account;
    }
}

