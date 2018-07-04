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
