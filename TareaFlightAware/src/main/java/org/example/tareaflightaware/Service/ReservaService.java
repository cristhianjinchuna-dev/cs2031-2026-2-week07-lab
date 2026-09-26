package org.example.tareaflightaware.Service;

import lombok.RequiredArgsConstructor;
import org.example.tareaflightaware.DTO.ReservaResponseDTO;
import org.example.tareaflightaware.Model.Reserva;
import org.example.tareaflightaware.Model.User;
import org.example.tareaflightaware.Model.Vuelo;
import org.example.tareaflightaware.Repository.ReservaRepository;
import org.example.tareaflightaware.Repository.VueloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final VueloRepository vueloRepository;
    private final EmailService emailService;

    @Transactional
    public ReservaResponseDTO book(Long flightId, User user) {
        Vuelo vuelo = vueloRepository.findById(flightId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no ha sido encontrado"));

        if (!vuelo.getSalida().isAfter(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vuelo salio o esta en tránsito");
        }
        if (vuelo.getAsientos() <= 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay asientos disponibles");
        }
        boolean conflicto = reservaRepository.findByCustomerId(user.getId()).stream()
                .anyMatch(r -> r.getVuelo().getSalida().isBefore(vuelo.getLlegada())
                        && r.getVuelo().getLlegada().isAfter(vuelo.getSalida()));
        if (conflicto) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tienes otra reserva en ese horario!");
        }

        vuelo.setAsientos(vuelo.getAsientos() - 1);
        vueloRepository.save(vuelo);

        Reserva reserva = new Reserva();
        reserva.setVuelo(vuelo);
        reserva.setCustomer(user);
        reserva.setFirstName(user.getFirstName());
        reserva.setLastName(user.getLastName());
        reserva.setFechaReserva(LocalDateTime.now());
        reserva = reservaRepository.save(reserva);

        emailService.sendConfirmation(reserva);
        return toDTO(reserva);
    }

    public ReservaResponseDTO findById(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));
        return toDTO(reserva);
    }

    private ReservaResponseDTO toDTO(Reserva reserva) {
        return new ReservaResponseDTO(reserva.getId(), reserva.getVuelo().getId(), reserva.getVuelo().getNumero(),
                reserva.getCustomer().getId(), reserva.getFirstName(), reserva.getLastName(), reserva.getFechaReserva());
    }
}
