package world.cup.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import org.springframework.validation.annotation.Validated;

@Validated
@Entity

public  class Participant {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participantId")
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private long tel;
	
    @Enumerated(EnumType.STRING)
	private EType type;
	
	@ManyToOne
	private Organisme organisme;
	
	@ManyToOne
	private Pays pays;
	
	@ManyToOne
	private Profil profil;
	
	
	public Participant() {
		super();
	}

	public Participant(String nom, String prenom, String email, long tel, EType type, Organisme organisme, Pays pays, Profil profil) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.type = type;
		this.organisme = organisme;
		this.pays = pays;
		this.profil = profil;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EType getType() {
		return type;
	}
	public void setType(EType type) {
		this.type = type;
	}
	public Organisme getOrganisme() {
		return organisme;
	}
	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	@Override
	public String toString() {
		return "Participant{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", email='" + email + '\'' +
				", tel=" + tel +
				", type=" + type +
				", organisme=" + organisme +
				", pays=" + pays +
				", profil=" + profil +
				'}';
	}
}

