# MediTrack — Servicio Reactivo de Citas Médicas

Proyecto académico de Programación Avanzada desarrollado con Spring WebFlux, Project Reactor, JUnit 4 y StepVerifier.

## Funcionalidades

- Modelo `Appointment` completamente inmutable.
- Copias defensivas de `notifyEmails` en constructor y getter.
- Flujo no bloqueante con `Flux` y `Mono`.
- Uso comentado de `filter`, `map`, `defaultIfEmpty` y `switchIfEmpty`.
- API REST reactiva con dos endpoints.
- Pruebas unitarias con patrón AAA y nombres descriptivos.
- Historial Git con una rama por actividad.

## Requisitos

- Java 17 o superior.
- Maven 3.6 o superior.
- Git.

Verifica las instalaciones:

```bash
java -version
mvn -version
git --version
```

## Ejecutar las pruebas

Desde la carpeta raíz del proyecto:

```bash
mvn clean test
```

El resultado esperado debe indicar cero fallos y cero errores:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación se inicia en:

```text
http://localhost:8080
```

## Probar los endpoints con curl

En CMD, Git Bash o una terminal Linux:

```bash
curl http://localhost:8080/api/appointments
curl http://localhost:8080/api/appointments/A1
```

En Windows PowerShell usa `curl.exe` para ejecutar el programa real:

```powershell
curl.exe http://localhost:8080/api/appointments
curl.exe http://localhost:8080/api/appointments/A1
```

## Ramas incluidas

```text
main
feature/modelo
feature/servicio-reactivo
feature/api-rest
feature/pruebas
```

Comprueba el historial:

```bash
git branch --all
git log --oneline --graph --decorate --all
```

## Subir a GitHub sin perder las ramas

1. Crea un repositorio público vacío en GitHub. No agregues README, `.gitignore` ni licencia desde la web.
2. Dentro de esta carpeta ejecuta:

```bash
git remote add origin https://github.com/TU_USUARIO/meditrack.git
git push -u origin main
git push origin feature/modelo
git push origin feature/servicio-reactivo
git push origin feature/api-rest
git push origin feature/pruebas
```

No ejecutes `git init`, porque este archivo comprimido ya incluye el historial y las ramas.

## Estructura principal

```text
src/main/java/com/meditrack/
├── MediTrackApplication.java
├── controller/AppointmentController.java
├── exception/AppointmentNotFoundException.java
├── model/Appointment.java
└── service/AppointmentService.java

src/test/java/com/meditrack/
├── model/AppointmentTest.java
└── service/AppointmentServiceTest.java
```

Consulta `docs/GUIA_EVIDENCIAS.md` antes de preparar el PDF para Moodle.
