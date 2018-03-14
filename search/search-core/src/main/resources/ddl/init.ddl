CREATE DATABASE  `test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
create table test.article
(
	id bigint(15) auto_increment comment '主键'
		primary key,
	title VARCHAR (100) null comment '标题',
	abstracts VARCHAR (100) null comment '摘要',
	content longtext null comment '内容',
	post_time TIMESTAMP null comment '发表时间',
	click_count bigint (15) null comment '点击率',
	author VARCHAR (50) null comment '作者',
  create_time TIMESTAMP null comment '创建时间',
  update_time TIMESTAMP null comment '修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='文章表';
