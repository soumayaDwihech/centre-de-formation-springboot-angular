package world.cup.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@Entity
public class Session_formation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column
	private Long id;
    private String Lieu;
    private Date date_debut;
    private Date date_fin;
    private Long nb_participant;
    @ManyToOne
    private Formateur formateur;
    
    @ManyToOne
    private Organisme organisme;
    
    @ManyToOne
    private Formation formation;
    
    
    @ManyToMany
    private Set<Participant> participants;
	
	
	
	public Session_formation() {
		super();
	}



	


	public Session_formation(String lieu, Date date_debut, Date date_fin, Long nb_participant, Formateur formateur,
			Organisme organisme, Formation formation, Set<Participant> participants) {
		Lieu = lieu;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nb_participant = nb_participant;
		this.formateur = formateur;
		this.organisme = organisme;
		this.formation = formation;
		this.participants = participants;
	}






	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLieu() {
		return Lieu;
	}



	public void setLieu(String lieu) {
		Lieu = lieu;
	}



	




	public Date getDate_debut() {
		return date_debut;
	}






	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}






	public Date getDate_fin() {
		return date_fin;
	}






	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}






	


	public Long getNb_participant() {
		return nb_participant;
	}






	public void setNb_participant(Long nb_participant) {
		this.nb_participant = nb_participant;
	}






	public Formateur getFormateur() {
		return formateur;
	}



	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}



	public Organisme getOrganisme() {
		return organisme;
	}



	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}


	@JsonIgnoreProperties({ "formateur" ,"session_formation","participant","formation"})

	public Formation getFormation() {
		return formation;
	}



	public void setFormation(Formation formation) {
		this.formation = formation;
	}



	public Set<Participant> getParticipant() {
		return participants;
	}


	
	public void setParticipant(Set<Participant> participants) {
		this.participants = participants;
	}


	@Override
	public String toString() {
		return "Session_formation{" +
				"id=" + id +
				", Lieu='" + Lieu + '\'' +
				", date_debut=" + date_debut +
				", date_fin=" + date_fin +
				", nb_participant=" + nb_participant +
				", formateur=" + formateur +
				", organisme=" + organisme +
				", formation=" + formation +
				", participants=" + participants +
				'}';
	}
}