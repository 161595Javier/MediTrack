package com.meditrack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa una cita médica inmutable.
 */
public final class Appointment {

    private final String id;
    private final String patientName;
    private final String specialty;
    private final Double costUsd;
    private final List<String> notifyEmails;

    public Appointment(String id,
                       String patientName,
                       String specialty,
                       Double costUsd,
                       List<String> notifyEmails) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.patientName = Objects.requireNonNull(patientName, "El nombre del paciente es obligatorio");
        this.specialty = Objects.requireNonNull(specialty, "La especialidad es obligatoria");
        this.costUsd = Objects.requireNonNull(costUsd, "El costo es obligatorio");

        Objects.requireNonNull(notifyEmails, "La lista de correos es obligatoria");
        this.notifyEmails = Collections.unmodifiableList(new ArrayList<>(notifyEmails));
    }

    public String getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Double getCostUsd() {
        return costUsd;
    }

    /**
     * Devuelve una nueva copia defensiva y de solo lectura para impedir que
     * código externo modifique la colección interna de la cita.
     */
    public List<String> getNotifyEmails() {
        return Collections.unmodifiableList(new ArrayList<>(notifyEmails));
    }
}
