/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50619
Source Host           : 172.16.1.127:3306
Source Database       : hhw

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2017-01-09 12:09:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `od_id` varchar(32) NOT NULL,
  `od_barcode` varchar(32) NOT NULL,
  `od_us_id` varchar(32) NOT NULL,
  `od_status` int(2) DEFAULT NULL,
  `od_park_site_id` varchar(32) DEFAULT NULL,
  `od_park_type` varchar(2) DEFAULT NULL,
  `od_start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '开始停车时间',
  `od_end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '停止停车的时间 ',
  `od_car_id` varchar(32) DEFAULT NULL COMMENT '用户车辆id',
  `od_first_price` decimal(10,2) DEFAULT NULL COMMENT '开始支付金额',
  `od_add_price` decimal(10,2) DEFAULT NULL COMMENT '累计支付金额',
  `od_operator_id` varchar(32) DEFAULT '' COMMENT '操作人id',
  `od_is_invoice` int(2) DEFAULT NULL COMMENT '是否开发票',
  `od_invoice_title` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `od_is_privilege` int(2) NOT NULL DEFAULT '0' COMMENT '是否哟特权',
  `od_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `od_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`od_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_gift
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_gift`;
CREATE TABLE `tb_order_gift` (
  `od_gf_id` varchar(255) NOT NULL,
  `od_gf_us_id` varchar(255) DEFAULT NULL,
  `od_gf_gk_id` varchar(255) DEFAULT NULL,
  `od_gf_code` varchar(255) DEFAULT NULL,
  `od_gf_is_user` int(2) DEFAULT NULL,
  `od_gf_start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `od_gf_end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `od_gf_price` decimal(10,2) DEFAULT NULL,
  `od_gf_create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `od_gf_modfiy_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`od_gf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_gift
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_gift_kind
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_gift_kind`;
CREATE TABLE `tb_order_gift_kind` (
  `od_gk_id` varchar(255) NOT NULL,
  `od_gk_name` varchar(255) DEFAULT NULL,
  `od_gk_begin_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `od_gk_end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `od_gk_desc` varchar(255) DEFAULT NULL,
  `od_gk_is_enabled` int(2) NOT NULL DEFAULT '0',
  `od_gk_is_exchange` int(2) NOT NULL DEFAULT '0',
  `od_gk_point` int(20) DEFAULT NULL,
  `od_gk_price` decimal(10,2) DEFAULT NULL,
  `od_gk_price_expression` varchar(255) DEFAULT NULL,
  `od_gk_prefix` varchar(255) DEFAULT NULL,
  `od_gk_create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `od_gk_modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`od_gk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_gift_kind
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_payment`;
CREATE TABLE `tb_order_payment` (
  `od_pay_id` varchar(255) NOT NULL,
  `od_pay_us_id` varchar(255) DEFAULT NULL,
  `od_pay_od_id` varchar(255) DEFAULT NULL,
  `od_pay_account` varchar(255) DEFAULT NULL,
  `od_pay_amount` varchar(255) DEFAULT NULL,
  `od_pay_bank` varchar(255) DEFAULT NULL,
  `od_pay_expire_time` datetime DEFAULT NULL,
  `od_pay_fee` varchar(255) DEFAULT NULL,
  `od_pay_memo` varchar(255) DEFAULT NULL,
  `od_pay_method` varchar(255) DEFAULT NULL,
  `od_pay_time` datetime DEFAULT NULL,
  `od_pay_sno` varchar(255) DEFAULT NULL,
  `od_pay_status` int(255) DEFAULT NULL,
  `od_pay_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `od_pay_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`od_pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_payment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_pay_kind
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_pay_kind`;
CREATE TABLE `tb_order_pay_kind` (
  `od_pk_id` varchar(255) NOT NULL,
  `od_pk_name` varchar(255) DEFAULT NULL,
  `od_pk_code` varchar(255) DEFAULT NULL,
  `od_pk_order` int(255) DEFAULT NULL,
  `od_pk_desc` varchar(255) DEFAULT NULL,
  `od_pk_icon` varchar(255) DEFAULT NULL,
  `od_pk_method` varchar(255) DEFAULT NULL,
  `od_pk_timeout` datetime DEFAULT NULL,
  `od_pk_content` varchar(255) DEFAULT NULL,
  `od_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `od_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`od_pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_pay_kind
-- ----------------------------

-- ----------------------------
-- Table structure for tb_other_park_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_admin`;
CREATE TABLE `tb_other_park_admin` (
  `ot_id` varchar(32) NOT NULL,
  `ot_user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `ot_site_id` varchar(32) DEFAULT NULL COMMENT '停车场ID',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_permission` varchar(32) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_admin
-- ----------------------------
INSERT INTO `tb_other_park_admin` VALUES ('1', null, null, '2017-01-05 23:29:50', '2017-01-05 23:29:50', null);

-- ----------------------------
-- Table structure for tb_other_park_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_area`;
CREATE TABLE `tb_other_park_area` (
  `ot_id` varchar(32) NOT NULL,
  `ot_p_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_name` varchar(255) DEFAULT NULL COMMENT '地区名称',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_area
-- ----------------------------
INSERT INTO `tb_other_park_area` VALUES ('1', null, '2017-01-05 23:37:01', '2017-01-05 23:37:01', null);

-- ----------------------------
-- Table structure for tb_other_park_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_image`;
CREATE TABLE `tb_other_park_image` (
  `ot_id` varchar(32) NOT NULL,
  `ot_site_id` varchar(32) DEFAULT NULL COMMENT '停车场ID',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_url` varchar(255) DEFAULT NULL COMMENT '图片链接',
  `ot_description` varchar(255) DEFAULT NULL COMMENT '图片说明',
  `ot_primary` int(10) DEFAULT '0' COMMENT '是否为首图',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_image
-- ----------------------------
INSERT INTO `tb_other_park_image` VALUES ('1', null, '2017-01-05 23:57:05', '2017-01-05 23:57:05', null, null, '0');

-- ----------------------------
-- Table structure for tb_other_park_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_log`;
CREATE TABLE `tb_other_park_log` (
  `ot_id` varchar(32) NOT NULL,
  `ot_user_id` varchar(255) DEFAULT NULL COMMENT '操作员',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_content` varchar(255) DEFAULT NULL COMMENT '内容',
  `ot_type` int(11) DEFAULT '0' COMMENT '类型',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_log
-- ----------------------------
INSERT INTO `tb_other_park_log` VALUES ('1', null, '2017-01-06 00:02:59', '2017-01-06 00:02:59', null, '0');

-- ----------------------------
-- Table structure for tb_other_park_price_rule
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_price_rule`;
CREATE TABLE `tb_other_park_price_rule` (
  `ot_id` varchar(32) NOT NULL,
  `ot_site_id` varchar(32) DEFAULT NULL COMMENT '停车场ID',
  `ot_catalog_id` varchar(32) DEFAULT NULL COMMENT '车位类型ID',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `ot_price` double(10,2) DEFAULT '0.00' COMMENT '价格（每小时）',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_price_rule
-- ----------------------------
INSERT INTO `tb_other_park_price_rule` VALUES ('1', null, null, '2017-01-06 00:15:59', '2017-01-06 00:15:59', '0.00');

-- ----------------------------
-- Table structure for tb_other_park_res
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_res`;
CREATE TABLE `tb_other_park_res` (
  `ot_id` varchar(32) NOT NULL,
  `ot_site_id` varchar(32) DEFAULT NULL COMMENT '停车场ID',
  `ot_type` varchar(20) DEFAULT NULL COMMENT '类型，1：露天 2：地下 3：多层',
  `ot_create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_count` bigint(20) DEFAULT '0' COMMENT '车位数量',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_res
-- ----------------------------
INSERT INTO `tb_other_park_res` VALUES ('1', null, null, '2017-01-05 23:44:19', '2017-01-05 23:44:19', '0');

-- ----------------------------
-- Table structure for tb_other_park_site
-- ----------------------------
DROP TABLE IF EXISTS `tb_other_park_site`;
CREATE TABLE `tb_other_park_site` (
  `ot_id` varchar(32) NOT NULL,
  `ot_create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `ot_modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ot_name` varchar(255) DEFAULT NULL COMMENT '停车场名称',
  `ot_description` varchar(255) DEFAULT NULL COMMENT '描述',
  `ot_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `ot_is_enabled` bit(1) DEFAULT b'0' COMMENT '是否启用',
  `ot_area` varchar(255) DEFAULT NULL COMMENT '地区',
  `ot_point_x` decimal(20,0) DEFAULT '0' COMMENT '百度坐标X',
  `ot_point_y` decimal(20,0) DEFAULT '0' COMMENT '百度坐标Y',
  `ot_release_res` bigint(20) DEFAULT '0' COMMENT '可供使用的车位数量',
  `ot_free_res` bigint(20) DEFAULT '0' COMMENT '当前空闲车位数量',
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_other_park_site
-- ----------------------------
INSERT INTO `tb_other_park_site` VALUES ('1', '2017-01-05 23:16:42', '2017-01-05 23:16:42', null, null, null, '\0', null, '0', '0', '0', '0');

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
INSERT INTO `tb_plug_in` VALUES ('2c9081ff5980fdee015980fe087a0000', 'plug', 'plug_V1.0.0', '2', '启动成功...', '2017-01-09 10:10:49', '2017-01-09 10:10:49');
INSERT INTO `tb_plug_in` VALUES ('2c9081ff5980fdee015980fe09e20001', 'user', 'user_V1.0.0', '2', '启动成功...', '2017-01-09 10:10:49', '2017-01-09 10:10:49');

-- ----------------------------
-- Table structure for tb_transaction
-- ----------------------------
DROP TABLE IF EXISTS `tb_transaction`;
CREATE TABLE `tb_transaction` (
  `tr_id` varchar(255) NOT NULL,
  `tr_name` varchar(255) DEFAULT NULL COMMENT '交易内容',
  `tr_num` varchar(255) DEFAULT NULL COMMENT '交易单号',
  `tr_price` varchar(255) DEFAULT NULL COMMENT '金额',
  `tr_real_price` varchar(255) DEFAULT NULL COMMENT '最后的金额',
  `tr_status` int(2) DEFAULT NULL COMMENT '状态',
  `tr_type` int(2) DEFAULT NULL COMMENT '交易类型',
  `tr_way` varchar(255) DEFAULT NULL COMMENT '交易方式',
  `tr_status_name` varchar(255) DEFAULT NULL COMMENT '状态名称',
  `tr_wl_id` varchar(255) DEFAULT NULL COMMENT '钱包id',
  `tr_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tr_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_transaction
-- ----------------------------

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
INSERT INTO `tb_user` VALUES ('2c9081ff5980fdee015980fe20f50002', null, '122', null, '', '1', '0', '2017-01-09 10:10:55', '2017-01-09 10:10:55');

-- ----------------------------
-- Table structure for tb_user_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_message`;
CREATE TABLE `tb_user_message` (
  `um_id` varchar(255) NOT NULL,
  `um_us_id` varchar(255) DEFAULT NULL,
  `um_content` varchar(255) DEFAULT NULL,
  `um_type` varchar(255) DEFAULT NULL,
  `um_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`um_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_message
-- ----------------------------

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
INSERT INTO `tb_user_other` VALUES ('2c9081ff5980fdee015980fe20fa0003', '2c9081ff5980fdee015980fe20f50002', '1', '122', null);

-- ----------------------------
-- Table structure for tb_user_pay_psd
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_pay_psd`;
CREATE TABLE `tb_user_pay_psd` (
  `pp_id` varchar(255) NOT NULL,
  `pp_pay_password` varchar(255) DEFAULT NULL,
  `pp_wl_id` varchar(255) DEFAULT NULL,
  `pp_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pp_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_pay_psd
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_resources
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_resources`;
CREATE TABLE `tb_user_resources` (
  `rs_id` varchar(255) NOT NULL,
  `rs_name` varchar(255) DEFAULT NULL,
  `rs_url` varchar(255) DEFAULT NULL,
  `rs_is_remove` int(2) DEFAULT NULL,
  `rs_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rs_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_resources
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `ro_id` varchar(255) NOT NULL,
  `ro_name` varchar(255) NOT NULL COMMENT '权限角色名称',
  `ro_code` varchar(255) DEFAULT NULL COMMENT '权限代码code',
  `ro_is_remove` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `ro_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ro_modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_roles`;
CREATE TABLE `tb_user_roles` (
  `ua_id` varchar(255) NOT NULL,
  `ua_us_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `ua_ro_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `ua_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ua_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户的角色';

-- ----------------------------
-- Records of tb_user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role_resources`;
CREATE TABLE `tb_user_role_resources` (
  `ur_id` varchar(255) NOT NULL,
  `ur_ro_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `ur_rs_id` varchar(255) DEFAULT NULL COMMENT '资源id',
  `ur_ro_name` varchar(255) DEFAULT NULL COMMENT '角色的名称',
  `ur_rs_name` varchar(255) DEFAULT NULL COMMENT '对应资源的名称',
  `ur_rs_url` varchar(255) DEFAULT NULL COMMENT '资源的url',
  `ur_is_remove` varchar(255) DEFAULT NULL,
  `ur_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ur_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role_resources
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_rs
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_rs`;
CREATE TABLE `tb_user_rs` (
  `urs_id` varchar(255) NOT NULL,
  `urs_us_id` varchar(255) DEFAULT NULL,
  `urs_access` varchar(255) DEFAULT NULL,
  `urs_type` varchar(255) DEFAULT NULL,
  `urs_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `urs_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`urs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中间缓存表';

-- ----------------------------
-- Records of tb_user_rs
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_token`;
CREATE TABLE `tb_user_token` (
  `tk_id` varchar(255) NOT NULL,
  `tk_token_content` varchar(255) NOT NULL COMMENT '令牌',
  `tk_us_id` varchar(255) NOT NULL COMMENT '用户id',
  `tk_expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`tk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_token
-- ----------------------------
INSERT INTO `tb_user_token` VALUES ('2c9081ff5980fdee015980fe20ff0004', 'A1BFCA94305843AEB01EB72D1670C8AB', '2c9081ff5980fdee015980fe20f50002', '2017-02-09 10:10:55');

-- ----------------------------
-- Table structure for tb_user_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_type`;
CREATE TABLE `tb_user_type` (
  `ut_id` varchar(255) NOT NULL,
  `ut_name` varchar(255) NOT NULL COMMENT '用户类型名称',
  `ut_is_remove` int(2) NOT NULL DEFAULT '0',
  `ut_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ut_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ut_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_wallet
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_wallet`;
CREATE TABLE `tb_user_wallet` (
  `wl_id` varchar(255) NOT NULL,
  `wl_balance` decimal(12,2) DEFAULT NULL COMMENT '余额',
  `wl_us_id` varchar(255) DEFAULT NULL COMMENT '关联user id',
  `wl_is_remove` int(2) DEFAULT '0' COMMENT '是否 删除',
  `wl_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `wl_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_wallet
-- ----------------------------
