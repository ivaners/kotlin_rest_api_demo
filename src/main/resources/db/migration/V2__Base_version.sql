CREATE TABLE `admin_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `remark` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
  `admin_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '账号',
  `mobile` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '绑定手机号',
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `admin_role_id` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '角色 id',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否逻辑删除: 0正常记录，1已逻辑删除记录',
  `created_at` datetime NOT NULL COMMENT '录入时间',
  `updated_at` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理后台登录账号表';

CREATE TABLE `admin_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '角色备注',
  `created_at` datetime NOT NULL COMMENT '记录创建时间',
  `updated_at` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='后台角色表';

INSERT INTO `admin_role`(`id`, `name`, `remark`,  `created_at`, `updated_at`) VALUES (1, '默认管理员', '', now(), now());

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastpasswordresetdate` datetime NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_authority` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
  KEY `FKpqlsjpkybgos9w2svcri7j8xy` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `authority_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, now());
INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, now());
INSERT INTO user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, now());

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 1);


CREATE TABLE `test_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

INSERT INTO `test_student`(`id`, `name`) VALUES (1, 'tt');
INSERT INTO `test_student`(`id`, `name`) VALUES (2, 'test');
