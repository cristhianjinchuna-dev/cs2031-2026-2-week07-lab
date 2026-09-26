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

public class ReservaResponseDTO {
    private Long id;
    private Long flightId;
    private String flightNumber;
    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDateTime bookingDate;
}
