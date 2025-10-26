# AppPokemon

API REST sencilla para administrar **Pokémon** con CRUD completo, documentada con **OpenAPI/Swagger** y persistencia en **PostgreSQL**.

> Proyecto basado en Spring Boot `3.5.7`, Java `17`, Spring Data JPA y Springdoc OpenAPI (`2.7.0`).

---

## 🧱 Tecnologías

- Java 17
- Spring Boot 3.5.7
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
- PostgreSQL (Driver: `org.postgresql`)
- Lombok
- Springdoc OpenAPI 3 (Swagger UI)

Estructura principal:

```
src/main/java/com/example/AppPokemon
├── AppPokemonApplication.java
├── config
│   └── OpenApiConfig.java
├── controller
│   └── PokemonController.java
├── entity
│   └── Pokemon.java
├── repository
│   └── PokemonRepository.java
└── service
    ├── PokemonService.java
    └── PokemonServiceImpl.java
```

---

## ⚙️ Requisitos

- **JDK 17+**
- **Maven 3.9+**
- **PostgreSQL 14+** (o Docker)
- (Opcional) **Docker & Docker Compose**

---

## 🗄️ Base de datos

Valores por defecto en `src/main/resources/application.properties`:

```properties
spring.application.name=AppPokemon

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/poke_db
spring.datasource.username=postgres
spring.datasource.password=root
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger / OpenAPI
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
springdoc.default-produces-media-type=application/json
springdoc.writer-with-default-pretty-printer=true
```

> Cambia `username` y `password` según tu entorno. Con `ddl-auto=update` las tablas se crean/actualizan automáticamente.

### Crear BD rápidamente (PostgreSQL local)
```sql
CREATE DATABASE poke_db;
-- Opcional: crear usuario dedicado
-- CREATE USER app_poke WITH PASSWORD 'tu_password';
-- GRANT ALL PRIVILEGES ON DATABASE poke_db TO app_poke;
```

### Alternativa rápida con Docker (PostgreSQL)
```bash
docker run --name postgres-poke -e POSTGRES_PASSWORD=root -e POSTGRES_DB=poke_db -p 5432:5432 -d postgres:15
```
Ajusta `application.properties` si usas otro usuario/puerto.

---

## ▶️ Ejecución

Clona el repo y ejecuta:

```bash
mvn spring-boot:run
# o
mvn clean package && java -jar target/AppPokemon-0.0.1-SNAPSHOT.jar
```

Por defecto la API queda en: `http://localhost:8080`

---

## 📘 Documentación (Swagger)

- UI: `http://localhost:8080/swagger-ui.html`
- JSON: `http://localhost:8080/v3/api-docs`

La configuración base está en `config/OpenApiConfig.java` (título, descripción, contacto y docs externas).

---

## 📦 Entidad principal

`Pokemon` → tabla `tbl_pokemons`

```java
Long   pokemonId  // ID autogenerado
String nombre     // p.ej. "Charizard"
String tipo       // p.ej. "Fuego"
int    ataque
int    defensa
```

---

## 🔗 Endpoints principales

`@RequestMapping("/api/pokemon")`

> Nota: Los ejemplos muestran el “happy path”. Usa los códigos de estado adecuados desde Swagger para ver todas las respuestas esperadas.

### 1) Listar todos
```
GET /api/pokemon/getAllPokemons
```
**cURL**
```bash
curl -X GET http://localhost:8080/api/pokemon/getAllPokemons
```

### 2) Obtener por ID
```
GET /api/pokemon/getPokemon/{pokemonId}
```
**cURL**
```bash
curl -X GET http://localhost:8080/api/pokemon/getPokemon/1
```

### 3) Crear
```
POST /api/pokemon/savePokemon
Content-Type: application/json
```
**Body**
```json
{
  "nombre": "Charizard",
  "tipo": "Fuego",
  "ataque": 534,
  "defensa": 408
}
```
**cURL**
```bash
curl -X POST http://localhost:8080/api/pokemon/savePokemon   -H "Content-Type: application/json"   -d '{"nombre":"Charizard","tipo":"Fuego","ataque":534,"defensa":408}'
```

### 4) Actualizar
```
PUT /api/pokemon/updatePokemon/{pokemonId}
Content-Type: application/json
```
**Body**
```json
{
  "nombre": "Charizard",
  "tipo": "Fuego/Volador",
  "ataque": 545,
  "defensa": 420
}
```
**cURL**
```bash
curl -X PUT http://localhost:8080/api/pokemon/updatePokemon/1   -H "Content-Type: application/json"   -d '{"nombre":"Charizard","tipo":"Fuego/Volador","ataque":545,"defensa":420}'
```

### 5) Eliminar
```
DELETE /api/pokemon/deletePokemon/{pokemonId}
```
**cURL**
```bash
curl -X DELETE http://localhost:8080/api/pokemon/deletePokemon/1
```

---

## 🧩 Notas de implementación

- `PokemonRepository` extiende `JpaRepository<Pokemon, Long>` y expone un `@Query` simple para listar.
- `PokemonService` define operaciones CRUD; `PokemonServiceImpl` implementa el flujo y usa `orElseThrow()` para `findById`.
- `OpenApiConfig` define metadatos de la API (título, contacto, licencia y documentación externa).

---

## 🧪 Pruebas rápidas (Postman/HTTP files)

Puedes importar `http` snippets en tu IDE o usar Postman:

```http
### Listar
GET http://localhost:8080/api/pokemon/getAllPokemons

### Crear
POST http://localhost:8080/api/pokemon/savePokemon
Content-Type: application/json

{
  "nombre": "Pikachu",
  "tipo": "Eléctrico",
  "ataque": 320,
  "defensa": 200
}

### Obtener
GET http://localhost:8080/api/pokemon/getPokemon/1

### Actualizar
PUT http://localhost:8080/api/pokemon/updatePokemon/1
Content-Type: application/json

{
  "nombre": "Pikachu",
  "tipo": "Eléctrico",
  "ataque": 330,
  "defensa": 210
}

### Eliminar
DELETE http://localhost:8080/api/pokemon/deletePokemon/1
```

---

## 🐳 (Opcional) Empaquetar con Docker

**Dockerfile** mínimo (ajusta la versión del JAR según tu build):

```dockerfile
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/AppPokemon-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
```

**docker-compose.yml** (API + PostgreSQL):

```yaml
services:
  db:
    image: postgres:15
    container_name: postgres-poke
    environment:
      POSTGRES_PASSWORD: root
      POSTGRES_DB: poke_db
      POSTGRES_USER: postgres
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

  api:
    build: .
    container_name: app-pokemon
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/poke_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: root
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    ports:
      - "8080:8080"

volumes:
  pgdata:
```

Levantar:

```bash
mvn clean package -DskipTests
docker compose up --build
```

---

## 🛠️ Troubleshooting

- **No se crea la tabla**: verifica la conexión a DB, credenciales y que `ddl-auto=update` esté activo.
- **Error de puerto**: si `8080` está ocupado, arranca con `--server.port=8081` o añade `server.port=8081` en `application.properties`.
- **Lombok**: si el IDE marca errores de getters/setters, instala el plugin de Lombok en tu IDE y habilita la opción *Annotation processing*.

---

## 📄 Licencia

MIT — úsalo libremente con atribución mínima.

---

## 👤 Autor / Contacto

- Luis Felipe González (fg180899@gmail.com) — *Equipo AppPokemon*
