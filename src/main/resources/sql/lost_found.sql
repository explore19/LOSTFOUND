/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : lost_found

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 05/12/2019 20:45:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item_type
-- ----------------------------
DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_type
-- ----------------------------
INSERT INTO `item_type` VALUES (1, '成为撒福娃', NULL, NULL);
INSERT INTO `item_type` VALUES (2, '我是憨憨', NULL, NULL);
INSERT INTO `item_type` VALUES (3, '憨憨是我', NULL, NULL);
INSERT INTO `item_type` VALUES (4, '我是小麻', NULL, NULL);
INSERT INTO `item_type` VALUES (5, '小麻是我', NULL, NULL);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `current_time` datetime(0) NOT NULL COMMENT '最后一次的登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for place
-- ----------------------------
DROP TABLE IF EXISTS `place`;
CREATE TABLE `place`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用来绑定用户 ',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '物品的名称',
  `lose_time` datetime(0) DEFAULT NULL COMMENT '丢失的时间',
  `lost_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '丢失的地点',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系方式',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '丢失物品的图片',
  `type` int(11) DEFAULT NULL COMMENT '物品类型',
  `details` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '丢失物品的详情',
  `post_type` int(255) DEFAULT NULL COMMENT '0-失物 1-拾物',
  `browse_points` int(11) DEFAULT NULL COMMENT '浏览次数',
  `praise_points` int(11) DEFAULT NULL COMMENT '点赞数',
  `status` int(255) DEFAULT NULL COMMENT '0-未审核 1-通过审核 2-未通过审核 ',
  `create_time` datetime(0) DEFAULT NULL COMMENT '消息发布时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `用户ID`(`user_id`) USING BTREE,
  INDEX `物品类型`(`type`) USING BTREE,
  CONSTRAINT `物品类型` FOREIGN KEY (`type`) REFERENCES `item_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `用户ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, 1, '手表', '2019-11-21 06:12:06', '大学生活动中心', NULL, 'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erSw1mEgM3BvAbSypP1iakzrYYEL9lYTZKBIe5ch6XCn23D5bpzuKdxMcuBqQVAzsxbjWTLyYFEnsA/132', NULL, '我是梁家耀，我是个铁憨憨', NULL, NULL, NULL, NULL, '2019-11-20 20:50:24', NULL);
INSERT INTO `post` VALUES (2, NULL, '发放', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (3, NULL, '花江村', NULL, '小黄', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (4, 1, '发放', '2019-12-19 19:32:34', '五教', '', NULL, NULL, '哈哈哈丢啊我国的哇波伏娃记福娃', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (5, 1, '服务器父亲', NULL, '的爱屋哇', NULL, NULL, NULL, '达瓦达瓦 ', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (6, 1, '带我去', NULL, '打大瓦房安抚啊', NULL, NULL, NULL, '达瓦富饶富饶我去染发无法', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise`  (
  `id` int(11) NOT NULL COMMENT '点赞的id',
  `post_id` int(11) DEFAULT NULL COMMENT '对用的帖子的Id',
  `user_id` int(11) DEFAULT NULL COMMENT '点赞的用户的Id',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建帖子的时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '最新一次点赞的时间',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `praise_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `praise_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `reply_id` int(11) DEFAULT NULL,
  `type` int(5) NOT NULL COMMENT '0-回复帖子 1-回复非帖子的信息',
  `info` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复消息的内容',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `用户的ID`(`user_id`) USING BTREE,
  INDEX `帖子的ID`(`post_id`) USING BTREE,
  INDEX `回复的ID`(`reply_id`) USING BTREE,
  CONSTRAINT `回复的ID` FOREIGN KEY (`reply_id`) REFERENCES `reply` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `帖子的ID` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `用户的ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rotation_chart
-- ----------------------------
DROP TABLE IF EXISTS `rotation_chart`;
CREATE TABLE `rotation_chart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0-帖子轮播图 1-广告轮播图',
  `post_id` int(11) DEFAULT NULL,
  `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `creat_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `帖子ID`(`post_id`) USING BTREE,
  CONSTRAINT `帖子ID` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小程序账号id',
  `sno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像path',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL COMMENT '用户资料变更时间',
  `current_time` datetime(0) DEFAULT NULL COMMENT '用户最后在线时间',
  `status` int(10) DEFAULT NULL COMMENT '-1-删除  0-是正常 1-是封禁 2-信息不完整 3-刚注册',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '123', '123456', '小高', '小花', '1', 'http://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erSw1mEgM3BvAbSypP1iakzrYYEL9lYTZKBIe5ch6XCn23D5bpzuKdxMcuBqQVAzsxbjWTLyYFEnsA/132', '1131231', '21313131', '2019-11-13 00:05:06', '2019-11-13 00:05:06', NULL, 3);
INSERT INTO `user` VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, NULL, '4578945', '小黄', '小花', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
