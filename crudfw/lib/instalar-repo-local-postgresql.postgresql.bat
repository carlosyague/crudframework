echo "Instalando paquetes en el repositorio Maven2 local"

cd c:\LibrAE\librae

echo "instalando postgresql-7.4.1-jdbc3.jar"

mvn install:install-file -DgroupId=postgresql -DartifactId=postgresql -Dversion=7.4.1-jdbc3 -Dpackaging=jar -Dfile=/lib/postgresql/postgresql-7.4.1-jdbc3.jar