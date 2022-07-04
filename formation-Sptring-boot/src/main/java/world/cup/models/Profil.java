package world.cup.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Profil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="id")
	private Long id;
	@Column(unique = true)
	private String libelle;
	
	@OneToMany(mappedBy="profil")
    private Set<Participant> participant;
	
	

	public Profil() {
	}

	public Profil(Long id, String libelle, Set<Participant> participant) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.participant = participant;
	}

	public Profil(String libelle) {
		this.libelle = libelle;
	}

	public Profil(Long idProfil, String libelle) {
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
