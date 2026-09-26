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
public class Vuelo {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String airline;
    @Column(unique = true)
    private String numero;
    private LocalDateTime llegada;
    private LocalDateTime salida;
    private Integer asientos;
}
