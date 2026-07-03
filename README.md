# 🧩 Sistema Web Kiwi

<p align="center">
  <strong>Backend de microservicios para gestión de usuarios, direcciones, donaciones y eventos</strong><br/>
  Arquitectura basada en Spring Boot y API Gateway.
</p>

##  Tabla de Contenidos

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características](#-características)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Arquitectura](#-arquitectura)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Contribución](#-contribución)
- [Contacto](#-contacto)

---

##  Acerca del Proyecto

Este proyecto es un backend de microservicios desarrollado como parte de la asignatura de Ingeniería de Software. Está diseñado para manejar de forma independiente distintos dominios del negocio: usuarios, direcciones, donaciones e eventos, con un `API Gateway` centralizado para enrutar las solicitudes.

El sistema está pensado para ser flexible y escalable, separando responsabilidades en servicios REST independientes conectados a bases de datos MySQL.

---

##  Características

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

##  Arquitectura

El proyecto backend contiene los siguientes servicios:

1. `api-gateway` — Puerto `9090`
2. `service-auth` — Puerto `8091`
3. `service-comprobante` — Puerto `8090`
4. `service-usuario` — Puerto `8082`
5. `service-direccion` — Puerto `8083`
6. `service-donacion` — Puerto `8084`
7. `service-mascota` — Puerto `8085`
8. `service-eventos` — Puerto `8086`
9. `service-inventario` — Puerto `8087`
10. `service-notificaciones` — Puerto `8088`
11. `service-sucursal` — Puerto `8089`

---

##  Instalación y Ejecución

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

Ejecuta cada servicio en su carpeta correspondiente usando Maven. Algunos servicios pueden iniciarse en paralelo, pero el `API Gateway` debe ejecutarse una vez que los microservicios estén disponibles.

```bash
cd backend/service-auth
mvn spring-boot:run
```

```bash
cd ../service-comprobante
mvn spring-boot:run
```

```bash
cd ../service-usuario
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
cd ../service-mascota
mvn spring-boot:run
```

```bash
cd ../service-eventos
mvn spring-boot:run
```

```bash
cd ../service-inventario
mvn spring-boot:run
```

```bash
cd ../service-notificaciones
mvn spring-boot:run
```

```bash
cd ../service-sucursal
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

### Service Auth (`http://localhost:8091`)

- `POST /auth/login`
- `POST /auth/register`

### Service Comprobante (`http://localhost:8090`)

- `POST /tickets`
- `GET /tickets`
- `GET /tickets/{id}`
- `PUT /tickets/{id}`
- `DELETE /tickets/{id}`
- `GET /tickets/donante/{donId}`
- `GET /tickets/voluntario/{volId}`

### Service Usuario (`http://localhost:8082`)

- `POST /roles`
- `GET /roles`
- `GET /roles/{id}`
- `PUT /roles/{id}`
- `DELETE /roles/{id}`

- `POST /usuarios`
- `GET /usuarios`
- `GET /usuarios/{id}`
- `GET /usuarios/rut/{rut}`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

- `POST /usuariorol`
- `GET /usuariorol`
- `GET /usuariorol/rol/{rolId}`
- `DELETE /usuariorol/{id}`

### Service Direccion (`http://localhost:8083`)

- `POST /comunas`
- `GET /comunas`
- `GET /comunas/{id}`
- `DELETE /comunas/{id}`

- `POST /direcciones`
- `GET /direcciones`
- `GET /direcciones/{id}`
- `DELETE /direcciones/{id}`

### Service Donacion (`http://localhost:8084`)

- `POST /insumos`
- `GET /insumos`
- `GET /insumos/{id}`
- `DELETE /insumos/{id}`

- `POST /donaciones`
- `GET /donaciones`
- `GET /donaciones/{id}`
- `PUT /donaciones/{id}`
- `DELETE /donaciones/{id}`
- `GET /donaciones/donante/{donId}`
- `GET /donaciones/insumo/{inId}`
- `GET /donaciones/campana/{cId}`

### Service Mascota (`http://localhost:8085`)

- `POST /mascotas`
- `GET /mascotas`
- `GET /mascotas/{id}`
- `DELETE /mascotas/{id}`

- `POST /adopciones`
- `GET /adopciones`
- `GET /adopciones/{id}`
- `PUT /adopciones/{id}`
- `DELETE /adopciones/{id}`
- `GET /adopciones/adoptante/{adId}`
- `GET /adopciones/voluntario/{voId}`

### Service Eventos (`http://localhost:8086`)

- `POST /campanias`
- `GET /campanias`
- `GET /campanias/{id}`
- `PUT /campanias/{id}`
- `DELETE /campanias/{id}`
- `GET /campanias/anio/{anio}`
- `GET /campanias/mes/{mes}`
- `GET /campanias/administrador/{aId}`

### Service Inventario (`http://localhost:8087`)

- `POST /inventarios`
- `GET /inventarios`
- `GET /inventarios/{id}`
- `GET /inventarios/insumo/{insumoId}`
- `PUT /inventarios/{id}`
- `DELETE /inventarios/{id}`

- `POST /movimientos`
- `POST /movimientos/donacion/{donacionId}`
- `GET /movimientos`
- `GET /movimientos/{id}`
- `DELETE /movimientos/{id}`
- `GET /movimientos/inventario/{inventarioId}`
- `GET /movimientos/tipo/{tipo}`
- `GET /movimientos/donacion/{donacionId}`

### Service Notificaciones (`http://localhost:8088`)

- `POST /notificaciones`
- `GET /notificaciones`
- `GET /notificaciones/{id}`
- `PUT /notificaciones/{id}`
- `PUT /notificaciones/{id}/leida`
- `DELETE /notificaciones/{id}`
- `GET /notificaciones/usuario/{usuarioId}`
- `GET /notificaciones/estado/{leida}`

### Service Sucursal (`http://localhost:8089`)

- `POST /sucursales`
- `GET /sucursales`
- `GET /sucursales/{id}`
- `PUT /sucursales/{id}`
- `DELETE /sucursales/{id}`
- `GET /sucursales/direccion/{direccionId}`

---

<p align="center">
  Sistema de Microservicios Kiwi © 2026
</p>
