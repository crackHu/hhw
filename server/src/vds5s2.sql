/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : vds5s2

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2016-12-27 18:08:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_plug_function
-- ----------------------------
DROP TABLE IF EXISTS `tb_plug_function`;
CREATE TABLE `tb_plug_function` (
  `pf_id` varchar(255) NOT NULL,
  `pf_pi_id` varchar(255) NOT NULL,
  `pf_name` varchar(255) DEFAULT NULL,
  `pf_status` int(2) DEFAULT NULL,
  `pf_msg` varchar(255) DEFAULT NULL,
  `pf_is_remove` int(255) DEFAULT NULL,
  `pf_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pf_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plug_function
-- ----------------------------
INSERT INTO `tb_plug_function` VALUES ('2c9081ff593fbf4e01593fbf663d0003', '2c9081ff593fbf4e01593fbf65930002', '发送验证码', '2', 'The appkey 23499998 is only allowed to call from its ip white list, but the source ip 116.23.231.221 is not included', '0', '2016-12-27 18:07:06', '2016-12-27 18:07:06');
INSERT INTO `tb_plug_function` VALUES ('2c9081ff593fbf4e01593fbf6ab40004', '2c9081ff593fbf4e01593fbf65930002', '获取SMS用户', '1', '启动成功', '0', '2016-12-27 18:07:06', '2016-12-27 18:07:06');

-- ----------------------------
-- Table structure for tb_plug_in
-- ----------------------------
DROP TABLE IF EXISTS `tb_plug_in`;
CREATE TABLE `tb_plug_in` (
  `pi_id` varchar(32) NOT NULL,
  `pi_package` varchar(255) NOT NULL,
  `pi_name` varchar(255) DEFAULT NULL,
  `pi_status` int(255) DEFAULT NULL,
  `pi_msg` varchar(255) DEFAULT NULL,
  `pi_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pi_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_plug_in
-- ----------------------------
INSERT INTO `tb_plug_in` VALUES ('2c9081ff593fbf4e01593fbf62560000', 'plug', 'plug_V1.0.0', '2', '启动成功...', '2016-12-27 18:07:04', '2016-12-27 18:07:04');
INSERT INTO `tb_plug_in` VALUES ('2c9081ff593fbf4e01593fbf642b0001', 'user', 'user_V1.0.0', '2', '启动成功...', '2016-12-27 18:07:05', '2016-12-27 18:07:05');
INSERT INTO `tb_plug_in` VALUES ('2c9081ff593fbf4e01593fbf65930002', 'sms', 'sms_V1.0.1', '2', '启动成功...', '2016-12-27 18:07:05', '2016-12-27 18:07:05');

-- ----------------------------
-- Table structure for tb_security_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_security_code`;
CREATE TABLE `tb_security_code` (
  `sc_id` varchar(255) NOT NULL,
  `sc_code` varchar(255) NOT NULL,
  `sc_phone` varchar(255) NOT NULL,
  `sc_expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_security_code
-- ----------------------------
INSERT INTO `tb_security_code` VALUES ('2c9081ff5905ad3a015905ad5d8a0000', '6591', '13711687995', '2016-12-23 17:02:32');

-- ----------------------------
-- Table structure for tb_sms_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_config`;
CREATE TABLE `tb_sms_config` (
  `sms_cf_id` varchar(32) NOT NULL,
  `sms_cf_cp_id` varchar(32) NOT NULL,
  `sms_cf_tp_code` varchar(255) NOT NULL,
  `sms_cf_value` varchar(255) DEFAULT NULL COMMENT '模板（展示用的）',
  `sms_cf_status` int(2) NOT NULL DEFAULT '0' COMMENT '模板的状态（0 不可用 1可用）',
  `sms_cf_is_remove` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0 否 1 是）',
  `sms_cf_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sms_cf_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`sms_cf_id`),
  KEY `s` (`sms_cf_cp_id`),
  CONSTRAINT `s` FOREIGN KEY (`sms_cf_cp_id`) REFERENCES `tb_sms_cp` (`sms_cp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_config
-- ----------------------------
INSERT INTO `tb_sms_config` VALUES ('11', '1', 'SMS_25520114', '短信', '1', '0', '2016-12-09 16:01:53', '2016-12-09 16:01:53');
INSERT INTO `tb_sms_config` VALUES ('12', '1', 'sms_1', 'tongzi', '0', '0', '2016-12-09 20:03:00', '2016-12-09 20:03:00');

-- ----------------------------
-- Table structure for tb_sms_cp
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_cp`;
CREATE TABLE `tb_sms_cp` (
  `sms_cp_id` varchar(32) NOT NULL,
  `sms_cp_name` varchar(255) NOT NULL COMMENT '公司名称',
  `sms_cp_app_key` varchar(255) NOT NULL,
  `sms_cp_secret` varchar(255) NOT NULL,
  `sms_is_remove` int(2) NOT NULL,
  `sms_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sms_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sms_cp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_cp
-- ----------------------------
INSERT INTO `tb_sms_cp` VALUES ('1', '钉钉医疗', '23499998', 'f0a81d7c250f81455c986ef66333b0a4', '0', '2016-12-09 11:46:06', '2016-12-09 11:46:06');
INSERT INTO `tb_sms_cp` VALUES ('2', '柏道科技', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('21', '柏道科技2', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('22', '柏道科技3', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('23', '柏道科技4', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('24', '柏道科技5', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('25', '柏道科技6', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('26', '柏道科技7', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('27', '柏道科技8', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('28', '柏道科技9', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');
INSERT INTO `tb_sms_cp` VALUES ('29', '柏道科技11', '120', '112', '0', '2016-12-26 17:49:44', '2016-12-26 17:49:44');

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `tk_id` varchar(255) NOT NULL,
  `tk_token_content` varchar(255) NOT NULL,
  `tk_us_id` varchar(255) NOT NULL,
  `tk_expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_token
-- ----------------------------
INSERT INTO `tb_token` VALUES ('2c9081ff5906e6ad015906e6d2650001', '161E3526D11042428CD5367FD6E2B08D', '2c9081ff5906e6ad015906e6d24d0000', '2017-01-19 15:39:56');
INSERT INTO `tb_token` VALUES ('2c9081ff59070dca0159070eb7290002', '3151AFE804CA44998AEB75A8E0CCED30', '2c9081ff59070dca0159070eb6ff0000', '2017-01-16 18:34:27');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `us_id` varchar(32) NOT NULL,
  `us_name` varchar(255) DEFAULT NULL,
  `us_user_name` varchar(255) DEFAULT NULL,
  `us_phone` varchar(255) DEFAULT NULL,
  `us_password` varchar(255) DEFAULT NULL,
  `us_type` varchar(255) NOT NULL,
  `us_is_remove` int(2) NOT NULL DEFAULT '0',
  `us_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `us_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '管理员', 'admin', null, '710E652D3DFE4DEFF74A704B8&08CC27', '2', '0', '2016-12-12 18:56:39', '2016-12-12 18:56:39');
INSERT INTO `tb_user` VALUES ('2c9081ff5906e6ad015906e6d24d0000', '未设置', 'cay', '13711687995', '710E652D3DFE4DEFF74A704B8&08CC27', '1', '0', '2016-12-16 17:29:17', '2016-12-16 17:29:17');
INSERT INTO `tb_user` VALUES ('2c9081ff59070dca0159070eb6ff0000', null, 'openId', null, '', 'UN', '0', '2016-12-16 17:55:22', '2016-12-16 17:55:22');

-- ----------------------------
-- Table structure for tb_user_other
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_other`;
CREATE TABLE `tb_user_other` (
  `uo_id` varchar(255) NOT NULL,
  `uo_us_id` varchar(255) DEFAULT NULL,
  `uo_type` varchar(255) DEFAULT NULL COMMENT 'QQ 还是微信',
  `uo_open_id` varchar(255) DEFAULT NULL COMMENT '第三方的accessToken',
  `uo_account` varchar(255) DEFAULT NULL COMMENT '第三方的账号',
  PRIMARY KEY (`uo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_other
-- ----------------------------
INSERT INTO `tb_user_other` VALUES ('2c9081ff59070dca0159070eb7230001', '2c9081ff59070dca0159070eb6ff0000', 'UN', 'openId', null);

-- ----------------------------
-- Table structure for tb_user_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_type`;
CREATE TABLE `tb_user_type` (
  `ut_id` varchar(255) NOT NULL,
  `ut_name` varchar(255) NOT NULL,
  `ut_is_remove` int(2) NOT NULL DEFAULT '0',
  `ut_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ut_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ut_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_type
-- ----------------------------
INSERT INTO `tb_user_type` VALUES ('1', '普通用户', '0', '2016-12-12 14:18:23', '2016-12-12 14:18:23');
INSERT INTO `tb_user_type` VALUES ('2', '管理员', '0', '2016-12-12 14:18:28', '2016-12-12 14:18:28');
INSERT INTO `tb_user_type` VALUES ('3', '超级管理员', '0', '2016-12-12 14:18:34', '2016-12-12 14:18:34');
