# DiagnosticApp
## Propósito
DiagnosticApp fue realizada dentro del contexto del curso de Proyecto de Ingenieria de Software.
El propósito  de este software es apoyar al proceso de evaluación de entrada (Ingreso al IP) que, a través de un generador de pruebas de diagnóstico, con esta herramienta se podrá tener una visión general del nivel de los conocimientos en diversas materias  y algunas características psicológicas de la generación entrante a la institución, con el fin de poder preparar a las respectivas áreas pedagógicas de la institución con esta información para que éstas puedan tomar acciones que se anticipen a la posible deserción por causas académicas


## Alcance
El software a construir tiene como objetivo apoyar al proceso de retención de alumnos a través de diversos test de diagnóstico de conocimientos relevantes y psicológicos, los cuales se deben resolver por parte de los estudiantes nuevos, incluyendo además la posibilidad de crear, modificar nuevas herramientas de evaluación diagnóstica. Estos le entregarán a la administración de la institución educacional a través de reportes, un perfil del nivel de conocimientos específicos y hábitos de estudios de ingreso de los alumnos. Dicha herramienta permitirá además la creación, edición y eliminación de perfiles de alumnos y examinadores.
Diagnostic-App, contempla la disponibilización de una serie de test preconfigurados en su implementación, de estos test, los de naturaleza evaluadora de conocimientos específicos podrán ser modificados por las áreas académicas respectivas según sus propios criterios, además contempla la incorporación de una base de datos propia de la aplicación, la que deberá poblarse por la institución luego de la implementación. Este software apoyará el proceso de retención de estudiantes de la deserción de la carrera mediante la entrega de la reportes producto de los test, otorgando así a la institución una serie de datos para que elabore sus políticas y planes de acción para abordar la generación de estudiantes en cuanto a su posible deserción.


## Sobre la Arquitectura
DiagnosticApp está desarrollado en lenguaje Java 8, y está compuesto por tres proyectos:
* DiagnosticApp - Core
  * Es un proyecto Java, en el cual se encuentran los Modelos, DAOs y Servicios externos.
* DiagnosticApp - API
  * Es un proyecto Java Web en el cual se definen los Controladores que proporciona los servicios API Rest con sus Request y Responss
* DiagnosticApp - Webapp
  * Es un proyecto Java Web, el cual ocupa la capa Web Pages para implementar la vista de DiagnosticApp, la cual está construída en AngularJS
