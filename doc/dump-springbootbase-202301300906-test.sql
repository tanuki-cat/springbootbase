-- MySQL dump 10.13  Distrib 5.7.40, for osx10.18 (x86_64)
--
-- Host: localhost    Database: springbootbase
-- ------------------------------------------------------
-- Server version	5.7.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permission_info`
--

DROP TABLE IF EXISTS `permission_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限表主键',
  `permission_name` varchar(255) NOT NULL COMMENT '权限名称',
  `permission_uri` varchar(255) NOT NULL COMMENT '权限URI',
  `permission_method` varchar(255) NOT NULL COMMENT '权限方法类型',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '权限创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_info`
--

LOCK TABLES `permission_info` WRITE;
/*!40000 ALTER TABLE `permission_info` DISABLE KEYS */;
INSERT INTO `permission_info` VALUES (1,'USER用户测试','/api/user','GET',0,'2020-07-05 18:13:47',NULL),(2,'ADMIN测试','/api/admin','GET',0,'2020-07-05 18:14:11',NULL);
/*!40000 ALTER TABLE `permission_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_info`
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色表主键',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) NOT NULL COMMENT '角色编码',
  `role_remark` varchar(255) NOT NULL COMMENT '角色备注',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '角色创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_info`
--

LOCK TABLES `role_info` WRITE;
/*!40000 ALTER TABLE `role_info` DISABLE KEYS */;
INSERT INTO `role_info` VALUES (1,'USER','USER','普通用户级别',0,'2020-07-02 10:34:37',NULL),(2,'ADMIN','ADMIN','超级管理员',0,'2020-07-05 18:12:57',NULL);
/*!40000 ALTER TABLE `role_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission_link`
--

DROP TABLE IF EXISTS `role_permission_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色与权限关联表',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `permission_id` bigint(20) NOT NULL COMMENT '权限表主键',
  `create_time` datetime NOT NULL COMMENT '创建时间 ',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission_link`
--

LOCK TABLES `role_permission_link` WRITE;
/*!40000 ALTER TABLE `role_permission_link` DISABLE KEYS */;
INSERT INTO `role_permission_link` VALUES (1,1,1,'2020-07-05 18:14:25',NULL),(2,2,2,'2020-07-05 18:14:31',NULL);
/*!40000 ALTER TABLE `role_permission_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_generator`
--

DROP TABLE IF EXISTS `test_generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_generator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_generator`
--

LOCK TABLES `test_generator` WRITE;
/*!40000 ALTER TABLE `test_generator` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
  `username` varchar(255) NOT NULL COMMENT '用户名称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1605835506195750915 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'user','$2a$10$ms3nmhX6O3J09oQDJlwC7eywfmuLkKiGKadvIno.SIBStlhXtvwGW',0,'2020-07-02 10:34:07',NULL),(2,'testUser1','$2a$10$hdiPsWeWuSwEqBriRoiPve6RCPEmBHCnAvlLVYhu4NYTDStXurdxa',0,'2022-12-20 12:48:00',NULL),(3,'litchi','$2a$10$fipdxA0GQ1yRUqFqPg.KfOKmfODw51jKV33VmxiD7n./1XbJQFNO.',0,'2022-12-22 16:13:12','2022-12-22 16:13:12'),(1605835506195750914,'lychee','$2a$10$CU9Vr5F0DLNdxhZaH8RVj.ccXTd34b8kzK/ZWSKxJyeM9o910lsY.',0,'2022-12-22 15:59:54','2022-12-22 15:59:54');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_link`
--

DROP TABLE IF EXISTS `user_role_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户与角色关联表主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户表主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色表主键',
  `create_time` datetime NOT NULL COMMENT '关联创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除字段 0未删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1605835506229305347 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_link`
--

LOCK TABLES `user_role_link` WRITE;
/*!40000 ALTER TABLE `user_role_link` DISABLE KEYS */;
INSERT INTO `user_role_link` VALUES (1,1,1,'2020-07-02 10:34:53',NULL,0),(2,2,1,'2022-12-20 12:51:00',NULL,0),(3,3,1,'2022-12-22 16:13:12','2022-12-22 16:13:12',0),(1605835506229305346,1605835506195750914,2,'2022-12-22 15:59:54','2022-12-22 15:59:54',0);
/*!40000 ALTER TABLE `user_role_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'springbootbase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-30  9:06:28
