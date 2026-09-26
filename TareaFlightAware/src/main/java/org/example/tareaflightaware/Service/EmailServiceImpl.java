package org.example.tareaflightaware.Service;

import org.example.tareaflightaware.Model.Reserva;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmailServiceImpl implements EmailService {

    // Genera flight_booking_email_${booking_id}.txt en la carpeta del proyecto.
    // LocalDateTime.toString() ya devuelve el formato ISO 8601 (ej: 2027-06-01T10:00)
    @Override
    public void sendConfirmation(Reserva reserva) {
        String body = "Pasajero: " + reserva.getFirstName() + " " + reserva.getLastName() + "\n"
                + "Número de vuelo: " + reserva.getVuelo().getNumero() + "\n"
                + "Salida: " + reserva.getVuelo().getSalida() + "\n"
                + "Llegada: " + reserva.getVuelo().getLlegada() + "\n"
                + "Fecha de reserva: " + reserva.getFechaReserva() + "\n";
        try {
            Files.writeString(Path.of("flight_booking_email_" + reserva.getId() + ".txt"), body);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
