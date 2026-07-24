# MediTrack — Servicio Reactivo de Citas Médicas

Proyecto académico de Programación Avanzada desarrollado con Spring WebFlux, Project Reactor, JUnit 4 y StepVerifier.

Realizado por: Alex Canchignia
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
