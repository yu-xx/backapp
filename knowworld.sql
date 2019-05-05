/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : knowworld

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-05-05 23:04:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kw_article
-- ----------------------------
DROP TABLE IF EXISTS `kw_article`;
CREATE TABLE `kw_article` (
  `article_id` bigint(25) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `summary` text NOT NULL,
  `type` tinyint(10) NOT NULL,
  `source` tinyint(10) DEFAULT NULL,
  `low_source` bigint(20) DEFAULT NULL COMMENT '预览图',
  `content` longtext NOT NULL,
  `cre_id` bigint(25) NOT NULL,
  `cre_name` varchar(255) NOT NULL,
  `oper_id` bigint(10) NOT NULL,
  `oper_name` varchar(255) NOT NULL,
  `cre_time` datetime NOT NULL,
  `oper_time` datetime NOT NULL,
  `status` tinyint(10) NOT NULL,
  `valid_flag` tinyint(10) NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_article
-- ----------------------------

-- ----------------------------
-- Table structure for kw_attachment
-- ----------------------------
DROP TABLE IF EXISTS `kw_attachment`;
CREATE TABLE `kw_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(64) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `original_name` varchar(255) DEFAULT NULL,
  `suffix` varchar(20) DEFAULT NULL,
  `att_type` varchar(20) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_78skwacaqlnd4qcbn05gjyokj` (`file_path`),
  KEY `FKccvplpvv5rqnjtx6tdghcr5vy` (`member_id`),
  CONSTRAINT `FKccvplpvv5rqnjtx6tdghcr5vy` FOREIGN KEY (`member_id`) REFERENCES `kw_member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_attachment
-- ----------------------------
INSERT INTO `kw_attachment` VALUES ('15', 'image/jpeg', '/attachment/avatar/2019/05/eb2e2d75-256b-4e3e-9eeb-ba5cdfc264ce.jpg', '243269', '微信图片_20180610171045.jpg', 'jpg', 'AVATAR', '2019-05-05 12:49:06', '1');
INSERT INTO `kw_attachment` VALUES ('16', 'image/png', '/attachment/avatar/2019/05/41dc1391-f896-4289-be74-b3967f87b15b.png', '869167', '挖酒网面试题.png', 'png', 'AVATAR', '2019-05-05 21:44:04', '1');

-- ----------------------------
-- Table structure for kw_member
-- ----------------------------
DROP TABLE IF EXISTS `kw_member`;
CREATE TABLE `kw_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `user_name` varchar(64) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `hiredate` datetime DEFAULT NULL,
  `real_name` varchar(64) NOT NULL,
  `telephone` varchar(64) DEFAULT NULL,
  `avatar` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3w4x463xehrckku45kvs911ml` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_member
-- ----------------------------
INSERT INTO `kw_member` VALUES ('1', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', '', '1747746938@qq.com', 'GIRL', '2019-05-01 00:00:00', '管理员', '18210182023', '/attachment/avatar/2019/05/eb2e2d75-256b-4e3e-9eeb-ba5cdfc264ce.jpg');

-- ----------------------------
-- Table structure for kw_member_role
-- ----------------------------
DROP TABLE IF EXISTS `kw_member_role`;
CREATE TABLE `kw_member_role` (
  `member_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKb17jj8ou6rp2lkxb5xen5tixe` (`role_id`),
  KEY `FK76a8mc5mub4tu1gndxph4ypls` (`member_id`),
  CONSTRAINT `FKqotkax6ppug3frgkwdimkpfli` FOREIGN KEY (`member_id`) REFERENCES `kw_member` (`id`),
  CONSTRAINT `FK76a8mc5mub4tu1gndxph4ypls` FOREIGN KEY (`member_id`) REFERENCES `kw_member` (`id`),
  CONSTRAINT `FKb17jj8ou6rp2lkxb5xen5tixe` FOREIGN KEY (`role_id`) REFERENCES `kw_role` (`id`),
  CONSTRAINT `FKouuby7915oegudjmka5u9p89e` FOREIGN KEY (`role_id`) REFERENCES `kw_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_member_role
-- ----------------------------
INSERT INTO `kw_member_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for kw_resource
-- ----------------------------
DROP TABLE IF EXISTS `kw_resource`;
CREATE TABLE `kw_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fun_urls` varchar(1024) DEFAULT NULL,
  `menu_url` varchar(128) DEFAULT NULL,
  `res_key` varchar(128) NOT NULL,
  `res_name` varchar(128) NOT NULL,
  `res_type` varchar(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ic22mdco0hjpt8qjosdnyhxcx` (`res_key`),
  KEY `FKo4megp72bdlng5bpjmo56v1wk` (`parent_id`),
  CONSTRAINT `FKkqilo0s6kx9j1h36he1lucl0d` FOREIGN KEY (`parent_id`) REFERENCES `kw_resource` (`id`),
  CONSTRAINT `FKo4megp72bdlng5bpjmo56v1wk` FOREIGN KEY (`parent_id`) REFERENCES `kw_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_resource
-- ----------------------------
INSERT INTO `kw_resource` VALUES ('1', '', '', 'system', '系统管理', 'MENU', '', null, '0');
INSERT INTO `kw_resource` VALUES ('3', '/system/member/list', '/system/member', 'system-member', '用户管理', 'MENU', '', '1', null);
INSERT INTO `kw_resource` VALUES ('10', '/system/role/list,/system/role/resource/tree', '/system/role', 'system-role', '角色管理', 'MENU', '', '1', null);
INSERT INTO `kw_resource` VALUES ('11', '/system/resource/list', '/system/resource', 'system-resource', '资源管理', 'MENU', '', '1', null);
INSERT INTO `kw_resource` VALUES ('12', '', '', 'role-create', '创建角色', 'FUNCTION', '', '10', null);
INSERT INTO `kw_resource` VALUES ('13', '', '/system/role/delete', 'role-delete', '删除角色', 'FUNCTION', '', '10', null);
INSERT INTO `kw_resource` VALUES ('14', '/system/role/update,/system/role/save', '', 'role-save', '保存编辑', 'FUNCTION', '', '10', null);
INSERT INTO `kw_resource` VALUES ('17', '/system/role/resource/save', '', 'reole-resource-save', '分配资源', 'FUNCTION', '', '10', null);
INSERT INTO `kw_resource` VALUES ('18', '/system/resource/form,/system/resource/parent/tree,/system/resource/save', '', 'resource-create', '创建资源', 'FUNCTION', '', '11', null);
INSERT INTO `kw_resource` VALUES ('19', '/system/resource/form,/system/resource/parent/tree,/system/resource/save', '', 'resource-edit', '编辑', 'FUNCTION', '', '11', null);
INSERT INTO `kw_resource` VALUES ('20', '/system/resource/delete', '', 'resource-delete', '删除', 'FUNCTION', '', '11', null);
INSERT INTO `kw_resource` VALUES ('21', '/system/member/form,/system/member/save', '', 'member-create', '创建用户', 'FUNCTION', '', '3', null);
INSERT INTO `kw_resource` VALUES ('22', '/system/member/delete', '', 'member-delete', '删除用户', 'FUNCTION', '', '3', null);
INSERT INTO `kw_resource` VALUES ('23', '/system/member/form,/system/member/update', '', 'member-edit', '编辑用户', 'FUNCTION', '', '3', null);
INSERT INTO `kw_resource` VALUES ('26', '/system/member/password/reset', '', 'member-reset-password', '重置密码', 'FUNCTION', '', '3', null);
INSERT INTO `kw_resource` VALUES ('28', '/system/dict/page', '/system/dict', 'system-dict', '数据字典', 'MENU', '', '1', null);
INSERT INTO `kw_resource` VALUES ('29', '/system/dict/form,/system/dict/save', '', 'dict-create', '创建数据字典', 'FUNCTION', '', '28', null);
INSERT INTO `kw_resource` VALUES ('30', '/system/dict/form,/system/dict/update', '', 'dict-edit', '编辑数据字典', 'FUNCTION', '', '28', null);
INSERT INTO `kw_resource` VALUES ('31', '/system/dict/delete', '', 'dict-delete', '删除数据字典', 'FUNCTION', '', '28', null);
INSERT INTO `kw_resource` VALUES ('33', '/system/dictItem/page', '/system/dictItem', 'system-dictItem', '数据字典项', 'MENU', '', '1', null);
INSERT INTO `kw_resource` VALUES ('34', '/system/dictItem/form,/system/dictItem/save', '', 'dictItem-create', '创建数据字典项', 'FUNCTION', '', '33', null);
INSERT INTO `kw_resource` VALUES ('35', '/system/dictItem/form,/system/dictItem/update', '', 'dictItem-edit', '编辑数据字典项', 'FUNCTION', '', '33', null);
INSERT INTO `kw_resource` VALUES ('36', '/system/dictItem/delete', '', 'dictItem-delete', '删除数据字典项', 'MENU', '', '33', null);
INSERT INTO `kw_resource` VALUES ('37', '', '', 'kw', '内容管理', 'MENU', '', null, '0');
INSERT INTO `kw_resource` VALUES ('38', '/kw/article/page', '/kw/article', 'kw-article', '内容发布', 'MENU', '', '37', null);
INSERT INTO `kw_resource` VALUES ('39', '/kw/article/form,/kw/article/save', '', 'article-create', '创建内容', 'FUNCTION', '', '38', null);
INSERT INTO `kw_resource` VALUES ('40', '/kw/article/form,/kw/article/update', '', 'article-edit', '编辑内容', 'FUNCTION', '', '38', null);
INSERT INTO `kw_resource` VALUES ('41', '/kw/article/delete', '', 'article-delete', '删除内容', 'FUNCTION', '', '38', null);

-- ----------------------------
-- Table structure for kw_role
-- ----------------------------
DROP TABLE IF EXISTS `kw_role`;
CREATE TABLE `kw_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(512) DEFAULT NULL,
  `role_name` varchar(30) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r0jsnwb00o0n376ghyuahuqfg` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_role
-- ----------------------------
INSERT INTO `kw_role` VALUES ('1', '有系统所有权限', '管理员', '');
INSERT INTO `kw_role` VALUES ('2', '编辑图文内容', '编辑人员', '');

-- ----------------------------
-- Table structure for kw_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `kw_role_resource`;
CREATE TABLE `kw_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  KEY `FKjwyt61kixx52wper9y0li38c2` (`resource_id`),
  KEY `FKasi3s87a7p562cyw0jt3m0isf` (`role_id`),
  CONSTRAINT `FK7vjv7sf00ujmpml30nk626e1i` FOREIGN KEY (`role_id`) REFERENCES `kw_role` (`id`),
  CONSTRAINT `FK43af0674pi0asmo8c57741k2y` FOREIGN KEY (`resource_id`) REFERENCES `kw_resource` (`id`),
  CONSTRAINT `FKasi3s87a7p562cyw0jt3m0isf` FOREIGN KEY (`role_id`) REFERENCES `kw_role` (`id`),
  CONSTRAINT `FKjwyt61kixx52wper9y0li38c2` FOREIGN KEY (`resource_id`) REFERENCES `kw_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kw_role_resource
-- ----------------------------
INSERT INTO `kw_role_resource` VALUES ('1', '1');
INSERT INTO `kw_role_resource` VALUES ('1', '3');
INSERT INTO `kw_role_resource` VALUES ('1', '21');
INSERT INTO `kw_role_resource` VALUES ('1', '22');
INSERT INTO `kw_role_resource` VALUES ('1', '23');
INSERT INTO `kw_role_resource` VALUES ('1', '10');
INSERT INTO `kw_role_resource` VALUES ('1', '12');
INSERT INTO `kw_role_resource` VALUES ('1', '13');
INSERT INTO `kw_role_resource` VALUES ('1', '14');
INSERT INTO `kw_role_resource` VALUES ('1', '17');
INSERT INTO `kw_role_resource` VALUES ('1', '11');
INSERT INTO `kw_role_resource` VALUES ('1', '18');
INSERT INTO `kw_role_resource` VALUES ('1', '19');
INSERT INTO `kw_role_resource` VALUES ('1', '20');

-- ----------------------------
-- Table structure for s_dict
-- ----------------------------
DROP TABLE IF EXISTS `s_dict`;
CREATE TABLE `s_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_code` varchar(50) DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  `order_num` int(10) DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_dict
-- ----------------------------
INSERT INTO `s_dict` VALUES ('5', 'GENDER', '性别', '1', '');
INSERT INTO `s_dict` VALUES ('6', 'ARTICLE_TYPE', '文章类型', '2', '1数码产品');

-- ----------------------------
-- Table structure for s_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `s_dict_item`;
CREATE TABLE `s_dict_item` (
  `dict_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典项ID',
  `dict_id` bigint(20) DEFAULT NULL COMMENT '字典ID',
  `item_code` int(10) DEFAULT NULL COMMENT '字典项编码',
  `item_name` varchar(255) DEFAULT NULL COMMENT '字典项名称',
  `order_num` int(10) DEFAULT NULL COMMENT '排序号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_dict_item
-- ----------------------------
INSERT INTO `s_dict_item` VALUES ('3', '5', '1', '男', '1', '');
INSERT INTO `s_dict_item` VALUES ('4', '5', '2', '女', '2', '');
INSERT INTO `s_dict_item` VALUES ('5', '6', '1', '数码产品', '1', '');
