-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: diagnosticapp
-- ------------------------------------------------------
-- Server version	5.6.35

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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `alumno_id` varchar(36) NOT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `apellido_paterno` varchar(45) DEFAULT NULL,
  `apellido_materno` varchar(45) DEFAULT NULL,
  `run` varchar(12) NOT NULL,
  `carrera_id` varchar(36) NOT NULL,
  `jornada_id` varchar(36) NOT NULL,
  PRIMARY KEY (`alumno_id`),
  UNIQUE KEY `run_UNIQUE` (`run`),
  KEY `fk_alumnos_carreras1_idx` (`carrera_id`),
  KEY `fk_alumnos_jornadas1_idx` (`jornada_id`),
  CONSTRAINT `fk_alumnos_carreras1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumnos_jornadas1` FOREIGN KEY (`jornada_id`) REFERENCES `jornadas` (`jornada_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumnos_respuestas`
--

DROP TABLE IF EXISTS `alumnos_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos_respuestas` (
  `alumno_respuesta_id` varchar(45) NOT NULL,
  `alumno_id` varchar(36) NOT NULL,
  `respuesta_id` varchar(36) NOT NULL,
  PRIMARY KEY (`alumno_respuesta_id`),
  KEY `fk_alumnos_has_respuestas_respuestas1_idx` (`respuesta_id`),
  KEY `fk_alumnos_has_respuestas_alumnos1_idx` (`alumno_id`),
  CONSTRAINT `fk_alumnos_has_respuestas_alumnos1` FOREIGN KEY (`alumno_id`) REFERENCES `alumnos` (`alumno_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumnos_has_respuestas_respuestas1` FOREIGN KEY (`respuesta_id`) REFERENCES `respuestas` (`respuesta_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos_respuestas`
--

LOCK TABLES `alumnos_respuestas` WRITE;
/*!40000 ALTER TABLE `alumnos_respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumnos_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendarizaciones`
--

DROP TABLE IF EXISTS `calendarizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendarizaciones` (
  `calendarizacion_id` varchar(36) NOT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_termino` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` text,
  `usuario_id` varchar(36) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`calendarizacion_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarizaciones`
--

LOCK TABLES `calendarizaciones` WRITE;
/*!40000 ALTER TABLE `calendarizaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarizaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendarizaciones_evaluaciones`
--

DROP TABLE IF EXISTS `calendarizaciones_evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendarizaciones_evaluaciones` (
  `calendarios_evaluacion_id` varchar(36) NOT NULL,
  `calendarizacion_id` varchar(36) NOT NULL,
  `evaluacion_id` varchar(36) NOT NULL,
  PRIMARY KEY (`calendarios_evaluacion_id`),
  KEY `fk_calendarizaciones_has_evaluaciones_evaluaciones1_idx` (`evaluacion_id`),
  KEY `fk_calendarizaciones_has_evaluaciones_calendarizaciones_idx` (`calendarizacion_id`),
  CONSTRAINT `fk_calendarizaciones_has_evaluaciones_calendarizaciones` FOREIGN KEY (`calendarizacion_id`) REFERENCES `calendarizaciones` (`calendarizacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_calendarizaciones_has_evaluaciones_evaluaciones1` FOREIGN KEY (`evaluacion_id`) REFERENCES `evaluaciones` (`evaluacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarizaciones_evaluaciones`
--

LOCK TABLES `calendarizaciones_evaluaciones` WRITE;
/*!40000 ALTER TABLE `calendarizaciones_evaluaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarizaciones_evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carreras` (
  `carrera_id` varchar(36) NOT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`carrera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `categoria_id` varchar(36) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `categoria_padre_id` varchar(36) NOT NULL,
  PRIMARY KEY (`categoria_id`,`categoria_padre_id`),
  KEY `fk_categorias_categorias1_idx` (`categoria_padre_id`),
  CONSTRAINT `fk_categorias_categorias1` FOREIGN KEY (`categoria_padre_id`) REFERENCES `categorias` (`categoria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluaciones`
--

DROP TABLE IF EXISTS `evaluaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluaciones` (
  `evaluacion_id` varchar(36) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `categoria_id` varchar(45) DEFAULT NULL,
  `usuario_id` varchar(45) DEFAULT NULL,
  `calendarizacion_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`evaluacion_id`),
  KEY `fk_evaluaciones_categorias1_idx` (`categoria_id`),
  KEY `fk_evaluaciones_usuarios1_idx` (`usuario_id`),
  CONSTRAINT `fk_evaluaciones_categorias1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`categoria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluaciones_usuarios1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluaciones`
--

LOCK TABLES `evaluaciones` WRITE;
/*!40000 ALTER TABLE `evaluaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagenes` (
  `imagen_id` varchar(36) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `data` longtext,
  PRIMARY KEY (`imagen_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornadas`
--

DROP TABLE IF EXISTS `jornadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornadas` (
  `jornada_id` varchar(36) NOT NULL,
  `codigo` varchar(1) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`jornada_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornadas`
--

LOCK TABLES `jornadas` WRITE;
/*!40000 ALTER TABLE `jornadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `jornadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `log_id` varchar(36) NOT NULL,
  `proceso_id` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `hostname` varchar(45) DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `stack_trace_fileId` varchar(250) DEFAULT NULL,
  `clase` varchar(45) DEFAULT NULL,
  `linea` int(11) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preguntas` (
  `pregunta_id` varchar(36) NOT NULL,
  `evaluacion_id` varchar(36) NOT NULL,
  `imagen_id` varchar(36) NOT NULL,
  `categoria_id` varchar(36) NOT NULL,
  `cuerpo` longtext,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`pregunta_id`),
  KEY `fk_preguntas_evaluaciones1_idx` (`evaluacion_id`),
  KEY `fk_preguntas_imagenes1_idx` (`imagen_id`),
  KEY `fk_preguntas_categorias1_idx` (`categoria_id`),
  CONSTRAINT `fk_preguntas_categorias1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`categoria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preguntas_evaluaciones1` FOREIGN KEY (`evaluacion_id`) REFERENCES `evaluaciones` (`evaluacion_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preguntas_imagenes1` FOREIGN KEY (`imagen_id`) REFERENCES `imagenes` (`imagen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuestas` (
  `respuesta_id` varchar(36) NOT NULL,
  `pregunta_id` varchar(45) DEFAULT NULL,
  `cuerpo` varchar(36) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `imagenes_imagen_id` varchar(36) NOT NULL,
  PRIMARY KEY (`respuesta_id`),
  KEY `fk_respuestas_preguntas1_idx` (`pregunta_id`),
  KEY `fk_respuestas_imagenes1_idx` (`imagenes_imagen_id`),
  CONSTRAINT `fk_respuestas_imagenes1` FOREIGN KEY (`imagenes_imagen_id`) REFERENCES `imagenes` (`imagen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_preguntas1` FOREIGN KEY (`pregunta_id`) REFERENCES `preguntas` (`pregunta_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `rol_id` varchar(36) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('1602338c-c85d-11e7-a06e-0159bb9ee5a1','examinador','Realiza operaciones sobre las evaluaciones y obtiene resultado de estas'),('5656e043-38c9-4eb4-aeb2-3cd3ffac3eed','alumno','Solo puede contestar evaluaciones'),('superadmindiagnosticapp','administrador','Administrador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tokens` (
  `token_id` varchar(36) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `creado` datetime DEFAULT NULL,
  `expira` datetime DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `usuario_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FK3lk2qjcmcoxjartjgb9mlc6kf` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES ('368ea5d52f4b6245692870e22aa74cd7f8e6','2017-11-12 17:05:21','2017-11-12 17:35:21',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('43bc926f2e02e2458628e302c9a4bde54549','2017-11-13 08:41:12','2017-11-13 09:11:12',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('43fe08f2220bd245972bdd02be5632310d80','2017-11-12 16:55:35','2017-11-12 17:25:35',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('4f3345d3261c82493c2910a233b6ff171a4b','2017-11-12 17:15:18','2017-11-12 17:45:18',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('4fce185828326243b62a18421563029b13b0','2017-11-13 07:11:24','2017-11-13 07:41:24',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('7349f09d2f03a247072b72c27d784f8720f9','2017-11-16 20:01:02','2017-11-16 20:31:02',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('802d165c2b8ea2420128c5828f9431888177','2017-11-12 17:17:54','2017-11-12 17:47:54',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('910c88812bf08245842b24223b3d3b070bb4','2017-11-13 19:33:28','2017-11-13 20:03:28',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('b8abb1782e8f4249e52859229c2e92e6a89d','2017-11-13 08:05:05','2017-11-13 08:35:05',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('e60608a02cd11248742a9b62702eb9f5ed49','2017-11-13 07:15:51','2017-11-13 07:45:51',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('fc2b449826ed924f3d2b76928b2355b99591','2017-11-16 19:42:05','2017-11-16 20:12:05',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usuario_id` varchar(36) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `run` varchar(12) NOT NULL,
  `rol_id` varchar(36) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `cambio_clave` varchar(255) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `creado` datetime DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `run_UNIQUE` (`run`),
  KEY `fk_usuarios_roles1_idx` (`rol_id`),
  CONSTRAINT `fk_usuarios_roles1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('670621dd-f2e0-4bcd-904a-f8c651cf250e','admin@da.cl','Super','Administrador','1-9','superadmindiagnosticapp','ciisa',NULL,1,'2017-11-12 16:45:00');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-16 20:01:53
