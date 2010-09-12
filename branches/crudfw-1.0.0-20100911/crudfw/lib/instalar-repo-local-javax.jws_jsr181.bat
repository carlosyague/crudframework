echo "Instalando paquetes en el repositorio Maven2 local"

cd c:\LibrAE\librae

echo "instalando jsr181.jar"
mvn install:install-file -DgroupId=javax.jws -DartifactId=jsr181 -Dversion=1.0 -Dpackaging=jar -Dfile=/lib/javax.jws/jsr181.jar