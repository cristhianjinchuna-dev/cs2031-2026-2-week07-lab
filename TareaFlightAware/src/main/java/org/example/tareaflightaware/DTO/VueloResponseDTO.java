package org.example.tareaflightaware.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VueloResponseDTO {
    private Long id;
    private String flightNumber;
    private String airline;
    private LocalDateTime aperturaTime;
    private LocalDateTime llegadaTime;
    private Integer availableSeats;
}
