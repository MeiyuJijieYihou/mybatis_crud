/*
 Navicat Premium Data Transfer

 Source Server         : dx
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : mybatis_learn

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 11/02/2020 21:29:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `money` int(24) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (10, 1, 100);
INSERT INTO `account` VALUES (20, 2, 200);
INSERT INTO `account` VALUES (30, 3, 300);
INSERT INTO `account` VALUES (40, 4, 400);
INSERT INTO `account` VALUES (50, 4, 500);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '老师');
INSERT INTO `role` VALUES (2, '学生');
INSERT INTO `role` VALUES (3, '教授');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `age` int(24) NULL DEFAULT NULL,
  `phone` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
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

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (10, 2, 1);
INSERT INTO `user_role` VALUES (20, 4, 2);
INSERT INTO `user_role` VALUES (30, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
