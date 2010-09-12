echo "Instalando paquetes en el repositorio Maven2 local"

cd c:\LibrAE\librae

echo "instalando a2jruntime.jar"

mvn install:install-file -DgroupId=com.k_int -DartifactId=a2jruntime -Dversion=2.0.4 -Dpackaging=jar -Dfile=/lib/com.k_int/a2jruntime.jar

