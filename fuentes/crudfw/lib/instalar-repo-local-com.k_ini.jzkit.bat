echo "Instalando paquetes en el repositorio Maven2 local"

cd c:\LibrAE\librae

echo "instalando jzkit.jar"

mvn install:install-file -DgroupId=com.k_int -DartifactId=jzkit -Dversion=2.0.4 -Dpackaging=jar -Dfile=/lib/com.k_int/jzkit.jar