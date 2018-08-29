/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zzm

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-08-29 00:11:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resc_role
-- ----------------------------
DROP TABLE IF EXISTS `resc_role`;
CREATE TABLE `resc_role` (
  `resc_id` bigint(20) NOT NULL DEFAULT '0',
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`resc_id`,`role_id`),
  KEY `fk_resc_role_role` (`role_id`),
  CONSTRAINT `fk_resc_role_resc` FOREIGN KEY (`resc_id`) REFERENCES `resc` (`id`),
  CONSTRAINT `fk_resc_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resc_role
-- ----------------------------
INSERT INTO `resc_role` VALUES ('1', '1');
INSERT INTO `resc_role` VALUES ('2', '1');
INSERT INTO `resc_role` VALUES ('2', '2');
INSERT INTO `resc_role` VALUES ('3', '3');
INSERT INTO `resc_role` VALUES ('4', '3');
