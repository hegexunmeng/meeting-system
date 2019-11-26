/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.6.14-log : Database - meeting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`meeting` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `meeting`;

/*Table structure for table `counter` */

DROP TABLE IF EXISTS `counter`;

CREATE TABLE `counter` (
  `visitcount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `counter` */

insert  into `counter`(`visitcount`) values 
(29);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `departmentid` int(16) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`departmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`departmentid`,`departmentname`) values 
(1,'技术部'),
(2,'人事部'),
(3,'财务部'),
(4,'行政部'),
(5,'天才部');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `employeeid` int(16) NOT NULL AUTO_INCREMENT,
  `employeename` varchar(14) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `departmentid` int(16) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

insert  into `employee`(`employeeid`,`employeename`,`username`,`phone`,`email`,`status`,`departmentid`,`password`,`role`) values 
(1,'天才英俊','admin','10086','xxx@163.com','1',1,'1','1'),
(8,'王晓华','wangxh','13671075406','wang@qq.com','1',1,'1','1'),
(9,'林耀坤','linyk','13671075406','yang@qq.com','2',2,'1','2'),
(10,'熊杰文','xiongjw','134555555','xiong@qq.com','2',3,'1','2'),
(11,'王敏','wangmin','1324554321','wangm@qq.com','2',4,'1','2'),
(12,'林耀坤','linyk','1547896765','kun@qq.com','1',7,'1','2'),
(13,'林耀坤','linyk','13897338822','yao@qq.com','1',1,'2','2'),
(14,'林耀坤','linyk','18908789808','yangyk@qq.com','1',2,'2','2'),
(15,'黄美玲','huangml','huangml@qq.com','13567898765','0',3,'1','2'),
(16,'黄美玲','huangml','huangml@qq.com','13567898765','1',4,'1','2'),
(17,'黄美玲','huangml002','huangml@qq.com','13567898765','1',1,'1','2'),
(20,'王敏','wangmin002','13454332334','wang@qq.com','0',4,'1','2'),
(21,'陈敏','chenm','13559994444','www@aa.com','0',2,'1','2'),
(23,'陈晨','wangm','22·2','11','0',1,'1','2'),
(25,'王晓华','wangxh222','111','1','0',4,'1','2'),
(28,'英俊啊','admin5','123456','123456@163.com','1',1,'admin5','2'),
(29,'英俊啊','admin5','123456','123456@163.com','2',1,'admin5','2'),
(30,'天才英俊2','admin2','1','1','1',3,'1','2'),
(31,'英俊','yingjun','huangml@qq.com','13567898765','0',1,'1','2');

/*Table structure for table `meeting` */

DROP TABLE IF EXISTS `meeting`;

CREATE TABLE `meeting` (
  `meetingid` int(16) NOT NULL AUTO_INCREMENT,
  `meetingname` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `roomid` int(16) DEFAULT NULL,
  `reservationistid` int(16) DEFAULT NULL,
  `numberofparticipants` int(16) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `reservationtime` datetime DEFAULT NULL,
  `canceledtime` datetime DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`meetingid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `meeting` */

insert  into `meeting`(`meetingid`,`meetingname`,`roomid`,`reservationistid`,`numberofparticipants`,`starttime`,`endtime`,`reservationtime`,`canceledtime`,`description`,`status`) values 
(1,'a',5,1,2,'2019-08-20 17:23:09','2019-08-21 17:23:10','2019-08-20 17:23:21','2019-08-20 19:48:16','a','1'),
(2,'天才',11,1,2,'2019-08-20 19:47:08','2019-08-21 19:47:10','2019-08-20 19:47:23',NULL,'天才','0'),
(3,'天才2',11,1,3,'2019-08-20 19:49:30','2019-08-23 19:49:32','2019-08-20 19:49:45',NULL,'啊飒飒','0'),
(4,'测试822',9,1,5,'2019-08-22 09:29:59','2019-08-24 09:30:01','2019-08-22 09:30:28','2019-08-22 19:02:58','测试8222','1'),
(5,'天才英俊',8,1,2,'2019-08-22 09:34:18','2019-08-26 09:34:20','2019-08-22 09:34:30','2019-08-22 19:03:01','','1'),
(6,'111',5,1,3,'2019-08-22 09:38:55','2019-08-24 09:38:57','2019-08-22 09:39:12',NULL,'啊','0'),
(7,'测试1',6,1,4,'2019-08-23 19:01:55','2019-08-31 19:02:03','2019-08-22 19:02:20',NULL,'','0'),
(8,'取消1',7,1,3,'2019-08-23 19:04:10','2019-08-27 19:04:13','2019-08-22 19:04:32','2019-08-22 19:04:50','','1'),
(9,'晓华参加1',9,8,2,'2019-08-22 19:25:12','2019-08-24 19:25:15','2019-08-22 19:25:28','2019-08-22 19:25:31','','1'),
(10,'晓华参加123',9,8,2,'2019-08-22 19:25:49','2019-08-24 19:25:53','2019-08-22 19:26:06','2019-08-22 19:34:05','','1'),
(11,'301会议室开会',12,8,3,'2019-08-26 19:26:58','2019-09-07 19:27:06','2019-08-22 19:27:31',NULL,'','0'),
(12,'123456',13,8,5,'2019-08-23 19:32:41','2019-08-24 19:32:44','2019-08-22 19:33:07','2019-08-22 19:34:42','','1');

/*Table structure for table `meetingparticipants` */

DROP TABLE IF EXISTS `meetingparticipants`;

CREATE TABLE `meetingparticipants` (
  `meetingid` int(16) NOT NULL,
  `employeeid` int(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `meetingparticipants` */

insert  into `meetingparticipants`(`meetingid`,`employeeid`) values 
(40,1),
(40,28),
(41,1),
(41,28),
(42,1),
(42,8),
(42,13),
(4,9),
(4,30),
(4,10),
(4,16),
(4,11),
(5,1),
(5,8),
(6,28),
(6,1),
(6,8),
(7,1),
(7,13),
(7,17),
(7,8),
(8,1),
(8,23),
(8,31),
(9,23),
(10,8),
(10,1),
(11,8),
(11,13),
(11,29),
(12,8),
(12,13),
(12,17),
(12,29),
(12,1),
(12,23);

/*Table structure for table `meetingroom` */

DROP TABLE IF EXISTS `meetingroom`;

CREATE TABLE `meetingroom` (
  `roomid` int(16) NOT NULL AUTO_INCREMENT,
  `roomnum` int(16) NOT NULL,
  `roomname` varchar(20) CHARACTER SET utf8 NOT NULL,
  `capacity` int(16) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `meetingroom` */

insert  into `meetingroom`(`roomid`,`roomnum`,`roomname`,`capacity`,`status`,`description`) values 
(5,101,'第一会议室',16,'0','公共会议室'),
(6,102,'第二会议室',5,'0','管理部门会议室'),
(7,103,'第三会议室',12,'0','市场部专用会议室'),
(8,401,'第四会议室',15,'0','公共会议室'),
(9,201,'第五会议室',15,'0','最大会议室'),
(10,601,'第六会议室',12,'0','需要提前三天预定'),
(11,104,'天才会议室',20,'0','天才会议室'),
(12,301,'301会议室',8,'0',''),
(13,302,'302会议室',8,'0','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
