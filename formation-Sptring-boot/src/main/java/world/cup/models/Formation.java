package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Formation {
    private static final long serialVersionUID = 1L;
    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String titre;
    
    @Enumerated(EnumType.STRING)
	private FOType type_formation;
    private int nb_session;
    private int duree;
    private int budget;
    private int annee;
    
    @ManyToOne
    @JoinColumn(name="domaine_id")
    private Domaine domaine;

    @OneToMany(mappedBy="formation")
    private Set<Session_formation> session_formation;

    
	public Formation() {
		super();
	}
	

	public Formation(Long id, String titre, FOType type_formation, int nb_session, int duree, int budget, int annee,
			Domaine domaine, Set<Session_formation> session_formation) {
		super();
		this.id = id;
		this.titre = titre;
		this.type_formation = type_formation;
		this.nb_session = nb_session;
		this.duree = duree;
		this.budget = budget;
		this.annee = annee;
		this.domaine = domaine;
		this.session_formation = session_formation;
	}


	public Formation(String titre, FOType type_formation, int nb_session, int duree, int budget, int annee,
			Domaine domaine, Set<Session_formation> session_formation) {
		super();
		this.titre = titre;
		this.type_formation = type_formation;
		this.nb_session = nb_session;
		this.duree = duree;
		this.budget = budget;
		this.annee = annee;
		this.domaine = domaine;
		this.session_formation = session_formation;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public FOType getType_formation() {
		return type_formation;
	}

	public void setType_formation(FOType type_formation) {
		this.type_formation = type_formation;
	}

	public int getNb_session() {
		return nb_session;
	}

	public void setNb_session(int nb_session) {
		this.nb_session = nb_session;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}


	@JsonIgnoreProperties({ "formateur" ,"session_formation","participant","formation"})
	public Domaine getDomaine() {
		return domaine;
	}

	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}



	@JsonIgnoreProperties({  "session_formation","participant","formation"})
	public Set<Session_formation> getSession_formation() {
		return session_formation;
	}

	public void setSession_formation(Set<Session_formation> session_formation) {
		this.session_formation = session_formation;
	}

	@Override
	public String toString() {
		return "Formation{" +
				"id=" + id +
				", titre='" + titre + '\'' +
				", type_formation=" + type_formation +
				", nb_session=" + nb_session +
				", duree=" + duree +
				", budget=" + budget +
				", annee=" + annee +
				", domaine=" + domaine +
				", session_formation=" + session_formation +
				'}';
	}
}

