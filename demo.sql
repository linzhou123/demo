/*
 Navicat Premium Data Transfer

 Source Server         : 10.0.0.143_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 10.0.0.143:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 24/08/2020 19:38:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口名称',
  `project_id` int(11) NOT NULL COMMENT 'project id',
  `api_suite_id` int(11) NOT NULL COMMENT '接口分类 ID',
  `domain` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '域名',
  `request_param_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求入参方式',
  `request_header` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `request_params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'body,post请求参数',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方式',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求路径',
  `request_assert` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求断言',
  `creat_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api
-- ----------------------------
INSERT INTO `api` VALUES (1, 'test', 1, 2, 'http://14.116.192.19:8884', NULL, ' [{\"key\":\"Content-Type\",\"value\":\"application/x-www-form-urlencoded\"}]', '[{\"key\":\"username\",\"value\":\"tpson003\"},{\"key\":\"password\",\"value\":\"d0c305881c28d5a61138b240c7297200\"},{\"key\":\"submit\",\"value\":\"login\"}]', NULL, 'Post', '/self_operator/login', NULL, 1597835237, 1597835237);
INSERT INTO `api` VALUES (3, '登录', 1, 2, 'http://14.116.192.19:8884', NULL, '[{\"key\":\"Content-Type\",\"value\":\"application/x-www-form-urlencoded\"}]', '[{\"key\":\"username\",\"value\":\"tpson003\"},{\"key\":\"password\",\"value\":\"d0c305881c28d5a61138b240c7297200\"},{\"key\":\"submit\",\"value\":\"login\"}]', NULL, 'Post', '/self_operator/login', NULL, 1597835357, 1597835357);

-- ----------------------------
-- Table structure for api_request_result
-- ----------------------------
DROP TABLE IF EXISTS `api_request_result`;
CREATE TABLE `api_request_result`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `api_id` int(10) NOT NULL COMMENT '接口id',
  `api_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口名称',
  `request_header` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `request_params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方式',
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求路径',
  `result_body` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回结果',
  `result_status` int(10) NULL DEFAULT NULL COMMENT '返回状态',
  `result_time` int(10) NULL DEFAULT NULL COMMENT '接口运行时间(ms)',
  `result_isPass` tinyint(4) NULL DEFAULT NULL COMMENT '判断运行接口是否报错',
  `creat_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口测试结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_request_result
-- ----------------------------
INSERT INTO `api_request_result` VALUES (4, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', NULL, 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-49bc1941db6e4db32bda90427f1928d9\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 2733, 0, 1597918730, 1597918730);
INSERT INTO `api_request_result` VALUES (5, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', NULL, 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-ba25d2e3989fd59e85aa64e46b558c09\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 1190, 0, 1597921625, 1597921625);
INSERT INTO `api_request_result` VALUES (6, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', '[{\"value\":\"tpson003\",\"key\":\"username\"},{\"value\":\"d0c305881c28d5a61138b240c7297200\",\"key\":\"password\"},{\"value\":\"login\",\"key\":\"submit\"}]', 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-4c3a14f054b368538ebee96b0bb6e974\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 1442, 0, 1597969976, 1597969976);
INSERT INTO `api_request_result` VALUES (7, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', '[{\"value\":\"tpson003\",\"key\":\"username\"},{\"value\":\"d0c305881c28d5a61138b240c7297200\",\"key\":\"password\"},{\"value\":\"login\",\"key\":\"submit\"}]', 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-ae23f7bc45c8ed8d9576f0e2741979bb\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 126, 0, 1597970141, 1597970141);
INSERT INTO `api_request_result` VALUES (8, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', '[{\"value\":\"tpson003\",\"key\":\"username\"},{\"value\":\"d0c305881c28d5a61138b240c7297200\",\"key\":\"password\"},{\"value\":\"login\",\"key\":\"submit\"}]', 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-2a276a00e5d811dd6ca86db9b0e4b068\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 1432, 0, 1597971461, 1597971461);
INSERT INTO `api_request_result` VALUES (9, 3, '登录', '[{\"value\":\"application/x-www-form-urlencoded\",\"key\":\"Content-Type\"}]', '[{\"value\":\"tpson003\",\"key\":\"username\"},{\"value\":\"d0c305881c28d5a61138b240c7297200\",\"key\":\"password\"},{\"value\":\"login\",\"key\":\"submit\"}]', 'Post', 'http://14.116.192.19:8884/self_operator/login', '{\n    \"code\": 0,\n    \"data\": {\n        \"user\": {\n            \"departmentId\": 0,\n            \"departmentName\": \"\",\n            \"enterpriseId\": 4,\n            \"enterpriseName\": \"自运营企业\",\n            \"isPrimary\": true,\n            \"logoPhotoId\": \"df3f3fe4997d860fc24397adf512e919\",\n            \"modules\": \"2000,2001,2002,2003,2010,2013,2012,2011,2014,2015,2020,2021,2022,2023,2030,2031,2032,2033,2034,2035,2036,2040,2041,2042,2043,2044,2045,2046,2047,2050,2060,2061,2062,2063,2064\",\n            \"mqttInfo\": {\n                \"clientId\": \"web-b15793bddd437cfda92b2fca2cf22163\",\n                \"heartbeat\": 10,\n                \"password\": \"tpson102304\",\n                \"protocolVersion\": \"MQTT3.1.1\",\n                \"server\": \"ws://14.116.192.19:25671/ws\",\n                \"username\": \"tpson\"\n            },\n            \"name\": \"tpson003\",\n            \"phone\": \"15736740078\",\n            \"posX\": \"120.17017364501955\",\n            \"posY\": \"30.24027637293337\",\n            \"userId\": 434069036964249600,\n            \"userPhotoUrl\": \"19efb150461686d4d755216705da54dc\",\n            \"userType\": 5\n        }\n    },\n    \"resultCode\": \"SUCCESS\",\n    \"success\": true\n}', 200, 104, 0, 1597971478, 1597971478);

-- ----------------------------
-- Table structure for api_suite
-- ----------------------------
DROP TABLE IF EXISTS `api_suite`;
CREATE TABLE `api_suite`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口分类名称',
  `project_id` int(11) NOT NULL COMMENT 'project id',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `creat_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_suite
-- ----------------------------
INSERT INTO `api_suite` VALUES (2, '测试项目', 1, '测试备注', 1596887050, 1597752887);

-- ----------------------------
-- Table structure for mistake
-- ----------------------------
DROP TABLE IF EXISTS `mistake`;
CREATE TABLE `mistake`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '受到伤害人员',
  `type` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '伤害类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '伤害类型',
  `mistake_level` int(1) NULL DEFAULT NULL COMMENT '伤害等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mistake
-- ----------------------------

-- ----------------------------
-- Table structure for mistaketype
-- ----------------------------
DROP TABLE IF EXISTS `mistaketype`;
CREATE TABLE `mistaketype`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `mistake_type` int(1) NOT NULL,
  `mistake_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mistaketype
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '测试项目', '测试项目描述1', 1596887050, 1596887050);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `creat_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lz测试', 'lztest', '123456', '2020-07-23 22:44:08', '2020-07-23 22:44:08');

SET FOREIGN_KEY_CHECKS = 1;
