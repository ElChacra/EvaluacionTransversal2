
---

# 📝 Microservicios PerfulandiaSPA

## 🚀 Descripción General

**PerfulandiaSPA** es un sistema distribuido compuesto por **cuatro microservicios** independientes, desarrollados en **Java 17** con **Spring Boot 3.4.5**.
Cada microservicio gestiona una parte central del dominio:

* **UsuarioService:** Gestión de usuarios (CRUD, overview de usuario, integración con pedidos y notificaciones).
* **ProductoService:** Gestión y catálogo de productos.
* **PedidoService:** Manejo de pedidos y sus estados.
* **NotificacionService:** Registro y consulta de notificaciones asociadas a pedidos/usuarios.

Cada uno implementa endpoints REST, pruebas unitarias, documentación Swagger/OpenAPI y, donde corresponde, HATEOAS.

---

## 🛠️ Tecnologías y Dependencias

| Tecnología                   | Versión | Uso Principal                               |
| ---------------------------- | ------- | ------------------------------------------- |
| Java                         | 17      | Lenguaje base                               |
| Spring Boot                  | 3.4.5   | Framework principal                         |
| spring-boot-starter-web      |         | REST API, RestTemplate                      |
| spring-boot-starter-data-jpa |         | JPA / Hibernate                             |
| springdoc-openapi-ui         |         | Documentación Swagger/OpenAPI               |
| spring-boot-starter-hateoas  |         | HATEOAS (enlaces hipermedia REST)           |
| spring-boot-devtools         |         | Recarga en caliente                         |
| MySQL Connector/J            | 8.x     | Conexión a MySQL                            |
| Lombok                       | 1.18.28 | Generación de getters/setters/constructores |
| Maven                        | 3.6+    | Gestión de dependencias y build             |
| JUnit 5 + Mockito            |         | Pruebas unitarias y mockeo                  |

---

## 📁 Estructura del Proyecto

Cada microservicio tiene una estructura similar, por ejemplo para `usuarioservice`:

```text
usuarioservice/
├─ src/main/java/com/perfulandia/usuarioservice
│  ├─ config
│  │   └─ AppConfig.java
│  ├─ controller
│  ├─ dto
│  ├─ model
│  ├─ repository
│  ├─ service
│  └─ assembler (si aplica, para HATEOAS)
├─ src/test/java/com/perfulandia/usuarioservice
├─ src/main/resources
│  └─ application.properties
└─ pom.xml
```

> Repite la misma lógica para `productservice`, `pedidoservice`, `notificacionservice`.

---

## ⚙️ Configuración Local

### 1. Prerrequisitos

* Java JDK 17+ y Maven 3.6+ instalados.
* MySQL con las bases de datos correspondientes creadas (ej: `perfulandia_usuarios_01v`, `perfulandia_productos_01v`, etc).

### 2. Configuración de cada microservicio

Ejemplo para `usuarioservice/src/main/resources/application.properties`:

```properties
spring.application.name=usuarioservice
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/perfulandia_usuarios_01v
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

> Ajusta el puerto y base de datos según cada servicio.

### 3. Comandos para levantar los microservicios

```bash
# Clonar el repositorio general y moverse a la carpeta de cada servicio:
git clone https://github.com/ElChacra/EvaluacionTransversal2.git

# Usuarios
cd EvaluacionTransversal2/usuarioservice
mvn spring-boot:run

# Productos
cd ../productservice
mvn spring-boot:run

# Pedidos
cd ../pedidoservice
mvn spring-boot:run

