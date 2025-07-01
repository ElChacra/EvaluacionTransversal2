
---

# 📝 Microservicios PerfulandiaSPA – UsuarioService

## 🚀 Descripción General

`UsuarioService` es un microservicio construido con **Java 17** y **Spring Boot 3.4.5**, encargado de la gestión de usuarios en Perfulandia SPA.
Incluye CRUD, integración con microservicios de Pedidos y Notificaciones, y un endpoint `/overview` para obtener el resumen combinado de datos del usuario.

---

## 🛠️ Tecnologías y Dependencias

| Tecnología                   | Versión | Uso Principal                               |
| ---------------------------- | ------- | ------------------------------------------- |
| Java                         | 17      | Lenguaje base                               |
| Spring Boot                  | 3.4.5   | Framework principal                         |
| spring-boot-starter-web      |         | REST API, RestTemplate                      |
| spring-boot-starter-data-jpa |         | JPA / Hibernate                             |
| spring-boot-devtools         |         | Recarga en caliente                         |
| springdoc-openapi-ui         |         | Documentación Swagger/OpenAPI               |
| spring-boot-starter-hateoas  |         | HATEOAS (enlaces hipermedia REST)           |
| MySQL Connector/J            | 8.x     | Conexión a MySQL                            |
| Lombok                       | 1.18.28 | Generación de getters/setters/constructores |
| Maven                        | 3.6+    | Gestión de dependencias y build             |
| JUnit 5 + Mockito            |         | Pruebas unitarias y mockeo                  |

---

## 📁 Estructura del Proyecto

```text
usuarioservice/
├─ src/main/java/com/perfulandia/usuarioservice
│  ├─ config
│  │   └─ AppConfig.java             // Bean RestTemplate
│  ├─ controller
│  │   └─ UsuarioController.java     // Endpoints CRUD + /overview + HATEOAS
│  ├─ dto
│  │   ├─ PedidoDTO.java             // Mapea PedidoService
│  │   ├─ NotificacionDTO.java       // Mapea NotificacionService
│  │   └─ UserOverviewDTO.java       // Agrega listas y conteos
│  ├─ model
│  │   └─ Usuario.java               // Entidad JPA usuario
│  ├─ repository
│  │   └─ UsuarioRepository.java     // Spring Data JPA
│  ├─ service
│  │   ├─ UsuarioService.java        // Lógica CRUD usuario
│  │   └─ UsuarioAggregationService.java // Lógica /overview
│  └─ assembler
│      └─ UsuarioModelAssembler.java // Assembler para HATEOAS
├─ src/test/java/com/perfulandia/usuarioservice
│  └─ UsuarioServiceTest.java        // Pruebas unitarias JUnit+Mockito
├─ src/main/resources
│  └─ application.properties         // Configuración de puerto y BD
└─ pom.xml                           // Dependencias Maven
```

---

## ⚙️ Configuración Local

### 1. Prerrequisitos

* Java JDK 17+ configurado.
* Maven 3.6+.
* MySQL con la base de datos `perfulandia_usuarios_01v` creada.

### 2. `application.properties` ejemplo

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

> ⚠️ Si usas contraseña para root, colócala en `spring.datasource.password`.

### 3. Comandos para clonar y levantar

```bash
git clone https://github.com/ElChacra/EvaluacionTransversal2.git
cd EvaluacionTransversal2/usuarioservice
git checkout -b feature/overview

mvn clean install
mvn spring-boot:run
```

---

## 🔗 Integración con Otros Microservicios

| Servicio            | Puerto | Endpoint                                   |
| ------------------- | ------ | ------------------------------------------ |
| PedidoService       | 8082   | GET `/api/pedidos/usuario/{userId}`        |
| NotificacionService | 8083   | GET `/api/notificaciones/usuario/{userId}` |

La integración se realiza con `RestTemplate`, inyectado vía `AppConfig.java`.

---

## 📋 Endpoints Principales

<table>
  <tr><th>HTTP</th><th>Ruta</th><th>Descripción</th></tr>
  <tr><td>GET</td><td><code>/api/usuarios</code></td><td>Listar usuarios</td></tr>
  <tr><td>POST</td><td><code>/api/usuarios</code></td><td>Crear usuario</td></tr>
  <tr><td>GET</td><td><code>/api/usuarios/{id}</code></td><td>Obtener usuario por ID</td></tr>
  <tr><td>DELETE</td><td><code>/api/usuarios/{id}</code></td><td>Eliminar usuario por ID</td></tr>
  <tr><td>GET</td><td><code>/api/usuarios/{id}/overview</code></td><td>Pedidos, notificaciones y conteos</td></tr>
</table>

**Ejemplo:**

```bash
curl -X GET http://localhost:8081/api/usuarios/1/overview
```

---

## 🧪 Pruebas y Confirmaciones

### **JUnit + Mockito**

