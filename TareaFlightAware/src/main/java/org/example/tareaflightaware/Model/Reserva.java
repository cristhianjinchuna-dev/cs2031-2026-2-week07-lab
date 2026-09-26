package org.example.tareaflightaware.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
public class Reserva {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private User customer;

    private String firstName;
    private String lastName;
    private LocalDateTime fechaReserva;
}