# Notificaciones
cd ../notificacionservice
mvn spring-boot:run
```

---

## 🔗 Puertos y Endpoints para Testeo

| Microservicio       | Puerto | Ejemplo de Endpoint                                                 | Swagger/OpenAPI                                     |
| ------------------- | ------ | ------------------------------------------------------------------- | --------------------------------------------------- |
| UsuarioService      | 8081   | [GET /api/usuarios](http://localhost:8081/api/usuarios)             | [Swagger UI](http://localhost:8081/swagger-ui.html) |
| ProductoService     | 8080   | [GET /api/productos](http://localhost:8080/api/productos)           | [Swagger UI](http://localhost:8080/swagger-ui.html) |
| PedidoService       | 8082   | [GET /api/pedidos](http://localhost:8082/api/pedidos)               | [Swagger UI](http://localhost:8082/swagger-ui.html) |
| NotificacionService | 8083   | [GET /api/notificaciones](http://localhost:8083/api/notificaciones) | [Swagger UI](http://localhost:8083/swagger-ui.html) |

**Ejemplo rápido para testear:**

```bash
curl http://localhost:8081/api/usuarios
curl http://localhost:8080/api/productos
curl http://localhost:8082/api/pedidos
curl http://localhost:8083/api/notificaciones
```

---

## 📋 Endpoints Destacados

* **UsuarioService**:
  `/api/usuarios`, `/api/usuarios/{id}`, `/api/usuarios/{id}/overview` (integración con pedidos y notificaciones, con HATEOAS y Swagger).
* **ProductoService**:
  `/api/productos`, `/api/productos/{id}`.
* **PedidoService**:
  `/api/pedidos`, `/api/pedidos/{id}`, `/api/pedidos/usuario/{userId}`.
* **NotificacionService**:
  `/api/notificaciones`, `/api/notificaciones/{id}`, `/api/notificaciones/usuario/{userId}`.

---

## 🧪 Pruebas y Confirmaciones

### **JUnit + Mockito**

* Ejecuta las pruebas unitarias para cada microservicio:

  ```bash
  mvn test
  ```
* Confirmar:

  * Cada microservicio tiene tests (`src/test/java/...`).
  * Se usa `@Mock`, `@InjectMocks`, `when(...)`, `verify(...)` (ver ejemplos en cada carpeta `*ServiceTest.java`).

### **Swagger / OpenAPI**

* Abre Swagger UI en el puerto de cada microservicio (ver tabla arriba).
* Verifica la documentación de endpoints, la presencia de anotaciones y prueba desde la interfaz.

### **HATEOAS**

* Presente en UsuarioService y donde corresponda.
* Cada GET debe incluir `_links` en la respuesta JSON.
* Revisa que se use `EntityModel`, `CollectionModel`, assembler y métodos con `linkTo(methodOn(...))`.

---

## 🚦 Formas de Testeo Rápido

* Navegador/curl:
  [http://localhost:8081/api/usuarios](http://localhost:8081/api/usuarios)
  [http://localhost:8080/api/productos](http://localhost:8080/api/productos)
  [http://localhost:8082/api/pedidos](http://localhost:8082/api/pedidos)
  [http://localhost:8083/api/notificaciones](http://localhost:8083/api/notificaciones)

* **Swagger UI:**
  Navega a `/swagger-ui.html` en cada microservicio.

* **Postman:**
  Importa los endpoints y prueba todos los métodos HTTP, verifica estructura JSON y HATEOAS.

---

## ✅ Requerimientos cumplidos para Evaluación Parcial 3

* [x] **Pruebas Unitarias (JUnit + Mockito):**
  Todos los microservicios cuentan con pruebas unitarias, usando mocks, inyección y verificación de lógica de negocio.

* [x] **Documentación Swagger/OpenAPI:**
  Cada servicio expone su documentación Swagger en `/swagger-ui.html` y utiliza anotaciones OpenAPI.

* [x] **HATEOAS:**
  Implementado donde corresponde (por ejemplo en UsuarioService) usando `EntityModel`, `CollectionModel` y assembler.

* [x] **Integración de Microservicios:**
  Los servicios interactúan entre sí donde es necesario (por ejemplo, overview de usuario consume Pedidos y Notificaciones).

* [x] **Pruebas de endpoints y lógica:**
  Verificado funcionamiento desde navegador, Swagger, Postman y línea de comandos.

---

## 🌟 Buenas Prácticas

* Manejo de errores/excepciones y códigos HTTP claros.
* Externalización de configuraciones.
* Logging de eventos relevantes.
* Documentación y comentarios en código.
* Estructura modular y desacoplada.

---

## 📚 Recursos útiles

* [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring HATEOAS](https://spring.io/guides/gs/rest-hateoas/)
* [Springdoc OpenAPI](https://springdoc.org/)
* [Mockito](https://site.mockito.org/)
* [Lombok](https://projectlombok.org/)




---

### 🔄 Cambiar entre ramas de desarrollo

```bash
# Cambiar a la rama de Swagger/OpenAPI
git checkout features/swagger

# Cambiar a la rama de HATEOAS
git checkout features/hateoas
```

---


## 📌 Proyecto académico

* **Evaluación Transversal 2, Desarrollo FullStack I, Duoc UC**
* **Docente:** Marcelo-Crisostomo ([GitHub](https://github.com/Marcelo-Crisostomo))
* **Equipo:** Manuel ([ManuelADMN](https://github.com/ManuelADMN)), ElChacra ([ElChacra](https://github.com/ElChacra)), Nelson Oyarzo ([NelsonOyarzo](https://github.com/NelsonOyarzo))

---
