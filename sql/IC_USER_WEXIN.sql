
-- ----------------------------
-- Table structure for FWT_USER_WEIXIN
-- ----------------------------

DROP TABLE IF EXISTS `FWT_USER_WEIXIN`;
CREATE TABLE `FWT_USER_WEIXIN` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `zxing` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1534 DEFAULT CHARSET=utf8 COMMENT='微信扩展表';

-- ----------------------------
-- Records of weixin
-- ----------------------------
INSERT INTO `FWT_USER_WEIXIN` VALUES ('1532', 'http://img01.sogoucdn.com/app/a/100520090/oIWsFt0KvQb-ao0wXBmWczF9f8Wc', '<em><!--red_beg-->火车头<!--red_end--></em>麻辣香锅', 'hctmlxgjsd', '专注于麻辣香锅制作,追求美味,追求体验.', 'http://img03.sogoucdn.com/app/a/100520105/5kxeUUPETX0yh1IynxnC', '我们的麻辣香锅，我们的相聚时光', '<p>初夏，毕业季又成为我们热门话题，此时，你大学时候的梦想实现了吗？想做的事情做了多少？不管怎样，三天端午小长假又来啦，不知去哪里聚会，就来麻辣香锅吧。香锅君为您配备了舒适避暑的场所，出示学生证还能享受8.8学生价。让忙碌的生活慢下来，追求满满的幸福。</p>', '2015-03-10 15:04:28');
