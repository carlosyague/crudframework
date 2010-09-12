    __    _ __         ___ ______
   / /   (_) /_  _____/   | ____/
  / /   / / __ \/ ___/ /| | __/
 / /___/ / /_/ / /  / ___ |/___
/_____/_/_.___/_/  /_/  |_|____/

Sistema Integral de Gestión de Bibliotecas - LibrÆ

http://www.juntadeandalucia.es/
http://www.juntaex.es/

UTE:
    http://www.sopraprofit.es/
    http://www.ingenia.es/

==============================================================================
librae-maven-plugin
    Es un plugin Maven basado en appfuse-maven-plugin, adaptado a las
    particularidades de la arquitectura de LibrÆ, para la generación
    automática de código a partir de entidades o tablas del modelo.

    Más info. sobre appfuse-maven-plugin: http://static.appfuse.org/plugins/appfuse-maven-plugin/

VERSIÓN: 0.1

INSTALACION:
	Al tener este plugin establecida como <distributionManagement /> el mismo repositorio que LibrÆ,
	no es necesario realizar ningún tipo de compilación/instalación de este módulo, a no ser que se haga
	alguna modificación. En ese caso, será preciso re-compilar (mvn install) y re-desplegar (mvn deploy).

MODO DE USO:
	Añadimos en los POMs de [core] y [web]:
	* librae-${subsistema}/core/pom.xml
	* librae-${subsistema}/web/pom.xml

	el siguiente <plugin />:


			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>librae-maven-plugin</artifactId>
				<version>0.1</version>
				<configuration>
					<genericCore>false</genericCore><!-- Set to false if you want Java files generated for your DAOs and Managers -->
					<fullSource>false</fullSource><!-- Set to true if you've "full-sourced" your project and changed org.appfuse to your package name -->
					<componentProperties>
						<appfusePackage>org.librae.common</appfusePackage>
						<authorName>UTE Sopra PROFit - Ingenia</authorName>
					</componentProperties>
				</configuration>
				<dependencies>
					<!-- Dependency needed by librae:gen-model to connect to database. -->
                	<!-- See http://issues.appfuse.org/browse/APF-868 to learn more.    -->
					<dependency>
						<groupId>${jdbc.groupId}</groupId>
						<artifactId>${jdbc.artifactId}</artifactId>
						<version>${jdbc.version}</version>
					</dependency>
				</dependencies>
			</plugin>

	NOTA: Una vez se haga la refactorización de paquetes de librae, <appfusePackage /> deberá valer: org.librae.common


	1. AUTOGENERACIÓN DE DAOs y MANAGERs EN EL MÓDULO [core]
	========================================================

###	1.1 Creamos manualmente nuestro POJO ${entidad} incluyendo las anotaciones JPA (Java Persistence API) necesarias:
	* org.librae.model.${entidad}.java


###	1.2 Instanciamos el plugin, indicándole el nombre de la entidad que queremos procesar, y opcionalmente, nuestro alias de autor.
	En el caso de que no se indique el authorName, se usará el valor especificado en el <plugin /> del pom.xml

	$librae-${subsistema}/core> mvn librae:gen-core -Dentity=${entidad} -DauthorName=AutorX

	Este comando nos generará las siguiente clases:
	* librae-${subsistema}/core/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/dao/I${entidad}DAO.java
	* librae-${subsistema}/core/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/dao/hibernate/${entidad}DAOImpl.java
	* librae-${subsistema}/core/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/service/I${entidad}Manager.java
	* librae-${subsistema}/core/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/service/impl/${entidad}ManagerImpl.java
	* librae-${subsistema}/core/src/test/java/es/juntadeandalucia/cultura/librae/${subsistema}/dao/impl/${entidad}DaoTest.java
	* librae-${subsistema}/core/src/test/java/es/juntadeandalucia/cultura/librae/${subsistema}/service/impl/${entidad}ManagerTest.java

	Y nos actualizará los siguientes archivos de configuración:
	* librae-${subsistema}/core/src/main/resources/hibernate.cfg.xml                     // Nos añade el hibernate-mapping de ${entidad}
	* librae-${subsistema}/core/src/test/resources/sample-data.xml                       // Nos añade datos aleatorios de carga iniciales de ${entidad}
	* librae-${subsistema}/core/src/main/resources/spring/applicationContext-dao.xml     // Nos añade la instancia del DAO de ${entidad}
	* librae-${subsistema}/core/src/main/resources/spring/applicationContext-service.xml // Nos añade la instancia del Manager de ${entidad}

	NOTA: Este plugin utiliza unos comentarios especiales para la inclusión de beans en los ficheros de spring applicationContext-*.xml.
	Con lo cuál, no deben eliminarse dichos comentarios y procurar emplazarlos al final de la declaración de beans:

	Fichero							Comentario
	applicationContext-dao.xml		<!-- Add new DAOs here -->
	applicationContext-service.xml	<!-- Add new Managers here -->



###	1.3 Empaquetamos el módulo [core] y lo instalamos en el repositorio local de maven:

	$librae-${subsistema}/core> mvn install



	2. AUTOGENERACIÓN DE VISTAS JSF (*.xhtml) EN EL MÓDULO [web]
	============================================================

### 2.1 Instanciamos el plugin, indicándole el nombre de la entidad que queremos procesar, y opcionalmente, nuestro alias de autor.
	En el caso de que no se indique el authorName, se usará el valor especificado en el <plugin /> del pom.xml

	$librae-${subsistema}/web> mvn librae:gen-web -Dentity=${entidad} -DauthorName=AutorX

	Este comando nos generará los siguiente archivos:
	* librae-${subsistema}/web/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/webapp/action/${entidad}FormAction.java
	* librae-${subsistema}/web/src/main/java/es/juntadeandalucia/cultura/librae/${subsistema}/webapp/action/${entidad}ListAction.java
	* librae-${subsistema}/web/src/test/java/es/juntadeandalucia/cultura/librae/${subsistema}/webapp/action/${entidad}FormTest.java
	* librae-${subsistema}/web/src/test/java/es/juntadeandalucia/cultura/librae/${subsistema}/webapp/action/${entidad}ListTest.java
	* librae-${subsistema}/web/src/main/webapp/pages/${entidad}/form.xhtml
	* librae-${subsistema}/web/src/main/webapp/pages/${entidad}/list.xhtml

	Y nos actualizará los siguientes archivos de configuración:
	* librae-${subsistema}/web/src/main/webapp/WEB-INF/faces-config.xml           // Nos incluirá las navigation-rules y managed-beans asociados a ${entidad}
	* librae-${subsistema}/web/src/main/resources/ApplicationResources.properties // Nos incluirá las properties multi-idioma por defecto


### 2.2 Compilamos el módulo [web] e iniciamos su servidor web asociado:

	$librae-${subsistema}/web> mvn package tomcat:run


### 2.3 Podremos acceder al listado de ${entidad} a través de la siguiente URL:

    http://localhost:${puerto}/librae-${subsistema}/pages/${entidad}/list.html
