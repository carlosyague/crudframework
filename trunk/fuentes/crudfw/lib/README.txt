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

En este directorio se incluyen las dependencias de LibrÆ con paquetes JAR 
no incluidos en repositorios Maven2 públicos para que se pueda construir
el sistema.

Se pueden instalar en un repositorio local de Maven2 mediante la ejecución
del script Bash: instalar-repo-local.sh

Relación de paquetes:

    - com.liferay
        |-              (desarrollo portlets liferay)
    - com.oracle
        \- ojdb14.jar (driver JDBC Oracle 10.2.0.3.0)
