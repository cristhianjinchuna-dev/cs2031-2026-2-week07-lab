package org.example.tareaflightaware.Service;

import lombok.RequiredArgsConstructor;
import org.example.tareaflightaware.DTO.VueloRequestDTO;
import org.example.tareaflightaware.DTO.VueloResponseDTO;
import org.example.tareaflightaware.Model.Vuelo;
import org.example.tareaflightaware.Repository.VueloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VueloService {
    private final VueloRepository vueloRepository;

    public VueloResponseDTO create(VueloRequestDTO request) {
        if (!request.getDepartureTime().isBefore(request.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La hora de salida debe ser anterior a la de llegada");
        }
        if (vueloRepository.existsByNumero(request.getFlightNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El número de vuelo ya existe");
        }

        Vuelo vuelo = new Vuelo();
        vuelo.setNumero(request.getFlightNumber());
        vuelo.setAirline(request.getAirline());
        vuelo.setSalida(request.getDepartureTime());
        vuelo.setLlegada(request.getArrivalTime());
        vuelo.setAsientos(request.getAvailableSeats());
        return toDTO(vueloRepository.save(vuelo));
    }

    public List<VueloResponseDTO> search(String flightNumber, String airline,
                                         LocalDateTime departureFrom, LocalDateTime departureTo) {
        return vueloRepository.findAll().stream()
                .filter(v -> flightNumber == null || v.getNumero().toUpperCase().contains(flightNumber.toUpperCase()))
                .filter(v -> airline == null || v.getAirline().toLowerCase().contains(airline.toLowerCase()))
                .filter(v -> departureFrom == null || !v.getSalida().isBefore(departureFrom))
                .filter(v -> departureTo == null || !v.getSalida().isAfter(departureTo))
                .map(v -> toDTO(v))
                .toList();
    }

    private VueloResponseDTO toDTO(Vuelo vuelo) {
        return new VueloResponseDTO(vuelo.getId(), vuelo.getNumero(), vuelo.getAirline(),
                vuelo.getSalida(), vuelo.getLlegada(), vuelo.getAsientos());
    }
}
