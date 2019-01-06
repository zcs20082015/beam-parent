/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : beam

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2019-01-04 09:04:12
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
INSERT INTO `beam_user` VALUES ('1', 'girl.gif', 'admin', 'ecfadcde9305f8891bcfe5a1e28c253e', '8pgby', '张三', '2017-05-05 00:00:00', '2', 'sn93@qq.com', '18200000000', '1', '25', null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('44', null, 'test', '45abb7879f6a8268f1ef600e6038ac73', 'ssts3', 'test', '2017-05-01 00:00:00', '1', 'abc@123.com', '', '3', null, null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('45', null, 'boss', '71887a5ad666a18f709e1d4e693d5a35', '1f7bf', '老板', '2017-12-04 00:00:00', '1', '', '', '1', null, null, null, null, null, null, '0');
INSERT INTO `beam_user` VALUES ('46', null, 'manager', 'b53cac62e7175637d4beb3b16b2f7915', 'j3cs9', '经理', '2017-12-04 00:00:00', '1', '', '', '1', null, null, null, null, null, null, '1');
INSERT INTO `beam_user` VALUES ('1046317597227974658', 'string', 'string', 'string', 'string', 'string', '2018-09-29 07:20:50', '0', 'string', 'string', '0', '0', 'string', '1', '2018-09-30 08:35:29', '1', '2018-09-30 09:05:55', '1');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1080345897063223297', 'DEFAULT', '0 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1080345897063223297', 'DEFAULT', null, 'com.hsshy.beam.quartz.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E68737368792E6265616D2E7379732E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000269647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7872002C636F6D2E68737368792E6265616D2E636F6D6D6F6E2E626173652E656E746974792E52657374456E74697479EE8CF07CFDCA369C0200024C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000A75706461746554696D6571007E000D78720030636F6D2E68737368792E6265616D2E636F6D6D6F6E2E626173652E656E746974792E4162737472616374456E74697479040721C4286B1A7C0200024A000B63757272656E74506167654A00087061676553697A6578720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C000000000000000102000078700000000000000000000000000000000A737200126A6176612E73716C2E54696D657374616D702618D5C80153BF650200014900056E616E6F737872000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001680D307CA078163B87407371007E00117708000001680D307CA078163B8740740008746573745461736B74000B30202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700EFE28DE95011001740005746573743270740006E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0018000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'LAPTOP-JP1B0EBJ1546483207689', '1546484558532', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1080345897063223297', 'DEFAULT', 'TASK_1080345897063223297', 'DEFAULT', null, '1546409520000', '-1', '5', 'PAUSED', 'CRON', '1546409508000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E68737368792E6265616D2E7379732E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000269647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7872002C636F6D2E68737368792E6265616D2E636F6D6D6F6E2E626173652E656E746974792E52657374456E74697479EE8CF07CFDCA369C0200024C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000A75706461746554696D6571007E000D78720030636F6D2E68737368792E6265616D2E636F6D6D6F6E2E626173652E656E746974792E4162737472616374456E74697479040721C4286B1A7C0200024A000B63757272656E74506167654A00087061676553697A6578720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C000000000000000102000078700000000000000000000000000000000A7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001680D307CA0787371007E00117708000001680D3654B878740008746573745461736B74000B30202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700EFE28DE95011001740005746573743270740006E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0017000000017800);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1080345897063223298 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('1080345897063223297', 'testTask', 'test2', null, '0 * * * * ?', '1', '测试', '2019-01-02 06:11:48', '2019-01-02 06:18:11');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `version` int(11) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone8', '100', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '20');
INSERT INTO `seckill` VALUES ('1001', '500元秒杀ipad2', '100', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '2');
INSERT INTO `seckill` VALUES ('1002', '300元秒杀小米4', '100', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '1');
INSERT INTO `seckill` VALUES ('1003', '200元秒杀红米note', '100', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '2018-05-10 15:31:53', '0');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `id` bigint(20) NOT NULL COMMENT '秒杀商品id',
  `seckill_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `state` tinyint(4) NOT NULL COMMENT '状态标示：-1指无效，0指成功，1指已付款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`,`user_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"uTUUJ1fidpo5Hc6b7qJXd2i5SyVUzsBvQJgKn_N6\",\"qiniuBucketName\":\"hsshy\",\"qiniuDomain\":\"http://img.hsshy.cn\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"_XKtAamyCjnQN_rW-cqjSOb5X5bmDONgfDBknzbR\",\"type\":1}', '1', '云存储配置信息', null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '总公司', '0', '0', '2018-12-18 21:43:48', '2018-12-18 21:43:53');
INSERT INTO `sys_dept` VALUES ('2', '1', '厦门分公司', '1', '0', '2018-12-22 23:28:09', '2018-12-22 23:28:11');
INSERT INTO `sys_dept` VALUES ('3', '1', '福州分公司', '2', '0', '2018-12-22 23:28:14', '2018-12-22 23:28:16');
INSERT INTO `sys_dept` VALUES ('4', '3', '技术部', '0', '0', '2018-12-22 23:28:18', '2018-12-22 23:28:21');
INSERT INTO `sys_dept` VALUES ('5', '2', '销售部', '1', '0', '2018-12-22 23:28:23', '2018-12-22 15:30:34');
INSERT INTO `sys_dept` VALUES ('1076499576671084546', '2', '技术部', '1', '0', '2018-12-22 15:27:54', '2018-12-22 15:27:54');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `num` int(11) DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('16', '0', '0', '状态', null, null, null);
INSERT INTO `sys_dict` VALUES ('17', '1', '16', '启用', null, null, null);
INSERT INTO `sys_dict` VALUES ('18', '2', '16', '禁用', null, null, null);
INSERT INTO `sys_dict` VALUES ('29', '0', '0', '性别', null, null, null);
INSERT INTO `sys_dict` VALUES ('30', '1', '29', '男', null, null, null);
INSERT INTO `sys_dict` VALUES ('31', '2', '29', '女', null, null, null);
INSERT INTO `sys_dict` VALUES ('35', '0', '0', '账号状态', null, null, null);
INSERT INTO `sys_dict` VALUES ('36', '1', '35', '启用', null, null, null);
INSERT INTO `sys_dict` VALUES ('37', '2', '35', '冻结', null, null, null);
INSERT INTO `sys_dict` VALUES ('38', '3', '35', '已删除', null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统首页', 'dashboard', 'sys:dashboard:info', '1', 'el-icon-lx-home', '0', '1', '1', '2018-12-06 14:54:07', '2018-12-06 14:54:09', '0');
INSERT INTO `sys_menu` VALUES ('15', '0', '系统管理', 'sys', null, '0', 'el-icon-lx-settings', '15', '1', '1', '2018-12-10 17:52:34', '2018-12-10 17:52:37', '0');
INSERT INTO `sys_menu` VALUES ('16', '15', '用户管理', 'sysuser', 'sys:user:list', '1', null, '16', '1', '1', '2018-12-10 18:28:59', '2019-01-02 10:38:09', '0');
INSERT INTO `sys_menu` VALUES ('17', '15', '角色管理', 'sysrole', 'sys:role:list', '1', null, '17', '1', '1', '2018-12-10 18:35:34', '2019-01-03 01:33:09', '0');
INSERT INTO `sys_menu` VALUES ('18', '15', '菜单管理', 'sysmenu', 'sys:menu:tree', '1', null, '18', '1', '1', '2018-12-10 18:36:16', '2019-01-03 01:39:01', '0');
INSERT INTO `sys_menu` VALUES ('19', '15', '部门管理', 'sysdept', 'sys:dept:tree', '1', null, '19', '1', '1', '2018-12-10 18:37:20', '2019-01-03 01:41:49', '0');
INSERT INTO `sys_menu` VALUES ('21', '15', '定时任务', 'schedulejob', 'sys:schedule:list', '1', null, '21', '1', '1', '2018-12-10 18:39:14', '2019-01-03 01:42:33', '0');
INSERT INTO `sys_menu` VALUES ('1080412372541181953', '16', '新增/编辑', 'sysuser/save', 'sys:user:save', '2', null, '1', null, null, '2019-01-02 10:35:57', '2019-01-02 10:37:26', '0');
INSERT INTO `sys_menu` VALUES ('1080413095496585218', '16', '删除', 'sysuser/del', 'sys:user:del', '2', null, '2', null, null, '2019-01-02 10:38:50', '2019-01-02 10:38:50', '0');
INSERT INTO `sys_menu` VALUES ('1080636440926232577', '16', '重置密码', 'sysuser/reset/password', 'sys:user:resetPassword', '2', null, '3', null, null, '2019-01-03 01:26:19', '2019-01-03 01:29:49', '0');
INSERT INTO `sys_menu` VALUES ('1080636634896015362', '16', '修改密码', 'sysuser/change/password', 'sys:user:changePassword', '2', null, '4', null, null, '2019-01-03 01:27:06', '2019-01-03 01:30:05', '0');
INSERT INTO `sys_menu` VALUES ('1080637598663188481', '17', '新增/编辑', 'sysrole/save', 'sys:role:save', '2', null, '1', null, null, '2019-01-03 01:30:55', '2019-01-03 01:30:55', '0');
INSERT INTO `sys_menu` VALUES ('1080637823859564545', '17', '删除', 'sysrole/del', 'sys:role:del', '2', null, '2', null, null, '2019-01-03 01:31:49', '2019-01-03 01:31:49', '0');
INSERT INTO `sys_menu` VALUES ('1080638043456544769', '17', '权限配置', 'sysrole/save/menu/perm', 'sys:role:saveMenuPerm', '2', null, '3', null, null, '2019-01-03 01:32:42', '2019-01-03 01:32:42', '0');
INSERT INTO `sys_menu` VALUES ('1080639293405274114', '18', '保存', 'sysmenu/save', 'sys:menu:save', '2', null, '1', null, null, '2019-01-03 01:37:40', '2019-01-03 01:37:40', '0');
INSERT INTO `sys_menu` VALUES ('1080639432148656130', '18', '删除', 'sysmenu/del', 'sys:menu:delete', '2', null, '2', null, null, '2019-01-03 01:38:13', '2019-01-03 01:38:13', '0');
INSERT INTO `sys_menu` VALUES ('1080639575438663681', '18', '详情', 'sysmenu/info', 'sys:menu:info', '2', null, '3', null, null, '2019-01-03 01:38:47', '2019-01-03 01:38:47', '0');
INSERT INTO `sys_menu` VALUES ('1080639863587348482', '19', '保存', 'sysdept/save', 'sys:dept:save', '2', null, '1', null, null, '2019-01-03 01:39:55', '2019-01-03 01:39:55', '0');
INSERT INTO `sys_menu` VALUES ('1080640119691550722', '19', '删除', 'sysdept/del', 'sys:dept:del', '2', null, '2', null, null, '2019-01-03 01:40:57', '2019-01-03 01:40:57', '0');
INSERT INTO `sys_menu` VALUES ('1080640228772814849', '19', '详情', 'sysdept/info', 'sys:dept:info', '2', null, '3', null, null, '2019-01-03 01:41:23', '2019-01-03 01:41:23', '0');
INSERT INTO `sys_menu` VALUES ('1080640763785650177', '21', '新增', 'schedule/save', 'sys:schedule:save', '2', null, '1', null, null, '2019-01-03 01:43:30', '2019-01-03 01:43:30', '0');
INSERT INTO `sys_menu` VALUES ('1080640900176027650', '21', '修改', 'schedule/update', 'sys:schedule:update', '2', null, '2', null, null, '2019-01-03 01:44:03', '2019-01-03 01:44:03', '0');
INSERT INTO `sys_menu` VALUES ('1080641066287243266', '21', '删除', 'schedule/del', 'sys:schedule:del', '2', null, '3', null, null, '2019-01-03 01:44:42', '2019-01-03 01:44:42', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员', '2018-10-10 22:39:38', '2018-12-23 11:45:49');
INSERT INTO `sys_role` VALUES ('1076685137679704065', '普通管理员', '测试', '2018-12-23 03:45:15', '2018-12-23 03:45:15');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('50', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('51', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('52', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('53', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('54', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('55', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('56', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('57', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('58', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('59', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('60', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('61', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('62', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('63', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('64', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('65', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('66', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('67', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('68', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('69', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('70', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('161', '1076685137679704065', '1');
INSERT INTO `sys_role_menu` VALUES ('162', '1076685137679704065', '15');
INSERT INTO `sys_role_menu` VALUES ('163', '1076685137679704065', '16');
INSERT INTO `sys_role_menu` VALUES ('164', '1076685137679704065', '17');
INSERT INTO `sys_role_menu` VALUES ('165', '1076685137679704065', '18');
INSERT INTO `sys_role_menu` VALUES ('166', '1076685137679704065', '19');
INSERT INTO `sys_role_menu` VALUES ('167', '1076685137679704065', '21');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `dept_id` bigint(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `status` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `version` int(11) DEFAULT NULL COMMENT '保留字段',
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'https://wx.qlogo.cn/mmopen/vi_32/WgSOjJRaQq8FbBHGAr5gLHdaVia7SrnOwRZ9TL8lcDicXuMgqSR8rVgebFa9uIRiaLzYrPdtIia3jqjzkHVVOS38sw/132', 'hs', '77b271f537182db8e5b5586c2c7ab335b37a542cb34563bcc0a3756ec32aed1f', 'BFDze8hBJwwhOw3BDvhg', '黄帅', '4', '2018-10-08 16:05:42', '1', '457030599@qq.com', '18359923352', '1', null, null, null, '2018-10-31 17:48:40', '2019-01-02 10:29:58', '0');
INSERT INTO `sys_user` VALUES ('1058283224922398721', 'https://wx.qlogo.cn/mmopen/vi_32/WgSOjJRaQq8FbBHGAr5gLHdaVia7SrnOwRZ9TL8lcDicXuMgqSR8rVgebFa9uIRiaLzYrPdtIia3jqjzkHVVOS38sw/132', 'wlp', 'f37b6d76452f47a406a38561319004629a6a58000afa339feb5ac25b54377a76', 'yWV0dwMLuaO7jOD9s6Nz', '吴莉萍', '2', '2018-12-11 11:22:16', '2', '1', '1', '1', null, null, null, '2018-11-02 09:02:37', '2018-11-05 10:29:24', '0');
INSERT INTO `sys_user` VALUES ('1072429679148908546', 'https://wx.qlogo.cn/mmopen/vi_32/WgSOjJRaQq8FbBHGAr5gLHdaVia7SrnOwRZ9TL8lcDicXuMgqSR8rVgebFa9uIRiaLzYrPdtIia3jqjzkHVVOS38sw/132', 'test', '1cf8ecb16f2b0182837c6acf211a3fe2b3156b72ba05b0a606a6ceb9cf1a86ed', '78MVgcmn1GFw98yyvpty', 'test', '3', null, null, null, null, '1', null, null, null, '2018-12-11 09:55:35', '2019-01-03 02:52:04', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('2', '1058283224922398721', '1');
INSERT INTO `sys_user_role` VALUES ('16', '1', '1');
INSERT INTO `sys_user_role` VALUES ('18', '1072429679148908546', '1076685137679704065');
