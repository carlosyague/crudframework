echo "Instalando paquetes en el repositorio Maven2 local"

cd c:\LibrAE\librae

echo "instalando camunda-urlparameter.jar"

mvn install:install-file -DgroupId=com.camunda -DartifactId=urlparameter -Dversion=1.0.0 -Dpackaging=jar -Dfile=/lib/com.camunda/camunda-urlparameter.jar