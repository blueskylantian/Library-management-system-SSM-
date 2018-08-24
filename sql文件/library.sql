/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-08-24 09:24:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL auto_increment,
  `reader_id` int(11) default NULL,
  `account_name` varchar(16) default NULL,
  `account_password` varchar(16) default NULL,
  `remark` varchar(20) default NULL,
  PRIMARY KEY  (`account_id`),
  KEY `FK_Reference_6` (`reader_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', 'admin', 'a', null);
INSERT INTO `account` VALUES ('2', '2', 'xiangyu', '123', null);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL auto_increment,
  `type_id` int(11) default NULL,
  `book_labels` varchar(256) default NULL,
  `book_num` varchar(20) default NULL,
  `book_name` varchar(20) default NULL,
  `book_author` varchar(20) default NULL,
  `book_publish` varchar(20) default NULL,
  `book_introduction` varchar(256) default NULL,
  `book_price` double(10,4) default NULL,
  `book_amount` int(8) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`book_id`),
  KEY `FK_Reference_2` (`type_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`type_id`) REFERENCES `booktypes` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '3', null, 'sssa', 'java核心技术', 'asd', '123', '123', '20.0000', '9', null);
INSERT INTO `book` VALUES ('3', '1', null, 'aaaa', 'java从入门到入土', 'aaaaa', 'asd', null, '133.0000', '9', null);
INSERT INTO `book` VALUES ('5', '1', null, null, '七天学会java', 'asd', 'aaaaaa', null, '456.0000', '8', null);
INSERT INTO `book` VALUES ('6', '1', null, 'asd', 'Spring', '123 ', '123', null, '123123.0000', '2', '');
INSERT INTO `book` VALUES ('7', '3', null, 'qqqqqq', '母猪的产后护理', 'asd', 'aaaaaa', null, '1.0000', '8', '');
INSERT INTO `book` VALUES ('8', '4', null, 'hhhhh', '比尔盖茨传', '比尔盖茨', '美国', null, '12.5000', '6', '自传');

-- ----------------------------
-- Table structure for booktypes
-- ----------------------------
DROP TABLE IF EXISTS `booktypes`;
CREATE TABLE `booktypes` (
  `type_id` int(11) NOT NULL auto_increment,
  `type_num` varchar(20) default NULL,
  `type_name` varchar(20) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktypes
-- ----------------------------
INSERT INTO `booktypes` VALUES ('1', '1', '计算机', null);
INSERT INTO `booktypes` VALUES ('2', '2', '科技', null);
INSERT INTO `booktypes` VALUES ('3', '3', '人文社科', null);
INSERT INTO `booktypes` VALUES ('4', '4', '传记', null);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL auto_increment,
  `book_id` int(11) default NULL,
  `reader_id` int(11) default NULL,
  `borrow_date` varchar(20) default NULL,
  `borrow_return` varchar(20) default NULL,
  `borrow_num` int(8) default NULL,
  `borrow_type` smallint(6) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`borrow_id`),
  KEY `FK_Reference_4` (`book_id`),
  KEY `FK_Reference_5` (`reader_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('43', '1', '2', '2018-01-22', '2018-03-06', '0', '-1', null);
INSERT INTO `borrow` VALUES ('44', '3', '2', '2018-08-22', '2018-09-06', '0', '1', null);
INSERT INTO `borrow` VALUES ('45', '1', '2', '2018-08-23', '2018-08-07', '0', '-1', null);

-- ----------------------------
-- Table structure for labels
-- ----------------------------
DROP TABLE IF EXISTS `labels`;
CREATE TABLE `labels` (
  `label_id` int(11) NOT NULL auto_increment,
  `label_num` varchar(20) default NULL,
  `label_name` varchar(20) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of labels
-- ----------------------------

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `reader_id` int(11) NOT NULL auto_increment,
  `reader_name` varchar(20) default NULL,
  `reader_type` smallint(6) default NULL,
  `reader_lendnum` int(8) default NULL,
  `reader_credit` smallint(6) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('1', '向向向', '-1', null, null, '系统管理员');
INSERT INTO `reader` VALUES ('2', 'cs', '0', '1', '1', null);

-- ----------------------------
-- Table structure for receipt
-- ----------------------------
DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
  `receipt_id` int(11) NOT NULL auto_increment,
  `borrow_id` int(11) default NULL,
  `reader_id` int(11) default NULL,
  `receipt_money` double(10,4) default NULL,
  `receipt_date` varchar(20) default NULL,
  `receipt_type` smallint(6) default NULL,
  PRIMARY KEY  (`receipt_id`),
  KEY `FK_Reference_7` (`borrow_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`borrow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receipt
-- ----------------------------

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `setting_id` int(11) NOT NULL auto_increment,
  `fine` double(10,4) default NULL,
  `lend_days` int(8) default NULL,
  `lend_num` int(8) default NULL,
  `teacher_num` int(8) default NULL,
  `student_num` int(8) default NULL,
  `remark` varchar(256) default NULL,
  PRIMARY KEY  (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', '0.5000', '15', '3', '3', '5', '默认设置');
INSERT INTO `setting` VALUES ('2', '1.0000', '15', '3', '3', '5', '默认设置');
INSERT INTO `setting` VALUES ('3', '0.5000', '15', '3', '3', '5', '默认设置');
