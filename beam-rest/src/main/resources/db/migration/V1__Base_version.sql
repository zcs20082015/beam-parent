/*
Navicat MySQL Data Transfer

Source Server         : 我的数据库
Source Server Version : 50721
Source Host           : 119.29.192.127:3306
Source Database       : beam_rest

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-30 10:24:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for beam_user
-- ----------------------------
DROP TABLE IF EXISTS `beam_user`;
CREATE TABLE `beam_user` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  `remarks` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of beam_user
-- ----------------------------
INSERT INTO `beam_user` VALUES ('0', 'string', 'string', 'string', 'string', 'string', '2018-09-28 01:57:58', '0', 'string', 'string', '0', '0', 'string', '0', '2018-09-28 01:57:58', '0', '2018-09-28 01:57:58', '1');
INSERT INTO `beam_user` VALUES ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '张三', '2017-05-05 00:00:00', '2', 'sn93@qq.com', '18200000000', '1', '25', null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('44', null, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', '1', 'abc@123.com', '', '3', null, null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('45', null, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', '1', '', '', '1', null, null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('46', null, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', '1', '', '', '1', null, null, null, null, null, null, '1');
INSERT INTO `beam_user` VALUES ('1045497501349797889', 'string', 'string', 'string', 'string', 'string', '2018-09-28 02:04:40', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-28 02:16:43', '1', '2018-09-28 02:16:43', '1');
INSERT INTO `beam_user` VALUES ('1045497989621293058', 'string', 'string', 'string', 'string', 'string', '2018-09-28 02:18:33', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-28 02:18:40', '1', '2018-09-28 02:18:40', '1');
INSERT INTO `beam_user` VALUES ('1045498075243814913', 'string', 'string', 'string', 'string', 'string', '2018-09-28 02:18:33', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-28 02:19:00', '1', '2018-09-28 02:19:00', '1');
INSERT INTO `beam_user` VALUES ('1045936460131405825', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-29 07:20:59', '1', '2018-09-29 07:20:59', '1');
INSERT INTO `beam_user` VALUES ('1045936835555168258', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-29 07:22:29', '1', '2018-09-29 07:22:29', '1');
INSERT INTO `beam_user` VALUES ('1045949263923900417', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-29 08:11:52', '1', '2018-09-29 08:11:52', '1');
INSERT INTO `beam_user` VALUES ('1045953092824330241', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-29 08:27:05', '1', '2018-09-29 08:27:05', '1');
INSERT INTO `beam_user` VALUES ('1045957588820422657', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-29 08:44:57', '1', '2018-09-29 08:44:57', '1');
SET FOREIGN_KEY_CHECKS=1;
