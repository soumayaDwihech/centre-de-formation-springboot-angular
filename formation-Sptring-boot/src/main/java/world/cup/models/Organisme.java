package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity

public class Organisme {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String libelle;
    
    @OneToMany(mappedBy="organisme")
    private Set<Participant> participant;
    
    @OneToMany(mappedBy="organisme")
    private Set<Formateur> formateur;
    
    @OneToMany(mappedBy="organisme")
    private Set<Session_formation> session_formation;

	public Organisme() {
		super();
	}

	public Organisme(Long id, String libelle, Set<Participant> participant, Set<Formateur> formateur,
			Set<Session_formation> session_formation) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.participant = participant;
		this.formateur = formateur;
		this.session_formation = session_formation;
	}

	public Organisme(String libelle, Set<Participant> participant, Set<Formateur> formateur,
			Set<Session_formation> session_formation) {
		super();
		this.libelle = libelle;
		this.participant = participant;
		this.formateur = formateur;
		this.session_formation = session_formation;
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


	@JsonIgnore
	public Set<Participant> getParticipant() {
		return participant;
	}

	public void setParticipant(Set<Participant> participant) {
		this.participant = participant;
	}


	@JsonIgnore
	public Set<Formateur> getFormateur() {
		return formateur;
	}

	public void setFormateur(Set<Formateur> formateur) {
		this.formateur = formateur;
	}

	@JsonIgnore
	public Set<Session_formation> getSession_formation() {
		return session_formation;
	}

	public void setSession_formation(Set<Session_formation> session_formation) {
		this.session_formation = session_formation;
	}
    
}