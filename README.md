## Backend

El backend está desarrollado con Spring Boot y requiere Java 17 y MySQL.

La base de datos debe llamarse `customers_gestion` y estar disponible antes de iniciar la aplicación.

Desde PowerShell, ubicarse en la carpeta del backend:

cd C:\Proyectos\CRUD_Clientes\crud_backend

Para descargar las dependencias, compilar y ejecutar las pruebas:
.\mvnw.cmd clean install

Para empaquetar el proyecto:
.\mvnw.cmd clean package

Para empaquetar sin ejecutar las pruebas:
.\mvnw.cmd clean package -DskipTests

Para iniciar el backend en modo desarrollo:
.\mvnw.cmd spring-boot:run

El backend estará disponible en `http://localhost:8080`.

También puede ejecutarse el JAR generado:

java -jar target\crud_backend-0.0.1-SNAPSHOT.jar


## Frontend

El frontend está desarrollado con Angular y requiere Node.js y npm.

Desde otra terminal, ubicarse en la carpeta del frontend:
cd C:\Proyectos\CRUD_Clientes\crud_frontend

Para descargar las dependencias:
npm install

Para compilar el frontend:
npm run build

Para iniciar el servidor de desarrollo:
npm start

El frontend estará disponible en `http://localhost:4200`.