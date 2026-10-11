package fr.esisar.bergerie.mouton;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Mouton extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String nom;

    @Column(nullable = false)
    public LocalDate dateNaissance;
}