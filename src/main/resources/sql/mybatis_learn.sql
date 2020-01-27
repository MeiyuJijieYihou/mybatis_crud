/*
 Navicat Premium Data Transfer

 Source Server         : dx
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : mybatis_learn

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 27/01/2020 22:43:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(24) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(24) DEFAULT NULL,
  `phone` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'test1', '2020-01-20', 12, '232');
INSERT INTO `user` VALUES (2, 'test2', '2020-01-13', 23, '2323');
INSERT INTO `user` VALUES (3, 'test3', '2020-01-07', 3, '2345654');
INSERT INTO `user` VALUES (4, '张三', '2020-01-22', 2, '3432ds');
INSERT INTO `user` VALUES (6, '王五', '2020-01-02', 5, '收到公司的');
INSERT INTO `user` VALUES (7, '大张三', '2020-01-08', 34, '的所发生的');
INSERT INTO `user` VALUES (8, '三张', '2020-01-03', 35, '是');
INSERT INTO `user` VALUES (9, 'test_save', '2020-01-24', 2, '32424');
INSERT INTO `user` VALUES (10, 'test_save', '2020-01-24', 2, '32424');
INSERT INTO `user` VALUES (11, 'test_save', '2020-01-27', 2, '32424');
INSERT INTO `user` VALUES (12, 'test_save', '2020-01-27', 2, '32424');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
