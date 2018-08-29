/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zzm

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-08-29 00:11:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resc
-- ----------------------------
DROP TABLE IF EXISTS `resc`;
CREATE TABLE `resc` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `res_type` varchar(50) DEFAULT NULL,
  `res_string` varchar(200) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resc
-- ----------------------------
INSERT INTO `resc` VALUES ('1', '', 'URL', '/jsp/adminPage.jsp', '管理员页面');
INSERT INTO `resc` VALUES ('2', '', 'URL', '/jsp/index.jsp', '');
INSERT INTO `resc` VALUES ('3', null, 'URL', '/test.action', '测试页面');
INSERT INTO `resc` VALUES ('4', null, 'URL', '/testJpa.action', '测试jpa');
