package fr.esisar.bergerie.mouton;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record PatchMoutonDTO(
        @Size(min = 1, max = 255) String nom,
        @PastOrPresent LocalDate dateNaissance
) {}