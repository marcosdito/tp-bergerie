package fr.esisar.bergerie.mouton;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateMoutonDTO(
        @NotBlank @Size(max = 255) String nom,
        @NotNull @PastOrPresent LocalDate dateNaissance
) {}