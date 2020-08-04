-- --------------------------------------------------------
-- 主机:                           47.101.198.61
-- Server version:               10.3.14-MariaDB - MariaDB Server
-- Server OS:                    Linux
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for jsp_plane_ticket_book
CREATE DATABASE IF NOT EXISTS `jsp_plane_ticket_book` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `jsp_plane_ticket_book`;

-- Dumping structure for table jsp_plane_ticket_book.admin_user
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE IF NOT EXISTS `admin_user` (
  `user` varchar(16) NOT NULL COMMENT '管理员用户名',
  `pwd` varchar(32) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='管理员用户表';

-- Dumping data for table jsp_plane_ticket_book.admin_user: 1 rows
/*!40000 ALTER TABLE `admin_user` DISABLE KEYS */;
INSERT INTO `admin_user` (`user`, `pwd`) VALUES
	('admin', '74D839D98630E280DF752E8939454A6B');
/*!40000 ALTER TABLE `admin_user` ENABLE KEYS */;

-- Dumping structure for table jsp_plane_ticket_book.common_user
DROP TABLE IF EXISTS `common_user`;
CREATE TABLE IF NOT EXISTS `common_user` (
  `user_name` varchar(16) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `avatar_img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table jsp_plane_ticket_book.common_user: 2 rows
/*!40000 ALTER TABLE `common_user` DISABLE KEYS */;
INSERT INTO `common_user` (`user_name`, `user_pwd`, `avatar_img`) VALUES
	('admin', '74D839D98630E280DF752E8939454A6B', '9c77f9e7-6334-49df-a444-149e7bd49f81.png'),
	('matou', 'F59BD65F7EDAFB087A81D4DCA06C4910', '8470c358-2855-4c99-9aed-3aac027c8b6f.jpg');
/*!40000 ALTER TABLE `common_user` ENABLE KEYS */;

-- Dumping structure for table jsp_plane_ticket_book.flight
DROP TABLE IF EXISTS `flight`;
CREATE TABLE IF NOT EXISTS `flight` (
  `f_n` varchar(6) NOT NULL COMMENT '航班号',
  `f_s_p` varchar(4) NOT NULL COMMENT '航班起点',
  `f_e_p` varchar(4) NOT NULL COMMENT '航班终点',
  `f_s_a` varchar(12) NOT NULL COMMENT '航班起飞机场',
  `f_a_a` varchar(12) NOT NULL COMMENT '航班到达机场',
  `f_d_t` varchar(5) NOT NULL COMMENT '起飞(departure)时间',
  `f_a_t` varchar(5) NOT NULL COMMENT '到达时间',
  `f_f_c_p` int(11) NOT NULL COMMENT '头等舱价格First class price',
  `f_s_c_p` int(11) NOT NULL COMMENT '商务舱价格',
  `f_t_c_p` int(11) NOT NULL COMMENT '经济舱价格',
  PRIMARY KEY (`f_n`),
  UNIQUE KEY `f_n` (`f_n`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='航班信息';

-- Dumping data for table jsp_plane_ticket_book.flight: 3 rows
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` (`f_n`, `f_s_p`, `f_e_p`, `f_s_a`, `f_a_a`, `f_d_t`, `f_a_t`, `f_f_c_p`, `f_s_c_p`, `f_t_c_p`) VALUES
	('SU2312', '北京', '郑州', '大兴机场', '新郑机场', '07:30', '12:00', 2100, 1500, 800),
	('CA3060', '北京', '上海', '首都机场', '虹桥机场', '08:30', '12:30', 2100, 1600, 1200),
	('IR5098', '北京', '德黑兰', '首都国际机场', '德黑兰国际机场', '9::50', '18:50', 5600, 18800, 3500);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;

-- Dumping structure for table jsp_plane_ticket_book.t_order
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE IF NOT EXISTS `t_order` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `order_user` varchar(50) NOT NULL COMMENT '下单用户',
  `f_n` varchar(50) NOT NULL COMMENT '航班号',
  `p_name` varchar(50) NOT NULL COMMENT '乘客姓名',
  `date` varchar(50) NOT NULL COMMENT '订单日期',
  `grade` varchar(50) NOT NULL COMMENT '舱别',
  `p_id` varchar(50) NOT NULL COMMENT '乘客身份证号',
  `contact` varchar(50) NOT NULL COMMENT '联系人',
  `c_p` varchar(50) NOT NULL COMMENT '联系人电话',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table jsp_plane_ticket_book.t_order: 3 rows
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` (`id`, `order_user`, `f_n`, `p_name`, `date`, `grade`, `p_id`, `contact`, `c_p`) VALUES
	(7, 'matou', 'SU2312', 'du yabo', '2020-01-10', '头等舱', '223600', 'du yabo', '13022502404'),
	(8, 'matou', 'SU2312', 'matou', '2020-01-09', '头等舱', '321312321213132213', 'matou', '13022502404'),
	(9, 'admin', 'CA3060', '王尧', '2020-01-09', '头等舱', '534534654654', '为人父', '45345443');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;

-- Dumping structure for table jsp_plane_ticket_book.user_message
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE IF NOT EXISTS `user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(10) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `message_content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- Dumping data for table jsp_plane_ticket_book.user_message: 3 rows
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;
INSERT INTO `user_message` (`id`, `time`, `user_name`, `message_content`) VALUES
	(24, '2019-6-14', '小糊涂仙', '贵公司服务态度很好，继续努力^c^'),
	(30, '2020-1-9', 'matou', '很好'),
	(31, '2020-1-9', '源码码头', '留言');
/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
