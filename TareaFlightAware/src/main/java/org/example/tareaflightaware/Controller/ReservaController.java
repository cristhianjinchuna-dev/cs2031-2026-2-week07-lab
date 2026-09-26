package org.example.tareaflightaware.Controller;

import jakarta.validation.Valid;
import org.example.tareaflightaware.DTO.ReservaRequestDTO;
import org.example.tareaflightaware.DTO.ReservaResponseDTO;
import org.example.tareaflightaware.Model.User;
import org.example.tareaflightaware.Service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/flights/book")
    public ResponseEntity<ReservaResponseDTO> book(@Valid @RequestBody ReservaRequestDTO request,
                                                   @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(reservaService.book(request.getFlightId(), user));
    }

    @GetMapping("/flight/book/{id}")
    public ResponseEntity<ReservaResponseDTO> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }
}
