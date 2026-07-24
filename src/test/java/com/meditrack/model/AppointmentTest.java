package com.meditrack.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

public class AppointmentTest {

    @Test
    public void getters_datosDelConstructor_debenDevolverLosMismosValores() {
        // Arrange
        List<String> emails = Arrays.asList("paciente@email.com", "recepcion@email.com");
        Appointment appointment = new Appointment(
                "A1",
                "Ana Torres",
                "Cardiología",
                75.0,
                emails
        );

        // Act
        String id = appointment.getId();
        String patientName = appointment.getPatientName();
        String specialty = appointment.getSpecialty();
        Double costUsd = appointment.getCostUsd();
        List<String> notifyEmails = appointment.getNotifyEmails();

        // Assert
        assertEquals("A1", id);
        assertEquals("Ana Torres", patientName);
        assertEquals("Cardiología", specialty);
        assertEquals(Double.valueOf(75.0), costUsd);
        assertEquals(emails, notifyEmails);
    }

    @Test
    public void constructor_listaOriginalModificada_debeConservarCopiaDefensiva() {
        // Arrange
        List<String> originalEmails = new ArrayList<>();
        originalEmails.add("paciente@email.com");
        Appointment appointment = new Appointment(
                "A2",
                "Luis Mendoza",
                "Pediatría",
                50.0,
                originalEmails
        );

        // Act
        originalEmails.add("correo.agregado@email.com");
        List<String> emailsFromAppointment = appointment.getNotifyEmails();

        // Assert
        assertEquals(1, emailsFromAppointment.size());
        assertNotSame(originalEmails, emailsFromAppointment);
    }

    @Test
    public void getNotifyEmails_intentoDeModificacion_debeDevolverListaSoloLectura() {
        // Arrange
        Appointment appointment = new Appointment(
                "A3",
                "María López",
                "Dermatología",
                60.0,
                Arrays.asList("maria.lopez@email.com")
        );
        List<String> emailsFromAppointment = appointment.getNotifyEmails();

        // Act
        try {
            emailsFromAppointment.add("otro@email.com");
            fail("La lista devuelta por el getter no debe permitir modificaciones");
        } catch (UnsupportedOperationException expected) {
            // Assert
            assertEquals(1, appointment.getNotifyEmails().size());
        }
    }
}
