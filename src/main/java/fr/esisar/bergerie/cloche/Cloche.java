package fr.esisar.bergerie.cloche;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Cloche extends PanacheEntity {
	@Column(nullable = false)
	public String couleur;
	
	@Column(nullable = false)
	public String taille;
}
