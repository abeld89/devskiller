# DEVSKILLER MANGO

## Introduction
Mini proyecto para el registro de usuarios y alta de slogans en la web de Mango.
Con una base de datos H2 en memoria, donde se almacenan estos usuarios con relación a sus slogans registrados para el concurso.

### Communication & Dependencies
Este microservicio usa el protocolo REST para exponer un servicio que se comunica con los repositorios pertinentes en H2.

## Endpoints & Services
El API Rest permite interactuar con la base de datos precargada para mostrar los datos.

* __POST__ `/user`: Crea un usuario con información básica.
* __PUT__ `/user/{userId}`: Actualiza un usuario pasando por parámetro el identificador.
* __GET__ `/campaigns/slogan`: Crea un slogan para el usuario indicado hasta un máximo de tres.

## Test
Se han desarrollado test unitarios y de integración.
Si se añade nueva funcionalidad, hay que realizar los test en sus respectivos paquetes: com.mango.customer.service.*

## Additional
- Se puede consultar el swagger desde -> http://{url}/swagger-ui.html
- Se puede deshabilitar la creación de la bd (H2) al arrancar desde las propiedades "application-local.yml" en spring.jpa.hibernate.ddl-auto = none
- Se puede cambiar el acceso a la bd (H2) de usuario y password desde las propiedades "application-local.yml" en spring.datasource.username y spring.datasource.password

## Puntos a mejorar
- No se está controlando los posibles errores en la creación de usuarios.
- Añadir más excepciones controladas con mensajes más específicos.
