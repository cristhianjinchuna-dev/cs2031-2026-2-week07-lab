package org.example.tareaflightaware.Service;

import org.example.tareaflightaware.Model.Reserva;

public interface EmailService {

    void sendConfirmation(Reserva reserva);
}
