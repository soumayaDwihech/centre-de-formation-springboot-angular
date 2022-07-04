package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Formateur {
    private static final long serialVersionUID = 1L;
    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String nom;
    private String pernom;
    private String mail;
    private long tel;
    
    @Enumerated(EnumType.STRING)
	private FType type;
    
    @ManyToOne
    @JoinColumn(name="organisme_id")
    private Organisme organisme;
    
    @JsonIgnore
	@OneToMany(mappedBy="formateur")
    private Set<Session_formation> session_formation;

	public Formateur() {
		super();
	}

	public Formateur(Long id, String nom, String pernom, String mail, long tel, FType type, Organisme organisme,
			Set<Session_formation> session_formation) {
		super();
		this.id = id;
		this.nom = nom;
		this.pernom = pernom;
		this.mail = mail;
		this.tel = tel;
		this.type = type;
		this.organisme = organisme;
		this.session_formation = session_formation;
	}

	public Formateur(String nom, String pernom, String mail, long tel, FType type, Organisme organisme,
			Set<Session_formation> session_formation) {
		super();
		this.nom = nom;
		this.pernom = pernom;
		this.mail = mail;
		this.tel = tel;
		this.type = type;
		this.organisme = organisme;
		this.session_formation = session_formation;
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

	public String getPernom() {
		return pernom;
	}

	public void setPernom(String pernom) {
		this.pernom = pernom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public FType getType() {
		return type;
	}

	public void setType(FType type) {
		this.type = type;
	}

	@JsonIgnoreProperties({ "formateur" ,"session_formation","participant"})
	public Organisme getOrganisme() {
		return organisme;
	}

	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}
	@JsonIgnoreProperties({ "formateur","organisme" })
	public Set<Session_formation> getSession_formation() {
		return session_formation;
	}

	public void setSession_formation(Set<Session_formation> session_formation) {
		this.session_formation = session_formation;
	}

}
