package com.meditrack.service;

import com.meditrack.exception.AppointmentNotFoundException;
import com.meditrack.model.Appointment;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppointmentServiceTest {

    @Test
    public void getValidAppointments_cincoCitas_debeEmitirSoloLasTresValidas() {
        // Arrange
        AppointmentService service = new AppointmentService();

        // Act
        Flux<Appointment> flujo = service.getValidAppointments();

        // Assert
        StepVerifier.create(flujo)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void getValidAppointments_todasInvalidas_debeEmitirCitaGenerica() {
        // Arrange
        Flux<Appointment> citasInvalidas = Flux.just(
                new Appointment(
                        "I1",
                        "Paciente sin costo",
                        "Cardiología",
                        0.0,
                        List.of("paciente@email.com")
                ),
                new Appointment(
                        "I2",
                        "Paciente sin correo",
                        "Pediatría",
                        45.0,
                        Collections.emptyList()
                )
        );
        AppointmentService service = new AppointmentService(citasInvalidas);

        // Act
        Flux<Appointment> flujo = service.getValidAppointments();

        // Assert
        StepVerifier.create(flujo)
                .assertNext(appointment -> assertEquals("DEFAULT", appointment.getId()))
                .verifyComplete();
    }

    @Test
    public void findById_idInexistente_debeTerminarConError() {
        // Arrange
        AppointmentService service = new AppointmentService();

        // Act
        Mono<Appointment> resultado = service.findById("NO-EXISTE");

        // Assert
        StepVerifier.create(resultado)
                .verifyError(AppointmentNotFoundException.class);
    }

    @Test
    public void getValidAppointments_especialidadesOriginales_debeTransformarlasAMayusculas() {
        // Arrange
        AppointmentService service = new AppointmentService();

        // Act
        Flux<Appointment> flujo = service.getValidAppointments();

        // Assert
        StepVerifier.create(flujo.map(Appointment::getSpecialty))
                .expectNext("CARDIOLOGÍA", "PEDIATRÍA", "DERMATOLOGÍA")
                .verifyComplete();
    }
}
