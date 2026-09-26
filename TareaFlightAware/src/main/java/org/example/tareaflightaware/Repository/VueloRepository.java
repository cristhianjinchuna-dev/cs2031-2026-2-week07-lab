package org.example.tareaflightaware.Repository;

import org.example.tareaflightaware.Model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
    boolean existsByNumero(String numero);
}
