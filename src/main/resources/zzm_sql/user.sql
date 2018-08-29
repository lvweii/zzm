/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : zzm

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-08-29 00:11:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '1', '管理员');
INSERT INTO `user` VALUES ('2', 'user', 'user', '1', '用户');
INSERT INTO `user` VALUES ('3', 'test', 'test', '1', '测试');
