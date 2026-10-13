package fr.esisar.bergerie.mouton;
import fr.esisar.bergerie.cloche.Cloche;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Mouton extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String nom;

    @Column(nullable = false)
    public LocalDate dateNaissance;
    
    @OneToOne
    public Cloche cloche;
}