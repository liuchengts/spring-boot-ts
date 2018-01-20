package com.account.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by apple on 2018/1/18.
 * 账户实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", schema = "test")
@DynamicUpdate
@DynamicInsert
public class Account implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户名
     */
    @Column(name = "account_name")
    private String accountName;
    /**
     * 密码
     */
    @Column(name = "account_pwd")
    private String accountPwd;
    /**
     * 认证角色
     */
    @Column(name = "authorities_text")
    private String authoritiesText;
    /**
     * 账户状态
     */
    @Column(name = "state")
    private Integer state;
}
