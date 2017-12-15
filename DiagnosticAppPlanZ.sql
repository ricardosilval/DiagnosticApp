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
INSERT INTO `alumnos` VALUES ('4a361bac25ce424a842836220c6e065ffc6f','Hernan Bernardino','Barra','Morales','18974786-1','51e7a2b92b135248912813b240ae222fbcdd','b5fd28782c5dc24ea12ac0d2ae4872029227'),('75079b5e2d9c7240242b1e42a675db22be64','José Felipe Charbel','Pavez','Gassibe','19113615-2','97fbcd6c29db924bff29b7d2c0bf117c561f','b5fd28782c5dc24ea12ac0d2ae4872029227'),('86d050532782b241a92961024903f1ce53b9','Juanito Ernesto','Lopez','Sepulveda','1-9','73be36442e277240012a107269e1d42ec8b8','b5fd28782c5dc24ea12ac0d2ae4872029227');
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
INSERT INTO `calendarizaciones` VALUES ('0423463a2705d24754287ad2bf8047ea4a93','2017-01-08 00:00:00','2017-01-26 00:00:00','Solo diurnos !!!!','dfdgf',NULL,1),('2f6b2d40-d561-11e7-a15f-cecfa4c5f064','2017-01-29 00:00:00','2027-01-29 00:00:00','Evaluacion VAK','Test para medir tu estilo de aprendizaje','85f10aec-d55f-11e7-a15f-cecfa4c5f064',1),('a654e9fa23afa247de2968529ab3e93d078f','2017-12-15 00:00:00','2017-11-30 00:00:00','dgfdf','dfdgf',NULL,1),('b795f87d227df24d662b6f428b11ddd19550','2017-12-01 00:00:00','2018-02-28 00:00:00','Matriculas 2018','Periodo en curso',NULL,1),('cb27a5ee2c63a249a42ab2c2521e3231d0be','2017-12-12 00:00:00','2017-12-20 00:00:00','efrdfdfsd','fsdfsdxfd',NULL,1),('e11915a12d15a241222aeb82a573880087a3','2018-01-01 00:00:00','2018-01-31 00:00:00','Generacion 2020','Para tecnicos programadores',NULL,1);
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
INSERT INTO `carreras` VALUES ('105743f628645240a728d1b2bc1203000701','IECIRE923','Ingenieria en Informática'),('51e7a2b92b135248912813b240ae222fbcdd','INGPAMAR','Ingenieria en Pavimentacion Marina'),('73be36442e277240012a107269e1d42ec8b8','TPCIRE99','Técnico en Programación'),('97fbcd6c29db924bff29b7d2c0bf117c561f','IECIRE928','Ingenieria en Informática');
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
  `categoria_padre_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`categoria_id`),
  KEY `fk_categorias_categorias1_idx` (`categoria_padre_id`),
  CONSTRAINT `fk_categorias_categorias1` FOREIGN KEY (`categoria_padre_id`) REFERENCES `categorias` (`categoria_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES ('1','Psicologia','Test para evaluar estilo de apredizaje',NULL),('d8053388-e060-11e7-ba10-b33d17bc6ce5','Matematicas','Conocimientos Matemáticos',NULL),('fc83ede4-e060-11e7-ba10-b33d17bc6ce5','Algebra','Algebra','d8053388-e060-11e7-ba10-b33d17bc6ce5');
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
INSERT INTO `evaluaciones` VALUES ('45d52890-d563-11e7-a15f-cecfa4c5f064','Evaluación VAK','1','85f10aec-d55f-11e7-a15f-cecfa4c5f064','2f6b2d40-d561-11e7-a15f-cecfa4c5f064');
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
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`jornada_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornadas`
--

