# 🚀 Microservicios Bancarios - `ms-clientes` & `ms-cuentas` 🏦

![Microservices](https://img.shields.io/badge/Microservices-SpringBoot-green) ![Docker](https://img.shields.io/badge/Docker-Compose-blue) ![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange) ![SQLServer](https://img.shields.io/badge/Database-SQLServer-red)

Este proyecto es una implementación de un **sistema bancario** basado en **microservicios** utilizando `Spring Boot`, `RabbitMQ`, `Docker`, `SQL Server` y `Docker Compose` para la infraestructura.

---

## ✨ **¿Qué Se Logró?**
✔ **Implementación de dos microservicios**:  
  - **`ms-clientes`** 📂 → Gestiona clientes y asignación de cuentas.  
  - **`ms-cuentas`** 📂 → Maneja cuentas, movimientos y generación de reportes.

✔ **Comunicación asíncrona** entre microservicios con **RabbitMQ** 📩.

✔ **Validaciones y lógica de negocio**, incluyendo:
   - Verificación de saldo antes de realizar un movimiento.
   - Asignación única de cuentas a clientes.

✔ **Pruebas Unitarias & de Integración** con `JUnit`.

✔ **Dockerización completa** 🐋:
   - `RabbitMQ` con creación automática de colas.
   - `SQL Server` con bases de datos `ClientesDB` y `CuentasDB`.
   - Microservicios empaquetados en `Docker`.

✔ **Despliegue local fácil** con `Docker Compose` 📦.

---

## 🛠 **🚀 ¿Cómo Deployar en Local?**
Sigue estos pasos para levantar todo el sistema en tu máquina **sin complicaciones**. 🚀

### 💮 **1⃣ Levantar RabbitMQ** 🐇
Ejecuta este comando:
```sh
docker-compose up -d rabbitmq
```
📌 **Verifica que RabbitMQ está corriendo en** `http://localhost:15672`  
🗜 **Credenciales:**  
  - Usuario: `guest`  
  - Contraseña: `guest`

---

### 💮 **2⃣ Levantar SQL Server** 🟢
Ejecuta este comando:
```sh
docker-compose up -d sqlserver
```
📌 **Verifica que está corriendo con:**
```sh
docker ps
```
Debe aparecer `sqlserver-container` en la lista.

#### **💮 2.1 - Conectar con Azure Data Studio**
📌 Usa las siguientes credenciales:
- **Servidor:** `localhost,1433`
- **Usuario:** `sa`
- **Contraseña:** `SiSePuede123*`
- **Autenticación:** SQL Server Authentication

---

### 💮 **3⃣ Crear las Bases de Datos** 🏦
Ejecuta lo siguiente en `Azure Data Studio` o `SQL Server Management Studio`:

```sql
CREATE DATABASE ClientesDB;
CREATE DATABASE CuentasDB;
```

---

### 💮 **4⃣ Levantar los Microservicios** 🚀
Ahora, ejecuta:
```sh
docker-compose up -d ms-clientes ms-cuentas
```
📌 **Verifica que están corriendo con:**
```sh
docker ps
```
Deben aparecer `ms-clientes` y `ms-cuentas` en la lista.

---

## 🔎 **📀 Probar los Endpoints con Postman**
📌 **Colección de Postman para pruebas:**  
👉 **[Acceder a la Colección en Postman](https://www.postman.com/frankdevg/workspace/pruebatecnicabanca/collection/15595185-bdbead67-296a-4f23-9636-e9cb526f2764?action=share&creator=15595185)**  

---

## 🚀 **📀 Cómo Detener Todo**
Si necesitas detener los servicios, ejecuta:
```sh
docker-compose down
```
Si deseas eliminar los volúmenes de datos:
```sh
docker-compose down -v
```

---

## 🎯 **📁 Tecnologías Utilizadas**
- ✅ `Spring Boot 3.2.4` 🚀
- ✅ `Spring Data JPA` 📂
- ✅ `RabbitMQ` 🐇
- ✅ `SQL Server` 🏦
- ✅ `Docker & Docker Compose` 🐋
- ✅ `JUnit` (Pruebas Unitarias) ✅
- ✅ `Postman` (Testing de API) ✅

---

## ✨ **🎯 Autor**
👨‍💻 **Desarrollado por:** [Tu Nombre]  
📞 **Contacto:** [Tu Correo o LinkedIn]

📌 **¡Gracias por visitar este repositorio!** Si te sirvió, **dale una ⭐ en GitHub** y **compártelo**. 🚀🔥
