/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : system_admin

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 15/07/2019 10:15:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fw_config
-- ----------------------------
DROP TABLE IF EXISTS `fw_config`;
CREATE TABLE `fw_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_config
-- ----------------------------
INSERT INTO `fw_config` VALUES (16, 'main', 'http://www.baidu.com', '123', '2019-06-19 13:29:50', '2019-07-04 18:08:16', '');
INSERT INTO `fw_config` VALUES (17, 'img_prefix', 'http://localhost:8888/image/', '图片前缀', '2019-07-03 10:31:21', NULL, '');
INSERT INTO `fw_config` VALUES (18, 'default_avatar', '1562136571644.jpg', '默认头像', '2019-07-03 14:51:43', '2019-07-04 18:08:10', '');

-- ----------------------------
-- Table structure for fw_dic
-- ----------------------------
DROP TABLE IF EXISTS `fw_dic`;
CREATE TABLE `fw_dic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标识',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典标题',
  `type` int(255) NULL DEFAULT NULL COMMENT '类型，目前只支持键值对',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_dic
-- ----------------------------
INSERT INTO `fw_dic` VALUES (1, 'IMAGE_STATUS', '图片类型管理', 1, '1:其他图片,2:商品图片,3:头像图片,4:富文本图片', '777', '2019-06-28 22:02:40', '2019-07-12 16:53:58', '');

-- ----------------------------
-- Table structure for fw_image
-- ----------------------------
DROP TABLE IF EXISTS `fw_image`;
CREATE TABLE `fw_image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `local_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本地url',
  `cloud_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '云端url',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` varbinary(255) NULL DEFAULT NULL COMMENT '类型 字典标识：IMAGE_STATUS',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fw_image_type_id`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_image
-- ----------------------------
INSERT INTO `fw_image` VALUES (89, '1561353717011.png', NULL, NULL, 0x32, '2019-06-24 13:21:57', NULL, '');
INSERT INTO `fw_image` VALUES (90, '1561353717011.jpg', NULL, NULL, 0x32, '2019-06-24 13:21:57', NULL, '');
INSERT INTO `fw_image` VALUES (91, '1561353717036.jpg', NULL, NULL, 0x32, '2019-06-24 13:21:57', NULL, '');
INSERT INTO `fw_image` VALUES (92, '1561353717011.jpg', NULL, NULL, 0x32, '2019-06-24 13:21:57', NULL, '');
INSERT INTO `fw_image` VALUES (93, '1561353726332.png', NULL, NULL, 0x32, '2019-06-24 13:22:06', NULL, '');
INSERT INTO `fw_image` VALUES (94, '1561353726362.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:06', NULL, '');
INSERT INTO `fw_image` VALUES (95, '1561353726350.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:06', NULL, '');
INSERT INTO `fw_image` VALUES (96, '1561353726388.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:06', NULL, '');
INSERT INTO `fw_image` VALUES (97, '1561353733904.png', NULL, NULL, 0x32, '2019-06-24 13:22:14', NULL, '');
INSERT INTO `fw_image` VALUES (98, '1561353733920.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:14', NULL, '');
INSERT INTO `fw_image` VALUES (99, '1561353733957.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:14', NULL, '');
INSERT INTO `fw_image` VALUES (100, '1561353733921.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:14', NULL, '');
INSERT INTO `fw_image` VALUES (101, '1561353740066.png', NULL, NULL, 0x32, '2019-06-24 13:22:20', NULL, '');
INSERT INTO `fw_image` VALUES (102, '1561353740099.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:20', NULL, '');
INSERT INTO `fw_image` VALUES (103, '1561353740100.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:20', NULL, '');
INSERT INTO `fw_image` VALUES (104, '1561353740100.jpg', NULL, NULL, 0x32, '2019-06-24 13:22:20', NULL, '');
INSERT INTO `fw_image` VALUES (105, '1561353777140.jpg', NULL, NULL, 0x33, '2019-06-24 13:22:57', NULL, '');
INSERT INTO `fw_image` VALUES (106, '1561353777153.jpg', NULL, NULL, 0x33, '2019-06-24 13:22:57', NULL, '');
INSERT INTO `fw_image` VALUES (107, '1561353777123.png', NULL, NULL, 0x33, '2019-06-24 13:22:57', NULL, '');
INSERT INTO `fw_image` VALUES (108, '1561353777134.jpg', NULL, NULL, 0x33, '2019-06-24 13:22:57', NULL, '');
INSERT INTO `fw_image` VALUES (109, '1562083155012.jpg', NULL, NULL, 0x32, '2019-07-02 23:59:15', NULL, '');
INSERT INTO `fw_image` VALUES (110, '1562083685093.jpg', NULL, NULL, 0x33, '2019-07-03 00:08:05', NULL, '');
INSERT INTO `fw_image` VALUES (111, '1562083765446.jpg', NULL, NULL, 0x33, '2019-07-03 00:09:25', NULL, '');
INSERT INTO `fw_image` VALUES (114, '1562084181981.jpg', NULL, NULL, 0x31, '2019-07-03 00:16:22', NULL, '');
INSERT INTO `fw_image` VALUES (115, '1562084347538.jpg', NULL, NULL, 0x31, '2019-07-03 00:19:07', NULL, '');
INSERT INTO `fw_image` VALUES (116, '1562084362048.png', NULL, NULL, 0x33, '2019-07-03 00:19:22', NULL, '');
INSERT INTO `fw_image` VALUES (117, '1562084378998.png', NULL, NULL, 0x33, '2019-07-03 00:19:39', NULL, '');
INSERT INTO `fw_image` VALUES (118, '1562084378998.jpg', NULL, NULL, 0x33, '2019-07-03 00:19:39', NULL, '');
INSERT INTO `fw_image` VALUES (122, '1562085440422.png', NULL, NULL, 0x33, '2019-07-03 00:37:20', NULL, '');
INSERT INTO `fw_image` VALUES (123, '1562085547809.jpg', NULL, NULL, 0x33, '2019-07-03 00:39:08', NULL, '');
INSERT INTO `fw_image` VALUES (124, '1562130186263.jpg', NULL, NULL, 0x33, '2019-07-03 13:03:06', NULL, '');
INSERT INTO `fw_image` VALUES (125, '1562130463556.png', NULL, NULL, 0x33, '2019-07-03 13:07:43', NULL, '');
INSERT INTO `fw_image` VALUES (126, '1562130473959.jpg', NULL, NULL, 0x33, '2019-07-03 13:07:54', NULL, '');
INSERT INTO `fw_image` VALUES (127, '1562487513421.jpg', NULL, NULL, 0x34, '2019-07-07 16:18:33', NULL, '');
INSERT INTO `fw_image` VALUES (128, '1562487722866.jpg', NULL, NULL, 0x34, '2019-07-07 16:22:03', NULL, '');
INSERT INTO `fw_image` VALUES (129, '1562487802453.jpg', NULL, NULL, 0x34, '2019-07-07 16:23:22', NULL, '');
INSERT INTO `fw_image` VALUES (130, '1562488882962.jpg', NULL, NULL, 0x34, '2019-07-07 16:41:23', NULL, '');
INSERT INTO `fw_image` VALUES (131, '1562488969532.jpg', NULL, NULL, 0x34, '2019-07-07 16:42:49', NULL, '');
INSERT INTO `fw_image` VALUES (132, '1562489064831.png', NULL, NULL, 0x34, '2019-07-07 16:44:25', NULL, '');
INSERT INTO `fw_image` VALUES (133, '1562825636773.jpg', NULL, NULL, 0x34, '2019-07-11 14:13:57', NULL, '');
INSERT INTO `fw_image` VALUES (134, '1563156092403.jpg', NULL, NULL, 0x33, '2019-07-15 10:01:32', NULL, '');

-- ----------------------------
-- Table structure for fw_notice
-- ----------------------------
DROP TABLE IF EXISTS `fw_notice`;
CREATE TABLE `fw_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时间',
  `status` int(255) NULL DEFAULT NULL COMMENT '状态 0关闭 1开启',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_notice
