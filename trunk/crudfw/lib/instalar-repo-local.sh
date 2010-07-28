#!/bin/bash

echo "Instalando paquetes en el repositorio Maven2 local"

# instalando ojdbc14.jar
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.3.0 -Dpackaging=jar -Dfile=com.oracle/ojdbc14.jar

# instalando jsr181.jar
mvn install:install-file -DgroupId=javax.jws -DartifactId=jsr181 -Dversion=1.0 -Dpackaging=jar -Dfile=javax.jws/jsr181.jar

# instalando postgresql-7.4.1-jdbc3.jar
mvn install:install-file -DgroupId=postgresql -DartifactId=postgresql -Dversion=7.4.1-jdbc3 -Dpackaging=jar -Dfile=postgresql/postgresql-7.4.1-jdbc3.jar

# instalando a2jruntime.jar
mvn install:install-file -DgroupId=com.k_int -DartifactId=a2jruntime -Dversion=2.0.4 -Dpackaging=jar -Dfile=com.k_int/a2jruntime.jar

# instalando jzkit.jar
mvn install:install-file -DgroupId=com.k_int -DartifactId=jzkit -Dversion=2.0.4 -Dpackaging=jar -Dfile=com.k_int/jzkit.jar

# instalando camunda-urlparameter.jar
mvn install:install-file -DgroupId=com.camunda -DartifactId=urlparameter -Dversion=1.0.0 -Dpackaging=jar -Dfile=com.camunda/camunda-urlparameter.jar