LOCK TABLES `jornadas` WRITE;
/*!40000 ALTER TABLE `jornadas` DISABLE KEYS */;
INSERT INTO `jornadas` VALUES ('b5fd28782c5dc24ea12ac0d2ae4872029227','diurno','diurno');
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
  `imagen_id` varchar(36) DEFAULT '',
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
INSERT INTO `preguntas` VALUES ('0c0efc3c-d6fb-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Cómo definirías tu forma de vestir?',1),('0dcb39a4-d620-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Cuando te dan instrucciones...',1),('146fbfa0-d567-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Cuando tienes que aprender algo de memoria...',1),('1587e57c-d6fa-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Cómo prefieres pasar el tiempo con tu pareja?',1),('2eecbd2e-d6fc-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué es lo que más te gusta de una habitación?',1),('325429b6-d625-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Si te ofrecieran uno de los siguientes empleos, ¿cuál elegirías?',1),('351e1d44-d6f4-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Si tuvieras que quedarte en una isla desierta, ¿qué preferirías llevar contigo?',1),('4022355a-d568-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Cuando estás en clase y el profesor explica algo que está escrito en la pizarra o en tu libro, te es más fácil seguir las explicaciones...',1),('40e72458-d6f5-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué programas de televisión prefieres ver?',1),('440251d6-d569-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Marca la frase con las que te identifiques más.',1),('576c7180-d626-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿A qué tipo de evento preferirías asistir?',1),('72583f2a-d6fc-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué es lo que más te gusta de viajar?',1),('782bd832-d632-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Principalmente, ¿cómo te consideras?',1),('84f7d3cc-d56a-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Cuando no encuentras las llaves en una bolsa, ¿qué haces para encontrarlas más rápidamente?',1),('8602f660-d6f6-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué prefieres hacer en tu tarde libre?',1),('861dfb60-d6f4-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué cosas te distraen más en clase?',1),('952c5cbc-d6fb-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Si pudieras elegir ¿qué preferirías ser?',1),('a4cbc60a-d564-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Qué tipo de examen realizas con mayor facilidad?',1),('a67694ca-d628-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','Si tuvieras mucho dinero ahora mismo, ¿qué harías?',1),('b0da6e24-d569-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Cuál de las siguientes actividades disfrutas más?',1),('b5a96596-d6f8-11e7-8591-989a3d6234e0','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿De qué manera te formas una opinión de otras personas?',1),('ef5fe232-d622-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿Cuál de estos ambientes te atrae más?',1),('f190cfda-d569-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','¿De qué manera te resulta más fácil aprender algo?',1),('f7532d9c-d630-11e7-a15f-cecfa4c5f064','45d52890-d563-11e7-a15f-cecfa4c5f064',NULL,'1','En clase lo que más te gusta para aprender es que...',1);
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
  `cuerpo` longtext,
  `valor` int(11) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `imagen_id` varchar(36) DEFAULT '',
  PRIMARY KEY (`respuesta_id`),
  KEY `fk_respuestas_preguntas1_idx` (`pregunta_id`),
  KEY `fk_respuestas_imagenes1_idx` (`imagen_id`),
  CONSTRAINT `fk_respuestas_imagenes1` FOREIGN KEY (`imagen_id`) REFERENCES `imagenes` (`imagen_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_respuestas_preguntas1` FOREIGN KEY (`pregunta_id`) REFERENCES `preguntas` (`pregunta_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES ('0abfd284-d633-11e7-a15f-cecfa4c5f064','782bd832-d632-11e7-a15f-cecfa4c5f064','Intelectual.',101,1,NULL),('0ac0209a-d633-11e7-a15f-cecfa4c5f064','782bd832-d632-11e7-a15f-cecfa4c5f064','Sociable.',102,1,NULL),('0ac047e6-d633-11e7-a15f-cecfa4c5f064','782bd832-d632-11e7-a15f-cecfa4c5f064','Atlético.',103,1,NULL),('0c3b7c56-d626-11e7-a15f-cecfa4c5f064','325429b6-d625-11e7-a15f-cecfa4c5f064','Editor de una revista.',101,1,NULL),('0c3bc4d6-d626-11e7-a15f-cecfa4c5f064','325429b6-d625-11e7-a15f-cecfa4c5f064','Locutor de una emisora de radio.',102,1,NULL),('0c3bf7d0-d626-11e7-a15f-cecfa4c5f064','325429b6-d625-11e7-a15f-cecfa4c5f064','Director de un club deportivo.',103,1,NULL),('15ba63aa-d621-11e7-a15f-cecfa4c5f064','0dcb39a4-d620-11e7-a15f-cecfa4c5f064','Me cuesta recordar las instrucciones orales, pero no hay problema si me las dan por escrito.',101,1,NULL),('18c0675c-d621-11e7-a15f-cecfa4c5f064','0dcb39a4-d620-11e7-a15f-cecfa4c5f064','Recuerdo con facilidad las palabras exactas de lo que me han dicho.',102,1,NULL),('26dbcc52-d566-11e7-a15f-cecfa4c5f064','a4cbc60a-d564-11e7-a15f-cecfa4c5f064','Examen oral',101,1,NULL),('2ba4da0e-d6f5-11e7-8591-989a3d6234e0','861dfb60-d6f4-11e7-8591-989a3d6234e0','Las explicaciones demasiado largas.',101,1,NULL),('2ba5266c-d6f5-11e7-8591-989a3d6234e0','861dfb60-d6f4-11e7-8591-989a3d6234e0','El ruido.',102,1,NULL),('2ba55812-d6f5-11e7-8591-989a3d6234e0','861dfb60-d6f4-11e7-8591-989a3d6234e0','El movimiento.',103,1,NULL),('3866dc42-d56a-11e7-a15f-cecfa4c5f064','f190cfda-d569-11e7-a15f-cecfa4c5f064','Escribiéndolo varias veces.',101,1,NULL),('38672008-d56a-11e7-a15f-cecfa4c5f064','f190cfda-d569-11e7-a15f-cecfa4c5f064','Repitiendo en voz alta.',102,1,NULL),('38674858-d56a-11e7-a15f-cecfa4c5f064','f190cfda-d569-11e7-a15f-cecfa4c5f064','Relacionándolo con algo, a poder ser divertido.',103,1,NULL),('3db96b00-d566-11e7-a15f-cecfa4c5f064','a4cbc60a-d564-11e7-a15f-cecfa4c5f064','Examen escrito',102,1,NULL),('4cc6372a-d6f8-11e7-8591-989a3d6234e0','8602f660-d6f6-11e7-8591-989a3d6234e0','Ir al cine.',101,1,NULL),('4cc6b178-d6f8-11e7-8591-989a3d6234e0','8602f660-d6f6-11e7-8591-989a3d6234e0','Ir a un concierto.',103,1,NULL),('510f9cb0-d566-11e7-a15f-cecfa4c5f064','a4cbc60a-d564-11e7-a15f-cecfa4c5f064','Examen de opción múltiple',103,1,NULL),('544e57c2-d6fb-11e7-8591-989a3d6234e0','0c0efc3c-d6fb-11e7-8591-989a3d6234e0','Con gusto y conjuntada.',101,1,NULL),('544e9b2e-d6fb-11e7-8591-989a3d6234e0','0c0efc3c-d6fb-11e7-8591-989a3d6234e0','Discreta pero correcta.',102,1,NULL),('544eca5e-d6fb-11e7-8591-989a3d6234e0','0c0efc3c-d6fb-11e7-8591-989a3d6234e0','Informal.',103,1,NULL),('5ac2e5d8-d6fa-11e7-8591-989a3d6234e0','1587e57c-d6fa-11e7-8591-989a3d6234e0','Viendo algo juntos.',101,1,NULL),('5ac32516-d6fa-11e7-8591-989a3d6234e0','1587e57c-d6fa-11e7-8591-989a3d6234e0','Conversando.',102,1,NULL),('5ac34aaa-d6fa-11e7-8591-989a3d6234e0','1587e57c-d6fa-11e7-8591-989a3d6234e0','Con caricias.',103,1,NULL),('62ecd480-d623-11e7-a15f-cecfa4c5f064','ef5fe232-d622-11e7-a15f-cecfa4c5f064','Uno con una hermosa vista al océano.',101,1,NULL),('62ed21f6-d623-11e7-a15f-cecfa4c5f064','ef5fe232-d622-11e7-a15f-cecfa4c5f064','Uno en el que se escuchen las olas del mar.',102,1,NULL),('62ed548c-d623-11e7-a15f-cecfa4c5f064','ef5fe232-d622-11e7-a15f-cecfa4c5f064','Uno en el que se sienta un clima agradable.',103,1,NULL),('63ccc9c6-d6fc-11e7-8591-989a3d6234e0','2eecbd2e-d6fc-11e7-8591-989a3d6234e0','Que esté limpia y ordenada.',101,1,NULL),('63cd1d36-d6fc-11e7-8591-989a3d6234e0','2eecbd2e-d6fc-11e7-8591-989a3d6234e0','Que sea silenciosa.',102,1,NULL),('63cd44be-d6fc-11e7-8591-989a3d6234e0','2eecbd2e-d6fc-11e7-8591-989a3d6234e0','Que esté limpia y ordenada.',103,1,NULL),('65e15282-d629-11e7-a15f-cecfa4c5f064','a67694ca-d628-11e7-a15f-cecfa4c5f064','Comprar una casa.',101,1,NULL),('65e19d3c-d629-11e7-a15f-cecfa4c5f064','a67694ca-d628-11e7-a15f-cecfa4c5f064','Adquirir un estudio de grabación.',102,1,NULL),('65e1d6bc-d629-11e7-a15f-cecfa4c5f064','a67694ca-d628-11e7-a15f-cecfa4c5f064','Viajar y conocer el mundo.',103,1,NULL),('6a53f9da-d6f8-11e7-8591-989a3d6234e0','8602f660-d6f6-11e7-8591-989a3d6234e0','Quedarme en casa.',102,1,NULL),('751ff84a-d6f4-11e7-8591-989a3d6234e0','351e1d44-d6f4-11e7-8591-989a3d6234e0','Algunos buenos libros.',101,1,NULL),('75203ac6-d6f4-11e7-8591-989a3d6234e0','351e1d44-d6f4-11e7-8591-989a3d6234e0','Un radio portátil.',102,1,NULL),('752061cc-d6f4-11e7-8591-989a3d6234e0','351e1d44-d6f4-11e7-8591-989a3d6234e0','Golosinas y comida enlatada.',103,1,NULL),('756d34aa-d6f6-11e7-8591-989a3d6234e0','40e72458-d6f5-11e7-8591-989a3d6234e0','Reportajes de descubrimientos y lugares.',101,1,NULL),('756d79ce-d6f6-11e7-8591-989a3d6234e0','40e72458-d6f5-11e7-8591-989a3d6234e0','Noticias sobre el mundo y la actualidad.',102,1,NULL),('756da3cc-d6f6-11e7-8591-989a3d6234e0','40e72458-d6f5-11e7-8591-989a3d6234e0','Programas de entretenimiento.',103,1,NULL),('7fd7d9f8-d567-11e7-a15f-cecfa4c5f064','146fbfa0-d567-11e7-a15f-cecfa4c5f064','Memorizo lo que veo y recuerdo la imagen (por ejemplo, la página del libro)',101,1,NULL),('83ce3424-d569-11e7-a15f-cecfa4c5f064','440251d6-d569-11e7-a15f-cecfa4c5f064','Mis cuadernos y libretas están ordenados y bien presentados, me molestan los tachones y las correcciones.',101,1,NULL),('83ce793e-d569-11e7-a15f-cecfa4c5f064','440251d6-d569-11e7-a15f-cecfa4c5f064','Prefiero escuchar chistes que leer cómics.',102,1,NULL),('83cea09e-d569-11e7-a15f-cecfa4c5f064','440251d6-d569-11e7-a15f-cecfa4c5f064','Me gusta tocar las cosas y tiendo a acercarme mucho a la gente cuando hablo con alguien.',103,1,NULL),('90de9b04-d631-11e7-a15f-cecfa4c5f064','f7532d9c-d630-11e7-a15f-cecfa4c5f064','Me den el material escrito y con fotos, diagramas.',101,1,NULL),('90dfa648-d631-11e7-a15f-cecfa4c5f064','f7532d9c-d630-11e7-a15f-cecfa4c5f064','Se organicen debates y que haya diálogo.',102,1,NULL),('90dfdfd2-d631-11e7-a15f-cecfa4c5f064','f7532d9c-d630-11e7-a15f-cecfa4c5f064','Se organicen actividades en que los alumnos tengan que hacer cosas y puedan moverse.',103,1,NULL),('a1e7ae1a-d6fc-11e7-8591-989a3d6234e0','72583f2a-d6fc-11e7-8591-989a3d6234e0','Aprender sobre otras costumbres.',103,1,NULL),('a6a60c94-d6fc-11e7-8591-989a3d6234e0','72583f2a-d6fc-11e7-8591-989a3d6234e0','Conocer lugares nuevos.',101,1,NULL),('a6a65398-d6fc-11e7-8591-989a3d6234e0','72583f2a-d6fc-11e7-8591-989a3d6234e0','Conocer personas y hacer nuevos amigos.',102,1,NULL),('b4a38d3a-d620-11e7-a15f-cecfa4c5f064','0dcb39a4-d620-11e7-a15f-cecfa4c5f064','Me pongo en movimiento antes de que acaben de hablar y explicar lo que hay que hacer.',103,1,NULL),('c23441ce-d567-11e7-a15f-cecfa4c5f064','146fbfa0-d567-11e7-a15f-cecfa4c5f064','Memorizo a base de pasear y mirar, y recuerdo una idea general mejor que los detalles',102,1,NULL),('d435bc5e-d567-11e7-a15f-cecfa4c5f064','146fbfa0-d567-11e7-a15f-cecfa4c5f064','Memorizo mejor si repito lo estudiado rítmicamente y recuerdo paso a paso',103,1,NULL),('d49b8024-d626-11e7-a15f-cecfa4c5f064','576c7180-d626-11e7-a15f-cecfa4c5f064','A un espectáculo de magia.',101,1,NULL),('d49cb1e2-d626-11e7-a15f-cecfa4c5f064','576c7180-d626-11e7-a15f-cecfa4c5f064','A un concierto de música.',102,1,NULL),('d49ce824-d626-11e7-a15f-cecfa4c5f064','576c7180-d626-11e7-a15f-cecfa4c5f064','A una muestra gastronómica.',103,1,NULL),('d7c7bf88-d568-11e7-a15f-cecfa4c5f064','4022355a-d568-11e7-a15f-cecfa4c5f064','Escuchando al profesor.',102,1,NULL),('e2a231ee-d569-11e7-a15f-cecfa4c5f064','b0da6e24-d569-11e7-a15f-cecfa4c5f064','Ver películas.',101,1,NULL),('e2a27758-d569-11e7-a15f-cecfa4c5f064','b0da6e24-d569-11e7-a15f-cecfa4c5f064','Escuchar música.',102,1,NULL),('e2a2abd8-d569-11e7-a15f-cecfa4c5f064','b0da6e24-d569-11e7-a15f-cecfa4c5f064','Bailar.',103,1,NULL),('e6c2a716-d6fb-11e7-8591-989a3d6234e0','952c5cbc-d6fb-11e7-8591-989a3d6234e0','Un gran pintor.',101,1,NULL),('e6c2e9d8-d6fb-11e7-8591-989a3d6234e0','952c5cbc-d6fb-11e7-8591-989a3d6234e0','Un gran músico.',102,1,NULL),('e6c311ce-d6fb-11e7-8591-989a3d6234e0','952c5cbc-d6fb-11e7-8591-989a3d6234e0','Un gran médico.',103,1,NULL),('eb2879f8-d56a-11e7-a15f-cecfa4c5f064','84f7d3cc-d56a-11e7-a15f-cecfa4c5f064','Las busco mirando.',101,1,NULL),('eb28b896-d56a-11e7-a15f-cecfa4c5f064','84f7d3cc-d56a-11e7-a15f-cecfa4c5f064','Sacudo la bolsa para oír el ruído.',102,1,NULL),('eb28df10-d56a-11e7-a15f-cecfa4c5f064','84f7d3cc-d56a-11e7-a15f-cecfa4c5f064','Las busco con la mano, pero sin mirar.',103,1,NULL),('eebbe4d0-d6f8-11e7-8591-989a3d6234e0','b5a96596-d6f8-11e7-8591-989a3d6234e0','Por su aspecto.',101,1,NULL),('eebc508c-d6f8-11e7-8591-989a3d6234e0','b5a96596-d6f8-11e7-8591-989a3d6234e0','Por la sinceridad en su voz.',102,1,NULL),('eebc8624-d6f8-11e7-8591-989a3d6234e0','b5a96596-d6f8-11e7-8591-989a3d6234e0','Por la forma de estrecharte la mano.',103,1,NULL),('f072defa-d568-11e7-a15f-cecfa4c5f064','4022355a-d568-11e7-a15f-cecfa4c5f064','Leyendo el libro o la pizarra.',101,1,NULL),('f0732388-d568-11e7-a15f-cecfa4c5f064','4022355a-d568-11e7-a15f-cecfa4c5f064','Me aburro y espero a que me den algo para hacer.',103,1,NULL);
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
INSERT INTO `roles` VALUES ('1602338c-c85d-11e7-a06e-0159bb9ee5a1','examinador','Realiza operaciones sobre las evaluaciones y obtiene resultado de estas'),('5656e043-38c9-4eb4-aeb2-3cd3ffac3eed','alumno','Solo puede contestar evaluaciones'),('BBDDdiagnosticapp','administradorBBDD','Inserta evaluaciones por BBDD '),('superadmindiagnosticapp','administrador','Administrador');
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
INSERT INTO `tokens` VALUES ('00081c272e17224fa828cdc2c9258833080f','2017-12-14 14:33:21','2017-12-14 15:03:21',1,'483377de2da7e247e02a977201d017069ede'),('1f2447bf2d2582460b2b8d52a503eb0e1c1e','2017-12-14 14:20:54','2017-12-14 14:50:54',1,'9a9444af2f46f24e472a4192bbcc9c45a667'),('2442669e20c8124dcb292f2271efc0a7f6ba','2017-12-13 08:11:21','2017-12-13 08:41:21',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('262bb10327e5b2418d28a4027bc6cde1838d','2017-12-14 14:41:11','2017-12-14 15:11:11',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('2e5562d32f097242ff2bb5329b5d479e70fb','2017-12-14 14:26:18','2017-12-14 14:56:18',1,'483377de2da7e247e02a977201d017069ede'),('2e7fc85d25da62448628bef27f30e72c7da1','2017-12-12 18:58:47','2017-12-12 19:28:47',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('32c0261f2683d2435b294862d7365a6d05ed','2017-12-11 23:50:13','2017-12-12 00:20:13',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('472f69dc28d3824f55294e12cec56e2dd8ac','2017-12-14 19:07:23','2017-12-14 19:37:23',1,'fa7f52332d7402431b2a9c12b8b7ebaf35a7'),('49912b9e2d47424b5529f572841da9b55705','2017-12-14 14:30:59','2017-12-14 15:00:59',1,'483377de2da7e247e02a977201d017069ede'),('6825ced82b20e24cfc2b4d72876c5f932bf4','2017-12-12 21:34:03','2017-12-12 22:04:03',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('84fccee127b8b24c7828dab2798dc429ba3c','2017-12-11 23:50:38','2017-12-12 00:20:38',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('8e3c93f7217642483a2bd9d22d7b2dc51d1c','2017-12-14 17:50:47','2017-12-14 18:20:47',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('8f4c658b2b32824444297b6226813ef4e31e','2017-12-14 19:46:58','2017-12-14 20:16:58',1,'bf621cd42aa2324f432b8352b5ce9e870c8a'),('afe835ae2d5fd246702af39203affc2f6d63','2017-12-14 14:26:55','2017-12-14 14:56:55',1,'483377de2da7e247e02a977201d017069ede'),('bdcf9ae1209e62449528ff724b0d1644b818','2017-12-12 19:11:55','2017-12-12 19:41:55',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('d8b98944210be240032ae0823aebe486f2e5','2017-12-14 14:27:39','2017-12-14 14:57:39',1,'483377de2da7e247e02a977201d017069ede'),('df195a5f2b9492499e2a6d6274638d890890','2017-12-12 21:37:14','2017-12-12 22:07:14',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e'),('e46e4f0f2ea03241582bf98273c7a55dbd59','2017-12-11 23:48:17','2017-12-12 00:18:17',1,'670621dd-f2e0-4bcd-904a-f8c651cf250e');
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
INSERT INTO `usuarios` VALUES ('483377de2da7e247e02a977201d017069ede',NULL,'Hernan Nanito Ernesto Alejando','Barra','18974786-1','5656e043-38c9-4eb4-aeb2-3cd3ffac3eed','18974786-1',NULL,1,'2017-12-14 14:26:18'),('670621dd-f2e0-4bcd-904a-f8c651cf250e','admin@da.cl','Super','Administrador','1-9','superadmindiagnosticapp','ciisa',NULL,1,'2017-11-12 16:45:00'),('85f10aec-d55f-11e7-a15f-cecfa4c5f064','bbdd@da.cl','Sistema','Administrador','1-8','BBDDdiagnosticapp','BBDD',NULL,1,'2017-11-29 20:46:30'),('bf621cd42aa2324f432b8352b5ce9e870c8a',NULL,'José Felipe Charbel','Pavez','19113615-2','5656e043-38c9-4eb4-aeb2-3cd3ffac3eed','19113615-2',NULL,1,'2017-12-14 19:46:58');
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

-- Dump completed on 2017-12-14 21:50:51
