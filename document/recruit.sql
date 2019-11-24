/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : recruit

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 10/08/2019 15:09:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_interview
-- ----------------------------
DROP TABLE IF EXISTS `tb_interview`;
CREATE TABLE `tb_interview`  (
  `interview_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `interviewer` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试官',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试详情',
  `interview_phase` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '面试阶段',
  PRIMARY KEY (`interview_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 599 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '#fsj.recruit' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_interview
-- ----------------------------
INSERT INTO `tb_interview` VALUES (10, 25, '张金高', '行为举止,言谈谈吐,知识储备,逻辑思维,组织能力,沟通能力,备注信息-8,9,6,7,8,9,建议通过', '一面');
INSERT INTO `tb_interview` VALUES (11, 25, '樊世杰', '行为礼貌,对专业的了解程度,对编程的了解程度,性格稳重程度（坐得住）,沟通能力,组织能力,备注信息-8,6,7,5,8,5,测试', '一面');

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question`  (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_question
-- ----------------------------
INSERT INTO `tb_question` VALUES (14, '你怎么认为中途退小组的行为？');
INSERT INTO `tb_question` VALUES (15, '你感觉在生活中与他人交流比较的费事或者费时吗？（尽量找不只是回答好的方面的）');
INSERT INTO `tb_question` VALUES (16, '如果在小组学习的时候你的进度不如别人或者别人确实比你聪明，你愿意和这样的人接触吗？');
INSERT INTO `tb_question` VALUES (17, '如果你现在是大二的学长，现在正在面试我们大一的人，你会提出怎么样的问题？');
INSERT INTO `tb_question` VALUES (18, '你感觉自己会坚持到最后吗？如果会坚持到最后，说一下你为什么感觉自己能坚持到最后？你觉得影响你坚持到最后的最大的原因是什么？');
INSERT INTO `tb_question` VALUES (19, '你理想的大学生活是什么样子的。从学习生活方面回答。');
INSERT INTO `tb_question` VALUES (20, '你对大学生活有规划吗？打不打算考研？（重点）');
INSERT INTO `tb_question` VALUES (21, '对于大学，你还愿意保留着高中的激情吗？');
INSERT INTO `tb_question` VALUES (22, '当时为什么报这个专业，对专业有无了解。');
INSERT INTO `tb_question` VALUES (24, '对自己的性格做出评价？（从中分析是否适合集体学习生活）');
INSERT INTO `tb_question` VALUES (25, '能不能接受小组的时间制度。');
INSERT INTO `tb_question` VALUES (26, '谈一谈自己以前都担任过哪些职务？（有无意愿竞选学生会及班干）');
INSERT INTO `tb_question` VALUES (27, '加入这个小组的目的是什么？');
INSERT INTO `tb_question` VALUES (28, '如果以后进入了这个小组，小组活动与班级活动或者其他活动有冲突了，你会怎么办？');
INSERT INTO `tb_question` VALUES (29, '你喜欢数学吗？（可以适当看出解决问题方式）');
INSERT INTO `tb_question` VALUES (30, '说一件你坚持做了最长时间的事！（然后问如果是可以展示的，问二面能不能展示给我们看）');
INSERT INTO `tb_question` VALUES (31, '会不会坐着一直做一件事不尝试做做其他事情');
INSERT INTO `tb_question` VALUES (32, '是感兴趣还是只是来试试');
INSERT INTO `tb_question` VALUES (33, '会不会经常觉得压力大？怎么解决这个问题？');
INSERT INTO `tb_question` VALUES (34, '有什么创新（学习上，以及生活方式）');
INSERT INTO `tb_question` VALUES (35, '做过什么管理工作（观察组织能力）');
INSERT INTO `tb_question` VALUES (36, '对小组的学习模式是否认可（再问是否真正了解小组学习模式）');
INSERT INTO `tb_question` VALUES (37, '之前有什么特长');
INSERT INTO `tb_question` VALUES (38, '你曾经对电脑接触的多吗？接触电脑都是做什么用的？（不说打游戏的，应该是不真诚，说打游戏的可能比较爱玩，都有例外）');
INSERT INTO `tb_question` VALUES (39, '说说你在网上浏览过哪些的网页？想要实现这些网页吗');
INSERT INTO `tb_question` VALUES (40, '说说你加入小组之后最想学的方向？简单谈一下为什么？');
INSERT INTO `tb_question` VALUES (41, '曾经有没有想过在看到什么网页或者软件之类的东西想要实现它的功能或者是想要知道它是怎么实现的（看看此人是不是对软件相关的学习是真的感兴趣）');
INSERT INTO `tb_question` VALUES (42, '未来想一直从事软件开发即编写代码之类的工作吗？（说想的都是骗子，我认为的哈哈）');
INSERT INTO `tb_question` VALUES (43, '你知道有哪些编程语言吗？（如果知道然后举例这个语言能干什么）');
INSERT INTO `tb_question` VALUES (44, '你认为网页只是单纯的页面吗？');
INSERT INTO `tb_question` VALUES (45, '你更喜欢哪一种编程语言。');
INSERT INTO `tb_question` VALUES (46, '想不想成为一个it大牛');
INSERT INTO `tb_question` VALUES (47, '说说自己对于it行业的认识。');
INSERT INTO `tb_question` VALUES (48, '对于计算机语言你了解多少');
INSERT INTO `tb_question` VALUES (49, '喜欢数学还是英语有没有什么成就（高考成绩方面，平时如果这两门成绩不好是怎么提升的）');
INSERT INTO `tb_question` VALUES (50, '了解程序员么？怎么看待的？');
INSERT INTO `tb_question` VALUES (51, '你对搭建网站有没有兴趣？');
INSERT INTO `tb_question` VALUES (52, '你认为的最好团队是怎样的？');
INSERT INTO `tb_question` VALUES (53, '说说你对这个专业的理解（这个专业的发展方向）');
INSERT INTO `tb_question` VALUES (54, '知道前端和后端的区别吗？');
INSERT INTO `tb_question` VALUES (55, '谈谈自己在计算机这一领域所知道的一些知识');
INSERT INTO `tb_question` VALUES (56, '对我们小组了解多少？ 有什么方面的评价？');
INSERT INTO `tb_question` VALUES (57, '当你在小组的学习过程中，遇到了不懂的问题你会怎么做？');
INSERT INTO `tb_question` VALUES (58, '你是怎么看待小组没有假期（或假期很少）这件事的？');

-- ----------------------------
-- Table structure for tb_score_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_score_item`;
CREATE TABLE `tb_score_item`  (
  `score_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `score_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `full_marks` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`score_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_score_item
-- ----------------------------
INSERT INTO `tb_score_item` VALUES (2, '行为礼貌', 10);
INSERT INTO `tb_score_item` VALUES (4, '对专业的了解程度', 10);
INSERT INTO `tb_score_item` VALUES (5, '对编程的兴趣', 10);
INSERT INTO `tb_score_item` VALUES (7, '性格稳重程度（坐得住）', 10);
INSERT INTO `tb_score_item` VALUES (8, '沟通能力', 10);
INSERT INTO `tb_score_item` VALUES (11, '组织能力', 10);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `student_class` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `qq` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `interviewers` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `apply_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `interview_phase` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `written_test_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `interview_time` datetime(0) NULL DEFAULT NULL,
  `interview_status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 375 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (25, '测试', '男', '计科184', '17630329897', '1052391560', '张金高,樊世杰', '2018-09-19 11:51:03', '一面通过', '一面', 0, '/upload/writtenTestImg/25/43a9f3c4-f0fb-4927-b82d-0fc4b26fd77d.jpg,/upload/writtenTestImg/25/561c8d9a-3a22-4c2b-8e65-f8bca38bbfb4.jpg', '2018-09-21 17:18:00', 1);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'fsj', 'fsj501', '樊世杰');
INSERT INTO `tb_user` VALUES (2, 'zjg', 'zjg501', '张金高');
INSERT INTO `tb_user` VALUES (3, 'wjx', 'wjx501', '王静晓');
INSERT INTO `tb_user` VALUES (4, 'wqj', 'wqj501', '王青杰');
INSERT INTO `tb_user` VALUES (5, 'alj', 'alj501', '安李杰');
INSERT INTO `tb_user` VALUES (6, 'xyf', 'xyf501', '谢亚飞');
INSERT INTO `tb_user` VALUES (7, 'mlm', 'mlm501', '马黎明');
INSERT INTO `tb_user` VALUES (8, 'zsh', 'zsh501', '张顺海');
INSERT INTO `tb_user` VALUES (9, 'lhj', 'lhj501', '李会娟');
INSERT INTO `tb_user` VALUES (10, 'tyj', 'tyj501', '汤艳杰');
INSERT INTO `tb_user` VALUES (11, 'wky', 'wky501', '武凯焱');
INSERT INTO `tb_user` VALUES (12, 'hyx', 'hyx501', '胡亚星');
INSERT INTO `tb_user` VALUES (13, 'hsb', 'hsb501', '胡帅博');
INSERT INTO `tb_user` VALUES (14, 'ljh', 'ljh501', '卢俊辉');
INSERT INTO `tb_user` VALUES (15, 'wb', 'wb501', '王冰');
INSERT INTO `tb_user` VALUES (16, 'brn', 'brn501', '白瑞娜');

SET FOREIGN_KEY_CHECKS = 1;