-- ----------------------------
INSERT INTO `fw_notice` VALUES (4, '测试', '<h1><font color=\"#8baa4a\" style=\"font-weight: bold;\">测试公告测试公告</font></h1><p><span style=\"background-color: rgb(249, 150, 59);\">测试公告测试公告&nbsp;</span></p><p>测试公告测试公告&nbsp;<br></p><p><img src=\"http://localhost:8888/image/1562825636773.jpg\" style=\"max-width:100%;\"><br></p>', '2019-07-07 00:00:00', 1, '2019-07-07 16:40:28', '2019-07-11 14:13:59', '');

-- ----------------------------
-- Table structure for fw_permission
-- ----------------------------
DROP TABLE IF EXISTS `fw_permission`;
CREATE TABLE `fw_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限url',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `perms` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `is_show` int(11) NULL DEFAULT NULL COMMENT '是否显0显示1不显示',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '打开方式',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_by` bigint(255) NULL DEFAULT NULL COMMENT '创建者id',
  `base_create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_permission
-- ----------------------------
INSERT INTO `fw_permission` VALUES (1, '系统管理', '', 0, NULL, 3, 0, NULL, NULL, '', NULL, NULL, '2019-07-05 17:17:10', NULL);
INSERT INTO `fw_permission` VALUES (2, '用户管理', '/admin/system/user/list', 1, NULL, 10, 1, NULL, NULL, '', NULL, NULL, '2019-06-19 09:40:28', NULL);
INSERT INTO `fw_permission` VALUES (3, '角色管理', '/admin/system/role/list', 1, NULL, 9, 1, NULL, NULL, '', NULL, NULL, '2019-06-19 09:40:35', NULL);
INSERT INTO `fw_permission` VALUES (4, '权限管理', '/admin/system/menu/list', 1, NULL, 8, 1, NULL, NULL, '', NULL, NULL, '2019-06-19 09:40:41', NULL);
INSERT INTO `fw_permission` VALUES (5, '开发者工具', '', 0, NULL, 1, 0, NULL, NULL, '', NULL, '2019-05-31 12:26:03', '2019-06-23 07:58:55', '');
INSERT INTO `fw_permission` VALUES (6, '生成工具', '/admin/system/generator/list', 1, NULL, 5, 5, NULL, NULL, '', NULL, '2019-05-31 12:32:16', '2019-07-05 17:18:33', '');
INSERT INTO `fw_permission` VALUES (8, '列表查看', '/admin/system/generator/list', 2, NULL, 2121, 6, NULL, NULL, '', NULL, '2019-06-03 12:14:28', '2019-06-20 13:23:18', '');
INSERT INTO `fw_permission` VALUES (9, '添加', '/admin/system/user/add', 2, NULL, 3, 2, NULL, NULL, '', NULL, '2019-06-11 17:29:54', NULL, '');
INSERT INTO `fw_permission` VALUES (10, '查看', '/admin/system/user/list', 2, NULL, 20, 2, NULL, NULL, '', NULL, '2019-06-12 11:37:04', NULL, '');
INSERT INTO `fw_permission` VALUES (11, '查看', '/admin/system/role/list', 2, NULL, 20, 3, NULL, NULL, '', NULL, '2019-06-12 11:37:16', NULL, '');
INSERT INTO `fw_permission` VALUES (12, '查看', '/admin/system/menu/list', 2, NULL, 20, 4, NULL, NULL, '', NULL, '2019-06-12 11:37:47', NULL, '');
INSERT INTO `fw_permission` VALUES (13, '修改', '/admin/system/user/edit', 2, NULL, 5, 2, NULL, NULL, '', NULL, '2019-06-12 12:40:28', '2019-07-05 12:35:58', '');
INSERT INTO `fw_permission` VALUES (16, '系统日志', '/admin/system/syslog/list', 1, NULL, 5, 1, NULL, NULL, '', NULL, '2019-06-17 16:15:26', '2019-06-19 09:41:07', '');
INSERT INTO `fw_permission` VALUES (17, '查看列表', '/admin/system/syslog/list', 2, NULL, 4, 16, NULL, NULL, '', NULL, '2019-06-17 16:15:51', NULL, '');
INSERT INTO `fw_permission` VALUES (18, '参数配置', '/admin/system/config/list', 1, NULL, 6, 1, NULL, NULL, '', NULL, '2019-06-19 09:39:32', '2019-07-05 16:17:06', '');
INSERT INTO `fw_permission` VALUES (19, '查看列表', '/admin/system/config/list', 2, NULL, 10, 18, NULL, NULL, '', NULL, '2019-06-19 09:39:50', NULL, '');
INSERT INTO `fw_permission` VALUES (20, ' 辅助功能', '', 0, NULL, 2, 0, NULL, NULL, '', NULL, '2019-06-22 22:08:34', NULL, '');
INSERT INTO `fw_permission` VALUES (23, '图库管理', '/admin/system/fwImage/list', 1, NULL, 5, 20, NULL, NULL, '', NULL, '2019-06-23 08:25:44', NULL, '');
INSERT INTO `fw_permission` VALUES (24, '列表管理', '/admin/system/fwImage/list', 2, NULL, 5, 23, NULL, NULL, '', NULL, '2019-06-23 08:26:06', NULL, '');
INSERT INTO `fw_permission` VALUES (25, '定时任务', '/admin/system/fwScheduleJob/list', 1, NULL, 4, 20, NULL, NULL, '', NULL, '2019-06-26 21:21:18', '2019-06-28 17:27:35', '');
INSERT INTO `fw_permission` VALUES (26, '列表查询', '/admin/system/fwScheduleJob/list', 2, NULL, 10, 25, NULL, NULL, '', NULL, '2019-06-26 21:21:40', NULL, '');
INSERT INTO `fw_permission` VALUES (27, '数据字典', '/admin/system/fwDic/list', 1, NULL, 3, 20, NULL, NULL, '', NULL, '2019-06-28 21:44:32', NULL, '');
INSERT INTO `fw_permission` VALUES (28, '列表', '/admin/system/fwDic/list', 2, NULL, 10, 27, NULL, NULL, '', NULL, '2019-06-28 21:44:53', '2019-07-05 13:49:46', '');
INSERT INTO `fw_permission` VALUES (29, '公告管理', '/admin/system/notice/list', 1, NULL, 6, 20, NULL, NULL, '', NULL, '2019-06-29 08:19:40', NULL, '');
INSERT INTO `fw_permission` VALUES (30, '列表查询', '/admin/system/notice/list', 2, NULL, 5, 29, NULL, NULL, '', NULL, '2019-06-29 08:20:07', NULL, '');
INSERT INTO `fw_permission` VALUES (32, '删除', '/admin/system/user/del', 2, NULL, 9, 2, NULL, NULL, '', NULL, '2019-07-05 13:11:15', NULL, '');
INSERT INTO `fw_permission` VALUES (33, '分配角色', '/admin/system/user/authorize', 2, NULL, 5, 2, NULL, NULL, '', NULL, '2019-07-05 13:11:44', NULL, '');
INSERT INTO `fw_permission` VALUES (34, '添加', '/admin/system/role/add', 2, NULL, 19, 3, NULL, NULL, '', NULL, '2019-07-05 13:25:15', NULL, '');
INSERT INTO `fw_permission` VALUES (35, '删除', '/admin/system/role/del', 2, NULL, 18, 3, NULL, NULL, '', NULL, '2019-07-05 13:27:57', NULL, '');
INSERT INTO `fw_permission` VALUES (36, '授权', '/admin/system/role/authPermission', 2, NULL, 17, 3, NULL, NULL, '', NULL, '2019-07-05 13:28:13', NULL, '');
INSERT INTO `fw_permission` VALUES (37, '修改', '/admin/system/role/edit', 2, NULL, 17, 3, NULL, NULL, '', NULL, '2019-07-05 13:28:28', NULL, '');
INSERT INTO `fw_permission` VALUES (38, '添加', '/admin/system/config/add', 2, NULL, 9, 18, NULL, NULL, '', NULL, '2019-07-05 13:35:36', NULL, '');
INSERT INTO `fw_permission` VALUES (39, '编辑', '/admin/system/config/edit', 2, NULL, 8, 18, NULL, NULL, '', NULL, '2019-07-05 13:35:54', NULL, '');
INSERT INTO `fw_permission` VALUES (40, '删除', '/admin/system/config/del', 2, NULL, 7, 18, NULL, NULL, '', NULL, '2019-07-05 13:36:05', NULL, '');
INSERT INTO `fw_permission` VALUES (41, '添加', '/admin/system/notice/add', 2, NULL, 9, 29, NULL, NULL, '', NULL, '2019-07-05 13:38:14', NULL, '');
INSERT INTO `fw_permission` VALUES (42, '编辑', '/admin/system/notice/edit', 2, NULL, 8, 29, NULL, NULL, '', NULL, '2019-07-05 13:38:29', NULL, '');
INSERT INTO `fw_permission` VALUES (43, '删除', '/admin/system/notice/del', 2, NULL, 7, 29, NULL, NULL, '', NULL, '2019-07-05 13:38:39', NULL, '');
INSERT INTO `fw_permission` VALUES (44, '上传', '/admin/system/fwImage/add', 2, NULL, 9, 23, NULL, NULL, '', NULL, '2019-07-05 13:41:00', NULL, '');
INSERT INTO `fw_permission` VALUES (45, '删除', '/admin/system/fwImage/del', 2, NULL, 8, 23, NULL, NULL, '', NULL, '2019-07-05 13:41:45', NULL, '');
INSERT INTO `fw_permission` VALUES (46, '添加', '/admin/system/fwScheduleJob/add', 2, NULL, 9, 25, NULL, NULL, '', NULL, '2019-07-05 13:43:49', NULL, '');
INSERT INTO `fw_permission` VALUES (47, '修改', '/admin/system/fwScheduleJob/edit', 2, NULL, 8, 25, NULL, NULL, '', NULL, '2019-07-05 13:44:02', NULL, '');
INSERT INTO `fw_permission` VALUES (48, '删除', '/admin/system/fwScheduleJob/del', 2, NULL, 7, 25, NULL, NULL, '', NULL, '2019-07-05 13:44:45', NULL, '');
INSERT INTO `fw_permission` VALUES (49, '批量暂停', '/admin/system/fwScheduleJob/pause', 2, NULL, 6, 25, NULL, NULL, '', NULL, '2019-07-05 13:45:58', NULL, '');
INSERT INTO `fw_permission` VALUES (50, '批量恢复', '/admin/system/fwScheduleJob/resume', 2, NULL, 5, 25, NULL, NULL, '', NULL, '2019-07-05 13:46:58', NULL, '');
INSERT INTO `fw_permission` VALUES (51, '立即执行', '/admin/system/fwScheduleJob/run', 2, NULL, 4, 25, NULL, NULL, '', NULL, '2019-07-05 13:47:28', NULL, '');
INSERT INTO `fw_permission` VALUES (52, '任务日志', '/admin/system/fwScheduleJobLog/list', 2, NULL, 3, 25, NULL, NULL, '', NULL, '2019-07-05 13:48:05', NULL, '');
INSERT INTO `fw_permission` VALUES (53, '添加', '/admin/system/fwDic/add', 2, NULL, 9, 27, NULL, NULL, '', NULL, '2019-07-05 13:49:05', NULL, '');
INSERT INTO `fw_permission` VALUES (54, '编辑', '/admin/system/fwDic/edit', 2, NULL, 8, 27, NULL, NULL, '', NULL, '2019-07-05 13:49:17', NULL, '');
INSERT INTO `fw_permission` VALUES (55, '删除', '/admin/system/fwDic/del', 2, NULL, 7, 27, NULL, NULL, '', NULL, '2019-07-05 13:49:28', NULL, '');
INSERT INTO `fw_permission` VALUES (56, '立即生成', '/admin/system/generator/code', 2, NULL, 5, 6, NULL, NULL, '', NULL, '2019-07-05 13:51:09', NULL, '');
INSERT INTO `fw_permission` VALUES (57, '添加目录', '/admin/system/menu/add', 2, NULL, 9, 4, NULL, NULL, '', NULL, '2019-07-05 13:55:28', NULL, '');
INSERT INTO `fw_permission` VALUES (58, '添加子菜单', '/admin/system/menu/addChild', 2, NULL, 6, 4, NULL, NULL, '', NULL, '2019-07-05 13:56:29', NULL, '');
INSERT INTO `fw_permission` VALUES (59, '编辑', '/admin/system/menu/edit', 2, NULL, 4, 4, NULL, NULL, '', NULL, '2019-07-05 14:01:47', NULL, '');
INSERT INTO `fw_permission` VALUES (60, '删除', '/admin/system/menu/del', 2, NULL, 2, 4, NULL, NULL, '', NULL, '2019-07-05 14:01:59', NULL, '');
INSERT INTO `fw_permission` VALUES (61, '公告详情', '/admin/system/notice/detail', 2, NULL, 5, 29, NULL, NULL, '', NULL, '2019-07-12 14:17:47', NULL, '');

-- ----------------------------
-- Table structure for fw_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `fw_permission_role`;
CREATE TABLE `fw_permission_role`  (
  `role_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  INDEX `fk_fw_permission_role_fw_permission_role_2`(`permission_id`) USING BTREE,
  INDEX `fk_fw__role_1`(`role_id`) USING BTREE,
  CONSTRAINT `fk_fw__role_1` FOREIGN KEY (`role_id`) REFERENCES `fw_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_fw_permission_role_fw_permission_role_2` FOREIGN KEY (`permission_id`) REFERENCES `fw_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限角色中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_permission_role
-- ----------------------------
INSERT INTO `fw_permission_role` VALUES (1, 1);
INSERT INTO `fw_permission_role` VALUES (1, 2);
INSERT INTO `fw_permission_role` VALUES (1, 3);
INSERT INTO `fw_permission_role` VALUES (1, 4);
INSERT INTO `fw_permission_role` VALUES (1, 5);
INSERT INTO `fw_permission_role` VALUES (1, 8);
INSERT INTO `fw_permission_role` VALUES (1, 9);
INSERT INTO `fw_permission_role` VALUES (1, 10);
INSERT INTO `fw_permission_role` VALUES (1, 11);
INSERT INTO `fw_permission_role` VALUES (1, 12);
INSERT INTO `fw_permission_role` VALUES (1, 13);
INSERT INTO `fw_permission_role` VALUES (1, 16);
INSERT INTO `fw_permission_role` VALUES (1, 17);
INSERT INTO `fw_permission_role` VALUES (1, 18);
INSERT INTO `fw_permission_role` VALUES (1, 19);
INSERT INTO `fw_permission_role` VALUES (1, 20);
INSERT INTO `fw_permission_role` VALUES (1, 23);
INSERT INTO `fw_permission_role` VALUES (1, 24);
INSERT INTO `fw_permission_role` VALUES (1, 25);
INSERT INTO `fw_permission_role` VALUES (1, 26);
INSERT INTO `fw_permission_role` VALUES (1, 27);
INSERT INTO `fw_permission_role` VALUES (1, 28);
INSERT INTO `fw_permission_role` VALUES (1, 29);
INSERT INTO `fw_permission_role` VALUES (1, 30);
INSERT INTO `fw_permission_role` VALUES (1, 32);
INSERT INTO `fw_permission_role` VALUES (1, 33);
INSERT INTO `fw_permission_role` VALUES (1, 34);
INSERT INTO `fw_permission_role` VALUES (1, 35);
INSERT INTO `fw_permission_role` VALUES (1, 36);
INSERT INTO `fw_permission_role` VALUES (1, 37);
INSERT INTO `fw_permission_role` VALUES (1, 38);
INSERT INTO `fw_permission_role` VALUES (1, 39);
INSERT INTO `fw_permission_role` VALUES (1, 40);
INSERT INTO `fw_permission_role` VALUES (1, 41);
INSERT INTO `fw_permission_role` VALUES (1, 42);
INSERT INTO `fw_permission_role` VALUES (1, 43);
INSERT INTO `fw_permission_role` VALUES (1, 44);
INSERT INTO `fw_permission_role` VALUES (1, 45);
INSERT INTO `fw_permission_role` VALUES (1, 46);
INSERT INTO `fw_permission_role` VALUES (1, 47);
INSERT INTO `fw_permission_role` VALUES (1, 48);
INSERT INTO `fw_permission_role` VALUES (1, 49);
INSERT INTO `fw_permission_role` VALUES (1, 50);
INSERT INTO `fw_permission_role` VALUES (1, 51);
INSERT INTO `fw_permission_role` VALUES (1, 52);
INSERT INTO `fw_permission_role` VALUES (1, 53);
INSERT INTO `fw_permission_role` VALUES (1, 54);
INSERT INTO `fw_permission_role` VALUES (1, 55);
INSERT INTO `fw_permission_role` VALUES (1, 56);
INSERT INTO `fw_permission_role` VALUES (1, 57);
INSERT INTO `fw_permission_role` VALUES (1, 58);
INSERT INTO `fw_permission_role` VALUES (1, 59);
INSERT INTO `fw_permission_role` VALUES (1, 60);
INSERT INTO `fw_permission_role` VALUES (1, 61);

-- ----------------------------
-- Table structure for fw_role
-- ----------------------------
DROP TABLE IF EXISTS `fw_role`;
CREATE TABLE `fw_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建id',
  `parent_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父id集合多个之前[1],[2],[3]隔开',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_code`(`role_code`) USING BTREE COMMENT ' 唯一',
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_role
-- ----------------------------
INSERT INTO `fw_role` VALUES (1, '管理员', '管理员', 'admin', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for fw_role_user
-- ----------------------------
DROP TABLE IF EXISTS `fw_role_user`;
CREATE TABLE `fw_role_user`  (
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  INDEX `fk_fw_role_user_fw_role_user_1`(`user_id`) USING BTREE,
  INDEX `fk_fw_role_user_fw_role_user_2`(`role_id`) USING BTREE,
  CONSTRAINT `fk_fw_role_user_fw_role_user_1` FOREIGN KEY (`user_id`) REFERENCES `fw_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_fw_role_user_fw_role_user_2` FOREIGN KEY (`role_id`) REFERENCES `fw_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_role_user
-- ----------------------------
INSERT INTO `fw_role_user` VALUES (1, 1);

-- ----------------------------
-- Table structure for fw_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `fw_schedule_job`;
CREATE TABLE `fw_schedule_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bean_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 参数',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(255) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_schedule_job
-- ----------------------------
INSERT INTO `fw_schedule_job` VALUES (1, 'testTask', 'bb', '0 0/1 * * * ?', 1, '66', '2019-06-27 20:23:05', '2019-06-27 20:33:06', '', 'run');

-- ----------------------------
-- Table structure for fw_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `fw_schedule_job_log`;
CREATE TABLE `fw_schedule_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) NULL DEFAULT NULL COMMENT '任务id',
  `bean_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(255) NULL DEFAULT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NULL DEFAULT NULL COMMENT '执行时间 毫秒',
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fw_schedule_job_id`(`job_id`) USING BTREE,
  CONSTRAINT `fw_schedule_job_id` FOREIGN KEY (`job_id`) REFERENCES `fw_schedule_job` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 223 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_schedule_job_log
-- ----------------------------
INSERT INTO `fw_schedule_job_log` VALUES (47, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:21:16', NULL, '', NULL);
INSERT INTO `fw_schedule_job_log` VALUES (48, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:21:20', NULL, '', NULL);
INSERT INTO `fw_schedule_job_log` VALUES (49, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:21:25', NULL, '', NULL);
INSERT INTO `fw_schedule_job_log` VALUES (50, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:21:30', NULL, '', NULL);
INSERT INTO `fw_schedule_job_log` VALUES (51, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:21:35', NULL, '', NULL);
INSERT INTO `fw_schedule_job_log` VALUES (52, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:25:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (53, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (54, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (55, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (56, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (57, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (58, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (59, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (60, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (61, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (62, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (63, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (64, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:26:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (65, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:27:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (66, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:27:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (67, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (68, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (69, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (70, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (71, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (72, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (73, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (74, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (75, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (76, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:27:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (77, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:28:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (78, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (79, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (80, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (81, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (82, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:31:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (83, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (84, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (85, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:31:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (86, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:32:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (87, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (88, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (89, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (90, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (91, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (92, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (93, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:32:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (94, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (95, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (96, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:32:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (97, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:32:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (98, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (99, NULL, 'testTask', 'aa', 0, NULL, 3, '2019-06-26 23:33:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (100, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:33:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (101, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:33:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (102, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (103, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:33:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (104, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (105, NULL, 'testTask', 'aa', 0, NULL, 9, '2019-06-26 23:33:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (106, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (107, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (108, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:33:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (109, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:33:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (110, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:34:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (111, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (112, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:34:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (113, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (114, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (115, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (116, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (117, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-26 23:34:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (118, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (119, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-26 23:34:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (120, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-26 23:34:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (121, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 10:52:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (122, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 10:52:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (123, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 10:52:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (124, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 10:52:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (125, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:04:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (126, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:04:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (127, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:04:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (128, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:04:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (129, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:04:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (130, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:04:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (131, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:04:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (132, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:04:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (133, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:08:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (134, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:08:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (135, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:09:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (136, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:09:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (137, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:09:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (138, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:09:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (139, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:09:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (140, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:09:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (141, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:09:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (142, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:09:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (143, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:09:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (144, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:09:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (145, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:09:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (146, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:09:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (147, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (148, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (149, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (150, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (151, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (152, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (153, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (154, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (155, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (156, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:10:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (157, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:10:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (158, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:10:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (159, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:19:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (160, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:19:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (161, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:19:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (162, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:19:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (163, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:19:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (164, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:19:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (165, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:19:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (166, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:19:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (167, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:19:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (168, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (169, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (170, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (171, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (172, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:20:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (173, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (174, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (175, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (176, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:20:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (177, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:20:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (178, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:20:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (179, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:20:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (180, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:21:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (181, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:21:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (182, NULL, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 11:21:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (183, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:21:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (184, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:21:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (185, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 11:21:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (186, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 11:21:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (187, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (188, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (189, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (190, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (191, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (192, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (193, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 12:14:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (194, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:40', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (195, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:45', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (196, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:50', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (197, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:14:55', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (198, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (199, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:05', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (200, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (201, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:15', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (202, NULL, 'testTask', 'aa', 0, NULL, 2, '2019-06-27 12:15:20', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (203, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:25', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (204, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:30', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (205, NULL, 'testTask', 'aa', 0, NULL, 1, '2019-06-27 12:15:35', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (206, NULL, 'testTask2', 'aa', 1, 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'testTask2\' available', 6, '2019-06-27 17:58:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (207, NULL, 'testTask2', 'aa', 1, 'org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named \'testTask2\' available', 2, '2019-06-27 17:58:10', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (208, 1, 'testTask', 'qq', 0, NULL, 1, '2019-06-27 20:23:09', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (209, 1, 'testTask', 'qq', 0, NULL, 0, '2019-06-27 20:24:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (210, 1, 'testTask', 'qq', 0, NULL, 1, '2019-06-27 20:24:01', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (211, 1, 'testTask', 'qq', 0, NULL, 2, '2019-06-27 20:25:47', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (212, 1, 'testTask', 'qq', 0, NULL, 1, '2019-06-27 20:26:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (213, 1, 'testTask', 'qq', 0, NULL, 0, '2019-06-27 20:28:58', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (214, 1, 'testTask', 'qq', 0, NULL, 0, '2019-06-27 20:29:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (215, 1, 'testTask', 'qq', 0, NULL, 1, '2019-06-27 20:29:02', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (216, 1, 'testTask', 'qq', 0, NULL, 0, '2019-06-27 20:29:17', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (217, 1, 'testTask', 'qq', 0, NULL, 1, '2019-06-27 20:30:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (218, 1, 'testTask', 'qq', 0, NULL, 0, '2019-06-27 20:31:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (219, 1, 'testTask', 'qq', 0, NULL, 2, '2019-06-27 20:32:00', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (220, 1, 'testTask', 'aa', 0, NULL, 0, '2019-06-27 20:32:36', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (221, 1, 'testTask', 'bb', 0, NULL, 3, '2019-06-27 20:32:51', NULL, '', 'run');
INSERT INTO `fw_schedule_job_log` VALUES (222, 1, 'testTask', 'bb', 0, NULL, 1, '2019-06-27 20:33:00', NULL, '', 'run');

-- ----------------------------
-- Table structure for fw_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `fw_sys_log`;
CREATE TABLE `fw_sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_id` bigint(255) NULL DEFAULT NULL COMMENT '用户id',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '执行时长 单位：毫秒',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行ip',
  `result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作日志记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_sys_log
-- ----------------------------
INSERT INTO `fw_sys_log` VALUES (1, 'admin', 1, '管理员', '修改用户', 'com.framework.admin.controller.FwUserController.editinfo()', '[{\"avatar\":\"1563156092403.jpg\",\"email\":\"\",\"mobile\":\"\",\"nickName\":\"管理员\",\"remark\":\"\",\"sex\":1,\"status\":1,\"userName\":\"admin\"}]', 88, '0:0:0:0:0:0:0:1', '{\"code\":\"0\",\"message\":\"操作成功\"}', '2019-07-15 10:01:34', NULL, '');
INSERT INTO `fw_sys_log` VALUES (2, 'admin', 1, '管理员', '修改用户', 'com.framework.admin.controller.FwUserController.editinfo()', '[{\"avatar\":\"1563156092403.jpg\",\"email\":\"510973261@qq.com\",\"mobile\":\"1530136093\",\"nickName\":\"管理员\",\"remark\":\"测试\",\"sex\":1,\"status\":1,\"userName\":\"admin\"}]', 68, '0:0:0:0:0:0:0:1', '{\"code\":\"0\",\"message\":\"操作成功\"}', '2019-07-15 10:02:04', NULL, '');
INSERT INTO `fw_sys_log` VALUES (3, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 12, '0:0:0:0:0:0:0:1', '{\"code\":\"0\",\"message\":\"操作成功\"}', '2019-07-15 10:06:50', NULL, '');
INSERT INTO `fw_sys_log` VALUES (4, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 6, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员账户\"}', '2019-07-15 10:07:25', NULL, '');
INSERT INTO `fw_sys_log` VALUES (5, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 10, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员账户\"}', '2019-07-15 10:07:29', NULL, '');
INSERT INTO `fw_sys_log` VALUES (6, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 13, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员账户\"}', '2019-07-15 10:07:37', NULL, '');
INSERT INTO `fw_sys_log` VALUES (7, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 8, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员账户\"}', '2019-07-15 10:08:08', NULL, '');
INSERT INTO `fw_sys_log` VALUES (8, 'admin', 1, '管理员', '删除用户', 'com.framework.admin.controller.FwUserController.del()', '[1]', 11, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员账户\"}', '2019-07-15 10:08:16', NULL, '');
INSERT INTO `fw_sys_log` VALUES (9, 'admin', 1, '管理员', '删除角色', 'com.framework.admin.controller.FwRoleController.del()', '[1]', 9, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能删除管理员角色\"}', '2019-07-15 10:08:32', NULL, '');
INSERT INTO `fw_sys_log` VALUES (10, 'admin', 1, '管理员', '删除角色', 'com.framework.admin.controller.FwRoleController.del()', '[1]', 5, '127.0.0.1', '{\"code\":\"-1\",\"message\":\"不能删除管理员角色\"}', '2019-07-15 10:08:59', NULL, '');
INSERT INTO `fw_sys_log` VALUES (11, 'admin', 1, '管理员', '删除角色', 'com.framework.admin.controller.FwRoleController.del()', '[1]', 3, '127.0.0.1', '{\"code\":\"-1\",\"message\":\"不能删除管理员角色\"}', '2019-07-15 10:09:05', NULL, '');
INSERT INTO `fw_sys_log` VALUES (12, 'admin', 1, '管理员', '修改角色', 'com.framework.admin.controller.FwRoleController.edit()', '[1,{\"id\":1,\"remark\":\"管理员\",\"roleCode\":\"admin\",\"roleName\":\"管理员\"}]', 11, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能操作管理员角色\"}', '2019-07-15 10:09:07', NULL, '');
INSERT INTO `fw_sys_log` VALUES (13, 'admin', 1, '管理员', '角色分配权限', 'com.framework.admin.controller.FwRoleController.authrole()', '[1,\"[20,29,61,43,42,41,30,27,55,54,53,28,25,52,51,50,49,48,47,46,26,23,45,44,24,5,1,18,40,39,38,19,16,17,4,60,59,58,57,12,3,37,36,35,34,11,2,33,32,13,10,9]\"]', 5, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能操作管理员角色\"}', '2019-07-15 10:09:11', NULL, '');
INSERT INTO `fw_sys_log` VALUES (14, 'admin', 1, '管理员', '角色分配权限', 'com.framework.admin.controller.FwRoleController.authrole()', '[1,\"[20,29,61,43,42,41,30,27,55,54,53,28,25,52,51,50,49,48,47,46,26,23,45,44,24,5,1,18,40,39,38,19,16,17,4,60,59,58,57,12,3,37,36,35,34,11,2,33,32,13,10,9]\"]', 11, '0:0:0:0:0:0:0:1', '{\"code\":\"-1\",\"message\":\"不能操作管理员角色\"}', '2019-07-15 10:09:41', NULL, '');
INSERT INTO `fw_sys_log` VALUES (15, 'admin', 1, '管理员', '角色分配权限', 'com.framework.admin.controller.FwRoleController.authrole()', '[1,\"[20,29,61,43,42,41,30,27,55,54,53,28,25,52,51,50,49,48,47,46,26,23,45,44,24,5,1,18,40,39,38,19,16,17,4,60,59,58,57,12,3,37,36,35,34,11,2,33,32,13,10,9]\"]', 7, '127.0.0.1', '{\"code\":\"-1\",\"message\":\"不能操作管理员角色\"}', '2019-07-15 10:09:55', NULL, '');

-- ----------------------------
-- Table structure for fw_user
-- ----------------------------
DROP TABLE IF EXISTS `fw_user`;
CREATE TABLE `fw_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建id',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态  0：禁用   1：正常',
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'pass',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(10) NULL DEFAULT NULL COMMENT '性别1男2女',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `parent_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所有父id集合多个之前[1],[2],[3]隔开',
  `base_create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `base_update_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fw_user
-- ----------------------------
INSERT INTO `fw_user` VALUES (1, 'admin', '$2a$10$xWNijWzpNp7FwiLKC.NM8O4BbrKMgFkYi54qN2EITTWbh2guqk8XC', '1530136093', NULL, NULL, '510973261@qq.com', 1, '123456', '管理员', 1, '1563156092403.jpg', NULL, NULL, '2019-07-15 10:02:04', NULL, '测试');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/1 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', NULL, 'com.framework.admin.job.util.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E636F6D2E6672616D65776F726B2E61646D696E2E656E746974792E46775363686564756C654A6F62456E7469747900000000000000010200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00066D6574686F6471007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7872002B636F6D2E6672616D65776F726B2E636F6D6D6F6E2E626173652E656E746974792E42617365456E74697479A2C1E3B59032D41B0200044C000E6261736543726561746554696D6571007E00094C000E6261736555706461746554696D6571007E00094C0008637265617465497071007E00094C000269647400104C6A6176612F6C616E672F4C6F6E673B7870740013323031392D30362D32372032303A32333A3035707400007372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740008746573745461736B74000D3020302F31202A202A202A203F74000372756E74000271717400023636737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0011000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'PC-20150815AXTH1563156740417', 1563156936581, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1561638840000, 1561638780000, 5, 'PAUSED', 'CRON', 1561638185000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002E636F6D2E6672616D65776F726B2E61646D696E2E656E746974792E46775363686564756C654A6F62456E7469747900000000000000010200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00066D6574686F6471007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7872002B636F6D2E6672616D65776F726B2E636F6D6D6F6E2E626173652E656E746974792E42617365456E74697479A2C1E3B59032D41B0200044C000E6261736543726561746554696D6571007E00094C000E6261736555706461746554696D6571007E00094C0008637265617465497071007E00094C000269647400104C6A6176612F6C616E672F4C6F6E673B7870740013323031392D30362D32372032303A32333A3035740013323031392D30362D32372032303A33323A32357400007372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740008746573745461736B74000D3020302F31202A202A202A203F74000372756E74000262627400023636737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0012000000007800);

SET FOREIGN_KEY_CHECKS = 1;
