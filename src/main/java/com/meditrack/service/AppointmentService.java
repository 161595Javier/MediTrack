package com.meditrack.service;

import com.meditrack.exception.AppointmentNotFoundException;
import com.meditrack.model.Appointment;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class AppointmentService {

    private static final Appointment DEFAULT_APPOINTMENT = new Appointment(
            "DEFAULT",
            "Paciente genérico",
            "MEDICINA GENERAL",
            1.0,
            List.of("notificaciones@meditrack.local")
    );

    private final Flux<Appointment> appointments;

    /**
     * Fuente principal en memoria: contiene tres citas válidas y dos inválidas.
     */
    public AppointmentService() {
        this(Flux.just(
                new Appointment(
                        "A1",
                        "Ana Torres",
                        "Cardiología",
                        75.0,
                        List.of("ana.torres@email.com")
                ),
                new Appointment(
                        "A2",
                        "Luis Mendoza",
                        "Pediatría",
                        50.0,
                        List.of("padres.luis@email.com", "recepcion@email.com")
                ),
                new Appointment(
                        "A3",
                        "María López",
                        "Dermatología",
                        60.0,
                        List.of("maria.lopez@email.com")
                ),
                new Appointment(
                        "A4",
                        "Carlos Ruiz",
                        "Neurología",
                        0.0,
                        List.of("carlos.ruiz@email.com")
                ),
                new Appointment(
                        "A5",
                        "Elena Castro",
                        "Oftalmología",
                        45.0,
                        Collections.emptyList()
                )
        ));
    }

    /**
     * Constructor auxiliar con visibilidad de paquete para que las pruebas
     * puedan controlar la fuente sin usar block() ni alterar la API pública.
     */
    AppointmentService(Flux<Appointment> appointments) {
        this.appointments = Objects.requireNonNull(appointments, "El flujo de citas es obligatorio");
    }

    public Flux<Appointment> getValidAppointments() {
        return appointments
                // filter descarta las citas que incumplen la regla de costo y correos.
                .filter(this::isValid)
                // map crea una nueva cita inmutable y normaliza la especialidad a mayúsculas.
                .map(this::normalizeSpecialty)
                // defaultIfEmpty garantiza una cita genérica cuando ninguna cita supera el filtro.
                .defaultIfEmpty(DEFAULT_APPOINTMENT);
    }

    public Mono<Appointment> findById(String id) {
        return getValidAppointments()
                // filter conserva únicamente la cita cuyo identificador coincide con el solicitado.
                .filter(appointment -> appointment.getId().equals(id))
                // next convierte el primer resultado del Flux en un Mono sin bloquear el hilo.
                .next()
                // switchIfEmpty transforma la ausencia de datos en una señal reactiva de error.
                .switchIfEmpty(Mono.defer(() -> Mono.error(new AppointmentNotFoundException(id))));
    }

    private boolean isValid(Appointment appointment) {
        return appointment.getCostUsd() > 0
                && !appointment.getNotifyEmails().isEmpty();
    }

    private Appointment normalizeSpecialty(Appointment appointment) {
        return new Appointment(
                appointment.getId(),
                appointment.getPatientName(),
                appointment.getSpecialty().toUpperCase(Locale.ROOT),
                appointment.getCostUsd(),
                appointment.getNotifyEmails()
        );
    }
}
