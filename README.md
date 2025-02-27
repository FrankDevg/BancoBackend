#  Microservicios Bancarios - `ms-clientes` & `ms-cuentas` 🏦

![Microservices](https://img.shields.io/badge/Microservices-SpringBoot-green) ![Docker](https://img.shields.io/badge/Docker-Compose-blue) ![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange) ![SQLServer](https://img.shields.io/badge/Database-SQLServer-red)
## Descripción General

Este proyecto es una implementación de un sistema bancario basado en **microservicios**, utilizando `Spring Boot`, `RabbitMQ`, `Docker`, `SQL Server` y `Docker Compose` para la infraestructura. Está diseñado para garantizar escalabilidad, confiabilidad y un alto desempeño en la gestión de clientes y cuentas bancarias.

## Arquitectura del Proyecto
### **Microservicios Implementados**
1. **`ms-clientes`** 📂 - Gestión de clientes y asignación de cuentas.
2. **`ms-cuentas`** 📂 - Manejo de cuentas, movimientos y generación de reportes.

### **Características Clave**
- **Comunicación asíncrona con RabbitMQ** para una integración eficiente entre microservicios.
- **Validaciones robustas** para asegurar la consistencia de datos:
  - Verificación de saldo antes de procesar movimientos.
  - Asignación controlada de cuentas a clientes.
- **Patrones de Diseño Implementados:**
  - `DDD (Domain Driven Design)` para una arquitectura modular.
  - `Repository Pattern` para desacoplar la lógica de acceso a datos.
  - `Pub/Sub` RabbitMQ
  - `Service Layer` se separa la lógica del negocio de los controllers
    
- **Pruebas Unitarias & de Integración** utilizando `JUnit`.
- **Contenedorización con Docker & Docker Compose**, asegurando despliegues eficientes y reproducibles.

## Instalación y Despliegue Local
### **1️⃣ Clonar el Proyecto**
```sh
git clone https://github.com/FrankDevg/BancoBackend.git
cd backend
```

### **2️⃣ Levantar Infraestructura con Docker Compose**
```sh
docker-compose up -d rabbitmq 
```
**Verificar RabbitMQ:** `http://localhost:15672`  
Credenciales: Usuario: `guest` | Contraseña: `guest`
```sh
docker ps
```
SQL SERVER
```sh
docker-compose up -d sqlserver 
```



**Verificar SQL Server:**
```sh
docker ps
```
Debe mostrar `sqlserver-container` corriendo.

### **3️⃣ Configuración de la Base de Datos**
Ejecutar el siguiente script en `Azure Data Studio` o `SQL Server Management Studio`: Asegurarse de que no este instanciado otro servidor sql en la maquina local
```sql
CREATE DATABASE ClientesDB;
GO

CREATE DATABASE CuentasDB;
GO
```

### **4️⃣ Levantar los Microservicios**
```sh
docker-compose up -d ms-clientes ms-cuentas
```
Verificar que ambos microservicios están activos con:
```sh
docker ps
```

## Endpoints y Pruebas con Postman
Para facilitar las pruebas, puedes acceder a la colección de Postman aquí:
👉 **[Colección de Pruebas en Postman](https://www.postman.com/frankdevg/workspace/pruebatecnicabanca/collection/15595185-bdbead67-296a-4f23-9636-e9cb526f2764?action=share&creator=15595185)**

## Cómo Detener los Servicios
Para detener los servicios ejecuta:
```sh
docker-compose down
```
Para eliminar los volúmenes de datos:
```sh
docker-compose down -v
```

## Tecnologías Utilizadas
- **`Spring Boot 3.2.4`**
- **`Spring Data JPA`**
- **`RabbitMQ`** (Mensajería asíncrona)
- **`SQL Server`** (Base de datos relacional)
- **`Docker & Docker Compose`**
- **`JUnit`** (Pruebas Unitarias e Integración)
- **`Postman`** (Testing de API)

## Autor
👨‍💻 **Desarrollado por:** Andrés Ruiz  
📧 **Contacto:** franklindbruiz@gmail.com  

Si este proyecto fue útil, considera compartirlo o darle una ⭐ en GitHub.



