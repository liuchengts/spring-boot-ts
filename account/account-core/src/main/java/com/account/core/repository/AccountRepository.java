package com.account.core.repository;


import com.account.common.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by apple on 2018/1/18.
 * db操作
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Serializable>
        , JpaSpecificationExecutor<Account> {
}
