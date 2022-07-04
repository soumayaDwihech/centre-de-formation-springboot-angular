package world.cup.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Domaine implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="id")
	private Long id;
	@Column(unique = true)
	private String libelle;
	
	@OneToMany(mappedBy="domaine")
    private Set<Formation> formation;

	public Domaine() {
	}

	public Domaine(Long id, String libelle, Set<Formation> formation) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.formation = formation;
	}

	public Domaine(String libelle) {
		this.libelle = libelle;
	}

	public Domaine(Long id, String libelle) {
		id = id;
		this.libelle = libelle;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
