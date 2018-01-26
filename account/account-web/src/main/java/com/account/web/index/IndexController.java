package com.account.web.index;

import com.account.common.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {


    @RequestMapping("/buyer/index")
    public Object buyer(@AuthenticationPrincipal Account account) {
        log.info("buyer");
        return account;
    }

    @RequestMapping("/seller/index")
    public Object seller(@AuthenticationPrincipal Account account) {
        log.info("seller");
        return account;
    }

}
