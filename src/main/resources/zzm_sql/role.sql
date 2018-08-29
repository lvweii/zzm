/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zzm

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-08-29 00:11:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员角色');
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '用户角色');
INSERT INTO `role` VALUES ('3', 'ROLE_TEST', '测试角色');
