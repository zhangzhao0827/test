/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ipproxypools

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-27 15:26:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ip_info
-- ----------------------------
DROP TABLE IF EXISTS `t_ip_info`;
CREATE TABLE `t_ip_info` (
  `id` varchar(512) NOT NULL,
  `ip_address` varchar(1024) DEFAULT NULL,
  `ip_port` varchar(10) DEFAULT NULL,
  `http_type` varchar(24) DEFAULT NULL,
  `ip_speed` varchar(24) DEFAULT NULL,
  `server_address` varchar(512) DEFAULT NULL,
  `verification_time` datetime DEFAULT NULL,
  `source_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `proxy_status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ip_source
-- ----------------------------
DROP TABLE IF EXISTS `t_ip_source`;
CREATE TABLE `t_ip_source` (
  `id` varchar(32) NOT NULL,
  `source_name` varchar(128) DEFAULT NULL,
  `source_url` varchar(1024) DEFAULT NULL,
  `max_page` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `page_elements` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ip_source_conf
-- ----------------------------
DROP TABLE IF EXISTS `t_ip_source_conf`;
CREATE TABLE `t_ip_source_conf` (
  `id` varchar(32) NOT NULL,
  `col_name` varchar(64) DEFAULT NULL,
  `col_field` varchar(64) DEFAULT NULL,
  `col_num` int(2) DEFAULT NULL,
  `col_elements` varchar(64) DEFAULT NULL,
  `col_attr` varchar(255) DEFAULT NULL,
  `is_child` int(2) DEFAULT NULL,
  `child_element_key` varchar(128) DEFAULT NULL,
  `child_element_attr` varchar(128) DEFAULT NULL,
  `source_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job` (
  `id` varchar(32) NOT NULL,
  `job_name` varchar(64) DEFAULT NULL,
  `job_group` varchar(54) DEFAULT NULL,
  `class_path` varchar(256) DEFAULT NULL,
  `method_name` varchar(128) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `cron` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_ip_source` VALUES ('299b812e179045d2b1898685261e92e3', '快代理', 'https://www.kuaidaili.com/free/inha/{page}', '2359', '2018-06-20 16:20:49', '.table tbody tr');
INSERT INTO `t_ip_source` VALUES ('82c89dc7ba1841a584062ecc2bd50865', '无忧代理', 'http://www.data5u.com/free/gnpt/index.shtml', '1', '2018-06-22 14:51:46', '.wlist ul .l2');
INSERT INTO `t_ip_source` VALUES ('96f9dfec25f74277ad41bcaafd28858c', '西次', 'http://www.xicidaili.com/nn/{page}', '3229', '2018-06-20 16:21:37', '#ip_list tbody tr');
INSERT INTO `t_ip_source` VALUES ('b62df84bb10f4d718f08046e0b01d17d', '89免费代理', 'http://www.89ip.cn/index_{page}.html', '188', '2018-06-22 11:43:33', '.layui-table tbody tr');

INSERT INTO `t_ip_source_conf` VALUES ('136b9e21d4b847f39624a6b8bd01a256', 'http类型', 'httpType', '5', 'td', null, '0', null, null, '96f9dfec25f74277ad41bcaafd28858c', '2018-06-21 15:01:47');
INSERT INTO `t_ip_source_conf` VALUES ('40dd606c11c24c49b5d0edcb0c11cd0b', 'ip地址', 'ipAddress', '0', 'td', null, '0', null, null, 'b62df84bb10f4d718f08046e0b01d17d', '2018-06-22 11:45:39');
INSERT INTO `t_ip_source_conf` VALUES ('45efdb9c9ea64e91a2ea54b746b481b7', '服务器位置', 'serverAddress', '2', 'td', null, '0', null, null, 'b62df84bb10f4d718f08046e0b01d17d', '2018-06-22 11:45:39');
INSERT INTO `t_ip_source_conf` VALUES ('5e1e03703f9946cd84886f7f0d8ad702', '响应速度', 'ipSpeed', '7', 'span li', null, '0', null, null, '82c89dc7ba1841a584062ecc2bd50865', '2018-06-22 14:58:48');
INSERT INTO `t_ip_source_conf` VALUES ('709466c5f30b4bb29ea304935b6e9f9e', '服务器位置', 'httpType', '3', 'span li', null, '0', null, null, '82c89dc7ba1841a584062ecc2bd50865', '2018-06-22 14:58:48');
INSERT INTO `t_ip_source_conf` VALUES ('7136286b62d941f1b67217e19f064541', 'http类型', 'httpType', '3', 'td', null, '0', null, null, '299b812e179045d2b1898685261e92e3', '2018-06-21 15:03:20');
INSERT INTO `t_ip_source_conf` VALUES ('7a5be7ccbdda44b981ce443508284bd1', '服务器位置', 'serverAddress', '4', 'td', null, '0', null, null, '299b812e179045d2b1898685261e92e3', '2018-06-21 15:03:20');
INSERT INTO `t_ip_source_conf` VALUES ('81e0c4ce18fe41d7a97e821ef0add18f', '服务器位置', 'serverAddress', '5', 'span li', null, '0', null, null, '82c89dc7ba1841a584062ecc2bd50865', '2018-06-22 14:58:48');
INSERT INTO `t_ip_source_conf` VALUES ('893396cf3e6a4be39bdcb9c80aaf74d8', 'ip地址', 'ipAddress', '1', 'td', null, '0', null, null, '96f9dfec25f74277ad41bcaafd28858c', '2018-06-21 15:01:47');
INSERT INTO `t_ip_source_conf` VALUES ('a23aedb96dae4e00abe814d08e91ec4c', 'ip地址', 'ipAddress', '0', 'span li', null, '0', null, null, '82c89dc7ba1841a584062ecc2bd50865', '2018-06-22 14:58:48');
INSERT INTO `t_ip_source_conf` VALUES ('a780149ec53c48b9afc7ef3d5b3e150d', '端口', 'ipPort', '1', 'td', null, '0', null, null, '299b812e179045d2b1898685261e92e3', '2018-06-21 15:03:20');
INSERT INTO `t_ip_source_conf` VALUES ('badbbb708b0f4501929a627f74441b73', '端口', 'ipPort', '1', 'td', null, '0', null, null, 'b62df84bb10f4d718f08046e0b01d17d', '2018-06-22 11:45:39');
INSERT INTO `t_ip_source_conf` VALUES ('c74eb9344599400995ba8d424eb91a62', '服务器位置', 'serverAddress', '3', 'td', null, '0', null, null, '96f9dfec25f74277ad41bcaafd28858c', '2018-06-21 15:01:47');
INSERT INTO `t_ip_source_conf` VALUES ('c9363d9ae860465fb4edbe794d4f9847', 'ip地址', 'ipAddress', '0', 'td', null, '0', null, null, '299b812e179045d2b1898685261e92e3', '2018-06-21 15:03:20');
INSERT INTO `t_ip_source_conf` VALUES ('e6466ed423b6494fb1dbc58e171389bd', '代理访问速度', 'ipSpeed', '5', 'td', null, '0', null, null, '299b812e179045d2b1898685261e92e3', '2018-06-21 15:03:20');
INSERT INTO `t_ip_source_conf` VALUES ('e7ce7f21f61640bca9a4d75bea1feaab', '代理访问速度', 'ipSpeed', '6', 'td', null, '1', 'div[class=bar]', 'title', '96f9dfec25f74277ad41bcaafd28858c', '2018-06-21 15:01:47');
INSERT INTO `t_ip_source_conf` VALUES ('f9af00a463f940eda1e7f9e238013842', '端口', 'ipPort', '1', 'span li', null, '0', null, null, '82c89dc7ba1841a584062ecc2bd50865', '2018-06-22 14:58:48');
INSERT INTO `t_ip_source_conf` VALUES ('fc92e910816142118876510576eb0128', '端口', 'ipPort', '2', 'td', null, '0', null, null, '96f9dfec25f74277ad41bcaafd28858c', '2018-06-21 15:01:47');

INSERT INTO `t_quartz_job` VALUES ('446e824fbb55455e908c424bf90cb48f', '更新添加ip数据', 'group', 'quartzTask', 'addOrUpDateIPInfo', '1', '0 0 0/2 * * ?', '2018-06-26 11:01:46', null);
