# 🧩 Sistema Web Kiwi

<p align="center">
  <strong>Backend de microservicios para gestión de usuarios, direcciones, donaciones y eventos</strong><br/>
  Arquitectura basada en Spring Boot y API Gateway.
</p>

## 📋 Tabla de Contenidos

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características](#-características)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Arquitectura](#-arquitectura)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Contribución](#-contribución)
- [Contacto](#-contacto)

---

## 🧬 Acerca del Proyecto

Este proyecto es un backend de microservicios desarrollado como parte de la asignatura de Ingeniería de Software. Está diseñado para manejar de forma independiente distintos dominios del negocio: usuarios, direcciones, donaciones e eventos, con un `API Gateway` centralizado para enrutar las solicitudes.

El sistema está pensado para ser flexible y escalable, separando responsabilidades en servicios REST independientes conectados a bases de datos MySQL.

---

## ✨ Características

- 👥 Gestión de usuarios con entidades de `Usuario` y `Rol`.
- 📍 Gestión de direcciones y comunas.
- 💝 Gestión de donaciones y recursos/insumos asociados.
- 🎉 Gestión de campañas y eventos.
- 🌐 Enrutamiento centralizado mediante `Spring Cloud Gateway`.
- 🗃️ Persistencia con `Spring Data JPA` y MySQL.
- ✅ Validación de entrada con Spring Validation.
- 📄 CRUD completo para cada dominio principal.

---

## 🛠️ Tecnologías Utilizadas

- Java 21
- Spring Boot 3.5.14
- Spring Cloud Gateway
- Spring Data JPA
- Spring Validation
- Spring Web / Spring WebFlux
- MySQL
- Lombok
- Maven

---

## 🏗️ Arquitectura

El proyecto backend contiene los siguientes servicios:

1. `api-gateway` — Puerto `9090`
2. `service-usuario` — Puerto `8082`
3. `service-direccion` — Puerto `8083`
4. `service-donacion` — Puerto `8084`
5. `service-eventos` — Puerto `8086`

> Nota: `api-gateway` también está configurado para enrutar `/mascotas/**` hacia `http://localhost:8085` si se añade el servicio correspondiente.

---

## 🚀 Instalación y Ejecución

### Prerrequisitos

- Java 21+
- Maven 3.9+
- Git
- MySQL

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd Ingenieria_De_Software_005D
```

### 2. Crear las bases de datos

Configura MySQL y crea las bases de datos necesarias:

```sql
CREATE DATABASE db_usuario;
CREATE DATABASE db_direccion;
CREATE DATABASE db_insumo;
CREATE DATABASE db_evento;
```

### 3. Iniciar cada servicio

Ejecuta cada servicio en su carpeta correspondiente usando Maven.

```bash
cd backend/service-usuario
mvn spring-boot:run
```

```bash
cd ../service-direccion
mvn spring-boot:run
```

```bash
cd ../service-donacion
mvn spring-boot:run
```

```bash
cd ../service-eventos
mvn spring-boot:run
```

```bash
cd ../api-gateway
mvn spring-boot:run
```

> El `API Gateway` debe iniciarse al final, después de que los microservicios estén disponibles.

---

## 📡 Endpoints de la API

Todos los endpoints se exponen desde el API Gateway en:

`http://localhost:9090`

### Service Usuario (`http://localhost:8082`)

- `GET /usuarios`
- `GET /usuarios/{id}`
- `GET /usuarios/rut/{rut}`
- `POST /usuarios`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

- `GET /roles`
- `GET /roles/{id}`
- `POST /roles`
- `PUT /roles/{id}`
- `DELETE /roles/{id}`

- `GET /usuariorol/rol/{rolId}`
- `DELETE /usuariorol/{id}`

### Service Direccion (`http://localhost:8083`)

- `GET /comunas`
- `GET /comunas/{id}`
- `DELETE /comunas/{id}`
- `GET /direcciones`
- `GET /direcciones/{id}`
- `DELETE /direcciones/{id}`

### Service Donacion (`http://localhost:8084`)

- `GET /donaciones`
- `GET /donaciones/{id}`
- `POST /donaciones`
- `PUT /donaciones/{id}`
- `DELETE /donaciones/{id}`
- `GET /donaciones/donante/{donId}`
- `GET /donaciones/insumo/{inId}`
- `GET /donaciones/campana/{cId}`

- `GET /insumos`
- `GET /insumos/{id}`
- `DELETE /insumos/{id}`

### Service Eventos (`http://localhost:8086`)

- `GET /campanias`
- `GET /campanias/{id}`
- `POST /campanias`
- `PUT /campanias/{id}`
- `DELETE /campanias/{id}`
- `GET /campanias/anio/{anio}`
- `GET /campanias/mes/{mes}`
- `GET /campanias/administrador/{aId}`

---

<p align="center">
  Sistema de Microservicios Kiwi © 2026
</p>
