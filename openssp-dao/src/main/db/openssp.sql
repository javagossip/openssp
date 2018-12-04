/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : openssp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-04 21:38:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_position
-- ----------------------------
DROP TABLE IF EXISTS `ad_position`;
CREATE TABLE `ad_position` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告位模板id',
  `name` varchar(128) DEFAULT NULL COMMENT '模板名称',
  `type` int(11) unsigned DEFAULT NULL COMMENT '模板类型，1-图片; 2-视频，3-信息流',
  `spec` varchar(4096) DEFAULT NULL COMMENT '模板定义JSON',
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告位模板';

-- ----------------------------
-- Records of ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'app名称',
  `description` varchar(255) DEFAULT NULL COMMENT '应用描述',
  `keywords` varchar(255) DEFAULT NULL COMMENT '应用关键字，多个字段用逗号分隔',
  `media_id` int(10) unsigned DEFAULT NULL COMMENT '媒体id',
  `app_id` varchar(64) DEFAULT NULL COMMENT 'ios/安卓市场app_id/非第三方app的app_id',
  `media_app_id` varchar(64) DEFAULT NULL COMMENT '媒体端app_id',
  `pkg_name` varchar(128) DEFAULT NULL COMMENT '安卓包名/ios bundle_id',
  `download_url` varchar(255) DEFAULT NULL COMMENT 'app下载地址',
  `paid` int(11) DEFAULT NULL COMMENT '付费类型,0-免费; 1-付费',
  `category` int(10) unsigned DEFAULT NULL COMMENT 'app分类id',
  `os` int(11) DEFAULT NULL COMMENT '操作系统',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(10) unsigned DEFAULT '1' COMMENT 'app状态',
  `ext` varchar(1024) DEFAULT NULL COMMENT '扩展字段,JSON表示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ios/android应用';

-- ----------------------------
-- Records of app
-- ----------------------------

-- ----------------------------
-- Table structure for app_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `app_ad_position`;
CREATE TABLE `app_ad_position` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ad_position_id` int(11) unsigned DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app和广告位关联表';

-- ----------------------------
-- Records of app_ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for media
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(32) DEFAULT NULL COMMENT '媒体uuid,由平台生成',
  `publisher_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '媒体名称',
  `type` int(11) unsigned DEFAULT NULL COMMENT '媒体类型, 1-app; 2-site; 3-adx/ssp',
  `status` int(10) unsigned DEFAULT NULL COMMENT '媒体状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体/流量源, 类型包括独立的app,流量聚合平台,站点';

-- ----------------------------
-- Records of media
-- ----------------------------

-- ----------------------------
-- Table structure for media_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `media_ad_position`;
CREATE TABLE `media_ad_position` (
  `id` int(11) NOT NULL,
  `media_id` int(10) unsigned DEFAULT NULL COMMENT '媒体id',
  `ad_position_id` int(11) DEFAULT NULL COMMENT '广告位id',
  `status` int(10) unsigned DEFAULT NULL COMMENT '媒体广告位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体广告位';

-- ----------------------------
-- Records of media_ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for media_floor
-- ----------------------------
DROP TABLE IF EXISTS `media_floor`;
CREATE TABLE `media_floor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `media_id` int(10) unsigned DEFAULT NULL COMMENT '媒体id',
  `ad_position_id` int(11) unsigned DEFAULT NULL COMMENT '广告位id',
  `traffic_attrs` varchar(1024) DEFAULT NULL COMMENT '底价依赖流量属性配置',
  `floor` int(11) DEFAULT NULL COMMENT '底价,单位是分',
  `status` int(11) DEFAULT NULL COMMENT '底价状态,0-无效;1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体刊例价配置';

-- ----------------------------
-- Records of media_floor
-- ----------------------------

-- ----------------------------
-- Table structure for media_floor_traffic_attr
-- ----------------------------
DROP TABLE IF EXISTS `media_floor_traffic_attr`;
CREATE TABLE `media_floor_traffic_attr` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id,主键',
  `media_id` int(11) unsigned DEFAULT NULL COMMENT '媒体id',
  `traffic_attr_id` int(11) unsigned DEFAULT NULL COMMENT '媒体流量属性id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体底价配置流量属性配置';

-- ----------------------------
-- Records of media_floor_traffic_attr
-- ----------------------------

-- ----------------------------
-- Table structure for media_traffic_attr
-- ----------------------------
DROP TABLE IF EXISTS `media_traffic_attr`;
CREATE TABLE `media_traffic_attr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `media_id` int(10) unsigned NOT NULL,
  `traffic_attr_id` int(10) unsigned NOT NULL,
  `enum_values` varchar(1024) DEFAULT NULL COMMENT '属性取值列表定义',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体流量属性定义';

-- ----------------------------
-- Records of media_traffic_attr
-- ----------------------------

-- ----------------------------
-- Table structure for publisher
-- ----------------------------
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '开发者名称',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) DEFAULT NULL COMMENT '开发者登陆密码',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件地址',
  `qq` varchar(32) DEFAULT NULL,
  `telephone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `bank_account` varchar(20) DEFAULT NULL COMMENT '银行账号',
  `bank_name` varchar(255) DEFAULT NULL COMMENT '开户行',
  `account_name` varchar(64) DEFAULT NULL COMMENT '开户名',
  `currency` int(11) unsigned DEFAULT '0' COMMENT '结算货币类型, 1-RMB, 2-USD',
  `status` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '开发者状态，0-无效; 1-有效;',
  `audit_status` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '审核状态，0-待审核；1-审核通过; 2-审核拒绝',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开发者/站长';

-- ----------------------------
-- Records of publisher
-- ----------------------------

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `domain` varchar(64) DEFAULT NULL COMMENT '站点域名',
  `name` varchar(64) DEFAULT NULL COMMENT '站点名称',
  `category` int(11) DEFAULT NULL COMMENT '站点分类',
  `description` varchar(255) DEFAULT NULL COMMENT '站点描述',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字列表',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `ext` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '站点状态,0-无效;1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site
-- ----------------------------

-- ----------------------------
-- Table structure for site_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `site_ad_position`;
CREATE TABLE `site_ad_position` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `site_id` int(11) unsigned DEFAULT NULL COMMENT '站点id',
  `ad_position_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_ad_position
-- ----------------------------

-- ----------------------------
-- Table structure for traffic_attr
-- ----------------------------
DROP TABLE IF EXISTS `traffic_attr`;
CREATE TABLE `traffic_attr` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '属性中文名',
  `en_name` varchar(64) DEFAULT NULL COMMENT '属性英文名',
  `component_type` int(11) unsigned DEFAULT NULL COMMENT '组件类型',
  `data_type` int(11) unsigned DEFAULT NULL COMMENT '数据类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流量属性定义';

-- ----------------------------
-- Records of traffic_attr
-- ----------------------------