* Ejecuta las pruebas unitarias:

  ```bash
  mvn test
  ```
* Confirma que tienes:

  * Al menos una clase de prueba por servicio.
  * Uso de `@Mock`, `@InjectMocks`, `when(...)`, `verify(...)` en los tests.
    Ejemplo (ver `UsuarioServiceTest.java`):

    ```java
    @Mock
    UsuarioRepository usuarioRepository;
    @InjectMocks
    UsuarioService usuarioService;
    @Test
    void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));
        Usuario usuario = usuarioService.findById(1L);
        verify(usuarioRepository).findById(1L);
        // Asserts...
    }
    ```

### **Swagger / OpenAPI**

* Verifica la doc de endpoints en:
  [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
* Confirmar presencia de:

  * Dependencia `springdoc-openapi-ui` en `pom.xml`.
  * Anotaciones `@Operation`, `@ApiResponse`, `@Tag` en tus controladores.
* Puedes testear todos los endpoints directamente desde la interfaz Swagger.

### **HATEOAS**

* Cada recurso de usuario debe venir con sus enlaces (`_links`) al hacer GET.
* Ejemplo de respuesta:

  ```json
  {
    "id": 1,
    "nombre": "admin",
    ...
    "_links": {
      "self": {"href": "http://localhost:8081/api/usuarios/1"},
      "overview": {"href": "http://localhost:8081/api/usuarios/1/overview"}
    }
  }
  ```
* Confirma que usas:

  * `EntityModel` y `CollectionModel` en los controladores.
  * Un `UsuarioModelAssembler` con métodos que agregan links (usando `linkTo(methodOn(...))`).

---

## 🚦 Formas de Testeo Rápido

### **Desde el navegador o curl**

* [http://localhost:8081/api/usuarios](http://localhost:8081/api/usuarios)
* [http://localhost:8081/api/usuarios/1](http://localhost:8081/api/usuarios/1)
* [http://localhost:8081/api/usuarios/1/overview](http://localhost:8081/api/usuarios/1/overview)

### **Swagger UI**

* [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### **Postman**

* Crea una colección con los endpoints de arriba.
* Prueba métodos POST/PUT/DELETE y verifica respuestas HATEOAS en JSON.

---

## 🌟 Buenas Prácticas

* Manejar excepciones y errores de red.
* Externalizar endpoints de otros microservicios.
* Usar logs descriptivos.
* Probar integración con los otros servicios levantados (`PedidoService`, `NotificacionService`).

---

## 📚 Recursos útiles

* [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Spring HATEOAS](https://spring.io/guides/gs/rest-hateoas/)
* [Springdoc OpenAPI](https://springdoc.org/)
* [Mockito](https://site.mockito.org/)
* [Lombok](https://projectlombok.org/)


---

## ✅ Requerimientos cumplidos para Evaluación Parcial 3

* [x] **Pruebas Unitarias (JUnit + Mockito):**
  Todos los servicios principales cuentan con pruebas unitarias.
  Se implementan mocks (`@Mock`), inyecciones de dependencias (`@InjectMocks`), y validaciones con `when()` y `verify()` para simular y comprobar la lógica de negocio.

* [x] **Documentación Swagger/OpenAPI:**
  El microservicio expone documentación automática de la API en
  [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
  con anotaciones como `@Operation`, `@ApiResponse` y `@Tag`.

* [x] **HATEOAS:**
  Todos los endpoints de usuario responden con enlaces auto-descriptivos (`self`, `overview`, etc.) utilizando `EntityModel`, `CollectionModel` y un assembler dedicado.

* [x] **Integración de Microservicios:**
  `UsuarioService` consume de manera síncrona los microservicios de **Pedidos** y **Notificaciones**, y expone el endpoint `/api/usuarios/{id}/overview` que integra información de ambos servicios.

* [x] **Pruebas de endpoints y lógica:**
  Se ha verificado la funcionalidad usando Swagger UI, Postman y curl.
  Los endpoints principales, respuestas HATEOAS, y la integración externa funcionan correctamente.

---

**Notas:**

* Si algún ítem requiere confirmación visual, se pueden adjuntar capturas de pantalla del Swagger UI, consola de test o ejemplos de respuesta en la entrega final.
* El detalle de implementación de cada requerimiento se encuentra documentado en este mismo README y en los comentarios del código fuente.

---

---

## 📌 Proyecto académico

* **Evaluación Transversal 2, Desarrollo FullStack I, Duoc UC**
* **Docente:** Marcelo-Crisostomo ([GitHub](https://github.com/Marcelo-Crisostomo))
* **Equipo:** Manuel ([ManuelADMN](https://github.com/ManuelADMN)), ElChacra ([ElChacra](https://github.com/ElChacra)), Nelson Oyarzo ([NelsonOyarzo](https://github.com/NelsonOyarzo))

---

