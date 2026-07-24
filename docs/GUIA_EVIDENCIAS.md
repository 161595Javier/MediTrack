# Guía para el PDF de evidencias

## Portada sugerida

- Asignatura: Programación Avanzada
- Tarea: MediTrack — Servicio Reactivo de Citas Médicas con Pruebas Unitarias
- Estudiante: [NOMBRE COMPLETO]
- Docente: [NOMBRE DEL DOCENTE]
- Fecha: [FECHA DE ENTREGA]
- Repositorio: [ENLACE PÚBLICO DE GITHUB]

## Evidencia 1 — Modelo inmutable

Captura completa o en varias partes legibles de:

```text
src/main/java/com/meditrack/model/Appointment.java
```

La captura debe mostrar:

- `public final class Appointment`.
- Atributos `private final`.
- Ausencia de setters.
- Copia defensiva en el constructor.
- Copia defensiva y lista de solo lectura en `getNotifyEmails()`.

## Evidencia 2 — Servicio reactivo

Captura de:

```text
src/main/java/com/meditrack/service/AppointmentService.java
```

Debe ser visible:

- Las cinco citas creadas con `Flux.just`.
- Tres citas válidas y dos inválidas.
- Los operadores `filter`, `map` y `defaultIfEmpty`.
- Los comentarios que justifican cada operador.
- `findById` con `next` y `switchIfEmpty`.
- Ausencia de `block()`.

## Evidencia 3 — Controlador

Aunque el enunciado no lo enumera entre las capturas de código obligatorias, conviene incluir:

```text
src/main/java/com/meditrack/controller/AppointmentController.java
```

Deben verse los retornos `Flux<Appointment>` y `Mono<Appointment>`.

## Evidencia 4 — Pruebas del modelo

Captura de:

```text
src/test/java/com/meditrack/model/AppointmentTest.java
```

Debe mostrar:

- Patrón Arrange–Act–Assert.
- `assertEquals` para getters.
- Modificación de la lista original.
- Verificación del tamaño interno.
- `assertNotSame`.
- Verificación de lista de solo lectura.

## Evidencia 5 — Pruebas del servicio

Captura de:

```text
src/test/java/com/meditrack/service/AppointmentServiceTest.java
```

Debe mostrar:

- `expectNextCount(3).verifyComplete()`.
- Caso con todas las citas inválidas y emisión de `DEFAULT`.
- Error de `findById` con ID inexistente.
- Uso de `StepVerifier`.

## Evidencia 6 — Pruebas exitosas

Ejecuta:

```bash
mvn clean test
```

Captura la terminal mostrando conjuntamente:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

No recortes la captura de modo que se pierda el comando ejecutado.

## Evidencia 7 — Endpoint de listado

Con la aplicación ejecutándose, abre otra terminal y ejecuta:

```bash
curl http://localhost:8080/api/appointments
```

En PowerShell:

```powershell
curl.exe http://localhost:8080/api/appointments
```

La respuesta debe contener exactamente tres citas y las especialidades en mayúsculas.

## Evidencia 8 — Endpoint por ID

Ejecuta:

```bash
curl http://localhost:8080/api/appointments/A1
```

En PowerShell:

```powershell
curl.exe http://localhost:8080/api/appointments/A1
```

La respuesta debe corresponder a Ana Torres y a la especialidad `CARDIOLOGÍA`.

## Evidencia 9 — Historial Git

Es recomendable agregar capturas de:

```bash
git branch --all
git log --oneline --graph --decorate --all
```

Así se demuestra el uso de las cuatro ramas y los commits semánticos.

## Orden sugerido del PDF

1. Portada.
2. Enlace público del repositorio.
3. Modelo inmutable.
4. Servicio reactivo.
5. Controlador.
6. Pruebas del modelo.
7. Pruebas del servicio.
8. Resultado de `mvn clean test`.
9. Resultado del endpoint de listado.
10. Resultado del endpoint por ID.
11. Historial de ramas y commits.
