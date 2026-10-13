package fr.esisar.bergerie.mouton;

import java.time.LocalDate;

public record MoutonResponseDTO(
        Long id,
        String nom,
        LocalDate dateNaissance
) {}