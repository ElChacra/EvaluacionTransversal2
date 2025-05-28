

📝 **Proyecto: Microservicios para PerfulandiaSPA**

---

### 🚀 Descripción General

`UsuarioService` es un microservicio construido con **Java 17** y **Spring Boot 3.4.5**, que gestiona los datos de usuarios en el ecosistema de Perfulandia SPA. Su funcionalidad avanzada consiste en:

1. **CRUD de usuarios** (crear, leer, actualizar, eliminar).
2. **Integración** con dos microservicios externos:

   * **`PedidoService`**: para obtener los pedidos de un usuario.
   * **`NotificacionService`**: para obtener las notificaciones vinculadas a esos pedidos.
3. **Endpoint `/overview`**: reúne, en un único JSON, la lista de pedidos, la lista de notificaciones y sus respectivos conteos.

---

## 🛠️ Tecnologías y Dependencias

| Tecnología                   | Versión | Uso Principal                               |
| ---------------------------- | ------- | ------------------------------------------- |
| Java                         | 17      | Lenguaje base                               |
| Spring Boot                  | 3.4.5   | Framework principal                         |
| spring-boot-starter-web      |         | REST API, RestTemplate                      |
| spring-boot-starter-data-jpa |         | JPA / Hibernate                             |
| spring-boot-devtools         |         | Recarga en caliente                         |
| MySQL Connector/J            | 8.x     | Conexión a MySQL                            |
| Lombok                       | 1.18.28 | Generación de getters/setters/constructores |
| Maven                        | 3.6+    | Gestión de dependencias y build             |

---

## 📁 Estructura del Proyecto

```text
usuarioservice/
├─ src/main/java/com/perfulandia/usuarioservice
│  ├─ config
│  │   └─ AppConfig.java             // Bean RestTemplate
│  ├─ controller
│  │   └─ UsuarioController.java     // Endpoints CRUD + /overview
│  ├─ dto
│  │   ├─ PedidoDTO.java             // Mapea PedidoService
│  │   ├─ NotificacionDTO.java       // Mapea NotificacionService
│  │   └─ UserOverviewDTO.java       // Agrega listas y conteos
│  ├─ model
│  │   └─ Usuario.java               // Entidad JPA usuario
│  ├─ repository
│  │   └─ UsuarioRepository.java     // Spring Data JPA
│  └─ service
│      ├─ UsuarioService.java       // Lógica CRUD usuario
│      └─ UsuarioAggregationService.java // Lógica /overview
├─ src/main/resources
│  └─ application.properties         // Configuración de puerto y BD
└─ pom.xml                           // Dependencias Maven
```

---

## ⚙️ Configuración Local

### 1. Prerrequisitos

* **Java JDK 17+** instalado y configurado en `PATH`.
* **Maven 3.6+** (viene integrado en IntelliJ).
* **MySQL** con base de datos `perfulandia_usuarios_01v` creada.

### 2. `application.properties`

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

> 🔒 Si tu `root` tiene contraseña, colócala en `spring.datasource.password`.

### 3. Comandos Git & Maven

```bash
# Clonar el repo y entrar al módulo
git clone https://github.com/ElChacra/EvaluacionTransversal2.git
cd EvaluacionTransversal2/usuarioservice

# Crear y cambiar a tu branch
git checkout -b feature/overview

# Compilar y ejecutar
mvn clean install
mvn spring-boot:run
```

---

## 🔗 Integración con Microservicios

`UsuarioService` llama **síncronamente** a otros dos servicios con `RestTemplate`:

| Servicio            | Puerto | Endpoint                                   |
| ------------------- | ------ | ------------------------------------------ |
| PedidoService       | 8082   | GET `/api/pedidos/usuario/{userId}`        |
| NotificacionService | 8083   | GET `/api/notificaciones/usuario/{userId}` |

**Configuración RestTemplate** (en `AppConfig.java`):

```java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

**Lógica `/overview`** (`UsuarioAggregationService.java`):

```java
List<PedidoDTO> pedidos = Arrays.asList(
    restTemplate.getForObject(PEDIDOS_URL + userId, PedidoDTO[].class)
);
List<NotificacionDTO> notifs = Arrays.asList(
    restTemplate.getForObject(NOTIF_URL + userId, NotificacionDTO[].class)
);
return UserOverviewDTO.builder()
    .pedidos(pedidos)
    .notificaciones(notifs)
    .pedidosCount(pedidos.size())
    .notificacionesCount(notifs.size())
    .build();
```

---

## 📋 Endpoints Principales

<table>
  <tr><th>HTTP</th><th>Ruta</th><th>Descripción</th></tr>
  <tr><td>GET</td><td><code>/api/usuarios</code></td><td>Listar usuarios</td></tr>
  <tr><td>POST</td><td><code>/api/usuarios</code></td><td>Crear usuario</td></tr>
  <tr><td>GET</td><td><code>/api/usuarios/{id}</code></td><td>Obtener usuario por ID</td></tr>
  <tr><td>DELETE</td><td><code>/api/usuarios/{id}</code></td><td>Eliminar usuario por ID</td></tr>
  <tr><td>GET</td><td><code>/api/usuarios/{id}/overview</code></td><td>Listar pedidos, notificaciones y conteos</td></tr>
</table>

**Ejemplo**:

```bash
curl -X GET http://localhost:8081/api/usuarios/1/overview
```

Respuesta:

```json
{
  "pedidos": [/*...*/],
  "notificaciones": [/*...*/],
  "pedidosCount": 3,
  "notificacionesCount": 5
}
```

---

## 🌟 Buenas Prácticas

* Manejar excepciones de red (códigos HTTP, timeouts).
* Externalizar URLs con `@Value` o `application.yml`.
* Evaluar circuit breaker (Resilience4j) para tolerancia a fallos.
* Secuencia de logging clara para depuración.

---

## 📚 Referencias & Recursos

* 🔗 [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
* 🔗 [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* 🔗 [REST Template Guide](https://spring.io/guides/gs/consuming-rest/)
* 🔗 [Lombok](https://projectlombok.org/)

---

## 📌 Información del Proyecto

* **Proyecto realizado para:** Evaluación Transversal 2, Desarrollo FullStack I, Ingeniería Informática.
* **Docente:** Marcelo-Crisostomo (GitHub: [Marcelo-Crisostomo](https://github.com/Marcelo-Crisostomo))
* **Equipo de Desarrollo:**

  * Manuel (GitHub: [ManuelADMN](https://github.com/ManuelADMN))
  * ElChacra (GitHub: [ElChacra](https://github.com/ElChacra))
  * Nelson Oyarzo (GitHub: [NelsonOyarzo](https://github.com/NelsonOyarzo))

---
