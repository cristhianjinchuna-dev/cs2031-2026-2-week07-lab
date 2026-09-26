package org.example.tareaflightaware.Controller;

import jakarta.validation.Valid;
import org.example.tareaflightaware.DTO.VueloRequestDTO;
import org.example.tareaflightaware.DTO.VueloResponseDTO;
import org.example.tareaflightaware.Service.VueloService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping("/create")
    public ResponseEntity<VueloResponseDTO> create(@Valid @RequestBody VueloRequestDTO request) {
        return ResponseEntity.ok(vueloService.create(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<VueloResponseDTO>> search(@RequestParam(required = false) String flightNumber,
                                                         @RequestParam(required = false) String airline,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureFrom,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTo) {
        return ResponseEntity.ok(vueloService.search(flightNumber, airline, departureFrom, departureTo));
    }
}
