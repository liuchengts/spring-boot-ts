create table account
(
	id bigint(15) auto_increment comment '主键'
		primary key,
	account_name VARCHAR (100) null comment '用户名',
	account_pwd VARCHAR (100) null comment '密码',
	authorities_text VARCHAR (100) null comment '认证角色',
	state INTEGER (2) null comment '账户状态'
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='账户表';
