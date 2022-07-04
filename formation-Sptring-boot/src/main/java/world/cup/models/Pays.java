package world.cup.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pays implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="id")
	private Long id;
	@Column(unique = true)
	private String nom;
	
	@OneToMany(mappedBy="pays")
    private Set<Participant> participant;
	
	
	public Pays() {
	}

	public Pays(Long id, String nom, Set<Participant> participant) {
		super();
		this.id = id;
		this.nom = nom;
		this.participant = participant;
	}

	public Pays(String nom) {
		this.nom = nom;
	}

	public Pays(Long id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
