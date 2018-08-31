/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-31 16:05:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('222', '0XOxEbIk0xDCSvFghD8kVw==', 'WQbdLnSEreGLP1V1lb8org==', '2018-08-28 17:12:50');
INSERT INTO `persistent_logins` VALUES ('www', '1Yq0sVmHqf3DkoY8smVaaA==', 'FID20z8+RojdZfTg5QqMJA==', '2018-08-28 17:14:41');
INSERT INTO `persistent_logins` VALUES ('www', '4gMl6XHhftVLdbVqHVtOdQ==', 'GNqRefhqxiWf6QC8ddj9kA==', '2018-08-28 16:44:37');
INSERT INTO `persistent_logins` VALUES ('WWW', 'dbhngZz0o8Ub/PtWpEXyPQ==', 'pxkVW1/kxvuE2l9TOjML9g==', '2018-08-27 11:49:11');
INSERT INTO `persistent_logins` VALUES ('www', 'erQOyVEmpAWSk6MGEIijPA==', 'h0zN3YYqznonToYEujYY1A==', '2018-08-22 09:11:21');
INSERT INTO `persistent_logins` VALUES ('www', 'ffdOCig1x2KrcburWAAldQ==', '7auBpiuRzgVjuqvRhYkauA==', '2018-08-20 14:38:03');
INSERT INTO `persistent_logins` VALUES ('kkk', 'nH5RKfzNQL7nEReolekv0Q==', '3YGPZG15/qavqGYejXY9qQ==', '2018-08-20 14:48:47');
INSERT INTO `persistent_logins` VALUES ('www', 'paD8qj+lT6KKB43gtFqLAw==', 'uySor+8ulAs18WQ+jFIESA==', '2018-08-28 17:14:30');
INSERT INTO `persistent_logins` VALUES ('www', 'QVKQy+4HrocpcueS9NgoCg==', 'yge72BqOV/aHtVzgOWBX1w==', '2018-08-20 14:38:33');
INSERT INTO `persistent_logins` VALUES ('www', 'SqSaysquggytrDyIh9uGCQ==', 'wgzXlnddHzJUrpqp6CS4bg==', '2018-08-27 17:11:54');
INSERT INTO `persistent_logins` VALUES ('www', 'T6qyVqjjmDegBTW3GT77sA==', 'sjyz+88L++g/ww4l/G9GxA==', '2018-08-22 10:40:50');
INSERT INTO `persistent_logins` VALUES ('www', 'vKrx0qyejVjQVi/dayM1Uw==', 'I/ivFCXQyHEXc3YLSLkSmA==', '2018-08-28 15:45:13');
INSERT INTO `persistent_logins` VALUES ('wcl', 'vuHqqG8Rh0gn5pMy2QRIWA==', 'ZU/cui8VM2UFPBZ68TMhKQ==', '2018-08-27 17:11:27');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'wcl');
INSERT INTO `test` VALUES ('2', '笑笑');

-- ----------------------------
-- Table structure for userconnection
-- ----------------------------
DROP TABLE IF EXISTS `userconnection`;
CREATE TABLE `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of userconnection
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('mkyong', '123456', '1');
INSERT INTO `users` VALUES ('wcl', '123456', '1');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`role`,`username`),
  KEY `fk_username_idx` (`username`),
  CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('2', 'mkyong', 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES ('4', 'wcl', 'ROLE_ADMIN');
INSERT INTO `user_roles` VALUES ('1', 'mkyong', 'ROLE_USER');
INSERT INTO `user_roles` VALUES ('3', 'wcl', 'ROLE_USER');
