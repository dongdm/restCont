/*
Navicat MySQL Data Transfer

Source Server         : fund-web
Source Server Version : 50622
Source Host           : 192.168.0.116:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2017-06-20 15:33:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ba_channel_content
-- ----------------------------
DROP TABLE IF EXISTS `ba_channel_content`;
CREATE TABLE `ba_channel_content` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `channel_id` int(10) NOT NULL COMMENT '栏目id',
  `describe` text CHARACTER SET utf8 COMMENT '描述',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `mark` varchar(1024) CHARACTER SET utf8 NOT NULL COMMENT '备注',
  `createBy` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `updateBy` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '更新人',
  `updateDate` datetime NOT NULL COMMENT '更新时间',
  `deleFlag` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT 'N' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='公司节点描述';

-- ----------------------------
-- Records of ba_channel_content
-- ----------------------------
